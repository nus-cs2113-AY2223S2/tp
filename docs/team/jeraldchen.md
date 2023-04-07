[comment]: <> (//@@author Jeraldchen)
# Jerald Chen - Project Portfolio Page

## Overview

## Project: DrDuke

DrDuke is a command line interface (CLI) application for managing patients' medical records. 
It can generate a diagnosis based on the symptoms provided by the user and then dispense the appropriate medicine.
This application also stores the patient's medical records in a text file for future reference.
It is optimized for use via a Command Line Interface (CLI). 
If you can type fast, DrDuke can get your patient management tasks done faster than traditional GUI apps. 
It is written in Java 11 and has about 5 kLoC. 


Given below are my contributions to the project.

### Summary of Contributions

* **New Feature**: Added the ability to reset a patient's diagnosis history.
  * What it does: allows the user to reset a patient's diagnosis history.
  * Justification: This feature improves the product significantly because a user can make a mistake when adding a diagnosis and 
  this feature allows the user to rectify the mistake.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of 
  design alternatives. The implementation too was challenging as it required changes to existing commands, any changes can result in 
  regression of the product as components may break.  


* **New Feature**: Added the ability to reset and delete a patient's symptom choice.
  * What it does: allows the user to reset a patient's symptom choice.
  * Justification: This feature improves the product significantly because a user can make a mistake when adding a symptom choice and 
  this feature allows the user to rectify the mistake.
  * Highlights: This enhancement brings greater convenience to the patient. At the start, a wrong input being keyed in
  will require the user to exit the program and login again which can be a hassle. This feature allows the user to rectify the mistake
    without having to exit the program.  
  

* **New Feature**: Added the ability to view symptom history.
  * What it does: allows the user to view a patient's symptom history.
  * Justification: The user could have forgotten what symptoms he/she had keyed in previously. This feature allows the user to view
    the symptom history and add/remove any symptoms that he/she may have forgotten.  


* **New Feature**: Link the commands to the parser to display menu options and output diagnosis and medications for the overall user interface experience.
  * What it does: allows the user to interact with the program.
  * Justification: This feature serves as the backbone of the program. Without this feature, the program will not be able to
    interact with the user.  


* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=jeraldchen&breakdown=true)
  * Search 'Jeraldchen' to find my contributions.
  
  
* **Project Management**:
  * Managed releases `v1.0` - `v2.0` (2 releases) on GitHub
  * Currently managing `v2.1`  
  

* **Enhancements to existing features**:
  * Wrote additional Junit tests to increase test coverage [#91](https://github.com/AY2223S2-CS2113-W13-1/tp/commit/201d52930f3778f2e6e9692a87d308021355ee0a)
  * Implemented try-catch blocks to handle exceptions and added defensive measures to prevent program from crashing [#92](https://github.com/AY2223S2-CS2113-W13-1/tp/commit/a8092485e3d44fb52760c7b17c8d658a2e9a3a88)  
    

* **Documentation**:
  * User Guide:
    * Added documentation for features like `resetSymptomChoice`, `viewSymptomHistory`, `deleteSymptom`, `resetDiagnosisHistory`, `addSymptomToSymptomList` and `parseSymptomInput` etc.
  * Developer Guide:
    * Added architecture diagram, class diagram interaction of UI and Sequence Diagram of Main Duke program.
    

* **Community**:
  * Reported bugs and suggestions to other teams in the class [#22](https://github.com/nus-cs2113-AY2223S2/tp/pull/22/files#r1151403222), [#18](https://github.com/nus-cs2113-AY2223S2/tp/pull/18/files#r1151416463), [#49](https://github.com/nus-cs2113-AY2223S2/tp/pull/49/files#r1151409910)
  * Engaged in discussions with other reviewers on how to improve our product [#134](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/134#issuecomment-1493253614), [#140](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/140)