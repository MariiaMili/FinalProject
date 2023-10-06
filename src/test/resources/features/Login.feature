@Login @Regression @FinalProject
Feature: User/login management

  Background: As a user, I am on the login page

  @validLogin @smokeTest
  Scenario: User should be able to login with valid credentials
    When I enter valid usermail "useremail" and valid password "password"
    And I click on login button
    Then I should see the "Logged in successfully."
    And I should be directed to the Dashboard page.

  #@blankCredentialsLogin
  #Scenario: User should be able to see an error message when login with blank credentials
  #Given As a user, I am on the login page
  #When I enter blank usermail "<useremail>" and blank password "<password>"
  #And Click on login button
  #Then I should see an error message "Email is Required" under usermail and "Password is Required" under password
  #And I should not be logged in
  @invalidCredentialsLogin
  Scenario Outline: Login with invalid user credentials
    When I enter invalid usermail "<usermail>" and password "<password>"
    And I click on login button
    Then I should see the usermail error message "<usermail error message>" if it exist
    And I should see the password error message "<password error message>" if it exist
    And I should see the popup error message "<popup error message>" if it exist
    And I should not be logged in

    Examples: Credentials Data
      | usermail                  | password         | usermail error message | password error message | popup error message                         |
      #|                           |                  | Email is required      | Password is required   |                                             |
      #|                           | primetech@school | Email is required      |                        |                                             |
      #| invalid@gmail.com         |                  |                        | Password is required   | Those credentials do not match our records. |
      #|                           | prime@school     | Email is required      |                        | Those credentials do not match our records. |
      #| fg                        | primetech@school |                        |                        | Those credentials do not match our records. |
      #| dummy@primetechschool.com | prime@school     |                        |                        | Those credentials do not match our records. |
      #| primetechschool.com       | prime@school     |                        |                        | Those credentials do not match our records. |
      #| dummy@primetechschool.com |                  |                        | Password is required   |                                             |
      
      #########
      |                           |                  | Field is required      | Field is required      |                                             |
      |                           | primetech@school | Field is required      |                        |                                             |
      | invalid@gmail.com         |                  |                        | Field is required      |                                             |
      |                           | prime@school     | Field is required      |                        |                                             |
      | fg                        | primetech@school | Incorrect Email.       |                        |                                             |
      | dummy@primetechschool.com | prime@school     |                        |                        | Those credentials do not match our records. |
      | primetechschool.com       | prime@school     | Incorrect Email.       |                        |                                             |
      | dummy@primetechschool.com |                  |                        | Field is required      |                                             |
