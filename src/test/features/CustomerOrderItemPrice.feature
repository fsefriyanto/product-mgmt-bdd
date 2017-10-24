Feature: Customer Order Item Price
         As User I can create new customer order item price
         
	Scenario: Add new customer order item price
		When user add new customer order item price with following detail
		|customerOrderItemId	|7								|
		|quantityAmount			|1								|
		|quantityUnit			|unit							|
		|unitPriceUnit			|currency						|
		|unitPriceAmount		|1								|
		|totalChargeUnit		|currency						|
		|totalChargeAmount		|1								|
		|description			|Compatible with nbn™ & ADSL	|
		|name					|Wi-Fi Hub+						|

		Then new customer order item price is created