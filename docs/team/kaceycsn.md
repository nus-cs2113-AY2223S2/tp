# Kacey Chew - Project Portfolio Page

## Overview
PocketPal is a money-management tool that allows you to record your financial transactions using the command-line interface.
It allows users to quickly log and access their expenses, and categorize them by type. Users can also easily filter expenses based on date, category and price range.
It offers automatic saving and loading of data to ensure data persistence. Overall, PocketPal is a convenient tool for managing your expenses.


### Summary of Contributions
**Code contributed:** [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=kaceycsn)

**Features implemented**
1. Implemented add feature
    - What it does: Allows user to add an entry into their account.

    - Justification: The add feature is a core functionality of PocketPal. Upon being able to add entries into PocketPal, users will then be able to access the other features of PocketPal to manage their account.

    - Highlights: This feature extends from abstract `command` class to prevent code duplication. The challenge with implementing this feature comes from having to keep track of the different categories that the entry can fall under. Therefore, `Category` enum is used to keep track of the category that each entry belong to.


2. Implemented delete feature
    - What it does: Allows user to remove an entry from their account

    - Justification: The delete feature is a core functionality of Pocketpal, and is essential for users to manage their account.

    - Highlights: This feature extends from abstract `command` class to prevent code duplication.


3. Improved on help feature
    - What it does: Break down help guide into a general help menu and specific help guides for each command
        - General help menu: Displays a list of all supported commands and a brief overview of their function.
        - Specific help guides: Displays more detailed help message with examples to allow user to get further assistance on specific commands.

    - Justification: Improves the clarity of help message displayed and reduces user's dependency on the user guide.

    - Highlight: This feature extends from abstract `command` class to prevent code duplication. The challenge with implementing this feature is organising the help message constant for each command in a neat and readable manner.


4. Wrote test cases for `AddCommand`, `DeleteCommand`, `HelpCommand`. Partially wrote some test cases for `ViewCommand`.


5. Fixed the following bugs/issues: #152, #144, #198


**Contributions to the UG**
- Added brief introduction and quick start guide (PR #71)

**Contributions to the DG**

- Added implementation steps for add, delete and help features
- Illustrated sequence diagrams and activity diagrams for add delete and help features
