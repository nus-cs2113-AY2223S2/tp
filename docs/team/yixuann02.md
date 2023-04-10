# Yi Xuan - Project Portfolio Page

## Overview

Apollo is a scheduling app for managing your tasks and lessons at the same time,
with integrated module information from the NUSMods database.
The target user, an average NUS student, interacts with Apollo using a CLI.
It is written in Java, and has about 5kLoC.

### Summary of Contributions

- [My code contributions](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=13-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=yixuann02&tabRepo=AY2223S2-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented

- Added the ability to show class information for a module
- Added the ability to list the module list from timetable
- Added the ability to list specific module and classes from module list
- Added clash warnings for Event-Deadlines clashes
- JUnit tests with full branch coverage for Module, ModuleList and SemesterUtils classes
- Junit tests for Parser, Ui, and ListModuleWithLessonCommand classes
- Patches, bug fixes
- Enhancing methods using Single Level of Abstraction Principle

#### Contributions to the UG

- Added/Elaborated on the following sections to the UG:
    - Listing the module list
    - Listing a module and class from module list
    - Showing a module information
    - Showing a module class information

#### Contributions to the DG

- Added/Elaborated on the following classes in the DG:
  - ShowModuleCommand 
  - ListModuleWithLessonCommand 
  - ListCommand
  - FindCommand
    - Find Task
    - Find Task on Date
  - Ui Component
  - Parser Component
  - Storage Component

- Added the following PlantUML diagrams:
  - ShowModuleCommand Sequence Diagram
  - ListModuleWithLessonCommand Sequence Diagram
  - ListCommand Sequence Diagram
  - DeleteModuleCommand Sequence Diagram
  - Parser Sequence Diagram
  - DeleteCommand Activity Diagram
  - Ui Class Diagram

#### Contributions to team-based tasks

- Assisted in maintaining the issue tracker
- Created Powerpoint slides for Demo Video
- Most instances of helping team members were done offline during our weekly meetings
- [PRs reviewed](https://github.com/AY2223S2-CS2113-T13-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ayixuann02)
