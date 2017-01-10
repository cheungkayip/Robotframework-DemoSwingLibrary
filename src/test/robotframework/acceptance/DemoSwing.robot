*** Settings ***
Resource  DemoSwing_common.robot
Suite Setup  Start Test Application
*** Variables ***

*** Test Cases ***
DemoSwing Test Case
    Push the button    Button1
    Push the button    Button2
    Push the button    Button3
    Push the button    Long-Named Button 4
    Push the button    Button5
    Check the Result TextField   You have clicked Button5