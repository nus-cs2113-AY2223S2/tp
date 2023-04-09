# Hamada Masahiro - Project Portfolio Page

## Overview
Our project, LifeTracker, is a calorie tracker CLI program. It allows the user to record down their daily net calorie gain, where the user can input the meals
he has consumed as well as the exercises that he has done.

In doing so, the user can better manage his daily calorie gain in order to meet their particular needs.

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed

[RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=Masahiro21&tabRepo=AY2223S2-CS2113-W15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Commands
Our project implements a variety of command which allows the user to input and keep track of their information 
via the CLI. They form the backbone of our project, and it is the key to it functioning smoothly. Below are the list of
commands I have worked on.

`view` command: Allows the user to view their information.
* Implemented the code to allow users to view their information

`update` command: Allows the user to update their information stored.
* Added methods to allow update command to handle updates to name, age and gender 

`help` command: Allows the user to view a help menu that gives them a quick guide on how to use the program.
* Abstracted print statements into UI

### Entity
`user` entity: The entity that initialises all the user's information.
* Expanded the information that the entity holds to include age and gender
* Implemented getter and setter methods for newly added information 
* Modified calculateCaloricNeeds method to factor in age and gender

### Contribution to team-based tasks
- Created and implemented the `view` command

### Enhancement to existing features
* Modified `view` command to be able to take in one-liner inputs from the user to view their information
  * Shortens the process of user viewing their information

* Modified `update` command to be able to take in one-liner inputs from the user to view their information
  * Shortens the process of user updating their information

## Project Management
* Managed release `v2.0` on GitHub

## UG/DG
* Added the introduction to the UG as well as a framework of explanation for the other members to build off
* Added Documentation for the `view` command and `update` command
* Added explanation of program and user stories

