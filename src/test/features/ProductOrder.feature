Feature: Product Order
         As User I can create new product order
         
    Scenario: Add new product order
     Given system already initiate
     When user add new product order with following detail
	 |category       |product                       |
	 |custOrderType  |new_order                     |
	 |prodOffCat     |simple                        |
	 |prodOffId      |8                             |
	 |prodOffName    |Wi-Fi Hub+ Modem 12 month plan|                 
	 |displayName    |Wi-Fi Hub+ Modem 12 month plan|                  
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

     Then new product order is created