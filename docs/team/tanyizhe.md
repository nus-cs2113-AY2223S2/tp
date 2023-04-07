[comment]: <> (//@@author tanyizhe)
# Tan Yi Zhe - Project Portfolio Page


## Overview
Dr. Duke is a Command-Line Interface application that can take in symptoms from the user
and diagnose the user with an illness. Dr. Duke is not a replacement for an actual doctor! 
The main aim of Dr. Duke is to allow users who don't feel so well, but not in need 
of serious medical attention, to get an idea of what illness they might have without
consulting a doctor.

After Diagnosis, Dr. Duke will prescribe relevant medication available over-the-counter
to the user. The medication prescribed gives the user an idea of what medications
can be used to treat their current ailment.

### Summary of Contributions
* **Code contributed**: [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=tanyizhe&breakdown=true)
* **Added features** :
    * Added MedicineManager and Medicine classes [#25](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/25):
        * **What it does**: Acts as the link between illnesses and medicines.
        * **Justification**: These classes are essential for the prescription function of the application to work.
    
    * Added Logic to Menu to get patient symptoms and prescribe medication [#43](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/43),
  [#44](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/44):
      * **What it does**: Takes in symptoms that user inputs, diagnose the user with an illness,
        and prescribes medication to the user.
      * **Justification**: The most important part of the application, links the Menu, Illness and Medicine
        parts of the application together.
    
    * Added feature to allow user to see their Medicine History [#68](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/68),
      [#81](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/81):
      * **What it does**: Records down medicine user was prescribed along with the date of the
        prescription. Records are saved in a text file with other information.
      * **Justification**: It is important for the user to be able to see their medicine history.
        This could potentially prevent an overdose of medication due to carelessness or neglect.
    
    * Added feature to list and find medicines [#102](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/102):
      * **What it does**: Lists all available medicines and find an available medicines using a keyword.
      *  **Justification**: Users need to be aware of what medicines they are consuming and
       make an informed choice regarding the medicine they choose to consume.
    
    * Added feature to delete multiple symptoms at once [#186](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/186):
      * **What it does**: Program can now take in multiple numbers with a space between them, and will delete each 
            selected symptom.
      * **Justification**: This feature is introduced after one of our tester's feedback. They wish to be able to delete 
        multiple symptoms at once. It is useful for veteran users of Dr. Duke.
* **Improved features** :
  * Improved symptom delete feature:
    *  **What it does**: Users are now able to delete multiple symptoms with one command.
    *  **Justification**: Experienced users may want to delete multiple symptoms in one go,
        rather than deleting each symptom one by one.
  * Wrote additional Junit tests to increase test coverage [#172](https://github.com/AY2223S2-CS2113-W13-1/tp/pull/172)
* **Documentation**:
    * User Guide:
        * Added documentation for features such as List Medicines and Find Medicine. Updated User guide accordingly
            whenever improvement is made.
        * Updated expected output accordingly when new feature is implemented.
    * Developer Guide:
        * Added sequence diagram for MedicineManager class. Wrote detailed explanation regarding
            how MedicineManager, Medicine, Parser, and IllnessMatch classes work and how they interact.
  * Community contributions:
    * Reported bugs for other teams:
      * [#1](https://github.com/tanyizhe/ped/issues/1), [#2](https://github.com/tanyizhe/ped/issues/2), [#3](https://github.com/tanyizhe/ped/issues/3), [#4](https://github.com/tanyizhe/ped/issues/4), [#5](https://github.com/tanyizhe/ped/issues/5), [#6](https://github.com/tanyizhe/ped/issues/6), [#7](https://github.com/tanyizhe/ped/issues/7), [#8](https://github.com/tanyizhe/ped/issues/8), [#9](https://github.com/tanyizhe/ped/issues/9), [#10](https://github.com/tanyizhe/ped/issues/10), [#11](https://github.com/tanyizhe/ped/issues/11), [#12](https://github.com/tanyizhe/ped/issues/12), [#13](https://github.com/tanyizhe/ped/issues/13), [#14](https://github.com/tanyizhe/ped/issues/14)
