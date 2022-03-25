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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@ContextConfiguration(classes = {TestApplication.class})
@PropertySource("classpath:application.properties")
@TestPropertySource(locations = "classpath:application.properties")
public class ZeeWebSteps {

    @Autowired
    @Lazy
    WebDriver driver;

    @Autowired
    @Lazy
    GooglePage googlePage;

    @Autowired
    @Lazy
    Scenario scenario;

    @Value(value = "${appUrl}")
    String appUrl1;

    @Value(value = "${env}")
    String env;


    @Given("I am on zee Home page")
    public void iAmOnZeeHomePage() {
        System.out.println("+++++++++"+appUrl1);
        System.out.println("+++++++++"+ env);

        driver.get(appUrl1);
    }

    @When("I choose Movies via Banner")
    public void iChooseMoviesViaBanner() {
    }

}
