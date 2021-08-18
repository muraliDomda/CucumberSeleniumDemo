package com.testing.steps;


import com.testing.TestApplication;
import com.testing.pages.GooglePage;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
@TestPropertySource(locations = "classpath:application.properties")
public class GoogleSteps {

    @Autowired
    @Lazy
    WebDriver driver;

    @Autowired
    @Lazy
    GooglePage googlePage;

    @Autowired
    @Lazy
    Scenario scenario;

    @Given("^I am on Google Search page$")
    public void iAmOnGoogleSearchPage() {
        driver.get("https://www.google.co.in/");
    }


    @When("^i perfrom search with \"([^\"]*)\" text$")
    public void iPerfromSearchWithText(String searchString) throws Throwable {
        googlePage.enterTextInsSearchInput(searchString)
                .clickSearchButton();
    }

    @Then("^I see Search results matching to \"([^\"]*)\" string$")
    public void iSeeSearchResultsMatchingToString(String arg0) throws Throwable {
        List<WebElement> linksList = googlePage.getSearchResultSLinks(arg0);
        for (WebElement ele:linksList) {
            scenario.write(ele.getText());
        }

    }

}
