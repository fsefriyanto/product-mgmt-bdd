Feature: Customer Account

 Background:
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

  Scenario: add new customer account
	  When user add new customer account with following detail
		  |name             |default   |
		  |accountType      |individual|
		  |creditLimitUnit  |currency  |
		  |creditLimitAmount|1000      |
		  |pin              |4932      |
	
	  Then new customer account is created