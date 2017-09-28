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

public class CustomerSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public CustomerSD(){
    	
    	When("^user add new customer with following detail$", (DataTable dataTable) -> {
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> customer = new HashMap<>();
    		customer.put("category", data.get(0).get(1));
    		customer.put("name", data.get(1).get(1));
    		
    		Map<String,String> party = new HashMap<>();
    		party.put("category", data.get(2).get(1));
    		party.put("id", data.get(3).get(1));
    		party.put("searchText", data.get(4).get(1));
    		party.put("givenName", data.get(5).get(1));
    		party.put("familyName", data.get(6).get(1));
    		party.put("name", data.get(7).get(1));
    		party.put("displayName", data.get(8).get(1));
    		
    		customer.put("party", party);
    		
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(customer)
            .when().post("/customer").then();
    	    
    	});

    	Then("^new customer is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
