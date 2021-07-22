package com.testing.helper;

import com.testing.TestApplication;
import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
public class WaitHelper {

    @Value(value = "${implicitTime}")
    int implicitTime;

    @Value(value = "${explicitTime}")
    int explicitTime;

    @Value(value = "${pageLoadWaitTime}")
    int pageLoadWaitTime;

    private WebDriver driver;

    @Autowired
    @Lazy
    private Scenario scenario;


    public void setImplicitWait(int timeout, TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(timeout, unit);
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingIntervalInMilliSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.pollingEvery(pollingIntervalInMilliSeconds, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(NoSuchFrameException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(ElementNotVisibleException.class);
        return wait;
    }

    private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingIntervalInMilliSeconds) {

        Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).pollingEvery(pollingIntervalInMilliSeconds, TimeUnit.MILLISECONDS)
                .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                .ignoring(java.util.NoSuchElementException.class);
        return fwait;

    }

    public WebElement waitForElement(By element, int timeOutInSeconds, int pollingIntervalInMilliSeconds) {
        Wait<WebDriver> fwait = getFluentWait(timeOutInSeconds, pollingIntervalInMilliSeconds);
        WebElement webElement = driver.findElement(element);
        fwait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    public void waitForElement(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToClickable(By element, int timeOutInSeconds, int pollingIntervalInMilliSeconds) {
        Wait<WebDriver> fwait = getFluentWait(timeOutInSeconds, pollingIntervalInMilliSeconds);
        WebElement webElement = driver.findElement(element);
        fwait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    public void waitForElementToClickable(int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForSpinnerToDisappear(int timeOutInSeconds, int pollingEveryInMilliSeconds) {
        driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
        try {
            Wait<WebDriver> fwait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSeconds);
            fwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner-arc']")));
            Wait<WebDriver> fwait1 = getFluentWait(timeOutInSeconds, pollingEveryInMilliSeconds);
            fwait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loadWrapper']")));
        } catch (Exception e) {

        }
        driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
    }


}
