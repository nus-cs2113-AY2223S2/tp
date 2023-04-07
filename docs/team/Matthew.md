# Matthew Liu - Project Portfolio Page

## Overview
**NUSplanner** is a tool for NUS students to plan events such as modules and project meetings. It is optimized for use through a Command Line Interface (CLI). 

### Summary of Contributions
[Reposense: Code Contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=f13-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=matthew-liu-zhenjie&tabRepo=AY2223S2-CS2113-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features Implemented:
* **Feature: Storage** 
  * What it does: Added the ability to store and load information to and from the user's local hard drive.
  * Justification: This feature ensures that users are able to keep track of their events as soon as they launch the application.
  * Highlights: Implemented an Adapter to store and load custom classes to and from a JSON file using the GSON API. 
  Also ensured defensiveness of Storage component such that any potentially malicious changes to the save file will not result in 
  catastrophic failure of the application
* **Resource File Parsing**:
  * What it does: Enables users to find and input modules into their planner, even when offline, for planning purposes. This is one of the main features of the application.
  * Justification: NUSMODS api might not be able to handle high traffic. As such, a local version of the data on the API should be saved
  locally, allowing students to plan their day regardless of NUSMODS traffic. 
  * Highlights: Data was scraped from the [NUSMODS API](https://api.nusmods.com/v2/) to enable the application to run offline. As the data was not in a 
  directly-usable format, a custom Adapter was created using the GSON API, converting it into custom-classes usable for the purposes of our application. 
  The java-compatible module data is then stored in a HashMap for fast retrieval. 

### Contributions to User Guide:
* Added documentation for Storage in the UG.
* Update changes in commands and command summary
* Proofread User Guide

### Contributions to the Developer's Guide
* Add documentation for the `Storage` component.
* Add documentation for the overall architecture of project, including architecture diagram to provide readers with a bird's eye view of the architecture.
* Add User Stories to the DG.
* Add Non-functional requirements to the DG. 

### Contributions to team-based tasks:
* Create Github labels for issues as recommended on the CS2113 website.
* Use Github Projects to create a task tracker for each milestone.
* Create and assign Issues for each milestone
* Maintain GitHub issue tracker by updating labels for issues.
* Reviewed PRs from all team members