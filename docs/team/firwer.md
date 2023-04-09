# Poh Wei Pin - Project Portfolio Page

## Overview
MagusStock is a Java command-line interface (CLI) application designed for inventory management.
This application that I have contributed to developing with my team aims to help store operators,
IT administrators and logistics managers to manage their inventory more efficiently and effectively.
While CLI applications are not as user-friendly as GUI applications, they are much faster to use
and more importantly, portable and can be used on any platform that supports Java.


### Summary of Contributions
Code contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=firwer&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=firwer&tabRepo=AY2223S2-CS2113-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=other~functional-code~test-code~docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

Enhancements implemented:
* Refactored and abstracted the whole Command class from inventory methods to its
  own class, `Command` and its subclasses.
* Created the AddCommand and AddParser feature
* Implemented Autosave feature to save the inventory data to a file after every command.
* Implemented the CSV reading and writing ability for the application.
* Implemented dashboard for inventory overview
* Standardisation of Error messages
* Javadocs Implementation for classes and methods
* Implemented JUnit tests for the `Add`, `Storage` and `AddParser`

Contributions to the UG:
* Wrote the documentation for the `add`, `autosave`, `db` command.
* Revamped the overall structure of the UG to make it more user-friendly and intuitive based on PED feedbacks.
* Introduced a better navigation system for the UG, with the use of Docsify and sidebar.
* Fixed typos and grammatical errors in the UG.
* Refactored the UG to make it more consistent in terms of formatting and phrasing.
* Fixed broken anchor links
* Improved overall user experience of our guide with a professional logo that I have designed


Contributions to the DG:
* Designed and came up with the architecture diagram of Magus Stock
* Wrote the breakdown and explaination of the architecture diagram
* Designed the Sequence Diagram overview of Magus Stock
* Designed Sequence Diagram and UML Diagram of `Add` Command (Including its parser)
* Wrote the explaination and steps of the `Add` Command
* Created the class diagrams for `Parser` and `Command` components

Contributions to team-based tasks:
* Created the organisation and repository for the team
* Setting up of permissions, adding team members and assigning roles
* Maintaining issue tracker through regular use of labels, role assignments and milestones
* Provided general reviews and suggestions to my team members PR
* Created the Architecture diagram of our project in the DG
* Designed the Sequence Diagram overview of our project in the DG
* Incorporated the use of Docsify to our project
* Designed a professional looking logo for our project
* Consistent checkstyle and code quality checks using Checkstyle, SpotBugs and SonarLint

Review/mentoring contributions:
* Reviewed team members' PRs and provided suggestions and feedbacks


Contribution DG Extracts:

![SequenceDiagram.png](..%2FSequenceDiagram.png)
![AddCommand.png](..%2FUML%2FAdd%2FAddCommand.png)
![AddParser.png](..%2FUML%2FAdd%2FAddParser.png)
![ParserClassDiagram.png](..%2FParserClassDiagram.png)
![Command_ParserFlowClassDiagram.png](..%2FCommand_ParserFlowClassDiagram.png)
![CommandClassDiagram.png](..%2FCommandClassDiagram.png)