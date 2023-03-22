# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## View Feature
The View Feature is facilitated by `Parser`,`Ui`,`WorkoutList`,`Command` and `ViewCommand`, where the user will enter a specific workout date and the nuber of exercises on that date will be displayed

Below are the speciic steps on how to use the view fucntion and how the mechanism will flow:

* Step 1: We will assume that the user has started a workout on two specific dates, `11/02/22` which was added with the following command `/start 11/02/22`
and `12/02/22`, which was added with the following command `/start 12/02/22`.
* Step 2: The user will then use the following command `/view 11/02/22` will be taken into the parser
and will return a list of exercises done on that specified date.

## Product scope
### Target user profile

We aim to help fellow gym enthusiasts and new gym members to help keep
track of their future and present training.

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
