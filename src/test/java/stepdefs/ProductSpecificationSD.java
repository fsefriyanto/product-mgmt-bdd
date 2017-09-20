package stepdefs;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ProductSpecificationSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public ProductSpecificationSD(){
    	
    	Given("^system already initiate$", () -> {
    		request.given()
            .contentType(ContentType.JSON)
            .baseUri(BASE_URL);
    	});

    	When("^new product specification with code \"([^\"]*)\" display name \"([^\"]*)\" description \"([^\"]*)\" category \"([^\"]*)\" added$", (String code, String displayName, String description, String category) -> {
    	    
    		Map<String,String> prodSpec = new HashMap<>();
    		prodSpec.put("code", code);
    		prodSpec.put("displayName", displayName);
    		prodSpec.put("description", description);
    		prodSpec.put("category", category);
            
    	    response = request.given()
            .contentType(ContentType.JSON)
            .body(prodSpec)
            .when().post("/product/specification").then();
    	});

    	Then("^new product specification is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
