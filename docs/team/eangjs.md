---
layout: page
title: Eugene's Project Portfolio Page
---

# Eugene - Project Portfolio Page

## Overview

Our AY22/23 Sem 2 CS2113 (Software Engineering) Team Project
A CLI based fitness tracker intended to give users a way to manage and learn new workouts. It hopes to encourage 
individuals to be motivated and keep fit so that they can live a healthy lifestyle.

## Summary of Contributions

Here are the contributions done by myself to FitnessDuke
Code Contributed by me on [Reposense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=EangJS&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=EangJS&tabRepo=AY2223S2-CS2113-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Features Contributed: 

### Feature: ```Generate``` 
* What it does: This feature would allow the user to generate a random set of exercises from our mini json database
of exercises. The exercise description and miscellaneous details will then be printed out to the user. 
* Justification: This feature is one of the building blocks of fitness duke where we would be able to help users 
  shortlist a set of their desired workouts to be carried out. 
* Highlights: This feature loads data from the resources folder into a class that contains all exercises within the 
  database to a class that consists of an ArrayList of exercise class

### Feature: Saving and loading of all data to and from Storage with Json (Excluding Achievements storage)
* What it does: This feature allows the user to save their data from their session into the hard disk as a json file 
  which can be later restored in the upcoming session such that their progress will be kept across multiple sessions.
* This feature is a key aspect of our project as we aim to help users progressively improve their fitness over a 
  period of time. Hence, by being able to save important user data such as workout history and weekly plans, the 
  user can reflect and review their progress.
* Highlights: This feature loads previous data from the json file (if any) and appends the current session to the 
  file. Key checks are made to ensure integrity of the file such that Null Pointer Exceptions are avoided.
* Abstracted the Storage package into an API over multiple iterations to heavily reduce coupling.
* Ensure that the Storage API is defensive in a sense that user manipulation of data files does not cause crashes in 
  other features of the program.

### Enhancements to existing features
* Wrote JUnit tests for our Storage.java API ```UserDataStorageTest.java```(excluding achievements)
Tests the saving and reading of data to and from the local hard disk and checks the handling of missing files.
* Wrote JUnit tests for generating exercises from resources file ```GenerateExerciseTest.java```.
* Fixed certain bugs causing the program to crash or inefficient methods. Such as removing random next index in 
  while loop to generate a random exercise and bugs causing null pointer exceptions.

### Contributions to the DG
* Wrote the plantUML file for the Storage API sequence and class diagrams
* Wrote the section on the ```Storage.java API``` in the DG that explains the key aspects on how our storage 
  mechanism works.
* Add descriptions to the value proposition of our project.
* Add some user stories.

### Contributions to the UG
* Contributed to the Getting Started portion of the UG
* Helped to develop the skeleton of the UG
* Contributed to the explanations of the usage of certain commands.

### Contributions to the team tasks
* Helped review PRs and made comments to them throughout the project
* Helped add issues and tagged them throughout the project.
