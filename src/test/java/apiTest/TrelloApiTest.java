package apiTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ConfigReader;
import utilities.Hooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TrelloApiTest extends TestBaseApi {
    Hooks hooks = new Hooks();
    Response response;
    JsonPath jsonPath;
    public static String boardId;
    public static String listId;
    public static String cardId;
    public List<String> cardIdList=new ArrayList<>();
    ApiData apiData = new ApiData();
    public static int count=0;

    public void createBoard() {
        HashMap<String,String> requestData = apiData.requestData();
        requestData.put("name","board1");
        setUp();
        spec.pathParams("parametre1", 1, "parametre2", "boards");
        response = given().
                spec(spec).
                contentType("application/json").
                body(apiData.requestData()).
                when().
                post("/{parametre1}/{parametre2}");
        jsonPath = response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("board1", jsonPath.getString("name"));
        boardId=jsonPath.getString("id");

    }

    public void createList() {
        setUp();
        spec.pathParams("parametre1", 1, "parametre2", "lists");
        HashMap<String, String> requestBody = apiData.requestData();
        requestBody.put("name", "list");
        requestBody.put("idBoard", boardId);
        response = given().
                spec(spec).
                contentType("application/json").
                body(requestBody).
                when().
                post("/{parametre1}/{parametre2}");
        jsonPath = response.jsonPath();
        listId=jsonPath.getString("id");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("list", jsonPath.getString("name"));
    }

    public void createCard() {
        setUp();
        spec.pathParams("parametre1", 1, "parametre2", "cards");
        HashMap<String, String> requestBody =apiData.requestData();
        requestBody.put("name", "card");
        requestBody.put("idList", listId);
        response = given().
                spec(spec).
                contentType("application/json").
                body(requestBody).
                when().
                post("/{parametre1}/{parametre2}");
        jsonPath = response.jsonPath();
        cardId=jsonPath.getString("id");
        cardIdList.add(cardId);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("card", jsonPath.getString("name"));
    }

    public void updateCard() {
      int random=hooks.randomCount(cardIdList.size());
        setUp();
        spec.pathParams("parametre1", 1, "parametre2", "cards");
        HashMap<String, String> requestBody = apiData.requestData();
        requestBody.put("name","updatecard");
        requestBody.put("id", cardIdList.get(random));
        response = given().
                spec(spec).
                contentType("application/json").
                body(requestBody).
                when().
                put("/{parametre1}/{parametre2}");
        jsonPath = response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("updatecard", jsonPath.getString("name"));
    }
    public void deleteCard() {
        setUp();
        spec.pathParams("parametre1", 1, "parametre2", "cards","parametre3",cardIdList.get(count));
        count++;
        HashMap<String, String> requestBody =apiData.requestData();
        requestBody.put("key", ConfigReader.getProperty("key"));
        requestBody.put("token", ConfigReader.getProperty("token"));
        response = given().
                spec(spec).
                contentType("application/json").
                body(requestBody).
                when().
                delete("/{parametre1}/{parametre2}/{parametre3}");
        Assert.assertEquals(200, response.getStatusCode());

    }
    public void deleteBoard() {
        setUp();
        spec.pathParams("parametre1", 1, "parametre2", "cards","parametre3", boardId);
        HashMap<String, String> requestBody =apiData.requestData();
        requestBody.put("key", ConfigReader.getProperty("key"));
        requestBody.put("token", ConfigReader.getProperty("token"));
        response = given().
                spec(spec).
                contentType("application/json").
                body(requestBody).
                when().
                delete("/{parametre1}/{parametre2}/{parametre3}");
        Assert.assertEquals(200, response.getStatusCode());
    }
}
