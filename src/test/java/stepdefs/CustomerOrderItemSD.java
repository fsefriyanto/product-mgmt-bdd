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

public class CustomerOrderItemSD implements En {

	private World world;

	private RequestSpecification request;
	private Response response;

	@Before
	public void before(Scenario scenario) {
		request = RestAssured.with();
		RestAssured.baseURI = "http://localhost:18080";
	}

	public CustomerOrderItemSD(World world) {
		this.world = world;

		When("^user add new customer order item with following detail$", (DataTable dataTable) -> {

			List<List<String>> data = dataTable.raw();

			Map<String, Object> customerOrderItem = new HashMap<>();
			customerOrderItem.put("referenceNumber", data.get(1).get(1));
			
			Map<String, String> quantity = new HashMap<>();
			quantity.put("amount", data.get(2).get(1));
			quantity.put("unit", data.get(3).get(1));
			
			customerOrderItem.put("quantity", quantity);
			
			customerOrderItem.put("add", data.get(4).get(1));
			Map<String,String> businessInteraction = new HashMap<>();
			businessInteraction.put("category", data.get(5).get(1));
			businessInteraction.put("customerOrderType", data.get(6).get(1));
			
			customerOrderItem.put("businessInteraction", businessInteraction);

			Map<String, String> prodOffering = new HashMap<>();
			prodOffering.put("category", data.get(2).get(1));
			prodOffering.put("id", this.world.productOfferingId);
			prodOffering.put("name", data.get(3).get(1));
			prodOffering.put("displayName", data.get(4).get(1));

			customerOrderItem.put("productOffering", prodOffering);

			Map<String, Object> product = new HashMap<>();
			product.put("category", data.get(5).get(1));
			product.put("id", data.get(6).get(1));
			product.put("name", data.get(6).get(1));

			List products = new ArrayList<>();
			products.add(product);

			customerOrderItem.put("products", products);

			response = request.given().contentType(ContentType.JSON).body(customerOrderItem).when().post("/customer/order/"+data.get(0).get(1)+"/items")
					.then().extract().response();

		});

		Then("^new customer order item is created$", () -> {
			Assert.assertEquals(201, response.getStatusCode());
			JsonPath jsonPath = new JsonPath(response.asString());
			this.world.customerOrderId = jsonPath.get("id").toString();
		});
	}

}
