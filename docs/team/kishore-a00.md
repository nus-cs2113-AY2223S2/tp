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
- Allows for the data stored in the text file to be parsed and loaded into the program when the user restarts the
  program
- Complements the data encoding feature
- Implemented error catching methods to prevent the program from crashing even if the text file has been modified

#### Delete feature
- Allows the user to delete a specific company when it is no longer needed
- Keeps EveNtUS from being cluttered with unwanted data
- Implemented error catching methods to prevent the program from crashing when incorrect parameters are given

### Enhancements to existing features
#### Additional error handling in Parser interface
- Added additional checks to catch if the user has input a value that is larger than the maximum integer size. EveNtUS would display a message to indicate that a smaller integer should be used instead

#### Detailed error messages
- Created exceptions such as IntegerSizeExceededException, NoSavedInformationException, NegativeNumberException to catch
  edge cases and display specific error messages to the user instead of generic error messages which might not highlight
  the reason why the command was unsuccessful

#### Filter venues by capacity feature
- Enhanced choosing venue feature by adding the functionality to filter venues by capacity.
- Users can easily filter out venues that are too small

#### Update event name feature
- Enhanced customisability by including a feature to allow users to modify the name of the event which will be stored even after the user exits the program
- Allows the user to keep track of the purpose of the event


### Documentation
#### Contributions to User Guide
- Documentation for Delete feature
- Documentation for Filtering venues by capacity


#### Contributions to Developer Guide
- Added table of contents
- Documentation for delete feature
- Documentation for store/load information to/from text files
- Documentation for custom exceptions
- UML diagrams (displayed below)
  - [Sequence diagram for Encoder feature](#sequence-diagram-for-encoder-feature)
  - [Sequence diagram for Decoder feature](#sequence-diagram-for-decoder-feature)
  - [Class diagram of custom Exceptions used](#class-diagram-of-custom-exceptions-used)

### Contributions to Team tasks
- PRs reviewed (with non-trivial comments regarding syntax and coding standards):
  [#17](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/17),
  [#31](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/31),
  [#115](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/115),
  [#118](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/118),
  [#126](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/126)
- Actively contributed ideas for features to implement and solutions for bugs faced
- Contributed JUnit tests for methods authored by my teammates: [#138](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/138)

<div style="page-break-after: always;"></div>

### Diagrams contributed for Developer Guide

#### Sequence diagram for Encoder feature
![Encoder.png](..%2FUML%2FImage%2FEncoder.png)

<div style="page-break-after: always;"></div>

#### Sequence diagram for Decoder feature
![Decoder.png](..%2FUML%2FImage%2FDecoder.png)

#### Class diagram of custom Exceptions used
![Exceptions.png](..%2FUML%2FImage%2FExceptions.png)

