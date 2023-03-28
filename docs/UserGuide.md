# User Guide

## Introduction

SEP Helper is a desktop application for Mechanical Engineering students, studying at the
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).

## Quick Start

1. Ensure that you have Java 11 or above installed in your computer, if not proceed to download from the link:
   https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html
2. Download the latest version of `SEPHelper.jar` from [here](http://link.to/duke).
3. Double-click the file to start the app.
4. Type the command in the terminal and press enter to execute it. E.g. entering `/help` will show the help page


## Features Overview

### Notes:
* **Command words**:
  * Command words are not case-sensitive.
    * E.g. `eXIT` will be accepted as the `exit` command.


### List: `list` command

All List commands start with `list`.
1. List Current
2. List PU
3. List [PU Abbreviation/PU INDEX]
4. List Current [PU Abbreviation/PU INDEX]

****
#### 1. Listing modules user has selected: `List Current`

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
#### 2. Listing out PU Module List: `List [PU Abbreviation/PU INDEX]`

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
#### 3. Listing out all Partner Universities: `list pu`

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
#### 4. Listing modules user has selected for specific Partner University: `List Current [PU Abbreviation]`

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

### Remove user selected modules from list: `remove`

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


### Search by NUS module Code: `search`
****
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

### Budget Planner: `/budget`

All Budget Planner commands start with /budget.

****

#### `budget` - Edits the budget

Edits the current total budget to a new amount the user plans to spend on his/her SEP trip

Format: `/budget budget [AMOUNT]`

- The `AMOUNT` is the user's input budget for his/her SEP trip in SGD.
- Initial budget is set to 0 when user has not set any budget before.

Example of usage:
`/budget budget 2000`

Expected outcome:

- Changes the budget `AMOUNT` to 2000 SGD.
- Shows a budget changed successful message to let the user check the budget set corresponds to his input.

Description of outcome:

```
____________________________________________________________
Budget has been changed to: 2000
____________________________________________________________
```

****

#### `accommodation` - Edits the accommodation cost

Edits the current accommodation planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget accommodation [AMOUNT]`

- The `AMOUNT` is the user's input accommodation cost for his/her SEP trip in SGD.
- Initial accommodation cost is set to 0 when user has not set any accommodation cost before.

Example of usage:
`/budget accommodation 2000`

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

#### `airplane` - Edits the Airplane Ticket cost

Edits the current Airplane Ticket planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget airplane [AMOUNT]`

- The `AMOUNT` is the user's input airplane ticket cost for his/her SEP trip in SGD.
- Initial airplane ticket cost is set to 0 when user has not set any airplane ticket cost before.

Example of usage:
`/budget airplane 2000`

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

#### `Food` - Edits the Food cost

Edits the current Food planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget food [AMOUNT]`

- The `AMOUNT` is the user's input food cost for his/her SEP trip in SGD.
- Initial food cost is set to 0 when user has not set any food cost before.

Example of usage:
`/budget food 2000`

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

#### `Entertainment` - Edits the Entertainment cost

Edits the current Entertainment planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget entertainment [AMOUNT]`

- The `AMOUNT` is the user's input entertainment cost for his/her SEP trip in SGD.
- Initial entertainment cost is set to 0 when user has not set any entertainment cost before.

Example of usage:
`/budget entertainment 2000`

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

#### `View` - Views the entire budget plan

Provides an overview of what the budget consists of.

Format: `/budget view`

Example of usage:
`/budget view`

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

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
