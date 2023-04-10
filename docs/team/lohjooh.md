# Loh Joo Hoe - Project Portfolio Page

## Overview
CLIAlgo allows users who are taking CS2040C, data structures and algorithms, using the 
coding language C++, to **organise their notes and code quickly**. CLIAlgo allows them
to **categorise them in a variety of ways** such as, by topic or importance, and filter those
files into a separate folder.

### Summary of Contributions

#### Features Implemented

1. FileManager
   - **What it does**: It manages and stores the local data of the application, facilitating
   updating the relevant local data files whenever a `add` or `remove` input is entered by the
   user. It also checks for corruption of the data file and discards corrupted entries. It also
   facilitates the creation of the data files and folders.

   - **Justification**: As CLIAlgo would ideally be for users to quickly find all the files that 
   they need, it would be a hassle to re-enter all the entries for each file whenever a CLIAlgo
   is rebooted. Thus, the `FileManager` which acts as the storage for CLIAlgo would help users
   "remember" files as long as those files are entered into CLIAlgo once. Furthermore, due to the particular
   implementation of `FileManager`, corruption of entries are easier to deal with as the data files are stored
   `Topic` by `Topic`, the user would then know which file in which `Topic` is corrupted if corruption occurs.

   - **Highlights**: The challenge for dealing with locally stored data is to think up of possible
   ways users could attempt to corrupt or break the data files and subsequently, crash the 
   application. Safeguards that are reasonable have to be added in such a way that does not
   affect the user experience when using the application, i.e. adding checks for deleted files
   and recreating missing files with its relevant data intact without affecting the user input.


2. Buffer and Export
   - **What it does**: The `Buffer` updates the `CS2040CFiles` stored within it with the `CS2040CFiles`
   returned when the user inputs `filter` or `topo`. These files can then be output to a specific folder
   `./export`, with all the contents copied exactly when the user inputs `export`. Furthermore, after
   the files are copied into `./export`, the folder the files are copied into is opened up automatically
   by CLIAlgo.

   - **Justification**: As the purpose for CLIAlgo is to make organising code files and note files relating
   to CS2040C more convenient, it would be illogical not to have a way of placing all the files relevant to
   the user's filtering queries into the same place such that the user can easily find the files that they
   need while the other irrelevant files are out of sight. As such, users can be more efficient in their
   studies as the application would categorise the files and output it for them.
   
   - **Highlights**: The challenge to implement this is the updating of the `Buffer` whenever a `filter` or
   `topo` command is input by the user. That is because the implementation of the said commands did not 
   initially have support to output the `CS2040CFiles` objects to the `Buffer` and said functionality has
   to be added retroactively to all the relevant `Command` object derivatives. It is also tedious to have to
   retroactively add `Buffer` to all the `execute()` methods of all `Command` such as to ensure future
   expandability, i.e. in case a future function needs to evoke `Buffer`  and its methods. `Buffer` is also
   a Singleton class, a class with a private constructor, as only one `Buffer` per instance of CLIAlgo is
   needed by the application.
   
3. Test Mode
   - **What it does**: Test mode is a mode that is started by inputting `start-test-mode` and ended by 
   inputting `end-test-mode`. When CLIAlgo is in test mode, all the data saved while in test mode would
   be cleared when test mode is ended.

   - **Justification**: Test mode is not a function meant to be used by a typical user of CLIAlgo. 
   However, it is made to be a debugging mode such that all the data is automatically cleared upon
   exit. This allows testers to not have to delete all the data manually whenever they need to test
   a feature which has save data involved.

#### Code Contributed
The code contributed can be found  on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=lohjooh&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lohjooh&tabRepo=AY2223S2-CS2113-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

#### Documentation
1. User Guide.
    - Add documentation for `export`.

2. Developer Guide
   - Add design for `FileManager`.
   - Add implementation for `initialize()`.
   - Add implementation for `writing a CS2040CFile to data file`.
   - Add design and implementation for `Buffer`.
   - Add entire section on guidelines for testers to test the application.

#### Team-based tasks
1. Attended and contributed to weekly discussions.
2. Helped in designing the implementation of the base functions of CLIAlgo.
3. Design the methods to save data and to encode and decoded the raw data.
4. Helped with debugging of code.
5. Fixed the following non-trivial bug:
[#36](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/36),
[#85](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/85),
[#179](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/179),
[#184](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/184),
[#187](https://github.com/AY2223S2-CS2113-T15-1/tp/issues/187)
6. Wrote template code for certain `Command`s.
7. Helped with integration of functions.
8. Participated in the video demo.