[LinusPortfolio.md](linuspuah.md) # portfolio


# <span style="color:#00C34C">Linus Puah Jia He - Project Portfolio Page</span>
## <span style="color:#00A36C">Overview</span>
BagPacker is a desktop Command Line Interface (CLI) application used to manage a user's packing list.
It stores a packing list, edit the quantities packed and the total quantity packed of an item, and searches the packing list for items.
It is written in Java, and has about 4k LoC.

---
## <span style="color:#00A36C">Summary of Contributions</span>


### <span style="color:darkGreen">Code Contributed</span>
2266 LOC contributed ([tP Code Dashboard](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=linuspuah&tabRepo=AY2223S2-CS2113-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false))
   - 628 lines documentation
   - 868 function-code
   - 770 test-code

---
### <span style="color:darkGreen">Enhancements implemented</span>
1. Added `ioHandler` package
   - Reading in from CLI in `Parser` class ([#8](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/8/commits))
   - Printing out to CLI in `Ui` class ([#8](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/8/commits))
2. Enhance `AddCommand` function
   - Create feature to deal with adding of repeated items ([#137](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/137))
3. Error catching and Bug Handling
   - Added various Exceptions in `exception` package ([#37](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/37/commits/ed24adb50654c55001ebe477d860c36cb6a6b9d5), [#165](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/165/commits))
   - Added try-catch statements to deal with functional errors ([#79](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/79/commits))
   - Bug Handling ([#36](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/36/files),[[#167](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/167/files))
4. Refactor packing list to use `ArrayList<>` instead of `String[]` ([#23](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/23/files#diff-215cdbb994affff07183e82ab3ac790e169e38469c4ec7d920f6ede6d2957f45))
5. Add J-unit Test for the majority of code base
   - Command Class J-unit tests ([#140](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/140), [#151](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/151))
6. Create feature 
   - `listunpacked` ([#151](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/151))
   - `deletelist` ([#44](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/44/commits))
7. Add JavaDocs ([#163](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/163/commits))

---
### <span style="color:darkGreen">Contributions to the [UG](../UserGuide.md)</span>
1. Update UG to include max integer supported by BagPacker ([#154](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/154))
---
### <span style="color:darkGreen">Contribution to the [DG](../DeveloperGuide.md)</span>
1. Update User Stories ([#44](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/44/commits))
2. Add explanation of overall command mechanism for the whole application ([#44](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/44/commits)) 
3. Add explanation for Exceptions ([#179](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/179/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b))
4. Add explanation for Parser Class ([#184](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/184/files))
5. Add explanation and [sequence diagram](#span-stylecolordarkgreen-contributions-to-the-developer-guide--extracts---span) for feature of
- adding repeated items in `AddCommand` ([#138](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/138),[#158](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/158/commits/6d154b8464ba4d57e8d19a958e8be0b554e73cd6#diff-60cb84c47c48c2d69d1f587d9c9ed3af7d99acf2c3b11cd94c8d86eb532e32e2))
- `runBagPacker()` overall Sequence Diagram ([#176](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/176/commits/0ae5782514e02b5c755b689550d1c595449d1128))
- `PackCommand` and `UnpackCommand` sequence Diagram
---
### <span style="color:darkGreen">Contributions to team-based tasks</span>

Plan and create the framework for BagPacker application
- `exception`, `iohandler`, `packingfunc` and the relevant j-unit test packages ([#7](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/7/files), [#8](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/8/commits))

Issues tracking IC
- Labelled, assigned and set milestones for most of the issues 
- Regularly update the [issues](https://github.com/AY2223S2-CS2113-T14-2/tp/issues/created_by/linuspuah) tracker on GitHub 
- Managed the issues from PE-Dry run to resolve any repeated issues

Managed internal deadlines for group tasking

---
### <span style="color:darkGreen">Review/mentoring contributions</span>
- Maintain code standard before merging ([#139](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/139))
- Suggest improvements to code ([#22](https://github.com/AY2223S2-CS2113-T14-2/tp/pull/22))
---
### <span style="color:darkGreen">Contributions to the Developer Guide (Extracts):</span>
![AddExistingItemDiagram.png](..%2Fdiagrams%2FAddExistingItemDiagram.png)![BagPackerClassDiagram.png](..%2Fdiagrams%2FBagPackerClassDiagram.png)![BagPackerSequenceDiagram.png](..%2Fdiagrams%2FBagPackerSequenceDiagram.png)
![ExceptionClassDiagram.png](..%2Fdiagrams%2FExceptionClassDiagram.png)![ExecutePackCommandSequenceDiagram.png](..%2Fdiagrams%2FExecutePackCommandSequenceDiagram.png)![ExecuteUnpackCommandSequenceDiagram.png](..%2Fdiagrams%2FExecuteUnpackCommandSequenceDiagram.png)
---


