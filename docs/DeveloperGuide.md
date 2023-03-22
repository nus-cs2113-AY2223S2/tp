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

### Implementation of MedicineManager class, Medicine class
![img_1.png](MedicineSequenceDiagram.png)
*Sequence diagram for medicine class*
#### From the menu to the Medicine Manager
User input from the menu will first get parsed by the parser class.
From the Parser class, a new MedicineManager class is created. The MedicineManager class initialises 2 hashtables
with medicines created with the Medicine class. Each medicine has a different name and dosage, which can be
retrieved using the Medicine.toString() and Medicine.getDosages().

#### Medicines
Medicine information is stored in two hashtables. One for matching the relevant medication with illnesses, the other
matching the medicines with correct dosages.

#### Analysing the symptoms and prescribing the medications
Diagnosis.getIllnessMatch() is called in the MedicineManager class, which returns an ArrayList of illnessMatch
objects. IllnessMatch objects contain information about the illness' name and the likelihood that the symptoms
the user has inputted match the illness (as explained in the "Diagnosis" section above.

Each illness will be analysed in the analyseIllness() method, and the medications that cure that illness
will be suggested to the user. This information is then printed, and stored in the user's account.


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
