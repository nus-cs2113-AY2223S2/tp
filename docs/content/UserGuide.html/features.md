---
title: Features
weight: 2
---

The table below provides a summary of all the currently supported features in PocketPal.
More detailed explanations on the usage of the commands are provided as well.

If you face any problems, do visit the FAQ segment!

| Command |                      Function                       |
| ------- | :-------------------------------------------------: |
| /add    |                   Adds an expense                   |
| /delete |                 Deletes an expense                  |
| /edit   |                  Edits an expense                   |
| /view   | Displays details of an expense e.g. Price, Category |
| /help   |               Displays the help menu                |
| /bye    |               Terminates the program                |

## Adding an expense: `/add`

Adds an expense to your current expenditure.

Format: `/add DESCRIPTION -c CATEGORY -p PRICE`

-   The `DESCRIPTION` and `CATEGORY` can be in a natural language format.
-   The `PRICE` must be in numeric or decimal format.
-   The `-c` and `-p` flags can be used in any order.

Here is a [list](#categories) of categories currently supported in PocketPal.

Example of usage:

`/add Lunch at McDonalds -c Food -19.9`

`/add Apple Macbook Air -p 1300 -c Personal`
