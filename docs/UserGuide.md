# User Guide 
#  ❚·══·❚ Fitz ❚·══·❚



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
  - [Display a workout: /view](#display-workout-view--list)
  - [Delete a workout: /delete](#delete-a-workout--delete)
  - [Exit app: /exit](#exit-app--exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction 
This application will help you track your current and future workouts.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Fitness Tracker` from [here](https://github.com/AY2223S2-CS2113-T14-1).



## Features

### Starting a workout: `/start`
Starts the workout for a specific date

Format: `/start <DD/MM/YY>`

* The `DATE` needs to be in the exact format.

Example of usage: 

`/start 11/03/23`

`/start 21/04/23`

Expected output:
```
Started new workout.
Use add command to add exercises to your workout!
```
### Adding to the workout: `/add`
Adds to the list of workouts.

Format: `/add <exercise_name> /weight <weight_used> /rps <reps_per_set>`

* The `/start` needs to be inputted first to use the `/add` command.

Example of usage:

`/add bench press /weight 100 /rps 5 5 5 5`

`/add leg press /weight 160 /rps 5 5 5 5`

Expected output:
```
Added bench press 100 5 5 5 5
```
### Listing workout dates: `/list`
Display the current workout dates

Format: `/list`

Example of usage:

`/list`

```
Here are the list of dates for your workout: 
Tue Mar 21 00:00:00 SGT 2023
```
### Viewing a specific workout: `/view`
Display the specified workout list

Format: `/view <DD/MM/YY>`

Example of usage:

`/view 21/03/23`

Expected output:
```
[bench press 100 5 5 5 5, bench press 100 5 5 5 5]
```
### Deleting a specific workout: `/delete`
Delete the specified workout

Format: `/delete <DD/MM/YY>`

Example of usage:

`/delete 21/03/23`

Expected output:
```
Workout deleted successfully.
```
### Exit the App: `/exit`
Delete the specified workout

Format: `/exit`

Example of usage:

`/exit`

Expected output:
```
Thank you and see you next time
```

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
