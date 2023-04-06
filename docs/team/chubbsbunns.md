---
layout: page
title: Dylan's Project Portfolio Page
---
## Dylan- Project Portfolio Page


## Overview
Our AY22/23 Sem 2 CS2113 (Software Engineering) Team Project
A CLI based fitness tracker intended to give users a way to manage and learn new workouts. We hope to be able to 
encourage individuals who are interested in keeping fit so that they can live a healthy lifestyle.


### Summary of Contributions
Below lists the contributions that I have provided to FitnessDuke.
Here is the contributed done by me:
[Reposense Link to my code](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ChubbsBunns&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ChubbsBunns&tabRepo=AY2223S2-CS2113-W13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Features Implemented


### Feature: ```easy```, ```medium```, ```hard``` filters
* What it does: Implemented the parameters/filters used when generating a list of exercises of a specific difficulty level and the 
corresponding error checks. <br>
* Justification: The JSON file used the words ```Beginner```, ```Intermediate```, ```Expert``` for their ```.getLevel()``` function, 
which I believed was not user-friendly enough from the user's perspective as we do not want the user to guess whether 
they are a *beginner* or an *expert*. Rather we wanted to give the user the perspective of whether they want an ```easy``` 
workout or a ```hard``` one, which is a reflection of the exercise, not the user.
* Highlights: The feature parses the ```easy```, ```medium```, ```hard``` inputs and returns a ```Beginner```, 
```Intermediate``` or ```Expert``` output that is given to the 
[GenerateExercise](..%2F..%2Fsrc%2Fmain%2Fjava%2Fseedu%2Fduke%2Fdata%2Fexercisegenerator%2FGenerateExercise.java) 
class for filtering.

### Feature: ```start```
* What it does: Starts a workout session, logging the last generated exercise session using the 
[GenerateExercise](..%2F..%2Fsrc%2Fmain%2Fjava%2Fseedu%2Fduke%2Fdata%2Fexercisegenerator%2FGenerateExercise.java)
class.
* Justification: This is one of the core features of Fitness Duke. Since motivating a user to exercise is the main 
reason why we are developing FitnessDuke, creating a feature that provides an indication to the user that he or she can
 start exercising is essential to FitnessDuke.
* Highlights: Switches the command handler to a different state, where the focus is on completing one's workout.
Commands after using the start command are limited to a few commands. This is meant to emphasize the importance 
of completing one's workout. More details of this can be seen below. 

### Feature: ```current```
* What it does: Lists the current workout session's exercises. 
* Justification: During a workout session, the user might forget what their next workout is. Listing the
workouts of the current session allow one to see all the workouts for the current session.
* Highlights: This command is only available during an exercise session, as it would only make sense for there to be
a "current" exercise when there is an exercise ongoing. Trying to access this method in other states 
(i.e. not during a workout session) will result in an error message popping up.

### Feature: ```finish``` ```cancel```
* What it does: Finishes or cancels the current workout session. If using the ```finish``` command, it will save 
the exercise session as a completed exercise session.
* Justification: A user may finish, or decide to give up on  a workout session. If they finish a session, they
* Highlights: Using the finish command will save the current workout session into a json file. Finishing exercises also
add current exercise counts to the achievements list. If an achievement is completed at this stage, a congratulations 
message will pop up and a list of all completed achievements will appear.

### Feature: ```achievements``` ```clear_achievements```
* What it does: A list of achievements that can be achieved by finishing workout sessions. Calling the 
```achievements``` command will list all the available achievements and their details (i.e. name, requirements, 
difficulty level, current count), and whether they have been achieved or not.
* Justification: Continuously motivating the user is essential to keeping them healthy in the long run.
Creating achievements to give them arbitrary goals is a way to give users an extrinsic source of motivation
to continue using FitnessDuke to exercise.
* Highlights: Different achievements have different difficulties, with an increasing number of exercises required. 
Higher difficulties have a higher number of "stars" (i.e. asterisks *) attached to them, hopefully enticing users to 
want to achieve them.