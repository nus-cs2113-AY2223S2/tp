[comment]: <> (//@@author Thunderdragon221)
# Ryan Tan - Project Portfolio Page

## Overview

## Project: Dr Duke

Dr Duke is a command line interface (CLI) application used to diagnose patients with mild illnesses.
Dr Duke is able to offer patients diagnoses based on their symptoms, recommend suitable medication to
purchase from pharmacies and store patients' medical records for future reference.
It is optimized for use via a Command Line Interface (CLI). 
If you can type fast, Dr Duke can give you a suitable diagnosis faster than visiting a General Practitioner. 
It is written in Java 11 and has about 5 kLoC. 


Given below are my contributions to the project.

### Summary of Contributions

* **New Feature**: Added the ability to display the user's diagnosis history.
  * What it does: allows the user to view his/her diagnosis history.
  * Justification: This feature improves the product significantly because the user can view his/her past diagnoses
  to see if he/she is prone to getting certain illnesses.

* **New Feature**: Added the ability to read in user symptoms.
  * What it does: allows the user to input any symptoms he/she has.
  * Justification: Allows the user to input his/her symptoms into Dr Duke to get a suitable diagnosis and relevant
  medication recommendations.

* **New Feature**: Added the ability to write and read data to and from a designated text file.
  * What it does: allows Dr Duke to keep track of past information entered for each user.
  * Justification: Allows the user to make use of other features to view past data that Dr Duke stores.
  * Highlights: This feature is immensely useful since it is the foundation that allows each user to access past 
  information related to him/her stored in Dr Duke, as well as any information he/she has input in the current
  session.

* **New Feature**: Added the ability to detect corruption in data files by checking validity of data fields.
  * What it does: allows Dr Duke to identify any data corruption present in data files.
  * Justification: Important as it prevents the program from working with corrupted data.
  * Highlights: This feature is extremely useful as it prevents the user from tampering with the data fields in the 
  text files, and raises a CorruptedDataException once any form of corruption is detected.


* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2023-02-17&until=2023-04-02&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
  * Search 'Thunderdragon221' to find my contributions.
  
  
* **Project Management**:
  * Managed releases `v1.0` - `v2.0` (2 releases) on GitHub
  * Currently managing `v2.1`  
  

* **Enhancements to existing features**:
  * Added tracking of dates to diagnoses of the user [#173](https://github.com/AY2223S2-CS2113-W13-1/tp/commit/511392b7462fb2315695f3fc39a9132642b5bf7d)
  * Implemented hashing of users' passwords as a form of security [#67](https://github.com/AY2223S2-CS2113-W13-1/tp/commit/91fd01f7a77507a48f045d5154d1eee58ae99b2f)
  * Added Storage Junit test class to test code functionality of Storage class [#112](https://github.com/AY2223S2-CS2113-W13-1/tp/commit/25588b4810094e8b680926d24dde59e626a3a378)
  * Implemented data file hashing to prevent program crash in the event of any intentional corruption in the data files [#164](https://github.com/AY2223S2-CS2113-W13-1/tp/commit/80a035704833f71c2aec09d3b441971445dea18e)  
    

* **Documentation**:
  * User Guide:
    * Added documentation on how Dr Duke handles corrupted data files, as well as declaration that Dr Duke does not 
    violate CS2113 definitions on multi-user applications in case users mistakenly believe it to be so.
  * Developer Guide:
    * Added Storage class diagram and Sequence Diagrams for Loading and Saving of data of the Duke program.
    

* **Community**:
  * Reported bugs and gave suggestions to another team [#32](https://github.com/nus-cs2113-AY2223S2/tp/pull/32/files/bcc9614448e1a1398ae817ead6e06652a4558e6e)
  * Commented on JAR file feedback received for V2.0 [#141](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/141), [#151](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/151), [#156](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/156)