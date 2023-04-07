# Leong Huen Weng's contribution to Pocket Pal

PocketPal is a user-friendly money-management app that makes it easy to track expenses. With a command-line interface, users can quickly log and access their expenses, and categorize them by type. Users can also easily filter expenses based on date, category and price range. Overall, PocketPal is a convenient tool for managing your expenses.

Stated below are my contributions to the project.
-  **New feature**: Added the ability to edit an entry that is currently in the EntryLog. 
    - What it does: It modifies a current entry using the id specified by the user. The user is also required to
      provide the final values of the fields that they want to modify.
    - Justification: This feature allows the user to directly rectify any mistakes in adding entries in 1 command, 
      instead of deleting the entry and adding it back (2 commands).
    - Highlights: To modify the EntryLog, a request has to be instantiated with the parameters given by the user. Upon 
      successful modification of entries in Backend, a response will be received with the details of the modified entry.
      These details will then be printed by the UI to the user. It was challenging to understand the roles of all the 
      classes involved in this entire process.
    - Credits: Most methods called here are custom methods defined in classes that are created by the members of our 
      team.

- **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=leonghuenweng)

- **Project Management**:
  - tagged PR appropriately with the issues that are solved by said PR (#PR 214) 

- **Enhancements to existing features**:
   - Added additional filter by date function for view command (PR #128) 

- **Documentation**:

- **Community**:

- **Tools**: