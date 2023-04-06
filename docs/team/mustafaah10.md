# Mustafa's Project Portfolio Page

## Project: [LifeTracker](https://github.com/AY2223S2-CS2113-W15-1/tp)

LifeTracker is a desktop application used for encouraging users to lead a healthier life by tracking their meals, exercises and net calorie intake. The user interacts with it using a CLI. 

Given below are my contributions to the project.

### General

#### LifeTracker
 - Worked on creating the main executable `LifeTracker` which initialises the various databases upon first starting the programme and continuously takes in and executes the user input thereafter.

#### Command Parser
 - Worked on creating the main interface `CommandParser` for parsing of user input to decide which command needs to be executed by the programme.

### Commands

I worked on some commands which help the user to input the relevant data into the CLI so that it can be tracked and recorded into the app's databases. Some examples are as follows:

#### Add Exercise
 - Handled the `AddExerciseCommand` for users to add exercise into the exercise database. Handles the parsing and exceptions for fields like the exercise name, description, date, calories etc. 
#### Delete Command
 - Handled the `DeleteCommand` for users to delete a meal or exercise from their respective databases.
#### Track Command
 - Handled the `TrackCalorieCommand` for users to track their overall history or see their history within some specified dates.
#### List Command
 - Handled the `ListCommand` which enables users to see all meals/ foods/ exercises in the current storage database.

### Entities
#### Exercise
 - Created the `Exercise` entity to be used which stores values in its fields for the exercise name, its description, the calories burnt and the date it was performed.

### Contributions to team-based tasks
 - Created the logger which logs events like when the user started the app, meals and exercises added, when exceptions occurred. 
 - Created a local log file which is written to by the logger mentioned above.

## Code Contributed
[RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=mustafaah10&breakdown=true)

## Project Management
- Helped to fix bugs discovered in our Team's product during user testing.

## Enhancements to existing features
 - Combined the deletion of meals and exercises into a single command since they used similar functionality
 - Implemented tracking within specified dates instead of just seeing all the history at once
 - Implemented a way for users to see their net calories burnt and consumed on a date when they add a meal or exercise