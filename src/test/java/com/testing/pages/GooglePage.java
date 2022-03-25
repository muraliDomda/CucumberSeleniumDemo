package com.testing.pages;

import com.testing.TestApplication;
import com.testing.helper.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@Component
@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")


public class GooglePage {


    @Autowired
    @Lazy
    WebElementHelper webElementHelper;

    @Autowired
    @Lazy
    WebDriver driver;

    By googleSearchInput = By.name("q");
    By search_Button = By.name("btnK");



    public GooglePage enterTextInsSearchInput(String searchString){
        webElementHelper.setText(googleSearchInput,searchString);
//        driver.findElement(googleSearchInput).sendKeys(searchString);
        return this;
    }

    public GooglePage clickSearchButton(){

//        webElementHelper.clickElement(search_Button);
//        webElementHelper.getWebElement(search_Button).click();
        driver.findElement(search_Button).click();
        return this;
    }


    public List<WebElement> getSearchResultSLinks(String searchString){
        List<WebElement> elementsList = driver.findElements(By.xpath("//div[@data-async-context='query:"+searchString+"']//a"));
       return  elementsList;
    }

}
