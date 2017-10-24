Feature: Product Offering

	Scenario: Add new product offering
	    As a user I can add new product offering
	    
		Given system already initiate
		When user add new product offering with following detail
		|category        |simple                  |
		|name            |Month-to-month unlimited|
		|displayName     |Month-to-month unlimited|
		|description     |Month-to-month unlimited|
		|prodSpecCat     |atomic                  |
		|prodSpecId      |1                       |
		|prodSpecCode    |prd_voice               |
		|prodSpecDispName|Voice Product           |
		|prodSpecDesc    |Voice Product           |
		
		Then new product offering is created