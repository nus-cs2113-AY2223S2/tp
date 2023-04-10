# LIANG TING YU - Project Portfolio Page


## Overview

Duck is a desktop task and class tracker for users to keep track of their schedule and the things they have to do. The user interacts with it using the CLI. It is written in Java, and has about 3.5kLoc.



## Summary of Contributions

### New Feature: Implementation of displaying upcoming deadline/event when starting duck.
- Displays upcoming deadline stored in the application when starting the application.
- Calculate the remaining time before the deadline


### New Feature: Implementation of displaying next upcoming deadline/event/class.
- Displays the next upcoming class stored in the application. Classes will be automatically sorted according to chronological order. Classes will also automatically be marked as done (represented by a cross) if the current time is past the ending time of the class, and their 'done' status will be reset at the start of each week.
- This is utilized in the ```upcoming_deadline```,```upcoming_class```,```upcoming_event```commands.


### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=liang&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


### Project Management:
- Group meetings/notes.
- Complete assigned task and double check typo/bugs of others.


### Documentation:
- User Guide:
    - Added documentation for the features `upcoming_deadline` , `upcoming_event`, `upcoming_class` , and feature displaying upcoming deadline/event.
    - fix inconsistent format.
    - Added missing links to the table of contents.


### Community:
- Reported bugs and suggestions for other teams (examples: [1](https://github.com/MichelleLiang0116/ped/issues/3), [2](https://github.com/MichelleLiang0116/ped/issues/2), [3](https://github.com/MichelleLiang0116/ped/issues/1))
