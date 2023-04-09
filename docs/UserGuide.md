# User Guide


## Introduction

Meal360 is a desktop app for managing your recipes and weekly meal plans, optimized for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If
you can type fast, Meal360 can get your recipe management tasks done faster than traditional GUI
apps.
* [Quick Start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)
___

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Meal360`
   from [here](https://github.com/AY2223S2-CS2113-F10-3/tp/releases).
3. Upon the first launch, the app will create a `database` folder in the same directory as the
   `tp.jar` file. This folder will contain all the data files that the app will use. Please do not
   touch these files to ensure smooth operation of the app.
4. To allow for immediate testing of features, the app comes preloaded with 10 recipes.

___

## Features

* [Add recipes](#adding-recipes--add)
* [Edit recipes](#editing-recipes--edit)
* [Delete Recipes](#deleting-recipes--delete)
* [View recipes](#viewing-recipes--view)
* [List relevant recipes](#listing-recipes--list)
* [Tag/Categorise recipes](#taggingcategorising-recipes--tag)
* [Add single recipe to weekly plan](#add-single-recipe-to-weekly-plan--weekly-add)
* [Add multiple recipe to weekly plan](#add-multiple-recipes-to-weekly-plan--weekly-multiadd)
* [Remove single recipe from weekly plan](#delete-single-recipe-from-weekly-plan--weekly-delete)
* [Remove multiple recipe from weekly plan](#delete-multiple-recipes-from-weekly-plan--weekly-multidelete)
* [Clear weekly plan](#clear-weekly-plan--weekly-clear)
* [Mark recipe in weekly plan as done](#mark-recipe-in-weekly-plan-as-done--weekly-done)
* [View weekly plan ingredients](#view-weekly-plan-ingredients--weeklyingredients)
* [View user ingredients](#view-user-ingredients--viewingredients)
* [View weekly plan](#view-weekly-plan--weeklyplan)
* [Random a recipe](#random-a-recipe--random)
* [Exit program](#exit-the-program--bye)
* [Add user ingredient](#add-user-ingredient--addi)
* [Delete user ingredient](#delete-user-ingredient--deli)

### HOW TO ADD INGREDIENTS TO A RECIPE:

* Please follow the below-mentioned format.

Format: `ingredient1_name=ingredient1_quantity ingredient2_name=ingredient2_quantity ...`

* Examples: `chicken=100 oil and sauce=200 vegetables=300`
  `meat=200 salt and spices=400`
* Type the ingredient name followed by equal sign and quantity in positive integer values.
* After the ingredients are key-in in, please type `done` in the next line to finish the process.
  *  **exception:** for editing ingredients partially, you just have to key in the 1 new ingredient and the recipe 
    manager knows that you have entered 1 ingredient. No need to type `done`.

<br>

### Adding recipes: `add`

Add new recipes to your list.

Format: `add /r [RECIPE_NAME]`

* `RECIPE_NAME` is basically a string.
* Type the recipe name after typing `/r `.

Example of usage:

`add /r chicken rice`
`add /r noodles`

<br>

### Editing recipes: `edit`

Edit recipes in your list partially, fully or add new ingredients to already existing recipe.

Format: `edit /r [RECIPE_NAME]`

* `RECIPE_NAME` is basically a string.
* Type the recipe name after typing `/r `.
* If you want to edit fully: press 1, edit partially: press 2, or add new ingredients: press 3.
* Follow the proper format while editing/adding ingredients.

Example of usage:

`edit /r chicken rice`
`edit /r noodles`

<br>

### Listing recipes: `list`

List all recipes or filtered list recipes by the name or ingredients.

Format: `list [/t] [KEYWORD && KEYWORD && ...]`

* The `KEYWORD` and `/t` are optional.
* `KEYWORD` can be a name of recipe or ingredients or tag.
* If user want to list recipes from tags `/t` is required. When `/t` is used,
  at least one `KEYWORD`, replacing by the tag name, is required.
* To list all recipes, do not add `KEYWORD` and `/t`.
* Use `&&` to list all the recipe that contain **all** specified `KEYWORD`.

Example of usage:

* `list` lists all the recipes.
* `list pizza` lists recipes that contain 'pizza' in the name or ingredients.
* `list milk && egg` lists recipes that contain __both__ 'milk' and 'egg' in the
  name or ingredients.
* `list /t western` lists the recipes that are in 'western' tag.
* `list /t western && chinese` lists recipes that is under __both__ 'western' and
  'chinese' tag.

<br>

### Viewing recipes: `view`

Views the list of ingredients and their quantities for a recipe.

Format: `view INDEX`

* Views the recipe at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1,2,3, ...

Example of usage:

`view 1`
`view 8`

<br>

### Deleting recipes: `delete`

Deletes one, a range, or all recipes currently in list.

Format: `delete INDEX/RANGE` or `delete /r NAME`

* Deletes the recipe(s) specified by the user either through the recipe index or name.
* The index refers to the index number shown in the displayed person list.
* The name refers to the recipe name in the list.

Example of usage:

`delete 1`
`delete 1-3`
`delete /r pizza`
`delete /r all`

<br>

### Tagging/Categorising recipes: `tag`

__Categorize recipes into a specific tag__

Adding one or more recipes into a tag, so that users can organize the recipes into categories

Format: `tag LABEL << [RECIPE_NAME && RECIPE_NAME && ...]`

* `LABEL` is required, and replace with the tag label that user want to
  add the recipes into. This is case-sensitive.
* At least one `RECIPE_NAME` is required.
* To add multiple recipes into the tag, use `&&` followed by next `RECIPE_NAME`.

Example of usage:

* `tag western << burger` adds burger into 'western' tag.
* `tag junk foods << burger && hotdog` adds burger and hotdog into 'junk foods' tag.
* `tag breakfast << milk && boiled egg && bread` adds milk, boiled egg and
  bread into 'breakfast' tag.

__Removing recipes from a tag__

Remove recipes from a specific tag.

Format: `tag LABEL >> [RECIPE_NAME && RECIPE_NAME && ...]`

* `LABEL` is required, and replace with the tag label that user want to
  remove the recipes from. This is case-sensitive.
* At least one `RECIPE_NAME` is required.
* To remove multiple recipes from the tag, use `&&` followed by next `RECIPE_NAME`.

Example of usage:

* `tag western>> burger` removes burger from 'western' tag.
* `tag junk foods >> burger && hotdog` remove burger and hotdog from 'junk foods' tag.
* `tag breakfast >> milk && boilded egg && bread` removes milk, boiled egg, and bread
  from 'breakfast' tag.

<br>

### Add single recipe to weekly plan: `weekly /add`

Adds an existing recipe to this week's plan.

Format: `weekly /add RECIPE_NAME QUANTITY`

* Adds the specified `RECIPE_ NAME` to this week's plan`QUANTITY` number of times, with quantity
  representing the number of days the user plans to prepare the recipe within the week.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.
* The quantity **must be a positive
  integer** between 1 and 1000.

Example of usage:

* `weekly /add pizza 2` adds pizza to this week's plan twice.
* `weekly /add burger 1` adds burger to this week's plan once.

<br>

### Add multiple recipes to weekly plan: `weekly /multiadd`

Adds multiple existing recipe to this week's plan.

Format: `weekly /multiadd [/r RECIPE_NAME /q QUANTITY]`

* Adds the specified `RECIPE NAME` to this week's plan`QUANTITY` number of times.
* At least one pair of `RECIPE_NAME` and `QUANTITY` is required.
* Each `RECIPE_NAME` and `QUANTITY` requires `/r` and `/q` before it respectively.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.
* The quantity **must be a positive
  integer** between 1 and 1000.
* If the same recipe is specified multiple times, only the **last** quantity specified
  will be used.

Example of usage:

* `weekly /multiadd /r pizza /q 2 /r burger /q 9` adds pizza twice and burger once to this week's
  plan.

<br>

### Delete single recipe from weekly plan: `weekly /delete`

Deletes an existing recipe from this week's plan.

Format: `weekly /delete RECIPE_NAME QUANTITY`

* Deletes the specified `RECIPE_NAME` from this week's plan `QUANTITY` number of times.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.
* The quantity **must be a positive
  integer** between 1 and 1000.
* Indicating a quantity more than the current quantity deletes the recipe completely from
  this week's plan.

Example of usage:

* `weekly /delete pizza 1` removes pizza from this week's plan once.

<br>

### Delete multiple recipes from weekly plan: `weekly /multidelete`

Deletes multiple existing recipe from this week's plan.

Format: `weekly /multidelete [/r RECIPE_NAME /q QUANTITY]`

* Deletes the specified `RECIPE_NAME` from this week's plan`QUANTITY` number of times.
* At least one pair of `RECIPE_NAME` and `QUANTITY` is required.
* Each `RECIPE_NAME` and `QUANTITY` requires `/r` and `/q` before it respectively.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.
* The quantity **must be a positive
  integer** between 1 and 1000.
* Indicating a quantity more than the current quantity deletes the recipe completely from
  this week's plan.
* If the same recipe is specified multiple times, only the **last** quantity specified
  will be used.

Example of usage:

* `weekly /multidelete /r pizza /q 2 /r burger /q 9` deletes pizza twice and burger once from this
  week's
  plan.

<br>

### Clear weekly plan: `weekly /clear`

Clears this week's plan by removing all recipes listed in weekly plan.

Format: `weekly /clear`

<br>

### Mark recipe in weekly plan as done: `weekly /done`

Mark a recipe in the weekly plan as completed. A single count of the recipe and its corresponding
ingredients will be removed from the weekly plan and the list of ingredients.

Format: `weekly /done RECIPE_NAME`

* This command executes only if the user has sufficient ingredients to prepare the recipe.
* This command executes only if the recipe is in the weekly plan.
* The recipe name refers to the name of the recipe shown in the displayed recipe list.

Example of usage:

* `weekly /done pizza` will mark pizza as done in the weekly plan, assuming the user has `pizza` in
  the weekly plan and there are sufficient ingredients.

<br>

### View weekly plan: `weeklyplan`

View this week's plan.

Format: `weeklyplan`

<br>

### View weekly plan ingredients: `weeklyingredients`

View this week's ingredients.

Format: `weeklyingredients`

<br>

### Random a recipe: `random`

Random a recipe from all the recipes that user have, and show the list of
ingredients and their quantities for a recipe.

Format: `random`

<br>

### Exit the program: `bye`

* Exits the program.
* If any ingredients are added by the user, they get saved in JSON files for usage the next time.

Format: `bye`

<br>

### Add user ingredient: `add_i`

Add user's ingredients into the ingredient list.
Format : `add_i /n INGREDIENT_NAME /c QUANTITY /d DATE`

* The quantity **must be a positive
  integer** between 1 and 1000.
* The date **must be in the format of DD/MM/YYYY**
* If the ingredient already exists in the list, the quantity will be added to the existing quantity,
  while the expiry date will be updated to reflect the expiry date of the new addition.

Example of usage:

* `add_i /n chicken /c 1 /d 01/01/2020` adds chicken with quantity 1 and expiry date 01/01/2020

<br>

### Delete user ingredient: `del_i`

Delete user's ingredients from the ingredient list.
Format : `del_i /n INGREDIENT_NAME /c QUANTITY`

* The quantity has to be **less than or equal** to the quantity of the ingredient in the list.
* The quantity **must be a positive
  integer** between 1 and 1000.
* Indicating a quantity more than the current quantity deletes the ingredient completely from
  the list.

Example of usage:

* `del_i /n chicken /c 1` deletes chicken with quantity 1

<br>

### View user ingredients: `view_ingredients`

View user's ingredients from the ingredient list.
Format : `view_ingredients`

---

## Command Summary

| Action                             | Format & Examples                                                                                           | 
|------------------------------------|-------------------------------------------------------------------------------------------------------------|
| Add recipe                         | `add /r [RECIPE_NAME]`<br/>e.g `add /r Chicken Rice`                                                        |
| Edit recipe                        | `edit /r [RECIPE_NAME]`<br/>e.g `edit /r Chicken Rice`                                                      |
| List recipe                        | `list [/t] [KEYWORD]`<br/>e.g `list pizza`                                                                  |
| View recipe                        | `view INDEX`<br/>e.g `view 1`                                                                               |
| Add tag/Categorise to recipes      | `tag LABEL << RECIPE_NAME`<br/>e.g `tag western << pizza`                                                   |
| Remove tag/Categorise from recipes | `tag LABEL >> RECIPE_NAME`<br/>e.g `tag western >> pizza`                                                   |
| Random a recipe                    | `random`                                                                                                    |
| Add to weekly plan                 | `weekly /add RECIPE_NAME QUANTITY`<br/>e.g `weekly /add pizza 2`                                            |
| Add multiple to weekly plan        | `weekly /multiadd [/r RECIPE_NAME /q QUANTITY]` <br/>e.g `weekly /multiadd /r pizza /q 1 /r burger /q 3`    |
| Delete from weekly plan            | `weekly /delete RECIPE_NAME`<br/>e.g `weekly /delete pizza`                                                 |
| Delete multiple from weekly plan   | `weekly /multidelete [/r RECIPE_NAME /q QUANTITY]` <br/>e.g `weekly /multiadd /r pizza /q 1 /r burger /q 4` |
| Clear weekly plan                  | `weekly /clear`                                                                                             |
| Mark recipe in weekly plan as done | `weekly /done RECIPE_NAME`<br/> e.g `weekly /done pizza`                                                    |
| View weekly plan                   | `weeklyplan`                                                                                                |
| View weekly ingredients            | `weeklyingredients`                                                                                         |
| View user ingredients              | `view_ingredients`                                                                                          |
| Add user ingredient                | `add_i /n NAME /c COUNT /d DATE` <br/>e.g `add_i /n Chicken Rice /c 100 /d 04/09/2023`                      |
| Delete user ingredient             | `del_i /n NAME /c COUNT` <br/>e.g `delete_i /n Chicken Rice /c 50`                                          |
