# Clement Hung - Project Portfolio Page

## Overview
ChChing is a financial management tool
which allows users to keep track
of their incomes and expenses conveniently.
There is also an option to change currency should users require currency conversion.

### Summary of Contributions
Given below are my contributions to the project.

#### Code Contributed:
[RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=hyperbola-bear&tabRepo=AY2223S2-CS2113-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


#### Project management:
* Creating issues for team members to work on

#### Enhancements Implemented:
* Created a `DeleteIncomeCommand` to allow users to delete an income from the income list.
* Created a `DeleteExpenseCommand` to allow users to delete an expense from the expense list.
* Created a currency conversion feature which allows the user to set and unset currencies to display the conversion from SGD to currencies of interest. When the user sets a currency, the currency will be displayed in the income, expense list and balance. When the user unsets a currency, the currency will no longer be displayed in the income, expense list and balance.
* Created a Selector to allow users to select the currency of interest.
* Created a Converter class to allow users to convert the currency of interest.
* Created a LiveCurrencyApi to get live currency rates from the internet and put it into the convertor hashmap.
* Contributed to tests for `SetCurrencyCommand`, `UnsetCurrencyCommand`, `DeleteIncomeCommand`, `DeleteExpenseCommand`.

#### Contributions to the UG:
Added documentation for the feature `Set Currency`, `Unset Currency`.

#### Contributions to the DG:
* Added `SetCurrencyCommand` sequence diagram
