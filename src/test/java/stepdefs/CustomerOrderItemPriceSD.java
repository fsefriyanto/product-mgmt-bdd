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

public class CustomerOrderItemPriceSD implements En {

	private World world;

	private RequestSpecification request;
	private Response response;

	@Before
	public void before(Scenario scenario) {
		request = RestAssured.with();
		RestAssured.baseURI = "http://localhost:18080";
	}

	public CustomerOrderItemPriceSD(World world) {
		this.world = world;

		When("^user add new customer order item price with following detail$", (DataTable dataTable) -> {

			List<List<String>> data = dataTable.raw();
			
			Map<String, Object> customerOrderItemPrice = new HashMap<>();

			Map<String, String> quantity = new HashMap<>();
			quantity.put("amount", data.get(1).get(1));
			quantity.put("unit", data.get(2).get(1));
			
			customerOrderItemPrice.put("quantity", quantity);
			
			Map<String, String> unitPrice = new HashMap<>();
			unitPrice.put("unit", data.get(3).get(1));
			unitPrice.put("amount", data.get(4).get(1));
			
			customerOrderItemPrice.put("unitPrice", unitPrice);
			
			Map<String, String> totalCharge = new HashMap<>();
			totalCharge.put("unit", data.get(5).get(1));
			totalCharge.put("amount", data.get(6).get(1));
			
			customerOrderItemPrice.put("totalCharge", totalCharge);
			
			customerOrderItemPrice.put("description", data.get(7).get(1));
			customerOrderItemPrice.put("name", data.get(8).get(1));
			
			response = request.given().contentType(ContentType.JSON).body(customerOrderItemPrice).when().post("/customer/order/items/"+data.get(0).get(1)+"/prices")
					.then().extract().response();

		});

		Then("^new customer order item price is created$", () -> {
			Assert.assertEquals(201, response.getStatusCode());
			JsonPath jsonPath = new JsonPath(response.asString());
			this.world.customerOrderItemPriceId = jsonPath.get("id").toString();
		});
	}

}
