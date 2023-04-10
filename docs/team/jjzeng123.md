<link type="text/css" rel="stylesheet" href="docs/main.css" />

# Jackie - Project Portfolio Page

## Overview
Meal Companion is a recipe book which allows you to search by the ingredients you already have so that you can easily find recipes you can make. Meal Companion is a one-stop solution to cookbooks, ingredient tracking and meal preparation. Users interact with the app via the Command Line Interface

## Contributions
* New Feature: Added `recipe favourite` command  
    * Allows users to mark a recipe as favourite

* New Feature: Added `recipe unfavourite` command
    * Allows users to unmark a recipe as favourite

* New Feature: Implemented easter egg
    * `hello world` command reveals easter egg hint
    * `hello psle` command reveals easter egg puzzle
    * `hello <puzzle_answer>` reveals easter egg

* Other Enhancements:
    * Calorie counts
        - Implemented a calorie field in recipes, returning an integer indicating the amount of calories each recipe contains
    * Details of all 6 default recipes
        - Filled out all data pertaining to all 6 basic recipes in JSON files including steps, ingredients, and caloric content
        - Updated JSON file containing list of valid ingredients to include all ingredients from the recipes

Code Contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=Jjzeng123&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Jjzeng123&tabRepo=AY2223S2-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)  


### Documentation

#### User Guide
- Hyperlinks
    - Table of contents
    - "Back to table of contents" button at the end of every section
    - "Back to top" button
    - Back and forth navigability between "Allergens" and "Recipe Possible" section
    - Back and forth navigability within each section ie. Ingredients section has "Back to Ingredients" button for each section on the command
- Screenshots
    - High-fidelity screenshots for all features with edits to highlight user inputs
- Feature instructions
    - Wrote detailed step-by-step instructions for every command  
- Command summary table
- Modified CSS file to override formatting bug in deployed Github Pages
- Critical Warnings regarding malformed inputs to warn users
- Tips and tricks for certain features

#### Developer Guide
- Introduction
- Getting Started
- User Stories
- Manual Testing

### Contributions to team tasks
* Updated integer values of ingredients to double for decimals and fractional usage in an early iteration, then reverted to integer and subsequently updated it across all source files and all test files

### Community
* Found and reported various bugs as well as provided suggestions for other teams: [PED repo](https://github.com/Jjzeng123/ped/issues)
