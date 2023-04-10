# Nguyen Duc Thang - Project Portfolio Page

## Moneymind
MoneyMind is a desktop app for managing your finances, optimized for use via a Command Line Interface (CLI). It is written in Java, has cross-platform compatibility with Windows, Linux and MacOS, and has about 3 kLoC.

### Summary of Contributions
* Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=Mnsd05&tabRepo=AY2223S2-CS2113-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* Feature implemented: Added the ability to parse user input
    * What it does: Ensure the user input is in correct format before executing the command
    * Justification: This feature is integral to perform any useful tasks. It enables the application to process and interpret user input accurately and efficiently, improving both the user experience and the functionality of the application.
    * Why it is hard to implement: Because it requires to handle all the possible exceptions that may occur when parsing the user input and to define custom exceptions to handle the unexpected cases like integer overflow, dummy input, etc. Most importantly, I have to come up with complex and efficient regexes to parse the user input.
* Features implemented: Added the logic to execute every feature of the product except "Search", "Bye" and "Summary" commands
    * What it does: Add, delete categories and view all the events in a category. Add, delete, edit events and view every event in the list. 
    * Justification: Those features are important because it contains the core functionality of the application.
* Contributions to the UG:
    * Added documentation for the format of "event", "edit", "delete" and "view" commands and
  examples of using them
* Contributions to the DG:
    * Added implementation details of the `command', 'parser', 'exception', 'category' and 'event' components.
    * Added manual testing instructions.
* Review/mentoring contributions:
    * Find bugs for teammates
    * Resolve merge conflicts
