# Seungjun Lee - project Portfolio Page

## 🤖 Project BadMaths

BadMaths is a study tool focusing on mathematical help and note-taking. It supports various math-related features like matrix calculation,
quadratic equations, and trigonemetric graphs.

## 👊🏻 Summary of Contributions

### 🧑🏻‍💻 Code contributed

You can see my code contributions in [here](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=0nandon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=0nandon&tabRepo=AY2223S2-CS2113-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### 📌 General functionalities implemented

- Created and implemented the initial `BadMaths` class, `Command` class and `Parser` class that helped to facilitate the general
  flow of the program. BadMaths class took in user input, Parser class made sense of the user input and Command class executed
  the desired features. All the included features in BadMaths depend and run on these 3 classes.

### 📕 Features added

- Added a feature to solve quadratic equations. This allows users to input any quadratic equation and BadMaths would be
  able to output the answer. If the answer is imaginary, BadMaths would tell the user as such. BadMaths would also find the
  minimum or maximum point of the quadratic graph.

### ⛑ Enhancements added

- Added JUnit tests for the below file:
  * `src/test/java/seedu/badmaths/matrix/CalculateTest.java`
  * `src/test/java/seedu/badmaths/matrix/Execute.java`
  * `src/test/java/seedu/badmaths/matrix/parse.java`
- Improve code more defensive with handling various possible exceptions:
  * ``
  * ``
  * ``
  * ``
- Refined the code to be more OOP with following [code standard](https://se-education.org/guides/conventions/java/basic.html).
- Added comments with `JavaDoc` style for the readability.
- Added `logging`, `assert` statement for making debugging more easily.

### Contributions to the UG

- Contributed the Table of Contents for easy access to various sections of the UG.
- Contributed the section explaining the usage of the Quadratic solver feature.
- Contributed to the FAQ section.

### Contributions to the DG

- Contributed to the section detailing the functionality of the Quadratic solver.
- Created a sequence diagram to further explain how Quadratic Solver works.
- Added User Stories.
- Added instructions for manual testing.

### Contributions to team-based tasks / Review contributions

- Contributed to brainstorming of ideas and feature generation.
- Tracked v1.0, v2.0 milestones.
- Helped group members with code quality checking and general coding advice.
- Helped to review Pull Requests before merging into master branch on Github.
