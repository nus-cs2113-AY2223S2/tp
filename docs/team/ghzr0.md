# Glenn - Project Portfolio Page

## Overview

Our AY22/23 Sem 2 CS2113 (Software Engineering) Team Project
A CLI based fitness tracker intended to give users a way to manage and learn new workouts. It hopes to encourage
individuals to be motivated and keep fit so that they can live a healthy lifestyle.


## Summary of Contributions

Here are the contributions done by myself to FitnessDuke
Code contributed by myself on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ghzr0&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ghzr0&tabRepo=AY2223S2-CS2113-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Features Contributed:

### Feature: ```find```
* What it does: This feature would allow the user to find exercises based on a specified keyword from our mini json database
  of exercises. The names of the exercises will then be printed out to the user.
* Justification: This would help users to be able to conveniently search for the full names of the exercises, while also
  exploring other possible types of exercises that may interest them in trying.
* Highlights: This feature filters and loads data containing the specified keyword from the resources folder, before
  returning an ArrayList of the filtered data output to the user.

### Feature: ```IPPT calculator: ippt [AGE] [RUNTIME] [PUSHUP SCORE] [SITUP SCORE]```
* What it does: Provides the user as a way to track his Individual Physical Proficiency Test(IPPT) progress,
and being able to calculate the total points he obtains from the session.
* Justification:By imbuing as a new exercise session, this reduces the hassle for the user to spend the extra time to search his IPPT 
progress via another platform/app, and he can now use one single program to do the exercise and know the number of points obtained from his progress.
* Highlights: This feature calculates the total points for the user based on the inputs of the user's scores and outputs to the user.
Furthermore, it adds to the history of the user's exercise sessions and the user can see the breakdown of the points in the history.
The scoring system is based on the data in the scores.json file, which determines the number of points awarded based on the age, reps and timing of the user.

### Enhancements to existing features
* Fixed bugs and issues related to the feature, where some keywords inputted by user will not reflect the relevant exercises being outputted.
* Included exceptions and considerations for possible invalid user's inputs when starting a new IPPT session.
### Contributions to the DG
* Contributed to the elaboration of some features

### Contributions to the UG
* Contributed to the ```find``` and ```ippt``` command portions of the UG
* Included more detailed elaboration of commands and examples

### Contributions to the team tasks
* Helped add issues and tagged them throughout the project.
* Helped to merge and review peers' pull requests 