# User Guide

## Introduction

Meal360 is a desktop app for managing your recipes, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Meal360 can get your recipe management tasks done faster than traditional GUI apps.

___

## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Down the latest version of `Duke` from [here](http://link.to/duke).

___

## Features

* View recipes
* Listing relevant recipes
* Add recipes to weekly plan
* Remove recipes from weekly plan
* View weekly plan

### Listing recipes: `list`

List all recipes or filtered list recipes by the name or ingredients.

Format: `list [KEYWORD & KEYWORD & ...]`

* The `KEYWORD` is optional. It can be a name of recipe or ingredients.
* To list all recipes, do not add `KEYWORD`.
* Use `&` to list all the recipe that contain all specified keywords.

Example of usage:

`list`
`list pizza`
`list milk & egg`

### Viewing recipes: `view`

Views the list of ingredients and their quantities for a recipe.

Format: `view INDEX`

* Views the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1,2,3, ...

Example of usage:

`view 1`

### Deleting recipes: `delete`

Deletes one, a range, or all recipes currently in list.

Format: `delete INDEX/RANGE` or `delete r/NAME`

* Deletes the recipe(s) specified by the user either through the recipe index or name.
* The index refers to the index number shown in the displayed person list.
* The name refers to the recipe name in the list.

Example of usage:

`delete 1`
`delete 1-3`
`delete r/pizza`
`delete r/all`


### Adding to this week's plan: `weekly /add`

Adds an existing recipe to this week's plan.

Format: `weekly /add RECIPE NAME QUANTITY`

* Adds the specified `RECIPE NAME` to this week's plan`[QUANTITY]` number of times.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.
* The quantity **must be a positive
  integer** 1,2,3, ...

Example of usage:

* `weekly /add pizza 2` adds pizza to this week's plan twice.
* `weekly /add burger 1` adds burger to this week's plan once.

### Deleting from this week's plan: `weekly /delete`

Deletes an existing recipe from this week's plan.

Format: `weekly /delete RECIPE NAME`

* Deletes the specified `RECIPE NAME` to this week's plan.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.

Example of usage:

* `weekly /remove pizza` removes pizza from this week's plan.

### View this week's plan: `weeklyplan`

View this week's plan.

Format: `weeklyplan`

### Exit the program: `bye`

Exits the program.

Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary
| Action | Format, Examples                      | 
|--------|---------------------------------------|
|List recipe | `list [KEYWORD]`<br/>e.g `list pizza` |
|View recipe | `view INDEX`<br/>e.g `view 1` |
|Add to weekly plan | `weekly /add RECIPE NAME QUANTITY`<br/>e.g `weekly /add pizza 2` |
|Delete from weekly plan | `weekly /delete RECIPE NAME`<br/>e.g `weekly /delete pizza` |
|View weekly plan | `weeklyplan` |
