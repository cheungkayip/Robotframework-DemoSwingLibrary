*** Settings ***
Library  SwingLibrary

*** Variables ***

*** Keywords ***
Start Test Application
    Start Application     nl.ns.demoswing.impl.DemoSwing
    Select Window         Demo Swing Application

Push the button
    [Arguments]    ${buttonName}
    Push Button    ${buttonName}

Check the Result TextField
    [Arguments]    ${textFieldValue}
    ${item}=  Get Textfield Value  Result
    Should Be Equal  ${item}  ${textFieldValue}