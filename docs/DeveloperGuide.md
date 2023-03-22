# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Implementation of MedicineManager class, Medicine class 
![img_1.png](MedicineSequenceDiagram.png)
*Sequence diagram for medicine class*

From the Parser class, a new MedicineManager class is created. The MedicineManager class initalises 2 hashtables
with medicines created with the Medicine class. Each medicine has a different name and dosage, which can be 
retrieved using the Medicine.toString() and Medicine.getDosages(). 

Diagnosis.getIllnessMatch() is called in the MedicineManager class, which returns an ArrayList of illnessMatch
objects. IllnessMatch objects contain information about the illness' name and the likelihood that the symptoms 
the user has inputted match the illness.

Each illness will be analysed in the analyseIllness() method, and the medications that cure that illness
will be suggested to the user. This information is then printed for the user to see.


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
