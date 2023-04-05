# User Guide

## Table of Contents
1. Introduction
2. Quick Start
3. Features
4. Summary of the commands

## Introduction

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient operation through the Command-Line Interface(CLI).


## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Download the latest version of `EvenNtUS` from [here](https://github.com/AY2223S2-CS2113-W12-2/tp/releases/tag/V2.0).
3. Copy the jar file to a folder.
4. Open Command Prompt in the folder and enter the command <code>java -jar tp.jar</code>
5. You can now use EveNtUS!

## Features

### `help` - Shows help guide
Shows the help guide that contains the features that are available for use, as well as the features' corresponding 
syntax for user input

Example of usage: `help`

Expected outcome:
```
help
____________________________________________________________
Here are the functions of the application!
To display this guide at any time, type:
help
To list companies, type:
list companies
To list venues, type:
list venues
To list unconfirmed attendees, type:
list unconfirmed
To add a company, type:
add n/<COMPANY_NAME> i/<INDUSTRY> c/<CONTACT_NUMBER> e/<EMAIL>
To delete company, type:
delete <INDEX>
To mark a company's attendance, type:
confirm <INDEX>
To unmark a company's attendance, type:
unconfirm <INDEX>
To load sample data, type:
load samples
To purge company list data, type:
purge
To search for a company, type:
find company <COMPANY_NAME>
To filter according to industry, type:
find industry <INDUSTRY>
To choose a venue, type:
choose venue <INDEX>
____________________________________________________________
____________________________________________________________
```


### `list companies` - Shows all current stored companies
Shows all companies currently stored in the company list.

Example of usage:
`list companies`
Expected outcome: 

```
____________________________________________________________
1
Company name: APPLE
Company contact number: 89934125
Company contact email: STEVENJOBS@APPLE.COM.SG
Company industry: TECHNOLOGY
[Unconfirmed]
2
Company name: KFC
Company contact number: 81213999
Company contact email: FRIEDCHICKEN@KFC.COM.SG
Company industry: ENGINEERING
[Unconfirmed]
3
Company name: DBS
Company contact number: 82920010
Company contact email: IBANKING@GMAIL.COM
Company industry: BANKING AND FINANCE
[Confirmed]
____________________________________________________________
```

### `list venues` - Shows all venues
Shows all venues available for the user to choose

Example of usage:
`list venues`
Expected outcome:

```
____________________________________________________________
1. Engineering Auditorium 9 Engineering Drive 1 (S) 117576 100
2. Hon Sui Sen Auditorium 1 Hon Sui Sen Drive (S) 117588 100
3. LT1 10 Kent Ridge Crescent (S) 119260 100
4. LT6 1 Hon Sui Sen Drive (S) 119260 100
5. University Cultural Centre 50 Kent Ridge Crescent (S) 119279 50
____________________________________________________________
```

### `confirm` - Marks a specific company's attendance as confirmed
Mark the status of a specific company's attendance as confirmed, and 
updates a status icon to [Confirmed] that represents it being marked confirmed.

Example of usage:
`confirm 1`
Expected outcome:

```
____________________________________________________________
Company has been successfully confirmed!
____________________________________________________________

```

### `unconfirm` - Marks a specific company's attendance as unconfirmed
Mark the status of a specific company's attendance as unconfirmed, and
updates a status icon to [Unconfirmed] that represents it being marked unconfirmed.

Example of usage:
`unconfirm 1`
Expected outcome:

```
____________________________________________________________
Company has been successfully uncomfirmed!
____________________________________________________________

```
### `list unconfirmed` - Shows all stored companies marked with unconfirmed attendance
Shows all the unconfirmed companies that are stored in the company list.

Example of usage:
`list unconfirmed`
Expected outcome:

```
____________________________________________________________
2
Company name: KFC
Company contact number: 81213999
Company contact email: FRIEDCHICKEN@KFC.COM.SG
Company industry: ENGINEERING
[Unconfirmed]
3
Company name: DBS
Company contact number: 82920010
Company contact email: IBANKING@GMAIL.COM
Company industry: BANKING AND FINANCE
[Unconfirmed]
____________________________________________________________
```

### `add ` - Add a company to the company list
Add the company to the list of companies, and updates the company list text file.

Example of usage:
`add n/tesla i/tech c/12308712 e/tesla@gmail.com`
Expected outcome:

```
____________________________________________________________
TESLA added successfully!
____________________________________________________________
```

### `delete ` - Delete a company from the company list
Delete a company from the company list, and updates the company list text file.

Example of usage:
`delete 1`
Expected outcome:

```
____________________________________________________________
Company information successfully deleted!
____________________________________________________________
```

### `load samples` - load samples company data for manual testing
Populate the company list with sample companies, and updates the company list text file

Example of usage:
`load samples`
Expected outcome:

```
____________________________________________________________
HUAWEI added successfully!
____________________________________________________________
____________________________________________________________
GOOGLE added successfully!
____________________________________________________________
____________________________________________________________
TIKTOK added successfully!
____________________________________________________________
____________________________________________________________
Sample data has been loaded into the list!
____________________________________________________________
```
### `purge ` - delete the company list data
delete the company list data, and updates the company list text file

Example of usage:
`purge`
Expected outcome:

```
____________________________________________________________
Data has been deleted successfully!
____________________________________________________________
```

### `find companies ` - find the companies based on a company name
find the companies based on a company name, or any of the alphabets in the company name

Example of usage:
`find company e`
Expected outcome:

```
____________________________________________________________
The company is found in the company list.
1
Company name: HUAWEI
Company contact number: 80060114
Company contact email: APSUPPORT@HUAWEI.COM
Company industry: TECH
[Unconfirmed]
The company is found in the company list.
2
Company name: GOOGLE
Company contact number: 91002500
Company contact email: GOOGLE@GOOGLE.COM
Company industry: TECH
[Unconfirmed]
____________________________________________________________
```

### `find industry <INDUSTRY>  ` - find the companies within an industry
find the companies within an industry based on the industry name 

Example of usage:
`find industry tech`
Expected outcome:

```
____________________________________________________________
Here are the companies in <TECH> field.
1.
Company name: HUAWEI
Company contact number: 80060114
Company contact email: APSUPPORT@HUAWEI.COM
Company industry: TECH
[Unconfirmed]
2.
Company name: GOOGLE
Company contact number: 91002500
Company contact email: GOOGLE@GOOGLE.COM
Company industry: TECH
[Unconfirmed]
____________________________________________________________
```

### `choose venue <index>` - updates the venue of the event
Updates the venue of the event from a list of venues, and updates the event details file

Example of usage:

`choose venue 1` would choose venue 1 which is Engineering Auditorium

`choose venue 3` would choose venue 3 which is LT1

Expected outcome:

```
____________________________________________________________
Engineering Auditorium is your venue!
____________________________________________________________
```
OR
```
____________________________________________________________
LT1 is your venue!
____________________________________________________________
```


### `exit ` - Exit the application
Exit the application

Example of usage:
`exit`
Expected outcome:

```
____________________________________________________________
Bye!
____________________________________________________________
```

## Command Summary

| Command                                                       | Usage                                              |
|---------------------------------------------------------------|----------------------------------------------------|
| help                                                          | display the commands of the application            |
| list companies                                                | list the companies                                 |
| list venues                                                   | list the venues                                    |
| list unconfirmed                                              | list the companies yet to confirm their attendance |
| add n/[COMPANY_NAME] i/[INDUSTRY] c/[CONTACT_NUMBER] e/[EMAIL] | add a company to the company list                  |
| delete [INDEX]                                                | delete a company in the company list               |
| confirm [INDEX]                                               | confirm a companies' attendance                    |
| unconfirm [INDEX]                                             | unconfirm a companies' attendance                  |
| load samples                                                  | load samples company data for manual testing       |
| purge                                                         | delete the company list data                       |
| find company [COMPANY_NAME]                                   | find the companies based on a company name         |
| find industry [INDUSTRY]                                      | find the companies within an industry              | 
| choose venue [INDEX]                                          | updates the venue of the event                     |
| update event name [EVENT_NAME]                                | updates the name of the event                      |
| exit                                                          | exit the application                               |