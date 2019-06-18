# README
This README would normally document whatever steps are necessary to get your application up and running.

# Text Search Sanity
This project contains the script SDK validation on the PDP for all the live clients.

# Purpose
The idea behind this script is to traverse through each and every category for all the live clients and click on any random product, then validate the products present on the SDK on PDP. Incase the SDK is not present on the PDP or the products are different from the API result, the script will fail and the notificcation will be sent to the respective service owners.

# Implementation
- For all the live clients, there are individual classes where the script will traverse through all the product categories, click on any product from the list, verify that the SDK is present or not on the PDP.
- If the SDK is present on the PDP, then it will compare the products being displayed in the SDK with  the respective API response.
- If all the above cases are success, then the PDP screenshot will be taken and will be placed on the PASSED folder, else the screenshot will be saved to FAILED folder.
- The test report along with the Folder link will be shared across all the stake holders.

# Installation
To run the project, the following dependencies need to be installed.

- Ecplise IDE
- Selenium JARs
- TestNG JARs
- Rest Assured JARs
- aws-java-sdk & amazon-kinesis-client JARs
- maven-compiler-plugin JARs

# Execution
To execute the project, the .sh file need be run with the following command

```sh runtestng.sh```
