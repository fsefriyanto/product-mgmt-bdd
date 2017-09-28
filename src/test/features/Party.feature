Feature: Party
         As User I can manage party
         
    Scenario: Add new party
     Given system already initiate
     When new party with detail below added
		|category|name    |party_name|
		|customer|customer|John Doe  |
     Then new party is created
