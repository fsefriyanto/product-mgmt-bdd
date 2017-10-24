package stepdefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomerSD implements En{
	
	private World world;
	private RequestSpecification request;
    private Response response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
        RestAssured.baseURI = "http://localhost:18080";
    }
	
    public CustomerSD(World world){
    	this.world = world;
    	
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
            .contentType(ContentType.JSON)
            .body(customer)
            .when().post("/customer").then().extract().response();
    	    
    	});

    	Then("^new customer is created$", () -> {
    	    Assert.assertEquals(HttpStatus.SC_CREATED,response.getStatusCode());
    	    JsonPath jsonPath = new JsonPath(response.asString());
    	    this.world.customerId = jsonPath.get("id").toString();
    	});
    }

}
