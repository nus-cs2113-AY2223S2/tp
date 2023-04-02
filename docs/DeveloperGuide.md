# Developer Guide

## Acknowledgements
[comment]: <> (//@@author tanyizhe)
HealthHub Singapore - www.healthhub.sg
Health Sciences Authority Singapore - www.hsa.giv.sg
Guardian Pharmacy - www.guardian.com.sg
Watsons Pharmacy - www.watsons.com.sg
MayoClinic - www.mayoclinic.org

## Design & implementation

[comment]: <> (//@@author Jeraldchen)

### Architecture
![Architecture.png](diagrams%2FArchitecture.png)

### Duke Main Sequence Diagram
![MainClass.png](diagrams%2FMainSequenceDiagram.png)

### User Interface
![UIClass.png](diagrams%2FMenuClassDiagram.png)

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

[comment]: <> (//@@author Brennanzuz)
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

![DiagnosisSequenceDiagram.png](diagrams%2FDiagnosisSequenceDiagram.png)

After the patient has entered all their symptoms, it will be passed to the `getPossibleIllnesses()` function
in `Diagnosis` where it will match each symptom the patient has to each symptom of all possible
illnesses `ALL_ILLNESSES`.

The percentage of matches the patient's symptoms has to the total number of symptoms the illness is specified to exhibit
is calculated, and if this percentage does not cross the `POSSIBILITY_THRESHOLD`, the patient would be declared to
possibly have the illness. To store this likely candidate, an `IllnessMatch` object is created storing the
candidate `Illness` and the `similarityPercentage`.

An `ArrayList<IllnessMatch> possibleIllnesses` is then returned to retrieve the appropriate medicine in another class.

[comment]: <> (//@@author tanyizhe)
### Implementation of MedicineManager class, Medicine class

![img_1.png](diagrams%2FMedicineSequenceDiagram.png)

*Sequence diagram for medicine class*

#### From the menu to the Medicine Manager

User input from the menu will first get parsed by the parser class.
From the Parser class, a new `MedicineManager` class is created. The `MedicineManager` class initialises 2 hash tables
with medicines created with the Medicine class. Each medicine has a different name and dosage, which can be
retrieved using the `Medicine.toString()` and `Medicine.getDosages()` methods.

#### Medicines

Medicine information is stored in two hash tables. One for matching the relevant medication with illnesses, the other
matching the medicines with correct dosages. Medicine information are 
stored in two separate hash tables so that only relevant information is obtained.
There are a lot of different kinds of medicines, which are all created as constants using the `final` keyword.
Information about medicines are sourced online from various pharmacies and their websites.

#### Analysing the symptoms and prescribing the medications

`Diagnosis.getIllnessMatch()` is called in the MedicineManager class, which returns an ArrayList of `illnessMatch`
objects. `IllnessMatch` objects contain information about the illness' name and the likelihood that the symptoms
the user has inputted match the illness as explained in the "Diagnosis" section above.

Each illness will be analysed in the `analyseIllness()` method, and the medications that are relevant 
to the mentioned illness will be suggested to the user. 
This information is then printed, and stored in the user's Medicine History.

#### Medicine History

The user's medicine history is stored in a dictionary within the `Storage` class. Whenever a user is prescribed
medication, the date of the prescription and medications prescribed are saved into a file, as explained below
in the Storage section of this Developer Guide. Medicine history of users is important and needs to be saved
to prevent abuse of the system and may cause undesirable effects such as overdose, if not properly monitored.

#### Listing and Finding Medicines

User can list and find available medicines using the application. Listing medicines calls the
`listMedicines()` method of the MedicineManager class. `listMedicines()` will collect all the keys of the
`medicineDosages` hash table, sort them using `Collections.sort()` and proceed to print them out in
alphabetical order.

[comment]: <> (//@@Thunderdragon221)
### Storage

![StorageClassDiagram.png](diagrams%2FStorageClassDiagram.png)

The Storage class is responsible for loading information from and writing information to a designated data file.

Upon each start up of the application, Dr Duke will call `loadData()`. If Dr Duke is started for the very first time 
on a device, the designated directory and file for all subsequent use will be created via `createDirectory()` and 
`createFile()`, which are part of the `loadData()` method. `readFile()` will then be called under `loadData()` to read
the data previously stored in the data file (if any). `readFile()` also calls `readMedicineHistoryFromFile()`, which 
reads in any past medicine dispensing records of the patient stored in the data file. `readFile()` throws
`CorruptedDataException` if any corruption in the data file is found in the process of reading from it, which is 
facilitated by the `endOfFile()` method.

During use of Dr Duke, if the patient decides to reset his/her diagnosis history, `resetDiagnosisHistory()` will be
called, which will call `saveData()` after resetting the patient's diagnosis history. After the patient has been
diagnosed with possible illnesses and dispensed appropriate medication, these records will also be saved by called
`saveData()`. Finally, before exiting Dr Duke, `saveData()` will also be called to write all existing data to the data
file.

### Implementation of loadData() in Storage Class
![loadDataSequenceDiagram.png](diagrams%2FloadDataSequenceDiagram.png)

### Implementation of saveData() in Storage Class
![saveDataSequenceDiagram.png](diagrams%2FsaveDataSequenceDiagram.png)

[comment]: <> (//@@Geeeetyx)

### Patient
![PatientClassDiagram.png](diagrams%2FPatientClassDiagram.png)

The Patient class is responsible for creating a patient object, which is required to store a user's information.

The method for creating a new Patient object is as follows:

~~~
new Patient(name, hash, diagnosisHistory, medicineHistory);
~~~

Once key information about the patient is obtained from the Menu class, the new patient object is created via:

~~~
Information.storePatientInfo(hash, new Patient(name, hash, diagnosisHistory, medicineHistory));
~~~

which is found in the Menu class.

#### Patient Class Methods:

The methods contained in the Patient Class are primarily used in the Information, Parser and Storage class.

The Patient class contains important getters and setters, allowing different classes to access the patient object and
the patient's information via the following methods:

`getName()` returns the name of the patient.

`getPassword()` returns the password of the patient's account.

`getPatientDiagnosisHistory()` allows for access to the array where past diagnoses are stored.

`getPatientMedicineHistory()` allows for access to the array where past prescribed medications are stored.

`setName()` and `setPassword()` allow the programme to set the patient's name and password.

`setPatientDiagnosisHistory()` and `setPatientMedicineHistory()` allow the programme to read data from a storage file,
and then set the stored data into the Patient object.

`updatePatientMedicineHistory()` and `updatePatientDiagnosisHistory()` allow us to add new diagnoses and medications
prescribed to the Patient object.

`appendMedicineToSameDate()` appends medicine patient is prescribed if additional medicine is prescribed on the same
day, allowing us to keep track of multiple prescriptions to one patient in one session.

## Product scope

### Target user profile

Doctor Duke is targeted at people who want to get diagnosed with certain **mild** illnesses they have
and/or get medicine to treat their illness without having to visit a General Practitioner.

### Value proposition

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
| v2.0    | experienced user                    | find what symptoms I have entered                                                                                | check if I entered the correct symptoms                                                                |
| v2.0    | meticulous patient                  | check what symptoms I have entered and have the option to delete certain ones                                    | make sure I key in the information accurately                                                          |
| v2.0    | nurse                               | use Doctor Duke to monitor when patients get their medication                                                    | keep tabs on anyone potentially misusing Dr Duke for medicine.                                         |

## Non-Functional Requirements

1. The programme must be secure and protects a patient's data, allowing access to the data either through an admin account,
or by the user himself.
2. The programme must be easy to use, with clear instructions and clean UI design.
3. The system must be reliable and function without errors or crashes.
4. The programme must be easy to maintain and update, with clear documentation and code organization.
5. The programme must comply with legal and regulatory requirements, such as data protection and privacy laws.
6. The programme should follow sound medical practices, by performing proper research into the different diagnoses and
medicines.

[//]: # (## Glossary)

[//]: # ()
[//]: # (* *glossary item* - Definition)

## Instructions for manual testing

Dummy accounts are provided for testing. First, access the Doctor Duke programme by following the steps in the
User Guide, which can be found [here](https://ay2223s2-cs2113-w13-1.github.io/tp/UserGuide.html).

Upon starting the programme, log in and enter the following name and password:

~~~
Name: Demo
Password: 1
~~~

This gives access to a dummy account "Demo", allowing for testing of Doctor Duke.
