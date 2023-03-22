# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}


## Implementations
### Exit Command

The `ExitCommand` inherits its properties from the abstract `Command` class.

The command to exit the program is `exit`.
If 'exit' is called, the program prints the exit message and terminates the program.

This is how the ExitCommand works:

1. The `main()` method in Main calls `run()` in Main. 

2. `run()` will call `runCommandTillExit()`.

3. The `ui` reads the user's input to retrieve
the command via `ui.getUserInput()` and parses it through `commandParser.parseCommand()`.

4. Within the `commandParser.parseCommand()`, some functions are also internally called.
    1. `newCommand()` is called to identify the String received
    2. `parseKeyword()` is called to split the command from the parameters.
 
5. A new `ExitCommand()` is called.

6. `ExitCommand` is returned to parseCommand().

7. `ExitCommand` is returned to main().

8. The Exit Command is now executed via `command.execute()`.

9. `runCommandTillExit()` will now call `command.isExit()` which returns `true`.

10. The loop is now broken and ui will call `ui.showEndingMessage()`.

11. The program will now exit.

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
