package support;

import cucumber.api.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import org.apache.log4j.Logger;

public class RestSupport {

    private final static Logger logger = Logger.getLogger(RestSupport.class);
    private static final String LINE_BREAK = "---------------------------------------------------------";
    private static final String REQUEST = "REQUEST";

    private RequestSpecification request = RestAssured.given();
    private String baseUri;

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public Response get(String path) {
        // TODO: need ability to add header to the request and log them
        String endpoint = baseUri + path;
        Response response = request.get(endpoint);
        logRequestDetails(endpoint);
        return response;
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

    private void logRequestDetails(String endpoint) {
        logger.info(LINE_BREAK);
        logger.info(REQUEST);
        logger.info(String.format("GET %s", endpoint));
        logger.info(LINE_BREAK);
    }
}