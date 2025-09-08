package CTTNotes.pages;
import AUT.listeners.ReportListeners;
import AUT.listeners.ReportListeners;
import AUT.utilities.CommonMethods;
import AUT.utilities.DataReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;
public class LoginPage {
    public WebDriver driver;
    public CommonMethods common;

    @FindBy(id = "un")
    WebElement username;

    @FindBy(id = "pw")
    WebElement password;

    @FindBy(xpath = "//*[@value='Sign In']/parent::div")
    WebElement btnLogin;

    @FindBy(xpath = "//a[@id='forgotPasswordLink']")
    WebElement forgotPasswordLink;


    @FindBy(xpath = "//button[contains(@class,'Button_SITE_HEADER_LAYOUT_ICON_NAVIGATION_MENU---btn')])[2]")
    WebElement menugrid1;

    @FindBy(xpath = "//button[contains(@class,'Button_SITE_HEADER_LAYOUT_ICON_NAVIGATION_MENU---btn')])[2]")
    WebElement menugrid2;

    public LoginPage(WebDriver driver, CommonMethods common) {
        this.driver = driver;
        this.common = common;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageDisplayed() {
        try {
            ReportListeners.logStep("pass", "Login page displayed.");
            System.out.println("Login page displayed.");
            return btnLogin.isDisplayed() && forgotPasswordLink.isDisplayed();
        } catch (Exception e) {
            ReportListeners.logStep("fail", "Login page not displayed. Exception occured : " + e);
            System.out.println("Login page not displayed. Exception occurred : " + e);
            return false;
        }
    }








    public Boolean onLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(btnLogin));
            System.out.println("Logged out - now on Login Page");
            return true;
        } catch (Exception e) {
            System.out.println("Logged out unsuccessful");
            return false;
        }
    }

    public Boolean exportButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(btnLogin));
            System.out.println("Logged out - now on Login Page");
            return true;
        } catch (Exception e) {
            System.out.println("Logged out unsuccessful");
            return false;
        }
    }
    public Alert getAlert() {
        try {
            ReportListeners.logStep("pass", "Switching to alert.");
            System.out.println("Switching to alert.");
            return driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            ReportListeners.logStep("fail", "Unable to switch to alert. Exception occured : " + e);
            System.out.println("Unable to switch to alert. Exception occurred : " + e);
            return null;
        }
    }

    public boolean isAlertPresent() {
        try {
            ReportListeners.logStep("pass", "Alert is present.");
            System.out.println("Alert is present.");
            return (driver.switchTo().alert() != null);
        } catch (NoAlertPresentException e) {
            ReportListeners.logStep("fail", "Alert not found. Exception occured : " + e);
            System.out.println("Alert not found. Exception occurred : " + e);
            return false;
        }
    }

    public String getAlertText() {
        try {
            driver.switchTo().alert().getText();
            ReportListeners.logStep("pass", "Text on the Alert popup is : " + driver.switchTo().alert().getText());
            System.out.println("Text on the Alert popup is : " + driver.switchTo().alert().getText());
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            ReportListeners.logStep("fail", "Unable to get Alert text. Exception occured : " + e);
            System.out.println("Unable to get Alert text. Exception occurred : " + e);
            return "";
        }
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            ReportListeners.logStep("pass", "Alert accepted.");
            System.out.println("Alert accepted.");
        } catch (NoAlertPresentException e) {
            ReportListeners.logStep("fail", "Unable to accept Alert. Exception occured : " + e);
            System.out.println("Unable to accept Alert. Exception occurred : " + e);
        }
    }

    public void clickVendorMenuGrid() {
        menugrid1.click();
        common.waitForSeconds(2);
        menugrid2.click();
    }
}