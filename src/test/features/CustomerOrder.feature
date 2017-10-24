Feature: Customer Order
         As User I can create new customer order
         
	Background:      
    	Given system already initiate
		When user add new product offering with following detail
		|category        |simple                      |
		|name            |Month-to-month unlimited    |
		|displayName     |Month-to-month unlimited    |
		|description     |Month-to-month unlimited    |
		|prodSpecCat     |atomic                      |
		|prodSpecId      |1                           |
		|prodSpecCode    |prd_voice                   |
		|prodSpecDispName|Voice Product               |
		|prodSpecDesc    |Voice Product               |
		
		Then new product offering is created
         
	Scenario: Add new customer order
		When user add new customer order with following detail
		|category       |product                       |
		|custOrderType  |new_order                     |
		|prodOffCat     |simple                        |
		|prodOffName    |Month-to-month unlimited      |                 
		|displayName    |Month-to-month unlimited      |                  
		|custCategory   |customer                      |
		|custId         |767                           |
		|custPartyCat   |individual                    |
		|custPartyId    |442                           |
		|partySearchText|Murray Cameron                |
		|partyGivenName |Cameron                       |
		|partyFamilyName|Murray                        |
		|partyName      |Mr Cameron Murray             |
		|displayName	 |Murray Cameron				|
		|customerId     |MR1009671                     |
		|customerStatus |prospective                   |

		Then new customer order is created