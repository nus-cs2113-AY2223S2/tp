# Toh Hong Feng - Project Portfolio Page

## Moneymind
MoneyMind is a desktop app for managing your finances, optimized for use via a Command Line Interface (CLI). It is written in Java, has cross-platform compatibility with Windows, Linux and MacOS, and has about 3 kLoC.

### Summary of Contributions
* Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Toh-HongFeng&tabRepo=AY2223S2-CS2113-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* Feature implemented: Added the ability to save and load the program's data
    * What it does: allows the user to automatically save and load the current list of categories and expenses to a text file
    * Justification: This feature is integral to the product because a user can save current session to continue in the future, or to send to another friend who can also use the list.
* Feature implemented: Added the ability to reminder users in advance of upcoming expenses
    * What it does: allows the user to use the date of an monthly recurring expense as a reminder for the user to prepare for the expense. This feature automatically updates the reminder date when the user adds a new expense to its next occurrence.
    * Justification: This feature is important because it allows the user to be reminded of upcoming expenses, and to plan their finances accordingly.
* Contributions to the UG:
    * Added documentation for the `reminder` feature
    * Added Q&A for the `storage` feature
    * Initial draft of the `Features` section of the UG
* Contributions to the DG:
    * Added implementation details of the `storage` feature
* Contributions to team-based tasks:
    * Set up team organization and repository on GitHub
    * Managed releases [v1.0](https://github.com/AY2223S2-CS2113-T15-3/tp/releases/tag/v1.0), [v2.0](https://github.com/AY2223S2-CS2113-T15-3/tp/releases/tag/v2.0) on GitHub
* Review/mentoring contributions:
    * Resolve merge conflicts for v1.0 (PRs [#8](https://github.com/AY2223S2-CS2113-T15-3/tp/pull/14), [#23](https://github.com/AY2223S2-CS2113-T15-3/tp/pull/23))
* Documentation: 
    * User Guide:
        * Created initial draft of `Features` section of UG
    * Developer Guide:
        * Added implementation details of the `storage` component
        * Added implementation details of the `reminder` component