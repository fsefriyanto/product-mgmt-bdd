package stepdefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class CustomerAccountSD implements En{
	
	private World world;
	
	private RequestSpecification request;
    private Response response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
        RestAssured.baseURI = "http://localhost:18080";
    }
	
    public CustomerAccountSD(World world){
    	this.world = world;
    	
    	When("^user add new customer account with following detail$", (DataTable dataTable) -> {
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> customerAccount = new HashMap<>();
    		customerAccount.put("name", data.get(0).get(1));
    		customerAccount.put("accountType", data.get(1).get(1));
    		
    		Map<String,Object> creditLimit = new HashMap<>();
    		creditLimit.put("unit", data.get(2).get(1));
    		creditLimit.put("amount", data.get(3).get(1));
    		
    		customerAccount.put("creditLimit", creditLimit);
    		
    		Map<String, String> customer = new HashMap<>();
    		customer.put("id", this.world.customerId);
    		
    		List customers = new ArrayList<>();
    		customers.add(customer);
    		
    		customerAccount.put("customers", customers);
    		
    	    response = request.given()
            .contentType(ContentType.JSON)
            .body(customerAccount)
            .when().post("/customer/account").then().extract().response();
    	    
    	});

    	Then("^new customer account is created$", () -> {
    		Assert.assertEquals(201, response.getStatusCode());
    	    JsonPath jsonPath = new JsonPath(response.asString());
    	    this.world.customerAccountId = jsonPath.get("id").toString();
    	});
    }

}
