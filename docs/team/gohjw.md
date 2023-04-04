# Goh Jin Wei Aaron - Project Portfolio Page

## Project: Expense Tracker ET
ET is an application that allows users to track and record their expenses easily. Users are able to make use of ET
by passing commands through a CLI.

Below are my contributions to the project:

- **Feature:** `add` command
  - Function: Allows the user to add expenses to the expense list by specifying the amount and date, as well as add 
  additional optional information such as expense category and currency.
  - This function is a basic function required for ET to work.
  - Highlights: The feature uses an API to obtain the exchange rate of the currency with respect to the date of 
  the expenditure.
  - Credits:
    - Monetary Authority of Singapore exchange rate API
    - `three-ten extra` library for obtaining additional date information
    - Osaango Academy Youtube for code to extract information from the API
- **Feature:** `total` command
  - Function: Allows the user to tally their expenses/
  - This function allows users to easily keep track of their total expenses.
  - Highlights: The total command calculates each expense with respect to the input currency provided
  and displays the total expenditure in SGD.
- Code Contributed: [RepoSenseLink](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=T13-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=GohJW&tabRepo=AY2223S2-CS2113-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- Project management:
  - Managed releases `v1.0` and `v2.0` on github
- **Enhancements**
  - Added offline support in the event exchange rate data is not obtainable (Pull request #89)
  - Added warnings to `add` command to warn users of invalid input types and currencies. (Pull request #148)
- Documentation
  - UserGuide:
    - Added documentation for `add` and `total`
  - DeveloperGuide:
    - Added implementation for `add` and `total`
- Community:
  - PRs reviewed including #8 #43 #71 #147 #150
