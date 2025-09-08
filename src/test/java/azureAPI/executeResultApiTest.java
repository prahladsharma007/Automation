package azureAPI;

import AUT.utilities.RestAssuredAzure;
import org.testng.annotations.Test;

import java.util.HashMap;

public class executeResultApiTest {

    String organizationName = "IntelligentAutomation-TAD";
    String projectName = "CTT Notes";
    String testPlanID;
    String[] rootSuiteID;

    HashMap<String, String[]> hm = new HashMap<>(){{
        put("108097", new String[]{"108098"});
        put("108099", new String[]{"108100"});//Key as Test Plan id and Value as Test Suite id
        //Use below code for multiple suite
        put("125328", new String[]{"126519", "126523", "126521", "126522", "126524", "126534",
                "126525", "126533", "126535", "126539", "126536", "126520", "126526", "126972",
                "126535", "126883", "128072", "129250", "128691", "129279", "129694", "129691",
                "129689", "129688", "129992", "129990", "129985", "129989", "129987", "129684",
                "129988"});
    }};

    @Test
    public void azureApiExecute() {
        System.out.println("Starting Azure API Hit");
        RestAssuredAzure azure = new RestAssuredAzure();
        for(String testPlanID: hm.keySet()){
            rootSuiteID= hm.get(testPlanID);
        RestAssuredAzure.api(organizationName, projectName, testPlanID, rootSuiteID);}
        System.out.println("Completed Azure API Result Update");
    }
}
