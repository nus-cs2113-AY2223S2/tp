# Vu Van Dung's Project Portfolio Page

## Project: Clanki

Clanki is a command line application one can use to store and update flashcards
to help them remember the relevant concepts. The user can interact with it by
typing code into their terminal.

Given below are my contribution to the project.

### Summary of Contributions

- **New feature:** Parser

  - What it does: allows the app to intuitively and safely parse user input
  - Justification: without it, imperative and specific implementation of the
    parser for each command might be necessary, which will just be a grand
    invitation for bugs
  - Highlights: thanks to this feature, all places in the app where user input
    is parsed can be handled declaratively. Since we take a lot of user inputs,
    this is crucial for the app

- **New feature:** Set up the ground work

  - What it does: not a user-facing feature, but this sets up Gradle, tests and
    an overall file structure along with crucial classes (`Flashcard`,
    `FlashcardList`, `FlashcardQueue`) that are used in all other places in the
    app
  - Justification: make development of the app easier

- **Major contribution:** Improve other features by other members

  - What it does: other members' code might be inconsistent, might be
    reinventing the wheels, might have hidden bugs, or overall just might be
    bad; I fixed them to a level I'm at least not depressed when looking at the
    code.

- **Code contributed:**
  [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=joulev&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=joulev&tabRepo=AY2223S2-CS2113-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- **Contributions to UG:**

  - Enhanced the user guide for all sections to make them more accurate and
    closer to the actual behaviour and output of the app
  - Added input restrictions (if any) to the UG

- **Contributions to DG:**

  - Added details for the parser

- Other contributions:

  - (Forced to) lead the project. I am basically the one who decided the
    timeline and did most of the admin stuff
  - Triaged incoming issues, opened tickets and assigned them to relevant
    personnels
  - Reviewed pull requests
