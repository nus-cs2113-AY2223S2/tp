# Bui Phuong Nam - Project Portfolio Page

## Project: Expense Tracker ET
The Expense Tracker (ET) CLI software helps users to easily manage their expense.

## Contribution
### Code Contribution: 
Code Contribution breakdown link: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=bui%20&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~other~test-code~functional-code&since=2023-02-17&tabOpen=true&zFR=false&tabType=zoom&zA=arsdorintbp2003&zR=AY2223S2-CS2113-T13-2%2Ftp%5Bmaster%5D&zACS=93.11111111111111&zS=2023-02-17&zFS=bui%20&zU=2023-04-10&zMG=false&zFTF=commit&zFGS=groupByRepos)

#### Command Feature
- **Feature:** `list` command
  - Function: Allows user to view their expense list they have added. 
  - This function will list all the expenses of the user until the command is inputted by the user, by printing all
expenses in the expenseList of the account. If the account is first logged in/ there is no expenses been added yet, 
`list` will print a message to note that there is no expenses in the list.

- **Feature** `exit` command (inside the account)
  - Function: Allow user to immediately exit the program, and save their account's expense list.
  - This method shares a similar saving methods as `logout`, but can terminate the program immediately.

- **Other Features** (some small add-on to fulfill the account's requirement)
  - `add` and `delete` command: After an expense is added or deleted via those commands, respectively, the json file 
will auto-save that expense to avoid the program to be closed unexpectedly.
  
#### Account Feature
- **Feature:** `signup` command
  - Function: Allow user to create their own account with unique username and password
  - This function will check the username and password of the user to pass some requirements, such as the password must
  have at least 8 characters, the username must contain only alphabet characters and numbers, and the username must not
  be taken previously by other account.
    - If the username and password passed all those requirements, they will be saved to the programme's user list for 
    tracking, and a unique json file to store their expenses are created. Then, a message is displayed to denote that
    the account is created successfully, and return the user to the initial command box where the user need to choose to
    `login`, `signup` or `exit`.
    - Else, a message will be printed to denote that which requirement the account haven't pass, and also return the 
    user to the initial command box where the user need to choose to `login`, `signup` or `exit`.
  - Case-sensitive doesn't matter in this method.
  
- **Feature:** `login` command
  - Function: Allow user to log in to their account. 
  - This method will scan the user inputted username and password, and check if they are matched or not.
    - If they are matched, the account will load all the expense data of the user from his / her last login sessions, 
    which store in a unique json file to that account. Then it will print a welcome message and a message to denote that
    the account is logged in successfully, and print a helper message to denote the user to type `help` to view the
    command instruction.
    - Else, a message will be printed to denote that the login process is not successfully, and comeback to the initial 
    command box where the user need to choose to `login`, `signup` or `exit`.
  - Case-sensitive doesn't matter in this method.

- **Feature:** `logout` command
  - Function: Allow user to log out of their account and save the expense list.
  - This method will store all the expense list inside the user account currently to the json file, and print a 
  message to denote that the user is logged out successfully. Then, it will lead the user comeback to the initial
  command box where the user need to choose to `login`, `signup`, or `exit`.
  
- **Feature:** `exit` command (outside the account)
  - Function: Allow user to exit of the program immediately.

- **Classes: (Basic):** `CommandRes`, `Command`, `Account`
  - `CommandRes` and `Command` are based class for all other command features.
  - `Account` is the based class for all account features.
  - Other classes that has contributed (not minimal): `Expense`, `ExpenseList`

- **Test Files:** 
  - Add test cases for all account features.

### UG Contribution:
- Documentation of feature `list`, differentiate between the documentation of feature `exit` inside and outside the
account.
- Documentation of all account features.

### DG Contribution:
- Diagrams and description for all account methods, and `list`.

### Community help:
- Detect some errors and discuss to solve the error (eg: the link error in UG).
- Help merge some PRs.
