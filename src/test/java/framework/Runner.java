package framework;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeSuite;

public class Runner {

    public static Page page;
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        Config config = new Config();
        config.LoadConfig();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        page = browser.newPage();
    }
}
