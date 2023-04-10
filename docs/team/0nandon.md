# 🧑🏻‍💻 Seungjun Lee, Project Portfolio Page

## 🤖 Project BadMaths

BadMaths is a study tool focusing on mathematical help and note-taking. It supports
a number of mathematical functions, including trigonemetric graphs, quadratic equations, and matrix calculations.

If you are more interested, refer the links below:
* [User Guide](https://ay2223s2-cs2113-f10-2.github.io/tp/UserGuide.html)
* [Code](https://github.com/AY2223S2-CS2113-F10-2/tp)

## 👊🏻 Summary of Contributions

### Code contributed

My code contribution can be mainly separated with two parts:
* source code for the matrix calculation
* JUnit test codes for the matrix calculation

You can see the file structure that I contributed below:

From the folder `src/main/java/seedu/badmaths/matrix`
```
├── matrix
│   ├── Calculate.java
│   ├── Calculator.java
│   ├── Execute.java
│   ├── Parser.java
│   ├── Shape.java
│   ├── Tensor.java
│   ├── Tensor2D.java
│   ├── Ui.java
│   └── exception
│       ├── ExceptionChecker.java
│       ├── ExceptionPrinter.java
│       ├── ShapeMismatchException.java
│       └── UnknownOperatorException.java
```

From the folder `src/test/java/seedu/badmaths/matrix`
```
├── matrix
│   ├── CalculateTest.java
│   ├── ExecuteTest.java
│   └── ParserTest.java
```
If you want to see my code contributions more specifically, click [here](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=0nandon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=0nandon&tabRepo=AY2223S2-CS2113-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

### Features added

- Added a feature to support various matrix calculation. This allows users to input matrix equation and BadMaths
  would be able to output the answer. BadMaths supports four operations below:
  * Matrix multiplication
  * Matrix element wise product
  * Matrix addition
  * Matrix subtraction

### Enhancements added

- Added JUnit tests for the below file:
  * `src/test/java/seedu/badmaths/matrix/CalculateTest.java`
  * `src/test/java/seedu/badmaths/matrix/Execute.java`
  * `src/test/java/seedu/badmaths/matrix/parse.java`
- Improve code more defensive with handling various possible exceptions:
  * `src/main/java/seedu/badmaths/matrix/exception/ShapeMismatchException.java`
    > This exception is called when the shape of the two matrices don't match to be calculated.
  * `src/main/java/seedu/badmaths/matrix/exception/UnknownOperatorException.java`
    > This exception is called when the user enters the unsupported operator.
- Refined the code to be more OOP with following [code standard](https://se-education.org/guides/conventions/java/basic.html).
- Added comments with `JavaDoc` style for the readability.
- Added `logging`, `assert` statement for making debugging more easily.

### Contributions to the UG

- Contributed the section explaining the usage of the Matrix calculation.
- Added various examples for the matrix calculation and expected output.
- Refined the whole contents of the UG for the readability.

### Contributions to the DG

- Contributed to the section detailing the functionality of the matrix calculation.
- Created a sequence diagram to further explain how matrix calculation is conducted.
- Refined the whole contents of the DG for the readability.

### Contributions to team-based tasks

- Contributed to brainstorming of ideas and feature generation.
- Tracked v1.0, v2.0 milestones.
- Provided basic coding advice and code quality checking assistance to group members.
- Reviewed Pull Requests with assistance before merging them into the master branch on Github.
