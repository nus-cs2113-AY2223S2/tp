# Ong Hee Jet - Project Portfolio Page

## Overview
CLIAlgo is a desktop application for **managing your CS2040C notes and code**. It is optimized to be used via a Command
Line Interface (CLI). If you can type fast, you can access and sort your notes faster than ever before.

### Summary of Contributions

#### Features Implemented

1. Parser
   - **What it does**: It processes commands by the User through the `Ui` and prepares the appropriate `Command`.
   object to be executed by `CLIAlgo`.

   - **Highlights**: The challenge with implementing the parser was being able to extract out relevant keywords from the
   user input and using the correct constructor to instantiate the correct `Command` object and returned it to 
   `CLIAlgo`. To do this task efficiently without excessive code duplication, the `Parser` implements the 
   `StringManipulation` interface. The `StringManipulation` interface handles all operations such as splitting the input
   string and throwing any `Exceptions` that occur during the operation and returns `null` when the string is
   empty.

<div style="page-break-after: always;"></div>

2. List all `CS2040CFile`s.
   - **What it does**: Allows users of `CLIAlgo` to list all the `CS2040CFile`s that are tracked by `CLIAlgo`. Each 
   `CS2040CFile` is also labelled as either a `CODE` or a `NOTE` file based on its file extension. `.cpp` files are
   labelled with `[CODE]` and files with `.txt` are labelled with `[NOTE]`.
   
   - **Justification**: By having the `list` feature, users can keep track on the number and type of notes stored within
   `CLIAlgo` which helps them in their revision. The labels serve as an important visual markers for them to identify
   relevant files when completing their weekly assignment and preparing for the practical exam (`[CODE]` files) or when
   they are preparing for the midterms and finals (`[NOTE]` files).

   - **Highlights**: The implementation of this feature uses the `ArrayList` data structure to pass around the list of
   all `CS2040CFile`. The concept of polymorphism was used to allow `ArrayList` to hold two different objects(`Note`
   and `Code`). I have also written the `printListOfCS2040CFiles()` method in the `Ui` class so that `ArrayList` can be
   printed without duplicating code.
   

3. Filter by topic.
   - **What it does**: The filter by topic feature has two executions. The first execution occurs when the optional
   `topic` field in the command is left empty. In this version, `CLIAlgo` prints out all `CS2040CFiles` that are tracked
   by the application. The `CS2040CFiles` are grouped according to the `topic` they are tagged to. The second execution
   of the filter by topic feature occurs when the user provides a valid `topic` in the `topic` field. In this execution, 
   `CLIAlgo` only prints `CS2040CFile`(s) tagged to the `topic` provided by the user. 

   - **Justification**: This feature allows users to easily view the relevant learning materials they are looking for.
   For example, if they want to study a specific chapter such was `SORTING`, they can use this command to extract out 
   relevant files to aid in their revision.

   - **Highlights**: The implementation of this feature was challenging as there are a total of 10 topics, each with
   their own set of notes. To implement this feature elegantly and efficiently, the 'HashMap' data structure was used
   to access the various `topics` in CS2040C in approximately O(1) time. The relevant `CS2040CFile`(s) can then be
   extracted and printed to the user.

<div style="page-break-after: always;"></div>

#### Code Contributed
The code contributed by me can be found on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=Ong%20Hee&sort=totalCommits%20dsc&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=functional-code~docs~test-code~other&since=2023-02-17&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=heejet&tabRepo=AY2223S2-CS2113-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

#### Documentation
1. User Guide.
   - Add documentation for `list`.
   - Add documentation for `filter by topic`.

2. Developer Guide.
   - Add architecture diagrams and explanations.
   - Add design and implementation for `list` feature.
   - Add design and implementation for `filter by topic`  feature.
   - Add design and implementation for `parser`.
   - Add architecture diagram for `CLIAlgo`.
   - Add user stories.

#### Team-based tasks
1. Created labels and milestones in the team's GitHub repository. 
2. Organized weekly online meetings and discussions. 
3. Created issues based on work assigned during team meetings. 
4. Compiled and build final `.jar` files for releases. 
5. Distributed workload among team members using GitHub issue tracker. 
6. Set internal deadlines each iteration using Milestones and GitHub Projects. 
7. Review team members PR with non-trivial comments.
8. Non-trivial bug fixes: [#124](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/124), 
[#127](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/127), 
[#138](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/138), 
[#166](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/166),
[#256](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/256).
9. Designed the architecture of `CLIAlgo` and how `TopicManager` and `Topic` interact with each other
to implement various features of `CLIAlgo` (`Filter`, `Add`, `Remove`, `List`, `Topo`).
10. Participated in video demo.
11. Help to reformat UG and DG.
