# User Guide 
#  ❚·══·❚ Fitz ❚·══·❚



Fitz is a fitness tracker for users who are active and trying to keep fit or hit a fitness goal. This app will be 
displayed and used in CLI format. It is targeted toward those who prefer to use CLI over GUI.
The product will be able to keep track of the user's progress, daily exercises, and workouts. This helps with 
planning future exercises.

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [The List of commands : /help](#help)
  - [Start a Workout: /start](#start-a-workout--start)
  - [Add exercise: /wadd](#add-exercise--add)
  - [Add calories: /cadd](#adding-calories--cadd)
  - [End current workout: /end](#end-current-workout--end)
  - [Display workout list: /list](#display-workout-list--list)
  - [Display a workout: /view](#viewing-a-specific-workout--wview)
  - [Display calories consumed on a specific date : /cview](#viewing-the-total-calories-on-a-specific-date--cview)
  - [Delete a workout: /delete](#delete-a-workout--delete)
  - [Count Sets and reps over a week: /count](#count-sets-and-reps--count)
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
### Adding to the workout: `/wadd`
Adds to the list of workouts.

Format: `/wadd <exercise_name> /weight <weight_used> /rps <reps_per_set>`

* The `/start` needs to be inputted first to use the `/add` command.

Example of usage:

`/wadd bench press /weight 100 /rps 5 5 5 5`

`/wadd leg press /weight 160 /rps 5 5 5 5`

Expected output:
```
Added bench press 100 5 5 5 5
```
### Adding to the workout: `/wadd`
Adds to the list of workouts.

Format: `/wadd <exercise_name> /weight <weight_used> /rps <reps_per_set>`

* The `/start` needs to be inputted first to use the `/wadd` command.

Example of usage:

`/wadd bench press /weight 100kg /rps 5 5 5 5`

`/wadd leg press /weight 160kg /rps 5 5 5 5`

Expected output:
```
Added bench press 100kg 5 5 5 5
```
### Adding Calories: `/cadd`
Adds to the list of workouts.

Format: `/cadd <DD/MM/YY> <FOOD_NAME> <CALORIES>`

* The `/start` needs to be inputted first to use the `/cadd` command.

Example of usage:

`/cadd 11/02/23 chicken 100`

`/cadd 11/02/23 fish 100`

Expected output:
```
Consumed additional 100kcal.
Total calories consumed: 100kcal
```
### Listing workout dates: `/list`
Display the current workout dates

Format: `/list`

Example of usage:

`/list`

```
Here are the list of dates of your workouts:
1. 11/02/23
```
### Viewing a specific workout: `/wview`
Display the specified workout list

Format: `/wview <DD/MM/YY>`

Example of usage:

`/view 21/03/23`

Expected output:
```
Here are the list of exercises in your workout:
1. bench press 100kg 5 5 5 5
```
### Viewing the total calories on a specific date: `/cview`
Display the specified workout list

Format: `/cview <DD/MM/YY>`

Example of usage:

`/ciew 21/03/23`

Expected output:
```
Calories consumed on 21/02/23: 200kcal.
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
### Counting sets and reps over a week: `/count`
Displays the list of distinct exercises over a week and the associated total number of sets and reps for each one

Format: `/count <DD/MM/YY>`

Example of usage:

`/count 21/03/23`

Expected output:
```
Exercises and number of sets and reps for the week of Tue Mar 21 00:00:00 SGT 2023
----------------------------------
Bench Press - 4 sets - 48 reps
Squats - 5 sets - 60 reps
----------------------------------
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

* The list of Commands `/help`
* Start a workout `/start <DD/MM/YY>`
* Add exercise `/wadd <EXERCISE_NAME> /weight <WEIGHT_USED> /rps <REPS_PER_SET>`
* Add calories `/cadd <DD/MM/YY> <FOOD_NAME> <CALORIES>`
* End current Workout `/end`
* Display workout list `/list`
* Display specific workout list `/wview <DD/MM/YY>`
* Display specific workout list `/cview <DD/MM/YY>`
* Display the amount of reps and set on a specific exercise `/count <DD/MM/YY>`
* Delete a workout `/delete <DD/MM/YY>`
* Exit app `/exit`
