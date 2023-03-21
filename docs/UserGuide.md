# User Guide

## Introduction

Doctor Duke is a desktop app for diagnosing patients who have mild illnesses, 
optimised for use via a Command Line Interface (CLI). Doctor Duke can diagnose illnesses and dispense medicine accordingly faster 
than visiting a General Practitioner.

## Quick Start


1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](http://link.to/duke).
3. Download the jar file from the latest release.
4. Copy the file to the folder you want to use as the home folder for your Duke.
5. Run the jar file using the command `java -jar {filename}.jar` in the terminal. Make sure you are in the same directory as the jar file.

## Features (Before Registering and Logging in) 

### Register, Login and Exit: `1, 2, 3`
Dr Duke's welcome message will be displayed. You can register, login or exit the program.

Welcome Message: 
```
Hello I am
 ____         ____        _        
|  _ \  ___  |  _ \ _  _ | | _____ 
| | | |/ _ \ | | | | | | | |/ / _ \
| |_| | |    | |_| | |_| |   <  __/
|____/|_|    |____/ \__,_|_|\_\___|

What would you like to do? Please enter the number:
1. Register
2. Login
3. Exit
```

Format: Press `1` or `2` or `3` to register, login or exit the program.

Example of usage: 
`1`

Expected outcome: 
```
Please enter your name: {Patient to key in name}

Please enter your password: {Patient to key in password}

PLease re-enter your password: {Patient to key in password again}

Registration successful!
```

Example of usage: `2`

Expected outcome: 
```
Please enter your name: 
Demo
Please enter your password: 
1
Login successful!
Welcome Demo!
What would you like to do? Please enter the number:
1. Report symptoms
2. View diagnosis history
3. Reset diagnosis history
4. Exit
```

Example of usage: `3`

```
Thank you for using Dr Duke!
```

## Features (After Registering and Logging in)

### Report Symptoms: `1`
Patient can report their symptoms and Dr Duke will diagnose the patient's illness and dispense medicine accordingly.

Format: `1`

Example of usage: `1`

Expected outcome: 
```
Here is the list of possible symptoms:
a. Fever
b. Dry Cough
c. Cough with phlegm
d. Runny nose
e. Headache
f. Chills
g. Fatigue
h. Sneezing
i. Blocked Nose
Please enter a symptom.
f
Do you have any other symptoms? [Y/N]
y
Here is the list of possible symptoms:
a. Fever
b. Dry Cough
c. Cough with phlegm
d. Runny nose
e. Headache
f. Chills
g. Fatigue
h. Sneezing
i. Blocked Nose

Please enter a symptom.
g
Do you have any other symptoms? [Y/N]
y
Here is the list of possible symptoms:
a. Fever
b. Dry Cough
c. Cough with phlegm
d. Runny nose
e. Headache
f. Chills
g. Fatigue
h. Sneezing
i. Blocked Nose

Please enter a symptom.
h
Do you have any other symptoms? [Y/N]
n
Your entered symptoms are: 
[CHILLS, FATIGUE, SNEEZING]
You may have: 
General Flu 66.66666666666666%
Medication for: General Flu
    Ibuprofen / Dosage: 1 or 2 pills every 4 to 6 hours
    Aspirin / Dosage: 1 or 2 pills every 4 to 6 hours
    Robitussin / Dosage: 20ml every 12 hours
```

### View Diagnosis History: `2`
Patient can view their diagnosis history.

Format: `2`

Example of usage: `2`

Expected outcome: 
```
Your diagnosis history is: 
General Flu
```

### Reset Diagnosis History: `3`
Patient can reset their diagnosis history.

Format: `3`

Example of usage: `3`

Expected outcome: 
```
Your diagnosis history has been reset.
```