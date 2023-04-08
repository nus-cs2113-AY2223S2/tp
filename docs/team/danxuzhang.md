# Danxu Zhang - Project Portfolio Page

## Overview
Duke of Books is a CLI library management system targetting NUS computer science (CS) students who wish to borrow and read CS related books. CS students are incredibly busy and hence having a command line interface (CLI) program without GUI makes finding the books they want quick and efficient. This software will also help them track loans and return dates.


## Summary of Contributions

Given below are my contributions to the project:

* **Code Contribution**:
Link to [RepoSense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=danxu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=danxuZhang&zR=AY2223S2-CS2113-F10-4%2Ftp%5Bmaster%5D&zACS=162.8&zS=2023-02-17&zFS=danxu&zU=2023-04-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **New Feature**: Added Book Loan Related Commands ([#12](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/12), 
  [#20](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/20), [#23](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/23), [#32](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/32), [#37](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/37))
  * What it does: allows the user to borrow, renew and return a book. 
  * Justification: this is a basic and core function for a library system.
  * Highlights: 
    * _borrow_, _renew_ and _return_ commands all extend from an abstract _loan_ command to reduce 
      duplicate code. 
    * A _LoanController_ is used to abstract modifications to loan records and minimize coupling 
      between loan commands and loan records. 
    * A parser is used to parse user raw input (string) to command.

* **New Feature**: Added Access Management Commands ([#58](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/58), 
  [#120](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/120), [#137](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/137)) 
  * What it does: allows users to create an account, change passwords, and log in to their accounts before executing 
user commands (such as _borrow_). 
  * Justification: a library system needs access management to record current user of the application.
  * Highlight:
    * Users' passwords are stored using their hash code instead of plain text for privacy and data security reasons.
    * Separate existing commands to user commands (extends _UserCommand_) and access commands (extends 
      _AccessCommand_), separate existing parser to _UserCommandParser_ and _AccessCommandParser_. These separations 
      are in accordance to the single responsibility principle.

* **Enhancement to Existing Features**: 
  * Fix parser bugs for different modules. ([#38](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/38))
  * Add printing due data of a loan to _history_ command. ([#139](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/139))

* **Documentation**:
  * **User Guide**: Add access management user documentation. [#61](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/61) 
  * **Developer Guide**: 
    * Add librarian commands developer documentation. [#50](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/50)
    * Add access management developer documentation. [#140](https://github.com/AY2223S2-CS2113-F10-4/tp/pull/140)

* **Community**:
  * **PR Review**: Reviewed 16 pull requests. (#4, #5, #7, #8, #14, #25, #31, #32, #33, #34, #40, #49, #51, #55, #134,
    #136)
  * **Issue Report**: Reported 15 issues. (#22, #29, #35, #36, #41, #42, #47, #60, #62, #119, #129, #132, #138, #148,
    #149) 
  * **Reported bugs and suggestions for other teams**: [T09-2](https://github.com/AY2223S2-CS2113T-T09-2/tp).