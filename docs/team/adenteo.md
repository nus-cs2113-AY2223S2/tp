# Aden Teo's Project Portfolio Page

# PocketPal

PocketPal is a user-friendly money-management app that makes it easy to track expenses. With a command-line interface,
users can quickly log and access their expenses, and categorize them by type. Users can also easily filter expenses
based on date, category and price range. Overall, PocketPal is a convenient tool for managing your expenses.

Given below are my contributions to the project.

- **Core Functionality**: Added the fundamental logic for parsing of user inputs.
    - **What it does**: Accurately and efficiently process user commands and carry out the requested actions.
    - **Justification**: This logic is crucial for interpreting user inputs and allowing the application to seamlessly
      handle a wide range of commands, hence providing a smooth user experience.
    - **Highlights**: This logic had to be constantly refined as we introduced additional features into PocketPal, and
      also when bugs were gradually being discovered. It was also challenging to provide a smooth experience where users
      were able to provide minimal arguments but yet achieve a wide range of functionality.
- **Feature**: Added the ability to view total costs of the expenses listed when using `/view`.
    - **What it does**: Allow the user to view the total expenditure of the expenses listed when filtered using `/view`.
    - **Justification**: This feature allows users to have a quick summary of their total expenses to date, or
      specifically for different categories, date periods, or price ranges.


- **Code contributed**: [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=adenteo) link

- **Project Management:**
    - Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

- **Enhancements to existing features**:
    1. Wrote test cases to achieve 100% test coverage for `Parser`. (PRs #117, #149, #226)
    2. Fixed functionality/feature bugs where negative and scientific notation for prices were accepted when creating or
       editing an entry. (PRs #119, #126)
    3. Refactored `Parser` entirely by creating an abstract `ParseCommand` class to be implemented by the respective
       commands, hence increasing clarity of the implementation. (PR #217)
    4. Fixed multiple feature/functionality and documentations bugs raised from dry run testing by:
        1. completely re-documenting User Guide, Developer Guide with widely used command-line syntax convention. (PRs
           #206, #221, #225)
        2. revising `Parser` logic with efficient encapsulation and abstraction. (PR #217)
- **Documentation**
    - **User Guide**:
        + Authored documentation for over 90% of the supported features, providing comprehensive usage instructions and
          illustrative examples of expected and actual outputs.
    - **Developer Guide**:
        + Illustrated implementation details of the `Parser` class with detailed class and sequence diagrams.
        + Wrote all test cases for manual testing.
    - **Video demonstration**:
        + Narrated and recorded the video demonstration of PocketPal.

- **Community**
    - PRs reviewed with non-trivial comments (PR #54, #56, #65, #88, #254)
    - Conducted peer review with W13-2 and provided constructive feedback, such as the implementation of Google's
      CLI style convention, as well as how to improve clarity of User Guide.