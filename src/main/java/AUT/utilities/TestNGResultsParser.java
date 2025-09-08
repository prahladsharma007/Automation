package AUT.utilities;

import AUT.constants.CommonConstants;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestNGResultsParser {

//    static String testNGResultsFilePath = CommonConstants.projectDirectory+ "/target/surefire-reports/testng-results.xml";
    static String testNGResultsFilePath = CommonConstants.projectDirectory+ "/testng-results.xml";

    public static Map<String, String> fetchTestCaseNamesWithStatus() {
        Map<String, String> testCasesStatus = new HashMap<>();
        
        try {
            // Read the JSON file
        	String jsonPath = CommonConstants.projectDirectory+"/TestResultsJson/testStatusReport.json";
            File file = new File(jsonPath);
            FileReader fileReader = new FileReader(file);
            StringBuilder stringBuilder = new StringBuilder();

            char[] buffer = new char[1024];
            int num;
            while ((num = fileReader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, num);
            }

            fileReader.close();

            // Parse JSON content
            String content = stringBuilder.toString();
            JSONObject jsonObject = new JSONObject(content);

            // Iterate over the entries in the JSON object
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String testName = keys.next();
                String status = jsonObject.getString(testName);

                // Add the test method name and status to the hashmap
                if ("Passed".equalsIgnoreCase(status)) {
                    testCasesStatus.put(testName, "Passed");
                } else if ("Failed".equalsIgnoreCase(status)) {
                    testCasesStatus.put(testName, "Failed");
                }
            }

            // Printing the results
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<Inside JSON RESULT PARSER>>>>>>>>>>>>>>>>>>>>>");
            for (Map.Entry<String, String> entry : testCasesStatus.entrySet()) {
                System.out.println("Test Case: " + entry.getKey() + " - Status: " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return testCasesStatus;
    }

    public static void main(String[] args) {

        Map<String, String> testCaseStatusMap = fetchTestCaseNamesWithStatus();

        // Printing the test cases and their statuses
        for (Map.Entry<String, String> entry : testCaseStatusMap.entrySet()) {
            System.out.println("Test Case: " + entry.getKey() + " - Status: " + entry.getValue());
        }
    }
}