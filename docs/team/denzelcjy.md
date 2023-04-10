# Denzel Chen - Project Portfolio Page

## Overview

Modganiser facilitates effortless module planning at your fingertips via the Command Line Interface (CLI), focused on helping
Information Security Undergraduates graduate on time in 4 years. It provides a platform to plan all 4 years of your modules
to ensure that you meet your graduation requirements.
Built to run on Java 11, Modganiser can definitely get your module plan up faster than traditional Graphical User Interface (GUI)
applications if you can type fast.

## Summary of Contributions

### Code Contributed
* Please click on this [link to my RepoSense report](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=denzelcjy&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to view my contributions.

### Enhancements Implemented
* Basic Template Class Code
    * **PR**: [#31](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/31)
      * Created the template code for the different classes of modules.

* Handled the Print class UI and Logo on startup
    * **PR**: [#31](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/31), [#37](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/37) 
      * Handled print messages to user, and creation of Modganiser Logo.

* Grade Command
    * **PR**: [#84](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/84), 
[#292](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/292)
      * Added the grade command to allow users to grade their completed modules in their module plan.

* CalculateCAP Command
    * **PR**: [#84](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/84)
      * Added the calculateCAP command to allow users to calculate their CAP based on graded modules that have grade value.
<div style="page-break-after: always;"></div>

* JUnit Testing, Assertions, Logging
    * **PR**: [#36](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/36), 
[#53](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/53), 
[#55](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/55), 
[#111](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/111), 
[#112](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/112)
      * Added Junit Testing to `Print` class,  `grade` and `calculateCAP` commands.
      * Added assertions for method in `Parser` class.
      * Set-up Logging, added logging to `grade` and `calculateCAP` commands.

* PE-D Bugs
    * **PR**: [#248](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/248)
      * Squashed PE-D bugs related to `grade` and `calculateCAP` commands, as well as modular credit limit error in `add` command.

### User Guide
* **PR**: [#138](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/138), 
[#145](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/145), 
[#160](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/160), 
[#163](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/163), 
[#172](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/172), 
[#177](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/177), 
[#256](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/256),
[#271](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/271)
  * Creation of hyperlinks to each section of the User Guide.
  * Added the Table of Contents.
  * Added sections on `grade` and `calculateCAP` command.
  * Added initial screenshots.
  * Added extra notes to fix PE-D bugs for User Guide.

### Developer Guide
* **PR**: [#99](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/99), 
[#281](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/281)
  * Added descriptions and UML diagrams for `grade` and `calculateCAP` commands

### Contributions to Team-based Tasks
* Started issue tracker, created labels for issues.
* Created the logger.
* Learnt PlantUML to create the template sequence diagram for my team.

### Reviewing / Mentoring
* Reviewing **PRs**: [#81](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/81), 
[#96](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/96),
[#117](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/117), 
[#162](https://github.com/AY2223S2-CS2113T-T09-4/tp/pull/162)

### Contributions beyond the Project Team
* Found and reported [PE-D bugs](https://github.com/denzelcjy/ped/issues) for team [Clanki](https://github.com/AY2223S2-CS2113-T15-4/tp)
* Forum contribution in finding [website bug](https://github.com/nus-cs2113-AY2223S2/forum/issues/23).

<div style="page-break-after: always;"></div>

### Extract: User Guide
#### 5.2.2 Adding Grade Obtained for Module: `grade`
**Function**

After you have finished the module, you will receive a grade for it. Input the grade you got for the module into Modganiser using this command which is used to [calculate your CAP](https://ay2223s2-cs2113t-t09-4.github.io/tp/UserGuide.html#542-calculating-your-current-cap-calculatecap).

| [Parameter](https://ay2223s2-cs2113t-t09-4.github.io/tp/UserGuide.html#8-glossary) |              Description              |                     Accepted Inputs                     |
|:------------------------:|:-------------------------------------:|:-------------------------------------------------------:|
|       MODULE_CODE        |           The module's code           | All inputs consisting of [alphanumeric](https://ay2223s2-cs2113t-t09-4.github.io/tp/UserGuide.html#8-glossary) characters                       |
|          GRADE           | The grade you obtained for the module | A+, A, A-, B+, B, B-, C+, C, C-, D+, D, F, S, U, CS, CU |

**Command Format**

`grade /MODULE_CODE /GRADE`

Example: `grade /CS2113T /A` adds grade “A” to CS2113T

![GradeCommand](../UG_Screenshots/InputGrade.png)

<div style="page-break-after: always;"></div>

### Extract: Developer Guide
#### Calculate CAP

`calculateCAP` command:

The `calculateCAP` command is used by the user to calculate the Cumulative Average Point (CAP) of graded modules.

The sequence by which the Parser class handles the `calculateCAP` command is as follows:
1. The `Parser` class extracts the commands from the user input and calls upon the `calculateCAPCommand()` method
   in the `Command` class.
2. The `calculateCAPCommand()` method extracts the other fields of the user input, and calls upon the
   `calculateCAP()` method in the `ModuleList` class.
3. The method then loops through the moduleList array to find modules that already have grades.
4. It checks if the module grade should be counted by self-invocation of the `shouldCountModuleGrade()` method
5. If it is, proceeds to obtain the grade value by self-invocation of the `getGradeValue()` method.
6. Calculates the accumulated grade values and their weightages based on modular credits, and finally calls the
   `printCalculatedCAP()` of the `Print` class to display the calculated CAP to the user

<div style="page-break-after: always;"></div>

The sequence of events above can be represented with the following sequence diagram:

![calculateCAP](../diagrams/CalculateCAP.png)