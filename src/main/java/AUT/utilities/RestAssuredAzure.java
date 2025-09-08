package AUT.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;

public class RestAssuredAzure {

    public RestAssuredAzure() {
        String baseUri = ConfigReader.getValue("azure_devops_base_uri");
        String apiToken = ConfigReader.getValue("azure_devops_api_token");
        io.restassured.RestAssured.baseURI = baseUri;
        io.restassured.RestAssured.authentication = preemptive().basic("<username>", apiToken);
    }

    public List<String> listOfTestPlans(String organization, String project) {
        String apiVersion = "7.2-preview.1";
        String requestUri = String.format("/%s/%s/_apis/testplan/plans?api-version=%s", organization, project, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        return jsonPath.getList("value.rootSuite.name");
    }

    public static String getLatestRun(String organization, String project) {
        String apiVersion = "7.2-preview.3";
        String requestUri = String.format("/%s/%s/_apis/test/runs?api-version=%s", organization, project, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
//        System.out.println(body);
//        ReportListeners_Old.logStep("Hello "+body);
        JsonPath jsonPath = new JsonPath(body);
        List<Integer> res = jsonPath.getList("value.id");
        return res.get(res.size()-1).toString();
    }

    public JsonPath listOfRuns(String organization, String project) {
        String apiVersion = "7.2-preview.1";
        String requestUri = String.format("/%s/%s/_apis/test/runs??api-version=%s", organization, project, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        return jsonPath;
    }

    public static Map<String, String> getRunTestResult(String organization, String project, String runId) {
        String apiVersion = "7.2-preview.6";
        String requestUri = String.format("/%s/%s/_apis/test/Runs/%s/results?outcomes&api-version=%s", organization, project, runId, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
//        System.out.println(body);

        JSONObject jsonObj = new JSONObject(body);
        JSONArray jsonArray = jsonObj.getJSONArray("value");

        Map<String, String> testCases = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject testCase = jsonArray.getJSONObject(i);
            JSONObject testCaseDetails = testCase.getJSONObject("testCase");
            String testCaseOutcome = testCase.getString("outcome");
            String name = testCaseDetails.getString("name");

            testCases.put(name, testCaseOutcome);
        }

        return testCases;
    }

    public List<String> listOfTestCases(String organization, String project, String planId, String suiteId) {
        String apiVersion = "7.2-preview.3";
        String requestUri = String.format("/%s/%s/_apis/testplan/Plans/%s/Suites/%s/TestCase?api-version=%s", organization, project, planId, suiteId, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        return jsonPath.getList("value.workItem.name");
    }

    public static Map<String, String> pairTestPointWithTestCase(String organization, String project, String planId, String suiteId, String key) {
        String apiVersion = "7.1-preview.2";
        String requestUri = String.format("/%s/%s/_apis/test/Plans/%s/Suites/%s/points?TestCaseId=%s&api-version=%s", organization, project, planId, suiteId, key, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
//        System.out.println(body);
        JSONObject jsonObj = new JSONObject(body);
        JSONArray jsonArray = jsonObj.getJSONArray("value");

        Map<String, String> testCases = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject testCase = jsonArray.getJSONObject(i);
            String id = String.valueOf(testCase.getInt("id"));
            JSONObject testCaseDetails = testCase.getJSONObject("testCase");
            String testCaseId = testCaseDetails.getString("id");

            testCases.put(testCaseId,id);
        }
        System.out.println("<<<<<<<<<<<<<<<<TC IN UPDATE POINT>>>>>>>>>>>>>>>>>>>>");
        for(Entry<String, String> tc:testCases.entrySet()) {
            System.out.println(tc.getKey()+": "+tc.getValue());
        }
        System.out.println("Total tests: "+testCases.size());
        return testCases;
    }

    public static List<String> updateTestPoint(String organization, String project, String planId, String suiteId, String pointId, String outcome) {
        String apiVersion = "7.2-preview.2";
        String requestUri = String.format("/%s/%s/_apis/test/Plans/%s/Suites/%s/points/%s?api-version=%s", organization, project, planId, suiteId, pointId, apiVersion);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("outcome", outcome);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
        System.out.println("body: " +body);
        JsonPath jsonPath = new JsonPath(body);
        System.out.println("jsonpath get list: "+jsonPath.getList("value"));
        return jsonPath.getList("value");
    }

    public static void api(String organizationName, String projectName, String testPlanID, String[] rootSuiteID) {
//        String latestRunId = getLatestRun(organizationName, projectName);
//    	String latestRunId = "101441";
        for(int i=0; i<rootSuiteID.length; i++){
            Map<String, String> testCaseName_outcome_map = TestNGResultsParser.fetchTestCaseNamesWithStatus();
            for (String key: testCaseName_outcome_map.keySet()){
                Map<String, String> testCaseName_testPoint_map = pairTestPointWithTestCase(organizationName,projectName, testPlanID,rootSuiteID[i], key);
//        System.out.println(latestRunId);
                System.out.println(testCaseName_outcome_map);
                System.out.println(testCaseName_testPoint_map);
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<Inside UPDATE API METHOD>>>>>>>>>>>>>>>>>>>>>");
                for (Entry<String, String> entry : testCaseName_testPoint_map.entrySet()) {
                    String testCaseId = entry.getKey();
                    String testPoint = entry.getValue();

                    if (testCaseName_outcome_map.containsKey(testCaseId)) {
                        String outcome = testCaseName_outcome_map.get(testCaseId);
                        System.out.println("Test Case ID: " + testCaseId + ", TestPoint: " + testPoint + ", Outcome: " + outcome);
                        updateTestPoint(organizationName,projectName, testPlanID,rootSuiteID[i], testPoint, outcome);
                    }
                }
            }}
    }

    public Map<String, String> getFailedTestCasesWithStackTrace(String organization, String project, String runId) {
        String apiVersion = "7.2-preview.6";
        String requestUri = String.format("/%s/%s/_apis/test/Runs/%s/results?outcomes&api-version=%s", organization, project, runId, apiVersion);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(requestUri)
                .then()
                .extract().response();
        String body = response.getBody().asString();
        JSONObject jsonObj = new JSONObject(body);
        JSONArray jsonArray = jsonObj.getJSONArray("value");

        Map<String, String> failedTestCasesWithStackTrace = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject testCase = jsonArray.getJSONObject(i);
            String testCaseOutcome = testCase.getString("outcome");
            if ("Failed".equalsIgnoreCase(testCaseOutcome)) {
                JSONObject testCaseDetails = testCase.getJSONObject("testCase");
                String name = testCaseDetails.getString("id");
                String stackTrace = testCase.optString("stackTrace", "No stack trace available");
                failedTestCasesWithStackTrace.put(name, stackTrace);
            }
        }

        return failedTestCasesWithStackTrace;
    }

}
