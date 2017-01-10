*** Settings ***
Resource  DemoSwing_common.robot
Suite Setup  Start Test Application
*** Variables ***

*** Test Cases ***
DemoSwing Test Case Button1
    Push the button  Button1
    Check the Result TextField   You have clicked Button1

DemoSwing Test Case Button2
    Push the button  Button2
    Check the Result TextField   You have clicked Button2

DemoSwing Test Case Button3
    Push the button  Button3
    Check the Result TextField   You have clicked Button3

DemoSwing Test Case Long-Named Button 4
    Push the button  Long-Named Button 4
    Check the Result TextField   You have clicked Long-Named Button 4

DemoSwing Test Case Button5
    Push the button  Button5
    Check the Result TextField   You have clicked Button5