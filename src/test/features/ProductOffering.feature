Feature: Product Offering

	Scenario: Add new product offering
	    As a user I can add new product offering
	    
		Given system already initiate
		When new product offering with detail below added
		|category|name                    |display_name            |description             |
		|simple  |Month-to-month unlimited|Month-to-month unlimited|Month-to-month unlimited|
		
		Then new product offering is created