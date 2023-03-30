# User Guide for Sniff

## Introduction

Sniff is an appointment manager that helps clinic deal with handling appointments of different types.
Features such as appointment tracking via uid, date, and appointment type help improve
efficiency in the workplace and reduce human errors.

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Sniff from [here](https://github.com/AY2223S2-CS2113-W12-1/tp/releases).
3. Move the file to a location where you want to run Sniff from.
4. Open a command terminal and navigate to the location where `sniff.jar` is.
5Use the following command to run the program: `java -jar sniff.jar`
6If Sniff runs successfully, you will see the following welcome message
```
______________________________________________________________________
 Hello! I'm Sniff, your personal appointment manager.
 What can I do for you?
______________________________________________________________________
```

## Features 

{Give detailed description of each feature}

### Adding a consultation appointment: `consultation`
Adds a new consultation appointment to the appointment manager.

Format: `consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH-MM* format.

Example of usage: 

* `consultation at/Cat an/Lulu on/Jon cn/91919191 cd/2023-12-12 ct/19:00`
* `consultation at/Dog an/Russ on/Sarah cn/92929292 cd/2023-10-15 ct/09:00`

### Adding a vaccination appointment: `vaccination`
Adds a new vaccination appointment to the appointment manager.

Format: `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE cd/DATE ct/TIME`

* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH-MM* format.

Example of usage:

* `vaccination at/Cat an/Lulu on/Jon cn/91919191 v/Covid vd/2023-12-12 vt/19:00`
* `vaccination at/Dog an/Russ on/Sarah cn/92929292 v/Rabies cd/2023-10-15 ct/09:00`

### Adding a surgery appointment: `surgery`
Adds a new surgery appointment to the appointment manager.

Format: `surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL`

* The `START_DATE` and `START_TIME` must be in *YYYY-MM-DD* format.
* The `TIME` and `END_TIME` must be in *HH-MM* format.
* The `PRIORITY_LEVEL` must be *H*, *M*, *L* format, representing HIGH, MEDIUM and LOW priorities respectively.

Example of usage:

* `surgery at/Cat an/Lulu on/Jon cn/91919191 sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/L`
* `surgery at/Dog an/Russ on/Sarah cn/92929292 sd/2023-10-15 st/09:00 ed/2023-12-15 et/20:00 p/M`

### Listing appointments: `list`
Retrieves all the tasks from Sniff Appointments ArrayList and displays it to the user.

Format: `list`

Example of usage: Displays the Sniff Task List.

```
______________________________________________________________________
list
______________________________________________________________________
1.  UID: S67775112T [X] | Priority: HIGH
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
 Start Date: 2023-12-12 | Start Time: 19:00
 End Date: 2023-12-12 | End Time: 20:00
2.  UID: V34624451A [ ] | vaccine: covid
 Date: 2023-12-12 | Time: 19:00
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
______________________________________________________________________
```
### Removing an appointment: `remove`
Removes an appointment with a specific UID from the appointment manager.

Format: `remove uid`


Example of usage: (with reference to the above appointment list)

* `remove S67775112T` to remove the first appointment.
* `remove V34624451A` to remove the second appointment.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
