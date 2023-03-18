# Apollo Developer Guide

## Table of Contents

1. [Acknowledgments](#acknowledgements)
2. [Design](#design)
    + [Architecture](#architecture)
    + [UI Component](#ui-component)
    + [Logic Component](#logic-component)
    + [Model Component](#model-component)
    + [Storage Component](#storage-component)
    + [Common Classes](#common-classes)
3. [Implementation](#implementation)
    + [Add Module](#add-module)
    + [Delete Module](#delete-module)
    + [List Module](#list-module)
    + [Add Task](#add-task)
    + [Delete Task](#delete-task)
    + [Mark Task](#mark-task)
    + [Unmark Task](#unmark-task)
    + [Find Task](#find-task)
    + [List Task](#list-task)
    + [Find Task on Date](#find-task-on-date)
    + [Storage](#storage)
    + [Logging](#logging)
4. [Documentation, logging, testing, configuration, dev-ops](#documentations-logging-testing-configuration-dev-ops)
    + [Documentation](#documentation)
    + [Logging](#logging)
    + [Testing](#testing)
    + [Configuration](#configuration)
    + [Dev-ops](#dev-ops)
5. [Appendix A: Requirements](#appendix-a-requirements)
    + [Product Scope](#product-scope)
        + [Target User Profile](#target-user-profile)
        + [Value Proposition](#value-proposition)
    + [User Stories](#user-stories)
    + [Use Cases](#use-cases)
    + [Non-Functional Requirements](#non-functional-requirements)
    + [Glossary](#glossary)
6. [Appendix B: Instructions for manual testing](#appendix-b-instructions-for-manual-testing)
    + [Launch and Shutdown](#launch-and-shutdown)

## Delete Module {#delete-module}

The DeleteModule functionality allows users to remove a module from the ModuleList.
It is facilitated by DeleteModuleCommand class which is an extension of the Command class.
Below are the steps to recreate it

Step 1: Define the Constructor :
The constructor of the DeleteModuleCommand class takes in a moduleCode as a parameter. This moduleCode is used to find
the module that needs to be deleted from the ModuleList.

Step 2: Define the setUpLogger() method :
The setUpLogger() method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

Step 3: Override the execute() method :
The execute() method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, and TaskList.

Step 4: Find the module to delete :
The first step in the execute() method is to find the module that needs to be deleted using the moduleCode parameter. If
the module is not found, a ModuleNotFoundException is thrown.

Step 5: Remove the module from the ModuleList :
If the module is found, it is removed from the ModuleList using the remove() method.

Step 6: Print the confirmation message :
A confirmation message is printed to the user indicating that the module has been successfully deleted.

Step 7: Update the storage :
The storage is updated with the new ModuleList

## Acknowledgements

We would like to acknowledge:

## Design & implementation

### Architecture

### UI Component

### Logic Component

### Model Component

### Storage Component

### Common Classes

## Documentation, logging, testing, configuration, dev-ops

### Documentation

### Logging

### Testing

### Configuration

### Dev-ops

## Appendix A: Requirements

### Product scope

#### Target user profile

The target user profile is an average NUS student who:

* has a need to manage a significant number of tasks (todo, event, deadline) and modules,
* prefer desktop applications over other types of applications,
* can type fast,
* prefers typing to mouse interactions,
* is reasonably comfortable using CLI apps.

#### Value proposition

Existing schedulers do not have access to NUS’s database, making it so that a student here would have to manually
input all their lessons. We can expedite this process by creating a scheduler that sets itself up via module codes.
It can also alert the user to possible event clashes.

### User Stories

| Priority | Version | As a ... | I want to ...             | So that I can ...                                           |
|:--------:|:-------:|:--------:|:--------------------------|-------------------------------------------------------------|
|          |  v1.0   | new user | see usage instructions    | refer to them when I forget how to use the application      |
|          |  v2.0   |   user   | find a to-do item by name | locate a to-do without having to go through the entire list |

## Use Cases

## Non-Functional Requirements

* Should work on any mainstream OS as long as it has Java 11 or above installed.
* Should be able to hold up to 1000 tasks without a noticeable sluggishness in performance for typical usage.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* *Mainstream OS* - Windows, Linux, Unix, OS-X
* *CLI* - Command Line Interface
*

## Appendix B: Instructions for manual testing

Given below are instructions to test the app manually.
> Note: These instructions only provide a starting point for testers to work on;
> testers are expected to do more *exploratory* testing. 
