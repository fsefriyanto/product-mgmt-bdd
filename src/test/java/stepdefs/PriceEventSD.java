package stepdefs;

import java.util.HashMap;
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

public class PriceEventSD implements En{
	

	private static final String BASE_URL = "http://localhost:18080";
	
	private RequestSpecification request;
    private ValidatableResponse response;

    @Before
    public void before(Scenario scenario) {
        request = RestAssured.with();
    }
	
    public PriceEventSD(){

//    	When("^new product specification type with code \"([^\"]*)\" display name \"([^\"]*)\" added$", (String code, String displayName) -> {
//    	    
//    		Map<String,String> prodSpec = new HashMap<>();
//    		prodSpec.put("code", code);
//    		prodSpec.put("displayName", displayName);
//            
//    	    response = request.given()
//            .contentType(ContentType.JSON).baseUri(BASE_URL)
//            .body(prodSpec)
//            .when().post("/product/specification/type").then();
//    	});
//
//    	Then("^new product specification type is created$", () -> {
//    	    response.statusCode(HttpStatus.SC_CREATED);
//    	});
    	
    	When("^new product price event with name \"([^\"]*)\" description \"([^\"]*)\" added with below details:$", (String code, String description, DataTable arg3) -> {
    	    // Write code here that turns the phrase above into concrete actions
    	    // For automatic transformation, change DataTable to one of
    	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
    	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//    	    throw new PendingException();
    		
//    		"specification": {
//            "id": 1,
//            "code": "apply_after_contract_period",
//            "displayName": "Apply after contract period"
//           
//        }
    		
    		Map<String,Object> priceEvent = new HashMap<>();
    		priceEvent.put("name", code);
    		priceEvent.put("description", description);
    		Map<String, String> priceEventSpec = new HashMap<>();
    		priceEventSpec.put("id", "1");
    		priceEventSpec.put("code", "apply_after_contract_period");
    		priceEventSpec.put("displayName", "Apply after contract period");
    		priceEvent.put("specification", priceEventSpec);
            
    	    response = request.given()
            .contentType(ContentType.JSON).baseUri(BASE_URL)
            .body(priceEvent)
            .when().post("/product/offering/price/event").then();
    	});

    	Then("^price event is created$", () -> {
    		response.statusCode(HttpStatus.SC_CREATED);
    	});
    	
    }

}
