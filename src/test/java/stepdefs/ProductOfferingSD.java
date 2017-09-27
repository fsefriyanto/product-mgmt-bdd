package stepdefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ProductOfferingSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public ProductOfferingSD(){
    	
    	When("^new product offering with detail below added$", (DataTable dataTable) -> {
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> productOffering = new HashMap<>();
    		productOffering.put("category", data.get(1).get(0));
    		productOffering.put("name", data.get(1).get(1));
    		productOffering.put("displayName", data.get(1).get(2));
    		productOffering.put("description", data.get(1).get(3));
    		
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(productOffering)
            .when().post("/product/offering").then();
    	    
    	});

    	Then("^new product offering is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
