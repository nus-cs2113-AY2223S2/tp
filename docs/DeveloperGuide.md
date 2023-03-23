# Developer Guide

---
## Acknowledgements

1. Command, Parser and UI java files are adapted from one of our group member's [Duke Project](https://github.com/MingEn82/tp)
---
## Design & implementation

---
### Architecture

![](../out/docs/uml/Architecture/architecture.svg)
**Main Components of LifeTracker**

`LifeTracker` is where users will access the application from. It is responsible for:

- At app launch: Initialise the Databases and UI classes
- At app closure: Saves all updated data to databases

The rest of the Application consists of three components:

- `UI`: Reponsible for I/O between user and application
- `Command`: Handles execution of user inputs
- `Storage`: Stores information of the user and meals eaten

___
# Appendix: Requirements

## Product scope


### Target user profile:

- Is an NUS student
- Looking to lose/maintain their weight
- Looking to make healthier choices in terms of food
- Can type fast
- Prefers typing to mouse interactions
- Is reasonably comfortable using CLI apps

### Value proposition:
For individuals trying to lose weight, managing their daily caloric intake is crucial. However, many may find it 
hard to track and manage their calories. _LifeTracker_ allows users to easily automate the tracking of their calories
and keep a record of their daily caloric intake. 

_LifeTracker_ can also keep track of the exercises that users have done and factor it in to their daily calories lost.

### User Stories
| Version | As a ... | I want to ...                                            | So that I can ...                                           |
|---------|----------|----------------------------------------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions                                   | refer to them when I forget how to use the application      |
| v1.0    | student  | calculate my caloric needs based on my height and weight | lead a healthy lifestyle within my caloric needs            |
| v1.0    | user     | view my previous meals                                   | track the calories of each meal                             |
| v1.0    | user     | key in my weight on a daily basis                        | keep track of my weight loss/gain                           |
| v2.0    | user     | find a to-do item by name                                | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java `11` installed

## Glossary

* *glossary item* - Definition

___
# Appendix: Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
