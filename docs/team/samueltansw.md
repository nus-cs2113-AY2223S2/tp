# Samuel Tan - Project Portfolio Page

## Table of Contents

1. [Overview](#overview)
2. [Summary of Contributions](#summary-of-contributions)
   - [Code Contributions](#code-contributions)
   - [Enhancements to BrokeMan](#enhancements-to-brokeman)
   - [Contributions to UG](#contributions-to-ug)
   - [Contributions to DG](#contributions-to-dg)
   - [Contribution to team-based tasks](#contribution-to-team-based-tasks)
   - [Contributions beyond the project team](#contributions-beyond-the-project-team)

## Overview

Hello I'm Samuel Tan, an aspiring Computer Engineer studying at National University of Singapore.

Together with my group members, we have created a product known as BrokeMan, which is a personal
budget manager. 

[back to contents](#table-of-contents)

---

## Summary of Contributions

I have contributed a **majority** of the enhancements to BrokeMan, User Guide (UG), and Developer Guide (DG).

The following below is the breakdown of my contributions to the team project BrokeMan.

### Code Contributions

- Here is an in-depth look at my [code contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=Samueltansw&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=Samueltansw&tabRepo=AY2223S2-CS2113-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

[back to contents](#table-of-contents)

---

### Enhancements to BrokeMan

1. Implemented the **entire** [BrokeMan](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/BrokeMan.java) (main) class which determines how the program runs.
2. Implemented the **entire** [Ui](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/ui/Ui.java) class to read input from the users and format the outputs to the users.
3. Implemented **almost all** the [Parser](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/parser/Parser.java) Class to format and make sense of the user inputs. As well as to handle exceptions.
4. Implemented **almost all** the [command](https://github.com/AY2223S2-CS2113-F13-2/tp/tree/master/src/main/java/seedu/brokeMan/command) classes and integrated the various command features of the product together,
ensuring the working of the product.
5. Helped with some parts of exception handling for SaveExpense and SaveBudget classes.
6. Implemented **almost all** the [exception](https://github.com/AY2223S2-CS2113-F13-2/tp/tree/master/src/main/java/seedu/brokeMan/exception) classes to determine the error messages displayed to users when invalid inputs are entered.
7. Implemented the **entire** [Messages](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/common/Messages.java) class under the common package which contains the various messages to be displayed to user.
8. Handled **almost all** the exceptions for the program to prevent crashing.
Most of the exceptions handling are done in the Parser class.
9. Implemented the JUnit tests for [ParserTest.java](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/test/java/seedu/brokeMan/parser/ParserTest.java).

[back to contents](#table-of-contents)

---

### Contributions to UG

1. Set up the  Table of Contents (TOC) to work properly such that it can link to the appropriate sections,
and added link back to the TOC at the end of every section.
2. Added the example outputs for **all** features of the product as well as the welcome message.
3. Contributed a **large portion** (more than half) of the instructions for **all** the feature in the UG such as `deleteExpense`, `deleteIncome`, `addExpense`, etc.
4. Helped with the formatting of [Command Summary](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/docs/UserGuide.md#command-summary)

[back to contents](#table-of-contents)

---

### Contributions to DG

1. Set up the skeleton of DG with appropriate headers for everything.
2. Set up the TOC to work properly such that it can link to the appropriate sections.
3. Added the [Acknowledgements](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/docs/DeveloperGuide.md#acknowledgements) section which acknowledges the external libraries we have used
4. Added the entire [Architecture](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/docs/DeveloperGuide.md#architecture)
section of the DG to explain how the classes of our program interacts with each other.
5. Added the entire [Ui](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/docs/DeveloperGuide.md#ui-component) section of the DG.
6. Added the [Common class](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/docs/DeveloperGuide.md#common-class) section of the DG.
7. Contributed to Target user profile  under `Appendix: Requirements` of DG.
8. Contributed to Value proposition  under `Appendix: Requirements` of DG.
9. Contributed to User Stories under `Appendix: Requirements` of DG.
10. Contributed to User Stories under `Appendix: Requirements` of DG.
11. Contributed to Glossary under `Appendix: Requirements` of DG.

[back to contents](#table-of-contents)

---

### Contribution to team-based tasks

- Contributes to team based tasks:
  - Maintaining issue tracker
  - Release Management (v2.0)

[back to contents](#table-of-contents)

---

### Contributions beyond the project team

Helped solved bugs in the methods of IncomeLists, and ExpenseLists when I help to integrate the
classes together for a working program. This happened at the early stage of our project when only the classes and methods for
Expense and Income are implemented, without any main, Ui, Parser, or Command classes. I implemented the rest of the classes and
made sure the features of expense and income are working as intended and that the program runs together with all the other classes.

[back to contents](#table-of-contents)
