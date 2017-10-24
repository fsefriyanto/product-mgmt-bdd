package stepdefs;

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

public class ProductOfferingSD implements En{
	
	private World world;
	
	private RequestSpecification request;
    private Response response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
        RestAssured.baseURI = "http://localhost:18080";
    }
	
    public ProductOfferingSD(World world){
    	this.world = world;
    	
    	When("^user add new product offering with following detail$", (DataTable dataTable) -> {
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> productOffering = new HashMap<>();
    		productOffering.put("category", data.get(0).get(1));
    		productOffering.put("name", data.get(1).get(1));
    		productOffering.put("displayName", data.get(2).get(1));
    		productOffering.put("description", data.get(3).get(1));
    		
    		Map<String, Object> prodSpec = new HashMap<>();
    		prodSpec.put("category", data.get(4).get(1));
    		prodSpec.put("id", data.get(5).get(1));
    		prodSpec.put("code", data.get(6).get(1));
    		prodSpec.put("displayName", data.get(7).get(1));
    		prodSpec.put("description", data.get(8).get(1));
    		
    		productOffering.put("productSpecification", prodSpec);
    		
    	    response = request.given()
            .contentType(ContentType.JSON)
            .body(productOffering)
            .when().post("/product/offering").then().extract().response();
    	    
    	});

    	Then("^new product offering is created$", () -> {
    	    Assert.assertEquals(201, response.getStatusCode());
    	    JsonPath jsonPath = new JsonPath(response.asString());
    	    this.world.productOfferingId = jsonPath.get("id").toString();
    	});
    }

}
