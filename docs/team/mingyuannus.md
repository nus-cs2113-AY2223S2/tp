# Li Mingyuan - Project Portfolio Page

## Moneymind
MoneyMind is a desktop app for managing your finances, optimized for use via a Command Line Interface (CLI). It is written in Java, has cross-platform compatibility with Windows, Linux and MacOS, and has about 3 kLoC.

### Summary of Contributions
* Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=mingyuannus&tabRepo=AY2223S2-CS2113-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* Feature implemented: Created the main class
  * What it does: The main class for the program which handles the main loop of the program.
  * Justification: Required for the program to function and is implemented using OOP best practices.
* Feature implemented: Added command to search for categories and events
  * What it does: Allows users to search for categories and events matching a certain keyword.
  * Justification: Users who have many categories and events may want to quickly find categories or events without having to manually find it from the entire list of categories or events.
* Feature implemented: Implemented the command interface architecture
  * What it does:  Ensures that all commands has a specific set of methods by inheriting from the command interface.
  * Justification: Better conforms to OOP principles and let any command be executed in a consistent way, and that new commands can be easily added by simply implementing the required methods.
* Feature implemented: Added exception to handle invalid commands
  * What is does: Exception class that is thrown when an invalid command error occurs and contains logic to show error message.
  * Justification: Provides a uniform way of handling exceptions due to invalid user input regardless of the type of command.
* Documentation
  * User guide
	  * Added documentation for the search command
	  * Formatting and other tweaks
  * Developer guide
	  * Added class diagram for commands component and parser component
	  * Formatting and other tweaks
