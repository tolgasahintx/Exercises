package com.crisp.Tests;

import com.crisp.Client.CrispApi;
import com.crisp.DataFiles.UserData;
import com.crisp.Models.UserDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsersTest extends CrispApi{

    private CrispApi crispApi;

    // Test case 1: Fetch all users with expected parameter and data
    // Test fails due to a bug in returning dateOfBirth for user with id: 444
    @Test
    public void getAllUsersTest() throws IOException {
        crispApi = new CrispApi();
        //get list of Maps from Response
        ObjectMapper mapper = new ObjectMapper();
        // Convert POJO to Map
        List<Map<String, String>> userDetailsList = new ArrayList<>();
        for(UserDetails item : crispApi.getAllUserDetails()){
            userDetailsList.add(mapper.convertValue(item, new TypeReference<Map<String, String>>() {}));
        }
        System.out.println("This test is expected to fail, bug reported");
        // Verify that all users' data from the response matches the data expected
        Assert.assertEquals(userDetailsList, UserData.createUserData());
    }

    @Test
    public void getUserWithIdTest() throws IOException {
        crispApi = new CrispApi();
        UserDetails[] userDetails = crispApi.getUserDetailsWithQueryParam("Id", "111");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getId(), "111");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getName(), "Jenn D.");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getDateOfBirth(), "1934-06-01");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getAddress(), "CA");
    }

    @Test
    public void getUserWithName() throws IOException {
        crispApi = new CrispApi();
        UserDetails[] userDetails = crispApi.getUserDetailsWithQueryParam("Name", "Jack");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getId(), "222");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getName(), "Jack");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getDateOfBirth(), "1956-05-01");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getAddress(), "MD");
    }

    @Test
    public void getUserWithDateOfBirth() throws IOException {
        crispApi = new CrispApi();
        UserDetails[] userDetails = crispApi.getUserDetailsWithQueryParam("DateOfBirth", "1966-04-01");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getId(), "333");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getName(), "Bernard");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getDateOfBirth(), "1966-04-01");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getAddress(), "CA State");
    }

    @Test
    public void getUserWithAddress() throws IOException {
        crispApi = new CrispApi();
        UserDetails[] userDetails = crispApi.getUserDetailsWithQueryParam("Address", "MD");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getId(), "222");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getName(), "Jack");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getDateOfBirth(), "1956-05-01");
        Assert.assertEquals(Arrays.asList(userDetails).get(0).getAddress(), "MD");
    }

    @Test
    public void invalidURL() {
        crispApi = new CrispApi();
        //Below script contains the status code assertion in makeGetRequest method
        APIResponse response = crispApi.makeGetRequest(BASE_URL.concat("invalid"), 404);
        Assert.assertTrue(response.text().contains("Not Found"));
    }

    @Test
    public void invalidId() throws IOException {
        crispApi = new CrispApi();
        UserDetails[] userDetails = crispApi.getUserDetailsWithQueryParam("Id", "invalid");
        Assert.assertTrue(Arrays.asList(userDetails).isEmpty());
    }

    @Test
    public void invalidDateFormat() throws IOException {
        crispApi = new CrispApi();
        UserDetails[] userDetails = crispApi.getUserDetailsWithQueryParam("DateOfBirth", "05-01-1956");
        Assert.assertTrue(Arrays.asList(userDetails).isEmpty());
    }
}