# Seungjun Lee, Project Portfolio Page

## Project BadMaths

BadMaths is a study tool focusing on mathematical help and note-taking. It supports
a number of mathematical functions, including trigonemetric graphs, quadratic equations, and matrix calculations.

## Summary of Contributions

### Code contributed

My code contribution can be mainly separated with two parts:

  * Source code for the matrix calculation and possible exceptions
  * JUnit test codes for the matrix calculation

If you want to see my code contributions more specifically, click [[here]](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=0nandon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=0nandon&tabRepo=AY2223S2-CS2113-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

### Features added

- Added a feature to support various matrix calculation. This allows users to input matrix equation and BadMaths
  would be able to output the answer. BadMaths supports four operations below:

  1. Matrix multiplication
  2. Matrix element wise product
  3. Matrix addition 
  4. Matrix subtraction
  5. Matrix transpose

### Enhancements added

- Added JUnit tests for the `Calculate`, `Parser`, `Execute` classes.
- Improve code more defensive with handling various possible exceptions:

  1. `ShapeMismatchException` : This exception is called when the shape of the two matrices don't match to be calculated.
  2. `UnknownOperatorException` : This exception is called when the user enters the unsupported operator.
  3. `MatrixShapeException` : This exception is called when the user declares the matrix with inappropriate shape.
  4. `MatrixFormatException` : This exception is called when the user declares the matrix with inappropriate format.
- Refined the code to be more OOP with following [[code standard]](https://se-education.org/guides/conventions/java/basic.html).
- Added comments with `JavaDoc` style for the readability.
- Added `logging`, `assert` statement for making debugging more easily.

### Contributions to the UG

- Contributed the section explaining the usage of the Matrix calculation.
- Added various examples for the matrix calculation and expected output.
- Added various examples for common mistakes that users can possibly do.
- Added several cautions for the appropriate usage of the matrix calculation.
- Added `FAQ` for the matrix calculation.
- Added `Command Summary` for the matrix calculation.
- Refined the whole contents of the UG for the readability.

### Contributions to the DG

- Contributed to the section detailing the functionality of the matrix calculation.
- Created a `sequence diagram` to further explain how matrix calculation is conducted.
- Created a `class diagram` to further explain relationships between classes for the matrix part.
- Refined the whole contents of the DG for the readability.

### Contributions to team-based tasks

- Contributed to brainstorming of ideas and feature generation.
- Tracked v1.0, v2.0, v2.1 milestones.
- Provided basic coding advice and code quality checking assistance to group members.
- Reviewed Pull Requests with assistance before merging them into the master branch on Github.
- Manually tested jar file with checking whether there is any error.
