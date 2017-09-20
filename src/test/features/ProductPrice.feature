Feature: Product Price

	Scenario: Add new product price
		Given system already initiate
		When new product price with detail below added
		|category|name         |description                  |product_id|price_type|price_amount|price_unit|
		|charge  |Gamer Package|Gamer Add-on Subscription Fee|8         |recurring |100         |currency  |
		
		Then new product price is created