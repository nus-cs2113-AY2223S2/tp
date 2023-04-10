# Clement Siut - Project Portfolio Page

## Project: NUS To-Do List
NUS To-Do List is a Java command-line interface (CLI) application that is designed to allow NUS students to keep track
of tasks that needs to be completed.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature**: Created a repeating attribute to allow users to repeat tasks.
    - What it does: Allows the user to add a repeating task to the To-Do list.
    - Justification: This feature is essential to the program as users may have regularly recurring tasks with similar 
      descriptions. This serves as a convenience for them as the program will automatically generate the next recurring
      task upon passing the deadline of the first.

- **New Feature**: Created an edit deadline command to allow users to edit deadlines of existing tasks.
    - What it does: Allows the user to edit the deadline of task with the given id by the user.
    - Justification: This feature is essential to the program as users may want to change the initial deadline provided
      as they may wish to bring forward or delay the deadline of the tasks.

- **New Feature**: Created an edit repeat command to allow users to edit the number of repeats for existing tasks.
    - What it does: Allows the user to edit the repeat count of a task with the given id by the user.
    - Justification: This feature is essential to the program as users may want to reduce or increase the number of 
      times a task is repeated to prevent unnecessary duplicate tasks from being created by the program.

- **New Feature**: Created filters for listing of existing tasks.
    - What it does: Allows the user to filter existing tasks in the task list and list the filtered tasks.
    - Justification: This feature is essential to the program as users may want to narrow down on certain tasks that they 
      would like to see in the To-do list and do not want to have to sieve through all tasks in it. Thus, the features helps 
      to save time for the user in identifying the tasks that they want to find.

- **New Feature**: Created a configuration file to allow users to control the frequency of repeating tasks as well as 
    checks for repeating tasks.
    - What it does: Allows the user to edit the frequency of repeating tasks and repeating tasks checks.
    - Justification: This feature is essential to the program as users may want to reduce the number of times the 
      program checks for repeating tasks to minimise the performance impact during their usage of the program. They may
      also want their tasks to repeat at a lower or higher frequency.


#### Code Contributed:
- [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=clement559&breakdown=true)

#### Enhancements Implemented:
- Wrote JUnit test for the edit deadline command.

#### Contributions to the UG:
- Contributed to the `Command Summary` section.
- Updated and contributed to the `Features` section.
    - Contributed to the `Edit/delete deadline` feature component.
    - Contributed to the `Edit/delete recurring count` feature component.
    - Contributed to the `View all/selected tasks in To-Do list` feature component.
    - Contributed to the `View/Edit configurable settings` feature component.
    - Contributed to the `Help` feature component.

#### Contributions to the DG:
- Added implementation details for the edit deadline command.
- Added implementation details for the repeating task command.
- Created Sequence Diagrams using Plant UML for edit deadline command.

#### Contributions to team-based tasks:
- Contributed to the planning of the product by coming up with features to implement.
- Ensure that the code being merged does not have bugs or typos.

#### Review/monitoring contributions:
- Reviewed and approved PRs for the team: [Full List](https://github.com/AY2223S2-CS2113-T11-4/tp/issues?q=is%3Aclosed+reviewed-by%3Aclement559)
- #13,#20,#22,#25,#32,#46,#47,#50,#55,#57,#69,#114,#119,#124,#126

### Contributions beyond the project team:
- Reviewed and provide suggestions to other group's developer guide: [CS2113-T09-1](https://github.com/nus-cs2113-AY2223S2/tp/pull/14#pullrequestreview-1364260237)
- Reported bugs in other teams' programs and provide suggestions to improve the program: [CS2113-T13-2](https://github.com/clement559/ped/issues).