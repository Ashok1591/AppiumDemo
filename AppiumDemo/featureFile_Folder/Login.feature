Feature: Test Login screen 

@sanity	
Scenario Outline: Login with valid user name and password
    When I enter username as "<username>"
    And I enter password as "<password>"
    And I login
    Then I should see Products page with title "<title>"
   
    Examples:
      | username      | password     | title    |
      | standard_user | secret_sauce | PRODUCTS |