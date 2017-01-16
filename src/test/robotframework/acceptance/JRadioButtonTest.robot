*** Settings ***
Resource  DemoSwing_common.robot
Suite Setup  Start Test Application
*** Variables ***

*** Keywords ***
#Gherkin style written actions which are mapped in DemoSwing_common.robot
I want to click the radiobutton "${buttonName}"
    Click the radio button  ${buttonName}

*** Test Cases ***
# Testcases that are mapped to the Keywords 
JRadioButton Test Case 1
    Given I want to click the radiobutton "RadioButton1"
    Then I want to see Result TextField to change to "You have selected RadioButton1"



