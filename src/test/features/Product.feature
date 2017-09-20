Feature: Product

	Scenario: Add New Product
		Given system already initiate
		When new product with details below added
		|name     |description|spec_id|spec_code      | spec_category|characteristic_val                         |char_spec_char_use|spec_char_value_use|
		|supernova|supernova  |1      |prd_mr_wifi_hub|atomic        |D100_U40_Mbps_TC4_P, D0.15_U0.15_Mbps_TC1_C|129               |223                |
		
		Then new product is created