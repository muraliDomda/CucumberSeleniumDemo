package com.testing.steps;


import com.testing.TestApplication;
import com.testing.pages.EbayHomePage;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.velocity.runtime.directive.Foreach;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Locale;


@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
@TestPropertySource(locations = "classpath:application.properties")

public class MyStepdefs1 {

    @Autowired
    @Lazy
    WebDriver driver;

    @Autowired
    @Lazy
    Scenario scenario;

    @Autowired
    @Lazy
    EbayHomePage ebayHomePage;


    @Given("I am on ebay home page")
    public void i_am_on_ebay_home_page() throws InterruptedException {
//        driver.get("https://mvnrepository.com/artifact/io.cucumber/cucumber-spring");
        driver.get("https://www.ebay.com/");
    }


    @Then("^i See homepage$")
    public void iSeeHomepage() {
        boolean searchButton = driver.findElement(By.cssSelector("input[value='Search']")).isDisplayed();
        Assert.assertTrue("HomePage is not displayed", searchButton);
    }




    @When("^I perfrom search operartion with \"([^\"]*)\"$")
    public void iPerfromSearchOperartionWith(String searchString) throws Throwable {

        ebayHomePage.enterValueInSearchInput(searchString).clicKOnSearchButton();
//        driver.findElement(By.cssSelector("input#gh-ac")).sendKeys(searchString);
//        driver.findElement(By.cssSelector("input[value='Search']")).click();
        Thread.sleep(5000);
    }

    @Then("^I see results matching to my search \"([^\"]*)\"$")
    public void iSeeResultsMatchingToMySearch(String searchString) throws Throwable {
        List<WebElement> elementsList = driver.findElements(By.cssSelector("ul.srp-results li.s-item h3"));
        for (int i = 0; i < elementsList.size()-1; i++) {
            String elementText = elementsList.get(i).getText();
            scenario.write("expected:" + searchString);
            scenario.write("Actual :" + elementText);
            Assert.assertTrue("searching element is not found in results", elementText.toLowerCase().contains(searchString.toLowerCase()
            ));
        }
    }
}
