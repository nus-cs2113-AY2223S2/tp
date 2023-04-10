# Leo Zheng Rui, Darren - Project Portfolio Page

## Overview

MyLedger is a desktop app for managing finances, designed for university students in the National University of Singapore (NUS), studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and classify their transactions into categories. This helps them to monitor their budget and expenses more effciently.

### Summary of Contributions
#### Main Features Implemented:

**The expenditure package**
   - **What it does** : Contains all the classes and operations for the fixed expenditure types and expenditure list.
   - **Justification**: The fixed expenditures in the program can be instantiated as real world expenditure, and kept track of in the application's expenditure list. Since the application aims to help users add and to manage expenditures, the package of classes forms the core of MyLedger.
   - **Highlights**: This feature sets the direction of MyLedger. Thus, deciding the attributes of each class played an important role in setting the trajectory of the application; brainstorming the important information the target user would need in their expenditure record.

**The Command Classes for all fixed expenditure types**
   - **What it does**: Contains all the command classes for the fixed expenditure types and to display expenditure list. Additionally, the `CommandResult.java`class displays to the user the result of the command after each successful execution.
   - **Justification**: With the help of MyLedger's parsing unit, the user's input are broken down and fed into their respective command class. The execution of these command classes produces the user's desired outcome (i.e. adding an academic expenditure into the current expenditure list).
   - **Highlights**: The implementation of the command classes requires good coordination with the implementor of the parsing class as they would need information on how each expenditure command is instantiated; it was done well.

<div style="page-break-after: always;"></div>

**The Sort Command**
   - **What it does**: Sorts the expenditure list in ascending or descending amount, or from the latest or earliest dates. 
   - **Justification**: This allows users to sort their expenditures to gain better insight to manage them; to rank the expenditures that spent the most/least money, or ones that have added the most recently/earliest.
   - **Highlights**: The feature will change the order of the expenditure list for future reference. This is done to improve user experience while navigating the sorted list after calling the command.

**The repeating date of the Accommodation and Tuition Expenditures**
   - **What it does**: The `AccommodationExpenditure` and `TuitionExpenditure` takes in a date of repeat. Whether or not the aforementioned expenditures have been marked beforehand, this feature ensures the the expenditures are unmarked after 1 full year.
   - **Justification**: The `AccommodationExpenditure` and `TuitionExpenditure` are lump sum payments and must be paid within a period of time. The nature of these expenditures are repeating and thus, users can trust MyLedger to unmark the aforementioned expenditures to serve as an annual reminder.
   - **Highlights**: This was a challenging feature as `LocalDate` is used extensively to compare the dates. Additionally, the method must be ran for the check to be ran, thus this is implemented in the main loop logic of the program. After a year has passed, the program will retain the day and month values of the repeat date, and increment the year. This will only run once every year and only ran after the specified date is reached or passed.

**Fix save file reading related bugs**
   - **What it does**: The save file stores all expenditures from the most recent expenditure list.
   - **Justification**: Upon corruption of the save file, the program oringally crashes. With the fixes, only individual corrupted expenditures are deleted, uncorrupted expenditures can still be loaded.
   - **Highlights**: Considering all possible combinations for corruption was challenging. Now the save file reading complies with the input rules defined by the application.

#### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=dsicol&tabRepo=AY2223S2-CS2113-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

<div style="page-break-after: always;"></div>

#### Project Management:
1. Milestones:
   - Managed all milestones: `v1.0`, `v2.0` and `v2.1`.
   - Managed all releases: `v1.0`, `v2.0` and `v2.1` (3 releases) on github.

#### Enhancement to existing features:
1. Enhancements:
   - Improved `AccommodationExpenditure` and `TuitionExpenditure` to be unmarked 1 year after setting it. This is due to the repeating nature of these expenditures. (Pull request [#132](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/132))
   - Wrote additional tests for the command classes to increase test coverage (Pull request [#40](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/40)).
   - Fix save file reading bugs (Pull request [#145](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/145), [#153](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/153)).

#### Documentation: 
1. User Guide:
   - Added the MyLedger command summary: [#68](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/68/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
   - Added user guide for `mark`, `unmark`, `set`, `check`, `list`, `find`, `sort`, `viewtype` and `viewdate`: [#78](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/78)

2. Developer Guide
   - Added documentation and class diagram for **Expenditure categories**.
   - Added documentation and sequence diagram for `repeatDate` process.
   - Added documentation and class diagram for **Command component**.
   - Added documentation and sequence diagram for **Add expenditure command** in the **Command List** section.
   - Added documentation and explanations for the `SortCommand`.

#### Team-based Tasks
1. Initiated, led, and contributed to weekly discussions.
2. PR reviewed with non-trivial review comments [#22](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/22), [#151](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/151), [#144](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/144), [#167](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/167)
3. Non-trivial bug fixes: [#127](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/127), [#145](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/145), [#153](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/153)