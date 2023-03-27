[comment]: <> (//@@author Geeeetyx)
# User Guide

## Introduction

Doctor Duke is a desktop app for diagnosing patients who have **_mild_** illnesses, 
optimised for use via a Command Line Interface (CLI). 

Doctor Duke can diagnose illnesses and suggest medicine accordingly faster than visiting a General Practitioner.

## Start Guide

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of **Doctor Duke** from [this GitHub repo](https://github.com/AY2223S2-CS2113-W13-1/tp/releases/tag/v1.0).
3. Download the jar file from the latest release, labelled as **Tp.main.jar**.
4. Place the file in the folder you want to use as the home folder for your Duke.
5. Run the jar file via double-clicking on the JAR file, or by copying the file path and entering the following
   command into your terminal:

   ~~~
   java -jar "C:\Users\Albert\Desktop\Tp.main.jar"
   ~~~

# Features Guide
Upon starting Doctor Duke, you will be greeted with a welcome message, 

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
Format: Press ``2`` to register.

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
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit---------------------------------------------------
~~~

### 1. Report Symptoms
The patient can choose what symptoms they have for Doctor Duke to diagnose.

Format: `1`

A list of symptoms will then be displayed to the user. 

~~~
---------------------------------------------------
What would you like to do? Please enter the number:
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms history
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit
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

Both forms yield the same expected result.

Expected outcome:
~~~
Please enter a symptom.
---------------------------------------------------
abc
Do you have any other symptoms? [Y/N]
n
Your entered symptoms are: 
[FEVER, DRY_COUGH, COUGH_WITH_PHLEGM]
~~~
~~~
Please enter a symptom.
---------------------------------------------------
a b c
Do you have any other symptoms? [Y/N]
n
Your entered symptoms are: 
[FEVER, DRY_COUGH, COUGH_WITH_PHLEGM]
~~~

The patient may key in ``Y`` or ``y`` to continue reporting symptoms.

After reporting their symptoms, Doctor Duke will then diagnose the patient, by:
1. showing what illness the patient might have,
2. and then displaying what medicine the patient should consume.

Expected Outcome:
~~~
Please enter a symptom.
---------------------------------------------------
a b c
Do you have any other symptoms? [Y/N]
n
Your entered symptoms are: 
[FEVER, DRY_COUGH, COUGH_WITH_PHLEGM]
---------------------------------------------------
You may have: 
Sore Throat    Match: 66.66666666666666%
Fever    Match: 100.0%
---------------------------------------------------
Medication for: Sore Throat
    Lozenges / Dosage: When you feel pain from sore throat
Medication for: Fever
    Paracetamol / Dosage: 1 or 2 pills up to 3 times a day
---------------------------------------------------
~~~

### 2. View Diagnosis History
The patient can view his/her diagnosis history.

Format: Press `2` to view diagnosis history.

Expected outcome: 
```
---------------------------------------------------
What would you like to do? Please enter the number:
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit
---------------------------------------------------
2
---------------------------------------------------
Your diagnosis history is: 
---------------------------------------------------
Sore Throat
Fever
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
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit
---------------------------------------------------
3
---------------------------------------------------
Your diagnosis history has been reset.
---------------------------------------------------
What would you like to do? Please enter the number:
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit
---------------------------------------------------
2
---------------------------------------------------
You have no past diagnoses
---------------------------------------------------
```
[comment]: <> (//@@author Jeraldchen)

### 4. Viewing Symptoms History
The patient can view the list of his/her symptoms that she has entered into Dr Duke.

Format: Enter `4` to choose to view the symptoms history.

Expected outcome (if no symptoms were entered prior):
```
---------------------------------------------------
You have not entered any symptoms.
---------------------------------------------------
```

Else, expected outcome after entering some symptoms:
```
---------------------------------------------------
DRY_COUGH
COUGH_WITH_PHLEGM
FEVER
---------------------------------------------------
```
### 5. Delete Symptom Choice

The patient can choose to delete a symptom from the list of symptoms that she has entered into Dr Duke.

Format: Enter `5` to choose to delete a symptom.

Expected outcome (if no symptoms were entered prior):
```
---------------------------------------------------
You have not entered any symptoms.
---------------------------------------------------
```

Else, expected outcome after entering some symptoms:
```
---------------------------------------------------
Here is the list of your symptoms:
1. FEVER
2. DRY_COUGH
3. COUGH_WITH_PHLEGM
4. THROAT_IRRITATION
5. LOSS_OF_TASTE_OR_SMELL
6. RUNNY_NOSE
Please enter the number of the symptom you want to delete.
---------------------------------------------------
4
---------------------------------------------------
Successfully deleted symptom!
Here is the updated list of your symptoms:
1. FEVER
2. DRY_COUGH
3. COUGH_WITH_PHLEGM
4. LOSS_OF_TASTE_OR_SMELL
5. RUNNY_NOSE
---------------------------------------------------

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
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit
---------------------------------------------------
6
--------------------------------------------------------
You have not entered any symptoms. No symptoms to reset.
~~~

Else, expected outcome after entering some symptoms:
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
8. Exit
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
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. View symptoms History
5. Delete symptom choice
6. Reset symptoms
7. View Medicine history
8. Exit
---------------------------------------------------
7
---------------------------------------------------
Medication History:
2023/03/22: [Paracetamol]
---------------------------------------------------
~~~

### 8. Exiting Doctor Duke
Once the patient is done with the program, he/she can exit the program.

Format: Enter `8` to exit Doctor Duke.

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
8. Exit
---------------------------------------------------
8
---------------------------------------------------
Thank you for using
 ____         ____        _        
|  _ \  ___  |  _ \ _  _ | | _____ 
| | | |/ _ \ | | | | | | | |/ / _ \
| |_| | |    | |_| | |_| |   <  __/
|____/|_|    |____/ \__,_|_|\_\___|
---------------------------------------------------
~~~
[comment]: <> (//@@author)
