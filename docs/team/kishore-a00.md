## Kishore S/O Asokan - Project Portfolio Page

### Overview of Project
EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient
operation through the Command-Line Interface(CLI)

### Contributions to the project: [Reposense page](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=kishore-a00&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### New features added
#### Encoding saved data to text file for storage
- Allows information keyed into the program to be saved even after the user exits the program
- Information is saved to a text file that is stored on the user's device

#### Decoding saved data from text file
- Allows for the data stored in the text file to be loaded into the programme when the user restarts the program
- Complements the data encoding feature
- Implemented error catching methods to prevent the program from crashing even if the text file has been modified

#### Delete company information from CompanyList
- Allows the user to delete a specific company when it is no longer needed
- Keeps EveNtUS from being cluttered with unwanted data
- Implemented error catching methods to prevent the program from crashing when incorrect delete commands are given

### Enhancements to existing features
#### Additional error handling in Parser interface
- Added additional checks to catch if the user has input a value that is larger than the maximum integer size. EveNtUS would display a message to indicate that a smaller integer should be used instead

#### Detailed error messages
- Added exceptions such as IntegerSizeExceededException, NoSavedInformationException, NegativeNumberException to catch
  edge cases and display specific error messages to the user instead of generic error messages which might not highlight
  the reason why the command was unsuccessful

#### Filter venues by capacity feature
- Enhanced choosing venue feature by adding the functionality to filter venues by capacity so that users are easily able to filter out venues that are too small instead of having to look through the capacity of every single venue available

#### Update event name feature
- Enhanced customisability by including a feature to allow users to modify the name of the event which will be stored even after the user exits the program
- Allows the user to keep track of the purpose of the event


## Documentation
### Contributions to User Guide
- Contributed documentation for the following features:
    - storage
    - delete
    - filter venues by capacity

### Contributions to Developer Guide
- Added table of contents in Developer Guide
- UML diagrams
    - [Sequence diagram for Encoder feature](https://github.com/AY2223S2-CS2113-W12-2/tp/blob/master/docs/UML/Encoder.puml)
    - [Sequence diagram for Decoder feature](https://github.com/AY2223S2-CS2113-W12-2/tp/blob/master/docs/UML/Decoder.puml)
    - [Class diagram of custom Exceptions](https://github.com/AY2223S2-CS2113-W12-2/tp/blob/master/docs/UML/Exceptions.puml)


## Contributions to Team tasks
- PRs reviewed (with non-trivial comments):
  [#17](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/17),
  [#31](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/31),
  [#115](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/115),
  [#118](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/118),
  [#126](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/126)
- Actively contributed ideas for features to implement and solutions for bugs faced
- Contributed to JUnit tests for methods authored by my teammates
  [#138](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/138)

