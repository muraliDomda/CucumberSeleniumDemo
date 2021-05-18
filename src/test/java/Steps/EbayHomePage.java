package Steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class EbayHomePage  {

    WebDriver driver;
    Scenario scenario;


//    public class LoginSteps {
//
//        Controller controller;
//        User user;
//        LoginWorkflow loginPage;
//
//        public LoginSteps(Controller controller, User user) {
//            this.controller = controller;
//            this.user = user;
//            loginPage = new LoginPage(this.controller);
//        }
//
//        @When("^I login$")
//        public void login() {
//            this.loginPage.loadEnvironment();
//            this.loginPage.login(this.user);
//        }
//    }

    public EbayHomePage(WebDriverHooks webDriverHooks) {
        this.driver = webDriverHooks.getDriver();
        this.scenario =webDriverHooks.getScenario();
    }

    @Given("I am on ebay home page")
    public void i_am_on_ebay_home_page() {
        driver.get("https://www.ebay.com/");
        System.out.println(" i have launcheed ebay home page");
        scenario.log(" test is logging");
    }
    @Then("i See homepage")
    public void i_see_homepage() {
        System.out.println(" i am on ebay home page");
    }
}
