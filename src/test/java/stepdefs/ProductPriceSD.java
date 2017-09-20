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

public class ProductPriceSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public ProductPriceSD(){
    	
    	When("^new product price with detail below added$", (DataTable dataTable) -> {
    	    // Write code here that turns the phrase above into concrete actions
    	    // For automatic transformation, change DataTable to one of
    	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
    		
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> productPrice = new HashMap<>();
    		productPrice.put("category", data.get(1).get(0));
    		productPrice.put("name", data.get(1).get(1));
    		productPrice.put("description", data.get(1).get(2));
    		
    		Map<String,String> product = new HashMap<>();
    		product.put("id", data.get(1).get(3));
    		
    		productPrice.put("product", product);
    		
    		Map<String, String> price = new HashMap<>();
    		price.put("amount", data.get(1).get(4));
    		price.put("unit", data.get(1).get(5));
    		
    		productPrice.put("price", price);
            
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(productPrice)
            .when().post("/product/"+data.get(1).get(3)+"/prices").then();
    	    
    	});

    	Then("^new product price is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
