# Er Jun Ze - Project Portfolio Page

## Project: NUS To-Do List

NUS To-Do List is a Java command-line interface (CLI) application that is designed to allow NUS students to keep track
of tasks that needs to be completed.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature**: Created the `Parser` and `ParserUtil` classes for parsing commands from the user.
  - What it does: Parses user input into the respective commands and their parameters.
  - Justification: This feature is essential to the program as it allows the program to understand what the user wants
    it to do.

- **New Feature**: Created a tag system for users to add tags, in the form of strings, to their tasks.
  - What it does: Allows users to add, edit and remove tags from their tasks using the `tags` command. Users can also
    see all the tags present in their task list using the `taglist` command, in case they forgot what tags they have
    used, after which they can view/edit tasks with those tags.
  - Justification: This feature is useful to the program as it sets up the ability for users to search for tasks by
    tags, or target certain tasks with commands depending on the tags they have. For example, students can add module
    codes to tasks as a tag, so they can easily find all tasks in their task list that are related to that module later.

- **New Feature**: Increase flexibility of task selection in multiple commands, not just the `list` command.
  - What it does: Allows users to select more than one task to target with a command. Users can specify multiple task
    ids instead of just a single id, or use filters to target all tasks fulfilling certain conditions.
  - Justification: This feature is useful to the program as it affords more flexibility to users when it comes to
    manipulating their task list. Users do not have to individually target tasks, which will require as many commands
    as tasks they want to edit/delete, and can instead mass edit/delete tasks.

### Code Contributed:

- [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=erjunze&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements Implemented:

- Wrote some JUnit tests for the `ParserUtil` class.
- Organized code to reduce coupling and be more in line with OOP principles.

### Contributions to the UG:

- Contributed to the `Command format` section.
- Contributed to the `Add task`, `Edit description`, `Edit tags`, and `View all tags` components in the `Features` section.

### Contributions to the DG:

- Added architecture diagram and described the overall project structure.
- Added component class diagrams and described briefly how each component functions.
- Formatted the colour scheme and other style attributes across all the diagrams.

### Contributions to team-based tasks:

- Contributed to the planning of the product by coming up with features to implement.
- Ensure that the code being merged does not have bugs or typos.

### Review/monitoring contributions:

- Reviewed and approved PRs for the [team](https://github.com/AY2223S2-CS2113-T11-4/tp/issues?q=is%3Aclosed+reviewed-by%3AERJUNZE)

### Contributions beyond the project team:

- Reviewed the developer guide for another team and provided suggestions: [CS2113-W15-1](https://github.com/nus-cs2113-AY2223S2/tp/pull/94)
- Reported bugs in the program of another team and provided suggestions for improvement: [CS2113T-T09-1](https://github.com/ERJUNZE/ped/issues)
