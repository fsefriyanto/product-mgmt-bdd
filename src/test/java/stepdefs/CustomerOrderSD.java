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

public class CustomerOrderSD implements En {

	private World world;

	private RequestSpecification request;
	private Response response;

	@Before
	public void before(Scenario scenario) {
		request = RestAssured.with();
		RestAssured.baseURI = "http://localhost:18080";
	}

	public CustomerOrderSD(World world) {
		this.world = world;

		When("^user add new customer order with following detail$", (DataTable dataTable) -> {

			List<List<String>> data = dataTable.raw();

			Map<String, Object> customerOrder = new HashMap<>();
			customerOrder.put("category", data.get(0).get(1));
			customerOrder.put("customerOrderType", data.get(1).get(1));

			Map<String, String> prodOffer = new HashMap<>();
			prodOffer.put("category", data.get(2).get(1));
			prodOffer.put("id", this.world.productOfferingId);
			prodOffer.put("name", data.get(3).get(1));
			prodOffer.put("displayName", data.get(4).get(1));

			List prodOffers = new ArrayList<>();
			prodOffers.add(prodOffer);

			customerOrder.put("productOfferings", prodOffers);

			Map<String, Object> customer = new HashMap<>();
			customer.put("category", data.get(5).get(1));
			customer.put("id", data.get(6).get(1));

			Map<String, String> party = new HashMap<>();
			party.put("category", data.get(7).get(1));
			party.put("id", data.get(8).get(1));
			party.put("searchText", data.get(9).get(1));
			party.put("givenName", data.get(10).get(1));
			party.put("familyName", data.get(11).get(1));
			party.put("name", data.get(12).get(1));
			party.put("displayName", data.get(13).get(1));

			customer.put("customerId", data.get(14).get(1));
			customer.put("customerStatus", data.get(15).get(1));

			customer.put("party", party);

			List customers = new ArrayList<>();
			customers.add(customer);

			customerOrder.put("customers", customers);

			response = request.given().contentType(ContentType.JSON).body(customerOrder).when().post("/customer/order")
					.then().extract().response();

		});

		Then("^new customer order is created$", () -> {
			Assert.assertEquals(201, response.getStatusCode());
			JsonPath jsonPath = new JsonPath(response.asString());
			this.world.customerOrderId = jsonPath.get("id").toString();
		});
	}

}
