package AUT.listeners;

import AUT.constants.CommonConstants;
import AUT.reports.ExtentReportManager;
import AUT.utilities.TestCaseId;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportListeners implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private HashMap<Integer, String> testStatusMap = new HashMap<>();
    private static HashMap<Integer, String> testStatusMap2 = new HashMap<>();

//    public int getTestId(String[] groups) {
//        int testCaseId =0;
//        for (String group : groups){
//            if(group.contains("TestCaseId=")){
//                testCaseId = Integer.parseInt(group.replace("TestCaseId=",""));
//            }
//        }
//        return testCaseId;
//    }

    public int getTestCaseId(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        int id = 0;
        if (method.isAnnotationPresent(TestCaseId.class)) {
            TestCaseId testCaseId = method.getAnnotation(TestCaseId.class);
            id = testCaseId.value();
            System.out.println("Test Case ID: " + id);
        }
        return id;
    }

    public void multipleTestSuccessStatusUpdate(int[] testcaseID, String testcaseStatus) {
        int Length = testcaseID.length;
        for(int i=0;i<Length;i++) {
            testStatusMap2.put(testcaseID[i], testcaseStatus);
       }
    }


    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.setupExtentReport();
        System.out.println("Report initialized.");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushExtentReport();
        System.out.println("Extent Report flushed.");
        openReportInBrowser(ExtentReportManager.extentReportFile);
        testStatusMap.putAll(testStatusMap2);
        createJsonReport(testStatusMap);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentReportManager.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        test.log(Status.INFO, "Test case '" + result.getMethod().getMethodName() + "' execution started.");
        System.out.println("Execution of '" + result.getMethod().getMethodName() + "' test has started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extentTest.get();
        String[] groups = result.getMethod().getGroups();
//        int testCaseId = getTestCaseId(groups);
        int testCaseId = getTestCaseId(result);
        test.log(Status.PASS, "Test case '" + result.getMethod().getMethodName() + "' execution passed.");
        System.out.println("Test case '" + result.getMethod().getMethodName() + "' execution passed.");
        testStatusMap.put(testCaseId, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        String[] groups = result.getMethod().getGroups();
        int testCaseId = getTestCaseId(result);
        test.log(Status.FAIL, "Test case '" + result.getMethod().getMethodName() + "' execution failed.");
        test.log(Status.FAIL, result.getThrowable());

        WebDriver driver = null;
        Object testObject = result.getInstance();
        Class<?> clazz = result.getTestClass().getRealClass();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("Declared field: " + field.getName());
        }
        try {
            driver = (WebDriver) clazz.getField("driver").get(testObject);
            //driver = (WebDriver) testObject.getClass().getDeclaredField("driver");
        } catch (Exception e) {
            System.out.println("Web Driver Value is :"+ String.valueOf(driver));
            System.out.println("DIDNT GET DRIVER********************");
            e.printStackTrace();
        }
        if (driver != null) {
            String fileName = "Screenshot_" + result.getMethod().getMethodName() + "_"
                    + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + ".png";
            ReportListeners.logScreenshotStep(driver, fileName);
        } else {
            System.out.println("WebDriver instance not found. Cannot capture screenshot.");
        }
        System.out.println("Test case '" + result.getMethod().getMethodName() + "' execution failed.");
        testStatusMap.put(testCaseId, "Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.log(Status.SKIP, "Test case '" + result.getMethod().getMethodName() + "' execution skipped.");
        System.out.println("Test case '" + result.getMethod().getMethodName() + "' execution skipped.");
    }

    public static void startTest(String testName) {
        ExtentTest test = ExtentReportManager.createTest(testName);
        extentTest.set(test);
        test.log(Status.INFO, "Test case '" + testName + "' execution started.");
        System.out.println("Execution of '" + testName + "' test has started.");
    }

    public static void endTest() {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.INFO, "Test case execution ended.");
            System.out.println("Execution of test case ended.");
        }
    }

    public static void logStep(String status, String stepDescription) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            switch (status) {
                case "info":
                    test.log(Status.INFO, stepDescription);
                    System.out.println(stepDescription);
                    break;
                case "pass":
                    test.log(Status.PASS, stepDescription);
                    break;
                case "fail":
                    test.log(Status.FAIL, stepDescription);
                    Assert.fail(stepDescription);
                    break;
            }

        }
    }

    public static void logScreenshotStep(WebDriver driver, String stepDescription) {
        try {
            ExtentTest test = extentTest.get();
            if (test != null) {
                test.log(Status.INFO, stepDescription);
                System.out.println(stepDescription);

                // Capture screenshot
                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String fileName = "Screenshot_" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + ".png";
                String destinationPath = "./Screenshots/" + fileName; // Save in "Screenshots" folder
                try {
                    FileHandler.copy(source, new File(destinationPath));
                } catch (IOException e) {
                    throw new RuntimeException("Error occurred while copying screenshot to " + destinationPath);
                }

                String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                // Log screenshot in the Extent report as base64
                test.info("Screenshot: ", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());

                System.out.println("Screenshot captured: " + fileName);
            }
        } catch (Exception e){
            System.out.println("Screenshot not taken: "+e);
        }
    }

    private void openReportInBrowser(String reportFilePath) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File reportFile = new File(reportFilePath);
            try {
                desktop.open(reportFile);
                System.out.println("Report opened in default web browser.");
            } catch (IOException e) {
                System.out.println("Error opening report file: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop is not supported, unable to open report automatically.");
        }
    }

    private void createJsonReport(Map<Integer, String> testStatusMap) {
        String jsonPath = CommonConstants.projectDirectory + "/TestResultsJson/testStatusReport.json";
        File jsonFile = new File(jsonPath);

        // Ensure the directory exists
        File directory = new File(jsonFile.getParent());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Delete existing file if it exists
        if (jsonFile.exists()) {
            jsonFile.delete();
        }

        // Create a new JSON file
        try {
            if (jsonFile.createNewFile()) {
                System.out.println("JSON file created: " + jsonFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Prepare JSON content
        JSONObject jsonObject = new JSONObject(testStatusMap);

        // Write JSON content to the file
        try (FileWriter file = new FileWriter(jsonPath)) {
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

