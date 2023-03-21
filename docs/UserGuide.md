# User Guide 
#  ❚·══·❚ Fitz ❚·══·❚


## Introduction 
This application will help you track your current and future workouts.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Fitz` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

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

Expected output:
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
### Deleting a specific workout: `/view`
Delete the specified workout

Format: `/delete <DD/MM/YY>`

Example of usage:

`/delete 21/03/23`

Expected output:
```
Workout deleted successfully.
```
### Exiting the App: `/exit`
Delete the specified workout

Format: `/exit`

Example of usage:

`/exit`

Expected output:
```
Thank you and see you next time
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

* Start `/start <DD/MM/YY>`
* Add   `/add <exercise_name> /weight <weight_used> /rps <reps_per_set>`
* List `/list`
* View `/view <DD/MM/YY>`
* Delete `/delete <DD/MM/YY>`
