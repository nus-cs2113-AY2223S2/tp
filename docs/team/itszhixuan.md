# Lee Zhi Xuan - Project Portfolio Page

## Overview
MyLedger is a desktop app for managing finances, designed for university students studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and monitor their budget and expenses, managing their transactions more effciently.

### Summary of Contributions
#### Main Features Implemented:

1. Parser for each command
   - **What it does** : Processes the inputs made by the user and converts it into its respective `Command` object for further processing. 
   - **Justification**: This allows the program to interact with the user by understand the user inputs made.
   - **Highlights**: This enhancement is required for all subsequent enhancements, which required extensive testing to prevent errors when reading inputs. To reduce duplicate code, each specific parse class will call on `ParseIndividualValue` to efficiently parse all input parameters and commands from a user.
         The implementation was challenging as each parsing action required a specific command class that had to inherit from `Command` and adhere to its guidelines. Also as previously mentioned, the challenge was also to consider all possible inputs that might result in our application not working.

2. Comparing budget with expenditures
   - **What it does**: `SetBudgetCommand` with the command `set` allows the user to input a positive numerical value that is not 0 that indicates their existing budget. `CheckBudgetCommand` with the command word `check` will then compare the budget with the sum of all expenditures made by the user. Output will display the current financial status compared to their budget.
   - **Justification**: With MyLedger being an app that allows users to monitor their budget and expenses, this will bridge the two together and provide insights on their current financial status with their budget.
   - **Highlights**: The challenge of comparing budget with all expenditures is having to consider the user inputs and different expenses. `Borrow` and `lend` are not considered official expenditures and would still require to be paid back, while other factors such as `marked` will also affect the total expenditure used to compare with their budget. The message output should also provide enough insights into their financial status.

3. Duplicate Command
   - **What it does**: Duplicates existing inputs in the list.
   - **Justification**: For repeated expenditures, it reduces the hassle of having to input all expenditure parameters again
   - **Highlights**: This enhancement provides an alternative way to create similar expenditures. Implementing this functionality requires reading the data on the txt.file and subsequently writing on it.

#### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=itszhixuan&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=itszhixuan&tabRepo=AY2223S2-CS2113-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Documentation: 
1. User Guide:
   - Added instructions for Quick Start

2. Developer Guide
   - Added documentation and sequence diagram for `Parser` 
   - Added all possible instructions for instructors to manual test for v2.0

#### Team-based Tasks
1. Attended and contributed to weekly discussions
2. PR reviewed with non-trivial review comments [#30](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/30)
, [#40](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/40) , [#49](https://github.com/AY2223S2-CS2113-T14-3/tp/pull/49)
3. Non-trivial bug fixes: [#90](https://github.com/AY2223S2-CS2113-T14-3/tp/issues/90)