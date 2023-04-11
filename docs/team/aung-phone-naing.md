# Aung Phone Naing - Project Portfolio Page

## Overview
Taste of Mom's (TOM) is a **desktop recipe manager application for managing recipes**, optimized for use via a Command Line Interface (CLI).

Our application will help students to **keep track and modify recipes** which they can learn to cook, so that they can enjoy the same taste of food made like as if they were at home as they live on campus.
It also helps them filter recipes with ease.

## Summary of Contributions
Responsible for writing out the code base, basic structure of the app, setting up organization repository and managing issues tracker.

### Code Contributed
[RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=Aung%20Phone%20Naing&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements Implemented
The following are my contributions to the project:

* ### Skeletal Code Implementation
  * **Parser, Command and Command Type Classes**
    * **Description**: Provide the underlying methods and attributes necessary for developers to use and add on to,
                       which can help with identifying and handling user commands. 
    * **Justification**: These classes are the core of the program and central to standardising the way to handle user 
                         inputs, providing a detailed structure in terms of executing various tasks.
    * **Highlights**: 
      * The implementation requires a deep analysis of design alternatives to determine the best way to construct the
        classes while ensuring they achieve the functional requirements.
  * **Recipe and Recipe List Classes**
    * **Description**: Provide the underlying methods and attributes necessary for developers to use and add on to,
                       which can help with managing of components in a recipe and the list of all recipes in the program.
    * **Justification**: These classes are central to allowing users to create and manage recipes which make up the 
                         essence of the app itself.
    * **Highlights**:
      * The classes provided as a base for which other developers use to expand to construct subclasses and attributes
        to add further features to the program such as `StepList`, `Step`, `IngredientList` and `Ingredient`.
  * **UI Class**
    * **Description**: Provide methods and attributes to be used by other developers when it comes to handling user interactions.
    * **Justification**: This provides a central platform in which developers can construct general methods for handling
                         similar user interactions which can be used across features.
    * **Highlights**:
      * The class ensures that there is a central location in which code can be easily refactored to handle any errors
        in regard to user interactions.
  
* ### New Features 
  * **View-name Command**
    * **Description**: Provide users with the ability to view the contents of a specific recipe by simply using the
      recipe name instead of index.
    * **Justification**: This command provides users with greater convenience to view recipes that they already know the
      name to so that they do not need to use the `Find` Command to get the recipe index before viewing.
    * **Highlights**:
        * This command requires comprehension of and working with code by other developers.
        * A good comprehension of users' needs in order to determine the significance of this feature was required as well.
  * **Invalid input handling for Add Command**
    * **Description**: Catches any instance of users attempting to input invalid characters into recipe names, tags,
                       ingredients and steps. 
    * **Justification**: Such instances of inputting special characters "!@#$%" into names may potentially mess with 
                         `Storage` or `Parser` class methods.
    * **Highlights**: 
      * This prevents attempts by malicious users to crash the program or unintentional inputs leading to program termination. 

* ### Testing 
    Wrote tests for the following features:
  * View
  * Parser for adding a recipe

### Contribution to Team-based Task

1. Came up with the user stories.
2. Managed releases v2.1 on GitHub.
3. Managed issue tracker on GitHub. (e.g. adding labels, assigning issues to team members, closing issues, etc)
4. Solving issues on Github: [#4](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/4) , [#7](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/7) , [#47](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/47) , [#48](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/48) , [#49](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/49) , [#65](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/65) , [#68](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/68) , [#72](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/72) , [#75](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/75) , [#76](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/76), [#84](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/84) , [#87](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/87) , [#91](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/91), [#126](https://github.com/AY2223S2-CS2113-F13-1/tp/issues/126)

### Documentation
* **Contributions to User Guide**
    
    Wrote the following segments of the user guide:
  * `View` Feature    
  * `Help` Feature


* **Contributions to Developer Guide**

  Wrote the following segments of the developer guide:
  * `Help` Feature
  * Instructions for Manual Testing
  * Sequence Diagrams for `View` and `Help` Features
  * Class Diagrams for `Help` and `Command` Classes

### Review/Mentoring Contributions
* PRs reviewed (with non-trivial comments) : [#116](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/116) , [#115](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/115) , [#6](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/6)
* PRs reviewed (all others) : [#1](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/1) , [#2](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/2) , [#27](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/27) , [#30](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/30) , [#38](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/38) , [#40](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/40) , [#45](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/45) , [#55](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/55) , [#57](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/57) , [#59](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/59) , [#107](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/107) , [#108](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/108) , [#114](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/114) , [#123](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/123) , [#131](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/131), [#133](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/133) , [#135](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/135) , [#142](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/142) , [#143](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/143) , [#148](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/148) , [#152](https://github.com/AY2223S2-CS2113-F13-1/tp/pull/152)

### Contributions beyond the Project Team
* [WELLNUS++](https://github.com/Aung-Phone-Naing/ped/issues)