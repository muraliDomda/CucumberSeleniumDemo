package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverHooks {

    private WebDriver driver;
    private Scenario scenario;

    @Before(order =1)
    public void setUp(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("Order 1 hook");
        this.scenario =scenario;
    }

    @Before(order =1)
    public void setUpNew() {
        System.out.println("Order 2 hook");
    }

    @After
    public void tearDown(Scenario scenario) {
            byte[] scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(scr,"image/png", scenario.getName());
            driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Scenario getScenario() {
        return scenario;
    }
}
