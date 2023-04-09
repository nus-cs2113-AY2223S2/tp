# User Guide 
#  ❚·══·❚ Fitz ❚·══·❚

Fitz is a fitness tracker for users who are active and trying to keep fit or hit a fitness goal. 
This app will be displayed and used in CLI format. It is targeted toward those who prefer to use CLI over GUI. 
The product will be able to keep track of the user's progress, daily exercises, and workouts. 
This helps with planning future exercises.

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
  - [Setting Up](setting-up)
  - [Viewing Help](viewing-help)
- [Features](#features)
  - [Workout Recorder](#workout-recorder)
      - [Start a workout: /wstart](#start-a-workout--wstart)
      - [Add exercises to current workout: /wadd](#add-exercises-to-current-workout--wadd)
      - [List workout dates: /wlist](#list-workout-dates--wlist)
      - [View workout details: /wview](#view-a-workout--wview)
      - [Delete workout record: /wdelete](#delete-a-workout--wdelete)
      - [Count sets and reps for a week: /wcount](#count-sets-and-reps-over-a-week--wcount)
  - [Calories Recorder](calories-recorder)
    - [Add calories consumed: /cadd](#add-calories-consumed--cadd)
    - [List all the datesof Calories consumed: /clist](#list-calories-clist)
    - [View calorie consumption: /cview](#view-calorie-consumption--cview)
    - [Delete calories record: /cdelete](#delete-a-workout--delete)
  - [List of valid workout commands : /whelp](#list-valid-commands--help)
  - [List of valid calorie commands : /chelp](#list-of-valid-commands--help)
  - [Exit the app: /exit](#exit-the-app--exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction 

Fitz - the ultimate fitness app for individuals who are passionate about their health and fitness.
With Fitz, you can achieve your fitness goals and track your progress with ease.
This innovative app is designed for users who prefer a CLI format,
making it a user-friendly option for anyone who wants to keep their fitness routine on track.
Whether you're a beginner or an experienced fitness enthusiast, Fitz is the perfect tool for you.
With its ability to track your daily exercises, workouts, and calories, you can take control of your fitness journey and achieve the results you desire.
Let's dive into the world of Fitz and discover how it can transform your fitness routine today!


## Quick Start

### Setting Up

1. Ensure that you have Java 11 or above installed. If not, kindly install Java's [latest version](https://www.oracle.com/java/technologies/downloads/)
2. Download the latest version of `Fitz` from [here](https://github.com/AY2223S2-CS2113-T14-1).
3. Copy the file to the folder that you wish to use as a home folder for Fitz. 
The data saved during the application will reside in your <home_folder>/data folder.
4. Launch a command prompt or terminal and run the command java -jar duke.jar to start the application.
5. Fitz will display a greeting message.
6. If you encounter any issues when setting up and hereon, do check out the [FAQ](#faq) section.

### Viewing Workout Help

Display basic or detailed help information explaining the commands available in the application.

Format: `/whelp`

Example of usage:

`/whelp`

Expected output:
```
Here are the list of commands that you can use for workout record:
=======================================
- [Start a workout: /wstart])
- [Add exercise: /wadd]
- [Display all the days: /wlist]
- [Display workouts information for a specific day: /wview]
- [Display total amount of reps and set for one week /wcount]
- [Delete workouts: /wdelete]
- [End current workout: /wend]
- [Exit app: /exit]
=======================================
```
### Viewing Calories Help

Display basic or detailed help information explaining the commands available in the application.

Format: `/chelp`

Example of usage:

`/chelp`

Expected output:
```
Here are the list of commands that you can use for calories record:
=======================================
- [Add food and calories: /cadd]
- [Display total calories consumption: /clist]
- [Display calories consumed on a specific date : /cview]
- [Delete calories record for one food: /cdelete]
- [Exit app: /exit]
=======================================
```


## Features

### Start a workout: `/wstart`
Starts the workout for a specific date

Format: `/wstart WORKOUT_NAME`

Example of usage: 

`/wstart chest day`

`/wstart leg day`

Expected output:
```
chest day started on 09/04/23.
```

### Add exercises to current workout: `/wadd`
Adds exercise to the current workout.

Format: `/wadd EXERCISE_NAME WEIGHT_USED_WEIGHT_UNIT RPS`

* `/wadd` only works after a workout is started with `/wstart`.
* `WEIGHT_USED_WEIGHT_UNIT` example: 100kg or 100lb (the unit needs to be connected with the weight used)
* `RPS` it means reps per set of an exercise it can be inputted in this format: 7 6 9

Example of usage:

`/wadd chest press 100kg 7 6 5`

`/wadd leg press 150kg 7 6 5`

Expected output:
```
chest press 100kg 7 6 5 has been added.
```

### List workout dates: `/wlist`
Display the list of dates of workouts done.

Format: `/wlist`

Example of usage:

`/wlist`

Expected output:
```
Here is the list of dates of your workouts:
1. 08/04/23 21/02/21 chest day
2. 08/04/23 chest day
3. 09/04/23 chest day
=======================================
```
### View a workout: `/wview`
Display the list of exercises done of a workout date.

Format: `/wview INDEX`

* `INDEX` is the number that is displayed when using the `/wlist` function
Example of usage:

`/wview 3`

Expected output:
```
Here are the list of exercises for chest day on 09/04/23.
1. chest press 100kg 7 6 5
=======================================
```
### Delete a workout: `/wdelete`
Delete a workout on a specified date.

Format: `/delete INDEX`

* `INDEX` is the number that is displayed when using the `/wlist` function

Example of usage:

`/delete 3`

Expected output:
```
Deleted chest day on 09/04/23.
```
### Count sets and reps over a week: `/wcount`
Displays the list of distinct exercises over a week and the associated total number of sets and reps for each one

Format: `/wcount <DD/MM/YY>`

Example of usage:

`/wcount 09/04/23`

Expected output:
```
Information of exercises for the week of 09/04/23
Name: leg press, sets: 3, rps:22
=======================================
```
### Add calories consumed: `/cadd`
Add record of calories consumed.

Format: `/cadd DD/MM/YY FOOD_NAME CALORIE_COUNT`

* `CALORIE_COUNT` can be omitted if food has not been added previously.

Example of usage:
`/cadd 11/02/23 chicken 100`

Expected output:
```
Added chicken(100 kcal) to 11/02/23.
```
### View calorie consumption: `/clist`
Display the list of dates and total calorie consumption.

Format: `/clist`

Example of usage:
`/clist`

Expected output:
```
Here is your list of daily calorie consumption: 
1. 11/02/23: 100kcal
=======================================
```
### View calorie consumption: `/cview`
View the total calorie consumption in a specified date.

Format: `/cview DD/MM/YY`

Example of usage:
`/cview`

Expected output:
```
Here are the foods consumed on 11/02/23:
1. chicken - 100kcal
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

**A**: As of v2.1, the app does save your data.

## Command Summary

* The list of workout Commands `/whelp`
* The list of calorie Commands `/chelp`
* Start a workout `/wstart WORKOUT_NAME`
* Add exercise `/wadd EXERCISE_NAME WEIGHT_USED_WEIGHT_UNIT RPS`
* List workout dates: `/wlist`
* View a workout: `/wview DD/MM/YY`
* Delete a workout `/wdelete DD/MM/YY`
* Count sets and reps over a week: `/wcount DD/MM/YY`
* End current Workout `/wend`
* Add calories consumed: `/cadd DD/MM/YY FOOD_NAME CALORIES`
* List calorie consumption dates: `/clist`
* View calorie consumption: `/cview DD/MM/YY`
* Delete calorie consumption date: `/cdelete DD/MM/YY`
* Exit app `/exit`
