# Wan Lin - Project Portfolio Page

## Overview
Apollo is a scheduling app for managing your tasks and lessons at the same time,
with integrated module information from the NUSMods database.
The target user, an average NUS student, interacts with Apollo using a CLI.
It is written in Java, and has about 5kLoC.

### Summary of Contributions
- [Breakdown of Code Contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=wan-lin&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=T-Wan-Lin&zR=AY2223S2-CS2113-T13-4%2Ftp%5Bmaster%5D&zACS=109.1875&zS=2023-02-17&zFS=wan-lin&zU=2023-04-03&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)
### Enhancements Implemented
- Enhanced `list` command to sort tasklist by type and then date within each task subtype.
- Enhanced `todo` command to provide suggestion for users if their todo insinuates a deadline task.
- Included week information display ability for lessons to `listmod` and `showmod` commands.
- Enhanced `addmod` command to sort module list according to EduRec standards.
- Revamped Help menu.
- Created specific help commands for each module command to give a shorter summary of each command’s format and purpose.
- Display the total modular credits taken by user and display the modular credit for each module in module list.
- Wrote JUnit tests for the following classes: `Parser`,`Ui`, `ModifyCommand`, `DateCommand`, `FindCommand`, `AddModuleCommand` and `SpecificHelpCommand`.
- Patches and bug fixes for v2.0.

### Contributions to the UG
#### Added/elaborated on to the following sections:
- Todo command (to include deadline suggestion enhancement)
- Delete task command
- Unmark Task command
- Mark Task command
- List Tasks command (to include sorting capability)
- Help Command

### Contributions to the DG
#### Added/elaborated on to the following sections:
- System Architecture 
- Delete Task command 
- Unmark Task command 
- Mark Task command
- List Task command (to include sorting capability)
- Modify Command Class Diagram
- Appendix A - Product Scope
- Appendix B - User Stories
- Appendix C - Non-Functional Requirements
- Appendix D - Glossary
- Appendix E - Instructions for Manual Testing
#### Added the following PlantUML diagrams:
- System Architecture Component Diagram
- Unmark Task Command Sequence Diagram
- Unmark Task Command Activity Diagram
- Delete Task Command Sequence Diagram
- Mark Task Command Activity Diagram

### Contributions to team-based tasks
- [PRs Reviewed](https://github.com/AY2223S2-CS2113-T13-4/tp/pulls?q=is%3Apr+reviewed-by%3At-wan-lin)
- Created Issues and helped in maintaining issue tracker
  - [Issues Authored](https://github.com/AY2223S2-CS2113-T13-4/tp/issues?q=is%3Aissue+author%3A%40me+is%3Aclosed)
- Documented target user profile
  - Set up initial spreadsheet of preliminary user stories
- Assisted in release management (v1.0)
- Stress tested and documented bugs before v2.0 release on Apollo v1.0
  - [Bugs found](https://github.com/AY2223S2-CS2113-T13-4/tp/issues?q=is%3Aissue+author%3A%40me+is%3Aclosed+label%3Atype.Bug)
- Sought approval for using NUSMods API on course [forum](https://github.com/nus-cs2113-AY2223S2/forum/issues/42)
- Wrote the script for video demo
- Most instances of helping team members were done offline during our weekly meetings.
