# Li Mingyuan - Project Portfolio Page

## Moneymind
MoneyMind is a desktop app for managing your finances, optimized for use via a Command Line Interface (CLI). It is written in Java, has cross-platform compatibility with Windows, Linux and MacOS, and has about 3 kLoC.

### Summary of Contributions
* Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=mingyuannus&tabRepo=AY2223S2-CS2113-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* Feature implemented: Added the MoneyMind class
  * What it does: The main class for the program that is created when the program starts up and runs the main loop of the program.
  * Justification: Required for the program to work and is implemented to make use of OOP best practices.
* Feature implemented: Added command to search for categories and events
  * What it does: Allows users to search for categories and events matching a certain keyword.
  * Justification: Users who have many categories and events may want to quickly find a specific category or event without having to manually find it from the entire list of categories or events.
* Feature implemented: Added command interface
  * What it does: Defines methods necessary for all commands to have and needs to be implemented by all commands.
  * Justification: Ensures that all commands has a specific set of methods, such that any command can be executed in a consistent way.
* Feature implemented: Added exception to handle invalid commands
  * What is does: Exception class that is thrown when an invalid command error occurs and contains logic to show error message.
  * Justification: Provides a uniform way of handling exceptions due to invalid user input regardless of the type of command.
* Documentation
  * User guide: Added documentation for the search command
  * Developer guide: Tweaked formatting
