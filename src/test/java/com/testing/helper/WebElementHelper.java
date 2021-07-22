package com.testing.helper;

import com.testing.TestApplication;
import cucumber.api.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
public class WebElementHelper {

    @Value(value = "${implicitTime}")
    int implicitTime;

    @Value(value = "${explicitTime}")
    int explicitTime;

    @Value(value = "${pageLoadWaitTime}")
    int pageLoadWaitTime;

    @Value(value = "${pollingInterval}")
    int pollingInterval;


    private WebDriver driver;

    @Autowired
    @Lazy
    private Scenario scenario;

    @Autowired
    @Lazy
    private WaitHelper waitHelper;



//    public void waitForPageToCompleteState(){
//        int counter = 0;
//        int maxNoOfEntries=5;
//        while(maxNoOfEntries >0 && counter!=maxNoOfEntries){
//            try{
//
//            }
//            catch(Exception e){
//
//            }
//        }
//    )
//    }

    public WebElement getWebElement(By locator) {
        try {
            waitHelper.waitForSpinnerToDisappear(explicitTime, pollingInterval);
            return waitHelper.waitForElement(locator, explicitTime, pollingInterval);
        } catch (StaleElementReferenceException exception) {
            waitHelper.waitForSpinnerToDisappear(explicitTime, pollingInterval);
            return waitHelper.waitForElement(locator, explicitTime, pollingInterval);
        }
    }
}
