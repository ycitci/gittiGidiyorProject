package apiTest;

import utilities.ConfigReader;

import java.util.HashMap;

public class ApiData {

    public HashMap<String,String>requestData(){
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("key", ConfigReader.getProperty("key"));
        requestBody.put("token", ConfigReader.getProperty("token"));
        return requestBody;
    }
}
