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
  - [Start a workout: /start](#start-a-workout--start)
  - [Add exercises to current workout: /wadd](#add-exercises-to-current-workout--wadd)
  - [End current workout: /end](#end-current-workout--end)
  - [List workout dates: /list](#list-workout-dates--list)
  - [View a workout: /wview](#view-a-workout--wview)
  - [Delete a workout: /delete](#delete-a-workout--delete)
  - [Count sets and reps over a week: /count](#count-sets-and-reps-over-a-week--count)
  - [Add calories consumed: /cadd](#add-calories-consumed--cadd)
  - [View calorie consumption: /cview](#view-calorie-consumption--cview)
  - [List valid commands : /help](#)
  - [Exit the app: /exit](#exit-the-app--exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction 
This application will help you track your current and future workouts.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Fitz` from [here](https://github.com/AY2223S2-CS2113-T14-1).


## Features

### Start a workout: `/start`
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

### Add exercises to current workout: `/wadd`
Adds exercise to the current workout.

Format: `/wadd <exercise_name> /weight <weight_used> /rps <reps_per_set>`

* `/wadd` only works after a workout is started with `/start`.

Example of usage:

`/wadd bench press /weight 100 /rps 5 5 5 5`

`/wadd leg press /weight 160 /rps 5 5 5 5`

Expected output:
```
Added bench press 100 5 5 5 5
```

### End current workout: `/end`
End the current workout.

Format: `/end`

Example of usage:
`\end`
Expected output:
```
Great job completing your workout!
```

### List workout dates: `/list`
Display the list of dates of workouts done.

Format: `/list`

Example of usage:

`/list`

Expected output:
```
Here are the list of dates of your workouts: 
1. 25/03/23
2. 26/03/23
```
### View a workout: `/wview`
Display the list of exercises done for a workout on a specified date.

Format: `/wview <DD/MM/YY>`

Example of usage:

`/wview 21/03/23`

Expected output:
```
Here are the list of exercises in your workout:
1. bench press 100kg 9 8 7 6 5
2. squats 120kg 8 6 5 4
```
### Delete a workout: `/delete`
Delete a workout on a specified date.

Format: `/delete <DD/MM/YY>`

Example of usage:

`/delete 21/03/23`

Expected output:
```
Workout deleted.
```
### Count sets and reps over a week: `/count`
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
### Add calories consumed: `/cadd`
Add record of calories consumed.

Format: `/cadd <DD/MM/YY> <FOOD_NAME> <CALORIE_COUNT>`

* `<CALORIE_COUNT>` can be omitted if food has not been added previously.

Example of usage:
`/cadd 25/03/23 chicken 100`
`/cadd 25/03/23 chicken`

Expected output:
```
Consumed additional 100kcal.
Total calories consumed: 100kcal
Consumed additional 100kcal.
Total calories consumed: 200kcal
```
### View calorie consumption: `/cview`
View the total calorie consumption in a specified date.

Format: `/view <DD/MM/YY>`

Example of usage:
`/cview`

Expected output:
```
Calories consumed on 25/03/23: 200kcal.
```
### List valid commands: `/help`
List out all possible commands the user can input.

Format: `/help`

Example of usage:

`/help`

Expected output:
```
Here are the list of commands that you can use:
=======================================
- [Start a Workout: /start])
- [Add exercise: /wadd]
- [Add calories: /cadd]
- [End current workout: /end]
- [Display workout list: /list]
- [Display a workout on a specific date : /wview]
- [Display calories consumed on a specific date : /cview]
- [Display the amount of reps and set on a specific exercise /count]
- [Delete a workout: /delete]
- [Exit app: /exit]
=======================================
```
### Exit the App: `/exit`
Exit the program.

Format: `/exit`

Example of usage:

`/exit`

Expected output:
```
Thank you, hope you had a great workout!!!
```

## FAQ

**Q**: Does the app save my workouts when I exit it?

**A**: As of v2.0, the app does not save your data. However, you can look forward to this feature in the next update!

## Command Summary

* The list of Commands `/help`
* Start a workout `/start <DD/MM/YY>`
* Add exercise `/wadd <EXERCISE_NAME> /weight <WEIGHT_USED> /rps <REPS_PER_SET>`
* End current Workout `/end`
* List workout dates: `/list`
* View a workout: `/wview <DD/MM/YY>`
* Delete a workout `/delete <DD/MM/YY>`
* Count sets and reps over a week: `/count <DD/MM/YY>`
* Add calories consumed: `/cadd <DD/MM/YY> <FOOD_NAME> <CALORIE_COUNT>`
* View calorie consumption: `/cview <DD/MM/YY>`
* Exit app `/exit`
