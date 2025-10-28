package CTTNotes.base;
import AUT.constants.CommonConstants;
import AUT.listeners.ReportListeners;
import AUT.utilities.CommonMethods;
import AUT.utilities.ConfigReader;
import CTTNotes.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
public class DriverManager {
    public WebDriver driver ;
    public CommonMethods common;
    public HomePage homePage;
    public LoginPage loginPage;

    @BeforeSuite
    public void beforeEverything() throws InterruptedException {
        System.out.println("Starting chrome instances killing");
        //killChromeInstances();
        Thread.sleep(5000);
        System.out.println("Chrome instances killed");
    }


    public void setDriver(String testBrowser) throws MalformedURLException {
        switch(testBrowser) {
            case "chrome": {
//                 driver = new ChromeDriver();
//                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--headless");
                // chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("force-device-scale-factor=" + ConfigReader.getValue("browser_zoom"));
                chromeOptions.addArguments("high-dpi-support=" + ConfigReader.getValue("browser_zoom"));
                File downloadFolder = new File(CommonConstants.getDownloadFilePath());
                if (!downloadFolder.exists()) downloadFolder.mkdirs();
                try {
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("download.default_directory",downloadFolder.getAbsolutePath());
                    prefs.put("download.prompt_for_download",false);
                    prefs.put("download.directory_upgrade",true);
                    chromeOptions.setExperimentalOption("prefs",prefs);
                    System.out.println("Preferences for the download folder path is set");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Could not set download behaviour" + e);
                    throw e;
                }
//                String folderPath = "./driver";
//                File folder = new File(folderPath);
//                if (folder.exists() && folder.isDirectory()) {
//                    System.out.println("The folder exists.");
//                    System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
//                }
                driver = new ChromeDriver(chromeOptions);
                System.out.println("***** Browser is chrome *****");
                break;
            }
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
        common = new CommonMethods(driver);
        loginPage = new LoginPage(driver, common);
        homePage = new HomePage(driver, common);
        driver.manage().deleteAllCookies();
        common.createFolder("Screenshots");
    }

    public void startTest(String testName) {
        ReportListeners.startTest(testName);
    }

    public void endTest() {
        ReportListeners.endTest();
    }

    public void logStep(String status, String stepDescription) {
        ReportListeners.logStep(status , stepDescription);
    }

    protected void killChromeInstances(){
        try {
            // Execute command to list all Chrome processes
            Process process = Runtime.getRuntime().exec("tasklist /fi \"imagename eq chrome.exe\"");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");

            // Read the output of the command
            java.io.InputStream is = process.getInputStream();
            java.util.Scanner scanner = new java.util.Scanner(is).useDelimiter("\\A");
            String output = scanner.hasNext() ? scanner.next() : "";

            // Split the output into lines
            String[] lines = output.split(System.getProperty("line.separator"));

            // Iterate through each line
            for (String line : lines) {
                // Check if the line contains Chrome process information
                if (line.contains("chrome.exe")) {
                    // Extract the process ID (PID)
                    String[] parts = line.trim().split("\\s+");
                    String pid = parts[1];

                    // Kill the Chrome process using its PID
                    Process killProcess = Runtime.getRuntime().exec("taskkill /F /PID " + pid);
                    killProcess.waitFor(); // Wait for the process to finish
                }
            }
            System.out.println("All Chrome instances killed successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @AfterTest
//    public void tearDown() {
//        System.out.println("Starting test tear down.");
//        if (fixture != null) {
//            fixture.tearDown();
//            System.out.println("Fixture tear down completed.");
//        }
//        if (driver != null) {
//            driver.quit();
//            System.out.println("WebDriver instances closed.");
//        }
//        System.out.println("Test tear down completed.");
//    }



}
