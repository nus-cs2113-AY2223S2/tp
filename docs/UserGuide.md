# User Guide

<!-- @@author de-yi -->

## Table of Contents

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features):

    * [help](#help)
    * [load samples](#load-samples)
    * [add](#add)
    * [choose venue](#choose-venue)
    * [update event name](#update-event-name)
    * [confirm](#confirm)
      | [unconfirm](#unconfirm)
    * [list companies](#list-companies)
      | [venues](#list-venues)
      | [unconfirmed](#list-unconfirmed)
    * [delete](#delete)
      | [purge](#purge)
    * [find companies](#find-companies)
      | [industry](#find-industry)
    * [filter venues](#filter-venues)
    * [exit](#exit)

4. [Summary of the Commands](#summary-of-the-commands)

<!-- @@author wuzhzn -->

## Introduction

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient operation through the Command-Line Interface(CLI).

<!-- @@author kishore-a00 -->

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `EvenNtUS` from [here](https://github.com/AY2223S2-CS2113-W12-2/tp/releases/tag/V2.0).
3. Copy the jar file to a folder.
4. Open Command Prompt in the folder and enter the command <code>java -jar tp.jar</code>
5. You can now use EveNtUS!

<!-- @@author Manoj364 -->

## Features

* Words written in upper case between square brackets([ ]) are parts that users can change.

### Help

Shows the help guide that contains the features that are available for use, as well as the features' corresponding
syntax for user input

* Format: `help`

* Example of usage: `help`

* Expected outcome:

```
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
                    
To filter according to venue size, type:
                    filter venues <SIZE>
 
To choose a venue, type:
                    choose venue <INDEX>

To update the event name, type:
                    update event name <EVENT_NAME>
                     
To exit the program, type:
                    exit
 
____________________________________________________________
____________________________________________________________
```

<!-- @@author de-yi -->

### Load Samples
Populate the company list with sample companies, and updates the company list text file

* Format: `load samples`

* Example of usage: `load samples`

* Expected outcome:

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
* If some of the sample companies has already been added into the list
* Expected output:
```
____________________________________________________________
Company already exists in the list!
____________________________________________________________
____________________________________________________________
Company already exists in the list!
____________________________________________________________
____________________________________________________________
Company already exists in the list!
____________________________________________________________
____________________________________________________________
Sample data has been loaded into the list!
____________________________________________________________
```

<!-- @@author de-yi -->

### Add
Add the company to the list of companies, and updates the company list text file.

* Format: `add n/[COMPANY_NAME] i/[INDUSTRY] c/[CONTACT_NUMBER] e/[EMAIL]`

    * All fields should not be empty.
    * `[COMPANY_NAME]` can be any character.
    * `[INDUSTRY]` should contain at least one alphabet.
    * `[CONTACT_NUMBER]` should be valid Singaporean number, which is 8-digit number starting with 3, 6, 8, or 9. Spaces between numbers would be automatically removed.
    * `[EMAIL]` should be valid email address containing but not does not start/end with "@" symbol. No space is allowed.
    * User can add only one company at a time and not repeat any of the fields.

* Example of usage: `add n/tesla i/tech c/34567890 e/tesla@gmail.com`

* Expected outcome:

```
____________________________________________________________
TESLA added successfully!
____________________________________________________________
```

<!-- @@author Manoj364 -->

* The same company cannot be added twice. If a company with existing name or contact detail is added, a warning will be displayed to the user
  instead.

* Expected outcome:
```
____________________________________________________________
Company already exists in the list!
____________________________________________________________
```

<!-- @@author wuzhzn -->

### Choose Venue
Updates the venue of the event from a list of venues, and updates the event details file.

To see the list of venues, refer to [list venues](#list-venues)

* Format: `choose venue [INDEX]`
    * `[INDEX]` must be in the range of the list of venues.
    * There must only be one space between `venue` and `[INDEX]`.

* Example of usage:

    * `choose venue 1` would choose venue 1 which is Engineering Auditorium
    * `choose venue 3` would choose venue 3 which is LT1

* Expected outcome:

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

### Update Event Name
Updates the event name of the event and updates the event details file.

* Format: `update event name [EVENT_NAME]`
    * `[EVENT_NAME]` must not be empty.

* Example of usage:

    * `update event name CDE fair` would update the event name to CDE fair.

* Expected outcome:

```
____________________________________________________________
CDE fair is your event name!
____________________________________________________________
```

<!-- @@author AkmalHanis -->

### Confirm
Mark the status of a specific company's attendance as confirmed, and
updates a status icon to `[Confirmed]` that represents it being marked confirmed.

* Format: `confirm [INDEX]`
  * There must only be one space between `confirm` and `[INDEX]`.


* Example of usage: `confirm 1`

* Expected outcome:

```
____________________________________________________________
This company has been successfully confirmed!
____________________________________________________________

```

* If the company has already been marked as confirmed
* Expected outcome:

```
____________________________________________________________
This company is already confirmed!
____________________________________________________________

```

### Unconfirm
Mark the status of a specific company's attendance as unconfirmed, and
updates a status icon to `[Unconfirmed]` that represents it being marked unconfirmed.

* Format: `unconfirm [INDEX]`
  * There must only be one space between `unconfirm` and `[INDEX]`.


* Example of usage: `unconfirm 1`

* Expected outcome:

```
____________________________________________________________
This company has been successfully unconfirmed!
____________________________________________________________

```

* If the company has already been marked as unconfirmed
* Expected outcome:

```
____________________________________________________________
This company is already unconfirmed!
____________________________________________________________

```

### List Companies
Shows all companies currently stored in the company list.

* Format: `list companies`
* Example of usage: `list companies`
* Expected outcome:

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

<!-- @@author wuzhzn -->

### List Venues
Shows all venues available for the user to choose

* Format: `list venues`

* Example of usage: `list venues`

* Expected outcome:

```
____________________________________________________________
1. Engineering Auditorium 9 Engineering Drive 1 (S) 117576 100
2. Hon Sui Sen Auditorium 1 Hon Sui Sen Drive (S) 117588 100
3. LT1 10 Kent Ridge Crescent (S) 119260 100
4. LT6 1 Hon Sui Sen Drive (S) 119260 100
5. University Cultural Centre 50 Kent Ridge Crescent (S) 119279 50
____________________________________________________________
```

<!-- @@author AkmalHanis -->

### List Unconfirmed
Shows all the unconfirmed companies that are stored in the company list.

* Format: `list unconfirmed`

* Example of usage: `list unconfirmed`

* Expected outcome:

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

<!-- @@author kishore-a00 -->

### Delete
Delete a company from the company list, and updates the company list text file.

* Format: `delete [INDEX]`
    * There must only be one space between `delete` and `[INDEX]`.

* Example of usage: `delete 1`

* Expected outcome:

```
____________________________________________________________
Company information successfully deleted!
____________________________________________________________
```

* If the company list is empty
* Expected outcome:

```
____________________________________________________________
Nothing inside company list
____________________________________________________________

```

<!-- @@author wuzhzn -->

### Purge
Delete the company list data, and updates the company list text file

* Format: `purge`

* Example of usage: `purge`

* Expected outcome:

```
____________________________________________________________
Data has been deleted successfully!
____________________________________________________________
```

* If the company list is empty
* Expected outcome:

```
____________________________________________________________
Nothing inside company list
____________________________________________________________

```

<!-- @@author de-yi -->

### Find Companies
Find the companies based on a company name, or any of the alphabets in keyword provided by the user.

* Format: `find companies [KEYWORD]`

* Example of usage: `find companies e`

* Expected outcome:

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

<!-- @@author de-yi -->

### Find Industry
Find the companies within an industry based on the keyword provided by the user.

* Format: `find industry [KEYWORD]`
    * `[KEYWORD]` must be the exact industry that the company is in.

* Example of usage: `find industry tech`

* Expected outcome:

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

* If there are no companies in that industry, it will still print a similar message. 
* Expected outcome if no companies in ABC industry:

```
____________________________________________________________
Here are the companies in <ABC> field.
____________________________________________________________

```

<!-- @@author kishore-a00 -->

### Filter Venues
Filters and returns venues that have a capacity greater than or equal to the size specified by the user.

* Format: `filter venues [SIZE]`
  * `[SIZE]` must be an integer greater than or equal to zero.
  * There must only be one space between `venues` and [SIZE].

* Example of usage: `filter venues 80`

* Expected outcome:

```
____________________________________________________________
1. Engineering Auditorium 9 Engineering Drive 1 (S) 117576 100
2. Hon Sui Sen Auditorium 1 Hon Sui Sen Drive (S) 117588 100
3. LT1 10 Kent Ridge Crescent (S) 119260 100
4. LT6 1 Hon Sui Sen Drive (S) 119260 100
____________________________________________________________
```

<!-- @@author wuzhzn -->

### Exit
Exit the application

* Format: `exit`

* Example of usage: `exit`

* Expected outcome:

```
____________________________________________________________
Bye!
____________________________________________________________
```

<!-- @@author Manoj364 -->

## Summary of the Commands

| Command                                                        | Usage                                              |
|----------------------------------------------------------------|----------------------------------------------------|
| help                                                           | display the commands of the application            |
| list companies                                                 | list the companies                                 |
| list venues                                                    | list the venues                                    |
| list unconfirmed                                               | list the companies yet to confirm their attendance |
| add n/[COMPANY_NAME] i/[INDUSTRY] c/[CONTACT_NUMBER] e/[EMAIL] | add a company to the company list                  |
| delete [INDEX]                                                 | delete a company in the company list               |
| confirm [INDEX]                                                | confirm a companies' attendance                    |
| unconfirm [INDEX]                                              | unconfirm a companies' attendance                  |
| load samples                                                   | load samples company data for manual testing       |
| purge                                                          | delete the company list data                       |
| find company [COMPANY_NAME]                                    | find the companies based on a company name         |
| find industry [INDUSTRY]                                       | find the companies within an industry              | 
| filter venues [SIZE]                                           | filter the venues by size                          | 
| choose venue [INDEX]                                           | updates the venue of the event                     |
| update event name [EVENT_NAME]                                 | updates the name of the event                      |
| exit                                                           | exit the application                               |
