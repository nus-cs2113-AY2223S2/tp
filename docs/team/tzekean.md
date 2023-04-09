# Ng Tze Kean - Project Portfolio Page

## Overview
DukeOfBooks is a Java Command-Line Interface (CLI) application that aims to provide NUS Computer Science student with easy and fast access to a library system to borrow books. GUI has been excluded as Computer Science students are known to type fast and they prefer a black console screen over bright GUIs.

Additionally, the program, beyond borrowing and returning books allows the user to search if a book exists and check if the books is on loan before borrowing the book. Like all librarys, there is a return date that books must be retuend to the library to avoid a fine by the school. A payment system is created to enforce this, where a student must first pay their fee before the book can be returned. If the student decides to not pay the fee then a very nasty outcome might ensue...

### Summary of Contributions
- Code contributed: [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=HiIAmTzeKean&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
- Added features:
    - Added base classes and packages [#7](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/7):
        - **What it does:** The base packages forms the general skeleton of the project for each member to work on
        - **Justification:** This feature the other team members to kick start the project with a guided structure. Some aspects like parser, ui and top logic module controls are added for a general architecture overview.
        - **Highlights:** Allows every member to be synchronised in our development phase while working towards the v1.0 milestone
    - Added Book package [#8](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/8):
        - **What it does:** Book model object
        - **Justification:** This feature allows other models to make use of the book object which is the core object of the entire application.
        - **Highlights:** Provides absraction of the Book object whereby other classes would use the public methods to operate on the Book. This allows for better separation of concerns in the design.
    - Added Inventory Controller [#40](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/40):
        - **What it does:** Logic controller for inventory operations
        - **Justification:** This feature works with other classes like *InventoryDetails* and *Inventory* to acts an overall controller to manage how the books are added/deleted in the library.
        - **Highlights:** Logic controller to enforce actions that can be taken by other classes on *Inventory* to ensure legal actions and checking done before operation on the the object.
    - Added Search Controller [#40](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/40):
        - **What it does:** Logic controller for search operation in library
        - **Justification:** This feature works by searching through the *Inventory* object created to find Book of matching Title or Topic string and returns all matches to the user.
        - **Highlights:** Allows every member to be synchronised in our development phase while working towards the v1.0 milestone
- Improved features:
    - Search and Librarian feature [#63](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/63), [#128](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/128):
        - Fixed buggy add action in Librarian feature
        - Added capability to search through use of Title and Topic together through ArrayList operations where the intersection of 2 list is returned
    - Class and sequence diagrams for documentation 
- User Guide:
    - Documentation for search and librarian feature [#55](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/55)
    - Quality checking of UG (alignment, truncating long examples) [#142](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/142)
- Developer Guide:
    - Architecture diagram of DukeOfBooks 
    - Sequence diagram of log-in
    - Class diagram of search, librarian, list, ...
- Team
    - Release management: [v1.0](https://github.com/AY2223S2-CS2113-F10-4/tp/releases/tag/v1.0)
- Community contributions
    - Provided code comment feedbacks
        - [MoneyMoover #26](https://github.com/nus-cs2113-AY2223S2/tp/pull/26)
        - [rainyDay #14](https://github.com/nus-cs2113-AY2223S2/tp/pull/14)
        - [MagusStock #39](https://github.com/nus-cs2113-AY2223S2/tp/pull/39)
    