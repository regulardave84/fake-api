package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import support.PropertiesSupport;
import support.ResponseSupport;
import support.RestSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FakeApiPostSteps {
    private static final String COMMON_PROPERTIES = "common.properties";
    private static final String FAKE_API_BASE_URI = "fake.api.base.uri";
    private static final String POSTS = "posts/";
    private RestSupport restSupport = new RestSupport();
    private ResponseSupport responseSupport = new ResponseSupport();
    private Response response;
    private PropertiesSupport propSupport = new PropertiesSupport();
    private String baseUri = propSupport.getValueFromFile(COMMON_PROPERTIES, FAKE_API_BASE_URI);

    @Given("^I am using the Fake API base URI$")
    public void iAmUsingTheFakeAPIBaseUri() {
        restSupport.setBaseUri(baseUri);
    }

    @When("^I request GET \\/post\\/(\\d+)$")
    public void iCallGETPost(int postNumber) {
        response = restSupport.get(POSTS + postNumber);
    }

    @When("^I request GET /posts$")
    public void iGetAllPosts() {
        response = restSupport.get(POSTS);
    }

    @When("^I request POST /posts with the following details:$")
    public void iRequestPostpostWithTheFollowingDetails(DataTable requestBody) {
        response = restSupport.post(POSTS, requestBody);
    }

    @When("^I request PUT /posts/(\\d+) with the following details:$")
    public void iRequestPostpostWithTheFollowingDetails(Integer postId, DataTable requestBody) {
        response = restSupport.put(POSTS + postId, requestBody);
    }

    @When("^I request DELETE /posts/(\\d+)$")
    public void iDeletePost(Integer postNumber) {
        response = restSupport.delete(POSTS + postNumber);
    }

    @Then("^the response returns a HTTP (\\d+) status code$")
    public void theResponseReturnsAHTTPStatusCode(int expStatusCode) {
        assertThat(response.statusCode(), is(expStatusCode));
    }

    @Then("^response matches the following:$")
    public void responseMatchesTheFollowing(DataTable expResponse) {
        responseSupport.assertResponseBodyMatches(response, expResponse);
    }

    @Then("^the response has more than (\\d+) entr(?:y|ies)$")
    public void theResponseHasMoreThanEntry(int numberOfEntries) {
        responseSupport.assertNumberOfEntriesInResponseIsGreaterOrEqualTo(response, numberOfEntries);
    }

}
