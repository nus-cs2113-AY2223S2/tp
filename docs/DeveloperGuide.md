# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

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



## Product scope
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
