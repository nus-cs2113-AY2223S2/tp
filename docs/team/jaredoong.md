# Jared Oong - Project Portfolio Page

## Overview

Meal360 is a desktop app for managing your recipes and weekly meal plans, optimized for use via a
Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If
you can type fast, Meal360 can get your recipe management tasks done faster than traditional GUI
apps.

### Summary of Contributions

* __Code
  contributed__ : [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=jaredoong&breakdown=true)
* __Functions/enhancements implemented__:
    * Added viewing of ingredients needed for a recipe based on index in recipe
      list. ([#15](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/15))
    * Added functionality to add single/multiple recipes to the weekly
      plan. ([#30](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/30),
      [#48](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/48))
    * Added functionality to remove single/multiple recipes from the weekly
      plan. ([#30](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/30),
      [#48](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/48))
    * Added functionality to view the weekly
      plan. ([#30](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/30))
    * Added functionality to mark recipes in the weekly plan as
      done. ([#74](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/74))
    * Added functionality to add user ingredients to the ingredient
      list. ([#64](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/64))
    * Added functionality to remove user ingredients from the ingredient
      list. ([#64](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/64))
    * Added functionality to view the ingredient
      list. ([#64](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/64))
    * Added loading and saving to
      databases. ([#39](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/39))
* __Contributions to the UG__ ([#30](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/30),
  [#74](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/74)):
    * Added documentation for the following commands:
        * Recipe related: `view INDEX`
        * Weekly meal plan related: `weekly /add RECIPE_NAME QUANTITY`
          , `weekly /multiadd [/r RECIPE_NAME /q QUANTITY]`
          , `weekly /delete RECIPE_NAME`, `weekly /multidelete [/r RECIPE_NAME /q QUANTITY]`
          , `weekly /done RECIPE_NAME`, `weeklyplan`
        * Ingredient related: `view_ingredients`, `add_i /n NAME /c COUNT /d DATE`
          , `del_i /n NAME /c COUNT`
* __Contributions to the DG__ ([#48](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/48)
  , [#123](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/123)
  ,[#129](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/129)):
    * Updated DG with anchor links for easier navigation.
    * Added documentation for the overall Architecture.
    * Added documentation for the following components:
        * `Meal360`, `RecipeList`, `IngredientList`,
          `WeeklyPlan`, `Database`
    * Added documentation for the following classes:
        * `Database`, `WeeklyPlan`
    * Added documentation for the following implementations:
        * Add Ingredient Feature
        * Delete Ingredient Feature
        * List Ingredient Feature
        * Edit Weekly Meal Plan Feature
        * List Weekly Plan Feature
        * Mark Recipe as Done Feature
* __Contributions to the team-based tasks__:
    * Setting up of GitHub team org/repo
    * Maintaining issue tracker
    * Release management (`v1.0`, `v2.0`)
* __Review/mentoring contributions__:
    * [#14](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/14),
      [#26](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/26),
      [#29](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/29),
      [#31](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/31),
      [#32](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/32),
      [#34](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/34),
      [#35](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/35),
      [#45](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/45),
      [#47](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/47),
      [#51](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/51),
      [#60](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/60),
      [#67](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/67),
      [#68](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/68),
      [#69](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/69),
      [#70](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/70),
      [#71](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/71),
      [#121](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/121),
      [#122](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/122),
      [#130](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/130),
      [#133](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/133)
* __Tools__:
    * Integrated third party library (GSON) to save and load data from
      databases. ([#39](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/39))