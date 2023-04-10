# User Guide for Sniff

## Introduction

Sniff is a `Command Line Interface (CLI) Appointment Tracker` designed specifically for veterinary clinics in Singapore.
Its main functions are to track various appointments of types: Surgery, Vaccination, & 
Consultation via uid, date, and appointment type to help vet clinics better manage their working staff.
<br>


## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Sniff from [here](https://github.com/AY2223S2-CS2113-W12-1/tp/releases).
3. Move the file to a location where you want to run Sniff from.
4. Open a command terminal and navigate to the location where `sniff.jar` is.
5. Use the following command to run the program: `java -jar sniff.jar`
6. If Sniff runs successfully, you will see the following welcome message.


```
______________________________________________________________________
 Hello! I'm Sniff, your personal appointment manager.
     _______. .__   __.  __   _______   _______
    /       | |  \ |  | |  | |   ____| |   ____|
   |   (----` |   \|  | |  | |  |__    |  |__
    \   \     |  . `  | |  | |   __|   |   __|
.----)   |    |  |\   | |  | |  |      |  |
|_______/     |__| \__| |__| |__|      |__|

 What can I do for you?
______________________________________________________________________
```
## Overview of Features

1. Adding appointments: `consultation`, `vaccination`, `surgery`.
2. Removing appointments: `remove`
3. List out all appointments: `list`
4. Mark/Unmark appointments: `mark`, `unmark`
5. View archived appointments (those marked done): `archive`
6. Find appointments: `find`
7. Edit appointments: `edit`
8. Help function: `help`
9. Exit the program: `bye`

## Features

### Adding a consultation appointment: `consultation`
Adds a new consultation appointment to the appointment manager.

Format: `consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* The `ANIMAL_TYPE`, `ANIMAL_NAME` and `OWNER_NAME` must be in alphabetical format.
* The `CONTACT_NUMBER` must be an 8-digit number.
* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH:MM* format.

Example of usage: 

* `consultation at/Cat an/Lulu on/Jon cn/91919191 cd/2023-12-12 ct/19:00`
* `consultation at/Dog an/Russ on/Sarah cn/92929292 cd/2023-10-15 ct/09:00`

```
______________________________________________________________________
 This appointment has been added to your appointment manager: 
 Consultation   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: C74584428I
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
 Consultation added successfully!
______________________________________________________________________
```

### Adding a vaccination appointment: `vaccination`
Adds a new vaccination appointment to the appointment manager.

Format: `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATE vt/TIME`

* The `ANIMAL_TYPE`, `ANIMAL_NAME`, `OWNER_NAME` must be in alphabetical format.
* The `CONTACT_NUMBER` must be an 8-digit number.
* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH:MM* format.

Example of usage:

* `vaccination at/Cat an/Lulu on/Jon cn/91919191 v/Covid vd/2023-12-12 vt/19:00`
* `vaccination at/Dog an/Russ on/Sarah cn/92929292 v/Rabies vd/2023-10-15 vt/09:00`

```
______________________________________________________________________
 This appointment has been added to your appointment manager: 
 Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V47278232N
 Vaccine: Covid
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
 Vaccination added successfully!
______________________________________________________________________
```

### Adding a surgery appointment: `surgery`
Adds a new surgery appointment to the appointment manager.

Format: `surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL`

* The `ANIMAL_TYPE`, `ANIMAL_NAME`, `OWNER_NAME` must be in alphabetical format.
* The `CONTACT_NUMBER` must be an 8-digit number.
* The `START_DATE` and `END_DATE` must be in *YYYY-MM-DD* format.
* The `START_TIME` and `END_TIME` must be in *HH:MM* format.
* The `PRIORITY_LEVEL` must be *H*, *M*, *L* format, representing HIGH, MEDIUM and LOW priorities respectively.

Example of usage:

* `surgery at/Cat an/Lulu on/Jon cn/91919191 sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/L`
* `surgery at/Dog an/Russ on/Sarah cn/92929292 sd/2023-10-15 st/09:00 ed/2023-12-15 et/20:00 p/M`

```
______________________________________________________________________
 This appointment has been added to your appointment manager: 
 Surgery  [ ] | Priority: LOW
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S88228101H
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
 Surgery added successfully!
______________________________________________________________________
```

### Listing appointments: `list`
Retrieves all unmarked tasks from Sniff Appointments ArrayList and displays it to the user.
Unmarked tasks are reflected on the list immediately. List is displayed sorted by date and time.
Earlier appointments will appear at the top of the list.

Format: `list`

Example of usage: Displays the unmarked appointments from Sniff Task List.

```
______________________________________________________________________
list
______________________________________________________________________
 1.  Consultation   [ ]
 Date: 2023-11-03
 Time: 10:00
 UID: C64016488E
 Animal Name: Mona | Animal Type: Cat
 Owner Name: Becks | Contact Number: 90199000

 2.  Consultation   [ ]
 Date: 2023-11-12
 Time: 19:00
 UID: C41606700F
 Animal Name: Peepoo | Animal Type: Dog
 Owner Name: Ken | Contact Number: 99999999

 3.  Vaccination   [ ]
 Date: 2023-11-31
 Time: 15:00
 UID: V77751751P
 Vaccine: Polyomavirus
 Animal Name: Birdy | Animal Type: Bird
 Owner Name: Ben | Contact Number: 10002999

 4.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S44657158O
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191

______________________________________________________________________
```

### Listing archived appointments: `archive`
Retrieves all marked tasks from Sniff Appointments ArrayList and displays it to the user.
Marked tasks are reflected on the Archive list immediately.

Format: `archive`

Example of usage: Displays the marked appointments from Sniff Task List.

```
______________________________________________________________________
archive
______________________________________________________________________
 1.  Consultation   [X]
 Date: 2023-11-03
 Time: 10:00
 UID: C64016488E
 Animal Name: Mona | Animal Type: Cat
 Owner Name: Becks | Contact Number: 90199000

 2.  Consultation   [X]
 Date: 2023-11-12
 Time: 19:00
 UID: C41606700F
 Animal Name: Peepoo | Animal Type: Dog
 Owner Name: Ken | Contact Number: 99999999

 3.  Vaccination   [X]
 Date: 2023-11-31
 Time: 15:00
 UID: V77751751P
 Vaccine: Polyomavirus
 Animal Name: Birdy | Animal Type: Bird
 Owner Name: Ben | Contact Number: 10002999

 4.  Surgery  [X] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S44657158O
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191

______________________________________________________________________
```

### Removing an appointment: `remove`
Removes an appointment with a specific UID from the appointment manager.

Format: `remove uid/<uid>`

Example of usage: Using the appointment list below,

```
______________________________________________________________________
list
______________________________________________________________________
 1.  Consultation   [ ]
 Date: 2023-11-03
 Time: 10:00
 UID: C64016488E
 Animal Name: Mona | Animal Type: Cat
 Owner Name: Becks | Contact Number: 90199000

 2.  Consultation   [ ]
 Date: 2023-11-12
 Time: 19:00
 UID: C41606700F
 Animal Name: Peepoo | Animal Type: Dog
 Owner Name: Ken | Contact Number: 99999999

 3.  Vaccination   [ ]
 Date: 2023-11-31
 Time: 15:00
 UID: V77751751P
 Vaccine: Polyomavirus
 Animal Name: Birdy | Animal Type: Bird
 Owner Name: Ben | Contact Number: 10002999

 4.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S44657158O
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191

______________________________________________________________________
```
Calling `remove uid/C41606700F` will remove the second appointment.
```
______________________________________________________________________
remove uid/C41606700F
______________________________________________________________________
 This appointment has been removed your appointment manager: 
 Consultation   [ ]
 Date: 2023-11-12
 Time: 19:00
 UID: C41606700F
 Animal Name: Peepoo | Animal Type: Dog
 Owner Name: Ken | Contact Number: 99999999

 Task removed successfully!
______________________________________________________________________
```
### Find by appointment UID / appointment type / animal type / date: <br>
Format: `find a/dog`, `find t/surgery`, `find uid/S02547136Q`, `find d/2023-12-12` <br>
Retrieves specific appointments requested by user <br>
Can filter by appointment UID, appointment type, animal type, date of appointment<br>
Example of usage: Displays the requested appointments <br>

```
______________________________________________________________________
find a/dog
______________________________________________________________________
 1.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S02547136Q
 Animal Name: lulu | Animal Type: dog
 Owner Name: jon | Contact Number: 91919191

______________________________________________________________________
```
```
______________________________________________________________________
find t/surgery
______________________________________________________________________
 1.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S01534766O
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191

______________________________________________________________________
```
```
______________________________________________________________________
find uid/S02547136Q
______________________________________________________________________
 1.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S02547136Q
 Animal Name: lulu | Animal Type: dog
 Owner Name: jon | Contact Number: 91919191

______________________________________________________________________
```
```
______________________________________________________________________
find d/2023-12-12
______________________________________________________________________
 1.  Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V17511055M
 Vaccine: covid
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
 
 ______________________________________________________________________
```
### Mark Appointment : `mark ` <br>

This feature marks the appointment as done and is denoted by an `[X]`.
The tasks are marked depending on the UID input the by user.
If the UID entry is not valid it displays a corresponding error message.<br>

Format : `mark uid/ `
Example : `mark uid/V14082745S`

Example of Valid Command:

```
mark uid/C67345117A
______________________________________________________________________
 Consultation   [X]
 Date: 2023-12-12
 Time: 19:00
 UID: C67345117A
 Animal Name: Oreo | Animal Type: Cat
 Owner Name: fred | Contact Number: 91919191
The appointment has been marked successfully
______________________________________________________________________
```
Example of Invalid Command :
```
mark uid/ggggg
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
 Here are possible places where you could have gone wrong: 
1. Check if the entered UID is valid.
2. Check if you have left the UID field empty.
3. Lastly check if the format is correct mark uid/UID
______________________________________________________________________

```


### UnMark Appointment : `unmark ` <br>

This feature Unmarks the appointment as not done and is denoted by an `[ ]`.
The tasks are Unmarked depending on the UID input the by user. 
If the UID entry is not valid it displays a corresponding error message.<br>

Example of Valid Command :
Format : `mark uid/ `
Example : `mark uid/V14082745S`
```

unmark uid/C80880043M
______________________________________________________________________
  1.  Consultation   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: C80880043M
 Animal Name: Oreo | Animal Type: Cat
 Owner Name: fred | Contact Number: 91919191


The appointment has been unMarked successfully
______________________________________________________________________
```
Example of InValid Command :
```
unmark 
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
 Here are possible places where you could have gone wrong: 
1. Check if the entered UID is valid.
2. Check if you have left the UID field empty.
3. Lastly check if the format is correct unmark uid/UID
______________________________________________________________________
```

### Editing a consultation appointment: `edit`
Edits an already existing consultation appointment 

Format: `edit uid/ID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* The `UID` should be a valid already existing ID
* The `ANIMAL_TYPE`, `ANIMAL_NAME` and `OWNER_NAME` must be in alphabetical format.
* The `CONTACT_NUMBER` must be an 8-digit number.
* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH:MM* format.

Example:
`edit uid/C28026345F at/Monkey an/Milo on/Smriti cn/91999999 cd/2023-12-12 ct/19:00`
```
______________________________________________________________________
Consultation changed successfully!
______________________________________________________________________
```

### Editing a surgery appointment: `edit`
Edits an already existing consultation appointment

Format: `edit uid/ID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL`

* The `UID` should be a valid already existing ID
* The `ANIMAL_TYPE`, `ANIMAL_NAME`, `OWNER_NAME` must be in alphabetical format.
* The `CONTACT_NUMBER` must be an 8-digit number.
* The `START_DATE` and `END_DATE` must be in *YYYY-MM-DD* format.
* The `START_TIME` and `END_TIME` must be in *HH:MM* format.
* The `PRIORITY_LEVEL` must be *H*, *M*, *L* format, representing HIGH, MEDIUM and LOW priorities respectively.

Example:
`edit uid/S03044138U at/Mouse an/Caramel on/Sam cn/93939393 sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/H`
```
______________________________________________________________________
 Surgery changed successfully!
______________________________________________________________________
```
### Editing a Vaccination appointment: `edit`
Edits an already existing consultation appointment

Format:   `edit uid/ID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE cd/DATE ct/TIME`

* The `UID` should be a valid already existing ID
* The `ANIMAL_TYPE`, `ANIMAL_NAME`, `OWNER_NAME` must be in alphabetical format.
* The `CONTACT_NUMBER` must be an 8-digit number.
* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH:MM* format.

Example:
`edit uid/V01087221W at/Dog an/Russ on/Abel cn/92929292 v/Covid vd/2023-12-12 vt/19:00`
```
______________________________________________________________________
Vaccination changed successfully!
______________________________________________________________________
```

### Help function: `help`
Shows all possible commands and their format.

Format: `help`

Example of usage:

```
______________________________________________________________________
These are the following Sniff commands available:

Add consultation appointment:

 consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME

Add vaccination appointment:

 vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATE vt/TIME

Add surgery appointment:

 surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL

Listing all appointments:

 list

Removing an appointment:

 remove uid/UID

Finding an appointment by animal type, appointment type or uid:

 find a/ANIMAL_TYPE

 find t/APPOINTMENT_TYPE 

 find uid/UID

 find d/DATE

Mark or UnMark an appointment:

 mark uid/UID

 unMark uid/UID

Editing an appointment:

 edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME

 edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATEvt/TIME

 edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATEst/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL

Archiving appointments:

 archive

Exiting the program:

 bye

Additional notes:

 1. DATES and TIMES format are in (YYYY-MM-DD) and (HH:MM) respectively

 2. PRIORITY_LEVEL format is in (L, M, H)
______________________________________________________________________
```

### Exiting the program: `bye`
Exits the appointment manager program.

Format: `bye`

Example of usage:

```
______________________________________________________________________
 Bye, hope to see you again soon!
______________________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Transfer the save file into the user directory and ensure that the naming of the file, as well as the file format remains the same.

**Q**: How do I save my data?

**A**: Data is automatically saved after every command, there is no need for the user to write any command to manually save their data.

## Command Summary

* Add consultation :
  `consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* Add surgery :
  `surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL` 


* Add vaccination :
  `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATE vt/TIME` 


* List :
  `list`

* Archived appointments :
  `archive`

* Remove appointment :
  `remove uid/UID`


* find appointment by animal_type :
`find a/dog` 


* find appointment by appointment_type :
`find t/surgery` 


* find appointment by appointment_uID :
`find uid/UID`


* Mark appointment :
  `mark uid/UID `


* UnMark appointment :
  `unmark uid/UID`

* Edit Consultation :
  `edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* Edit Surgery :
  `edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL`


* Edit vaccination :
  `edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE cd/DATE ct/TIME` 

* Help: 
  `help`

* Exit the program: 
  `bye`

