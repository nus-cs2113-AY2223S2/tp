# Developer Guide

## Acknowledgements

1. The CS2113 Team (Professor and Teaching Assistants) for their guidance in teaching us concepts of Software Engineering and OOP. 
2. AddressBook3 Guidance and examples for OOP and project aspects were used from this repository.
3. Used IDE Sequence Diagram tool for Sequence Diagrams. 
4. Used [Draw.io](https://www.draw.io/index.html) to draw our UML diagram.

## Design & implementation

![img_31.png](img_31.png) <br>
**Figure 1: High Level UML Diagram of Sniff Appointment Manager** <br>

![img_38.png](img_38.png) <br>
**Figure 2: Sequence Diagram for .run() method of Sniff Appointment Manager** <br>

### User Interface (UI) - Class Implementation
1. The **`UI class`** named `Ui` belongs to the package functionalities.ui. The class contains several static and non-static methods that display messages to the user such as **`showUserMessage()`**, **`showErrorMessage()`**, and **`showWelcomeMessage()`**. The class is also used to read user input, format and print appointment lists, and add or remove appointments from the **`sniff appointment manager`**. The **`Ui`** class has a **`showLine()`** method that displays a divider line to the user.
2. The **`UI class`** has a private static final String DOT_THEN_SPACE field that is used as a constant string value to format user messages. It has a **`readUserCommand()`** method that returns a string value that is entered by the user through the Command Line Interface (CLI).
3. The class imports the **`SniffException`** class from the exception package and utilizes it in the **`showErrorMessage()`** method to display an error message to the user.

### Command - Class Implementation
1. The **`Command class`** named `Command` belongs to the package functionalities.commands. The class is an abstract class that provides a basic template for implementing commands in the command-line interface. This class is designed to be extended by subclasses that implement specific commands, such as **`ListCommand`** and **`ConsultationCommand`**.
2. The **`Command class`** has a boolean isExit field that can be set to true to exit the entire programme, or remain as false to continue running the programme. It has a **`isExit()`** method that sets that field to false initially. It also has a **`executeCommand()`** method that takes an instance of SniffTasks as a parameter and throws a SniffException if an error occurs during execution. Subclasses override this method in order to run other commands.

![img_35.png](img_35.png)<br>
**Figure 3: Sequence Diagram showing the logical implementation of executeCommand() for the EditConsultation Command**

![img_36.png](img_36.png)<br>
**Figure 4: Sequence Diagram showing the logical implementation of executeCommand() for the EditVaccination Command**

![img_37.png](img_37.png)<br>
**Figure 5: Sequence Diagram showing the logical implementation of executeCommand() for the EditSurgery Command**

#### Editing Appointments
1. The `UI` class reads in the input from the user and then parses them depending on the starting initial of UID in the `Parser` for each appointment.
2. If the inputs are valid, and the UID exists (it is an existing appointment), the appointment is removed by `Remove Command` and then `AppointmentCommand` is created and then executed, otherwise an exception is thrown.
3. The `Snifftasks` class then edits the appointment to the list of current appointments.#### Editing Appointments
1. The `UI` class reads in the input from the user and then parses them depending on the starting initial of UID in the `Parser` for each appointment.
2. If the inputs are valid, and the UID exists (it is an existing appointment), the appointment is removed by `Remove Command` and then `AppointmentCommand` is created and then executed, otherwise an exception is thrown.
3. The `Snifftasks` class then edits the appointment to the list of current appointments.

### Parser - Class Implementation
1. The Parser class takes in a user command and generates a corresponding Command object for veterinary management system tasks such as **add consultation, vaccination or surgery, find, remove, list, and exit**. This implementation makes use of the Command design pattern to encapsulate the behavior of different types of commands, and the parser serves as a factory for creating these commands based on the user input.
2. The Parser class contains several static methods that parse different types of commands, such as **`ConsultationCommand`**, **`VaccinationCommand`**, **`SurgeryCommand`**, **`FindCommand`**, **`RemoveCommand`**, **`ListCommand`**, and **`ExitCommand`**.
3. The **`parse()`** method takes a user command as a String named **`task`** and determines the type of command based on the first word of the command. If it matches any of the known command types, it delegates parsing to the corresponding parse method.
4. If the first word of the command does not match any known type, it throws a **`SniffException`**. Each parse method takes the command String as input and uses substring operations to extract the various parameters of the command. It then creates a new Command object with these parameters and assigns it to the "command" static variable.
5. Finally, the parse method returns the command object. If an exception occurs during parsing, it throws a **`SniffException`**.

### Storage - Class Implementation 
![img_23.png](img_23.png) <br>
**Figure 7.1: Sequence Diagram of Storage class** 

![img_24.png](img_24.png) <br>
**Figure 7.2: Sequence Diagram of Storage class**
1. The Storage class takes in the path of the Sniff storage file.
2. **`openFile(String filePath)`** method reads and adds the SniffAppointments contents into the Appointments task list.
3. **`saveAppointments(String filePath)`** method saves the Archived task contents into the SniffArchive File.
4. **`extractData`** method parses the saved file and identify stored appointments. Depending on the type of appointments, it will then call either **`readConsultationintoAppointmentList()`** / **`readVaccinationintoAppointmentList()`** / **`readSurgeryintoAppointmentList()`** to add these appointment objects into **`ArrayList<Appointment> APPOINTMENTS`**.
5. If the file is stored in an incorrect format / has missing details, a **`SniffException`** is thrown.
6. At the end of the application all unmarked appointments are saved into the `SniffAppointments.txt` file using the `saveAppointments` method.

### Archive - Class Implementation
![img_30.png](img_30.png) <br>
**Figure 8.1: Sequence Diagram of Archive Class**

![img_29.png](img_29.png) <br>
**Figure 8.2: Sequence Diagram of Archive Class**
1. The Archive class takes in the path of the SniffArchive storage file.
2. **`openArchiveFile(String filePath)`** method reads and adds the Archived task contents into the Appointments task list.
3. **`saveArchivedAppointments(String filePath)`** method saves the Archived task contents into the SniffArchive File.
4. **`extractArchiveData(File archiveFile)`** method parses the saved file and identify stored appointments. Depending on the type of appointments, it will then call either **`addConsult(String content)`** / **`addVaccine(String content)`** / **`addSurgery(String content)`** to add these appointment objects into **`ArrayList<Appointment> APPOINTMENTS`**.
5. If the file is stored in an incorrect format / has missing details, a **`SniffException`** is thrown.
6. At the end of the application all marked appointments are saved into the `SniffArchive.txt` file using the `saveArchivedAppointments` method.

### Find - Find Implementation
![img_25.png](img_25.png)<br>
**Figure 9: Sequence Diagram showing the logical implementation of executeCommand() for the Find Command**

- Find command can be used by the user to find up to 4 categories, **`Appointment ID`**, **`Appointments Type`**, **`Animal Type`**, **`Date of Appointment`**.
- If user input is not supported by these three find commands, a **`SniffException`** is thrown.

#### findAppointment()
1. **`findAppointment`** loops through arraylist appointments and checks if matching appointment ID is present.
2. If matching appointment ID is found, it calls **`toString`** and **`formatPrintList`** to print out appointments to user.
3. If empty appointment ID is provided by user or appointment ID is in an invalid format, **`SniffException`**, is thrown for both cases.
4. If no matching appointments are stored, an ui method, **`showUserMessage`** is called.

#### findAnimal()
1. **`findAnimal`** loops through arraylist appointments and checks if appointments for specified animal type is present.
2. If matching appointments are found, it calls **`toString`** and **`formatPrintList`** to print out appointments to user.
3. If no matching appointments are stored, ui method, **`showUserMessage`** is called.

#### findType()
1. **`findType`** loops through arraylist appointments and checks if specified appointment type (surgery, consultation, vaccination) is present.
2. If appointments are found, it calls **`toString`** and **`formatPrintList`** to print out appointments to user.
3. If no matching appointments are stored, ui method, **`showUserMessage`** is called.

#### findDate()
1. **`findDate`** loops through arraylist appointments and checks if any appointment is present for the given date.
2. If appointments are found, it calls **`toString`** and **`formatPrintList`** to print out appointments to user.
3. If no matching appointments are stored, ui method, **`showUserMessage`** is called.

#### mark()
![img_32.png](img_32.png)
1. **`mark`** loops through arraylist appointments and checks if ID is present and finds the appointment index.
2. If appointments is not previously marked , it calls **`isDone`** and sets the value to true and **`showUserMessage`** is called.
3. If it is already marked then a corresponding **`showUserMessage`** is called.

#### unmark()
![img_34.png](img_34.png)
1. **`unmark`** loops through arraylist appointments and checks if ID is present and finds the appointment index.
2. If appointments is not previously unmarked , it calls **`isDone`** and sets the value to false and **`showUserMessage`** is called.
3. If it is already unmarked then a corresponding **`showUserMessage`** is called.

### Appointment - Class Implementation (non UID)
1. The appointment class takes in the user input of adding an appointment to the list of appointments.
2. The **`Appointment class`** named `Appointment` takes in input such as uid, name, type of appointment, animal type, and date of appointment. This appointment will then  be added to the list.
3. If the user inputs omits any entry or adds any extra entry then an error message will be displayed. An error message will also be displayed if the input type is of the wrong the format.

#### Adding Appointments
![AddAppointmentSequenceDiagram.png](AddAppointmentSequenceDiagram.png)<br>
**Figure 10: Generic Sequence Diagram for adding an appointment**
1. The `UI` class reads in the input from the user and then parses all the inputs in the `Parser` for each appointment. 
2. If the inputs are valid, an `AppointmentCommand` is created and then executed, otherwise an exception is thrown.
3. The `Snifftasks` class then adds the appointment to the list of current appointments.

##### UID Generation

The UID is generated to produce a 10 character string representing the Appointment ID tagged to each appointment.
The UID string generated consists of 3 substrings that are concatenated together:
- The first substring is a one-character string that represents the appointment type the UID is generated to.  
  For example, an appointment type of `Surgery` will be denoted as `"S"`.
- The second substring is an eight character long string with each character representing a digit from 0-9.  
  Each digit is chosen at random by using Java's inbuilt `Random` class. An example of this substring 
  will be `"01234567"`.
- The third substring is a one-character string representing a random letter chosen from A-Z.  
  This letter is also chosen using Java's inbuilt `Random` class.
- The UID string is concatenated together using Java's inbuilt `StringBuilder` class. The `StringBuilder` class provides
  a faster way to concatenate strings as compared to using the `+` operator.   
  An example of a randomly generated UID string for each appointment type is shown below:
  - Consultation: `C82739812B`
  - Vaccination: `V71829748S`
  - Surgery: `S23847989T`


###### Alternatives
Other alternatives that was considered are:
- Using the Java inbuilt UUID class.
   - Pros: Do not need to implement the class itself. Easy to use.
   - Cons: Overkill as it generates a 128 bit String. Cannot customise to fit our custom uid format.
- Asking the user to manually key in a UID string themselves.
   - Pros: Do not need to implement the feature itself.
   - Cons: We felt that this is counter-intuitive as it increases workload and human error. Automating
     this process will reduce potential errors.


### List appointments - Implementation
Apart from only each appointment in the appointment list, it also sorts the list by date and time in ascending order.
This is done using the inbuilt `.sort()` function, while a custom comparator class named `DateTimeComparator` is made to 
for comparison between dates and times of appointments of different types. Currently `DateTimeComparator` only supports
the existing appointment classes, namely: `Consultation`, `Vaccination` and `Surgery`.

### Remove appointments - Implementation
The implementation of the `remove` feature is similar to `mark` and `unmark`. The difference is that the `remove` feature
will remove the appointment from the `ArrayList` that it is store in.


## Product scope
### Target user profile

Veterinarians in Vet Clinics around Singapore who
* prefer typing over using a mouse.
* are using a US keyboard.
* need to keep track a large number of appointments.
* prefer Command Line Interface(CLI) over other interfaces.

### Value proposition

Veterinary Clinics have a large number of appointments for the veterinarians and their admin staff to handle.

Sniff is an appointment manager that helps clinics keep track of their appointments. This eases the workload of the clinic staff
and helps improve efficiency in running a Vet clinic, while reducing human errors occurring in the workplace.

## User Stories


| Version | As a ... | I want to ...                               | So that I can ...                                        |
|---------|----------|---------------------------------------------|----------------------------------------------------------|
| v1.0    |new user| add new appointments                        |                                                          |
| v1.0    |user| remove appointments                         | update the list with latest appointments                 |
| v1.0    |user| find appointments                           | view a specific existing appointment                     |
| v1.0    |user| list appointments                           | view all existing appointments                           |
| v1.0    |user| categorize appointments by appointment type | manage efficiently                                       |
| v2.0    |user| mark appointments                           | differentiate between completed and pending appointments |
| v2.0    |user| unmark appointments                         | retrieve appointments marked accidently                  |
| v2.0    |user| track appointment dates                     | prioritize the upcoming appointments                     |
| v2.0    |user| track locations                             | inform the doctor                                        |
| v2.1    |user| view all appointments by date               | look at upcoming appointments for the clinic             |
| v2.1    |user| edit the existing appointment               | to make edits to the existing appointment                |


## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 appointments without a noticeable change in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
   be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Main Command**: The first WORD that a user types in. `e.g. consultation, list` etc
* **Argument**: A word that is a parameter is prefixed by `/`. `e.g. at/, cd/`

## Instructions for manual testing
Given below are the instructions to test Sniff manually

### Launch
1. Ensure you have Java 11 or above installed.
2. Download the latest copy of `Sniff.jar` [here](https://github.com/AY2223S2-CS2113-W12-1/tp/releases)
3. Move the jar file to a folder where you want to run Sniff
4. Open the command terminal and change the directory to the address of the folder containing Sniff.jar
5. Use the following command in the command terminal to run Sniff:
   `java -jar sniff.jar`
6. If Sniff runs successfully, you will see the following welcome message:

```
______________________________________________________________________
 Hello! I'm Sniff, your personal appointment manager.
     _______. .__   __.  __   _______   _______ 
    /       | |  \ |  | |  | |   ____| |   ____|
   |   (----` |   \|  | |  | |  |__    |  |__  
    \   \     |  . `  | |  | |   __|   |   __| 
.----)   |    |  |\   | |  | |  |      |  |   
|_______/     |__| \__| |__| |__|      |__|   

 What can I do for you?
______________________________________________________________________
```

### Sample test cases

#### Adding appointments

1. Definitions:
   1. 3 types of appointments: `consultation`, `vaccination`, `surgery`.
   2. Animal type: `at/`.
   3. Animal name: `an/`.
   4. Owner name: `on/`.
   5. Contact number: `cn/`.
   6. Dates: `cd`, `vd`, `sd`, `ed`, in the format of **YYYY-MM-DD**.
   7. Times: `ct`, `vt`, `st`, `et`, in the format of **HH:MM**.
   8. Vaccine: `v/`.
   9. Priority: `p/`, in the format of **H**, **M**, or **L**.
   
2. Test case: `consultation at/Cat an/Lulu on/Jon cn/91919191 cd/2023-12-12 ct/19:00`<br>
   Expected output: A consultation appointment has been added successfully, details shown in the status message. <br>
   Example: <br>


```
______________________________________________________________________
 This appointment has been added to your appointment manager: 
 Consultation   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: C28216660A
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
 Consultation added successfully!
______________________________________________________________________

```

Test case: `consultation at/an/Lulu on/Jon cn/91919191 cd/2023-12-12 ct/19:00` <br>
   Expected output: A consultation appointment is not added due to invalid description. <br>
   Example: <br>
```
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
 Animal Type cannot be empty!
______________________________________________________________________
```

Test case: `vaccination at/Dog an/Russ on/Abel cn/92929292 v/Covid vd/2023-12-12 vt/19:00` <br>
   Expected output: A vaccination appointment has been added successfully, details show in the status message. <br>
   Example: <br>
```
______________________________________________________________________
 This appointment has been added to your appointment manager: 
 Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V57416751U
 Vaccine: Covid
 Animal Name: Russ | Animal Type: Dog
 Owner Name: Abel | Contact Number: 92929292
 Vaccination added successfully!
______________________________________________________________________
```

Test case: `vaccination at/Dog an/Russ on/Abel cn/92929292 v/Covid vd/12-12-2023 vt/19:00` <br>
   Expected output: A vaccination appointment is not added due to invalid date description. <br>
   Example: <br>
```
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
The date/time description is invalid.
______________________________________________________________________
```

Test case: `surgery at/Hamster an/Polly on/Sam cn/93939393 sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/H` <br>
   Expected output: A surgery appointment has been added successfully, details shown in the status message. <br>
   Example: <br>
```
______________________________________________________________________
 This appointment has been added to your appointment manager: 
 Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Polly | Animal Type: Hamster
 Owner Name: Sam | Contact Number: 93939393
 Surgery added successfully!
______________________________________________________________________
```

Test case: `surgery at/Hamster an/Polly on/Sam cn/93939393 sd/2023-12-12 st/19:00 ed/2023-12-12 et/19:00 p/H` <br>
   Expected output: A surgery appointment is not added due to dates and times being the same. <br>
   Example: <br>
```
______________________________________________________________________
 Sorry, an error was encountered! Here is the error description:
 The start time cannot be the same as the end time!
______________________________________________________________________
```

#### Listing appointments

1. Prerequisites: Add an appointment using any of the add commands. At least one appointment in the list. <br>
2. Test case: `list`<br>
   Expected output: A list of all unmarked appointments. <br>
   Example: <br>

```
______________________________________________________________________
 1.  Consultation   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: C28216660A
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191

 2.  Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V57416751U
 Vaccine: Covid
 Animal Name: Russ | Animal Type: Dog
 Owner Name: Abel | Contact Number: 92929292

 3.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Polly | Animal Type: Hamster
 Owner Name: Sam | Contact Number: 93939393

______________________________________________________________________
```

#### Archiving appointments

1. Prerequisites: Shift an appointment using any of the add commands. At least one appointment in the archive. <br>
2. Test case: `archive`<br>
   Expected output: A list of all marked appointments. <br>
   Example:<br>

```
______________________________________________________________________
 1.  Consultation   [X]
 Date: 2023-12-12
 Time: 19:00
 UID: C28216660A
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191

 2.  Vaccination   [X]
 Date: 2023-12-12
 Time: 19:00
 UID: V57416751U
 Vaccine: Covid
 Animal Name: Russ | Animal Type: Dog
 Owner Name: Abel | Contact Number: 92929292

 3.  Surgery  [X] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Polly | Animal Type: Hamster
 Owner Name: Sam | Contact Number: 93939393

______________________________________________________________________
```

#### Finding appointments

1. Prerequisites: Add an appointment using any of the add commands. At least one appointment in the list. <br>
2. Test case: `find a/Dog` <br>
   Expected output: A list of all previously added appointments with the animal type. <br>
   Example: <br>

```
______________________________________________________________________
 1.  Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V57416751U
 Vaccine: Covid
 Animal Name: Russ | Animal Type: Dog
 Owner Name: Abel | Contact Number: 92929292

______________________________________________________________________
```

Test case: `find t/surgery` <br>
   Expected output: A list of all previously added appointments with the appointment type. <br>
   Example: <br>

```
______________________________________________________________________
 1.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Caramel | Animal Type: Mouse
 Owner Name: Sam | Contact Number: 93939393

______________________________________________________________________

```

Test case: `find d/2023-12-12` <br>
   Expected output: A list of all previously added appointments with the specified date. <br>
   Example: <br>
    
```
______________________________________________________________________
 1.  Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V57416751U
 Vaccine: Covid
 Animal Name: Russ | Animal Type: Dog
 Owner Name: Abel | Contact Number: 92929292
 2.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Caramel | Animal Type: Mouse
 Owner Name: Sam | Contact Number: 93939393
 3.  Consultation   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: C40384411E
 Animal Name: nono | Animal Type: dog
 Owner Name: jon | Contact Number: 91919191
______________________________________________________________________
```

Test case: `find uid/S02547136Q` <br>
   Expected output: A list of all previously added appointment with the appointment uid. <br>
   Example: <br>

```
______________________________________________________________________
 1.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S02547136Q
 Animal Name: lulu | Animal Type: dog
 Owner Name: jon | Contact Number: 91919191
 
______________________________________________________________________
```

Test case: `find d/2023-12-12` <br>
   Expected output: A list of all previously added appointments with the same appointment date. <br>
   Example: <br>

```
______________________________________________________________________
1.  Vaccination   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: V17511055M
 Vaccine: covid
 Animal Name: lulu | Animal Type: cat
 Owner Name: jon | Contact Number: 91919191
 
______________________________________________________________________
```

#### Removing appointments

1. Prerequisites: The UID of the appointment you want to remove. Use `list` or `find` to help you get the specific UID. <br>
2. Test case: `remove uid/C28216660A`<br>
   Expected output: The appointment with the specified UID is removed, details shown in the status message. <br>
   Example: <br>

```
______________________________________________________________________
 This appointment has been removed your appointment manager: 
 Consultation   [ ]
 Date: 2023-12-12
 Time: 19:00
 UID: C28216660A
 Animal Name: Lulu | Animal Type: Cat
 Owner Name: Jon | Contact Number: 91919191
 Task removed successfully!
______________________________________________________________________
```

#### Mark/UnMark appointments

1. Prerequisites: The UID of the appointment you want to remove. Use `list` or `find` to help you get the specific UID. <br> 
2. Test case: `mark uid/S82358005U`<br>
   Expected output: If the appointment UID entered is valid and not already marked the corresponding appointment is marked on the list. <br>
   A corresponding successful mark message is displayed. <br>
   Example: <br>

```
______________________________________________________________________
 1.  Surgery  [X] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Polly | Animal Type: Hamster
 Owner Name: Sam | Contact Number: 93939393


The appointment has been marked successfully
______________________________________________________________________
```

Test case: `unmark uid/V12400172X`<br>
   Expected output: If the appointment UID entered is valid and not already unmarked the corresponding appointment is unmarked on the list.
   A corresponding successful Unmark message is displayed. <br>
   Example: <br>

```
______________________________________________________________________
 1.  Surgery  [ ] | Priority: HIGH
 Start Date: 2023-12-12
 End Date: 2023-12-12
 Start Time: 19:00
 End Time: 20:00
 UID: S82358005U
 Animal Name: Polly | Animal Type: Hamster
 Owner Name: Sam | Contact Number: 93939393
 

The appointment has been unMarked successfully
______________________________________________________________________

```

#### Editing appointments
1. Prerequisites : The UID of the appointment that you want to should already exist. You can find the appointment using `list` or `find` command. 
2. Test case: `edit uid/C67345117A at/Mouse an/freddy on/muthu cn/91917777 cd/2023-12-12 ct/19:00` <br>
   Expected output : Editing consultation : Enter the edit command with uID/ of the appointment that you want to edit. If the 
                                            appointment exists then the appointment is changed. If it does not exist it gives an error message. <br>

Example:<br>

```
______________________________________________________________________
Consultation changed successfully!
______________________________________________________________________
```

Test case: `edit uid/S03044138U at/Mouse an/Caramel on/Sam cn/93939393 sd/2023-12-12 st/19:00 ed/2023-12-12 et/20:00 p/H` <br>
   Expected output : Editing consultation : Enter the edit command with uID/ of the appointment that you want to edit. If the
                                            appointment exists then the appointment is changed. If it does not exist it gives an error message. <br>

Example: <br>

```
______________________________________________________________________
 Surgery changed successfully!
______________________________________________________________________
```

Test case: `edit uid/V01087221W at/Dog an/Russ on/Abel cn/92929292 v/Covid vd/2023-12-12 vt/19:00` <br>
   Expected output : Editing consultation : Enter the edit command with uID/ of the appointment that you want to edit. If the
                                            appointment exists then the appointment is changed. If it does not exist it gives an error message. <br>

Example:<br>

```
______________________________________________________________________
Vaccination changed successfully!
______________________________________________________________________
```

#### Help function
1. Example: `help` <br>

```
______________________________________________________________________
These are the following Sniff commands available:

Add consultation appointment:

 consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME

Add vaccination appointment:

 vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATE vt/TIME

Add surgery appointment:

 surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL

Listing all appointments:

 list

Removing an appointment:

 remove uid/UID

Finding an appointment by animal type, appointment type or uid:

 find a/ANIMAL_TYPE

 find t/APPOINTMENT_TYPE 

 find uid/UID

 find d/DATE

Mark or UnMark an appointment:

 mark uid/UID

 unMark uid/UID

Editing an appointment:

 edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME

 edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATEvt/TIME

 edit uid/UID at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATEst/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL

Archiving appointments:

 archive

Exiting the program:

 bye

Additional notes:

 1. DATES and TIMES format are in (YYYY-MM-DD) and (HH:MM) respectively

 2. PRIORITY_LEVEL format is in (L, M, H)
______________________________________________________________________
```