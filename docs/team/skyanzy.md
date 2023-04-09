# Yan Zaiya - Project Portfolio Page

## Overview

Duck is a desktop task and class tracker for users to keep track of their schedule and the things they have to do. The user interacts with it using the CLI. It is written in Java, and has about 3.5kLoc.


## Summary of Contributions

### New Feature: Implementation of the `RecurringDeadline` Task
- Allows users to keep track of deadlines that happen every week by only adding the deadline once instead of every week.
- `Storage` class was modified to recognise a `RecurringDeadline`.

### New Feature: Implementation of the `RecurringEvent` Task
- Allows users to keep track of events that happen every week by only adding the event once instead of every week.
- `Storage` class was modified to recognise a `RecurringEvent`.

### New Feature: `list_today`
- Displays all deadlines and events(recurring tasks included) saved in Duck that take place on the same day as the local time.

### New Feature: Adding a `RecurringDeadline`
- By following the specified format as indicated in the User Guide, users will be able to add RecurringDeadline to their schedule which are saved to the savefile.

### New Feature: Adding a `RecurringEvent`
- By following the specified format as indicated in the User Guide, users will be able to add RecurringEvent to their schedule which are saved to the savefile.

### New Feature: `list X`
- Lists all the non-recurring deadlines and events that would happen within X+1 days. 
- Justification: this allows users to plan their schedule better by easily referring to the upcoming tasks in the future.

### New Feature: `edit <task_number>`
- Allows uers to edit a specific piece of information regarding a stored task and then updates the savefile.
- Justification: this is done so that the if the users want to modify/ save wrong information about a certain task, they do not have to delete the task and add a new one back in. 

### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=T11&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=skyanzy&tabRepo=AY2223S2-CS2113-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management:
- Updating the team when a new PR is merged to ensure everyone is on the same page.
- Integration of different parts when a new feature is implemented.

### Documentation:
- User Guide:
  - Added documentation for the features `edit <task_number>` , `list_today`, adding a `RecurringDeadline and RecurringEvnet`.
  - cleared some issues regarding inconsistent format.
  - Added some missing links to the table of contents.
  
- Developer Guide:
  - Added design and the implementation of RecurringDeadline and RecurringEvent
  - Added a class diagram to illustrate the implementation

### Community:
- Reported bugs and suggestions for other teams(e.g. [1](https://github.com/skyanzy/ped/issues/5), [2](https://github.com/skyanzy/ped/issues/2), [3](https://github.com/skyanzy/ped/issues/1))
