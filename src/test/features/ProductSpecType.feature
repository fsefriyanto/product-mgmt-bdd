Feature: Product Specification Type

	Scenario: Add New Product Specification type
		Given system already initiate
		When new product specification type with code "inet" display name "Internet" added
		Then new product specification type is created