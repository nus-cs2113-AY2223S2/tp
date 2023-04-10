# ***LifeTracker User Guide***

## Introduction

**_LifeTracker_** is an application that allows users who are health conscious automate the tracking of their calories and keep a record of 
their calorie intake history.

The app also allows users to keep track of their daily exercises to compute their calorie loss.

Their net calorie gain/loss can then be viewed.
- [Quick Start](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#quick-start)
- [Features](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#features)
  - [Viewing User Profile](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#viewing-user-profile-view)
  - [Updating User Profile](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#updating-user-profile-update)
  - [Adding a meal](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#adding-a-meal-add)
  - [Listing foods](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#listing-foods-list)
  - [Listing meals](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#listing-meals-list)
  - [Listing exercises](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#listing-exercises-list)
  - [Deleting meals](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#deleting-meals-delete)
  - [Filtering foods](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#filtering-foods-filter)
  - [Viewing nutrition content of foods](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#view-nutrition-content-of-food-nutrition)
  - [Adding an exercise](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#adding-an-exercise-exercise)
  - [Tracking net calorie intake](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#tracking-net-calorie-intake-track)
  - [Displaying examples of meals and exercises](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#displaying-examples-of-meals-and-exercises-examples)
  - [Exiting the application](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#exiting-the-application-bye)
- [FAQ](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#faq)
- [Command Summary](https://ay2223s2-cs2113-w15-1.github.io/tp/UserGuide.html#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `LifeTracker` from [here](https://github.com/AY2223S2-CS2113-W15-1/tp).
3. Copy the file to the folder you want ot use as the home folder for your LifeTracker.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar lifetracker.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. 
6. Refer to the features below for details of each command.

## Features

* View and Update user profile
* Add meal
* Delete meal
* Add Exercise
* Calculate caloric needs
* Calculate amount of calories left in the day
* Find the nutrition of a certain kind of food
* Filter foods based on calories
* Track Calorie Intake

### Viewing User Profile: `view`

To view user's profile. 
User can simply input a single line command to view the specific information in their user profile using the format
below.

Format: `view /[fieldName]`

Here is a table of the information that the user can choose to view alongside the field name of it:

<div style="display: grid; grid-template-columns: 200px 200px; grid-row-gap: 0px;">
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">Information</p>
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">/[fieldName]</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Name</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/name</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Weight</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/weight</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Height</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/height</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Age</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/age</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Gender</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/gender</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Daily caloric limit</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/caloricLimit</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Calories remaining for today</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/caloriesLeft</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Target weight</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/targetWeight</p>
</div>
<br>

Example:

![view-example](./UG-images/view-example.PNG)


### Updating User Profile: `update`

Allows the user to update any of their information.
User can simply input a single line command to update the specific information in their user profile using the format
below.

Format: `update /[fieldName] [newInfo]`

Here is a table of the information that the user can choose to update alongside the field name of it:

<div style="display: grid; grid-template-columns: 130px 130px 700px; grid-row-gap: 0px;">
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">Information</p>
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">/[fieldName]</p>
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">Comments</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Name</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/name</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">For more than one name, separate with an underscore. E.g Firstname_LastName</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Weight</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/weight</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Weight can only take in numbers between 0 and 700 kg.</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Height</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/height</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Height can only take in numbers between 0 and 300 cm.</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Age</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/age</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Age can only take in numbers between 0 and 120 years old.</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Gender</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/gender</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Gender can only take in male or female as arguments.</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Target weight</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">/targetWeight</p>
  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">TargetWeight can only take in numbers between 0 and 700 kg.</p>
</div>
<br>

Example:

![update-example](./UG-images/update-example.PNG)

### Adding a meal: `add`

Adds a new meal to database

Format: `add /on [date] /type [MealType] /foods [foods]`

* The `date` should be in `d/M/yyyy` format
* The `MealType` can be one of the following
  * Breakfast
  * Lunch
  * Dinner
* The `foods` is a list of foods seperated by `, ` (Comma with a space after)

Example of usage:

`add /on 3/3/2023 /type Lunch /foods Spaghetti, Alfredo (Small)`

![add-example](./UG-images/add-example.PNG)

#### Alternatives

For users who are not experienced with typing fast on keyboards, *LifeTracker* offers a menu option to add meal

Format: `add`

![add-alt-example](./UG-images/add-example2.PNG)

### Listing foods: `list`

For users to view all foods currently supported in the *LifeTracker* database

Format: `list foods`

Example:
![list-foods-example](./UG-images/list-foods-example.PNG)

### Listing meals: `list`

For users to view previously added meals

Format: `list meals`

Example:
![list-meals-example](./UG-images/list-meals-example.PNG)

### Listing exercises: `list`

For users to view previously added exercises

Format `list exercises`

Example:
![list-exercises-example](./UG-images/list-exercises-example.PNG)

### Deleting meals and exercises: `delete`

For users to remove previously added meals and exercises

Format: `delete /[meal, exercise] [index]`

* The `index` should be a positive integer and must be less than number of meals/exercises added
* It is recommended to run `list meals/exercises` beforehand to get the index of the meal/exercise you want to delete

Example:
![delete-example](./UG-images/delete-example.PNG)

### Filtering foods: `filter`

For users to filter the foods by their calorie content. The user inputs the lower and higher bound that they want to filter the food by,
in terms of the calorie content of the food.

The list of food within the range will then be displayed, from which the user can choose from.

Format: `filter [lower_bound] [upper_bound]`

* The lower and upper bound should be a `float` value
* The lower bound should be lower than or equal to the upper bound

Example:
![filter-example](./UG-images/filter-example.PNG)

### View nutrition content of food: `nutrition`

For users to view the nutrition content of the food. The user first needs to search for the food, then the nutrition content of the food will be printed.

Format: `nutrition`

* The first food search should be in English
* To select the particular food filtered, an integer should be inputted

Example:
![nutrition-example](./UG-images/nutrition-example.PNG)

### Adding an exercise: `exercise`

For user to input the exercise done previously.

Format: `exercise /type [exercise_name] /description [exercise_description] /calories [calories_burnt] /on [date]`

* The `exercise name` and `exercise description` accepts any input
* The `calories` should be in `float` format
* The `date` should be in `d/M/yyyy` format

Example:
![exercise-example](./UG-images/exercise-example.PNG)

### Tracking net calorie intake: `track`

For user to track their previous net calorie intake.

There are 2 options to run this command:

1) For user to view all their calorie history from past meals and exercises input
Format: `track all`

2) For user to view calorie history within a specified time-frame
Format: `track /start [start_date] /end [end_date]`

* The dates should be in `d/M/yyyy` format

Output:
![track-example](./UG-images/track-example.PNG)

### Displaying examples of meals and exercises: `examples`

For user to gain some inspiration on exercises to do, as well as some idea on the meals that they can eat.

Format: `examples [meal/exercise]`

Example:
![examples-example](./UG-images/examples-example.PNG)

### Exiting the application: `bye`
Allows user to exit the appliction.

Format: `bye`

Output:
![bye-example](./UG-images/bye-example.PNG)

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Do copy the `data` folder and its contents to the new computer

**Q**: Where do you get the data for the food from?

**A**: The nutrition data is based on food from NUS TechnoEdge canteen, as this application would be mainly targeted at NUS Engineering students who eats there regularly. 

## Command Summary

Note: 
1) Fields within square brackets are variable and are to be changed according to user needs.
2) Fields with a comma inside the square brackets indicate that any of the multiple options can be input according to user needs.

<div style="display: grid; grid-template-columns: 130px 850px; grid-row-gap: 0px;">
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">Action</p>
  <p style="border: thin solid; text-align: center; font-weight: bold; margin: 0px;">Format, Examples</p>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">View</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>view /[fieldName]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Update</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>update /[fieldName]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Add</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>add /on [date] /type [MealType] /foods [foods]</code> OR <code>add</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">List</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>list [foods, meals, exercises]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Delete</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>delete /[meal, exercise] [index]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Filter</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>filter [lower_bound] [upper_bound]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Nutrition</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>nutrition</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Exercise</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>exercise /type [exercise_name] /description [exercise_description] /calories [calories_burnt] /on [date]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Track</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>track all</code> OR <code>track /start [start_date] /end [end_date]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Examples</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>examples [meal, exercise]</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Help</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>help</code></div>

  <p style="border: thin solid; margin: 0px; padding: 5px 15px;">Exit</p>
  <div style="border: thin solid; margin: 0px; padding: 5px 15px;"><code>bye</code></div>
</div>
<br>
