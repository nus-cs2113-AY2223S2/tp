# Jeremiah Ong - Project Portfolio Page

## Overview
Duck is a desktop task and class tracker for users to keep track of their schedule and the things they have to do. The user interacts with it using the CLI. It is written in Java, and has about 3.5kLoc.


## Summary of Contributions

### New Feature: Implementation of ```print_priority``` feature

- Added functions to print all tasks arranged by priority, rather than by the order in which they were added
- Also has other smaller functions as specified by the user guide to only print tasks of a certain priority

### New Feature: Implementation of ```notes``` feature

- This feature allows users to add additional notes to each task that they will want to take note of
- Notes will also be saved to be viewed again on next start up of Duck

### New Feature: New tasks automatically assigned low priority

- Automatically assigns new tasks low priority until they are assigned a higher priority by the user


### New Feature: Adding notes to tasks

- Allows the user to add a note to the specified task
- By following the steps outlined by the user guide, a note can be added to a task. 
The note will be saved when Duck is closed

### New Feature: Deleting notes under tasks

- his feature allows the user to delete notes that have been created for whatever reason  
- By following the steps outlined by the user guide, existing notes can be deleted under their respective tasks.

### New Feature: Editing notes under tasks

- This feature allows the user to change an existing note under a specified task  
- By following the steps outlined by the user guide, existing notes can be edited.

### New Feature: Printing Duck

- Prints ASCII art of a duck to suit the theme of the product
- Acts as the avatar for the application

### New Feature: Implementation of ```motivation``` feature

- Prints a motivational quote for the user if they want some motivation
- Also prints on startup when greeting the user

### Cleaning up of code

- Helped to make the code more defensive to prevent bad inputs from crashing Duck, specifically indexes that accessed out of bounds

### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=miahjerry&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Project Management:

- Helped to host group meetings and organize them to ensure group was on task and on the same page
- Liased with other groups to promote mutual stress-testing of each other's product 

### Documentation:
- User Guide:
    - Added documentation for the features ```print_priority```, ```low_priority```,```medium_priority```, ```high_priority```, ```add_notes```, ```edit_notes```, ```delete_notes```,and ```motivation```

### Community:
- Reported bugs and suggestions for other teams (examples: [1](https://github.com/miahjerry/ped/issues/4), [2](https://github.com/miahjerry/ped/issues/7), [3](https://github.com/miahjerry/ped/issues/8))
