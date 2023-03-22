# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
<br>
### [Proposed] Duplication checker feature
#### Proposed Implementation
The proposed <code>duplicationChecker</code> is facilitated by <code>Parser</code>. It will check against the existing
<code>CompanyList</code> to check if the company has already been added. If the same company already exists in the
<code>CompanyList</code>, it will inform the user by displaying a message to the user. Otherwise, it will proceed to 
format the data that was entered by the user so that the parameters that are passed into <code>addCommand</code> are
standardized to remove any potential duplicated addition issues.

## Product scope
### Target user profile

{Describe the target user profile}

EveNtus targets career fair event managers who are looking to better manage the companies attending the career fair, as well as managing the venue and crowd size. 

### Value proposition

{Describe the value proposition: what problem does it solve?}

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient operation through the Command-Line Interface(CLI).


## User Stories

| Version | As a ... | I want to ... | So that I can ...|
|---------|----------|---------------|------------------|
| v1.0    |new user|see usage instructions|refer to them when I forget how to use the application|
| v2.0    |user|find a to-do item by name|locate a to-do without having to go through the entire list|
| v2.0    |basic user|purge the sample data|so that I can start to input my data|


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

1. To load sample company data, run "load samples" to load some companies into company list. 
2. To purge all company list data, run "purge".
3. To choose a venue for the event, run "choose venue (index)".