# Developer Guide

---
### Acknowledgements

1. Command, Parser and UI java files are adapted from one of our group member's [Duke Project](https://github.com/MingEn82/ip)
---

## Design

---
### Architecture

![](./uml/architecture.PNG)

**Main Components of LifeTracker**

`LifeTracker` is where users will access the application from. It is responsible for:

- At app launch: Initialise the Databases and UI classes
- At app closure: Saves all updated data to databases

The rest of the Application consists of three components:

- `UI`: Reponsible for I/O between user and application
- `Command`: Handles execution of user inputs
- `Storage`: Stores information of the user and meals eaten

---
### UI Component
The UI is made up of three classes, `GeneralUi`, `CalorieUi` and `WeightUi`, and together,
they improve on the accessibility of the application.

The `UI` component,
- Displays what are the possible inputs at any one time
- Allows the user to check that a value added is correct
- Informs the user whenever there is an invalid input

### Storage Component

![storage-class-diagram](./uml/storage-class-diagram.PNG)

The `Storage` is the base class which all `Storage` components inherit from. There are currently four storages:

1. `FoodStorage`: For parsing and storing all of the foods supported by _LifeTracker_
2. `UserStorage`: For storing the user's profile
3. `MealStorage`: For storing the meals that the user ate
4. `ExerciseStorage`: For storing the exercises that the user did

#### Interfaces

To ensure that each `Storage` component only implements methods for writing and reading when needed, we created two interfaces

1. `FileReadable`: Has `load()` method for `Storage` component to be able to read from a database
2. `FileWritable`: Has `write()` method for `Storage` component to be able to write to a database

For example, `FoodStorage` is not meant to be edited by user, hence it does not implement `FileWritable`

# Implementation
This section describes some noteworthy details on how certain features are implemented.  
<p>&nbsp;</p>

## [Proposed] Add meal feature

### Proposed Implementation

The proposed mechanism for adding a meal is facilitated by `AddMealCommand`. It extends `Command` and overrides the
`execute` method in the `Command` class.

In this command, there are 2 ways for the user to add a meal to storage. 
1. Using a one-line command in their CLI in the format `add [DATE] /on [MEAL_TYPE] /type [FOOD_INDEX] /foods`
2. Typing `add` into the CLI and following the printed prompts to enter the date in the specified format, type of meal and specific food.

<img src="uml/AddMealCommand1.png" alt="Sequence Diagram" width="500">

Step 1. As seen from the sequence diagram above, when the AddMealCommand is executed via the `execute` method in 
LifeTracker, the user's input is first parsed to determine how he/she wants to input it. Either method sets the food, 
date and meal type features necessary to create a new meal.

Step 2. The constructor for the `Meal` class is called which instantiates a new instance of Meal using the
parameters provided.

Step 3. `mealStorage` saves the meal to the database and then `ui` prints out the confirmation of the meal added.

### Design considerations

## [Proposed] List feature

### Proposed Implementation

The proposed mechanism for listing stored foods, meals and exercises is facilitated by `ListCommand`. It extends `Command` and overrides the `execute` method in the `Command` class.

Step 1: The user will input either `list foods`, `list meals`, `list exercises` based on which information the user wants to retrieve.
Step 2: Based the input, `ListCommand` will call either `printAllFoods`, `printAllMeals`, or `printAllExercises` method of the `ui` object
Step 3: The `ui` will retreive the relevant information from the storage and print out their details

![list-command](./uml/ListCommand.PNG)

### Design Considerations

## [Proposed] Delete meal feature

### Proposed Implementation

In order to delete a meal from their meal history, a user has to type `delete [MEAL_INDEX]` into the CLI. Users can see 
which meal they want to delete by viewing the mealData.csv file.

<img src="uml/DeleteMealCommand1.png" alt="Sequence Diagram" width="500">

In the above implementation, DeleteMealCommand parses the user input to obtain the index to delete and proceeds to 
delete it via the method from mealStorage() and prints out the deleted meal to the user.

### Design considerations

## [Proposed] View feature

### Proposed Implementation

The proposed view mechanism is facilitated by `ViewUserCommand`. It extends `Command` and overrides the 
`execute` method in the `Command` class. 

It stores the user's data internally as `user` and the meals consumed by the user as `meals`. It also 
initializes the UI for calories as `calorieUI`.

Given below is an example usage scenario and how the view feature behaves at each step.

Step 1. The user launches the application and calls the `view` command. The `ViewUserCommand` will be initialized
with the current user and meal storage state. `user` and `meals` will point to the user storage and meal state 
respectively.

<img src="uml/ViewUserCommandSD1.png" alt="Sequence Diagram 1" width="500">


The user will then be presented with a menu showing the different user details he can view as seen in the code
snippet below.  

````
View user settings
1. View Name
2. View Weight
3. View Height
4. View Age      
5. View Gender
6. View Daily Caloric limit
7. View Calories left today
8. Back
````

Step 2. The user chooses to view his weight by inputting the number `2` to choose `View Weight`.
This calls the getter method `getWeight()` in the entity `User` to return the current weight of the user
and initializes the variable `weight` with that value. 

<img src="uml/ViewUserCommandSD2.png" alt="Sequence Diagram 2" width="500">

It then prints to screen the weight of the user like in the code snippet below.

````
Weight: {weight} kg

Continue viewing?
1. Yes
2. No
````
Step 3. The user chooses to continue viewing by inputting the number `1` and chooses `View Daily Caloric Limit` next
by inputting the number `6`. This calls the getter method `getCaloricLimit()` in the entity `User` to return the
current daily caloric limit of the user and initializes the variable `caloricLimit` with that value. 

<img src="uml/ViewUserCommandSD3.png" alt="Sequence Diagram 2" width="500">

It then prints to screen the daily caloric limit of the user like in the code snippet below.

````
This is your daily caloric limit: 
{caloricLimit} Kcal

Continue viewing?
1. Yes
2. No
````

Step 4. The user executes the command `update` to update his user details. The user is presented with a menu showing the
different user details he can update as seen in the code snippet below.

````
Update user settings
1. Update Name
2. Update Weight
3. Update Height
4. Update Age
5. Update Gender
````
The user chooses to update his weight by inputting the number `2`. This causes the state of the user's details to be 
modified and the user's weight as well as his caloric limit will be updated in accordance to the new weight entered.

> Insert UML diagram showing the update process

Step 5. The user then executes the command `view` to view his updated weight and daily caloric limits.

### Design considerations

## [Proposed] Update feature

### Proposed Implementation

### Design considerations:

## [Proposed] Nutrition feature

### Proposed Implementation

### Design considerations:

## [Proposed] Filter feature

### Proposed Implementation

### Design considerations:

## [Proposed] Exercise feature

### Proposed Implementation

### Design considerations:

## [Proposed] Track feature

### Proposed Implementation

The proposed mechanism for tracking net calorie intake is facilitated by `TrackCalorieCommand`. It extends `Command` and overrides the `execute` method in the `Command` class.

Step 1: The user will input `track` and optional arguments `/start` and `/end` to filter the results based on date
Step 2a: Based the input, `TrackCalorieCommand` will either parse the start date from user input or set the start date to the earliest meal or exercise added by calling `getStartingDate`.
Step 2b: Based the input, `TrackCalorieCommand` will either parse the end date from user input or set the end date to the latest meal or exercise added by calling `getEndingDate`.
Step 3: `TrackCalorieCommand` will retrieve the meals and exercises filtered from the starting date by calling `getMealByDate` and `getExerciseByDate` respectively.
Step 4: `TrackCalorieCommand` will iterate through the filtered meals and exercises day by day and print out the net calorie intake for each day.

![track-calories-command](./uml/TrackCaloriesCommand.PNG)

### Design considerations:

## [Proposed] Examples feature

### Proposed Implementation

### Design considerations:


# Appendix: Requirements

## Product scope

### Target user profile:

- Is an NUS student
- Looking to lose/maintain their weight
- Looking to make healthier choices in terms of food
- Can type fast
- Prefers typing to mouse interactions
- Is reasonably comfortable using CLI apps

### Value proposition:
For individuals trying to lose weight, managing their daily caloric intake is crucial. However, many may find it 
hard to track and manage their calories. _LifeTracker_ allows users to easily automate the tracking of their calories
and keep a record of their daily caloric intake. 

_LifeTracker_ can also keep track of the exercises that users have done and factor it in to their daily calories lost. This allows
the user to keep track of their net calorie gain on a daily basis.

### User Stories
| Version | As a ... | I want to ...                                            | So that I can ...                                           |
|---------|----------|----------------------------------------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions                                   | refer to them when I forget how to use the application      |
| v1.0    | user     | calculate my caloric needs based on my height and weight | lead a healthy lifestyle within my caloric needs            |
| v1.0    | user     | add a meal                                               | keep track of the food I have eaten on a particular day     |
| v1.0    | user     | delete a meal                                            | remove a meal if I entered it in wrongly                    |
| v1.0    | user     | view my previous meals                                   | track the calories of each meal                             |
| v1.0    | user     | key in my weight on a daily basis                        | keep track of my weight loss/gain                           |
| v1.0    | user     | see the amount of calories left I have in the day        | not exceed my daily caloric limit                           |
| v2.0    | user     | search for a type of food and view its nutritional contents | make a more informed choice about what to eat            |
| v2.0    | user     | search for meals within a specific calorie range         | decide which meal to consume                                |
| v2.0    | user     | enter the type and duration of exercise I have completed | keep better track of my physical activies                   |

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java `11` installed.
2. This app is meant for a single user and will not be able to accurately keep track of the meals, personal information and caloric requirements for different people.
3. This app is targeted at users who would prefer typing over other input types. Ideally they would have an above-average typing speed and therefore prefer CLI over other interfaces. 

## Glossary

* *glossary item* - Definition
___
## Instructions for manual testing

Given below are instructions on how to test the application by yourself manually. You can use these instructions as a starting point for your testing. 

### Launch and Shutdown
- Initial launch
  - Download the jar file from the release page and copy the file into an empty folder
  - Open a cmd terminal and redirect or cd into the folder the jar file was downloaded into.
  - Type "java -jar tp.jar" and press enter to run the file.
 
- Shutdown
  - Type "bye" into the command line and press enter to exit the application.

### View user profile

Test case: view

Expected: Menu where user can input a value from 1 to 9 to view their current profile: 

1. Name
2. Weight
3. Height
4. Age
5. Gender
6. Daily Caloric Limit
7. Calories Remaining for today
8. View Target Weight
9. Exit

User can then input 1 to continue viewing their profile or 2 to exit.

### Update user profile

Test case: update

Expected: Menu where user can input a value from 1 to 7 to update their current profile: 

1. Name
2. Weight
3. Height
4. Age
5. Gender
6. Target Weight
7. Exit

User can then input 1 to continue viewing their ofile or 2 to exit.

### Adding a meal

Test case: add /on 3/3/2023 /type Lunch /foods Spaghetti, Alfredo (Small)

Expected: Spaghetti and Alfredo are added to the list. Details of the food such as calories are shown in the status message.

Test case: add

Expected: Application will then ask for date of meal, type of meal, and food, and will then display the foods in the database containing the food that was added. Food is then added to the list and details of the food such as calories are shown in the status message.

Test case: add /on dummy /type dummy /foods dummy

Expected: No food is added. Error details are shown in the status message, such as "_dummy_ is not a valid date", "Invalid meal type" or "no food found with _dummy_".

### List or foods in database meals added

Test case: list meals

Expected: A list of meals eaten today would be displayed.

Test case: list foods

Expected: A list of all foods in the databse would be displayed.

Test case: list dummy

Expected: An error message would be displayed.

### Deleting a meal

Prerequisite: List all meals eaten using the list command. At least 1 meal in the list.

Test case: delete 1

Expected: First meal is deleted from the list. Details of the deleted meal are shown in the status message. 

Test case: delete 0

Expected: No meal is deleted. Error details shown in the status message.

Other incorrect delete commands to try: delete, delete x, (where x is larger than the list size)

Expected: Similar to previous.

### Filter foods based on calories

Test case: 

Expected:

### Find nutrition of a food

Test case: 

Expected:

### Add a exericse

Test case: 

Expected:

### Track calorie intake

Test case: 

Expected:

### See examples of meals or exercise

Test case: 

Expected:

### See list of available commands

Test case: 

Expected:

### Exiting the program

Test case: 

Expected:

