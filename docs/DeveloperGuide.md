# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
<br

<img src="eventus.png">

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

## Product scope
### Target user profile

{Describe the target user profile}

EveNtus targets career fair event managers who are looking to better manage the companies attending the career fair, as well as managing the venue and crowd size. 

### Value proposition

{Describe the value proposition: what problem does it solve?}

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient operation through the Command-Line Interface(CLI).


## User Stories

| Version | As a ...   | I want to ...                         | So that I can ...                                            |
|---------|------------|---------------------------------------|--------------------------------------------------------------|
| v1.0    | new user   | see usage instructions                | refer to them when I forget how to use the application       |
| v1.0    | basic user | be able to delete company information | keep track of only the companies who are attending the event |
| v2.0    | user       | find a to-do item by name             | locate a to-do without having to go through the entire list  |
| v2.0    | basic user | purge the sample data                 | so that I can start to input my data                         |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

1. To load sample company data, run "load samples" to load some companies into company list. 
2. To purge all company list data, run "purge".
3. To choose a venue for the event, run "choose venue (index)".
4. To delete company information, run "delete (index)".