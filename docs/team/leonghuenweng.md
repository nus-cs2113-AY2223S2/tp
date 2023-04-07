# Leong Huen Weng's contribution to Pocket Pal

PocketPal is a user-friendly money-management app that makes it easy to track expenses. With a command-line interface, users can quickly log and access their expenses, and categorize them by type. Users can also easily filter expenses based on date, category and price range. Overall, PocketPal is a convenient tool for managing your expenses.

Stated below are my contributions to the project.
-  **New feature**: Added the ability to edit an entry that is currently in the EntryLog. 
    - What it does: It modifies a current entry using the id specified by the user. The user is also required to
      provide the final values of the fields that they want to modify.
    - Justification: This feature allows the user to directly rectify any mistakes in adding entries in 1 command, 
      instead of deleting the entry and adding it back (2 commands).
    - Highlights: To modify the EntryLog, the edit Command had to invoke the methods of multiple classes such as Backend
      , Requests, Response and UI. It was challenging to understand this entire process since there were so many classes
      involved.
    - Credits: Most methods called here are custom methods defined in classes that are created by the members of our 
      team.

- **New feature**: Added the ability to view entries that fulfill criteria set by users.
    - What it does: It prints the latest N entries in the specified category.
    - Justification: This helps users understand their expenditure across different aspects (Food, transport etc) 
      separately.

- **New feature**: Added the help command.
    - What it does: Not only does it provide the general format for user inputs, it also displays example command usage 
      to guide beginners who have just started using the app.
    - Justification: Not only will this help beginners familiarise themselves with the commands, it will also make it 
      convenient for acceptance testers.

- **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=leonghuenweng)

- **Project Management**:
  - tagged PR appropriately with the issues that are solved by said PR (#PR 214) 

- **Enhancements to existing features**:
   - Added additional filter by date function for view command (PR #128) 

- **Documentation**:
   - User Guide:
     - Added documentation for filter function of view command (PR #132)
     - Added a brief introduction of PocketPal (PR #213)
   - Developer Guide: 
     - Added implementation steps of view, help and exit commands, along with some sequence and class diagrams. 
       (PR #132)
     - Added implementation steps, class diagram and sequence diagram for edit Command#116

- **Community**:
  - Identified bugs for Modganiser product [Modganiser](https://github.com/AY2223S2-CS2113T-T09-4/tp/issues)
