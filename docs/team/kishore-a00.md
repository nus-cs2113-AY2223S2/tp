# Kishore S/O Asokan - Project Portfolio Page

### Overview of Project
EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient
operation through the Command-Line Interface(CLI).

### Contributions to the project: [Reposense page](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=kishore-a00&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### New features added
#### Encoding saved data to text file for storage
- Purpose: Allows for the storage of the information the user keyed into EveNtUS. This means that the event manager  
(our target audience) does not have to re-type all the information they keyed in previously everytime they reboot 
EveNtUS.
- Justification: This makes EveNtUS a system that event managers would like to use as ensuring
their data is stored is important for their job.

#### Decoding saved data from text file
- Purpose: Allows for the stored data to be reloaded into the programme when the event manager starts the programme.
- Justification: Having a good decoder is paramount as it complements the encoding feature.
- Highlights: As the data is stored on a text file, anyone can edit it. Thus, I implemented many error catching methods
to prevent EveNtUS from crashing if the text file was edited. Best of all, EveNtUS is able to skip erroneous lines
ensuring that whatever data is intact will be retrieved.

#### Delete company information from CompanyList
- Purpose: Allows the event manager to delete stored information when it is no longer needed.
- Justification: Despite having the `confirm` and `unconfirm` commands, the event manager requires a `delete` function 
so that their Event list is not filled with information that is no longer needed. This prevents clutter and enhances the
their experience when using EveNtUS.


### Enhancements to existing features
#### Additional error handling in Parser interface
Added additional checks to catch if the event manager has input a value that is larger than the maximum integer size, 
EveNtUS would display a message to indicate that a smaller integer should be used instead. 

#### Created new Exceptions
Added exceptions such as IntegerSizeExceededException, NoSavedInformationException, NegativeNumberException to catch 
edge cases and display specific error messages to the event manager instead of displaying a generic error message which 
would not be useful to them.

#### Filter venues by capacity feature
Added the functionality to filter venues by capacity so that event managers are easily able to filter out venues that 
are too small instead of having to look through the capacity of every single venue available.


#### Update event name feature
Previously, the event name was 'Default Event' with no way to change it. I added a function to allow event managers to
rename the event so that they can customise the name of the event accordingly.

## Documentation
### Contributions to User Guide
- Contributed documentation for storage feature, delete, update event name and filter venues by capacity features

### Contributions to Developer Guide
- Table of contents
- Sequence diagram for Encoder feature
![Encoder.png](..%2FUML%2FImage%2FEncoder.png)
- Sequence diagram for Decoder feature
![Decoder.png](..%2FUML%2FImage%2FDecoder.png)
- Class diagram for Exceptions
![Exceptions.png](..%2FUML%2FImage%2FExceptions.png)


## Contributions to Team tasks
- PRs reviewed (with non-trivial comments): 
[#17](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/17), 
[#31](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/31), 
[#115](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/115),
[#118](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/118),
[#126](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/126)
- Actively contributed in coming up with ideas and solutions for EveNtUS
- Contributed to JUnit tests for methods authored by my teammates 
[#138](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/138)
