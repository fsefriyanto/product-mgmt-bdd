Feature: Product Offering

	Scenario: Add new product offering
	    As a user I can add new product offering
	    
		Given system already initiate
		When new product offering with detail below added
		|category |name          |description                  |offering_category|offering_id|price_type|price_amount|price_unit|
		|one_time |Wi-Fi Hub+ SSS|Compatible with nbn™ & ADSL  |simple           |8          |one_time  |100         |currency  |
		
		Then new product offering is created