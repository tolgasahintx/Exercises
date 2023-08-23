package com.crisp.Client;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;

import java.io.IOException;

public class CrispApi {

    public static final String BASE_URL = "https://apimgmt-dev-crisp.azure-api.net/patients/query";

    private Playwright playwright;
    private APIRequest apirequest;
    private APIRequestContext requestContext;

    public APIResponse makeGetRequest(String url, int statusCode) {
        playwright = Playwright.create();
        apirequest = playwright.request();
        requestContext = apirequest.newContext();
        APIResponse apiResponse = requestContext.get(url);
        // status code is being verified here
        Assert.assertEquals(apiResponse.status(), statusCode);
        return apiResponse;
    }

    public APIResponse makeGetRequestWithId(String url, RequestOptions queryParam, int statusCode) {
        playwright = Playwright.create();
        apirequest = playwright.request();
        requestContext = apirequest.newContext();
        APIResponse apiResponse = requestContext.get(url, queryParam);
        // status code is being verified here
        Assert.assertEquals(apiResponse.status(), statusCode);
        return apiResponse;
    }

    public com.crisp.Models.UserDetails[] getAllUserDetails() throws IOException {
        APIResponse apiResponse = makeGetRequest(BASE_URL, 200);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return objectMapper.readValue(apiResponse.text(), com.crisp.Models.UserDetails[].class);
    }

    public com.crisp.Models.UserDetails[] getUserDetailsWithQueryParam(String queryName, String param) throws IOException {
        APIResponse apiResponse = makeGetRequestWithId(BASE_URL, RequestOptions.create().setQueryParam(queryName, param), 200);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return objectMapper.readValue(apiResponse.text(), com.crisp.Models.UserDetails[].class);
    }

}
