# Developer Guide
Welcome to the Meal Companion Developer Guide! Thank you for taking an interest in the behind-the-scenes working of our product, and we hope this document proves informative and useful for your work.

## Table of Contents
1. [Acknowledgements](#acknowledgements)
2. [Setting up, getting started](#setting-up-getting-started)
3. [Design](#design)
   - [Architecture](#architecture)
   - [Command Parsing](#command-parsing)
   - [Ingredient Class](#ingredient-class)
   - [Recipe Class](#recipe-class)
4. [Implementation](#implementation)
   - [Ingredients](#ingredients)
     - [Add and Remove Command](#add-and-remove-command)
     - [Clear Command](#clear-command)
   - [Recipes](#recipes)
     - [Make Command](#make-command)
     - [Recipe Possible Command](#recipe-possible-command)
     - [Recipe All Command](#recipe-all-command)
     - [Recipe Almost Command](#recipe-almost-command)
     - [Recipe Need Command](#recipe-need-command)
   - [Storage Feature](#storage-feature)
5. [Appendix: Requirements](#appendix-requirements)
   - [Product scope](#product-scope)
   - [User stories](#user-stories)
   - [Use cases](#use-cases)
   - [Non-Functional Requirements](#non-functional-requirements)
   - [Glossary](#glossary)
6. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)


## Acknowledgements

We would like to thank the authors and contributors of the [GSON](https://github.com/google/gson) library.

Ideas regarding project structure, parsing, argument validation, and exception handling were taken from
[Ethan's iP](https://github.com/ethanyidong/ip), but were heavily modified and improved upon.

###### [Back to table of contents](#table-of-contents)

## Setting up, getting started

1. Java 11 is required for Meal Companion to function properly, please make sure your device has Java 11 installed. If not, you may download it [here](https://www.oracle.com/sg/java/technologies/downloads/#java11). If you need further asssistance installing Java 11, you may refer to the [Installation Guide](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html).

2. Once you have ascertained that your device has Java 11 installed, you may download the `.jar file` [here](https://github.com/AY2223S2-CS2113T-T09-3/tp/releases/tag/v2.0). 

![jar3.png](images/jar3.png)

Click on `mealcompanion.jar` to automatically download the file. Place the file in a folder of your choice.

If you wish to view the source code, you may click on either `Source code (zip)` or `Source code (tar.gz)` to download the source code of Meal Companion for your perusal and testing. Unzip the folder and open the project using any Integrated Development Environment (IDE), although we recommend using IntelliJ Idea. You can download IntelliJ Idea [here](https://www.jetbrains.com/idea/download/#section=windows). Please ensure you download the right version for your respective operating system. 

3. Open the command terminal and navigate to the folder where you downloaded `mealcompanion.jar` using `cd <file path>` commands as necessary.
4. Ensure the terminal is within the correct folder.
5. Use the command `java -jar mealcompanion.jar` to launch Meal Companion.   
![startup2.png](images/startup2.png)
6. When you are finished using Meal Companion, use the `bye` command to terminate the application. This will ensure your stored data is saved correctly for future access.

Please be advised that we cannot guarantee complete functionality of Meal Companion if you have modified the source code in your own local IDE. If you have any queries, feel free to reach out to any member of the development team and we will do our best to assist you.

###### [Back to table of contents](#table-of-contents)

## Design

### Architecture

![Architecture.png](images/Architecture.png)

The **Architecture Diagram** given above explains the high-level design of the program.
Given below is a quick overview of main components.

#### Main Components

* `MealCompanion`: Main entry of the program, initialises and connects the components
* `UI`: In charge of printing messages
* `Logic`: Determines the command to execute
* `Model`: Holds data from program in memory
* `Storage`: Read and write data from hard disk
* `Command`: Specific commands for execution

#### Program Flow
- The `MealCompanionSession` controls access to the `UI`, `Parsing`, `Model`, and `Storage` components of the app
- The user will send input to the `MealCompanionSession`.
- The `MealCompanionSession` will parse the input to produce a `Command`, which will hook back into the
`MealCompanionSession` to gain access to the other components when running

### Command Parsing

The system of `Routable` classes is built to allow for a composable setup of commands and subcommands.

Developers are able to build a "routing tree" by nesting `CommandRouterNode`s together, allowing for command matching
to be done in a robust and extensible way. This architecture was chosen because our commands are designed to have common
prefixes, such as the "recipe all" and "recipe possible" commands.

Therefore, we need to standardize the way we parse
the command in such a way that we do not have to worry about match priority or ordering, as we would if we
were to implement this with simple if-else or switch statements.

![RouterUML.png](images/RouterUML.png)

- The "routing tree" is built around the concept of the `Routable` interface, with a `CommandRouterNode` at the root.
- Classes which implement `Routable` have a `resolve` function which will map a `CommandTokens` object to an
`ExecutableCommandFactory`.
- Routing is done in a depth-first fashion via recursive calls to `resolve`, until an
endpoint is reached at which point the corresponding `ExecutableCommandFactory` is returned.

![RouterSnippetUML.png](images/RouterSnippetUML.png)

This is an object diagram representing a portion of the routing tree for MealCompanion, supporting the "recipe all",
"recipe possible", and "ingredient list" commands.

The sequence diagram below illustrates the process for resolving the "recipe all" command.

![RouterSequenceUML.png](images/RouterSequenceUML.png)

#### Command Tokens and Recursive Matching

The `CommandTokens` class represents a rewindable queue of words in the user's input
which allows for performant matching of nested commands. As the `CommandTokens` instance gets passed to the 
`CommandRouterNode`, it will check if the word at the head matches its name. If it does, the queue is advanced and the
tokens are passed recursively deeper into the routing tree, and if not the queue is rewound and passed back up the 
routing tree.

#### Argument Extractors

![Extractors.png](images/Extractors.png)

This is a class diagram representing the `Extractors` system for extracting values from passed arguments. Note that not
all implementations of `ArgumentExtractor` is included.
`Extractors` provide validated inputs to the `ExecutableCommand` that is built by the factory.

The `ExecutableCommandFactory` may provide a list of `Extractors` to be run via the `getExtractors()` method.
The `runExtractors()` function should be called when building the command.

If extraction is run successfully, the factory may pull the validated
value out of the `ArgumentExtractor` using the `getExtractedValue()` method, and pass it to the `ExecutableCommand`
constructor.

If extraction yields an exception, the exception will be passed up to the caller of `buildCommand()`.

### Exception Handling

We have 5 different custom exceptions thrown by our code:
- `CommandRunException` - thrown only by `ExecutableCommand`s a problem occurs while running a command
- `InvalidArgumentException` - thrown only by `ArgumentExtractor`s when an argument value is malformed
- `InvalidCommandException` - thrown only by `Extractor`s when the command is malformed
- `MealCompanionException` - thrown by code run outside the REPL (for example, loading save data)

`CommandRunException`s and `InvalidCommandException`s are handled in the `MealCompanionSession`. The error message
is printed for the user and the program will wait for the next command.

`InvalidArgumentException` is never thrown outside the context of a `Extractor` calling its child `ArgumentExtractor`.
The `Extractor` will always wrap the thrown `InvalidArgumentException` in an `InvalidCommandException`, which is
handled as above.

`MealCompanionException` should be handled by the caller of the function which throws it. We are in the process of
shifting this responsibility away from the `ExecutableCommand`s.

### Ingredient Class

Below shows the class diagram of how ingredients are being stored in our program

![IngredientUML.png](images/IngredientUML.png)

The current `MealCompanionSession` would keep track of the `IngredientList` which is an ArrayList of `Ingredient` objects. Each `Ingredient` object has a name and its associated quantity. This association would allow us to perform the adding and removal of ingredients by the user.

### Recipe Class

Below shows the class diagram of how recipes are being stored in our program

![RecipeUML.png](images%2FRecipeUML.png)

The current `MealCompanionSession` would keep track of a `RecipeList` which is an ArrayList of `Recipe` objects.

###### [Back to table of contents](#table-of-contents)

## Implementation

### Ingredients
The following sections will cover the implementation of features related to ingredients.

###### [Back to table of contents](#table-of-contents)

#### Add and Remove Command

The add and remove command is facilitated by the methods in `IngredientList` and `Ingredient` of `MealCompanionSession`.

Given below is an example usage scenario and how the add and remove command behaves:

Step 1. The user inputs his command e.g. `add egg /qty 5`, `IngredientList` would be called to check if egg is already stored inside the list

Step 2. Suppose egg is not currently stored in `IngredientList`,  the name of the ingredient 'egg' would be crosschecked with our database of known ingredients

Step 3. Since egg is part of our list of known ingredients, a new `Ingredient` object would be created with the quantity, 5, and name, egg, specified by the user and added to `IngredientList` with the `add()` method.

Below shows the sequence diagram for the above AddCommand:

![AddIngredientSequenceUML.png](images/AddIngredientSequenceUML.png)

Continuing from the above AddCommand:

Step 4. Now the user decides to remove 2 eggs and inputs his command e.g. `remove egg /qty 2`, the `IngredientList` would be searched through to see if egg is in the list

Step 5. Since egg is in the list, its corresponding index in the list would be generated. 

Step 6. The current quantity of egg in the list would be obtained by calling `getQuantity()` and checked to see if it is greater or equals to '2' the quantity input by the user

Step 7. Since the quantity input by the user is smaller than the current quantity of egg, which is 5, in the `IngredientList`, the new quantity would be calculated to be 3 and updated by calling `setQuantity(3)` 

Below shows the sequence diagram for the above RemoveCommand:

![RemoveIngredientSequenceUML.png](images/RemoveIngredientSequenceUML.png)

###### [Back to table of contents](#table-of-contents)

#### Clear Command

The clear command is facilitated by "ClearCommand"

It requires `IngredientList` of `MealCompanionSession`.

The clear commands clears all ingredients currently in inventory

The following sequence diagram shows how the Clear Command works:

![ClearSequenceUML.png](images/ClearSequenceUML.png)

###### [Back to table of contents](#table-of-contents)

### Recipes
The following sections will cover the implementation of features related to recipes.

The activity diagram summarises how a user can use these features. 

![RecipeActivity.png](images/RecipeActivity.png)

###### [Back to table of contents](#table-of-contents)

#### Make Command

The make command is facilitated by `MakeCommand`.

It requires `RecipeList`, `IngredientList` and `Recipe` of `MealCompanionSession`. 

It is also implemented through methods in `RecipeCommand`.  

The make command takes in a recipe number as parameter.

The ingredients needed to make the recipe specified by the recipe number would be removed from inventory

The following sequence diagram shows how the Make Command works:

![MakeSequenceUML.png](images/MakeSequenceUML.png)

###### [Back to table of contents](#table-of-contents)

### Recipe Possible Command

The recipe possible command is facilitated by `RecipePossibleCommand`. 

It requires `RecipeList`, `IngredientList` and `Arraylist of Allergens`` of `MealCompanionSession`.

Given below is the only example usage scenario and how the recipe possible command behaves at each step.

Step 1: User wants to get a list of recipes that can be made with the current list of ingredients. User calls `recipe possible`.

Step 2: `RecipePossibleCommand` executes by retrieving the `RecipeList`, `IngredientList` and `Arraylist of Allergens` of `MealCompanionSession`.

Step 3: Every `Recipe` in `RecipeList` is checked against all `Ingredient` in `IngredientList` to see if it can be made.

Step 4: `Recipe` that can be made are returned from getPossibleRecipes function.

The following sequence diagram shows how the Recipe Possible Command works:

![RecipePossibleCommandSequence.png](images%2FRecipePossibleCommandSequence.png)

###### [Back to table of contents](#table-of-contents)

#### Recipe All Command

The recipe possible command is facilitated by `RecipeAllCommand`.

It requires `RecipeList` of `MealCompanionSession`.

Given below is the only example usage scenario and how the recipe possible command behaves at each step.

Step 1: User wants to get the full list of recipes stored in the program. User calls `recipe all`.

Step 2: `RecipeAllCommand` executes by retrieving the `RecipeList` of `MealCompanionSession`.

Step 3: Every `Recipe` in `RecipeList` is formatted then outputted.

The following sequence diagram shows how the Recipe All Command works:

![RecipeAllCommandSequence.png](images%2FRecipeAllCommandSequence.png)

###### [Back to table of contents](#table-of-contents)

#### Recipe Almost Command

The recipe almost command is facilitated by `RecipeAlmostCommand`.

It requires `RecipeList` and `IngredientList` of `MealCompanionSession`.

Given below is the only example usage scenario and how the recipe possible command behaves at each step.

Step 1: User wants to get a list of recipes that can almost be made with the current list of ingredients, i.e. the user is missing less than 4 ingredients to make the recipe. User calls `recipe almost`.

Step 2: `RecipeAlmostCommand` executes by retrieving the `RecipeList` and `IngredientList` of `MealCompanionSession`.

Step 3: Each `Recipe` in `RecipeList` is checked by the ingredients it needs against the `Ingredient` in `IngredientList`, to determine how many missing ingredients there are.

Step 4: `Recipe` that contain less than 4 missing ingredients are listed.

###### [Back to table of contents](#table-of-contents)

#### Recipe Need Command

The recipe need command is facilitated by `RecipeNeedCommand`.

It requires `RecipeList` and `IngredientList` of `MealCompanionSession`.

It takes in a recipe index, retrieves the `Recipe` from the `RecipeList` and outputs the additional `Ingredients` required to make the `Recipe` that are missing from the `IngredientList`.

###### [Back to table of contents](#table-of-contents)

### Storage Feature

The storage mechanism of ingredients is facilitated by `IngredientStorage`.

It requires `IngredientList` of `MealCompanionSession`.

This features saves ingredient list data in json format and reads them back into ingredient objects.

The following sequence diagram shows how data storage works:

![StorageSequenceUML.png](images/StorageSequenceUML.png)

The following activity diagram summarizes what happens when a user executes a command which alters the ingredients list:

![StoargeActivity.png](images/StorageActivity.png)

###### [Back to table of contents](#table-of-contents)

## Appendix: Requirements

### Product scope

#### Target user profile
Our target user profile is the average School of Computing student that resides on campus, regardless of Residential College (RC), Halls, Residences or Houses. There is a focus on those who regularly cook in their hostels or would like to pick up cooking.

#### Value proposition
Our product targets students who would like to save money on buying or ordering food, and also to save time by preparing their meals beforehand, so they can eat immediately after a class without having to queue for food or look for a seat in a crowded canteen on campus. Aside from RCs, hostels on campus do not provide meal plans, so our product addresses this by helping users pick up cooking and manage the ingredients they have in their hostel refrigerators. Even within RCs, the meal plans do not cover lunch, meaning students need to source their own lunch, which is a problem that our product also aims to alleviate.

###### [Back to table of contents](#table-of-contents)

### User Stories

#### v1.0

| As a ... | I want to ... | So that ... |
| -------- | ------------- | ----------- |
| User | see the list of items in the fridge | I can know what is/is not in my fridge |
| User | add an item of a specified quantity | I can put new items into my fridge |
| User | remove an item of a specified quantity | I can know which items I have already used |
| User | find recipes I can make with my current ingredients | I can cook without having to go shopping |
| User | see the list of recipes available | I can know which recipes are registered within the app |
| User | have a default list of recipes initialized at the start | I can at least prepare some default dishes without knowing any recipes |
| New User | immediately see how to interact with the app | I can begin using the app without needing to refer to external help |
| User with a new device | transfer the saved customized data to a new computer | I can use Meal Companion on a different computer without having to re-enter all the information |

#### v2.0

| As a ... | I want to ... | So that ... |
| -------- | ------------- | ----------- |
| User with allergies | add my allergens to the app | I can avoid ingredients that I am allergic to |
| User with allergies | block recipes that include allergens | I can avoid a medical emergency because of not realizing the recipe included allergens |
| User | add recipes to a favourite section | I can easily see recipes that I like |
| User who wants to lose weight | see the calorie count of the recipes | I can keep track of my caloric intake |
| User who gets bored of the same recipe | randomly select a recipe | I can try new recipes from the list of existing recipes |
| User returning after a long period of not using the app | quickly clear all ingredients from the saved list | I can return to using the app without having to individually delete items that I no longer have |

###### [Back to table of contents](#table-of-contents)

### Use Cases

**Use Case #1: Make a Recipe** <br>
**MSS**
1. User requests to list possible recipes to make
2. MealCompanion shows list of possible recipes using the current ingredients list of the user
3. User requests to make a specific recipe from the list
4. MealCompanion makes this recipe and removes all relevant ingredients from the ingredients list
<br/><br/>
   Use case ends.

**Extensions** <br>
* 2a. The list is empty. 
  <br/><br/>
  Use case ends.
   <br/><br/>
* 3a. The given index is invalid.
  * MealCompanion shows an error message.
  <br/><br/>
  Use case resumes at step 2.

**Use Case #2: See Missing Ingredients for Recipe:**
**MSS**
1. User requests to list all recipes
2. MealCompanion shows list of all recipes
3. User requests to see missing ingredients of a specific recipe from the list
4. MealCompanion shows list of ingredients in the recipe that are not in the ingredients list
   <br/><br/>
   Use case ends.

**Extensions** <br>
* 3a. The given index is invalid.
   * MealCompanion shows an error message.
     <br/><br/>
     Use case resumes at step 2.


###### [Back to table of contents](#table-of-contents)

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should respond to a command within a second.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A user should be able to infer what the commands do from their names.

###### [Back to table of contents](#table-of-contents)

## Glossary

* **Mainstream OS:** Windows, Linux, Unix, OS-X

###### [Back to table of contents](#table-of-contents)

## Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

### Launch and Shutdown

1. Initial Launch
   * Download the jar file and copy into an empty folder.
   * Run jar file in a terminal. <br>
   Expected: Welcome message is shown in the terminal and `ingredients.txt` file is created in the folder containing the jar file.
<br/><br/>
2. Exit from Program
   * Run the `bye` command. <br>
   Expected: Program exits and updates are made accordingly to the `ingredients.txt` file based on the commands ran before exiting the program.

### Make a Recipe

1. Prerequisites: List all possible recipes using the `recipes list` command. Recipe of index `1`  is in the list.
<br/><br/>
2. Test Case: `make 1`
Expected: All ingredients needed to make the recipe is removed from the ingredients list. User is informed of each ingredient that is removed.
<br/><br/>
3. Test Case: `make 0`
Expected: Error message is shown and no changes are made to the ingredients list.
<br/><br/>
4. Other incorrect delete commands to try: `delete`, `delete abc`, `delete x`, `...` (where x is a large integer) <br>
Expected: Similar to previous.
<br/><br/>

### Recipe All

1. Test Case: `recipe all`
Expected: All 6 stored recipes are displayed

2. Test Case: `recipe list`
Expected: Error message for incorrect command is shown.

3. Other incorrect commands to try: `recipe list all`, `recipe show all`, `recipe show` etc.  
Expected: Error message for incorrect command is shown.

### Recipe Detail

1. Test Case: `recipe 1`
Expected: Details for recipe 1 (Beef Burger) are shown with all ingredients and instructions.

2. Test Case: `recipe 0`
Expected: Error message for invalid recipe is shown.

3. Other test cases: `recipe <index_number>` where `<index_number>` is 1-6 inclusive.
Expected: Respective recipes are displayed correctly with all ingredients and instructions.

4. Incorrect test cases to try: `recipe <x>` where `<x>` is either out of bounds of 1-6 or an invalid flag (ie. a string or a decimal)
Expected: Error message for invalid recipe is shown.

### Saving Data
1. Dealing with corrupted data file
   * To simulate a corrupted data file, modify the format of the data stored in `ingredients.txt` file. <br>
   Expected: MealCompanion to throw error message notifying users of corrupted data file, program still runs normally.

### Command lists for testing  
Please ensure that your ingredients list is in the correct and/or is currently storing the ingredient(s) for the commands to be tested successfully, more details are in the table. 

| Correct Command | Expected behaviour |
| --------------- | ------------------ |
| `add apple /qty 3` | Success message is printed, 3 apples added to ingredients list and `ingredients.txt` |
| `remove ground apple /qty 1` | Success message is printed, 1 apple removed from ingredients list and `ingredients.txt` (please ensure you have performed the previous command first) |
| `ingredients list` | All stored ingredients listed out |
| `ingredients search apple` | Output shows the valid ingredient and the unit of measurement (if applicable) |
| `ingredients search` | Returns list of all valid ingredients |
| `clear` | Deletes all stored ingredients |
| `allergen add egg` | Allergy to egg added, any recipes containing eggs will not be shown to users |
| `allergen remove egg` | Allergy to egg removed, any recipes containing eggs will now be shown to users (please ensure you have performed the previous command first) |
| `allergen list` | Shows list of all ingredients that user is allergic to |
| `recipe all` | Shows all stored recipes in a list |
| `recipe 1` | Shows details of recipe 1 (Beef Burger), replace with any number 1-6 inclusive to view other recipes |
| `recipe possible` | Shows recipes which users can make with their stored ingredients, will omit recipes that contain allergens |
| `recipe random` | Shows a random recipe, does not omit recipes containing allergens |
| `recipe find chicken` | Returns a list of recipes whose names contain the keyword - in this case chicken |
| `recipe need 2` | Shows users which ingredients are lacking for the specified recipe - in this case recipe 2 (cup of water) |
| `recipe almost` | Shows users which recipes are only lacking 3 or fewer ingredients |
| `make 1` | Consumes the required amount of ingredients to make the specified recipe, deleting them from the ingredients list - in this case recipe 1 (Beef Burger) (please ensure you have first added all the required ingredients, please use `recipe 1` or `recipe need 1` before using `make 1`) |
| `recipe favourite 1` | Favourites the specified recipe and outputs a star next to that recipe when `recipe all` is used - in this case recipe 1 (Beef Burger) |
| `recipe unfavourite 1` | Unfavourites the specified recipe and removes the star in `recipe all` - in this case recipe 1 (Beef Burger) |
| `help` | Outputs list of commands and explanations | 
| `hello world` | Outputs first easter egg hint |
| `hello psle` | Outputs easter egg puzzle |
| `hello <puzzle_answer>` | Solve the puzzle and input it in `<puzzle_answer>` in the format specified in the terminal, and it will output our first easter egg |
| `hello world walt` | Outputs second easter egg |
  
For more detailed examples of commands, refer to our [User Guide](https://ay2223s2-cs2113t-t09-3.github.io/tp/UserGuide.html). 

###### [Back to the top](#developer-guide)
