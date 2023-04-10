# Nikhil Shashidhar - Project Portfolio Page

## Overview
`CLIAlgo` is a command-line interface application for CS2040C students to organize, view and export their notes. It 
allows users to perform operations like filtering by topic or importance, perform topo-sort and export files to make 
accessing relevant notes quick and easy.

### Summary of Contributions

#### Features Implemented

1. Filter By Importance

   - **What it does**: This operation allows users to filter CS2040C files ranked in descending order of importance. 
   When adding a file, the user is allowed to key in an importance level for the file being added from a range of 1-10
   in the `i/` field (set to 5 if left blank). When `filter k/importance [t/TOPIC]` is input, all notes are displayed 
   in decreasing order of importance - and can be further filtered by the specified topic (optional field). 

   - **Justification**: Different students may find certain files more or less important than others. This tool lists 
   files with the highest importance files on top, allowing users to prioritise what to look into based on what they 
   set the file importance to upon making the entry (which they may have forgotten long into the future). Additionally,
   users can also further filter their files of a certain `TOPIC` by decreasing order of importance.
   
   - **Highlights**: The implementation was rather challenging because this was a v2.0 feature, which required changing 
   of a few classes. It involved making new constructors, custom lambda comparators and handling specific invalid 
   commands.

2. Help Command

   - **What it does**: This operation allows users to view the main help page and get further assistance on specific
   commands displayed on the help page. This allows new users to view the list of available functions supported
   by `CLIAlgo` easily, and further view how to use each specific command.
   
   - **Justification**: Having this feature on the CLI reduces the dependency on the UG (on top of reducing the need to 
   constantly switch tabs).
   
   - **Highlights**: This feature extends from the abstract `Command` class (inheritance) to prevent code duplication.

#### Code Contributed
The code contributed can be found on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=nikkiDEEE&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=nikkiDEEE&tabRepo=AY2223S2-CS2113-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

#### Documentation
1. User Guide
   - Add documentation for "Quick Start"
   - Add documentation for `help` feature.
   - Add documentation for `filter by importance` feature.

2. Developer Guide
   - Add documentation for "Product Scope"
   - Add design and implementation for `Ui`
   - Add design and implementation for `help`

#### Team-based tasks
1. Attended and contributed to weekly discussions.
2. Accommodated and accounted for team opinions in implementation.
3. Review team members PR with non-trivial comments.
4. Fixed the following non-trivial bugs:
   [#169](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/169)
5. Helped to test, report and debug the application.
6. Participated and edited the video demo.
7. Styled the UG (Jekyll).
