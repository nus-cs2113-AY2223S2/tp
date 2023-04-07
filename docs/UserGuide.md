# User Guide
# Table of contents

- [User Guide](#user-guide)
    - [1.0 Introduction](#10-introduction)
    - [2.0 Quick Start](#20-quick-start)
    - [3.0 Features](#30-features)
    - [FAQ](#faq)
    - [Command Summary](#command-summary)
## 1.0 Introduction
🗓️ NUSPlanner is a desktop app that allows for an *easy* and *straightforward* way for NUS students to manage their schedule ranging from person, school or external related activities. This application makes use of a desktop Command Line Interface (CLI), enabling a quick and sleek method of getting your schedule in check.

⌨️ If you can type fast, NUSPlanner can get your contact management tasks done faster and more efficiently than traditional GUI apps.

✅ NUSPlanner is available for download for operating systems such as Windows, Linux and OS-X.

## 2.0 Quick Start
Before we get started, here's what you need to do:

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `NUSPlanner.jar` from [here](https://github.com/AY2223S2-CS2113-F13-3/tp/releases).
3. Open your terminal or command prompt
4. Navigate to the file directory of where the jar file is saved
5. Execute `NUSPlanner.jar` using the following command: `java -jar NUSPlanner.jar`
6. Refer to section [3.0 Features] for more details of commands

## 3.0 Features

Read this section to find out how NUSPlanner can make your life easier!

### Adding an event: `add`
Adds an event to the schedule. Assuming x is an switch. Use –x to specify the attributes to edit.

Format: `add –e EVENTNAME –st STARTTIME –sd STARTDATE –et ENDTIME –ed ENDDATE -v VENUE -r RECURRING TIME`
* sd and ed must be of the format YYYY/MM/DD
* e, sd and st are compulsory fields
* ed and et are optional, but they must be written together if you use them
* v is optional
* r is optional, the format is x D/ x W, which means the event will happen in every x day/x week.

#### Examples of usage
* Add event that starts and ends on different days: `add –e Career Fair –st 14:00 –sd 2023/02/10 –et 16:00 –ed 2023-02-11`

* Add event that happens every week: `add –e collect mails –st 8:00 –sd 2023/02/10 –et 8:10 -ed 2023/02/10 -r 1 W`

Expected outcome:
~~~
____________________________________________________________
Event successfully added!
____________________________________________________________
~~~

### Adding a module: `add`
Adds a module to the schedule. Assuming x is an switch. Use –x to specify the attributes to edit.

Format: `add –m MODULECODE -n CLASSNUMBER -l LESSONTYPE`
* All fields are compulsory

#### Examples of usage
* Add a lecture of module: `add -m AC5001 -n 1 -l lecture`
* Add a tutorial of module: `add -m CS2100 -n 17 -l tutorial`

Expected outcome:
~~~
____________________________________________________________
Event successfully added: 

    > Added 13 classes of Module: AC5001
____________________________________________________________
~~~
~~~
____________________________________________________________
Event successfully added: 

    > Added 11 classes of Module: CS2100
____________________________________________________________
~~~

### Deleting an event: `delete`
Delete a single event or all events from the schedule. Use –s to specify the events to delete, or –all to delete all events.

Format:
* `delete –s INDEX_OF_EVENT `
* `delete -all`

#### Examples of usage
* Delete a single event: `delete –s 1`
* Delete all events: `delete -all`

Expected outcome:
~~~
____________________________________________________________
Event(s) successfully deleted!
____________________________________________________________
~~~

### List an event: `list`
Displays a list of all events that have been added to the schedule.

Format:
* List all events: `list`
* List timetable of specific week: `list -w WEEKNUM`

#### Examples of usage
* List all events: `list`
* List timetable of specific week: `list -w 7`

Expected outcome:
~~~
____________________________________________________________
   > 1.[E] AC5001 (2022/08/10 14:00 to 2022/08/10 17:00 not recurring)
   > 2.[E] AC5001 (2022/08/17 14:00 to 2022/08/17 17:00 not recurring)
   > 3.[E] AC5001 (2022/08/24 14:00 to 2022/08/24 17:00 not recurring)
   > 4.[E] AC5001 (2022/08/31 14:00 to 2022/08/31 17:00 not recurring)
   > 5.[E] AC5001 (2022/09/07 14:00 to 2022/09/07 17:00 not recurring)
   > 6.[E] AC5001 (2022/09/14 14:00 to 2022/09/14 17:00 not recurring)
   > 7.[E] AC5001 (2022/09/21 14:00 to 2022/09/21 17:00 not recurring)
   > 8.[E] AC5001 (2022/09/28 14:00 to 2022/09/28 17:00 not recurring)
   > 9.[E] AC5001 (2022/10/05 14:00 to 2022/10/05 17:00 not recurring)
   > 10.[E] AC5001 (2022/10/12 14:00 to 2022/10/12 17:00 not recurring)
   > 11.[E] AC5001 (2022/10/19 14:00 to 2022/10/19 17:00 not recurring)
   > 12.[E] AC5001 (2022/10/26 14:00 to 2022/10/26 17:00 not recurring)
   > 13.[E] AC5001 (2022/11/02 14:00 to 2022/11/02 17:00 not recurring)
   > 14.[E] CS2100 (2022/08/26 16:00 to 2022/08/26 17:00 not recurring)
   > 15.[E] CS2100 (2022/09/02 16:00 to 2022/09/02 17:00 not recurring)
   > 16.[E] CS2100 (2022/09/09 16:00 to 2022/09/09 17:00 not recurring)
   > 17.[E] CS2100 (2022/09/16 16:00 to 2022/09/16 17:00 not recurring)
   > 18.[E] CS2100 (2022/09/23 16:00 to 2022/09/23 17:00 not recurring)
   > 19.[E] CS2100 (2022/09/30 16:00 to 2022/09/30 17:00 not recurring)
   > 20.[E] CS2100 (2022/10/07 16:00 to 2022/10/07 17:00 not recurring)
   > 21.[E] CS2100 (2022/10/14 16:00 to 2022/10/14 17:00 not recurring)
   > 22.[E] CS2100 (2022/10/21 16:00 to 2022/10/21 17:00 not recurring)
   > 23.[E] CS2100 (2022/10/28 16:00 to 2022/10/28 17:00 not recurring)
   > 24.[E] CS2100 (2022/11/04 16:00 to 2022/11/04 17:00 not recurring)
____________________________________________________________
~~~
~~~
Showing schedule for semester 1 and week 7

TIME      |MONDAY         |TUESDAY        |WEDNESDAY      |THURSDAY       |FRIDAY         |SATURDAY       |SUNDAY         |
----------+---------------+---------------+---------------+---------------+---------------+---------------+---------------+
08:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
08:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
09:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
09:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
10:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
10:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
11:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
11:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
12:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
12:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
13:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
13:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
14:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
14:30     |               |               |AC5001         |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
15:00     |               |               |AC5001         |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
15:30     |               |               |AC5001         |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
16:00     |               |               |AC5001         |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
16:30     |               |               |AC5001         |               |CS2100         |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
17:00     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+
17:30     |               |               |               |               |               |               |               |
          +---------------+---------------+---------------+---------------+---------------+---------------+---------------+

____________________________________________________________
~~~

### Editing an event: `edit`
Edit an event that has been added to the schedule. Use –x to specify the attributes to edit.

Format: `edit -i INDEX_OF_EVENT –st STARTTIME –sd STARTDATE –et ENDTIME –ed ENDDATE -v VENUE -r x D/W`
* sd is required and it must be of the format YYYY/MM/DD
* st is required and it must be of the format HH:MM
* Other fields are optional

#### Examples of usage
* Edit the start time of an event: `edit –i 2 –st 16:00`
* Edit the start date, start time and end time of an event: `edit -i 3 –sd 2023/02/11 –st 8:00 –et 10:00 `

Expected outcome:
~~~
____________________________________________________________
Event edited successfully!
____________________________________________________________
~~~

### Saving Data
NusPlanner data are saved to the hard drive automatically after the `bye` command to quit it. There is no need to save manually.

### Editing the data file
Data for NUSPlanner is saved as a `.json` file. The path for the save file is `[JAR file location]/save.json`. Advanced users are welcome to update data directly by editing the data file.

🚧 **Warning**: If your changes to the data file makes its format invalid, NUSPlanner will discard all data and load an empty data file at the next run.

## FAQ

**Q**: Why is a JAR file required?

**A**: Java ARchive, also known as JAR, is a container that groups multiple small files to enable efficient execution, which is similar to a ZIP file.


**Q**: Where can I report bugs I found while using NUSPlanner?

**A**: Our team of developers are always on the look-out to fix such bugs.
Please add a description of the bug found under our [Issue Tracker](https://github.com/AY2223S2-CS2113-F13-3/tp/issues).

## Command Summary
👉 Words in **CAPITAL LETTERS** are user input

👉 Each word is separated by only a **single whitespace**


**Command | Description**
* `add –e EVENTNAME –st STARTTIME –sd STARTDATE –et ENDTIME –ed ENDDATE -v VENUE -r x D/W` | Add event
* `add –m MODULECODE -n CLASSNUMBER -l LESSONTYPE`
* `delete –s 1` | Delete a single event
* `delete -all` | Delete all events
* `list` | List all events
* `edit -i INDEX_OF_EVENT –st STARTTIME –sd STARTDATE –et ENDTIME –ed ENDDATE -v VENUE -r x D/W` | Edit event
