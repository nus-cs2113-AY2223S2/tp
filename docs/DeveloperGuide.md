# Developer Guide

## Acknowledgements

The CS2113 Team (Professor Akshay <3) for the guidance and teaching us concepts of Software Engineering and OOP

## Design & implementation

### Main overview of the system:

* Main Components
  * Ui: Deals with user input/output
  * Storage: Deals with data management on hard disk
  * Parser: Deals with parsing user input
  * Command: Deals with handling command input logic

![broadOverview.png](UML%2FImage%2FbroadOverview.png)

Fig 1


### Ui 

Ui class involves all the methods

Types of methods:

<code>seq of 1 ui</code>


### Storage


<code>sequence diagram</code>

### Parser



### Command


<code>List of commands</code>

<code> Seq of 1 command</code>

### [Proposed] Duplication checker feature
#### Proposed Implementation
The proposed <code>duplicationChecker</code> is facilitated by <code>Parser</code>. It will check against the existing
<code>CompanyList</code> to check if the company has already been added. If the same company already exists in the
<code>CompanyList</code>, it will inform the user by displaying a message to the user. Otherwise, it will proceed to 
format the data that was entered by the user so that the parameters that are passed into <code>addCommand</code> are
standardized to remove any potential duplicated addition issues.

### [Proposed] Storage feature
#### Proposed Implementation
The proposed Storage feature will allow the user to save the information of companies to disk. A text file will be 
created to store all the company information the user wishes to save. This text file will be overwritten everytime the 
user makes changes to the stored information. When relaunching EventUS, the program will check if there is a text file 
which contains the information of previously saved companies. If the text file exists, the information of the companies 
stored will be copied over to a local ArrayList. If there is no text file, a text file will be created as soon as the 
user tries to add company information.

### [Proposed] Add feature
#### Proposed Implementation
The proposed <code>CompanyList.add</code> is facilitated by <code>AddCommand</code>. It will add new company to existing 
list of companies. It has three parameters, <code>String companyName </code>, <code>Int contactNumber </code>, and <code>
String contactEmail</code>. Using this information, <code>CompanyList.add</code> creates new Company object and adds it 
at the end of the company list. After successfully executing the command, it will show successful addition message using
the related method in the <code>Ui</code>.

### [Proposed] Storing event details feature
#### Proposed Implementation
The proposed eventDetailStorage feature will allow users to save the event details of the current session to hard disk. 
A text file will be created to store information such as event name and venue information. This text file will be 
overwritten everytime the user makes changes to the stored information, such as renaming the event name or changing the 
venue of the event. When relaunching EventUS, the program will check if there is a text file which contains the 
information of an event. If the text file exists, the information of the Event instance will be updated with the event 
details. If there is no text file, a text file will be created.

### [Proposed] List companies feature
#### Proposed Implementation
The proposed <code>CompanyList.printCompanyInformation</code> is facilitated by <code>ListCompanyCommand</code>. It will
print out the companies that are stored in an ArrayList called companyList. The Company object is then printed out with 
an integer denoting its index, four parameters, <code>String companyName </code>, <code>String industry </code>, 
<code>Int contactNumber </code>, and <code>String contactEmail </code>, and its confirmation status. It will print out 
each company from the entire companyList from index 0 to the final index of the last company stored in companyList.



## Product scope
### Target user profile

EveNtUS targets career fair event managers who are looking to better manage the companies attending the career fair, as well as managing the venue and crowd size. 

### Value proposition

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient operation through the Command-Line Interface(CLI).


## User Stories

| Version | As a ...   | I want to ...                         | So that I can ...                                            |
|---------|------------|---------------------------------------|--------------------------------------------------------------|
| v1.0    | new user   | see usage instructions                | refer to them when I forget how to use the application       |
| v1.0    | basic user | be able to delete company information | keep track of only the companies who are attending the event |
| v2.0    | user       | find a to-do item by name             | locate a to-do without having to go through the entire list  |
| v2.0    | basic user | load some sample data                 | so that I can get familiar with the application              |
| v2.0    | basic user | select a venue for the event          | keep track of the venue information                          |
| v2.0    | basic user | purge the sample data                 | so that I can start to input my data                         |



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
2. To reset the company list and purge all company list data, run <code>purge</code>
3. To choose a venue for the event, run <code>choose venue (index)</code>.
4. To delete company information, run <code>delete (index)</code>.