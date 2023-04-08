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
      - [Start a new day: /wday](#start-a-new-day--wday)
      - [Start a workout: /wstart](#start-a-workout--wstart)
      - [Add exercises to current workout: /wadd](#add-exercises-to-current-workout--wadd)
      - [List workout dates: /wlist](#list-workout-dates--wlist)
      - [View workout details: /wview](#view-a-workout--wview)
      - [Delete workout record: /wdelete](#delete-a-workout--wdelete)
      - [Count sets and reps for a week: /wcount](#count-sets-and-reps-over-a-week--wcount)
  - [Calories Recorder](calories-recorder)
    - [Start a new day: /cday](#start-a-calories--wstart)
    - [Add calories consumed: /cadd](#add-calories-consumed--cadd)
    - [List all the datesof Calories consumed: /clist](#list-calories-clist)
    - [View calorie consumption: /cview](#view-calorie-consumption--cview)
    - [Delete calories record: /cdelete](#delete-a-workout--delete)
  - [List of valid commands : /help](#list-of-valid-commands--help)
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
- [Start a new day: /wday])
- [Start a workout: /wstart])
- [Add exercise: /wadd]
- [Display all the days: /wlist]
- [Display workouts information for a specific day: /wview]
- [Display total amount of reps and set for one week /wcount]
- [Delete workouts: /wdelete]
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
- [Start a new day: /cday])
- [Add food and calories: /cadd]
- [Display total calories consumption: /clist]
- [Display calories consumed on a specific date : /cview]
- [Delete calories record for one food: /cdelete]
- [Exit app: /exit]
=======================================
```


## Features

### Start a workout: `/wday`
Starts the workout for a specific date

Format: `/wday DD/MM/YY`

* The `DATE` needs to be in the exact format.

Example of usage:

`/wstart 11/03/23`

`/wstart 21/04/23`

Expected output:
```
Great! You have added a new workout for 11/02/23
```

### Start a workout: `/wstart`
Starts the workout for a specific date

Format: `/wstart WORKOUT_NAME`

* The `DATE` needs to be in the exact format.

Example of usage: 

`/wstart Chest day`

`/wstart leg day`

Expected output:
```
/wstart Chest day
Great! You have added a new workout for Chest day.
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
