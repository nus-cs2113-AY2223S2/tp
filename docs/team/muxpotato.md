MuxPotato - Project Portfolio Page

## Overview
SEP Helper is a desktop application for Mechanical Engineering students, studying at the
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).

### Summary of Contributions

#### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=muxpotato&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=MuxPotato&tabRepo=AY2223S2-CS2113-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


## Enhancements implemented:

**User Interface** 

Created User Interface that prints out majority of messages out to users when using the application.

**Listing functions** 

Added 
1. List Current Pu command 
2. List Current Command
3. List Pu Command
4. List PU modules Command

Link to Pull Request: [13](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/13),
[63](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/63), [59](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/59)

**Enhancements to Listing Functions**

1. Altered list functions to list out specific module list for Partner Universities instead of listing out 
all modules users has selected.
2. Added Sorting function to sort module information printed according to length displayed on user console for
increase in readability. Used Comparator with Module class to achieve this.
3. Improved List Pu function for increase in readability. 

Link to Pull Request: [151](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/151)

Motivation from these enhancements were from PE-Dry Run.

Link to Issues: [129](https://github.com/AY2223S2-CS2113-T12-3/tp/issues/129),
[113](https://github.com/AY2223S2-CS2113-T12-3/tp/issues/113), 
[95](https://github.com/AY2223S2-CS2113-T12-3/tp/issues/95)

**Parser Class**

Provided switch case template for parser to process user inputs. 


**Unit Testing for UI**

Added unit testing for printing functions (self-learnt) using ByteArrayOutputStream library.
Link to PR: [41](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/41)

**Add/Remove Function**
Altered both functions to utilize simple algorithms to support add/remove functions using indices relative
to specific PUs list.
Link to PR: [152](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/152)

## Contributions to the UG:
Added section for list functions for user modules and Korea Partner Universities Modules.

Edited Quick Start Guide.

Added command summary table. [171](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/171)


## Contributions to the DG:
1. Drafted application Architecture picture, and high-level overall description of program.
   Link to PR: [53](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/53)
2. Drew Sequence Diagrams for List commands and remove command.
Link to PR: [53](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/53), 
[78](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/78)
3. Drew final application Architecture picture using WonderShareEdraw and wrote the majority of architecture description.
   Link to PR: [191](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/191)
4. Main bulk of Instructions for Manual Testing.
   Link to PR: [210](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/210)
5. Wrote Product Scope, Target User Profile and Value Proposition.
   Link to PR: [183](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/183)
6. Class Diagram and explanation for UI and List Commands.
   Link to PR: [78](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/78)



## Contributions to team-based tasks:

**Integration of Storage, Parser and DataReader.**

Integrated Storage, Parser and DataReader classes earlier on in the project for the first working prototype.
Resolved errors caused by Github Actions 3 checks, in particular the I/O tests caused by integration of various classes.

Pull Request Link: [13](https://github.com/AY2223S2-CS2113-T12-3/tp/pull/13)

## Review/mentoring contributions:
Assisted teammates with bug with whitespaces with Grandle + Github Actions checks.
Issue Link: [42](https://github.com/AY2223S2-CS2113-T12-3/tp/issues/42)

Assisted teammates with PlantUML syntax.
