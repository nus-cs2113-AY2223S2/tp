# Developer Guide
## Table of Contents

1. [Acknowledgements](#acknowledgements) 
2. [Design & implementation](#design--implementation)
   - [Store Notes](#store-notes) 
   - [Graph](#graph)
2. [Product Scope](#product-scope)
   - [Target user profile](#target-user-profile)
   - [Value proposition](#value-proposition)
3. [User Stories](#user-stories)
4. [Non-Functional Requirements](#non-functional-requirements)
5. [Glossary](#glossary)
6. [Instructions for manual testing](#instructions-for-manual-testing)
## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}


## Design & implementation
### Store Notes
The 

Store. hello
List.

### Help
The Help feature allows users to view possible options by calling `Help.` which reads the content stored in the HelpManual.txt.

The feature implemented involves reading the content from HelpManual.txt file and logging the result of read operation using the Java Logging API.

#### HelpManual text:
`HelpManual.txt` is a text file that contains the help manual for the application. It is located in the `src/main/resources` directory of the project.
The purpose of this file is to provide users with helpful information on how to use the application.

#### HelpManual class:
Step 1. Define the HelpManual class: The HelpManual class is defined as a public class with two static fields: filePath and logFilePath. 
These fields contain the file path of the HelpManual.txt file and the name of the log file, respectively.

Step 2. Setting up the Logger: The Logger is set up in the HelpManual class using the Java Logging API. 
The `setUpLogger()` method resets the LogManager, sets the logger's level to ALL, and creates a log file if it does not already exist. 
This method is called before the `readHelpManual()` method to ensure that the logger is properly configured.

Step 3. Reading the file:
The `readHelpManual()` method is implemented to read the content from the HelpManual.txt file.
It first calls the setUpLogger() method to configure the logger. Then it obtains an InputStream object that reads the content from the 
file using the `getResourceAsStream()` method. Before reading the file, the `readHelpManual()` method asserts that the input stream is not null to ensure that the file exists and is readable.
The file is then read line by line using a BufferedReader object, and each line is appended to a StringBuilder object. Otherwise,
the `getResourceAsStream()` method will return a null value, and an `AssertionError` will be thrown.

Step 4. Logging the result: After reading the file, the `readHelpManual()` method closes the BufferedReader and InputStream 
objects and logs the success or failure of the read operation using the logger. If the read operation is successful, a message
is logged to the console and log file indicating that the HelpManual file was successfully read. If an exception occurs during
the read operation, an error message is logged to the console and log file.


### Graph
The Graph feature allows users to analyse basic Trigonometry signals by calling `startGraphAnalysis()` which
creates a new instance of `TrigoGraphAnalyser` named `analyser`.

Below is an example usage scenario and how the `Graph` feature behaves at each step.

When user enters an input:
```
Graph. 2*sin(2*x+5)-1
```
#### Parser class, Command class:
Step 1. Parser class will split the input into `Graph.` and `2*sin(2*x+5)-1`, which are `command` and `toDo` respectively.
The command class creates a new instance of `TrigoGraph` and pass in `toDo` as a parameter for the constructor. It
then executes `trigoGraph.startGraphAnalysis()`based on `command` using switch-case statements.

#### TrigoGraph class:
Step 2. Constructor for the TrigoGraph class takes in `2*sin(2*x+5)-1` and assigns it to `trigoEqn` of type String. When `startGraphAnalysis()`
is called from the command class, a new instance of `TrigoGraphAnalyser` called `analyser` is created in TrigoGraph, which accepts `trigoEqn`
as the parameter.

Step 3. When `analyser.canStartAnalyser()` is true, it will print out the amplitude, frequency, phase and vertical shift
to the user using the Ui class. Each of the method calls to the Ui class requires an input of type double which can
be achieved by calling `analyser.getAmplitude()`, `analyser.getFreq()` and so on.

#### TrigoGraphAnalyser class:
Step 4. In the `canStartAnalyser()` method, the trigo equation is split into various parts, and there
is a method call to create `setUpLogger()` to log exceptions when user enters
invalid trigo equation. `canStartAnalyser()` returns false if an exception is reached, else it returns true.

Step 5. `canStartAnalyser()` calls `splitAmplitudeFromTrigoEqn()`. This method will split
the input into `2` and `sin(2*x+5)-1` using `*` as the regex. 

Step 6. 2 tests are ran within `splitAmplitudeFromTrigoEqn()` to check for negative amplitude and multiple asterisk. 
In this case, both checks will return false.

Step 7. The separated String is passed into `String[] amplitudeAndEqn`. The string is then passed into `findAmplitude` 
to determine the amplitude. In `findAmplitude(eqn)`, `isAmplitudeEqualsToOne(eqn[0])` will check if the input string 
starts with `cos`, `sin` or `tan`, where `eqn[0]` is `2` and `eqn[1]` is `sin(2*x+5)-1`. 
If this is true, 1.0 will be assigned to `amplitude`. Else, string `2` will be converted to double `2.0` and assigned to 
`amplitude`.

Step 8. After finding `amplitude`, `canStartAnalyser()` calls `splitTrigoAndVerticalShift(amplitudeAndEqn[1])`, where
`amplitudeAndEqn[1]` is `sin(2*x+5)-1`. `splitTrigoAndVerticalShift` splits `sin(2*x+5)-1` into `sin(2*x+5` and `-1`
based on `)` and returns the separated string to `trigoAndVerticalShift`.

Step 9. `canStartAnalyser()` calls `findVerticalShift()` which takes in `sin(2*x+5` and `-1` as a string [] parameter. 
In `findVerticalShift()`, method call to `isVerticalShiftZero(-1)` is false and thus, 
`-1` is converted to double `-1.0` and assigned to `vertricalShift`.

Step 10. In `canStartAnalyser()`, string `trigo` is assigned `sin(2*x+5`. 

Step 11. `canStartAnalyser()` calls `splitTrigoIntoPhasors()` and takes in `trigo` as the parameter. In `splitTrigoIntoPhasors()`, 
`startPosOfPhase` is 4 while `endPosOfPhase` is 9. A new String variable `phase` is assigned the substring of trigo from 
4 to 9, resulting in `2*x+5`. `splitPhasorsIntoFreq()` is called and it accepts string `2*x+5` as input.

Step 12. In `splitPhasorsIntoFreq()`, `findFreqForPlus()` is called which takes in string `2*x+5` as input. 

Step 13. In `findFreqForPlus()`, `2*x+5` is split into `2*x` and `5` using the split function with `+` as the regex. 
`findPhase()` which accepts `5` and a boolean `false` as parameters, and `findFreq()` which accepts `2*x` and a boolean `false` are called.

Step 14. In `findPhase()`, `phase` is assigned the double of `5` and in `findFreq()`, `2*x` is substring into `2` and converted 
to double `2.0`. `Freq` is assigned the double value of `2.0 / (2 * Math.PI)`.

Step 15. Finally, `canStartAnalyser()` reaches the end and returns true.

#### TrigoGraphVisualiser class:
Step 16. The `TrigoGraphVisualiser` extends JPanel and overrides the `paintComponent()` method in JComponent. 

Step 17. A new instance of `TrigoGraphVisualiser` is created in `startGraphAnalysis` to assign corresponding values to the amplitude, phase, frequency, 
vertical shift, and the trigonometric function in `TrigoGraphVisualiser`. Next, `startVisualiser()` is called to create the frame 
for the graph.

Step 18. The frame is set half of the screen size using `setSize()` and `getScreenSize()`. 

Step 19. When the frame is created, `paintComponent` will be called. It sets up the scale factors which will scale the values
generated by the trigo function to that of the frame. It the sets up the x and y axis using `g.drawLine()`.

Step 20. Using the switch-case statements. `drawSinCurve()` will be executed and amplitudes will be labelled on the axis. 
The for loop will run from `xMin` to `xMax`, which are the negative frequency and positive frequency for one period respectively.
After getting the y value, both x and y are scaled to xPixel and yPixel respectively. A horizontal line is drawn at every pixel, which
eventually forms the sine graph.

//{Describe the design and implementation of the product. 
//Use UML diagrams and short code snippets where applicable.}


## Product Scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
