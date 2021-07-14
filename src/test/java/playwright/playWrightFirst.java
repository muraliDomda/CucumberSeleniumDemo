package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class playWrightFirst {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.screenshot();
            page.video().saveAs(Paths.get("C:\\Automation\\cucumberSeleniumDemo\\src")) ;

            page.navigate("http://playwright.dev");
            System.out.println(page.title());
            Page page1 = browser.newPage();
            page1.navigate("http://playwright.dev");
            browser.close();




        }



    }
}
