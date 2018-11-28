package support;

import cucumber.api.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestSupport {
    private RequestSpecification request = RestAssured.given();
    private String baseUri;

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response get(String path) {
        return request.get(baseUri + path);
    }

    public Response post(String path, DataTable table) {
        Map<String, String> requestBody = convertDataTableToMap(table);
        return request.body(requestBody).post(baseUri + path);
    }

    public Response put(String path, DataTable table) {
        Map<String, String> requestBody = convertDataTableToMap(table);
        return request.body(requestBody).put(baseUri + path);
    }

    public Response delete(String path) {
        return request.delete(baseUri + path);
    }

    private Map<String,String> convertDataTableToMap(DataTable table) {
        return table.asMap(String.class, String.class);
    }
}