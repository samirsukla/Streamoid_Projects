# README #

This README would normally document whatever steps are necessary to get your application up and running.

# Text Search Sanity

This project contains the script for Text Search Sanity.

## Purpose 

The idea behind this script is to find out the differences between a list of expected PIDs and the actual outcome of the realtime API.

## Implementation

- For a list of Text Search query, we store the IDs of first 10 relevant products for individual queries.
- In the script, we hit the real time API with the above mentioned queries everyday.
- In the test report, we list out differences between the stored PIDs with the actual PIDs against a particular query.
- If there is no mismatch in both the list, we display the row with background color Green, else the row will be diaplayed as Red with mentioning the number of differences and the list of different product IDs.
- At the end of the week, we will look into the whole weeks report and find out the relevance of the new PIDs with respect to the given query.
- The test results will be sent to the stake holders incase anything fails.



## Installation

To run the project, the following dependencies need to be installed.

- Ecplise IDE
- Selenium JARs
- TestNG JARs
- Apache POI JARs
- aws-java-sdk & amazon-kinesis-client JARs
- maven-compiler-plugin JARs

## Execution

To execute the project, the .sh file need be run with the following command

`sh runtextsearchsuite.sh`