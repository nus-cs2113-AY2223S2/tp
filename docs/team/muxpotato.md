MuxPotato - Project Portfolio Page

## Overview
SEP Helper is a desktop application for Mechanical Engineering students, studying at the
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).

### Summary of Contributions

## Code Contributed: 
https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=muxpotato&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MuxPotato&tabRepo=AY2223S2-CS2113-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false

## Enhancements implemented:

**User Interface** 

Made User Interface that prints out majority of messages out to users when using the application.

**Listing functions** 

Added 
1. List Current Pu command 
2. List Current Command

Altered list functions to list out specific module list for Partner Universities instead of listing out 
all modules users has selected.

Provided switch case template for parser to process user inputs.

Used sort function with comparators (self-learnt) to sort modules according to printing length to 
make application more user-friendly.

**Unit Testing for UI**

Added unit testing for printing functions (self-learnt) using ByteArrayOutputStream library.

## Add/Remove Function
Edited Add and Remove function to use index in order to add/remove functions.

Altered both functions to utilize simple algorithms to support add/remove functions using indices relative
to specific PUs list. 

## Contributions to the UG:
Added section for list functions for user modules and Korea Partner Universities Modules.

## Contributions to the DG:
Drafted application Architecture, and high-level overall description of program.

Drew Sequence Diagram for List commands and remove command.

## Contributions to team-based tasks:

**Integration of Storage, Parser and DataReader.**

Integrated Storage, Parser and DataReader classes earlier on in the project for the first working prototype.
Resolved errors caused by Github Actions 3 checks, in particular the I/O tests caused by integration of various classes.

Pull Request Link: https://github.com/AY2223S2-CS2113-T12-3/tp/pull/13

## Review/mentoring contributions:
Assisted teammates with bug with whitespaces with Grandle + Github Actions checks.
Issue Link: https://github.com/AY2223S2-CS2113-T12-3/tp/issues/42

Assisted teammates with PlantUML syntax.