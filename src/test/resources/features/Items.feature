@Item @smokeTest @FinalProject
Feature: Items management

  Background: 
    Given As an entity user, I am logged in
    When I navigate to Items tab

  @additem
  Scenario: As a user, I am able to create an item or a service
    And I click on +Add Item button
    Then I should be on the page with Name, Price and Unit dropdown
    And I confirm that the unit dropdown has 34 options
    And I confirm that Description is displayed
    When I provide item name "Candle", price "15.99", unit "pc", and description "white non scented candle"
    And I click Save Item button
    Then I should see New Item is added to the Item list table
    And I delete the item

  @verifyNameField
  Scenario Outline: As a user, I am able to enter Name successfully or see the error message
    And I click on +Add Item button
    When I enter <length> characters Name
    Then I should see name "<error message>" if it exist

    Examples: 
      | length | error message                      |
      #|     50 |                                              |
      #|     51 | Display Name must be less than 50 characters |
      #|      1 |                                              |
      #|      2 |                                              |
      #|     49 |                                              |
      #########
      |     50 |                                    |
      |     51 |                                    |
      |      1 | Name must have at least 3 letters. |
      |      2 | Name must have at least 3 letters. |
      |     49 |                                    |

  @verifyDescriptionField
  Scenario Outline: As a user, I am able to enter Description successfully or see the error message
    And I click on +Add Item button
    When I enter <length> characters Description
    Then I should see description "<error message>" if it exist

    Examples: 
      | length | error message                              |
      #|    201 | Description must be 200 characters or less |
      #|      1 |                                            |
      #|      2 |                                            |
      #|    199 |                                            |
      #|    200 |                                            |
#########
      |    201 |                                            |
      |      1 |                                            |
      |      2 |                                            |
      |    199 |                                            |
      |    200 |                                            |
