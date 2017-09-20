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

public class ProductOfferingPriceSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public ProductOfferingPriceSD(){
    	
    	When("^new product offering price with detail below added$", (DataTable dataTable) -> {
    		
    		List<List<String>> data = dataTable.raw();
    		
    		Map<String,Object> productOfferingPrice = new HashMap<>();
    		productOfferingPrice.put("category", data.get(1).get(0));
    		productOfferingPrice.put("name", data.get(1).get(1));
    		productOfferingPrice.put("description", data.get(1).get(2));
    		
    		Map<String,String> offering = new HashMap<>();
    		offering.put("category", data.get(1).get(3));
    		offering.put("id", data.get(1).get(4));
    		
    		productOfferingPrice.put("product", offering);
    		
    		productOfferingPrice.put("priceType", data.get(1).get(5));
    		
    		Map<String, String> price = new HashMap<>();
    		price.put("amount", data.get(1).get(6));
    		price.put("unit", data.get(1).get(7));
    		
    		productOfferingPrice.put("price", price);
            
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(productOfferingPrice)
            .when().post("/product/offering/"+data.get(1).get(4)+"/prices").then();
    	    
    	});

    	Then("^new product offering price is created$", () -> {
    	    response.statusCode(HttpStatus.SC_CREATED);
    	});
    }

}
