---
layout: page
---
# EthanYidong - Project Portfolio Page

## Overview

Meal Companion is a recipe book which allows you to search
by the ingredients you already have so that you can easily find recipes you can make.

### Summary of Contributions
[Contributed Code](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ethanyidong&breakdown=true)

#### Enhancements
- Overall command parsing structure
- Overall application architecture
- Ingredient and recipe database setup
  - Recipe cooking and preparation time
- Ingredients list command
- Ingredients search command
- Allergens command

#### UG
- Front Matter: extended value proposition
- Command syntax (done inside draft, copied over to GitHub)

#### DG
- Architecture description and diagram (along with Jing Ya)
- Describe how routing is implemented and why
  - Routing class diagram
  - Routing sequence diagram
- Describe `Extractor` system for argument parsing
- Describe how exceptions are handled

### Contributions to Team-Based Tasks
- Overall command parsing structure 
- Overall application architecture
- Created v1.0 Release
- Setup assertions for Gradle
- Error handling
  - Note that edits are not attributed under RepoSense as they are too sparse to justify author tags
  - All edits in question are in this [PR](https://github.com/AY2223S2-CS2113T-T09-3/tp/pull/159)

#### Reviewing/Mentoring Contributions
- Assisted Jia Chen in setting up his local repository workflow
- Wrote example code to help teammates understand how to utilize the command framework setup by me

#### Contributions Beyond the Project Team
- Found a bug which caused a crash in [Budget Buddy](https://github.com/AY2223S2-CS2113-W15-3/tp)
<div style="page-break-after: always;"></div>

# Contributions to the Developer Guide (Extracts)

The system of `Routable` classes is built to allow for a composable setup of commands and subcommands.

Developers are able to build a "routing tree" by nesting `CommandRouterNode`s together, allowing for command matching
to be done in a robust and extensible way. This architecture was chosen because our commands are designed to have common
prefixes, such as the "recipe all" and "recipe possible" commands.

Therefore, we need to standardize the way we parse
the command in such a way that we do not have to worry about match priority or ordering, as we would if we
were to implement this with simple if-else or switch statements.

<img src="../images/RouterUML.png" width="450"/>

- The "routing tree" is built around the concept of the `Routable` interface, with a `CommandRouterNode` at the root.
- Classes which implement `Routable` have a `resolve` function which will map a `CommandTokens` object to an
  `ExecutableCommandFactory`.
- Routing is done in a depth-first fashion via recursive calls to `resolve`, until an
  endpoint is reached at which point the corresponding `ExecutableCommandFactory` is returned.

<img src="../images/RouterSnippetUML.png" width="450"/>

This is an object diagram representing a portion of the routing tree for MealCompanion, supporting the "recipe all",
"recipe possible", and "ingredient list" commands.

The sequence diagram below illustrates the process for resolving the "recipe all" command.

<img src="../images/RouterSequenceUML.png" width="450"/>

#### Command Tokens and Recursive Matching

The `CommandTokens` class represents a rewindable queue of words in the user's input
which allows for performant matching of nested commands. As the `CommandTokens` instance gets passed to the
`CommandRouterNode`, it will check if the word at the head matches its name. If it does, the queue is advanced and the
tokens are passed recursively deeper into the routing tree, and if not the queue is rewound and passed back up the
routing tree.

#### Argument Extractors
<img src="../images/Extractors.png" width="450"/>

This is a class diagram representing the `Extractors` system for extracting values from passed arguments. Note that not
all implementations of `ArgumentExtractor` is included.
`Extractors` provide validated inputs to the `ExecutableCommand` that is built by the factory.

The `ExecutableCommandFactory` may provide a list of `Extractors` to be run via the `getExtractors()` method.
The `runExtractors()` function should be called when building the command.

If extraction is run successfully, the factory may pull the validated
value out of the `ArgumentExtractor` using the `getExtractedValue()` method, and pass it to the `ExecutableCommand`
constructor.

If extraction yields an exception, the exception will be passed up to the caller of `buildCommand()`.

### Exception Handling

We have 5 different custom exceptions thrown by our code:
- `CommandRunException` - thrown only by `ExecutableCommand`s a problem occurs while running a command
- `InvalidArgumentException` - thrown only by `ArgumentExtractor`s when an argument value is malformed
- `InvalidCommandException` - thrown only by `Extractor`s when the command is malformed
- `MealCompanionException` - thrown by code run outside the REPL (for example, loading save data)

`CommandRunException`s and `InvalidCommandException`s are handled in the `MealCompanionSession`. The error message
is printed for the user and the program will wait for the next command.

`InvalidArgumentException` is never thrown outside the context of a `Extractor` calling its child `ArgumentExtractor`.
The `Extractor` will always wrap the thrown `InvalidArgumentException` in an `InvalidCommandException`, which is
handled as above.

`MealCompanionException` should be handled by the caller of the function which throws it. We are in the process of
shifting this responsibility away from the `ExecutableCommand`s.
<div style="page-break-after: always;"></div>

# Contributions to the User Guide (Extracts)

## Who is this for?
This document is for busy students and working adults who want to begin cooking with Meal Companion. If you want to see how Meal Companion can change your food habits to support a sustainable and healthy lifestyle, read on!

## Why Meal Companion?

Cooking is a committment. It's hard to buy exactly the right amount of ingredients especially when you're cooking for one, and even if you can stomach eating the same recipe every day for a week, the ingredients run out at different times! Most people, when faced with this problem, will simply throw out whatever goes bad and go back to their daily trips to canteens, hawkers, or resturaunts. And, that's where Meal Companion comes in! Here's how we improve upon the experience of commonly used recipe sources.

### Versus Traditional Recipe Books

Let's be real here, very few of us still use traditional recipe books. They're clunky to navigate, easy to dirty, and expensive to buy. Comparatively, Meal Companion is fast to navigate and easy to use. More importantly, someone with no idea what to cook beforehand would struggle to find a suitable recipe in a long recipe book, but with Meal Companion, you can instantly figure out what dishes you can cook with what you have.

### Versus Online Recipe Websites

Online recipe websites have a leg up on traditional recipe books because they are also easy to navigate and search through. However, similar to their analog counterparts, it can be daunting to scroll through hundreds of recipes only to find that you're a few eggs or half a carrot short of being able to cook any of them. Meal Companion alleviates this pain by allowing you to only display recipes that are possible for you to cook right now.

### Versus Recipe Videos

With the rise of shortform content such as TikToks, YouTube Shorts, and Instagram Reels, the trendiest recipes are always just a swipe away. However, those recipes can be hard to follow. Even if a text version is provided in the description of these videos, they still fall into the same issues as other online recipes. Plus, searching for them is a nightmare as the platforms they are on were not purpose-built for cooking. Not only does Meal Companion help you easily find recipes that you'd actually be interested in, it can also help you with using up the rest of your ingredients.