# Keane Chan - Project Portfolio Page

## Overview: PC Part Picker
PC Part picker is a CLI application that allows users to create and save their own custom PC build. Our application allows users to add and remove components from their build, while viewing the total price against their budget of their build. 
## Summary of Contributions
[Code Contributed](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=typing&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=typingpanda&tabRepo=AY2223S2-CS2113-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Enhancements implemented:

**Functional Code**
- `BuilderListComponentCommand` for builder mode
  - Filter methods for list component command for all component types
- `BuilderEditNameCommand` for builder mode
  - Command to change the name of a PC build
- `BuilderEditBudgetCommand` for builder mode
  - Command to change the budget of a PC build
- `BackCommand` for builder mode
  - Command to return from PCBuilderMenu to PCViewerMenu
- `ComponentList` 
  - Filter methods for all component types
- `PCBuilderMenu` 
  - Menu to display the user's PC build
- `UnrecognizedCommand` class for handling invalid commands
  
**JUnit Tests**
- JUnit Tests for `BuilderListComponentCommand`, `BuilderEditNameCommand`, `BuilderEditBudgetCommand`, `BackCommand` and `BuilderSelectComponentCommand`

**Logging**
- Logging for `BigPP` class
  - Logging for overall flow of the application
  
---
## Contributions to the UG:
-  `list COMPONENT_TYPE [-FLAG]` command
- `name` command for builder mode
- `budget` command for builder mode

In the UG, clear instructions were given for the user to follow to use the commands. The UG also included examples to help the user understand how to use the commands.

---
## Contributions to the DG:
- `UI` Class Diagram
- Object Diagrams for `ViewerAddCommand` and `BuilderEditNameCommand`

In the DG, explanations were given for the design of the application. The diagrams were also used to help understand the design of the application.

---
## Review/mentoring contributions:

  - [PR1](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/35#discussion_r1128972854)
  - [PR2](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/97#discussion_r1141366981)
  - [PR3](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/33#discussion_r1129009774)
  - [PR4](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/68#discussion_r1135988546)
  - [PR5](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/108#discussion_r1144216412)
  
  In these pull requests, I reviewed my teammates' code by facilitating discussions on the code and complimenting them on their work when deserved. I also explained the rationale behind my code to my teammates when they asked for clarification.  
