@UIComponents @FinalProject
Feature: Validation of UI components

  Background: 
    Given As a user, I am on the login page

  @Login_UIComponents
  Scenario: User should be able to locate Login page components
    When I view the page title
    #Then I verify the page title contains "crater"
    And I view the email text box placed underneath the title
    Then I verify email text box has label "Email"
    And I view the password Text box placed underneath the Email text box
    Then I verify ithe password Text box has label "Password"
    And I view the forgot password link placed underneath the password text box
    Then I verify a link has title "Forgot Password?"
    And I view the Login button placed underneath the forgot password link
    Then I verify a button is labeled "Login"
    Then I verify the footer has a text "Copyright @ Crater Invoice, Inc. 2023".
    And I view the main Heading to the right of the page
    Then I see the main Heading located to the right with the text "Simple Invoicing for Individuals Small Businesses"
    And I view secondary Heading
    Then I verify the second Heading has a text "Crater helps you track expenses, record payments & generate beautiful invoices & estimates."

  @Items_UIComponents
  Scenario: User should be able to locate Items page components
    Given As an entity user, I am logged in
    When I navigate to Items tab
    Then I view the menu navigation path as "Home/Items" placed under Items
    And I view a secondary button titled "Filter" with a filter icon
    And I should see a primary button titled "+ Add Item"
    Then I view a table with columns: "Select All checkbox","NAME","UNIT","PRICE","ADDED ON"
    And I should see a link icon showing three dots 
    When I click on the three dots link
    Then I should see options:"Edit" and "Delete"
    Then I should see pagination text "Showing 1 to 10 of 105 results"
    And I should see pagination navigation with controls
    Then I should see number indicating the page the user is currently on and the next upcoming page
    And I should see left arrow is disabled
    Then I click on the last page button
    And I should see right arrow is disabled
