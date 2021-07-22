package com.testing.pages;

import com.testing.TestApplication;
import cucumber.api.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
public class EbayHomePage {

    @Value(value = "${implicitTime}")
    int implicitTime;

    @Value(value = "${explicitTime}")
    int explicitTime;

    @Value(value = "${pageLoadWaitTime}")
    int pageLoadWaitTime;

    @Autowired
    @Lazy
    WebDriver driver;

    @Autowired
    @Lazy
    private Scenario scenario;


    private By searchInput = By.cssSelector("input#gh-ac");
    private By searchButton = By.cssSelector("input[value='Search']");

    public EbayHomePage enterValueInSearchInput(String searchString){
       driver.findElement(searchInput).sendKeys(searchString);
    return this;
    };

    public EbayHomePage clicKOnSearchButton(){
        driver.findElement(searchButton).click();
        return this;
    };

}
