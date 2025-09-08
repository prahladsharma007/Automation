package AUT.utilities;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Rest {

    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";

    public Response hitGetMethod(HashMap<String, String> headers,
                                 Map<String, String> queryParams,
                                 Map<String, String> pathParam, String baseUrl,
                                 String endPoint ,int expectedStatusCode) {

        generateCurl("GET", headers, queryParams, pathParam, baseUrl,
                endPoint,"");

        Response response;
        response = given().
                filter(new AllureRestAssured()).
                baseUri(baseUrl).
                //log().all().
                        headers(headers).
                queryParams(queryParams).
                pathParams(pathParam).
                when().
                get(endPoint).
                then().
                //log().headers().
                        assertThat().
                statusCode(expectedStatusCode).
                extract().
                response();

        return response;
    }


    public Response hitPostMethod(HashMap<String, String> headers,
                                  Map<String, String> queryParams,
                                  Map<String, String> pathParam, String baseUrl,
                                  String endPoint , String body, int expectedStatusCode) {

        generateCurl("POST", headers, queryParams, pathParam, baseUrl,
                endPoint, body);

        Response response;
        response = given().
                filter(new AllureRestAssured()).
                baseUri(baseUrl).
                //log().all().
                        headers(headers).
                //body(file).
                        body(body).
                queryParams(queryParams).
                pathParams(pathParam).
                when().
                post(endPoint).
                then().
                //log().headers().
                        assertThat().
                statusCode(expectedStatusCode).
                extract().
                response();
        return response;
    }

    public Response hitPutMethod(HashMap<String, String> headers,
                                 Map<String, String> queryParams,
                                 Map<String, String> pathParam, String baseUrl,
                                 String endPoint , String body, int expectedStatusCode) {

        generateCurl("PUT", headers, queryParams, pathParam, baseUrl,
                endPoint,body);

        Response response;
        response = given().
                filter(new AllureRestAssured()).
                baseUri(baseUrl).
                log().all().
                headers(headers).
                body(body).
                queryParams(queryParams).
                pathParams(pathParam).
                when().
                post(endPoint).
                then().
                log().headers().
                assertThat().
                statusCode(expectedStatusCode).
                extract().
                response();
        return response;
    }

    public Response hitDeleteMethod(HashMap<String, String> headers,
                                    Map<String, String> queryParams,
                                    Map<String, String> pathParam, String baseUrl,
                                    String endPoint , String body, int expectedStatusCode) {

        generateCurl("DELETE", headers, queryParams, pathParam, baseUrl,
                endPoint,body);

        Response response;
        response = given().
                filter(new AllureRestAssured()).
                baseUri(baseUrl).
                log().all().
                headers(headers).
                body(body).
                queryParams(queryParams).
                pathParams(pathParam).
                when().
                post(endPoint).
                then().
                log().headers().
                assertThat().
                statusCode(expectedStatusCode).
                extract().
                response();
        return response;
    }

    public Response hitFileUpload(HashMap<String, String> headers,
                                  Map<String, String> queryParams,
                                  Map<String, String> pathParam, String baseUrl,
                                  String endPoint , File file, int expectedStatusCode) {

        Response response;
        response = given().
                filter(new AllureRestAssured()).
                baseUri(baseUrl).
                //log().all().
                        headers(headers).
                //body(file).
                        body(file).
                queryParams(queryParams).
                pathParams(pathParam).
                when().
                post(endPoint).
                then().
                //log().headers().
                        assertThat().
                statusCode(expectedStatusCode).
                extract().
                response();
        return response;
    }

    public void generateCurl(String methodType, HashMap<String, String> headers,
                             Map<String, String> queryParams, Map<String, String> pathParams,
                             String baseUrl, String endPoint , String body){
        String curlCommand;
        if(pathParams.isEmpty()){
            curlCommand = new CurlBuilder()
                    .setMethod(methodType)
                    .setUrl(baseUrl+endPoint)
                    .addHeaders(headers)
                    .addQueryParams(queryParams)
                    .setBody(body)
                    .build();
        }
        else{
            curlCommand = new CurlBuilder()
                    .setMethod(methodType)
                    .addHeaders(headers)
                    .addQueryParams(queryParams)
                    .setBody(body)
                    .setPathParams(pathParams, baseUrl+endPoint)
                    .build();
        }
        // Print the cURL command
//        System.out.println("Generated cURL Command:");
//        System.out.println(curlCommand);
    }

    public void validateKeyInObject(List<String> keys, LinkedHashMap<String,String> map){
        try{
            for(String key:keys){
                assertThat(map, hasKey(key));
            }
        }catch(Exception e){
            failure(getMethodName());
            e.printStackTrace();
            //e.getMessage();
        }
    }

    public String getMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public void success(String name){
        System.out.println(name +" is :"+GREEN+"Passed"+RESET);
    }

    public void failure(String name){
        System.out.println(name + " is :"+RED +"Failed"+RESET);
    }


}
