Feature: Customer Order Item
         As User I can create new customer order item
         
	Scenario: Add new customer order item
		When user add new customer order item with following detail
		|customerOrderId		|62293   					|
		|refNumber      		|HEHELD01					|
		|quantityAmount 		|1       					|
		|quantityUnit   		|unit    					|
		|action             	|add     					|
		|busIntCategory     	|product 					|
		|busIntCustomerOrderTp	|new_order					|
		|prodOffCat				|simple						|
		|prodOffName			|12-month unlimited nbn™	|
		|prodOffDispName		|unlimited nbn™ 12-month    |
		|prodCategory			|component					|
		|prodId					|1029						|
		|prodName				|12-month unlimited nbn™	|

		Then new customer order item is created