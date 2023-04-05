# Jerald Gau - Project Portfolio Page

## Overview

Duck is a desktop task and class tracker for users to keep track of their schedule and the things they have to do. The user interacts with it using the CLI. It is written in Java, and has about 3.5kLoc.

<br />

## Summary of Contributions

### Base Code: Contributed code from iP Duke bot to serve as a base to work upon.
- Justification: Duck is an improved version of the Duke bot, building upon the pre-existing functionality of Duke with enhanced features like date/time tracking, as well as a new school class scheduler. As such, it made sense to reuse code from the iP and add in features from there.

<br />

### New Feature: Implementation of the ```SchoolClass``` Task
- Added a new Task class called SchoolClass, which are added to a separate list from the other tasks to facilitate schedule tracking.
- Makes use of the jave.time package for registering valid start/end timings, as well as java.time.DayOfWeek to register valid days in a week. This facilitates the time tracking feature.
- Revamped how saving and loading from savefile works from the iP base code, in order to account for the new SchoolClass Task. The saving and loading of task priorities was also tweaked in order for it to work with adding SchoolClasses to the savefile.

<br />

### New Feature: Class schedule with automatic time tracking
- SchoolClasses will be added to a separate list which automatically sorts them based on their registered day of week, and starting/ending time.
- The SchoolClasses will be automatically marked as done when the current time is past their registered ending time, and their done status will be reset to not done at the start of a new week.

<br />

### New Feature: ```list_classes```
- Displays all the currently registered SchoolClasses saved in Duck, and lists them out based on their day of week, starting and ending time. 
- Shows whether the class is over for the week, with a "done" marker [X] shown on the left of each class.

<br />

### New Feature: Adding a SchoolClass
- By following the specified format as indicated in the User Guide, users will be able to add SchoolClasses to their schedule. 
- The added SchoolClasses will be saved to the savefile, which will be loaded upon starting up Duck again.

<br />

### New Feature: Removing a SchoolClass
- By Following the specified format as indicated in the User Guide, users will be able to remove unwanted SchoolClasses from their schedule.

<br />

### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=jeraldgau&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

<br />

### Project Management:
- Managed the releases ```v1.0``` - ```v2.0``` (2 releases) on GitHub

<br />

### Documentation:
- User Guide:
  - Added documentation for the features ```list_classes```, ```remove class``` and ```Adding a School Class```
  
- Developer Guide:
  - Added design and implementation details of the SchoolClass feature
  - Added the sequence diagram for the ```Adding a School Class``` function
  - Added the sequence diagram for the ```list_classes``` function
  
<br />

### Community:
- Reported bugs and suggestions for other teams (examples: [1](https://github.com/jeraldgau/ped/issues/9), [2](https://github.com/jeraldgau/ped/issues/7), [3](https://github.com/jeraldgau/ped/issues/5))
