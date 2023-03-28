# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

{Give detailed description of each feature}

### Adding a todo: `todo`

Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Budget Planner: `/budget`

All Budget Planner commands start with /budget.

****

#### `budget` - Edits the budget

Edits the current total budget to a new amount the user plans to spend on his/her SEP trip

Format: `/budget budget [AMOUNT]`

- The `AMOUNT` is the user's input budget for his/her SEP trip in SGD.
- Initial budget is set to 0 when user has not set any budget before.

Example of usage:
`/budget budget 2000`

Expected outcome:

- Changes the budget `AMOUNT` to 2000 SGD.
- Shows a budget changed successful message to let the user check the budget set corresponds to his input.

Description of outcome:

```
____________________________________________________________
Budget has been changed to: 2000
____________________________________________________________
```

****

#### `accommodation` - Edits the accommodation cost

Edits the current accommodation planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget accommodation [AMOUNT]`

- The `AMOUNT` is the user's input accommodation cost for his/her SEP trip in SGD.
- Initial accommodation cost is set to 0 when user has not set any accommodation cost before.

Example of usage:
`/budget accommodation 2000`

Expected outcome:

- Changes the accommodation cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Accommodation cost has been changed to: 2000
____________________________________________________________
```

****

#### `airplane` - Edits the Airplane Ticket cost

Edits the current Airplane Ticket planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget airplane [AMOUNT]`

- The `AMOUNT` is the user's input airplane ticket cost for his/her SEP trip in SGD.
- Initial airplane ticket cost is set to 0 when user has not set any airplane ticket cost before.

Example of usage:
`/budget airplane 2000`

Expected outcome:

- Changes the airplane cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Airplane Ticket cost has been changed to: 2000
____________________________________________________________
```

****

#### `Food` - Edits the Food cost

Edits the current Food planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget food [AMOUNT]`

- The `AMOUNT` is the user's input food cost for his/her SEP trip in SGD.
- Initial food cost is set to 0 when user has not set any food cost before.

Example of usage:
`/budget food 2000`

Expected outcome:

- Changes the food cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Food cost has been changed to: 2000
____________________________________________________________
```

****

#### `Entertainment` - Edits the Entertainment cost

Edits the current Entertainment planned cost to a new amount the user plans to spend on his/her SEP trip

Format: `/budget entertainment [AMOUNT]`

- The `AMOUNT` is the user's input entertainment cost for his/her SEP trip in SGD.
- Initial entertainment cost is set to 0 when user has not set any entertainment cost before.

Example of usage:
`/budget entertainment 2000`

Expected outcome:

- Changes the entertainment cost `AMOUNT` to 2000 SGD.
- Shows a cost changed successful message to let the user check the cost set corresponds to his/her input.

Description of outcome:

```
____________________________________________________________
Entertainment cost has been changed to: 2000
____________________________________________________________
```

****

#### `View` - Views the entire budget plan

Provides an overview of what the budget consists of.

Format: `/budget view`

Example of usage:
`/budget view`

Expected outcome:

- Shows the total budget amount planned for the SEP trip.
- Shows the Accommodation cost
- Shows the Airplane Ticket cost
- Shows the Food cost
- Shows the Entertainment Cost
- Shows the Surplus, which could be a Deficit if value is negative

Description of outcome:

```
____________________________________________________________
Total budget: 2000
Accommodation cost: 2000
Airplane Ticket cost: 2000
Food cost: 2000
Entertainment cost: 2000
Surplus/Deficit: -6000
____________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
