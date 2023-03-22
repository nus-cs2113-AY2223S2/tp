# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Diagnosis
![DiagnosisClassDiagram.png](diagrams%2FDiagnosisClassDiagram.png)
#### Symptoms
All possible symptoms that patients may suffer from are included into this enumeration.
#### Illness
This class is inherited by all other illnesses. It contains the name of the illness `illnessName`, its `severityLevel`, whether it is chronic `isChronic`, and most importantly, the list of symptoms specific to the illness `symptoms`.

Child classes of `Illness` are given their values on creation of the object in their constructor function. Hence `new Diarrhoea()` will create an object with `.getSymptoms()` returning an `ArrayList<Symptom>` of `Symptom.WET_STOOL`, `Symptom.STOMACH_ACHE`, and `Symptom.DIARRHOEA`.

Knowing this, each child object of `Illness` will be created and stored in `ArrayList<Illness> ALL_ILLNESSES`.
#### Diagnosis algorithm
After the patient has entered all their symptoms, it will be passed to the `getPossibleIllnesses()` function in `Diagnosis` where it will match each symptom the patient has to each symptom of all possible illnesses `ALL_ILLNESSES`.

The percentage of matches the patient's symptoms has to the total number of symptoms the illness is specified to exhibit is calculated, and if this percentage does not cross the `POSSIBILITY_THRESHOLD`, the patient would be declared to possibly have the illness. To store this likely candidate, an `IllnessMatch` object is created storing the candidate `Illness` and the `similarityPercentage`.

An `ArrayList<IllnessMatch> possibleIllnesses` is then returned to retrieve the appropriate medicine in another class.
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
