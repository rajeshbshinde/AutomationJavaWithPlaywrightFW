package commonLibrary;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;
import org.json.JSONObject;

public class HttpClientHelper {

    private static final Logger logger = Logger.getLogger(HttpClientHelper.class.getName());
    private final HttpClient client;

    // Constructor initializes the HttpClient instance
    public HttpClientHelper() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Sends an HTTP GET request to the specified URL.
     * Written by: Rajesh Shinde
     * @param url The URL to send the request to.
     * @return The response body as a String.
     */
    public String sendGetRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            return sendRequest(request);
        } catch (Exception e) {
            logger.severe("Error in GET request: " + e.getMessage());
            return null;
        }
    }

    /**
     * Sends an HTTP POST request with JSON payload to the specified URL.
     * Written by: Rajesh Shinde
     * @param url  The URL to send the request to.
     * @param jsonPayload The JSON payload to send in the request body.
     * @return The response body as a String.
     */
    public String sendPostRequest(String url,  JSONObject jsonPayload) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload.toString()))
                    .build();
            return sendRequest(request);
        } catch (Exception e) {
            logger.severe("Error in POST request: " + e.getMessage());
            return null;
        }
    }

    /**
     * Sends an HTTP PUT request with JSON payload to the specified URL.
     * Written by: Rajesh Shinde
     * @param url  The URL to send the request to.
     * @param jsonPayload The JSON payload to send in the request body.
     * @return The response body as a String.
     */
    public String sendPutRequest(String url, JSONObject jsonPayload) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload.toString()))
                    .build();
            return sendRequest(request);
        } catch (Exception e) {
            logger.severe("Error in PUT request: " + e.getMessage());
            return null;
        }
    }

    /**
     * Sends an HTTP DELETE request to the specified URL.
     * Written by: Rajesh Shinde
     * @param url The URL to send the request to.
     * @return The response body as a String.
     */
    public String sendDeleteRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();
            return sendRequest(request);
        } catch (Exception e) {
            logger.severe("Error in DELETE request: " + e.getMessage());
            return null;
        }
    }

    /**
     * Helper method to send the HTTP request and handle the response.
     * Written by: Rajesh Shinde
     * @param request The HttpRequest object to send.
     * @return The response body as a String.
     * @throws Exception if an error occurs during the request.
     */
    private String sendRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Log status code and response
        int statusCode = response.statusCode();
        logger.info("Response Code: " + statusCode);

        if (statusCode >= 200 && statusCode < 300) {
            return response.body();
        } else {
            handleError(statusCode, response.body());
            return null;
        }
    }

    /**
     * Handles error responses based on status code.
     * Written by: Rajesh Shinde
     * @param statusCode The HTTP status code.
     * @param responseBody The response body content.
     */
    private void handleError(int statusCode, String responseBody) {
        switch (statusCode) {
            case 400:
                logger.severe("Bad Request: " + responseBody);
                break;
            case 401:
                logger.severe("Unauthorized: " + responseBody);
                break;
            case 403:
                logger.severe("Forbidden: " + responseBody);
                break;
            case 404:
                logger.severe("Not Found: " + responseBody);
                break;
            case 500:
                logger.severe("Internal Server Error: " + responseBody);
                break;
            default:
                logger.severe("Unexpected Error [" + statusCode + "]: " + responseBody);
                break;
        }
    }
}