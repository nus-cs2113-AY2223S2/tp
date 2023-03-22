---
title: Manual testing
weight: 6
---

## Testing with sample data (from file)

PocketPal stores data in a *storage.txt* file under the "*data/*" directory. Each row in the "*storage.txt*" file
represents a single expense Entry. Each column in each row should have 3 columns, representing the *description* of the
Entry, *amount* associated with the Entry and *category* of the Entry in that order, and are separated with the ","
delimiter. All of them are in the String format.

An example *storage.txt* file that will be readable by PocketPal is as such:

'''
Apple Juice,5.50,Food
Bus Card,50,Transportation
Paracetamol,10.39,Medical
'''

which will give us 3 Entries.

As of the time of writing, the available categories are:

- Clothing
- Entertainment
- Food
- Medical
- Others
- Personal
- Transportation
- Utilities
- Income

An empty input for category is not allowed. If necessary, use the "Others" category.

## Exceptions

Exceptions are thrown for a couple of cases where files are being read. If you wish to test the exceptions, they can be
replicated as follows:

1. Delimiter is invalid: If the delimiter is not the comma (","), it is not recognised as a delimiter and will not be
   processed correctly.
   Example row: Apple Juice|5.50|Food - In this case, the pipe ("|") is used as a delimiter, which is not allowed.
2. Amount is invalid: If the amount is not a numeric, it is not recognised as a valid amount and will not be processed
   correctly.
   Example row: Apple Juice,5A6B,Food - In this case, the amount is "5A6B", which is not a numeric and therefore not
   allowed.
3. Category is invalid: If the category is not a string from the list of allowed categories (see above), it is not
   recognised as a valid category and will not be processed correctly.
   Example row: Apple Juice,5.50,Drink - In this case, the category is "Drink", which is not a valid category and
   therefore not allowed.
4. Not enough columns: If a row has insufficient columns compared to what is needed, the Entry cannot be created.
   Example row: Apple Juice,5.50 - In this case, there are only two categories which is not allowed.

# Launching of PocketPal

First, place the downloaded *PocketPal.jar* into an empty folder. Launch Windows Powershell in the
directory of *PocketPal.jar* and run the following command to launch PocketPal.

{{< code lang="bash" >}}java -jar PocketPal.jar{{< /code >}}

# Feature Testing

The following section provides instructions and code snippets for the manual testing of all currently supported features
in PocketPal.

{{< alert style="info" >}}
**Do note that the test cases provided are not exhaustive and may not cover all possible outcomes.** {{< /alert >}}

## Add expense: /add

**Usage:** `/add <DESCRIPTION> <-c | -category CATEGORY> <-p | -price PRICE>`

##### Test Case 1 (All required flags are provided):

**Prerequisites:** None

{{< code lang="Java" >}} /add McDonalds -c Food -p 10.50 {{< /code >}}

Expected output:
{{<code lang="Text">}}________________________________________________
The following expenditure has been added:
Description: McDonalds
Price: $10.50
Category: Food
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

##### Test Case 2 (Missing price flag):

**Prerequisites:** None

{{< code lang="Java" >}} /add McDonalds -c Food {{< /code >}}

Expected output:
{{<code lang="Text">}}________________________________________________
Please specify the description, category and price!
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

## View expense: /view

**Usage:** `/view [COUNT] [-c | -category CATEGORY]`

##### Test case 1 (No expenses exist):

**Prerequisites:** None.

{{<code lang="Java">}}/view
{{</code>}}

Expected output:

{{<code lang="Text">}}________________________________________________
There are no entries available.
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

##### Test case 2 (Multiple expenses exist):

**Prerequisites:** At least **3** existing expenses.

{{<code lang="Java">}}/view 3
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
These are the latest 3 entries.
Total expenditure: $168.0
<1>: Lunch (Food) - $7.50 <<22 Mar 2023, 22:29:35>>
<2>: McDonalds (Food) - $10.50 <<22 Mar 2023, 22:30:11>>
<3>: Birthday Dinner (Food) - $150.00 <<22 Mar 2023, 22:30:24>>
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

## Delete expense: /delete

**Usage:** `/delete <EXPENSE_ID> [ADDITIONAL_EXPENSE_ID]`

{{< alert style="info">}} You may view the list of existing expenses along with their corresponding indexes with
`/view`.
{{</alert >}}

##### Test case 1:

**Prerequisites:** At least **3** expenses pre-added into the program, with the 3rd expense matching the one shown
in the example above.

{{<code lang="Java">}}/delete 3
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
The following expenditure has been deleted:
Description: Birthday Dinner
Price: $150.00
Category: Food
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

##### Test case 2:

**Prerequisites:** Fewer than **5** expenses pre-added into the program

{{<code lang="Java">}}/delete 20
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
Please enter a valid numerical index!
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

## Edit expense: /edit

**Usage:** `/edit <EXPENSE_ID> [FLAG...]`

##### Test case 1 (Editing all flags):

**Prerequisites:** At least **2** expenses pre-added into the program.

{{<code lang="Java">}}/edit 2 -p 300.50 -c others -d MacBook Air
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
The following expenditure has been updated:
Description: MacBook Air
Price: $300.50
Category: Others
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

##### Test case 2 (Editing price only):

**Prerequisites:** At least **2** expenses pre-added into the program, with the 2nd expense matching the one shown
in the example above.

{{<code lang="Java">}}/edit 2 -p 300.50
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
The following expenditure has been updated:
Description: MacBook Air
Price: $300.50
Category: Others
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

## Show help menu: /help

**Usage:** `/help`

##### Test case:

**Prerequisites:** None.

{{<code lang="Java">}}/help
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
PocketPal is a expense tracking app, optimised for use via a Command Line Interface.
Users can take advantage of the input flags for entering entries quickly.
Listed below are the various commands that are currently supported.

Add - Adds an expense to your current expenditure.
Usage: /add <DESCRIPTION> <-c CATEGORY> <-p PRICE>

Delete - Deletes a specified expense from your expenditure.
Usage: /delete <EXPENSE_ID> [ADDITIONAL_EXPENSE_ID...]

Edit - Edits a specified expense in your current expenditure.
Usage: /edit <EXPENSE_ID> [FLAG...]

View - Displays a list of your current expenditure.
Usage: /view [COUNT] [-c | -category CATEGORY]

Help - Displays the help menu.
Usage: /help

Exit - Terminates PocketPal.
Usage: /bye
________________________________________________
Enter a command or /help to see the list of commands available.
{{</code>}}

## Terminate program: /bye

**Usage:** `/bye`

##### Test case:

**Prerequisites:** None.

{{<code lang="Java">}}/delete 3
{{</code>}}

Expected output:
{{<code lang="Text">}}________________________________________________
Bye. See you again :)
________________________________________________
{{</code>}}

{{< alert style="info" >}} More test cases will be added as more features are introduced. {{< /alert >}}
