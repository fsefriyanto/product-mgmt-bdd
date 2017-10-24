Feature: Product Offering Price

	Scenario: Add new product offering price
		Given system already initiate
		When new product offering price with detail below added
		|category      |one_time                   |
		|name          |Wi-Fi Hub+ SSS             |          
		|description   |Compatible with nbn™ & ADSL|                  
		|offerCategory |simple                     |
		|offerId       |8                          |
		|priceType     |one_time                   |
		|priceAmount   |100                        |
		|priceUnit     |currency                   |
		
		Then new product offering price is created