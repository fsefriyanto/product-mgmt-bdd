Feature: Customer
         As User I can manage customer
         
 Scenario: Add new customer
  Given system already initiate
  When user add new customer with following detail
		|category       |customer         |
		|name           |customer         |
		|partyCategory  |individual       |
		|partyId        |442              |
	    |partySearchText|Murray Cameron   |
	    |partyGivenName |Cameron          |
	    |partyFamilyName|Murray           |
	    |partyName      |Mr Cameron Murray|
	    |displayName	|Murray Cameron	  |

   Then new customer is created
