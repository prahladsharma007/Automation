package CTTNotes.testcases.smoke;

import AUT.utilities.ConfigReader;
import AUT.utilities.TestCaseId;
import CTTNotes.base.AppConfig;
import CTTNotes.base.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class TC01_EELogin extends DriverManager {
    private static final Logger LOG = LogManager.getLogger(TC01_EELogin.class);
    String url = AppConfig.url;


    @Parameters("browser")
    @BeforeMethod
    void beforeClass(@Optional("browser") String browser) throws MalformedURLException {
        if (browser.isEmpty() || browser.equalsIgnoreCase("browser")) {
            System.out.println("reading browser value from config properties file");
            browser = ConfigReader.getValue("browser");
        }
        //browser = "chrome";
        System.out.println("final browser value is '" + browser + "'");
        setDriver(browser);
    }

    @Test(priority = 1, description = "Login with Library Manager and verify if access is there or not for Notes Library")

    @TestCaseId(117074)
    public void TC01_EELogin() throws InterruptedException {
        try {
            System.out.println("Test initialized with web-driver");
            driver.get("https://qa.eventexperio.com/");
            driver.findElement(By.cssSelector("button[class='primary-btn text-[15px]']")).click();
            driver.findElement(By.id("phn-input")).sendKeys("9999999999");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            Thread.sleep(2000);
        } catch (Exception e) {
            LOG.error("Error occurred in TC01_EELogin of CTT Notes: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
