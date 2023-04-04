# Cleon - Project Portfolio Page

## Overview

Our AY22/23 Sem 2 CS2113 (Software Engineering) Team Project
A CLI based fitness tracker intended to give users a way to manage and learn new workouts. It hopes to encourage
individuals to be motivated and keep fit so that they can live a healthy lifestyle.

## Summary of Contributions

Here are the contributions done by myself to FitnessDuke

## Features Contributed:

### Feature: ```Generate FILTER1 FILTER2 NUMBER```
* What it does: Using the generate feature with the addition of filters would allow the user to generate a random
  set of exercises according to the filters and number of exercises provided.
* Justification: This feature to be able to filter the exercises is a crucial part of fitness duke as it helps
  users shortlist a set of their desired workout according to the filters available.
* Highlights: This feature starts with an array of all possible exercises, then passes the same array through a
  function to filter the array based on the first filter. If there are multiple filters, the same filtered array
  will be passed into the next filter and repeats the cycle, eventually ending up with the final filtered array.


### Feature: ```planner```
* What it does: Allows users to add and remove workout plans (plans that have a pre-set list of filter/s) to a
  weekly schedule.
* Justification: This feature is a crucial part of fitness duke as it helps users efficiently and quickly start
  their desired workout without having to type in a long `generate` command each time. This feature is also crucial
  to the efficiency as users can easily create a weekly plan to follow, not having to waste time thinking of
  exercises for each day of the week.
* Highlights: This feature starts with an empty weekly calendar, and upon addition of plans, each plan name will
  appear on the workout plan on the day specified, together with the filters tied with the plan name.

### Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=khulon&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements to existing features
* Wrote JUnit tests for our planner feature, which tests the addition, deletion and displaying of workout plans.
* Fixed certain bugs and standardized the way code was written to match the same coding conventions.
* Integrated all the different filters into a main filter method.

### Contributions to the DG
* Wrote the plantUML file for the Planner Command Handler and Add Plans.
* Added more elaborations to the explanations of features.

### Contributions to the UG
* Contributed to the Planner portion of the UG
* Helped to elaborate more on explanations of commands.
* Contributed examples to some usage of commands.

### Contributions to the team tasks
* Helped add issues and tagged them throughout the project.