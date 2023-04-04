[comment]: <> (//@@author Geeeetyx)

# Gareth Tan - Project Portfolio Page

## Overview

## Project: Dr Duke

Dr Duke is an application used to automate diagnosing patients with mild illnesses.
The main outline of Dr Duke is as follows:

1. It asks a patient for his/her symptoms and gives them a diagnosis of illness what the patient might have based on the
   symptoms entered.
2. Dr Duke then recommends certain medications, that can be bought from a local pharmacy, that can help to cure the mild
   illness the patient has.
3. If the illness is determined to be severe or not mild, Dr Duke would then recommend the patient to visit a General
   Practitioner instead.

The Dr Duke programme is designed for use via a Command Line Interface, thus for user that can type fast, Dr Duke give
you a diagnosis faster than visiting a General Practitioner.
It is written in Java 11 and has about 5 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

* **Code contributed**:
  [Reposense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=geeeetyx&breakdown=true)

* **Added Features**:
    * Added Patient class [#16](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/16)
        * **What it does**  : Acts as the main object to be used throughout the Dr Duke programme.
        * **Justification** :
            1. Stores the key information about the user, which are name, password, diagnosis history and medicine
               history.
            2. Used in other classes in Dr Duke such as Storage and Menu

    * Added Queue system [#117](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/117)
        * **What it does**  : A queue number system, based on how many users have logged in to the Dr Duke programme.
        * **Justification** :
            1. The main purpose is to implement a basic feature that can be built upon should Dr Duke be deployed in
               actual pharmacies.
            2. Simulates a queue number system, meant to be implemented in a pharmacy to manage a queue in a pharmacy,
               like in a normal polyclinic.

* **Enhancements to Existing Features**:
    * Added more Over-The-Counter Medicines [#118](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/118)
        * **What it does**  : Improves the database of medicines that Dr Duke has, to be more in line with the medicines
          actually found in pharmacies.
        * **Justification** : Sets the foundation for more medicines that can be found in pharmacies to be added to Dr
          Duke.

    * Added checkers and output messages if patient does not enter any
      symptom [#170](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/170)
        * **What it does**  : Ensures the patient enters symptoms before Dr Duke gives a diagnosis.
        * **Justification** : This was a bug found during a review found by other teams. See
          Issue [#129](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/129).

    * Dr Duke re-diagnoses the patient when the patient deletes previously entered
      symptoms [#170](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/170)
        * **What it does**  : Dr Duke now gives the patient a new diagnosis after the patient chooses to delete certain
          symptoms that were previously chosen.
        * **Justification** : To improve the flow of the programme and thus the user experience.

    * Improved UI of Dr Duke [#78](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/78)
        * **What it does**  : Improves the design of what the user sees when using Dr Duke.
        * **Justification** : For better user experience.

* **Documentation**:
    * **User Guide** :
        * Laid the groundwork for the improved User Guide [#78](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/78)

    * **Developer Guide** :
        * Added explanation of the Patient class [#94](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/94)

        * Added class diagram of the Patient class [#84] (https://github.com/AY2223S2-CS2113-W13-1/tp/pull/84)

        * Added user stories, value proposition and manual testing
          instructions [#85](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/85)

* **Community**:
    * Reported bugs for other teams
        * [#1](https://github.com/Geeeetyx/ped/issues/1)
        * [#2](https://github.com/Geeeetyx/ped/issues/2)
        * [#3](https://github.com/Geeeetyx/ped/issues/3)
        * [#4](https://github.com/Geeeetyx/ped/issues/4)
        * [#5](https://github.com/Geeeetyx/ped/issues/5)
        * [#6](https://github.com/Geeeetyx/ped/issues/6)
        * [#7](https://github.com/Geeeetyx/ped/issues/7)
        * [#8](https://github.com/Geeeetyx/ped/issues/8)
        * [#9](https://github.com/Geeeetyx/ped/issues/9)
        * [#10](https://github.com/Geeeetyx/ped/issues/10)
        * [#11](https://github.com/Geeeetyx/ped/issues/11)
        * [#12](https://github.com/Geeeetyx/ped/issues/12)
        * [#13](https://github.com/Geeeetyx/ped/issues/13)
        * [#14](https://github.com/Geeeetyx/ped/issues/14)

    * Commented on feedback received for Dr Duke v2.0
        * [#153](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/153)
        * [#150](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/150)
        * [#149](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/149)
        * [#136](https://github.com/AY2223S2-CS2113-W13-1/tp/issues/136)
