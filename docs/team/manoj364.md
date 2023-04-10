# Manoj Dorairajan - Project Portfolio Page

## Overview

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient
operation through the Command-Line Interface(CLI).

### Summary of Contributions

- Code contributed: [Reposense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=manoj364&breakdown=true)

#### Enhancements Implemented:

- New Feature: Ui
    - What it does: Allows the user to interact with the program by receiving messages from the program, triggered by
      their inputs.
    - Justification: This feature improves the product significantly as a user is able to receive visual confirmation
      that the requested commands are either being executed or rejected by the application. If rejected, 
      the corresponding reason is also displayed to the user
- New Feature: Duplication checker
    - What it does: Makes sure that duplicate entries by the user are not added into the list
    - Justification: This feature improves the product significantly as a user can mistakenly add the same company twice
      and the program provides a convenient way to be informed of the duplication mistake
    - Highlights: This feature was used in other program features as well, such as  `add samples` where the duplication
      checker is used to check if samples have already been added by the user

#### Enhancements to existing features:

- Improved feature: Duplication checker for each field
    - Enhancement: The Duplication checker now checks each field to make sure that the company name, contact email, or
      contact email are not the same for any 2 entries in the `CompanyList` arraylist. If such a scenario exists, the
      program will inform the user of the duplication error. ([#125](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/125))
- Improved feature: Formatting for input fields
    - Enhancement: For the `add` feature, the fields that are filled in by the user get formatted before being passed as
      parameters to be added into the `CompanyList` arraylist. The formatting includes capitalisation and removing
      trailing and leading whitespaces. ([#37](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/37))
- Improved feature: Tests for `CompanyList` methods
  - Enhancement: Wrote additional tests for `CompanyList` to increase coverage ([#136](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/136))

### Documentation

- UG contribution
    - Added documentation for `help` command
    - Resolved PE-D bugs ([#124](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/124))

- DG contribution
    - Added documentation for `Ui`
    - Class diagram for `Ui` class
      - ![Ui_class.png](..%2FUML%2FImage%2FUi_class.png)
    - Sequence diagram for the `showWelcome` method in `Ui` class
        - ![Ui_sequence_diagram.png](..%2FUML%2FImage%2FUi_sequence_diagram.png)
    - Added documentation for Duplication Checker feature
    - Sequence diagram for Duplication Checker feature
        - ![Duplication_checker.png](..%2FUML%2FImage%2FDuplication_checker.png)

### Contributions to team-based tasks
- Created milestones for each release: [v1.0](https://github.com/AY2223S2-CS2113-W12-2/tp/milestone/1), [v2.0](https://github.com/AY2223S2-CS2113-W12-2/tp/milestone/2), [v2.1](https://github.com/AY2223S2-CS2113-W12-2/tp/milestone/3)
- Reviewed PRs (with non-trivial comments): [#17](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/17), [#20](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/20), [#35](https://github.com/AY2223S2-CS2113-W12-2/tp/pull/35)

