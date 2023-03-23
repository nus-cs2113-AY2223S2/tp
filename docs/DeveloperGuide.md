# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

[comment]: <> (//@@Jeraldchen)

### User Interface
![UIClass.png](diagrams%2FUIClass.png)

This feature shows how the user interface is being implemented via three main classes which are Menu, Information and Parser.

### Menu
The menu class is responsible for the user interface of the application. It is responsible for displaying the menu and
taking in user input. The menu class is also responsible for the main loop of the application, which is the loop that
continues to run until the user exits the application. The menu class allows patients to log in, register and exit the
program. 

`showWelcomeMenu()` is used to display the welcome menu to the user.
`register()` is used to register a new patient into the system.
`login()` is used to log a patient into the system.
`exit()` is used to exit the program.
`showAccountMenu()` is used to display the list of choices to the user.
`getUserSymptoms` is to ask input form user and store it in an arraylist.
`addSymptomToSymptomList()` is used to add the symptom to the symptom list.
`parseSymptomInput()` is used to parse the symptom input from the user.
`addSymptoms()` is used to add the symptoms to the symptom list.
`displaySymptomsList()` is used to display the list of symptoms to the user.
`displayPossibleIllnesses()` is used to display the list of possible illnesses to the user.

### Information
The information class is responsible for displaying information to the user. It is responsible for displaying the
information of the patient, the medicine and the diagnosis. It is also responsible for displaying the list of
symptoms and the list of illnesses. A hashmap is used to store the information of the patient and the hashed password.

`storePatientInfo()` is used to store the patient information into the hashmap. 
`getPatientInfo()` is used to retrieve the patient information from the hashmap.
`printDiagnosis()` is used to print the diagnosis of the patient.
`resetDiagnosticHistory()` is used to reset and clear the diagnostic history of the patient.
`checkHash()` is used to check if the hashed password is correct and matches.
`getAllPatientData()` is used to retrieve all the patient data from the hashmap.
`resetSymptomChoice()` is used to reset the symptom choice of the patient in case an error was made.
`hashPassword()` is used to hash the password of the patient.

### Parser
The parser class is responsible for parsing the user input. It is responsible for parsing the user input from the
menu and the symptom list. It is also responsible for parsing the user input from the medicine list.

`parseWelcome()` is used to parse the user input from the welcome menu.
`parseAccountCommand()` is used to parse the user command and then execute the command based on the choice number.

[comment]: <> (//@@Brennanzuz)
### Diagnosis

![DiagnosisClassDiagram.png](diagrams%2FDiagnosisClassDiagram.png)

#### Symptoms

All possible symptoms that patients may suffer from are included into this enumeration.

#### Illness

This class is inherited by all other illnesses. It contains the name of the illness `illnessName`, its `severityLevel`,
whether it is chronic `isChronic`, and most importantly, the list of symptoms specific to the illness `symptoms`.

Child classes of `Illness` are given their values on creation of the object in their constructor function.
Hence `new Diarrhoea()` will create an object with `.getSymptoms()` returning an `ArrayList<Symptom>`
of `Symptom.WET_STOOL`, `Symptom.STOMACH_ACHE`, and `Symptom.DIARRHOEA`.

Knowing this, each child object of `Illness` will be created and stored in `ArrayList<Illness> ALL_ILLNESSES`.

#### Diagnosis algorithm

After the patient has entered all their symptoms, it will be passed to the `getPossibleIllnesses()` function
in `Diagnosis` where it will match each symptom the patient has to each symptom of all possible
illnesses `ALL_ILLNESSES`.

The percentage of matches the patient's symptoms has to the total number of symptoms the illness is specified to exhibit
is calculated, and if this percentage does not cross the `POSSIBILITY_THRESHOLD`, the patient would be declared to
possibly have the illness. To store this likely candidate, an `IllnessMatch` object is created storing the
candidate `Illness` and the `similarityPercentage`.

An `ArrayList<IllnessMatch> possibleIllnesses` is then returned to retrieve the appropriate medicine in another class.

### Implementation of MedicineManager class, Medicine class

![img_1.png](MedicineSequenceDiagram.png)
*Sequence diagram for medicine class*

#### From the menu to the Medicine Manager

User input from the menu will first get parsed by the parser class.
From the Parser class, a new MedicineManager class is created. The MedicineManager class initialises 2 hash tables
with medicines created with the Medicine class. Each medicine has a different name and dosage, which can be
retrieved using the Medicine.toString() and Medicine.getDosages().

#### Medicines

Medicine information is stored in two hash tables. One for matching the relevant medication with illnesses, the other
matching the medicines with correct dosages.

#### Analysing the symptoms and prescribing the medications

Diagnosis.getIllnessMatch() is called in the MedicineManager class, which returns an ArrayList of illnessMatch
objects. IllnessMatch objects contain information about the illness' name and the likelihood that the symptoms
the user has inputted match the illness as explained in the "Diagnosis" section above.

Each illness will be analysed in the analyseIllness() method, and the medications that cure that illness
will be suggested to the user. This information is then printed, and stored in the user's account.

## Product scope

### Target user profile

{Describe the target user profile}

Doctor Duke is targeted at people who want to get diagnosed with certain **mild** illnesses they have
and/or get medicine to treat their illness without having to visit a General Practitioner.

### Value proposition

{Describe the value proposition: what problem does it solve?}

The current median wait times at a polyclinics across Singapore average at around 15-20 minutes,
(Source: 
[MOH](https://www.moh.gov.sg/resources-statistics/healthcare-institution-statistics/waiting-times-for-registration-and-for-consultation-at-polyclinics)).

This wait time might be too long for people who only have mild illnesses, and thus do not require consulting a general
practitioner, yet they do not know what medicine they require.

Thus, Doctor Duke aims to solve this issue, by creating a programme that can diagnose illnesses based on what symptoms
the patient might have, and dispense medicine and dosage accordingly, at a speed much faster than waiting at a clinic.

## User Stories

| Version | As a ...                            | I want to ...                                                                                                    | So that I can ...                                                                                      |
|---------|-------------------------------------|------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| v1.0    | new user                            | see instructions that are clear and succinct on how to use the app                                               | refer to them when I forget how to use the application, without needing to consult anyone              |
| v1.0    | new user                            | view medicine dosages prescribed easily                                                                          | take medication safely.                                                                                |
| v1.0    | new user                            | check what kind of illness I have based on a strange combination of symptoms I woke up with                      | find out if I need to consult a doctor and prevent myself from wasting time queueing to see one.       |
| v1.0    | office worker feeling a bit ill     | quickly get medicine during lunch break                                                                          | get back to work quickly                                                                               |
| v1.0    | patient who dislikes seeing doctors | be prescribed medication without consulting a doctor                                                             | avoid interacting with doctors.                                                                        |
| v1.0    | patient                             | to know whether my symptoms are serious enough to warrant a trip to the doctor or would some medicine be enough. | save time on visiting a doctor.                                                                        |
| v1.0    | medical professional                | identify more severe illnesses that patients might have                                                          | be referred to by Doctor Duke so that the patient can get the medical care required.                   |
| v1.0    | government official                 | only prescribes non-controlled drugs through Doctor Duke                                                         | monitor who is getting access to controlled substances.                                                |
| v1.0    | doctor                              | ensure Doctor Duke does not give out Medical Certificates                                                        | control the issuance of such certificates and they are only from a licensed and qualified professional |
| v1.0    | doctor                              | treat patients with mild illnesses quickly                                                                       | focus on patients with more severe illnesses.                                                          |
| v1.0    | doctor                              | set a cap on the amount of medicine prescribed                                                                   | avoid cases of patients overdosing.                                                                    |
| v2.0    | user                                | find a to-do item by name                                                                                        | locate a to-do without having to go through the entire list                                            |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

Dummy accounts are provided for testing. First, access the Doctor Duke programme by following the steps in the
User Guide, which can be found [here](https://ay2223s2-cs2113-w13-1.github.io/tp/UserGuide.html).

Upon starting the programme, log in and enter the following name and password:

~~~
Name: Demo
Password: 1
~~~

This gives access to a dummy account "Demo", allowing for testing of Doctor Duke.
