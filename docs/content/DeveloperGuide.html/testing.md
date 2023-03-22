---
title: Manual testing
weight: 6
---

## Testing with sample data (from file)

PocketPal stores data in a "*storage.txt*" file under the "*data/*" directory. Each row in the "*storage.txt*" file represents a single expense Entry. Each column in each row should have 3 columns, representing the *description* of the Entry, *amount* associated with the Entry and *category* of the Entry in that order, and are separated with the "," delimiter. All of them are in the String format.

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

Exceptions are thrown for a couple of cases where files are being read. If you wish to test the exceptions, they can be replicated as follows:

1. Delimiter is invalid: If the delimiter is not the comma (","), it is not recognised as a delimiter and will not be processed correctly.
    Example row: Apple Juice|5.50|Food - In this case, the pipe ("|") is used as a delimiter, which is not allowed.
2. Amount is invalid: If the amount is not a numeric, it is not recognised as a valid amount and will not be processed correctly.
    Example row: Apple Juice,5A6B,Food - In this case, the amount is "5A6B", which is not a numeric and therefore not allowed.
3. Category is invalid: If the category is not a string from the list of allowed categories (see above), it is not recognised as a valid category and will not be processed correctly.
    Example row: Apple Juice,5.50,Drink - In this case, the category is "Drink", which is not a valid category and therefore not allowed.
4. Not enough columns: If a row has insufficient columns compared to what is needed, the Entry cannot be created.
    Example row: Apple Juice,5.50 - In this case, there are only two categories which is not allowed.

# Feature Testing

The following section provides instructions and code snippets for the manual testing of all currently supported features in PocketPal.

## Add expense: /add
**Usage:** `/add <DESCRIPTION> <-c | -category CATEGORY> <-p | -price PRICE>`

Test Case: 

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

## View expense: /view
**Usage:** `/view [COUNT] [-c | -category CATEGORY]`

Test case (No expenses exist):

**Prerequisites:** None.

{{<code lang="Java">}}
{{</code>}}


## Delete expense: /delete
**Prerequisites:** Multiple expenses pre-added into the program. View list of expenses with `/view` 
## Edit expense: /edit

## Help: /help
## Bye: /bye