# Marek Pinto - Project Portfolio Page

## Overview

Pet Tracker is a Command Line Application to help keep track of multiple pets.
This project is intended to be used by people who own multiple pets like pet sitter or pet hotels.
This project tracks the Pets and also tasks to reminder users about tasks they need to do like feeding their pet,
with a reminder system in place for overdue tasks.

### Summary of Contributions

## Code Contributed [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=marekpinto&tabRepo=AY2223S2-CS2113-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## New Feature: Edit Tasks [#72](https://github.com/AY2223S2-CS2113-T11-3/tp/pull/72)
- What it does: Allows the user to edit task description or dates after they have already been added
- How it works: The user can edit tasks by date or description
- Justification: If the user feels the date or description is inaccurate, they can update it to maintain an accurate and current schedule

## New Feature: Schedule [#80](https://github.com/AY2223S2-CS2113-T11-3/tp/pull/80)
- What it does: Displays all tasks that have a deadline in the order of their deadline
- How it works: It sorts the tasks by deadline then iterates through them
- Justification: Users are often most-concerned with their most pertinent tasks, so this easily formats the task in order of how pressing they are

## Enhancements Implemented
- Wrote Ui code to handle all input from the user and print output to command line [#28](https://github.com/AY2223S2-CS2113-T11-3/tp/pull/28)
- Created methods for Petlist, such as find() and get() [#35](https://github.com/AY2223S2-CS2113-T11-3/tp/pull/35)

## UG Contribution [#80](https://github.com/AY2223S2-CS2113-T11-3/tp/pull/80)
- Wrote UG docs for schedule command
- Wrote UG docs for edit-task command

## DG Contributions [#69](https://github.com/AY2223S2-CS2113-T11-3/tp/pull/69)
- Wrote DG docs with sequence diagram for remove-stat command

## Community

Found bugs during PE-D for Team MovieMate (W12-4)
- [#108](https://github.com/AY2223S2-CS2113-W12-4/tp/issues/108)
- [#125](https://github.com/AY2223S2-CS2113-W12-4/tp/issues/125)
- [#126](https://github.com/AY2223S2-CS2113-W12-4/tp/issues/126)
- [#128](https://github.com/AY2223S2-CS2113-W12-4/tp/issues/128)
- [#115](https://github.com/AY2223S2-CS2113-W12-4/tp/issues/115)

### UG Extract

### Print Task Schedule: `schedule`
Views the current list of tasks with an associated deadline, in order of deadline.

Format: `schedule`

Example of usage:

`schedule`

Expected Output:

```
Here is your schedule:
1. [ ] Buy food (Deadline: 2021-03-01)
2. [ ] Feed the dog (Deadline: 2021-04-02)
```

### DG Extracts

[RemoveStatSeqDiagram](../diagrams/RemoveStatCommand.puml)