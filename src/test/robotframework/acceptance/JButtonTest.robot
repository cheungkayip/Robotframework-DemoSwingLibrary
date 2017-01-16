*** Settings ***
Resource  DemoSwing_common.robot
Suite Setup  Start Test Application
*** Variables ***

*** Keywords ***
#Gherkin style written actions which are mapped in DemoSwing_common.robot
I want to push the "${buttonName}"
    Push the button  ${buttonName}

*** Test Cases ***
# Testcases that are mapped to the Keywords
JButton Test Case 1
    Given I want to push the "Button1"
    Then I want to see Result TextField to change to "You have clicked Button1"

JButton Test Case 2
    Given I want to push the "Button2"
    Then I want to see Result TextField to change to "You have clicked Button2"

JButton Test Case 3
    Given I want to push the "Button3"
    Then I want to see Result TextField to change to "You have clicked Button3"

JButton Test Case 4
    Given I want to push the "Long-Named Button 4"
    Then I want to see Result TextField to change to "You have clicked Long-Named Button 4"

JButton Test Case 5
    Given I want to push the "Button5"
    Then I want to see Result TextField to change to "You have clicked Button5"


