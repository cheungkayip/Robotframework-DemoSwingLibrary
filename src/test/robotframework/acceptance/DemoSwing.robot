*** Settings ***
Resource  DemoSwing_common.robot
Suite Setup  Start Test Application
*** Variables ***

*** Keywords ***
I want to push the "${buttonName}"
    Push the button  ${buttonName}

I want to see Result TextField to change to "${resultValue}"
    Check the Result TextField   ${resultValue}

*** Test Cases ***
DemoSwing Test Case 1
    Given I want to push the "Button1"
    Then I want to see Result TextField to change to "You have clicked Button1"

DemoSwing Test Case 2
    Given I want to push the "Button2"
    Then I want to see Result TextField to change to "You have clicked Button2"

DemoSwing Test Case 3
    Given I want to push the "Button3"
    Then I want to see Result TextField to change to "You have clicked Button3"

DemoSwing Test Case 4
    Given I want to push the "Long-Named Button 4"
    Then I want to see Result TextField to change to "You have clicked Long-Named Button 4"

DemoSwing Test Case 5
    Given I want to push the "Button5"
    Then I want to see Result TextField to change to "You have clicked Button5"


