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

public class ProductSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public ProductSD(){
    	
    	When("^new product with details below added$", (DataTable dataTable) -> {
    	    // Write code here that turns the phrase above into concrete actions
    	    // For automatic transformation, change DataTable to one of
    	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
    		
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> product = new HashMap<>();
    		product.put("name", data.get(1).get(0));
    		product.put("description", data.get(1).get(1));
    		
    		Map<String,String> specification = new HashMap<>();
    		specification.put("id", data.get(1).get(2));
    		specification.put("code", data.get(1).get(3));
    		specification.put("category", data.get(1).get(4));
    		
    		product.put("specification", specification);
            
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(product)
            .when().post("/product").then();
    	    
    	});

    	Then("^new product is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
