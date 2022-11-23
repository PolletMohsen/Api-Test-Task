Feature: Users API Testing

  Scenario: GET USERS API
    Given Get Users API is provided
    When User call users API
    Then OK status code should be returned

  Scenario: Create USERS API
    Given  Have a create user API
    When  Call create API
    Then User will be create with OK status code

  Scenario: GET SPECIFIC USERS API
    Given Get Users By Id API is provided
    When User call get users API with id
    Then user name should be returned

