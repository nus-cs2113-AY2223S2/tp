# Ong Chuan Kai - Project Portfolio Page

## Overview
CLIAlgo is a Command-Line Interface (CLI) application that allows CS2040C (Data Structures and Algorithm 
module by Prof Steven Halim) students to be able to **keep track of their note and code files** via features
such as adding, removing, exporting, filtering and topological sorting. 

### Summary of Contributions

#### Features Implemented

1. Adding of `CS2040CFile`s.
    - **What it does**: It allows the user to add their note or code files (`.txt` and `.cpp` 
    files respectively) stored locally into CLIAlgo to be kept tracked of. The note or code 
    files will be stored in CLIAlgo as `CS2040CFile` objects.

    - **Justification**: The add feature is the starting point for other features within CLIAlgo.
    Being able to add specific note and code files into CLIAlgo, the users will then be able to 
    access the other features of CLIAlgo. 

    - **Highlights**: The challenge with implementing this feature comes from having to keep track
    of the 10 different topics that the `CS2040CFile`s can come from. Therefore, the add feature is
    implemented with the `Topic` class that keeps track of the list of `CS2040CFile`s within the same 
    topic and `TopicManager` class that keeps track of the `Topic`s. 

2. Check existent of text or code file stored locally.
   - **What it does**: It checks the added `CS2040CFile`s by the user to determine whether they are
   stored locally as `.txt` or `.cpp` files as well as if the file exists locally.

   - **Justification**: Determining whether the `CS2040CFile`s added to CLIAlgo are `.txt`, `.cpp` or
   non-existent is important in ensuring that CLIAlgo is able to provide the proper user experience as
   an application that allows users to keep track of their `CS2040CFile`s.

   - **Highlights**: The implementation of this feature requires taking into account the possibilities
   whereby a `CS2040CFile` added exist as a `.txt`, exist as a `.cpp`, exist as other file types or does
   not exist at all. The implementation thus considers the situations whereby the `CS2040CFile` exist as
   other file types or does not exist at all to simply be non-existent. Therefore, a `FileType` enumeration
   had to be created with the values of `TXT`, `CPP` and `DOESNOTEXIST`.

3. Topological Sort of CS2040C Files.
    - **What it does**: It prints the `CS2040CFile`s in topological order based on the name of a given
    `CS2040CFile`. The `CS2040CFile`s within the same topic of the provided `CS2040CFile` will be printed,
    together with the `CS2040CFile`s of the topics that come before it. The topological order of the topics
    are fixed, following the teaching order of the topics in CS2040C.

    - **Justification**: This feature will aid the users in their revising of certain topics as there can be
    links between previous topics and pre-requisites that need to be studied before the current topic can be
    properly understood. Thus, printing a topological sort of the `CS2040CFile`s that come before a specified
    `CS2040CFile` name will allow users to be more aware of the content that needs to be studied.

    - **Highlights**: The implementation of this feature requires the checking of the topic that the specified
    `CS2040CFile` comes from. Afterwards, following an order that is pre-defined as a constant in `TopicManager`,
    the `CS2040CFile`s within the topic and before will be printed. Thus, a LinkedHashMap had to be used to 
    maintain the order of the topics. The challenge thus comes from passing the list of topologically sorted 
    notes to be printed from the `TopicManager` to `TopoCommand` to handle the printing. Thus, a method 
    `getAllCS2040CFilesBeforeTopic()` had to be created.

#### Code Contributed
The code contributed by me can be found on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ong-ck&tabRepo=AY2223S2-CS2113-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Documentation
1. User Guide.
    - Add table of contents.
    - Add documentation for `TopoSort`.
    - Add command summary.


2. Developer Guide.
    - Add table of contents.
    - Add design and implementation for `TopoSort` feature.
    - Add glossary.
    - Resizing and centering of all images.

#### Team-based tasks
1. Attended and participated actively in weekly discussions.
2. Fixed the following non-trivial bugs:
[#67](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/67),
[#96](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/96)
3. Helped to test and debug the application.
4. Wrote the `Command` abstract class that other classes inherit from and certain `Command` subclasses.
5. Helped implement the designs created by other team members.
6. Implement a colour scheme via a `style.iuml` file for all UML Class and Sequence Diagrams. 
7. Participated in video demo.
