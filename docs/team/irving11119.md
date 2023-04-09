# Irving - Project Portfolio Page

## Overview
Apollo is a scheduling app for managing your tasks and lessons at the same time,
with integrated module information from the NUSMods database.
The target user, an average NUS student, interacts with Apollo using a CLI.
It is written in Java, and has about 5kLoC.

### Summary of Contributions
- [My code contributions](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=irving&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=irving11119&tabRepo=AY2223S2-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
#### Enhancements Implemented
- Implemented the ability to add modules to the module list 
- Implemented the ability to add classes to timetable
- Implemented the ability to delete modules by index
- Implemented the ability to delete classes
- Implemented the ability to show class information for a module
- Implemented Calendar class and CalendarModule class
- Implemented Module, ModuleList, Timetable classes
- Updated Storage to save and load module list and timetable
- Added backend data integration with NUSMods API
- Contributed to JUnit tests with full branch coverage for following classes:
  - AddModuleCommand
  - DeleteModuleCommand
  - AddClassCommand
  - DeleteClassCommand
  - ShowModuleCommand
#### Contributions to the UG
- Added/Elaborated on the following sections to the UG:
  - Adding a module
  - Deleting a module
  - Adding a class
  - Deleting a class
  - Showing a module
- Formatting of UG
  - Updated table of contents
  - Added hyperlinks to table of contents
- Added Q&A section
#### Contributions to the DG
- Added/Elaborated on the following classes in the DG:
  - AddModuleCommand
  - ListModuleCommand
  - Logging
- Added UML diagrams for the following classes:
  - AddModuleCommand
  - ListModuleCommand
- Miscellaneous:
  - Formatting of DG
  - Update acknowledgements
    - Include libraries used in project
#### Contributions to team-based tasks
- Set up GitHub Team Org and Repository
- Set up issue tracker and milestones
- Assisted in maintaining the issue tracker
- Assisted in release management (v2.0)
- Incorporated external library (GSON) into project
- Scraped NUSMods API for module information using self written Python script
- Most instances of helping team members were done offline during our weekly meetings.
  - [NUSMods Scraper](https://github.com/irving11119/NUSMods-Scraper) 
- [PRs reviewed](https://github.com/AY2223S2-CS2113-T13-4/tp/pulls?q=is%3Apr+reviewed-by%3Airving11119+)


