# Developer Guide

<!-- @@author kishore-a00 -->

## Table of Contents
1. [Acknowledgements](#acknowledgements) 

2. [Design & Implementation](#design--implementation)
    * [Main overview of the system](#main-overview-of-the-system)
    * [Event Class](#event-class)
    * [Ui](#ui)
    * [Storage](#storage)
    * [Parser](#parser)
    * [Command](#command)
    * [Features](#features)
      * [Add feature](#add-feature)
      * [Duplication checker feature](#duplication-checker-feature)
      * [Delete feature](#delete-feature)
      * [List companies feature](#list-companies-feature)
      * [Store/Load Company information to/from text file feature](#storeload-company-information-tofrom-text-file-feature)
      * [Storing event details feature](#storing-event-details-feature)
    * [Exceptions](#custom-exceptions)
3. [Product scope](#product-scope)
    * [Target user profile](#target-user-profile)
    * [Value proposition](#value-proposition)
4. [User Stories](#user-stories)
5. [Future Implementations](#futurepossible-implementations)
6. [Non-Functional Requirements](#non-functional-requirements)
7. [Glossary](#glossary)
8. [Instructions for manual testing](#instructions-for-manual-testing)

<!-- @@author wuzhzn -->

## Acknowledgements

The CS2113 Team (Professor Akshay <3) for the guidance and teaching us concepts of Software Engineering and OOP

## Design & implementation

### Main overview of the system

* Main Components
  * Ui: Deals with user input/output
  * Storage: Deals with data management on hard disk
  * Parser: Deals with parsing user input
  * Command: Deals with handling command input logic

![broadOverview.png](UML%2FImage%2FbroadOverview.png)

Fig 1: Shows the architecture diagram of the main components of EveNtUs.
The basic description of the interaction between the class is as follows.

At the launch of the programme,the Storage class retrieves the information from text files and updates the event class in Fig 2.
It then goes into a waiting state, where user input will parsed and return as a Command. Following that, the command
will be executed and corresponding Ui messages will be displayed.


### Event Class

![event.png](UML%2FImage%2Fevent.png)

Fig 2: Shows the class Event that holds event details and the company list.

The Event class holds the information regarding the event details and company list, and this is the class that the Storage 
loads/save the information to/from. 

<!-- @@author Manoj364 -->

### Ui 

Ui class involves all the methods that display messages to the user when the user is interacting with the program.

Types of methods:

1. Greeting Messages: Messages shown to welcome user and acknowledge their exit
2. Confirmation Messages: Messages shown to inform user that their desired action has been carried out
3. Exception Messages: Messages shown to inform user of their invalid actions and its reasons


![Ui_class.png](UML%2FImage%2FUi_class.png)


Fig 3: Shows the class diagram of the `Ui` class. There are no attributes for the `Ui` class.

![Ui_sequence_diagram.png](UML%2FImage%2FUi_sequence_diagram.png)

Fig 4: Shows the sequence diagram for when `showWelcome` is called by `EveNtUS` during the startup of the application.

<!-- @@author wuzhzn -->

### Storage

![StorageOverview.png](UML%2FImage%2FStorageOverview.png)

Fig 5: Shows the super Storage class that the EventDetailsStorage, CompanyListEncoder, and CompanyListDecoder classes inherits from. 

The detailed implementation is explained in the features section. [Storage explanation](#storeload-company-information-to-text-file-feature)

### Parser

Parser involves dealing with parsing user input to understand what the user wants to do. It returns a 
<code>Command</code> class that it executed during the run-time. The possible commands are listed below. 

<!-- @@author AkmalHanis -->

### Command
Command classes are executed after the parser class has processed the user input.   

The following Command classes inherits from the main <code>Command</code> class and
executes different code.

![AddCommand_Class.png](UML%2FImage%2FAddCommand_Class.png)

Fig 6: Shows the list of commands that inherits from the Command class.

### Features

####  Add feature
The adding to company list feature  is facilitated by <code>AddCommand</code>. It will add new <code>Company</code> to existing
list of companies. It has four parameters, <code>companyName</code>, <code>industry</code>, <code>contactNumber</code>, and <code>contactEmail</code>.
After successfully executing the command, it will show successful addition message using
the related method in the <code>Ui</code> class.

![AddCommand2.png](UML%2FImage%2FAddCommand2.png)

Fig 7: Shows an high level overview of the <code>addCommand</code> execute method.

<!-- @@author Manoj364 -->

#### Duplication checker feature

The duplicationChecker is facilitated by <code>CompanyList</code>. It will check against the existing
<code>CompanyList</code> to check if the company has already been added. The check is done in 3 ways: `company name`,
`contact number`, and `contact email`. If any one of the above-mentioned details are identical to the ones already present
in the <code>CompanyList</code>, it will inform the user by displaying a message to the user. Otherwise, it will proceed to 
format the data that was entered by the user so that the parameters that are passed into <code>addCommand</code> are
standardized to remove any potential duplicated addition issues. 

![Duplication_checker.png](UML%2FImage%2FDuplication_checker.png)

Fig 8: Shows the sequence diagram for the Duplication Checker feature when the user adds a new company to the list

<!-- @@author kishore-a00 -->

####  Delete feature
The deleting company feature  is facilitated by <code>DeleteCommand</code>. It will delete the company at the specified 
index in the list of companies. After successfully executing the command,
it will show successful deletion message using the related method in the <code>Ui</code>.

<!-- @@author wuzhzn -->

#### List companies feature
The listing company feature  is facilitated by <code>ListCompanyCommand</code>. It will print out the companies
in the list of companies.
The Company object is then printed out with
an integer denoting its index, and four parameters, <code>companyName</code>, <code>industry</code>,
<code>contactNumber</code>, and <code>contactEmail </code>, and its confirmation status.

<!-- @@author kishore-a00 -->

####  Store/Load Company information to/from text file feature
The Storing company list feature will allow the user to save the information of companies to disk. A text file will be
created to store all the company information the user wishes to save. This text file will be overwritten everytime the
user makes changes to the stored information. 

When relaunching EventUS, the program will check if there is a text file which contains the information of previously 
saved companies. If the text file exists, the information of the companies
stored will be parsed and added to a local ArrayList. If the text file does not exist, it is created.

![Encoder.png](UML%2FImage%2FEncoder.png)

Fig 9: Shows how the <code>CompanyListEncoder</code> deals with saving the data to the text file.

![Decoder.png](UML%2FImage%2FDecoder.png)

Fig 10: Shows how the <code>CompanyListDecoder</code> deals with copying the data from the text file.

<!-- @@author wuzhzn -->

####  Storing event details feature
The eventDetailStorage feature will allow users to save the event details of the current session to hard disk.
A text file will be created to store information such as event name and venue information. This text file will be
overwritten everytime the user makes changes to the stored information, such as renaming the event name or changing the
venue of the event. When relaunching EventUS, the program will check if there is a text file which contains the
information of an event. If the text file exists, the information of the Event instance will be updated with the event
details. If there is no text file, a text file will be created.

![chooseVenueCommand.png](UML%2FImage%2FchooseVenueCommand.png)

Fig 11: Shows how the <code>ChooseVenueCommand</code> updates the venue in the <code>Event</code> class and updates
the text file through <code>EventDetailsStorage</code>

<!-- @@author kishore-a00 -->

### Custom Exceptions
Custom exceptions are created to catch erroneous user inputs that might otherwise cause EveNtUS to crash. When these 
exceptions are caught, specific error messages are displayed to the user to indicate why their input was unsuccessful.
These custom exceptions inherit from the Exception Class.

![Exceptions.png](UML%2FImage%2FExceptions.png)

Fig 12: Shows the class diagram of custom Exceptions used in EveNtUS

<!-- @@author wuzhzn -->

## Product scope
### Target user profile

EveNtUS targets career fair event managers who are looking to better manage the companies attending the career fair, as well as managing the venue and crowd size. 

### Value proposition

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient operation through the Command-Line Interface(CLI).

<!-- @@author de-yi -->

## User Stories

| Version | As a ...   | I want to ...                                                | So that I can ...                                                                               |
|------|------------|--------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| v1.0 | new user   | see usage instructions                                       | refer to them when I forget how to use the application                                          |
| v1.0 | basic user | be able to delete company information                        | delete wrongly added companies                                                                  |
| v1.0 | basic user | add a company to the company list                            | add desired companies                                                                           |
| v1.0 | basic user | list the companies that I have added                         | have a basic record of who is attending the event                                               |
| v2.0 | basic user | load some sample data                                        | get familiar with the application                                                               |
| v2.0 | basic user | select a venue for the event                                 | keep track of the venue information                                                             |
| v2.0 | basic user | purge the sample data                                        | start to input my data                                                                          |
| v2.0 | basic user | save and retrieve my data                                    | continue past sessions                                                                          |
| v2.0 | basic user | search for companies attending the career fair               | easily confirm their attendance without having to manually look through the entire list         |
| v2.0 | basic user | filter the companies based on the industries they belong     | easily look for a company in a specific industry                                                |
| v2.0 | basic user | prevent me from adding the same company twice                | prevent overlap of data                                                                         |
| v2.0 | basic user | tag the companies attendance status as confirmed/unconfirmed | ensure there are sufficient companies and any further follow-up action can be taken accordingly |
| v2.1 | basic user | update the name of the event                                 | customise the event name                                                                        |
| v2.1 | basic user | filter by venue size                                         | choose the correct venue                                                                        |

<!-- @@author wuzhzn -->

## Future/Possible implementations
1. Having more venues
2. User can print out the event details on the interface
3. Set dates for event

## Non-Functional Requirements

1. The programme should run even without the existence of the text file at the start
2. The programme should be able to handle user changing the text files
3. The programme should run on different Operating System

## Glossary

* *EveNtUS* - Name of our application
* *Ui* - Name of the class that deals with user input/output

## Instructions for manual testing

1. To load sample company data, run <code>load samples</code> to load some companies into company list
2. Type <code>help</code> for more information on the commands
3. To reset the company list and purge all company list data, run <code>purge</code>
