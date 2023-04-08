# Leong Yat Pang - Project Portfolio Page

## Overview
Recipe Manager(RM) is a <strong>desktop recipe manager application for managing recipes, 
optimized for use via a Command Line Interface</strong>(CLI). 
It is written in Java, and has about 4 kLoC.

## Summary of Contributions

### Code Contributed
[Code Contributions](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=YatPang&tabRepo=AY2223S2-CS2113-F13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented
* **Saving Recipe List**<br>
Implemented the logic to save recipes in recipe list automatically after changes are made to the recipe list.
The app saves recipes in the `/data` directory as individual `.txt` files.
<br>
This is one of the basic features of the application.
* **Loading Saved Data**<br>
Implemented the logic to load recipes saved from previous sessions.
The app scans the `/data` directory for `.txt` files, 
and reads files individually to generate stored recipes.
<br>
This is one of the basic features of the application.
* **Searching Recipe List by Name**<br>
`findname [term]`<br>
Implemented the command and methods to search the recipe list for items with names that contain the searched term.
* **Searching Recipe List by Tag**<br>
Implemented the command and methods to search the recipe list for items with tags that contain the searched term.
* **Adding an Element to Recipe**<br>
Implemented the command and logic to add step and ingredients to an existing recipe on the recipe list.
* **Deleting an Element from Recipe**<br>
  Implemented the command and logic to delete step and ingredients to an existing recipe on the recipe list.

### Contributions to UG
Added user documentation to explain how to use the following commands:
* `findname`
* `findtag`
* `addtorecipe`
* `deletefromrecipe`

### Contributions to DG
* Added UML diagram and technical documentation to illustrate how recipe data is stored and loaded.