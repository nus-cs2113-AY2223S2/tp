# User Guide for Sniff

## Introduction

Sniff is an appointment manager that helps clinic deal with handling appointments of different types.
Features such as appointment tracking via uid, date, and appointment type help improve efficiency in the workplace 
and reduce human errors.


## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Sniff from [here](https://github.com/AY2223S2-CS2113-W12-1/tp/releases).
3. Move the file to a location where you want to run Sniff from.
4. Open a command terminal and navigate to the location where `sniff.jar` is.
5. Use the following command to run the program: `java -jar sniff.jar`
6. If Sniff runs successfully, you will see the following welcome message
```
______________________________________________________________________
 Hello! I'm Sniff, your personal appointment manager.
 What can I do for you?
______________________________________________________________________
```

## Features

### Adding a consultation appointment: `consultation`
Adds a new consultation appointment to the appointment manager.

Format: `consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME`

* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH:MM* format.

Example of usage: 

* `consultation at/Cat an/Lulu on/Jon cn/91919191 cd/2023-12-12 ct/19:00`
* `consultation at/Dog an/Russ on/Sarah cn/92929292 cd/2023-10-15 ct/09:00`

### Adding a vaccination appointment: `vaccination`
Adds a new vaccination appointment to the appointment manager.

Format: `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATE vt/TIME`

* The `DATE` must be in *YYYY-MM-DD* format.
* The `TIME` must be in *HH:MM* format.

Example of usage:

* `vaccination at/Cat an/Lulu on/Jon cn/91919191 v/Covid vd/2023-12-12 vt/19:00`
* `vaccination at/Dog an/Russ on/Sarah cn/92929292 v/Rabies vd/2023-10-15 vt/09:00`

### Adding a surgery appointment: `surgery`
Adds a new surgery appointment to the appointment manager.

Format: `surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL`

* The `START_DATE` and `END_DATE` must be in *YYYY-MM-DD* format.
* The `START_TIME` and `END_TIME` must be in *HH:MM* format.
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

### Find by appointment UID / appointment type / animal type: <br>
Format: `find a/dog` `find t/surgery` `find aID/123` <br>
Retrieves specific appointments requested by user <br>
Can filter by appointment ID, appointment type, animal type <br>
Example of usage: Displays the requested appointments <br>

```
______________________________________________________________________
find a/cat
______________________________________________________________________
1.  UID: C31245737A [ ]
 Date: 2023-12-12 | Time: 19:00
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
2.  UID: S52117007D [ ] | Priority: HIGH
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
 Start Date: 2023-12-12 | Start Time: 19:00
 End Date: 2023-12-12 | End Time: 20:00
______________________________________________________________________
```
```
______________________________________________________________________
find t/consultation
______________________________________________________________________
1.  UID: C31245737A [ ]
 Date: 2023-12-12 | Time: 19:00
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
______________________________________________________________________
```
```
______________________________________________________________________
find aID/V14082745S
______________________________________________________________________
1.  UID: V14082745S [ ] | vaccine: covid
 Date: 2023-12-12 | Time: 19:00
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
 Sorry, an error was encountered! Here is the error description:
 Appointment ID must consist of integers!
______________________________________________________________________
```
### Mark Appointment : `mark ` <br>

This feature marks the appointment as done and is denoted by an `[X]`.
The tasks are marked depending on the uID input the by user.
If the uID entry is not valid it displays a corresponding error message.<br>

Format : `mark uID/ `
Example : `mark uID/V14082745S`

Example of Valid Command:

```
mark uID/C26135173W
______________________________________________________________________
The appointment has been marked successfully
Task marked successfully!
______________________________________________________________________
```
Example of InValid Command :
```
mark uID/C26135173W_
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
 There are no appointments with this ID.
______________________________________________________________________

```
Marked in the list:
```
list
______________________________________________________________________
1.  UID: S53144505J [ ] | Priority: LOW
    Animal Name: Lulu | Animal Type: Cat
    Owner Name: Jon | Contact Number: 91919191
    Start Date: 2023-12-12 | Start Time: 19:00
    End Date: 2023-12-12 | End Time: 20:00
2.  UID: V48152237H [ ] | vaccine: Covid
    Date: 2023-12-12 | Time: 19:00
    Animal Name: Lulu | Animal Type: Cat
    Owner Name: Jon | Contact Number: 91919191
3.  UID: C26135173W [X]
    Date: 2023-12-12 | Time: 19:00
    Animal Name: Lulu | Animal Type: Cat
    Owner Name: Jon | Contact Number: 91919191
______________________________________________________________________
```

### UnMark Appointment : `unmark ` <br>

This feature Unmarks the appointment as not done and is denoted by an `[ ]`.
The tasks are Unmarked depending on the uID input the by user. 
If the uID entry is not valid it displays a corresponding error message.<br>

Example of Valid Command :
```

unmark uID/C26135173W
______________________________________________________________________
The appointment has been unmarked successfully
Task unmarked successfully!
______________________________________________________________________
```
Example of InValid Command :
```
unmark uID/C26135173E
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
 There are no appointments with this ID.
______________________________________________________________________
```
Corresponding list :
```
list
______________________________________________________________________
1.  UID: S53144505J [ ] | Priority: LOW
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
 Start Date: 2023-12-12 | Start Time: 19:00
 End Date: 2023-12-12 | End Time: 20:00
2.  UID: V48152237H [ ] | vaccine: Covid
 Date: 2023-12-12 | Time: 19:00
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
3.  UID: C26135173W [ ]
 Date: 2023-12-12 | Time: 19:00
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
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

**A**: 

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add consultation :
  `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE cd/DATE ct/TIME` 


* Add surgery :
  `surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL` 


* Add vaccination :
  `vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE cd/DATE ct/TIME` 


* List :
  `list`


* Remove appointment :
  `remove uid`


* find appointment by animal_type :
`find a/dog` 


* find appointment by appointment_type :
`find t/surgery` 


* find appointment by appointment_uID :
`find aID/123`


* Mark appointment :
  `mark uID/ `


* UnMark appointment :
  `unmark uID/ `


 
 
