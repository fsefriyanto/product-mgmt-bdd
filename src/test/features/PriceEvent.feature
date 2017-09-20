Feature: Product Price Event

	Scenario: Add new product price event
		Given system already initiate
		When new product price event with name "<name>" description "<description>" added with below details:
		|name                       |description                |
		|Apply after contract period|Apply after contract period|
		
		Then price event is created