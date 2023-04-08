# Fan Zixian - Project Portfolio Page

## Project: Expense Tracker ET
The Expense Tracker (ET) CLI software helps users to easily manage their expense.

## Contributions
### Code Contribution:
Code Contribution breakdown link:
[RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=FanZixian&zR=AY2223S2-CS2113-T13-2%2Ftp%5Bmaster%5D&zACS=149.5023976392475&zS=2023-02-17&zFS=&zU=2023-04-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

- **Feature:** `help` command
    - Function: Provide user-friendly helper page for user to check available command
- **Feature:** `sort` command
    - Function: Allows user to sort their expense list according to Category (C) or Date (D)
    - This function will traverse the expense list already stored, and create a new stable sorted 
expense list according to user's requirement  

- **Feature:** `category` command
    - Function: Display all the expenses created before by user, and display expenses with category specified by user
    - This method returns default uncategorized expenses if the user does not enter any category. the method uses Hash Set to get all categories.
    - Case sensitivity doesn't matter in this method.

- **Feature:** `find` command
    - Function: Find expenses with information that user specified
    - This method simply traverse all expenses and review their attributes to decide whether to display or not.
    - Case sensitivity doesn't matter in this method.

- **Classes (Basic):** `Time`, `Expense`, `WelcomeMessage`
    - They are basic classes to support functionality.
    - Credit: `WelcomeMessage` comes from the idea provided by [Display ASCII art in Java](https://www.baeldung.com/ascii-art-in-java), 
which uses awt class to set up logo

- **Test Files**
    - Add test cases for `sort`, `list`, `find`, `category` features.
    

### UG Contribution:
- Introduction part
- Documentation of feature `help`, `sort`, `category`, `find`

### DG Contribution:
- Diagrams and description for methods `sort` and `category`

### Community help:
- Help merge some PRs
