package AUT.reports;

import AUT.constants.CommonConstants;
import AUT.utilities.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

	public static ExtentReports extentReport;
	public static String extentReportFile;
	public static long suiteStartTime;

	@BeforeTest
	public static ExtentReports setupExtentReport() {
		try {
			System.out.println("Setting up Extent Report...");
			String filePath = CommonConstants.getExtentReportFilepath();
			String fileName = "ExecutionReport_" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + ".html";
			extentReportFile = filePath + fileName;
			System.out.println("Report File Path: " + extentReportFile);

			extentReport = new ExtentReports();
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setReportName(ConfigReader.getValue("test_report_name"));
			sparkReporter.config().setDocumentTitle(ConfigReader.getValue("test_report_name"));
			sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

			extentReport.attachReporter(sparkReporter);
			extentReport.setSystemInfo("Application", ConfigReader.getValue("application"));
			extentReport.setSystemInfo("Application URL", ConfigReader.getValue("url"));
			extentReport.setSystemInfo("Browser", ConfigReader.getValue("browser"));
			extentReport.setSystemInfo("Tested By", ConfigReader.getValue("tested_by"));
			extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
			extentReport.setSystemInfo("Java version", System.getProperty("java.version"));
			System.out.println("Extent Report setup completed.");

			suiteStartTime = System.currentTimeMillis();

			return extentReport;
		} catch (Exception e) {
			System.err.println("Error occurred while setting up Extent Report: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static ExtentReports getExtentReport() {
		if (extentReport == null) {
			setupExtentReport();
		}
		return extentReport;
	}

	public static void flushExtentReport() {
		if (extentReport != null) {
			long suiteEndTime = System.currentTimeMillis();
			long suiteExecutionTime = suiteEndTime - suiteStartTime;
			String totalTime = String.format("%02d:%02d:%02d",
					(suiteExecutionTime / (1000 * 60 * 60)) % 24,
					(suiteExecutionTime / (1000 * 60)) % 60,
					(suiteExecutionTime / 1000) % 60);

			// Add the total execution time to the Extent report
			extentReport.setSystemInfo("Total Execution Time", totalTime);
			extentReport.flush();
		}
	}

	public static ExtentTest createTest(String testName) {
		if (extentReport == null) {
			setupExtentReport();
		}
		return extentReport.createTest(testName);
	}
}
