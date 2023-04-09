[comment]: <> (//@@author Geeeetyx)
# User Guide

## Introduction

Dr Duke is a desktop app for diagnosing patients who have **_mild_** illnesses, 
optimised for use via a Command Line Interface (CLI). 

Doctor Duke can diagnose illnesses and suggest medicine accordingly faster than visiting a General Practitioner.

Note: Dr Duke is NOT a multi-user application as defined by CS2113 rules, and does not violate any of the conditions
listed of a multi-user application by CS2113 definition.

## Start Guide

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of **Dr Duke** from [this GitHub repo](https://github.com/AY2223S2-CS2113-W13-1/tp/releases/tag/v2.0).
3. Download the jar file from the latest release, labelled as **[CS2113-W13-1][DrDuke].jar**.
4. Place the file in the folder you want to use as the home folder for your Duke.
5. Run the jar file via double-clicking on the JAR file, or by copying the file path and entering the copied
   filepath command into your terminal:

Example:

~~~
java -jar "C:\Users\Albert\Desktop\[CS2113-W13-1][DrDuke].jar"
~~~

# Features Guide

Upon starting Dr Duke, you will be greeted with a welcome message, 

A menu with options for registering and/or logging in will be shown.

Expected outcome:
~~~
Hello I am
 ____         ____        _        
|  _ \  ___  |  _ \ _  _ | | _____ 
| | | |/ _ \ | | | | | | | |/ / _ \
| |_| | |    | |_| | |_| |   <  __/
|____/|_|    |____/ \__,_|_|\_\___|

---------------------------------------------------
What would you like to do? Please enter the number:
1. Register
2. Login
3. Exit
---------------------------------------------------
~~~

## Register, Log In, Exiting

### 1. Registering

A new patient can register him/herself into Doctor Duke, by keying in his/her name and a password.

Format: Enter ``1`` to register.

Note: 
1. When keying in passwords, any white spaces in the passwords keyed in will be stripped.
2. There can be patients with the same name and hence username. Thus, we allow the same username with 2 or more passwords to access different accounts.

Expected Outcome: 
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1. Register
2. Login
3. Exit
---------------------------------------------------
1
---------------------------------------------------
Please enter your name: 
Demo
---------------------------------------------------
Please enter your password: 
3
-------------------------------
Please re-enter your password: 
3
---------------------------------------------------
Registration successful!
---------------------------------------------------
~~~

### Logging In

Format: Press ``2`` to log in.

Expected outcome: 
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1. Register
2. Login
3. Exit
---------------------------------------------------
2
---------------------------------------------------
Please enter your name: 
Demo
---------------------------------------------------
Please enter your password: 
3
---------------------------------------------------
Login successful!
Welcome Demo!
---------------------------------------------------
~~~

### Exiting

Format: Press ``3`` to exit.

Expected Outcome:
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1. Register
2. Login
3. Exit
---------------------------------------------------
3
---------------------------------------------------
Thank you for using
 ____         ____        _        
|  _ \  ___  |  _ \ _  _ | | _____ 
| | | |/ _ \ | | | | | | | |/ / _ \
| |_| | |    | |_| | |_| |   <  __/
|____/|_|    |____/ \__,_|_|\_\___|
---------------------------------------------------
~~~

## After Registering and/or Logging in

Upon registering and/or logging in, the following menu will be shown.

~~~
---------------------------------------------------
Login successful!
Welcome Demo!
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
~~~

### 1. Report Symptoms
The patient can choose what symptoms they have for Doctor Duke to diagnose.

Format: `1`

A list of symptoms will then be displayed to the user. 

~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
1
---------------------------------------------------
Here is the list of possible symptoms:
a. Fever
b. Dry Cough
c. Cough with Phlegm
d. Throat Irritation
e. Loss of Taste or Smell
f. Runny nose
g. Headache
h. Chills
i. Fatigue
j. Sneezing
k. Blocked Nose
l. Itchy eyes
m. Red eyes
n. Diarrhoea
o. Stomachache
p. Wet Stools
q. Hard or Lumpy Stools
r. Nausea
s. Vomiting
t. Sleeplessness
u. Blurred Vision
v. Sensitivity to Light and Sound
w. Muscle ache
x. Backache

Please enter a symptom.
---------------------------------------------------

~~~
The patient will then key in his/her symptoms accordingly, by keying in 'a' for fever, 'b' for Dry cough, 
so on and so forth, in one string or with spaces.

DO NOT spell out the symptom, just choose the option.

Both forms yield the same expected result.

Expected outcome:
```
Please enter a symptom.
---------------------------------------------------
abc
Do you have any other symptoms? [Y/N]
n
Your entered symptoms are: 
[FEVER, DRY_COUGH, COUGH_WITH_PHLEGM]
```
OR
```
Please enter a symptom.
---------------------------------------------------
abc
Do you have any other symptoms? [Y/N]
N
Your entered symptoms are: 
fever
dry cough
cough with phlegm
```

The patient may key in `Y` or `y`to continue reporting symptoms. Once the patient is finished reporting their symptoms, they can type `N` or `n` to finish reporting their symptoms.

After reporting their symptoms, Doctor Duke will then diagnose the patient, by:
1. Showing what illness the patient might have,
2. Displaying what medicine the patient should consume.

Note that if the combination of symptoms entered does not correspond to any known illness, the patient will be referred to the doctor instead of receiving medication for an unconfirmed illness.

Expected Outcome:
```
Please enter a symptom.
---------------------------------------------------
abc
Do you have any other symptoms? [Y/N]
N
Your entered symptoms are: 
fever
dry cough
cough with phlegm
============================================================================================
You may have: 
Sore Throat    Match: 66.67%
Fever    Match: 100.00%
-----------------------------------------------------------
Below are some recommended medications for you to purchase:
-----------------------------------------------------------
Medication for: Sore Throat
    Lozenges - Dosage: When you feel pain from sore throat
-----------------------------------------------------------
    Description: Lozenges are used to medicate the mouth and throat for the slow administration in digestion or 
    cough remedies. Lozenges may contain an anesthetic, a demulcent, or an antiseptic.
-----------------------------------------------------------
    Difflam Throat Spray - Dosage: 2 - 4 times every 1.5 - 3 hours until you feel better
-----------------------------------------------------------
    Description: This spray offers targeted and rapid symptom relief for hard-to-reach inflamed and 
    painful areas in your mouth and throat. It provides rapid relief from local inflammation 
    and pain from 60 seconds. 
-----------------------------------------------------------
Medication for: Fever
    Paracetamol - Dosage: 1 or 2 pills up to 3 times a day
-----------------------------------------------------------
    Description: Paracetamol is a commonly used medicine that can help treat pain and reduce a high temperature 
    It's typically used to relieve mild or moderate pain, such as headaches, 
    toothache or sprains, and reduce fevers caused by illnesses such as colds and flu.
-----------------------------------------------------------
End of diagnosis. Please proceed to your nearest pharmacy to purchase the above medications if applicable.
============================================================================================
```

### 2. View Diagnosis History
The patient can view his/her diagnosis history.

Format: Press `2` to view diagnosis history.

Expected outcome: 
```
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
2
---------------------------------------------------
Your diagnosis history is: 
2023/04/05: [Sore Throat, Fever]
---------------------------------------------------
```

### 3. Reset Diagnosis History
The patient can choose to reset his/her diagnosis history.

Format: Enter `3` to reset diagnosis history.

To test if the diagnosis history has been reset, enter `2` afterwards to check the diagnosis history.

Expected outcome: 
```
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
3
---------------------------------------------------
Your diagnosis history has been reset.
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
2
---------------------------------------------------
You have no past diagnoses
---------------------------------------------------
```
[comment]: <> (//@@author Jeraldchen)

### 4. Viewing Symptoms History
The patient can view the list of his/her symptoms that she has entered into Dr Duke.

Format: Enter `4` to choose to view the symptoms' history.

Expected outcome (if no symptoms were entered prior):
```
---------------------------------------------------
You have not entered any symptoms.
---------------------------------------------------
```

Else, expected outcome after entering some symptoms:
```
---------------------------------------------------
fever
dry cough
cough with phlegm
---------------------------------------------------
```
[comment]: <> (//@@author tanyizhe)
### 5. Delete Symptom Choice

The patient can choose to delete symptom(s) from the list of symptoms that they have
entered into Dr Duke.

Format: Enter `5` to choose to delete symptom(s).

Expected outcome (if no symptoms were entered prior):
```
---------------------------------------------------
You have not entered any symptoms.
---------------------------------------------------
```

If some symptoms have already been entered:
```
Here is the list of your symptoms:
1. nausea
2. runny nose
3. throat irritation
4. headache
Please enter the numbers of the symptom you want to delete.
Please put a space between each number to delete multiple symptoms.
---------------------------------------------------
1 2
Successfully deleted symptom(s)!
Here is the updated list of your symptoms:
1. throat irritation
2. headache
---------------------------------------------------
Below is your new diagnosis:
============================================================================================
You may have: 
Headache    Match: 100.00%
-----------------------------------------------------------
Medication for: Headache
    Paracetamol - Dosage: 1 or 2 pills up to 3 times a day
-----------------------------------------------------------
    Description: Paracetamol is a commonly used medicine that can help treat pain and reduce a high temperature 
    It's typically used to relieve mild or moderate pain, such as headaches, 
    toothache or sprains, and reduce fevers caused by illnesses such as colds and flu.
-----------------------------------------------------------
End of diagnosis. Please proceed to your nearest pharmacy to purchase the above medications if applicable.
============================================================================================
```

[comment]: <> (//@@author Geeeetyx)
### 6. Reset Symptoms
The patient can choose to reset his/her entered symptoms.

Format: Enter `6` to reset entered symptoms.

If the patient has not entered any symptoms prior to this, Doctor Duke will prompt the patient.\
We assume this occurs after the patient first logs in and attempts to reset the non-existent symptoms.

Expected outcome (if no symptoms were entered prior):
~~~
---------------------------------------------------
Login successful!
Welcome Demo!
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
6
--------------------------------------------------
You have not entered any symptoms. No symptoms to reset.
~~~

Else, expected outcome after entering some symptoms:
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
6
---------------------------------------------------
Your symptom choice has been reset.
---------------------------------------------------
~~~

### 7. Viewing Medicine History
The patient can choose to view his/her medicine history.

Format: Enter `7` to view past medicine history.


Expected outcome:
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
7
---------------------------------------------------
Medication History:
2023/03/22: [Paracetamol]
---------------------------------------------------
~~~
[comment]: <> (//@@author tanyizhe)
### 8. List all medicines
The patient can view all available medicines.

Format: enter `8` to list medicines.

Expected outcome:
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
8
---------------------------------------------------
List of available medications:
Aspirin
Dulcolax
Eye Drops
Guaifenesin
Ibuprofen
Lozenges
Magnesium
Paracetamol
Robitussin
Ultracarbon
---------------------------------------------------
~~~
### 9. Find medicine
The patient can find an available medicine.

Format: enter `9` to find a medicine. 
The program will prompt the patient for a keyword.

Expected outcome:
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. List available medicines
9. Find available medicine
0. Exit
---------------------------------------------------
9
---------------------------------------------------
Please enter a keyword:
---------------------------------------------------
a
---------------------------------------------------
Results for "a":
Aspirin
Dulcolax
Guaifenesin
Magnesium
Paracetamol
Ultracarbon
---------------------------------------------------
~~~

[comment]: <> (//@@author Geeeetyx)
### 10. Viewing your Queue Number
When a user logs in, he/she is assigned a queue number.

As of v2.1, this queue number feature is only a basic feature. With more work it could be further integrated with
an actual pharmercy to manage the queue of patients.

Format: Enter `10` to view your queue number.
~~~
---------------------------------------------------
Login successful!
Welcome Demo!
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
10
---------------------------------------------------
This is your queue number
---------------------------------------------------
7
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
~~~



### 0. Exiting Doctor Duke
Once the patient is done with the program, he/she can exit the program.

Format: Enter `0` to exit Doctor Duke.

Expected outcome:
~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1.  Report symptoms
2.  View diagnosis history
3.  Reset diagnosis history
4.  View symptoms History
5.  Delete symptom choice
6.  Reset symptoms
7.  View Medicine history
8.  List available medicines
9.  Find available medicine
10. Display Queue Number
0. Exit
---------------------------------------------------
0
---------------------------------------------------
Thank you for using
 ____         ____        _        
|  _ \  ___  |  _ \ _  _ | | _____ 
| | | |/ _ \ | | | | | | | |/ / _ \
| |_| | |    | |_| | |_| |   <  __/
|____/|_|    |____/ \__,_|_|\_\___|
---------------------------------------------------
~~~
[comment]: <> (//@@author Thunderdragon221)
### Appendix: Corrupted data
Once corruption is detected in any of the data files upon start 
up, Dr Duke will automatically exit WITHOUT SAVING with the 
following error message:
~~~
ERROR: Data file is corrupted. Clear all data files or 
restore data to uncorrupted state before trying again.
~~~
This is to give the user a chance to restore the data file(s) and
undo any tampering previously carried out, instead of 
mercilessly wiping all data from existence. The option is thus
given to the user to either restore the data or clear it entirely.

Should the corruption be undone, Dr Duke will next start up
successfully as if no corruption had ever existed.