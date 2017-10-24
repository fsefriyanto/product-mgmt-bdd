package stepdefs;

import java.util.ArrayList;
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

    	When("^user add new product specification with following detail$", (DataTable dataTable) -> {
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> prodSpec = new HashMap<>();
    		prodSpec.put("code", data.get(0).get(1));
    		prodSpec.put("displayName", data.get(1).get(1));
    		prodSpec.put("description", data.get(2).get(1));
    		prodSpec.put("category", data.get(3).get(1));
    		
    		Map<String, String> type = new HashMap<>();
    		type.put("id",data.get(4).get(1) );
    		type.put("code", data.get(5).get(1));
    		type.put("displayName", data.get(6).get(1));
    		
    		List types = new ArrayList<>();
    		types.add(type);
    		
    		prodSpec.put("types", types);
            
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
