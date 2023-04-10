# PoobalanAatmikaLakshmi - Project Portfolio Page

## Overview
Apollo is a scheduling app for managing your tasks and lessons at the same time,
with integrated module information from the NUSMods database.
The target user, an average NUS student, interacts with Apollo using a CLI.
It is written in Java, and has about 5kLoC.

### Summary of Contributions
-[My code contributions](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=PoobalanAatmikaLakshmi&tabRepo=AY2223S2-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Enhancements Implemented
- Created classes for specific help for each of the commands in the task commands section of user guide 
- Modified parser to add specific help commands 
- Set up logger for help command class 
- Updated error message for AddCommand class particularly for the event-lesson clash
- Added functionality to handle duplicates in storage class
- Implemented code to detect events clashing with other events 
- Added code to detect duplicate modules in moduleList and its exception 
- Added some JUnit tests for Parser class 
- Added Junit tests for task command portion of help command class
- Changed datetime format from yyyy-MM-ddThh:mm to dd-MM-yyyy-hh:mm to increase user-friendliness and updated all relevant files(including Exception,Ui,Test files)
- Patches 

#### Contributions to the UG
- Updated user guide with information about clashes between event/deadline/modules 
- Updated user guide on listmod list module with lessons command 
- Updated user guide on change in datetime format from yyyy-MM-ddThh:mm to dd-MM-yyyy-hh:mm  

#### Contributions to the DG
- Delete Module 
- Sequence diagrams for DateCommand, FindCommand,DeleteHelpCommand
- Class diagram for Storage
- Appendix E: Adding a ToDo/Event/Deadline,Deleting a Todo/Event/Deadline,Adding a Module,Adding a Lesson,Deleting a Module,Deleting a Lesson


#### Contributions to team-based tasks
- Helped maintain issue tracker 
- Helped write script for demo video 
- Released v2.1 jar 
- [PRs Reviewed](https://github.com/AY2223S2-CS2113-T13-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Apoobalanaatmikalakshmi+)
