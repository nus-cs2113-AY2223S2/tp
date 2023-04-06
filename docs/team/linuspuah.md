[LinusPortfolio.md](linuspuah.md) # portfolio

# Linus Puah Jia He - Project Portfolio Page

## Overview

### Summary of Contributions

---

##### Code contributed
1242 LOC contributed ([tP Code Dashboard](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=linuspuah&tabRepo=AY2223S2-CS2113-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false))
   - 71 lines documentation
   - 483 function-code
   - 534 test-code

---

##### Enhancements implemented
1. Added `ioHandler` package
   - Reading in from CLI in `Parser` class ([#8](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/8/commits))
   - Printing out to CLI in `Ui` class ([#8](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/8/commits))
2. Enhance `AddCommand` function
   - Create feature to deal with adding of repeated items ([#137](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/137))
3. Error catching and Bug Handling
   - Added various Exceptions in `exception` package ([#37](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/37/commits/ed24adb50654c55001ebe477d860c36cb6a6b9d5))
   - Added try-catch statements to deal with functional errors ([#79](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/79/commits))
   - Bug Handling ([#36](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/36/files), )
4. Refactor packing list to use `ArrayList<>` instead of `String[]` ([#23](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/23/files#diff-215cdbb994affff07183e82ab3ac790e169e38469c4ec7d920f6ede6d2957f45))
5. Add J-unit Test for the majority of code base
   - Command Class J-unit tests ([#140](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/140), [#151](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/151))
6. Create feature 
   - `listunpacked` ([#151](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/151))
   - `deletelist` ([#44](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/44/commits))
---

##### Contributions to the [UG](../UserGuide.md)
1. Update UG to include max integer supported by BagPacker ([#154](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/154))
---
##### Contribution to the [DG](../DeveloperGuide.md)
1. Update User Stories ([#44](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/44/commits))
2. Add explanation of overall command mechanism for the whole application ([#44](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/44/commits)) 
3. Add explanation and [sequence diagram](#contributions-to-the-developer-guide--extracts--) for feature of adding repeated items in `AddCommand` ([#138](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/138))
---
##### Contributions to team-based tasks
Plan and create the framework for BagPacker application
- `exception`, `iohandler`, `packingfunc` and the relevant j-unit test packages ([#7](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/7/files), [#8](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/8/commits))

Issues tracking IC
- Labelled, assigned and set milestones for most of the issues 
- Regularly update the [issues](https://github.com/AY2223S2-CS2113-T14-2/tp/issues/created_by/linuspuah) tracker on GitHub 
- Managed the issues from PE-Dry run to resolve any repeated issues

Managed internal deadlines for group tasking

---
##### Review/mentoring contributions
- Maintain code standard before merging ([#139](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/139))
- Suggest improvements to code ([#22](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/22))
---
##### Contributions beyond the project team

---

##### Contributions to the Developer Guide (Extracts):

![AddExistingItemDiagram.png](..%2FumlDiagrams%2FAddExistingItemDiagram.png)
---

##### Contributions to the User Guide (Extracts):

