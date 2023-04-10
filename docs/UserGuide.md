<!-- @@author Magmanat -->
<!-- omit in toc -->
<div style="page-break-after: always;"></div>

# User Guide

<!-- omit in toc -->
## Table of contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Commands](#commands)
  - [Common Commands](#common-commands)
    - [**Bye Command**](#bye-command)
    - [**Help Command**](#help-command)
  - [PCViewer Mode Commands](#pcviewer-mode-commands)
    - [**Add Command**](#add-command)
    - [**View Command**](#view-command)
    - [**Edit Command**](#edit-command)
    - [**Delete Command**](#delete-command)
    - [**Filter Command**](#filter-command)
  - [PCBuilder Mode Commands](#pcbuilder-mode-commands)
    - [**List Command**](#list-command)
    - [**Select Command**](#select-command)
    - [**Unselect Command**](#unselect-command)
    - [**Compare Command**](#compare-command)
    - [**Budget Command**](#budget-command)
    - [**Name Command**](#name-command)
    - [**Custom Command**](#custom-command)
    - [**Back Command**](#back-command)
- [Additional Features](#additional-features)
  - [Compatibility Check](#compatibility-check)
  - [Advanced User Guide](#advanced-user-guide)
- [Glossary](#glossary)
  - [Parameters Glossary](#parameters-glossary)
  - [Command Glossary](#command-glossary)
    - [Acceptable Custom Component Commands](#acceptable-custom-component-commands)

<div style="page-break-after: always;"></div>

## Introduction

Welcome to Big PC Part-Picker (BigPP)
BigPP is a command-line application designed for PC enthusiasts and professional PC builders who aim to:

- streamline the [process](#commands) of managing computer builds
- verify the [compatibility](#compatibility-check) of selected components.

This developer-oriented guide offers a comprehensive overview of the various commands and features available in BigPP. It is intended for users who are familiar with command-line interfaces.
If you encounter unfamiliar terminology in this guide, please refer to the [Glossary](#glossary) for a list of terms and their definitions.

<div style="page-break-after: always;"></div>

## Getting Started

1. Install [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) for your operating system. If you are unsure about how to do this, you can refer to the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html) or consult a search engine.
2. Download the latest version of `BigPP` from
   [the releases page](https://github.com/AY2223S2-CS2113-T12-2/tp/releases).
3. Copy the file to an empty folder you want to use as the _home folder_ This will be the folder in which BigPP will store all of its user data.
4. Right click inside the _home folder_ and open a terminal at that location
5. Run the command `java -jar BigPP.jar` to start the app.
6. Refer to the [Commands](#Commands) below to try out the application!

<div style="page-break-after: always;"></div>

Upon successful initialization of the program for the first time, you would be greeted by the following, assuming you do not currently have a user.json file in your _home folder_

```
User file does not exist. Creating new user file.
User PCs found, loading...
          _____                    _____                    _____                            _____                    _____
         /\    \                  /\    \                  /\    \                          /\    \                  /\    \
        /::\    \                /::\    \                /::\    \                        /::\    \                /::\    \
       /::::\    \               \:::\    \              /::::\    \                      /::::\    \              /::::\    \
      /::::::\    \               \:::\    \            /::::::\    \                    /::::::\    \            /::::::\    \
     /:::/\:::\    \               \:::\    \          /:::/\:::\    \                  /:::/\:::\    \          /:::/\:::\    \
    /:::/__\:::\    \               \:::\    \        /:::/  \:::\    \                /:::/__\:::\    \        /:::/__\:::\    \
   /::::\   \:::\    \              /::::\    \      /:::/    \:::\    \              /::::\   \:::\    \      /::::\   \:::\    \
  /::::::\   \:::\    \    ____    /::::::\    \    /:::/    / \:::\    \            /::::::\   \:::\    \    /::::::\   \:::\    \
 /:::/\:::\   \:::\ ___\  /\   \  /:::/\:::\    \  /:::/    /   \:::\ ___\          /:::/\:::\   \:::\____\  /:::/\:::\   \:::\____\
/:::/__\:::\   \:::|    |/::\   \/:::/  \:::\____\/:::/____/  ___\:::|    |        /:::/  \:::\   \:::|    |/:::/  \:::\   \:::|    |
\:::\   \:::\  /:::|____|\:::\  /:::/    \::/    /\:::\    \ /\  /:::|____|        \::/    \:::\  /:::|____|\::/    \:::\  /:::|____|
 \:::\   \:::\/:::/    /  \:::\/:::/    / \/____/  \:::\    /::\ \::/    /          \/_____/\:::\/:::/    /  \/_____/\:::\/:::/    /
  \:::\   \::::::/    /    \::::::/    /            \:::\   \:::\ \/____/                    \::::::/    /            \::::::/    /
   \:::\   \::::/    /      \::::/____/              \:::\   \:::\____\                       \::::/    /              \::::/    /
    \:::\  /:::/    /        \:::\    \               \:::\  /:::/    /                        \::/____/                \::/____/
     \:::\/:::/    /          \:::\    \               \:::\/:::/    /                          ~~                       ~~
      \::::::/    /            \:::\    \               \::::::/    /
       \::::/    /              \:::\____\               \::::/    /
        \::/____/                \::/    /                \::/____/
         ~~                       \/____/


Welcome to BigPP!
===================================================
PC viewer
Here is the list of PC Builds:
1.Prebuilt-PC: [beginner] - $917.28/infinite - Complete
2.Prebuilt-PC: [intermediate] - $1710.74/infinite - Complete
3.Prebuilt-PC: [advanced] - $2339.74/infinite - Complete
What would you like to do?
===================================================

```

If the ASCII art of BIG PP does not look correct, it is due to your terminal size being too small, if so you can disregard this issue.

<div style="page-break-after: always;"></div>

## Commands

Our commands are split up for two modes, PCViewer and PCBuilder. The commands for each mode will only be recognised for the specific mode that you are currently in. The modes will specifically be stated at the top of each printout in the terminal. Additional leading and trailing whitespaces from user input will be ignored.

For reference,

- PCViewer looks like this:

```
===================================================
PC viewer
Here is the list of PC Builds:
1.Prebuilt-PC: [beginner] - $917.28/infinite - Complete
2.Prebuilt-PC: [intermediate] - $1710.74/infinite - Complete
3.Prebuilt-PC: [expert] - $2339.74/infinite - Complete
What would you like to do?
===================================================
```

- PCBuilder looks like this

```
===================================================
PC builder
Custom-PC: [expert (copy)] - $2339.74/infinite - Complete
Power Consumption: 584.2W/850.0W
Components:
CPU        : AMD Ryzen 9 7950X
CPU Cooler : Cooler Master MASTERLIQUID ML120L RGB V2
GPU        : MSI GAMING Z TRIO RTX3080
Motherboard: Gigabyte B650I AORUS ULTRA
RAM        : Corsair Vengeance RGB Pro 32 GB
Storage    : Samsung 980 Pro
PSU        : SeaSonic FOCUS PLUS 850 Gold
Chassis    : Corsair iCUE 4000X RGB

What would you like to do?
===================================================
```

<div style="page-break-after: always;"></div>

---

### Common Commands

Common commands will be the commands that the user will be able to use in both the modes

<!-- @@author Reynold-SL -->

#### **Bye Command**

Usage: `bye`
Functionality: Exits the application and saves all user data that has currently been changed since the opening of the application

```diff
- **IMPORTANT:** the user MUST execute this command 
- to save the data that has been edited,
- if the user decides to end the program using ctrl+c or 
- other methods, their edited data would be lost!
```

<!-- @@author Reynold-SL -->

<div style="page-break-after: always;"></div>

#### **Help Command**

Usage: `help`

Functionality: Displays the valid commands in the respective modes

Example:

- Entering the help command in the PCViewer mode will produce the following output at the bottom of your terminal:

```
Here are the list of valid commands: 
_____________________________________________________
| Command Type            | Command Usage           |
|-------------------------|-------------------------|
| Add new PC              | add PC_NAME             |
| Edit PC                 | edit PC_INDEX           |
| View PC Specs           | view PC_INDEX           |
| Delete PC               | delete PC_INDEX         |
| Filter PC List          | filter FILTER_FLAGS     |
| Exit program            | bye                     |
-----------------------------------------------------
For more detailed documentation on commands, please refer to our user guide!
```

- Entering the help command in the PCBuilder mode will produce the following output at the bottom of your terminal:

```
Here are the list of valid commands:
______________________________________________________________________________
| Command Type            | Command Usage                                    |
|-------------------------|--------------------------------------------------|
| List Component          | list COMPONENT_TYPE [-COMPONENT_FLAG lIST_FLAG]  |
| Select Component        | select COMPONENT_TYPE COMPONENT_INDEX            |
| Unselect Component      | unselect COMPONENT_TYPE                          |
| Compare Components      | compare COMPONENT_TYPE INDEX_1 & INDEX_2         |
| Change Budget           | budget POSITIVE_INTEGER                          |
| Change PC Name          | name PC_NAME                                     |
| Create Custom Component | custom COMPONENT_TYPE SPEC1|SPEC_2|...           |
| Back Command            | back                                             |
| Exit program            | bye                                              |
------------------------------------------------------------------------------
For more detailed documentation on commands, please refer to our user guide!
```

Notes: Entering the help command in either PCViewer mode or PCBuilder mode will present different sets of valid commands

---

<!-- @@author superkaiba -->

<div style="page-break-after: always;"></div>

### PCViewer Mode Commands

PCViewer Mode commands will be the commands that the user will be able to use in the PCViewer Mode

#### **Add Command**

Usage: `add PC_NAME`

Functionality: Adds a PC with the name `PC_NAME` to your PC list

Example:

> input: add NEWPC

```
===================================================
PC viewer
Here is the list of PC Builds:
1.Prebuilt-PC: [beginner] - $917.28/infinite - Complete
2.Prebuilt-PC: [intermediate] - $1710.74/infinite - Complete
3.Prebuilt-PC: [expert] - $2339.74/infinite - Complete
4.Custom-PC: [NEWPC] - $0.0/infinite - Incomplete
What would you like to do?
===================================================
Custom PC: [ NEWPC ] has been created
```

Notes: newly added PCs will always begin with infinite budget

<div style="page-break-after: always;"></div>

---

#### **View Command**

Usage: `view PC_INDEX`

Functionality: Views the PC with index `PC_INDEX`

Example:

> input: view 3

```
===================================================
PC viewer
Here is the list of PC Builds:
    1.Prebuilt-PC: [beginner] - $917.28/infinite - Complete
    2.Prebuilt-PC: [intermediate] - $1710.74/infinite - Complete
    3.Prebuilt-PC: [expert] - $2339.74/infinite - Complete
    4.Custom-PC: [NEWPC] - $0.0/infinite - Incomplete
What would you like to do?
===================================================
Prebuilt-PC: [expert] - $2339.74/infinite - Complete
Power Consumption: 584.2W/850.0W
Components:
CPU        : AMD Ryzen 9 7950X
CPU Cooler : Cooler Master MASTERLIQUID ML120L RGB V2
GPU        : MSI GAMING Z TRIO RTX3080
Motherboard: Gigabyte B650I AORUS ULTRA
RAM        : Corsair Vengeance RGB Pro 32 GB
Storage    : Samsung 980 Pro
PSU        : SeaSonic FOCUS PLUS 850 Gold
Chassis    : Corsair iCUE 4000X RGB
```

<div style="page-break-after: always;"></div>

---

<!-- @@author Magmanat -->

#### **Edit Command**

Usage: `edit PC_INDEX`

Functionality: Enters PCBuilder mode for the PC with index `PC_INDEX`.

Example:

> input: edit 4

```
===================================================
PC builder
Custom-PC: [NEWPC] - $0.0/infinite - Incomplete
Power Consumption: 0.0W/0W
Components:
CPU        : - Not Selected -
CPU Cooler : - Not Selected -
GPU        : - Not Selected -
Motherboard: - Not Selected -
RAM        : - Not Selected -
Storage    : - Not Selected -
PSU        : - Not Selected -
Chassis    : - Not Selected -

What would you like to do?
===================================================
Currently editing PC: NEWPC
```

Notes: If the PC that the user has selected to edit is a `PREBUILT_PC`, the application would create a copy of it and mark it as a `CUSTOM_PC`. This is because we provide the prebuilts as a reference for the user, and not for editing.

<div style="page-break-after: always;"></div>

---

#### **Delete Command**

Usage: `delete PC_INDEX`

Functionality: Deletes the PC with index `PC_INDEX`

Example:

> input: delete 4

```
===================================================
PC viewer
Here is the list of PC Builds:
    1.Prebuilt-PC: [beginner] - $917.28/infinite - Complete
    2.Prebuilt-PC: [intermediate] - $1710.74/infinite - Complete
    3.Prebuilt-PC: [expert] - $2339.74/infinite - Complete
What would you like to do?
===================================================
Custom PC: [ NEWPC ] has been deleted
```

<div style="page-break-after: always;"></div>

---

<!-- @@author Reynold-SL -->

#### **Filter Command**

Usage: `filter FILTER_FLAGS`

Available flags:

> `-name PC_NAME`
>
> `-price /from PC_START_PRICE /to PC_END_PRICE`
>
> `-built PC_ISCOMPLETE`
>
> `-clear`

Functionality:
The following explains the functionality of filtering by each flag:

- `-name PC_NAME` will filter the PC List by the given name
- `price /from PC_START_PRICE /to PC_END_PRICE` will filter the PC List by a given price range
- `-built PC_ISCOMPLETE` will filter the PC List by built of either complete or incomplete
- `-clear` will clear all filters that were applied previously

Example: filter the PC List by the name of intermediate, in the range of starting price from 1000 to 3000 and is a completed build

> input: filter -name intermediate -price /from 1000 /to 3000 -built complete

```
===================================================
PC viewer
Here is the list of PC Builds:
    2.Prebuilt-PC: [intermediate] - $1710.74/infinite - Complete
What would you like to do?
===================================================
Filter completed
```

Notes: The flags can by strung together _in any order_ such as `filter -price /from 1000 /to 3000 -name intermediate -built complete`. However, if the `-clear` flag is detected, this will take **precedence** and remove all filters regardless of other flags.
Please note that you will still be able to edit the PCs that are not filtered as the filter is simply only for easier viewing of the PCs which you would like to edit.

<div style="page-break-after: always;"></div>

---

<!-- @@author typingpanda -->

### PCBuilder Mode Commands

PCBuilder Mode commands will be the commands that the user will be able to use in the PCBuilder Mode

#### **List Command**

Usage: `list COMPONENT_TYPE [-FLAG]`

Available flags:

> `-details`
>
> `-name COMPONENT_NAME`
>
> `-brand COMPONENT_BRAND`
>
> `-price /from COMPONENT_START_PRICE /to COMPONENT_END_PRICE`
>
> `-power /from COMPONENT_START_POWER /to COMPONENT_END_POWER`
>
> `-formfactor COMPONENT_FORMFACTOR`
>
> `-socket COMPONENT_SOCKET`
>
> `-core /from COMPONENT_START_CORE /to COMPONENT_END_CORE`
>
> `-thread /from COMPONENT_START_THREAD /to COMPONENT_END_THREAD`
>
> `-baseclock /from COMPONENT_START_BASECLOCK /to COMPONENT_END_BASECLOCK`
>
> `-boostclock /from COMPONENT_START_BOOSTCLOCK /to COMPONENT_END_BOOSTCLOCK`
>
> `-size /from COMPONENT_START_SIZE /to COMPONENT_END_SIZE`
>
> `-rpm /from COMPONENT_START_RPM /to COMPONENT_END_RPM`
>
> `-noise /from COMPONENT_START_NOISE /to COMPONENT_END_NOISE`
>
> `-memory COMPONENT_MEMORY`
>
> `-sticks /from COMPONENT_START_STICKS /to COMPONENT_END_STICKS`
>
> `-speed /from COMPONENT_START_SPEED /to COMPONENT_END_SPEED`
>
> `-type COMPONENT_TYPE`
>
> `-efficiency COMPONENT_EFFICIENCY`

<div style="page-break-after: always;"></div>

Functionality: Lists all components of type `COMPONENT_TYPE` with optional flags. List command shows only the names of components for readability. For detailed information, use the `-details` flag. Multiple flags can be used together.

Example:

> input: list cpu -name intel -price /from 70 /to 100 -details

```
Here are all available components of type 'cpu':
meeting the following criteria:
name: intel
price: 70 to 100
1.
NAME: Intel core i3-10100
BRAND: Intel
PRICE: 99.5
CORES: 4
THREADS: 8
BASECLOCK: 3.6
BOOSTCLOCK: 4.3
POWER: 65.0
SOCKET: LGA1200
================
```

Notes: Arguments with missing flags will be ignored. Flags that are not relevant to the component will also be ignored.

<div style="page-break-after: always;"></div>

---

<!-- @@author Ryujikjs -->

#### **Select Command**

Usage: `select COMPONENT_TYPE COMPONENT_INDEX`

Functionality: Adds the component of type `COMPONENT_TYPE` with index `INDEX` to the current PC Build

Example: add component of type `GPU` with index `4` to the current PC Build.

> input: select gpu 4

```
===================================================
PC builder
Custom-PC: [pc] - $934.06/infinite - Incomplete
Power Consumption: 350.0W/0W
Components:
CPU        : - Not Selected -
CPU Cooler : - Not Selected -
GPU        : MSI GAMING Z TRIO RTX3080
Motherboard: - Not Selected -
RAM        : - Not Selected -
Storage    : - Not Selected -
PSU        : - Not Selected -
Chassis    : - Not Selected -

What would you like to do?
===================================================
gpu added! : MSI GAMING Z TRIO RTX3080

```

Notes: you can select any component at any time and do not need to list components before selecting as long as you know which index you want to add.

<div style="page-break-after: always;"></div>

---

#### **Unselect Command**

Usage: `unselect COMPONENT_TYPE`

Functionality: Removes the component of type `COMPONENT_TYPE` from your PC Build.

Example: remove component of type `GPU` from the current PC Build.

> input: unselect gpu

```
===================================================
PC builder
Custom-PC: [pc] - $0.0/infinite - Incomplete
Power Consumption: 0.0W/0W
Components:
CPU        : - Not Selected -
CPU Cooler : - Not Selected -
GPU        : - Not Selected -
Motherboard: - Not Selected -
RAM        : - Not Selected -
Storage    : - Not Selected -
PSU        : - Not Selected -
Chassis    : - Not Selected -

What would you like to do?
===================================================
gpu removed!
```

<div style="page-break-after: always;"></div>

---

#### **Compare Command**

Usage: `compare COMPONENT_TYPE INDEX_1 & INDEX_2`

Functionality: Compares 2 components,`INDEX_1` and `INDEX_2` of type `COMPONENT_TYPE` with one another. Compares each specification in a table format

Example: Compare 2 components of type `CPU`. Comparison between indexes `1` and `2`.

> input: compare cpu 1 & 2

```
________________________________________________________________________________________________
|NAME        |Intel core i3-10100                     |Intel core i5-10600k                    |
|------------|----------------------------------------|----------------------------------------|
|PRICE       |$99.5                                   |$182.47                                 |
|SOCKET      |LGA1200                                 |LGA1200                                 |
|CORES       |4                                       |6                                       |
|THREADS     |8                                       |12                                      |
|BASE CLOCK  |3.6GHz                                  |4.1GHz                                  |
|BOOST CLOCK |4.3GHz                                  |4.8GHz                                  |
|POWER       |65.0W                                   |125.0W                                  |
________________________________________________________________________________________________

```

<div style="page-break-after: always;"></div>

---

<!-- @@author typingpanda -->

#### **Budget Command**

Usage: `budget INTEGER`

Functionality: Sets the `BUDGET` of the current PC build to either an `INTEGER` by entering a positive integer or to `INFINITE` by entering `-1` as arguments.

Example: Set `BUDGET` of the PC you are currently editing to be $1000

> input: budget 1000

```
===================================================
PC builder
Custom-PC: [beginner (copy)] - $813.29/$1000.00 - Complete
Power Consumption: 234.5W/850.0W
Components:
CPU        : Intel core i3-10100
CPU Cooler : Thermalright AXP90-X36
GPU        : Gigabyte GAMING OC RTX3050
Motherboard: Asus Prime Z590-P WiFi
RAM        : Kingston FURY Beast 16 GB
Storage    : ADATA XPG SPECTRIX S40G RGB
PSU        : SeaSonic FOCUS PLUS 850 Gold
Chassis    : Fractal Design Pop XL Air

What would you like to do?
===================================================
Current build budget is now: $1000.00

```

<div style="page-break-after: always;"></div>

Example: Set `BUDGET` of the PC you are currently editing to be `INFINITE`

> input: budget -1

```
===================================================
PC builder
Custom-PC: [beginner (copy)] - $917.28/infinite - Complete
Power Consumption: 234.5W/850.0W
Components:
CPU        : Intel core i3-10100
CPU Cooler : Thermalright AXP90-X36
GPU        : Gigabyte GAMING OC RTX3050
Motherboard: Asus Prime Z590-P WiFi
RAM        : G.Skill Ripjaws X 8 GB
Storage    : ADATA XPG SPECTRIX S40G RGB
PSU        : SeaSonic FOCUS PLUS 850 Gold
Chassis    : Fractal Design Pop XL Air

What would you like to do?
===================================================       
Current build budget is now: infinite

```
<div style="page-break-after: always;"></div>

---

#### **Name Command**

Usage: `name PC_NAME`

Functionality: Sets the name of the current PC build to `PC_NAME`

Example Set the name of PC to be MyPC

> input: name MyPC

```
===================================================
PC builder
Custom-PC: [MyPC] - $917.28/infinite - Complete
Power Consumption: 234.5W/850.0W
Components:
CPU        : Intel core i3-10100
CPU Cooler : Thermalright AXP90-X36
GPU        : Gigabyte GAMING OC RTX3050
Motherboard: Asus Prime Z590-P WiFi
RAM        : G.Skill Ripjaws X 8 GB
Storage    : ADATA XPG SPECTRIX S40G RGB
PSU        : SeaSonic FOCUS PLUS 850 Gold
Chassis    : Fractal Design Pop XL Air

What would you like to do?
===================================================
Current build name is now: MyPC
```

<div style="page-break-after: always;"></div>

---

<!-- @@author superkaiba -->

#### **Custom Command**

Usage: `custom COMPONENT_TYPE SPEC_1|SPEC_2|...`

Functionality: Adds a custom component of type COMPONENT_TYPE with the specs `SPEC_1`, `SPEC_2`...... to the current PC build

> input: custom cpu Intel-new-cpu\|Intel\|99.5\|4\|8\|3.5\|4.6\|122\|LGA1200

```
===================================================
PC builder
Custom-PC: [pc-1] - $99.5/infinite - Incomplete
Power Consumption: 122.0W/0W
Components:
CPU        : Intel-new-cpu
CPU Cooler : - Not Selected -
GPU        : - Not Selected -
Motherboard: - Not Selected -
RAM        : - Not Selected -
Storage    : - Not Selected -
PSU        : - Not Selected -
Chassis    : - Not Selected -

What would you like to do?
===================================================
CPU added: Intel-new-cpu
```

Notes: Please see the glossary for the `SPEC` definitions for each component

---

#### **Back Command**

Usage: `back`

Functionality: Goes back to PCViewer menu

<div style="page-break-after: always;"></div>

---

<!-- @@author Magmanat -->

## Additional Features

### Compatibility Check

BigPP will check if your components that you added are compatible with each other. There are a few ways that BigPP will ensure your PC components are all compatible with each other.

1. `CHASSIS` must always be of a bigger or same `FORMFACTOR` than the inner components such as `GPU`, `PSU` etc, this check is ignored if your chassis is not added.
2. Total power consumption of all `COMPONENT_TYPE` components in your build apart from `PSU` must be below 80% of the power supplied by the `PSU` selected to ensure there is enough power in your build, this check is ignored if your psu is not added.
3. Your `BUDGET` always needs to be larger than the total price of your entire build, this check is ignored if your budget is infinite.
4. Your `CPU` and `MOTHERBOARD` needs to have the same `SOCKET`.

Based on the rules stated above, the compatibility checker will throw the user error messages if he tries to add any component which breaks the rules, and suggest changes that the user can make to avoid adding incompatible components.

<div style="page-break-after: always;"></div>

Example: The `CPU` current added has a socket **LGA1200** and the new `MOTHERBOARD` that the user wants to add has a socket **AM5**.

> input: custom motherboard newmobo\|AMD\|10\|atx\|AM5\|10

```
===================================================
PC builder
Custom-PC: [pc] - $224.48/infinite - Incomplete
Power Consumption: 65.0W/0W
Components:
CPU        : Intel core i3-10100
CPU Cooler : - Not Selected -
GPU        : - Not Selected -
Motherboard: - Not Selected -
RAM        : - Not Selected -
Storage    : - Not Selected -
PSU        : - Not Selected -
Chassis    : Fractal Design Pop XL Air

What would you like to do?
===================================================
Motherboard socket is not compatible with current cpu socket
Please choose another motherboard or change your cpu
```

### Advanced User Guide
You are able to edit the Json file to change the data of your custom built PCs as an advanced user. However if any formatting error is created due to this, the program would prompt:
```
Error loading user PCs, please backup your user json then delete it.
```
This will allow the application to create a new user json for you to freely use the app once again, and the backed-up json file will be there for you to refer to in case there is important information still there. 
If certain attributes of the components have been deleted, such as "name", "price" etc, the application would still be able to load up, but these attributes would be set as null values. In order to change back these values, either add a custom component with the updated attributes, or exit the application and edit the values inside the user json file.

<div style="page-break-after: always;"></div>

<!-- @@author Ryujikjs -->

## Glossary

### Parameters Glossary

|    Parameter     |                                    Explanation                                    |             Type              |        Example        |
| :--------------: | :-------------------------------------------------------------------------------: |:-----------------------------:|:---------------------:|
|     PC_NAME      |                               Name of the PC Build                                |            String             |      "The Beast"      |
|     PC_INDEX     |                              Index number of the PC                               |       Positive Integer        |           1           |
|   PREBUILT_PC    |                      PC with all its components pre selected                      |             -nil-             |         -nil-         |
|    CUSTOM_PC     |                      PC that can be fully customised by user                      |             -nil-             |         -nil-         |
|  PC_START_COST   |             Initial cost of the PC (must be lesser than PC_END_COST)              |        Positive Float         |         0.00          |
|   PC_END_COST    |             Final cost of the PC (must be greater than PC_START_COST)             |        Positive Float         |        1500.90        |
|       NAME       |                               Name of the component                               |            String             | "Intel core i3-10100" |
|      PRICE       |                              Price of the component                               | Positive Float (<= 100000.00) |        100.00         |
|      BRAND       |                    Name of the manufacturer of the compoenent                     |            String             |         "msi"         |
|      POWER       |                The power consumption of the component in Watts(W)                 |  Positive Float (<= 5000.0)   |         150.0         |
|       CORE       |                 Number of physical cores avaiable for computation                 |       Positive Integer        |           4           |
|      THREAD      |                 Number of virtual cores avaiable for computation                  |       Positive Integer        |           8           |
|    BASECLOCK     |                Baseline clockspeed of component in gigahertz (GHz)                |   Positive Float (<= 10.0)    |          3.2          |
|    BOOSTCLOCK    |          Boosted clockspeed of component when under load gigahertz (GHz)          |   Positive Float (<= 10.0)    |          4.2          |
|       RPM        |                 Speed of rotation in revolutions per minute (RPM)                 |       Positive Integer        |          500          |
|      NOISE       |              Measurement of sound component produces in decibels(dB)              |   Positive Integer (<= 500)   |          43           |
| POSITIVE_INTEGER |                       A positive integer between 0 - 65,535                       |       Positive Integer        |           1           |

| **Parameter**  | **Explanation**                                      | **Type**         |                                                                               **All Acceptable Inputs**                                                                                |
| -------------- | ---------------------------------------------------- | ---------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| SIZE           | Size of storage components in gigabytes(GB)          | Positive Integer |                                                                                512, 1024, 2048 and 4096                                                                                |
| MEMORY         | Amount of random access memory(RAM) in gigabytes(GB) | Positive Integer |                                                                                    8, 16, 32 and 64                                                                                    |
| STICKS         | Numper of physical RAM sticks                        | Positive Integer |                                                                                       1, 2 and 4                                                                                       |
| SPEED          | Speed of RAM in Megahertz(MHz)                       | Positive Integer |                                                                            1600, 2000, 2666, 3200 and 3600                                                                             |
| TYPE           | Type of storage device                               | String           |                                                                                      `ssd` and `hdd`                                                                                   |
| FILTER_FLAGS   | Attributes of PC Build to filter                     | String           |                                            `-name`,`-price`,`-built` and`-clear`                                             |
| LIST_FLAGS     | Attributes of the component                          | String           | `-name`, `-brand`, `-price`,`-power`, `-formfactor`, `-socket`, `-core`, `-thread`, `-baseclock`, `-boostclock`, `-size`, `-rpm`, `-noise`, `-memory`, `-sticks`, `-speed`, `-details`, `-efficiency` and `-type` |
| PC_ISCOMPLETE  | State of PC                                          | String           |                                                                               `complete` and `incomplete`                                                                                |
| COMPONENT_TYPE | Type of Component that is required to build a PC     | String           |                                                       `cpu`,`gpu`,`cpu-cooler`,`motherboard`,`ram`,`storage`,`psu` and `chassis`                                                       |
| FORMFACTOR     | The standard size specification of the components    | String           |                                                                                `atx`,`mini` and `micro`                                                                                |
|      SOCKET      | Physical socket where CPU is placed in(CPU must be seated in a compatible socket) |      String      |       `LGA1200`,`LGA1700`,`AM4` and `AM5`       |
|    EFFICIENCY    |                       The power efficiency of power supply                        |      String      |        `gold`,`silver` and `bronze`         |

<div style="page-break-after: always;"></div>

### Command Glossary

|   Viewer Menu Commands    |                     Format                     |                                                Flags                                                |
| :-----------------------: | :--------------------------------------------: | :-------------------------------------------------------------------------------------------------: |
|          Add PC           |                 `add PC_NAME`                  |                                                 nil                                                 |
|          View PC          |                `view PC_INDEX`                 |                                                 nil                                                 |
|          Edit PC          |                `edit PC_INDEX`                 |                                                 nil                                                 |
|         Delete PC         |               `delete PC_INDEX`                |                                                 nil                                                 |
|        Filter PCs         |             `filter FILTER_FLAGS`              |   `-name`,`-price`,`-built` and `-clear`   |


| **Builder Menu Commands** |                   **Format**                   |                                              **Flags**                                              |
| :-----------------------: | :--------------------------------------------: | :-------------------------------------------------------------------------------------------------: |
|         List CPU          |     `list cpu [-COMPONENT_FLAG LIST_FLAG]`     | `-name`, `-brand`, `-price`,`-details`,`-power`, `-socket`, `-core`, `-thread`, `-baseclock` and `-boostclock` |
|         List GPU          |     `list gpu [-COMPONENT_FLAG LIST_FLAG]`     |                       `-name`, `-brand`, `-price`,`-details`,`-power` and `-formfactor`                        |
|      List CPU Cooler      | `list cpu-cooler [-COMPONENT_FLAG LIST_FLAG]`  |                     `-name`, `-brand`, `-price`,`-details`, `-rpm`, `-noise` and `-power`                      |
|     List Motherboard      | `list motherboard [-COMPONENT_FLAG LIST_FLAG]` |                   `-name`, `-brand`, `-price`,`-details`,`-formfactor`,`-socket`and `-power`                   |
|         List RAM          |     `list ram [-COMPONENT_FLAG LIST_FLAG]`     |               `-name`, `-brand`, `-price`,`-details`,`-memory`, `-sticks`, `-speed` and`-power`                |
|       List Storage        |   `list storage [-COMPONENT_FLAG LIST_FLAG]`   |                     `-name`, `-brand`, `-price`,`-details`, `-type`, `-size` and `-power`                      |
|         List PSU          |     `list psu [-COMPONENT_FLAG LIST_FLAG]`     |                `-name`, `-brand`, `-price`,`-details`,`-efficiency`, `-formfactor` and `-power`                |
|        List Chasis        |   `list chasis [-COMPONENT_FLAG LIST_FLAG]`    |                            `-name`, `-brand`, `-price`,`-details` and `-formfactor`                            |
|     Select Component      |    `select COMPONENT_TYPE COMPONENT_INDEX`     |                                                 nil                                                 |
|    Unselect Component     |   `unselect COMPONENT_TYPE COMPONENT_INDEX`    |                                                 nil                                                 |
|    Compare Components     |   `compare COMPONENT_TYPE INDEX_1 & INDEX_2`   |                                                 nil                                                 |
|          Budget           |           `budget POSITIVE_INTEGER`            |                                                 nil                                                 |
|          Name PC          |                 `name PC_NAME`                 |                                                 nil                                                 |
|     Custom Component      |  `custom COMPONENT_TYPE SPEC_1\|SPEC_2\|...`   |                     [Acceptable Format](#acceptable-custom-component-commands)                      |
|           Back            |                     `back`                     |                                                 nil                                                 |


<div style="page-break-after: always;"></div>

#### Acceptable Custom Component Commands

The acceptable formats for custom component creation are as shown below

1. `custom cpu NAME|BRAND|PRICE|CORE|THREAD|BASECLOCK|BOOSTCLOCK|POWER|SOCKET`
2. `custom gpu NAME|BRAND|PRICE|POWER|FORMFACTOR`
3. `custom cpu-cooler NAME|BRAND|PRICE|RPM|NOISE|POWER`
4. `custom motherboard NAME|BRAND|PRICE|FORMFACTOR|SOCKET|POWER`
5. `custom ram NAME|BRAND|PRICE|MEMORY|STICKS|SPEED|POWER`
6. `custom storage NAME|BRAND|PRICE|TYPE|SIZE|POWER`
7. `custom psu NAME|BRAND|PRICE|EFFICIENCY|FORMFACTOR|POWER`
8. `custom chassis NAME|BRAND|PRICE|FORMFACTOR`
