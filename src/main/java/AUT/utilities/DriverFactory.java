package AUT.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":

                    ChromeOptions chromeOption = new ChromeOptions();
//                    chromeOption.addArguments("--headless");
                    chromeOption.addArguments("--start-maximized");
                    chromeOption.addArguments("force-device-scale-factor=" + ConfigReader.getValue("browser_zoom"));
                    chromeOption.addArguments("high-dpi-support=" + ConfigReader.getValue("browser_zoom"));
                    String folderPath = "./driver";
                    File folder = new File(folderPath);
                    if (folder.exists() && folder.isDirectory()) {
                        System.out.println("The folder exists.");
                        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
                    }
//                    System.setProperty("webdriver.chrome.driver", "C:/Users/SHARMP3B/Automation/chromedriver.exe");
                    driver = new ChromeDriver(chromeOption);

                    break;
                case "firefox":
//                    FirefoxOptions firefoxOption = new FirefoxOptions();
//                    firefoxOption.addArguments("--start-maximized");
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "edge":
                    EdgeOptions edgeOption = new EdgeOptions();
                    Map<String, Object> prefs = new LinkedHashMap<>();
                    prefs.put("user_experience_metrics.personalization_data_consent_enabled", Boolean.TRUE);
                    edgeOption.setExperimentalOption("prefs", prefs);
//                    edgeOption.addArguments("--headless");
                    edgeOption.addArguments("--start-maximized");
                    edgeOption.addArguments("force-device-scale-factor=" + ConfigReader.getValue("browser_zoom"));
                    edgeOption.addArguments("high-dpi-support=" + ConfigReader.getValue("browser_zoom"));
                    driver = new EdgeDriver(edgeOption);
                    break;
                default:
                    System.out.println("Browser not supported, using Chrome as default");
                    ChromeOptions option = new ChromeOptions();
                    option.addArguments("--start-maximized");
                    option.addArguments("force-device-scale-factor=" + ConfigReader.getValue("browser_zoom"));
                    option.addArguments("high-dpi-support=" + ConfigReader.getValue("browser_zoom"));
                    driver = new ChromeDriver(option);
            }
        }
        return driver;
    }
}
