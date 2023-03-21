---
title: Design & implementation
weight: 1
---

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## PocketPal's name and logo

PocketPal was named as such because we aspired for it to become a lightweight application that can act as a companion to assist in managing the users' (pocket) money.

## Entry

## Storage

## UI

## Parser
The `Parser` class is a fundamental component instantiated as soon as PocketPal is initialised. Its __purpose__ is to convert the user's input into structured data which then produces clear instructions for the rest of the program.

Some of its core features include:
- Breaking down user input and extracting the relevant data for further processing.
- Performing input validation and error handling to ensure that input data is in the correct format and ready to be processed.
- Converting the input data into the correct format and returning it as a `Command` class to be further processed by the application.

How `Parser` works:
1. When a user enters a command, the `Frontend` uses `Parser` to resolve the user input via `parseUserInput()`.
2. Within `parseUserInput()`, the corresponding `parseXCommand()` ( `X` is a placeholder for the various command names[^1] e.g. `parseAddCommand()`, `parseDeleteCommand()`. )  is invoked to validate that the user input is in the correct format. Any exceptions will be thrown and their corresponding error messages will be shown to the user via the `ui` class.
2. If the user input is valid, an `XCommand` object containing the relevant data is created and returned. E.g. `parseAddCommand()` would create a `AddCommand` object containing the description, price and category.
3. From there, the `XCommand` is ready to be executed by the program. ( All `XCommand` classes inherit from `Command` and have corresponding `execute()` that carry out their specific instructions.)

[^1]: A list of currently supported commands in PocketPal can be found [here](../../UserGuide.html/features/)

Here's a class diagram that shows the core structure of the `Parser` class.




