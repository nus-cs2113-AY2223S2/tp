# Song Zijin's Project Portfolio Page

## Project: Clanki

Clanki is a command line application one can use to store and update flashcards to help
them remember the relevant concepts. The user can interact with it by typing code into
their terminal.

Given below are my contribution to the project.

### Summary of Contributions

- **New feature:** Added ability to add a new command
    - What it does: allows the user to add a new flashcard with the specified question and answer text.
    - Justification: This feature is one of the basic building block of the entire program, without it, no
      other command on modifying the flashcards can work.
    - Highlights: Since it is the initial step, building this feature included setting the groundwork in other basic
      classes, such as creating the abstract Command class for other commands to use and FlashcardList class to
      handle the flashcards that are created. JUnit test codes are also written for this feature.
    - Credits: idea for the entire structure are reused from Song Zijin's individual project
      (link [here](https://github.com/SongZijin/ip)), with many inspiration from
      [addressbook-level2](https://github.com/se-edu/addressbook-level2).
- **New feature:** Added ability to save current state of all flashcards and load the state upon application start
    - What it does: allows the user to store the previously recorded flashcards,
      so that they can come back to review it in another day.
    - Justification: This feature improves the product significantly, as the user can close the application
      and come back in another day to review the flashcards they wish to learn.
    - Highlights: The entire function consist of 3 parts, the encoder, decoder and a class that link the other two
      together. The feature is also flexible when the stored data is corrupted, with the incorrectly formatted line
      automatically deleted instead of crushing the whole program.
    - Credits: the code is improved on the storage code from
      [addressbook-level2](https://github.com/se-edu/addressbook-level2).
- **New feature:** Added a help menu
    - What it does: provides the user some infomation about what are the commands they can use for this application.
    - Justification: This feature improves the product, as the user is now able to use the product independently,
      without the need to look up any user guide of the product.

- **Code contributed:**
  [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=SongZijin&tabRepo=AY2223S2-CS2113-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- **Contributions to UG:**
    - Added section under Quick Start.
    - Provided initial draft to `add` function.
    - Added documentation for `bye` and `help` functions.
- **Contributions to DG:**
    - Added implementation details for the `add` function.

- Other contributions:
    - Reviewed pull requests, such as [#53](https://github.com/AY2223S2-CS2113-T15-4/tp/pull/53)
    - Posted issues, such as [#61](https://github.com/AY2223S2-CS2113-T15-4/tp/issues/61)
