package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/FeatureFile", glue ={"Steps"},
        plugin = {"pretty","html:target/Report1.html", "json:target/json-report/cucumber.json" ,"junit:target/junit-report"},
//dryRun =true,
tags = "@t3"
//name =  "test"
)
public class TestRunner {

}
