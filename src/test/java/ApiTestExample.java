import commonLibrary.HttpClientHelper;
import org.json.JSONObject;

public class ApiTestExample {

    public static void main(String[] args) {
        HttpClientHelper httpClientHelper = new HttpClientHelper();

        //  GET request
        String getResponse = httpClientHelper.sendGetRequest("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println("GET Response: " + getResponse);

        //  POST request
        JSONObject postPayload = new JSONObject();
        postPayload.put("title", "foo");
        postPayload.put("body", "bar");
        postPayload.put("userId", 1);
        String postResponse = httpClientHelper.sendPostRequest("https://jsonplaceholder.typicode.com/posts", postPayload);

        System.out.println("POST Response: " + postResponse);

        //  PUT request
        JSONObject putPayload = new JSONObject();
        putPayload.put("title", "updated title");
        putPayload.put("body", "updated body");
        putPayload.put("userId", 1);
        String putResponse = httpClientHelper.sendPutRequest("https://jsonplaceholder.typicode.com/posts/1", putPayload);
        System.out.println("PUT Response: " + putResponse);

        //  DELETE request
        String deleteResponse = httpClientHelper.sendDeleteRequest("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println("DELETE Response: " + deleteResponse);
    }
}
