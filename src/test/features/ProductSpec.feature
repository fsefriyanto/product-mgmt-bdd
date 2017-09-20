Feature: Product Specification

	Scenario: Add New Product Specification
		Given system already initiate
		When new product specification with code "inet" display name "Internet" description "Internet" category "atomic" added
		Then new product specification is created