# Artemis Ngoh - Project Portfolio Page

## Overview
MagusStock is a Java command-line interface (CLI) application designed for inventory management.
This application that I have contributed to developing with my team aims to help store operators,
IT administrators and logistics managers to manage their inventory more efficiently and effectively.
While CLI applications are not as user-friendly as GUI applications, they are much faster to use
and more importantly, portable and can be used on any platform that supports Java.

### Summary of Contributions
Code contributions: [reposense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ArtemiszenN&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

Enhancements implemented:
* Refactored the parser class to a `MainParser` that delegates parsing to the respective `Parser`, which is abstract and has a Parser for most commands.
* Created the `SearchParser` and `SearchCommand` feature, as well as related exceptions.
* Created the `Trie` and `TrieNode` classes to complement the search feature and ensure functionality with a large dataset expected with medium-sized enterprises.
* Created the `FilterParser` and `FilterCommand` feature, as well as related exceptions.
* Created the `HistoryParser` and `HistoryCommand` feature, as well as related exceptions.
* Hardened the loading and saving of the data file for session and alert persistent functionality, along with a small `Sanitizer`.
* Integrated `search` and `history` to work with `add`, `edit`, `remove`, `restock` and `sell`, `save` and `load` functions. This is necessary as they use separate data.
* Javadocs implementation for relevant classes.
* Unit mock tests and integration tests for `search`, `filter`, `history` and `trie` (only unit tests for `trie`).

Contributions to the UG:
* Sections contributed: `Search`, `Filter`, `History`.

Contributions to the DG:
* Sections contributed: `Search`, `Filter`, `History`.
* UML Diagrams added are shown at the bottom of the page.

Contributions to team-based tasks:
* Wrote base code with no functionality (other than greet and exit) for the team to start.
* Validated the speed of our program for medium-sized enterprises with a benchmark on a 4,000 item dataset.
* Consistent merges to just fix checkstyle errors before we figured out the checkstyle plugin.
* First to do class and sequence diagrams so the team has an example to standardize on and reference.
* Fixed the release for PED at 5:36 AM on the day (it wasn't compiling).
* *Neutered* the text-ui-tests.


Review/mentoring contributions:
* https://github.com/AY2223S2-CS2113-W12-3/tp/pull/70
* https://github.com/AY2223S2-CS2113-W12-3/tp/pull/83
* https://github.com/AY2223S2-CS2113-W12-3/tp/issues/103
* https://github.com/AY2223S2-CS2113-W12-3/tp/pull/90
* https://github.com/AY2223S2-CS2113-W12-3/tp/pull/69
* https://github.com/AY2223S2-CS2113-W12-3/tp/pull/32
* Helped teammates brainstorm/look at/find solutions to bugs in many instances

Contributions beyond the project team:
* 12 bugs found during PED

![FilterSequence.png](..%2FUML%2FFilter%2FFilterSequence.png)
![FilterPrice.png](..%2FUML%2FFilter%2FFilterPrice.png)
![FilterTagCategory.png](..%2FUML%2FFilter%2FFilterTagCategory.png)

![FilterStep1.png](..%2FUML%2FFilter%2FFilterStep1.png)
![FilterStep3.png](..%2FUML%2FFilter%2FFilterStep3.png)
![FilterStep5.png](..%2FUML%2FFilter%2FFilterStep5.png)

![SearchSequence.png](..%2FUML%2FSearch%2FSearchSequence.png)
![SearchCommand.png](..%2FUML%2FSearch%2FSearchCommand.png)

![SearchStep1.png](..%2FUML%2FSearch%2FSearchStep1.png)
![SearchStep3.png](..%2FUML%2FSearch%2FSearchStep3.png)
![SearchStep5.png](..%2FUML%2FSearch%2FSearchStep5.png)
![SearchStep5UPC.png](..%2FUML%2FSearch%2FSearchStep5UPC.png)

![HistoryParser.png](..%2FUML%2FHistory%2FHistoryParser.png)

![HistoryStep1.png](..%2FUML%2FHistory%2FHistoryStep1.png)
![HistoryStep2.png](..%2FUML%2FHistory%2FHistoryStep2.png)
![HistoryStep3.png](..%2FUML%2FHistory%2FHistoryStep3.png)