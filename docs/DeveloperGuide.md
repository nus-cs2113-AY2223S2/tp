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
to the user using the Ui class. Each of the method calls to the Ui class requires a input of type double which can
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

Step 14. In `findPhase()`, `phase` is assigned the double of `5` and in `findFreq()`, `2*x` is substring into `2` and coverted 
to double `2.0`. `Freq` is assigned the double value of `2.0 / (2 * Math.PI)`.

Step . Finally, `canStartAnalyser()` reaches the end and returns true.


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
