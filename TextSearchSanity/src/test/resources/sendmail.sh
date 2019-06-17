#!/bin/bash

cd /Users/samirsukla/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid\ Projects/automation-scripts/TextSearchSanity

mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
mvn test -Dtest=**/SendStatusReport.java
