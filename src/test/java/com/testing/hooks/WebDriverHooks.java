package com.testing.hooks;

import com.testing.TestApplication;
import com.testing.utilities.Listener;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
@TestPropertySource(locations = "classpath:application.properties")
public class WebDriverHooks {

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


    @Before(order = 1)
    public void setUp(Scenario scenario) throws InterruptedException {
        TestApplication.scenarioThreadLocal.set(scenario);
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageLoadWaitTime,TimeUnit.SECONDS);
        EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
        Listener listener = new Listener();
        TestApplication.webDriverEventListenerThreadLocal.set(listener);
        edriver.register(listener);
        TestApplication.driverThreadLocal.set(edriver);

    }



    @After(order = 1)
    public void tearDown(Scenario scenario) throws InterruptedException {
        byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenShot, "image/png");
        TestApplication.scenarioThreadLocal.remove();
        if (driver != null) {
            driver.quit();
        } else {
            System.out.println("driver is already quit");
        }
    }

}
