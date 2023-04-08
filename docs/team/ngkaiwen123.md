# Ng Kai Wen's Project Portfolio Page

## Project: MagusStock
MagusStock is a Java command-line interface (CLI) application designed for inventory management.
Given below are my contributions to the project.

### Summary of Contributions
* **New Feature Addition**: Added the ability to `edit` item attributes (Name/Quantity/Price/Category)
  * What it does: Allows the user to change the attributes of an item stored in the inventory list to a value of
  their choosing, albeit within a set range of values.
  * Justification: This feature is absolutely essential to the workings of the program as mistakes in user inputs
  are inevitable. This feature aids in the rectification of such mistakes without total removal of the item from
  the inventory list.
  * Highlights: Implementation of this feature was challenging as many user input combinations have to be accounted
  for, due to the many optional input parameters that users get to choose from.
*  **New Feature Addition**: Added the ability to `restock` stock levels of an item.
    * What it does: Allows the user to restock an item found in the inventory list, by simply keying in the quantity to
    be added without manually editing the stock levels of an item.
    * Justification: This feature provides an easy way for users to track stock levels of an item after restocking,
    without the need to manually calculate the current stock levels of an item and then edit the quantity of an item.
    This not only reduces mistakes made by the user during calculation, but also forgoes the need to retrieve current
    stock levels for manual calculation.
* **New Feature Addition**: Added the ability to `sell` an item, thereby reducing the quantity of an item.
  * What it does: Allows the user to sell a fixed number of an item based on the user's input, thereby reducing
  the current stock levels of an item.
  * Justification: This feature promotes ease of use of the application as users no longer have to perform mental
  deductions and manually editing the stock levels of an item. Program automatically deducts the desired amount from
  the current item stock for the user.
* **Code Contributed**: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ngkaiwen123&breakdown=true)
* **Developer Guide (DG) Contributions:**
  * Created UML Sequence Diagrams for the Edit, Sell and Restock features of the application.
  * Provided documentation for the Edit, Sell and Restock features of the MagusStock application in the DG.
  * Contributed to User Stories, Value Proposition, Glossary, Target User Profile sections of the Developer Guide.
* **User Guide (UG) Contributions:**
  * Authored the Edit, Sell and Restock sections of the User Guide. 
  * Helped in the reformatting of features listed in the User Guide, to ensure consistency and standardization in
  elaborations/examples provided for each of the commands listed in the User Guide.
* **Project Management**:
  * Creation of Pull-Request to the nus-cs2113-AY2223S2/tp repository for the group.
* **Community:**
  * Reported a higher than average issue count during the PE Dry-Run, of which many critical bugs were found.