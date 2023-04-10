# Nicholas H Goh Maowen - Project Portfolio Page

## Overview
CLIAlgo is a desktop application for **managing your CS2040C notes and code**. It is optimized to be used via a Command
Line Interface (CLI). If you can type fast, you can access and sort your notes faster than ever before.

### Summary of Contributions

#### Features Implemented

1. Remove `CS2040CFile`
    - **What it does**: Allows users of `CLIAlgo` to remove `CS2040CFile`s that are stored inside `CLIAlgo`. This does
      not delete the `CS2040CFile` from the program directory, but rather it stops our `CLIAlgo` program from tracking
      it.
    - **Justification**: By having the `Remove` feature, users can choose to remove `CS2040CFile`s which they would no
      longer want to be tracked by our `CLIAlgo` manager. This can be useful if the users do not want to over clutter 
      their `CLIAlgo` with unnecessary `CS2040CFile`s which they have added previously.
    - **Highlights**: The concept of abstraction was used, whereby the lower level details of removing the  
      `CS2040CFile`s is not handled in the primary `RemoveCommand` object, but by the `TopicManger`and `FileManager`
      objects.

#### Code Contributed
The code contributed by me can be found on [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=nicholas&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=test-code~other~docs~functional-code&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=nicholas132000&tabRepo=AY2223S2-CS2113-T15-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code~other~docs~functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

#### Documentation
1. User Guide.
    - Add documentation for `add`.
    - Add documentation for `remove`.
    - Add documentation for `FAQ`.

2. Developer Guide.
    - Add design and implementation for `Add` feature.
    - Add design and implementation for `Remove`  feature.

#### Team-based tasks
1. Attended weekly discussions.
2. Planned out the direction and flow of the demo video.
3. Participated in the video demo.


