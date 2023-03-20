# User Guide

## Introduction

Fitness Tracker is for users who are active and trying to keep fit or hit a fitness goal. This app will be 
displayed and used in CLI format. It is targeted toward those who prefer to use CLI over GUI.
The product will be able to keep track of the user's progress, daily exercises, and workouts. This helps with 
planning future exercises.

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Start a Workout: /start](#start-a-workout--start)
  - [Add exercise: /add](#add-exercise--add)
  - [End current workout: /end](#end-current-workout--end)
  - [Display workout list: /list](#display-workout-list--list)
  - [Delete a workout: /delete](#delete-a-workout--delete)
  - [Exit app: /exit](#exit-app--exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Fitness Tracker` from [here](https://github.com/AY2223S2-CS2113-T14-1).

## Features

### Start a Workout: `/start`
Starts a new workout on a specific date.

Format: `/start <DD/MM/YY>`

* The date, `DD/MM/YY`, should be entered in the correct format 

Example of usage: 

`/start 21/03/23`

### Add exercise: `/add`
Adds an exercise to the current workout. This records the weight used and the number of reps per set

Format: `/add <EXERCISE_NAME> /weight <WEIGHT_USED> /rps <REPS_PER_SET>`

Example of usage:

`/add Bench Press /weight 100kg /rps 5 5 4 3 3`

### End current Workout: `/end`
Ends the current workout

Format: `/end`

Example of usage:

`/start 21/03/23`

### Display workout list: `/list`
List out all the dates of completed workouts.

Format: `/list`

Example of usage:

`/list`

### Delete a workout: `/delete`
Delete a new workout completed on a specific date.

Format: `/delete <DD/MM/YY>`

* The date, `DD/MM/YY`, should be entered in the correct format

Example of usage:

`/delete 21/03/23`

### Exit app: `/exit`
List out all the dates of completed workouts.

Format: `/exit`

Example of usage:

`/exit`

## FAQ

**Q**: Does the app save my workouts when I exit it?

**A**: As of v1.0, the app does not save your data. However, you can look forward to this feature in the next update!

## Command Summary

* Start a workout `/start <DD/MM/YY>`
* Add exercise `/add <EXERCISE_NAME> /weight <WEIGHT_USED> /rps <REPS_PER_SET>`
* End current Workout `/end`
* Display workout list `/list`
* Delete a workout `/delete <DD/MM/YY>`
* Exit app `/exit`
* 