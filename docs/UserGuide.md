# User Guide

* **[1 Introduction](#1-introduction)**
   * [1.1 What is SEP Helper](#11-what-is-sep-helper)
   * [1.2 How to use this User Guide](#12-how-to-use-this-user-guide)
* **[2 Quick Start](#2-quick-start)**
* **[3 Features Overview](#3-features-overview)**
* [3.1 Help Command](#31-help-command--help)
   * [3.2 Modules](#32-modules)
     * [3.2.1 List](#321-list--list-command)
       * [3.2.1.1 List the Saved Modules](#3211-listing-modules-user-has-selected--list-current)
       * [3.2.1.2 List all the Partner Universities](#3212-listing-out-pu-module-list--list-pu-abbreviationpu-index)
       * [3.2.1.3 List the Modules from a Partner University](#3213-listing-out-all-partner-universities--list-pu)
       * [3.2.1.4 List the Saved Modules from a Partner 
University](#3214-listing-modules-user-has-selected-for-specific-partner-university--list-current-pu-abbreviation)
     * [3.2.2 Add](#322-add)
     * [3.2.3 Remove](#323-remove-user-selected-modules-from-list--remove)
     * [3.2.4 Search](#324-search-by-nus-module-code--search)
   * [3.3 Deadline](#33-deadlines)
     * [3.3.1 List the Deadlines](#331-list-all-the-deadlines-saved--deadlinelist)
     * [3.3.2 Add Deadlines](#332-add-a-new-deadline--deadlineadd)
     * [3.3.3 Remove Deadlines](#333-remove-a-deadline-from-the-list--deadlineremove)
   * [3.4 Budget Planner](#34-budget-planner--budget)
     * [3.4.1 Edit the Budget](#341-budget---edits-the-budget)
     * [3.4.2 Edit the Accommodation Cost](#342-accommodation---edits-the-accommodation-cost)
     * [3.4.3 Edit the Airplane Ticket Cost](#343-airplane---edits-the-airplane-ticket-cost)
     * [3.4.4 Edit the Food Cost](#344-food---edits-the-food-cost)
     * [3.4.5 Edit the Entertainment Cost](#345-entertainment---edits-the-entertainment-cost)
     * [3.4.6 View the Budget Plan](#346-view---views-the-entire-budget-plan)
* **[4 FAQ](#4-faq)**
* **[5 Command Summary](#5-command-summary)**

---
## 1 Introduction
### 1.1 What is SEP Helper
SEP Helper is a desktop application for Mechanical Engineering students, studying at the
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).
---
### 1.2 How to Use this User Guide

---
## 2 Quick Start

1. Ensure that you have Java 11 or above installed in your computer, if not proceed to download from the link:
   https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html
2. Download the latest version of `SEPHelper.jar` from [here](http://link.to/duke).
3. Using command prompt type `"java -jar [FILE PATH OF JAR]"` to start app.
4. Type the command in the terminal and press enter to execute it. E.g. entering `/help` will show the help page

---
## 3 Features Overview

#### Notes:
* **Command words**:
  * Command words are not case-sensitive.
    * E.g. `eXIT` will be accepted as the `exit` command.
    * 
### 3.1 Help Command: `/help`
Provides a list of all the commands and its format, along with the respective
descriptions.

Format: `/help`

Expected Outcome: A list of all the commands.

Description of Outcome:
```
Here are the list of commands:
LIST PU                          : Provides the list of Partner Universities available
LIST [PU ABBRV]                  : Provides the list of all modules available in the specified Partner University
LIST [PU INDEX]                  : Provides the list of all modules available in the specified Partner University
                                   by index of LIST PU
LIST [PU ABBRV] /filter [FILTER] : Provides the list of modules in the specified filters
                                  [FILTER] Format 1: mc == [num of MCs]
                                  [FILTER] Format 2: [description] in name
LIST CURRENT                     : Provides the list of modules that the user has added to his/her list of interest
LIST CURRENT [PU ABBRV]          : Provides the list of modules that user has added to his list of
                                   list of interest for the specified PU
ADD [PU ABBRV]/[MODULE CODE]     : Adds the specified module into user's current list of modules
REMOVE [PU ABBRV]/ [INDEX]       : Removes the specified module by index from user's current list
SEARCH [NUS MOD CODE]            : Search for PU modules that can map the user's targeted module
/budget budget [AMOUNT]          : Allows the user to input/edit the total amount of budget for his/her SEP trip
/budget accommodation [AMOUNT]   : Allows the user to input/edit the total amount of accommodation cost
                                   for his/her SEP trip
/budget airplane [AMOUNT]        : Allows the user to input/edit the total amount of airplane
                                   ticket cost for his/her SEP trip
/budget food [AMOUNT]            : Allows the user to input/edit the total amount of food cost for his/her SEP trip
/budget entertainment [AMOUNT]   : Allows the user to input/edit the total amount of entertainment
                                   cost for his/her SEP trip
/budget view                     : Provides an overview of the user's planned budget
/deadline/list                   : Provides the list of deadlines the user has added
/deadline/add [DEADLINE DESCRIPTION] /by [DD-MM-YYYY] : Allows the user to add in his/her own personalized deadlines
                                    of the key dates for certain SEP requirements
/deadline/remove [DEADLINE INDEX] : Allows the user to remove the specific deadline from the list
EXIT                              : Exits the program
```
---
### 3.2 Modules
#### 3.2.1 List: `list` command

All List commands start with `list`.
1. List Current
2. List PU
3. List [PU Abbreviation/PU INDEX]
4. List Current [PU Abbreviation/PU INDEX]

****
##### 3.2.1.1 Listing modules user has selected: `List Current`

Format: `list current`

Example of usage: `list current`

Expected outcome: Modules that user has previously selected will be listed sorted by Partner Universities.

Description of outcome:
```
List of Added Modules for: KOREA UNIVERSITY
____________________________________________________________
1.[AE320][Aerodynamics II][3]
maps to ----> [ME4231][Aerodynamics][4]
____________________________________________________________

List of Added Modules for: KOREA ADVANCED INSTITUTE OF SCIENCE & TECHNOLOGY
____________________________________________________________
1.[IE321][PRODUCTION MANAGEMENT I][0]
maps to ----> [ME3662][Technical Elective][4]
____________________________________________________________

The current module list is empty for: POHANG UNIVERSITY OF SCIENCE & TECHNOLOGY
____________________________________________________________
____________________________________________________________

The current module list is empty for: SEOUL NATIONAL UNIVERSITY
____________________________________________________________
____________________________________________________________

List of Added Modules for: YONSEI UNIVERSITY
____________________________________________________________
1.[DAA3250][CHEM ENG THERMODYNAMICS I][0]
maps to ----> [ME3221][Sustainable Energy Conversion][4]
2.[MEU3680][MECHANICAL SYSTEM CONTROL][3]
maps to ----> [ME2142][Feedback Control Systems][4]
____________________________________________________________
```

****
##### 3.2.1.2 Listing out PU Module List: `List [PU Abbreviation/PU INDEX]`

**PU Module List** - Get a list of all the modules provided by the specific PU </span>

Format: `list [PU Abbreviation Name]` or `list [PU Index]`

* The `PU Abbreviation Name` is the abbreviation name of the PU as shown in the universities list.
* The `PU Index` is the university index of the PU as shown in the universities list.

Example of usage: `list ku` or `list 1`

Expected outcome:
* A list of modules under Korea University appears.

Description of outcome:
```
KOREA UNIVERSITY Modules
____________________________________________________________
1. [AMSE216][Introduction to biomaterials][3]
   maps to ----> [ME4253][Biomaterials Engineering][4]
2. [IWC311][Heat Transfer][3]
   maps to ----> [ME3122][Heat Transfer][4]
3. [AE320][Aerodynamics II][3]
   maps to ----> [ME4231][Aerodynamics][4]
4. [IWC109][Engineering Design][3]
   maps to ----> [ME4661][Exchange Elective][4]
____________________________________________________________
```
****
##### 3.2.1.3 Listing out all Partner Universities: `list pu`

Format: `list pu`

Expected outcome:
A list of all Partner Universities appears.

Description of outcome:
```
This is the list of PUs:
____________________________________________________________
1. KOREA UNIVERSITY KU
2. KOREA ADVANCED INSTITUTE OF SCIENCE & TECHNOLOGY KAIST
3. POHANG UNIVERSITY OF SCIENCE & TECHNOLOGY POSTECH
4. SEOUL NATIONAL UNIVERSITY SNU
5. YONSEI UNIVERSITY YU
____________________________________________________________
```
****
##### 3.2.1.4 Listing modules user has selected for specific Partner University: `List Current [PU Abbreviation]`

Format: `List Current [PU Abbreviation]`

* The `PU Abbreviation Name` is the abbreviation name of the PU as shown in the universities list.

Example of usage: `list current ku` 

Expected outcome: List of modules for Korea University that user has selected will appear.

Description of outcome:
```
List of Added Modules for: KOREA UNIVERSITY
____________________________________________________________
1.[AE320][Aerodynamics II][3]
maps to ----> [ME4231][Aerodynamics][4]
2.[IWC311][Heat Transfer][3]
maps to ----> [ME3122][Heat Transfer][4]
____________________________________________________________
```
****
#### 3.2.2 Add user selected modules to list: `add`

Format: `add [PU ABBREVIATION]/[MODULE CODE]`

* The `PU Abbreviation Name` is the abbreviation name of the PU as shown in the universities list.
* The  `MODULE CODE` is the Partner University's module code provided by ` list current`
  or ` list current [PU Abbreviation]` command.

Example of usage: `add KU/AMSE216`

Expected outcome: Adds the module AMSE216 from Korea University to the list of user selected modules.

Description of outcome:
```
This module has been added to the current module list!
____________________________________________________________
```

**Before Command** `add KU/AMSE216`
```
The current module list is empty for: KOREA UNIVERSITY
____________________________________________________________
____________________________________________________________
```

**After Command** `add KU/AMSE216`
```
List of Added Modules for: KOREA UNIVERSITY
____________________________________________________________
1.[AMSE216][Introduction to biomaterials][3]
   maps to ----> [ME4253][Biomaterials Engineering][4]
____________________________________________________________
```
---
#### 3.2.3 Remove user selected modules from list: `remove`

Format: `remove [PU ABBREVIATION]/[INDEX TO REMOVE]`

* The `PU Abbreviation Name` is the abbreviation name of the PU as shown in the universities list.
* The  `INDEX TO REMOVE` is the numbering of the module provided by ` list current`
or ` list current [PU Abbreviation]` command.

Example of usage: `remove KU/2` 

Expected outcome: Removes module indexed at 2 for Korea University from list of user selected modules.

Description of outcome: 

**Before Command** `remove KU/2`
```
List of Added Modules for: KOREA UNIVERSITY
____________________________________________________________
1.[AE320][Aerodynamics II][3]
maps to ----> [ME4231][Aerodynamics][4]
2.[IWC311][Heat Transfer][3]
maps to ----> [ME3122][Heat Transfer][4]
____________________________________________________________
```

**After Command** `remove KU/2`
```
List of Added Modules for: KOREA UNIVERSITY
____________________________________________________________
1.[AE320][Aerodynamics II][3]
maps to ----> [ME4231][Aerodynamics][4]
____________________________________________________________
```

Tip: This command is best utilized by executing `list current [PU Abbreviation]` or `list current` beforehand.

****


#### 3.2.4 Search by NUS module Code: `search`
Search for PU modules that can be mapped to the user's specific NUS module code.

Format: `search [NUS MODULE CODE]`
* The `NUS MODULE CODE` is the module that the user wants to map overseas.

Example of usage: `search ME4661`

Expected outcome:
* A list of PU's modules that can be mapped to the user's specific NUS module code will appear under its respective PU
heading

Description of outcome:
```
Here is/are the list/s of modules that can map this NUS module code: ME4661
____________________________________________________________
____________________________________________________________
KU
____________________________________________________________
1. [IWC109][Engineering Design][3]
____________________________________________________________
KAIST
____________________________________________________________
1. [AE405][Satellite Systems][3]
2. [ENGME203][Mechatronics system design][3]
____________________________________________________________
POSTECH
____________________________________________________________
1. [MECH441][METAL FORMING][3]
____________________________________________________________
SNU
____________________________________________________________
1. [406.752][VEHICLE ERGONOMICS][3]
2. [ZXX4582.503][FUNCTIONAL POLYMER NANOMATERIALS][0]
3. [M2794.008600][INVISCID FLOW][0]
4. [M2795.006500][AIR BREATHING PROPULSION THEORY][0]
5. [457.206][SOIL MECHANICS][0]
6. [M2795.00400][HIGH ENERGY THERMOFLUID DYNAMICS][0]

```
---
### 3.3 Deadlines
Deadlines are tasks added by the user. There will be a due date for the task.
If the task is due in 7 days, there will be a reminder shown along with the 
welcome message when the program first runs.
#### 3.3.1 List All the Deadlines Saved: `/deadline/list`

Provides the list of all the deadlines the user has added.

Format: /deadline/list

Expected Outcome:
```
List of Deadlines:
____________________________________________________________
1. Take passport photo [Due by: 01-03-2023]
2. Submit documents to KU [Due by: 20-03-2023]
____________________________________________________________
```
---
#### 3.3.2 Add a New Deadline: `/deadline/add`
Adds a new deadline to the list of saved deadlines.

Format: `/deadline/add  [TASK] /by [DUE DATE]`

:warning: Take note DUE DATE format is in DD-MM-YYYY

Example of Usage: `/deadline/add Take passport photo /by 01-03-2023`

Expected Outcome: Adds a task named: Take passport photo. It is set to due-by
01-03-2023. 

Description of Outcome:
```
This deadline has been added to the current deadlines
____________________________________________________________
```
---
#### 3.3.3 Remove a Deadline from the List: `/deadline/remove`
User deletes a deadline from the list of saved deadlines by index.

Format: `/deadline/remove [INDEX]`

Example of Usage: `/deadline/remove 1`

Expected Outcome: Deletes the first index from the list of saved deadlines

Description of Outcome:
```
This module has been deleted from the current module list!
____________________________________________________________
```
---

### 3.4 Budget Planner: `/budget`

All Budget Planner commands start with /budget.

****

#### 3.4.1 Edits the budget - `budget`

Edits the current total budget to a new amount the user plans to spend on his/her SEP trip

Format: `/budget /budget /[AMOUNT]`

- The `AMOUNT` is the user's input budget for his/her SEP trip in SGD.
- Initial budget is set to 0 when user has not set any budget before.

Example of Usage:
`/budget /budget /2000`

Expected Outcome:

- Changes the budget `AMOUNT` to 2000 SGD.
- Shows a budget changed successful message to let the user check the budget set corresponds to his input.

Description of outcome:

```
____________________________________________________________
Budget has been changed to: 2000
____________________________________________________________
```

****

#### 3.4.2 Edits the accommodation cost - `accommodation` 

Edits the current accommodation planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget /accommodation /[AMOUNT]`

- The `AMOUNT` is the user's input accommodation cost for his/her SEP trip in SGD.
- Initial accommodation cost is set to 0 when user has not set any accommodation cost before.

Example of usage:
`/budget /accommodation /2000`

Expected outcome:

- Changes the accommodation cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Accommodation cost has been changed to: 2000
____________________________________________________________
```

****

#### 3.4.3 Edits the Airplane Ticket cost - `airplane` 

Edits the current Airplane Ticket planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget /airplane /[AMOUNT]`

- The `AMOUNT` is the user's input airplane ticket cost for his/her SEP trip in SGD.
- Initial airplane ticket cost is set to 0 when user has not set any airplane ticket cost before.

Example of usage:
`/budget /airplane /2000`

Expected outcome:

- Changes the airplane cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Airplane Ticket cost has been changed to: 2000
____________________________________________________________
```

****

#### 3.4.4 Edits the Food cost - `Food` 

Edits the current Food planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget /food /[AMOUNT]`

- The `AMOUNT` is the user's input food cost for his/her SEP trip in SGD.
- Initial food cost is set to 0 when user has not set any food cost before.

Example of usage:
`/budget /food /2000`

Expected outcome:

- Changes the food cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Food cost has been changed to: 2000
____________________________________________________________
```

****

#### 3.4.5 Edits the Entertainment cost - `Entertainment` 

Edits the current Entertainment planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget /entertainment /[AMOUNT]`

- The `AMOUNT` is the user's input entertainment cost for his/her SEP trip in SGD.
- Initial entertainment cost is set to 0 when user has not set any entertainment cost before.

Example of usage:
`/budget /entertainment /2000`

Expected outcome:

- Changes the entertainment cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Entertainment cost has been changed to: 2000
____________________________________________________________
```

****

#### 3.4.6 Views the entire budget plan - `View` 

Provides an overview of what the budget consists of.

Format: `/budget /view`

Example of usage:
`/budget /view`

Expected outcome:

- Shows the total budget amount planned for the SEP trip.
- Shows the Accommodation cost
- Shows the Airplane Ticket cost
- Shows the Food cost
- Shows the Entertainment Cost
- Shows the Surplus, which could be a Deficit if value is negative

Description of outcome:

```
____________________________________________________________
Total budget: 2000
Accommodation cost: 2000
Airplane Ticket cost: 2000
Food cost: 2000
Entertainment cost: 2000
Surplus/Deficit: -6000
____________________________________________________________
```
---


---
## 4 Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
