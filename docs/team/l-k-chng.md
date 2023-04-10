---
layout: page
title: Lip Kuang's Project Portfolio Page
---

# Lip Kuang - Project Portfolio Page

## Overview

Our AY22/23 Sem 2 CS2113 (Software Engineering) Team Project
A CLI based fitness tracker intended to give users a way to manage and learn new workouts. It hopes to encourage
individuals to be motivated and keep fit so that they can live a healthy lifestyle.

### Summary of Contributions

Here are the contributions done by myself to FitnessDuke

## Features Contributed: 

### Feature: ```upper```, ```core```, ```legs``` filters
* What it does: This feature allows users to generate a list of exercises according to workout type. The three different
workout types are ```upper```, ```core``` and ```legs```, which correspond to upper body, core and legs exercises. 
* A sample generation of workout exercises consisting of leg exercises is: ```generate legs 3``` which would generate 3 leg exercises for the user.
* Justification: This feature is a key aspect of fitness duke as it allows users to tailor a workout session which targets a specific part of the body.
* Highlights: This feature starts with an array of all possible exercises, then passes the same array through a
  function to filter the array based on workout type. It then returns the array filtered by workout type. 

### Feature: ```data``` 
* What it does: This feature shows users additional details regarding the exercises they have completed. It allows users
to view their completed exercises, along with the number of times that they have completed each exercise. Additionally,
it shows them the total of unique and non-unique exercises that they have completed.
* Justification: This feature is important for fitness duke as by being able to view completed exercises along with 
their frequencies of completion, users can keep track of their overall fitness progress. By being able to view
the total number of exercises they have completed, it would also serve to motivate users to work harder to complete more 
exercises.
* Highlights: This feature takes in the arraylist of all user career sessions and taps into each individual exercise for
each user career session. The exercises are then passed into a HashMap, with the exercise description as the key and 
frequencies of completing each exercise as the value, before it is printed as an output.

### Feature: ```delete``` 
* What it does: This feature allows users to delete a completed workout session according to its workout session number.
* Justification: This feature is key to fitness duke as it allows users to delete the workout sessions which they feel are outdated/irrelevant.
* Highlights: This feature takes in the arraylist of all user career sessions and deletes the session according to the session number of the user career session which the user specifies. 

### Feature: ```search```
* What it does: This feature allows users to search for exercises they have completed according to the keyword they have
input.
* An example of the search command would be ```search arm```, and any exercise the user has completed that contains the 
keyword "arm" would be displayed to the user. Details of the exercise such as exercise ID, workout type and description 
will be visible to the user.
* Justification: This feature is important as it allows users to view their completed exercises easily through the input
of a keyword, rather than having to scroll through the entire list of exercises that they have completed.
* Highlights: This feature filters and loads data containing the specified keyword from the user career data, which 
contains the user's completed workout sessions, before returning an ArrayList of the filtered data output to the user.

### Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=L-K-Chng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=false&zFR=false)

### Enhancements to existing features
* Wrote JUnit tests for Ui features, which tests the correct printing of output to the user.
* Wrote Junit tests for the workout type filter for the ```generate``` command.
* Added exceptions to handle error for single word user commands.
* Improved Ui, eg. updating help messages for users to its latest state to improve user experience.
* Added assertions to ensure correctness of code.

### Contributions to the DG
* Wrote the plantUML file for the Ui class.
* Contributed to CommandList plantUML diagram and explanation.
* Added more elaborations to the explanations of features.

### Contributions to the UG
* Contributed to the Planner portion of the UG.
* Contributed to the Workout portion of the UG.
* Contributed to the explanation and examples of the usage of certain commands.

### Contributions to the team tasks
* Helped review PRs and made comments to them throughout the project
* Helped add issues and tagged them throughout the project.
