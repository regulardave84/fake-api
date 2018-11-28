package support;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jayway.jsonassert.JsonAssert.collectionWithSize;
import static com.jayway.jsonassert.JsonAssert.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseSupport {

    private static final String IS_BOOLEAN_REGEX = "^true|false$";
    private static final String IS_INTEGER_REGEX = "^-?(0|[1-9]\\d*)$";
    private static final String BODY_ROOT = "$";

    public void assertResponseBodyMatches(Response response, DataTable table) {
        Map<String, String> tableAsMap = table.asMap(String.class, String.class);

        for (Map.Entry<String, String> expectedField : tableAsMap.entrySet()) {
            String json = response.getBody().asString();
            String jsonPath = expectedField.getKey();
            String expectedValue = expectedField.getValue();

            if (expectedValue.matches(IS_BOOLEAN_REGEX)) {
                with(json).assertThat(jsonPath, is(Boolean.valueOf(expectedValue)));
            } else if (expectedValue.matches(IS_INTEGER_REGEX)) {
                with(json).assertThat(jsonPath, is(Integer.parseInt(expectedValue)));
            } else {
                with(json).assertThat(jsonPath, is(expectedValue));
            }
        }
    }

    public void assertNumberOfEntriesInResponseIsGreaterOrEqualTo(Response response, int entriesNo) {
        String json = response.getBody().asString();
        with(json).assertThat(BODY_ROOT, is(collectionWithSize(greaterThanOrEqualTo(entriesNo))));
    }

}