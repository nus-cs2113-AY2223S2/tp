# Thomas Jiralerspong - Project Portfolio Page

## Overview: PC Part Picker
PC Part picker is a CLI application that allows users to design and save their own custom PC build. Users can create, 
view, and delete builds, use and modify pre-existing builds, add parts from an existing database, or create their
own custom parts to add to their builds.

## Summary of Contributions
- [Code contributed](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=superkaiba&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
- Enhancements implemented:
  - `custom COMPONENT_TYPE SPEC_1,SPEC_2,...` command 
    - Wrote abstract `BuilderCustomComponentCommand` class for 
    all the specific `CustomComponentCommand` classes to inherit from
    - Wrote specialized subclasses for each COMPONENT_TYPE (inheriting from `BuilderCustomComponentCommand`)
    to add a custom component of each COMPONENT_TYPE
  - `list COMPONENT_TYPE` command
    - Wrote the `BuilderListComponentCommand` class which fetches a list of all components of a certain type from 
    the `DataStorage` class, filters them based on flags provided by the user, and displays them to the user
    in a nice format 
    - Modified the `DataStorage` class to have it store separate lists of all components of each type and 
    make these lists easily accessible to other classes
  - `help` command in builder mode
- Contributions to the UG:
  - Section on `custom COMPONENT_TYPE SPEC_1,SPEC_2,...` command
    - Detailed description of how to use the command and example code
- Contributions to the DG:
  - Section on `list COMPONENT_TYPE` command:
    - Detailed description of how the command was implemented and sequence diagram
    showing the flow of the program when the command is used
- Review/mentoring contributions:
  - [PR1](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/72)
  - [PR2](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/47)
  - [PR3](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/25)
