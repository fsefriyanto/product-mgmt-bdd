Feature: Product Catalog
         As user I can manage product catalog

	Scenario: Add new product catalog
		Given system already initiate
		When new product catalog with detail below added
		|name       |description|
		|catalog abc|catalog abc|
		
		Then new product catalog is created