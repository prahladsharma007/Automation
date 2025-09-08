//package AUT.utilities;
//
//import com.appiancorp.ps.automatedtest.fixture.SitesFixture;
//import org.openqa.selenium.WebDriver;
//
//public class WebDriverMgr {
//    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//    public static ThreadLocal<SitesFixture> fixture = new ThreadLocal<>();
//
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//
//    public static SitesFixture getFixture() {
//        return fixture.get();
//    }
//
//    public static void setWebDriver(WebDriver driverParam) {
//        driver.set(driverParam);
//    }
//
//    public static void setFixture(SitesFixture fixtureParam) {
//        fixture.set(fixtureParam);
//    }
//}