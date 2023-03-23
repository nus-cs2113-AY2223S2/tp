# Developer Guide

* [Acknowledgements](#acknowledgements)
* [Setting up, getting started](#setting-up-getting-started)
* [Design & implementation](#design--implementation)
    * [Architecture](#architecture)
    * [UI Component](#ui-component)
    * [Parser Component](#parser-component)
    * [Recipe Component](#recipe-component)
    * [RecipeList Component](#recipelist-component)
    * [WeeklyPlan Component](#weeklyplan-component)
    * [Database Component](#database-component)

* [Appendix: Requirements](#appendix-requirements)
    * [Product scope](#product-scope)
    * [Target user profile](#target-user-profile)
    * [Value proposition](#value-proposition)
    * [User Stories](#user-stories)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)

---

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries --
include links to the original source as well}

---

## Setting up, getting started

---

## Design & implementation

* [Architecture](#architecture)
* [UI Component](#ui-component)
* [Parser Component](#parser-component)
* [Recipe Component](#recipe-component)
* [RecipeList Component](#recipelist-component)
* [WeeklyPlan Component](#weeklyplan-component)
* [Database Component](#database-component)

### Architecture

### UI Component

### Parser Component

### Recipe Component

### RecipeList Component

API: `RecipeList.java`
The `RecipeList` component:

* extends from `ArrayList<Recipe>`
* stores the recipes the user has added as `Recipe` objects
* allows users to add their own recipes
* allows users to delete the existing recipes
* allows users to edit the existing recipes
* allows users to view the entire list of recipes
* allows users to view the ingredients required for a recipe
* allows users to tag single or multiple recipes from the recipe list for filtering

### WeeklyPlan Component

API: `WeeklyPlan.java`

The `WeeklyPlan` component:

* extends from `HashMap<String, Integer>`
* stores the names of recipes that the user plans to prepare for the week as `String`
* stores the number of times the user plans to prepare each recipe as `Integer`
* allows users to add single or multiple recipes from the weekly plan
* allows users to delete single or multiple recipes from the weekly plan

How the `WeeklyPlan` component works:

1. When the user enters an input with the first word being `weekly`, the input is passed to
   the `Parser` component.
2. Based on the second argument, the `Parser` component will call the appropriate method in the
   `Parser` component.
3. `Parser` component then returns a `WeeklyPlan` object to indicate the recipe(s) to be added or
   deleted from the weekly plan.
4. `WeeklyPlan` component then uses either `addPlans` or `deletePlans` method to add or delete
   the recipe(s) from the weekly plan.

The sequence diagram below shows how the `WeeklyPlan` component works when the user
inputs `weekly /add burger 1`:
![](../docs/UML/WeeklyPlan/AddWeeklyPlanUML.png)

### Database Component

API: `Database.java`

The `Database` component:

* stores the recipes in a local database in json format
* loads up automatically upon startup of program
* saves automatically upon exit of program
* comes with a default database of 10 recipes for new users

How the `Database` component works at start up:

1. Upon starting up the program, the `Database` component will check for the existence of a
   database file in the local directory.
2. If the database file exists, the `Database` component will load the recipes from the database
   file into a `RecipeList` and return this `RecipeList`.
3. If the database file does not exist, the `Database` component will check if the parent directory,
   and create any missing directories and files as necessary.
4. If the database file does not exist or if the file was empty, the `Database` component will
   create a new `RecipeList`
   with 10 default recipes and return this `RecipeList`.

The activity diagram below shows how the `Database` component works at start up:
![](../docs/UML/Database/DatabaseStartupUML.png)
---

## Appendix: Requirements

### Product scope

### Target user profile

* has a need to manage a significant number of recipes
* has a need to manage a significant number of ingredients
* has a need to manage their meal plan on a weekly basis
* can type fast
* prefers typing to mouse input
* is reasonably comfortable using CLI apps

### Value proposition

Allows management of recipes, ingredients and weekly meal plan faster than a typical mosue/GUI
driven app.

### User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

### Non-Functional Requirements

{Give non-functional requirements}

### Glossary

* *glossary item* - Definition

### Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used
for testing}
