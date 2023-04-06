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
        * `view INDEX`
        * `weekly /add RECIPE_NAME QUANTITY`
        * `weekly /multiadd [/r RECIPE_NAME /q QUANTITY]`
        * `weekly /delete RECIPE_NAME`
        * `weekly /multidelete [/r RECIPE_NAME /q QUANTITY]`
        * `weekly /done RECIPE_NAME`
        * `weeklyplan`
        * `view_ingredients`
        * `add_i /n NAME /c COUNT /d DATE`
        * `del_i /n NAME /c COUNT`
* __Contributions to the DG__ ([#48](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/48)):
    * Updated DG with anchor links for easier navigation.
    * Added documentation for the following classes:
        * `Database`
        * `WeeklyPlan`
* __Contributions to the team-based tasks__:
    * Setting up of GitHub team org/repo
    * Maintaining issue tracker
    * Release management (`v1.0`, `v2.0`)
* __Review/mentoring contributions (with non-trivial comments)__:
    * [#14](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/14),
      [#26](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/26),
      [#32](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/32),
      [#34](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/34),
      [#45](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/45),
      [#51](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/51),
      [#60](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/60),
      [#70](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/70)
* __Tools__:
    * Integrated third party library (GSON) to save and load data from
      databases. ([#39](https://github.com/AY2223S2-CS2113-F10-3/tp/pull/39))