
@tag
Feature: Prchase the order from Ecomerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecomerce Page


  @tag2
  Scenario Outline: Positive test of submiting the order
    Given logged in with username <name> and password <password>
    When I add the produt <productName> from the cart
    And checkout <productName> and submit the order
    Then "THNKYOU FOR THE ORDER" Message is displayed on the confirmationPage

    Examples: 
      | name                    |password  | productName|
      | gavvaladinesh@gmail.com |Dinesh@454| ZARA COAT 3|
      
