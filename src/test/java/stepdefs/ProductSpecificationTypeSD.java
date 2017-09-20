package stepdefs;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ProductSpecificationTypeSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public ProductSpecificationTypeSD(){

    	When("^new product specification type with code \"([^\"]*)\" display name \"([^\"]*)\" added$", (String code, String displayName) -> {
    	    
    		Map<String,String> prodSpec = new HashMap<>();
    		prodSpec.put("code", code);
    		prodSpec.put("displayName", displayName);
            
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(prodSpec)
            .when().post("/product/specification/type").then();
    	});

    	Then("^new product specification type is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
