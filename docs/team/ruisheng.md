# Seow Rui Sheng - Project Portfolio Page

## Project: NUS To-Do List
NUS To-Do List is a Java command-line interface (CLI) application that is designed to allow NUS students to keep track
of tasks that needs to be completed.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature**: Created a delete command to allow users to delete tasks. 
  - What it does: Allows the user to remove a task with the given id by the user from the To-Do list.
  - Justification: This feature is essential to the program as users may make mistakes in commands or a task that may
    be overdue. This feature provides a convenient way to rectify the mistakes or remove unwanted tasks.

- **New Feature**: Created a mark command to allow users to mark tasks.
    - What it does: Allows the user to mark a task with the given id by the user as completed.
    - Justification: This feature is essential to the program as users may want to indicate that they have completed a
      and to better find the remaining tasks that are incomplete.

- **New Feature**: Created an unmark command to allow users to unmark tasks.
    - What it does: Allows the user to unmark a task with the given id by the user as completed.
    - Justification: This feature is essential to the program as users may want to remove a completed task from the list
      without deleting it entirely. It is also helpful as users may make mistakes by marking an uncompleted task. It is 
      also useful for recurring tasks that need to be completed on a regular basis.

- **New Feature**: Created an email command to allow users to add/edit/delete email.
    - What it does: Allows the user to add, edit, or delete the email address of a task with the given id in the To-Do List.
    - Justification: This feature is essential to the program as users may want to add an email address associated to the
      task so that they can keep track of the professors to email if the users need help. Users may make mistakes when
      entering an email address which is why users can edit and delete the email.

- **New Feature**: Created a priority command to allow users to edit priority level.
    - What it does: Allows the user to edit, or delete the priority level of a task with the given id in the To-Do List.
    - Justification: This feature is essential to the program as different tasks have different urgency and the app should
      provide a convenient way to shows the user which tasks should be prioritised and which task should be left later.

- **New Feature**: Provide a help list.
    - What it does: Displays a help list to show the user all the commands of the program.
    - Justification: This feature is essential to the program as users do not know the commands of the To-Do List and it
      would be tiring to have to constantly refer to the UserGuide.


#### Code Contributed:
- [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ruishenggit&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements Implemented:
- Wrote JUnit test for the delete command.

#### Contributions to the UG:
- Contributed to the `Command Summary` section.
- Updated and contributed to the `Features` section.
  - Contributed to the `Mark a task` feature component.
  - Contributed to the `Unmark a task` feature component. 
  - Contributed to the `Delete a task` feature component.
  - Contributed to the `Edit priority level of a task` feature component.
  - Contributed to the `Add/Edit/Delete an email` feature component.
  - Contributed to the `Help` feature component.

#### Contributions to the DG:
- Added implementation details for the mark/unmark command.
- Added implementation details for the delete command.
- Created Sequence Diagrams using Plant UML for delete, mark, unmark commands.

#### Contributions to team-based tasks:
- Contributed to the planning of the product by coming up with features to implement.
- Ensure that the code being merged does not have bugs or typos.

#### Review/monitoring contributions:
- Reviewed and approved PRs for the team: [CS2113-T11-4](https://github.com/AY2223S2-CS2113-T11-4/tp/pulls?q=is%3Apr+is%3Aclosed)

### Contributions beyond the project team:
- Reviewed and provide suggestions to other group's developer guide: [CS2113-T09-2](https://github.com/nus-cs2113-AY2223S2/tp/pulls?q=is%3Aopen+is%3Apr+CS2113T-T09-2+)
- Reported bugs in other teams' programs and provide suggestions to improve the program: [CS2113-T15-3](https://github.com/RuiShengGit/ped/issues).