Feature: Product Specification

	Scenario: Add New Product Specification
		Given system already initiate
		When user add new product specification with following detail 
		|code           |inet          |
		|displayName    |internet      |
		|description    |internet      |
		|category       |atomic        |
		|typeId         |6             |
		|typeCode       |adsl_broadband|
		|typeDisplayName|ADSL Broadband|
		
		Then new product specification is created