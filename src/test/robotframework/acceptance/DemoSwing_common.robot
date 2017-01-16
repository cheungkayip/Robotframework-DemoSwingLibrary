*** Variables ***
${screenshotDirectory}    ./target/robotframework-reports/robotframework-screenshots
*** Settings ***
Library  SwingLibrary
Library  DateTime
Library  OperatingSystem
Library  Screenshot

*** Keywords ***
Start Test Application
    Start Application     nl.ns.demoswing.impl.DemoSwing
    Select Window         Demo Swing Application
    ${date}=  Get Current Date
    Create Directory  ${screenshotDirectory}_${date}
    Set Screenshot Directory  ${screenshotDirectory}_${date}

Push the button
    [Arguments]    ${buttonName}
    Push Button    ${buttonName}
    Take Screenshot  ./Button

Click the radio button
    [Arguments]    ${radioButtonName}
    Push Radio Button    ${radioButtonName}
    Take Screenshot  ./RadioButton

Check the Result TextField
    [Arguments]    ${textFieldValue}
    ${item}=  Get Textfield Value  Result
    Should Be Equal  ${item}  ${textFieldValue}
    Take Screenshot  ./TextFieldResult

#General Gherkin Cucumber style scenarios
I want to see Result TextField to change to "${resultValue}"
    Check the Result TextField   ${resultValue}