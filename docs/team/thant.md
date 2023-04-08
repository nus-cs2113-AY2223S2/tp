# Thant Aung Htet Nyan - Project Portfolio Page

## Overview

Duck is a desktop task and class tracker for users to keep track of their schedule and the things they have to do. The user interacts with it using the CLI. It is written in Java, and has about 3.5kLoc.



## Summary of Contributions

### New Feature: Implementation of Date-Time tracking across Duck
- Elaboration: Duck is an improved version of the Duke bot, building upon the pre-existing functionality of Duke with enhanced features like date/time tracking, which was added by me. This date/time tracking is crucial in a number of new Duck features.


### New Feature: Implementation of the ```priority``` variables
- Adds a new priority variable to the Task class that ranges from 1-3, being LOW MEDIUM HIGH respectively.
- This is utilized in the ```priority_list``` new feature as well as the ```high_priority```,```medium_priority```,```low_priority```commands.


### New Feature: Implementation of the ```purge``` feature
- Checks across the ```TaskList``` array to identify all ```Deadline``` and ```Event``` objects.
- Checks for if the ```by``` variable for ```Deadline```objects or the ```end```variable for ```Event``` objects have passed the localtime of the local machine where Duck is running on.
- Displays all expired tasks, as well as the total count of expired tasks.
- Prompts user for a ```Y/N``` to confirm the removal of expired tasks.
- This feature helps keep the list of tasks clean, and is automatically run once on Duck's startup.


### New Feature: Implementation of the ```clear``` feature
- Wipes the ```TaskList``` array clean and wipes the local ```savedata``` file clean as well.
- User is prompted for a ```Y/N``` confirmation as this process is irreversible.
- Justification: During debugging / demonstrations, I continually manually deleted and recreated the ```savedata``` file to reset Duck to a blank slate. Hence, I decided to just make it an easily accessible feature for Duck users and it became highly useful.


### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=thant&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


### Project Management:
- Initiated meetings / kept group on track to meet specific deadlines.
- Specified and broke down which issues should be done by v1.0, v2.0 and so forth to breakdown work timelines.


### Documentation:
- User Guide:
  - Did up most of the initial User Guide.
  - Updated respective features as new features were added / input methods were updated.
  - Added hyperlinks to the feature lists.
  
- Developer Guide:
  - Did the ```architecture``` section.
  - Did the design and implementation for ```purge``` and ```clear``` features
  - Added the sequence diagram for the ```purge``` function
  - Added an editable class diagram powerpoint skeleton located at ```docs/tP Diagram Editable.pptx```


### Community:
- Reported bugs and suggestions for other teams (examples: [1](https://github.com/thant/ped/issues/5), [2](https://github.com/thant/ped/issues/6), [3](https://github.com/thant/ped/issues/8))
