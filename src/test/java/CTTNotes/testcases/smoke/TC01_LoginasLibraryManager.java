package CTTNotes.testcases.smoke;

import AUT.utilities.ConfigReader;
import AUT.utilities.TestCaseId;
import CTTNotes.base.AppConfig;
import CTTNotes.base.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class TC01_LoginasLibraryManager extends DriverManager {
    private static final Logger LOG = LogManager.getLogger(TC01_LoginasLibraryManager.class);
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
    public void TC01_LoginasLibraryManager() {
        try {
            System.out.println("Test initialized with web-driver");

//            loginPage.performAppianLoginSuperUser(url);
//            loginPage.verifySuccessfulLogin();
            homePage.openItemFromDropdown("NOTE LIBRARY");
        } catch (Exception e) {
            LOG.error("Error occurred in TC01_VerifyLoginWithLibraryManager of CTT Notes: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void endTest() {
        driver.quit();
    }
}
