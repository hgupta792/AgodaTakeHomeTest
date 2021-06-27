Feature: Verify Get Quotes API

#Get Quotes API provides endpoints for fetching famous quotes. It supports querying by tags and author name, and also support pagination.
#Below scenarios contains all tests for params tags,author and pages.
   
   
 @positive @singleParam @tags
Scenario Outline: User calls GetQuotes with tag query param multiple values(AND)
   Given  I call URL with endpoint "/quotes" with tag value "<tagValue>"
   Then  I see status code 200   
   And   I only see results with tag "<tagValue>" in response    
 Examples:
 |tagValue|
 |technology|
 |technology,famous-quotes|
 
 
 @positive @singleParam @tags
Scenario: User calls GetQuotes with tag query param multiple values (OR)
   Given  I call URL with endpoint "/quotes" with tag value "technology|famous-quotes"
   Then  I see status code 200   
   And   I only see results with tag "technology|famous-quotes" in response
 
 
 @positive @singleParam @author
Scenario Outline: User calls GetQuotes with author query param
   Given  I call URL with endpoint "/quotes" with author value "<authorValue>"
   Then  I see status code 200   
   And   I only see results with author "<authorValue>" in response 
 Examples:
 |authorValue|
 |Elbert Hubbard|
 |Amber Valletta|
 
 @positive @singleParam @page
Scenario Outline: User calls GetQuotes with page query param
   Given  I call URL with endpoint "/quotes" with page value "<pageValue>"
   Then  I see status code 200   
   And   I only see results for pageNumber "<pageValue>" in response 
 Examples:
 |pageValue|
 |1|
 |95|
 |96|

@positive @multipleParam @author @tags
Scenario Outline: User calls GetQuotes with tag&author query param
   Given  I call URL with endpoint "/quotes" with tag value "<tagValue>" and author value "<authorValue>"
   Then  I see status code 200   
   And   I only see results with tag "<tagValue>" in response 
   And   I only see results with author "<authorValue>" in response 
 Examples:
 |tagValue|authorValue|
 |inspirational|Helmut Schmidt|
 
 @positive @multipleParam @author @page
Scenario Outline: User calls GetQuotes with page&author query param
   Given  I call URL with endpoint "/quotes" with page value "<pageValue>" and author value "<authorValue>"
   Then  I see status code 200   
   And   I only see results for pageNumber "<pageValue>" in response 
   And   I only see results with author "<authorValue>" in response
 Examples:
 |pageValue|authorValue|
 |1|Helmut Schmidt|
 |2|Helmut Schmidt|
   
   
 @positive @multipleParam @tags @page
Scenario Outline: User calls GetQuotes with page&tag query param
   Given I call URL with endpoint "/quotes" with page value "<pageValue>" and tag value "<tagValue>"
   Then  I see status code 200   
   And   I only see results for pageNumber "<pageValue>" in response 
   And   I only see results with tag "<tagValue>" in response
 Examples:
 |pageValue|tagValue|
 |1|technology,famous-quotes|
 |2|technology,famous-quotes|
 
 
 @positive @allParam @tags @page @author
Scenario Outline: User calls GetQuotes with page , tag & author query param
   Given I call URL with endpoint "/quotes" with page value "<pageValue>" and tag value "<tagValue>" and authorValue "<authorValue>"
   Then  I see status code 200   
   And   I only see results for pageNumber "<pageValue>" in response 
   And   I only see results with tag "<tagValue>" in response
   And   I only see results with author "<authorValue>" in response
 Examples:
 |pageValue|tagValue|authorValue|
 |1|technology,famous-quotes|Albert Einstein|
 
 
 @negative
Scenario: User calls GetQuotes with incorrect endpoint
   Given I call URL with incorrect endpoint "/quote" with page value "1"
   Then  I see status code 404   
   And   I see error message "The requested resource could not be found"
 
 
 
 
   
   
