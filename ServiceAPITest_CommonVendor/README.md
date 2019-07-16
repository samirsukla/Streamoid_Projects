# README
This README would normally document whatever steps are necessary to get your application up and running.

# Service API Check for Common Vendors
This project contains the script for Schema validation of all the APIs for all the other Commaon Vendors like ABFRL, Pothys...etc.

# Purpose
The idea behind this script is to find out the differences between the stored schema and the actual schema from the realtime APIs. Incase there are any differences, the script will fail and the notificcation will be sent to the respective service owners.

# Implementation
- For all the V1 APIs, we got the schema from online JSON schema convertor and stored in a `.json` file in our resources folder.
- In the script, we hit the real time APIs and get the schema of the response we got using Java libraray.
- Then we compare the stored schema with the actual schema. If there is no mismatch between these two schemas, then the test will pass otherwise test will fail.
- The test report will be sent to the stake holders.

# Installation
To run the project, the following dependencies need to be installed.

- Ecplise IDE
- Selenium JARs
- TestNG JARs
- Json Schema Validator JARs
- Rest Assured JARs
- aws-java-sdk & amazon-kinesis-client JARs
- maven-compiler-plugin JARs

# Execution
To execute the project, the .sh file need be run with the following command

```sh apischemavalidation.sh```
