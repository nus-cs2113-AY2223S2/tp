# Leonardo Ong Dingchao - Project Portfolio Page

## Overview
MyLedger is a desktop app for managing finances, designed for university students studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and monitor their budget and expenses, managing their transactions more effciently.

### Summary of Contributions
#### Main Features Implemented:

1. Storage for MyLedger
   - **What it does** : Processes the list of expenditures in MyLedger, updating the text file with the save information after every command, and initializing the list
   when MyLedger begins running.
   - **Justification**: This allows the program to remember past user inputs, making MyLedger a more effective ledger.
   - **Highlights**: This feature is an essential part of MyLedger, as the programme being unable to store past entries defeats the purpose of a ledger.
   The challenge of the implementation came in the form of having to account for how to store the different types of expenditures, with some expenditures having more fields than others, as well as account for how to deal with a corrupted save file.

2. View command
   - **What it does**: `ViewTypeCommand` and `ViewDateCommand` with the command `viewtype` and `viewdate` shows the user a filtered list of expenditures that have a specific command or date, as well as tallying the total amount of the expenditures filtered by the list.
   - **Justification**: With MyLedger being an app that allows users to monitor their budget and expenses, this feature allows the user to more effiently track their expenditures of each type or date.
   - **Highlights**: Implementing `ViewTypeCommand` was more challenging compared to `ViewDateCommand`. `ViewDateCommand` could be done by comparing the input LocalDate with the LocalDate attribute stored within each expenditure, whereas for `ViewTypeCommand` the expenditure type does not have an attribute allocated to it, and a getType method, which returns a different string based on expenditure type, was needed for comparison. 

3. Currency feature
   - **What it does**: A new class containing the currency values of the countries with universities partnered with NUS based on SGD. `showrates` is a command that lists out all these curries values and the List and View commands have been added with a currency field, that allows the user to output the expenditure amount in the desired currency. 
   - **Justification**: This implementation value adds MyLedger in making the app more convenient for exchange students studying in NUS, giving them a gauge on how much their expenditure is in their home currency.
   - **Highlights**: Initially we proposed using an API to constantly update the currencies values of the feature, however considering the large amounts of potential bugs and requiring internet connection, we decided the app would benefit more from being concise and using a constant value that servers as a rough gauge. The challenges faced when implementing this feature comes from all of the methods involved in printing an expenditure already using the toString method without a currency field, and changing the toString to include that field would introduce further issues, thus a new method that functions similarly to toString had to be implemented.

#### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=Chick3nBoy&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Chick3nBoy&tabRepo=AY2223S2-CS2113-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Documentation: 
1. User Guide:
   - Added instructions for Quick Start

2. Developer Guide
   - Added architecture diagram and documentation. 
   - Added documentation and sequence diagram for `Storage`, `View Command`, and `Currency`

#### Team-based Tasks
1. Attended and contributed to weekly discussions
2. PR reviewed with non-trivial review comments [#18](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/18) , 
[#41](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/41) , [#134](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/134)
3. Non-trivial bug fixes: [#28](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/28/commits)