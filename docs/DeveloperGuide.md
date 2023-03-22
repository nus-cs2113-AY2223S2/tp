# Developer Guide

SEP Helper is a desktop application for Mechanical Engineering students, studying at the 
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).
---
## Table of Contents
1. [Acknowledgements](#acknowledgements)
2. [Design & Implementation](#design--implementation)
   1. [Help Command](#help-command)
   2. [Delete Command](#delete-command)
3. [Product Scope](#product-scope)
   1. [Target User Profile](#target-user-profile)
   2. [Value Proposition](#value-proposition)
4. [User Stories](#user-stories)
5. [Non-Functional Requirements](#non-functional-requirements)
6. [Glossary](#glossary)
7. [Instructions for Manual Testing](#instructions-for-manual-testing)
---
## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Storage

The Storage class handles the loading and storing of information into a single text file in the User's computer.
During the initialisation of the Storage class, which is at the start of the program, loading of data occurs.
From here on, querying of the Storage class is allowed, where modules saved by the user can be accessed through
the ListCurrentCommand. There are only two commands that  will cause changes to the text file. They are the AddCommand 
and DeleteCommand. This involves adding of new modules and deleting of old modules to the Storage. The text file will 
be continuously updated every time an Add or Delete command is called.

How module data is stored in text file:

Module information is stored in one single line separated by commas
`univID`,`moduleCode`,`moduleName`,`moduleMCs`,`nusModuleCode`,`nusModuleName`,`nusModuleMcs`


### Help Command
The help command provides a list of commands and the commands' respective input format for the user.  

The following sequence diagram shows the relationship between the classes involved when the delete command is called.

### Delete Command
The delete command removes a module from the user's saved list of modules that is specified by the user.  

The following sequence diagram shows the relationship between the classes involved when the delete command is called.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
