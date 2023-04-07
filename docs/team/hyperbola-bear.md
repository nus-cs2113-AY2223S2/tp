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

- Creating issues for team members to work on

#### Enhancements Implemented:

- Created a `DeleteIncomeCommand` to allow users to delete an income from the income list.
- Created a `DeleteExpenseCommand` to allow users to delete an expense from the expense list.
- Created a `BalanceCommand` to allow users to view the balance of the user derived from their expenses and income.
- Refactored and upgraded `FindCommand` to allow users to find income and expenses based on their type. For incomes, user can search based on date and dexcription. For expenses, user can search based on date, description and category. Each of the search parameters are optional, however, at least one search parameter must be provided for `FindCommand` to work.
- Created a currency conversion feature which allows the user to set and unset currencies to display the conversion from SGD to currencies of interest. When the user sets a currency, the currency will be displayed in the income, expense list and balance. When the user unsets a currency, the currency will no longer be displayed in the income, expense list and balance.
- Created a Selector to allow users to select the currency of interest.
- Created a Converter class to allow users to convert the currency of interest.
- Created a LiveCurrencyApi to get live currency rates from the internet and put it into the convertor hashmap.
- Contributed to tests for `SetCurrencyCommand`, `UnsetCurrencyCommand`, `DeleteIncomeCommand`, `DeleteExpenseCommand`, `ShowTargetCommand`, `IncomeList`, `ExpenseList`,

#### Contributions to the UG:

Added documentation for the feature `Set Currency`, `Unset Currency` and `FindCommand`.

#### Contributions to the DG:

- Added `SetCurrencyCommand` sequence diagram
- Added implementation details for `SetCurrencyCommand`
- Added `UnsetCurrencyCommand` sequence diagram
- Added implementation details for `FindCommand`
- Added `FindCommand` sequence diagram

#### Contributions to team-based tasks:

- Ensuring Github's Java CI checks are passing

#### Review/monitoring contributions

- PRs reviewed: [CS2113-T13-1](https://github.com/nus-cs2113-AY2223S2/tp/pulls?q=is%3Aopen+is%3Apr+CS2113-T13-1+), [CS2113-W12-4](https://github.com/nus-cs2113-AY2223S2/tp/pulls?q=is%3Aopen+is%3Apr+CS2113-W12-4+)
- Bugs reported and suggestions: [CS2113-F10-1](https://github.com/hyperbola-bear/ped/issues)
