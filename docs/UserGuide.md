# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features

{Give detailed description of each feature}

### Adding a card: `card add -q {question} -a {answer}`

Adds a new Card with its question and answer.

Example of usage:

`card add -q "how do i use this command? -a "by referring to this user guide"`

### Listing all cards : `card list`

List all existing cards

Sample output :

```

Here is a list of your cards :
1.Qn: is akhil a dirty commie
Ans: yes
UUID:  f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454
2.Qn: why do other groups keep attackin ian
Ans: he is not a dirty commie
UUID:  00000000-0000-0000-0000-000000000001
```

### Deleting a card : `card delete -i {cardUUID}`

Deletes a Card based on the cardUUID

Example of usage:

`card delete -i f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454`

### Tagging a card : `card tag -c {cardUUID} -t {tagName}`

Example of usage :
`card tag -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 -t physics`

Sample output :

```agsl
Tag does not exist.. creating a new tag: physics
Successfully added tag 83f26992-09d7-496b-b7a8-3ad05e43c8b7 to card f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454
```

### Putting a card into a deck : `card deck -c {cardUUID} -d {deckName}`

Example of usage :
`card deck -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 -d deckTest`

Sample output :

```
Deck does not exist.. creating a new one
Successfully added card f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 to deck fd2df33d-4bbe-4be7-83df-5ddaecd3f1ca
```

### Viewing a card : `card view -c {cardUUID}

View the content of a Card based on its cardUUID

Example of usage :
`card view -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454`

Sample output :

```agsl
Qn: is akhil a dirty commie
Ans: yes
UUID:  f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454
Here are the tags : 
1.Tag name : physics, tag uuid : 83f26992-09d7-496b-b7a8-3ad05e43c8b7
```

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: You do so by following these steps:

1. Export the deck as a Json file using the command "export"
2. Navigate to the directory of your Inka installation.
3. The file will be named savedata.json. Copy this file.
4. Paste this file in the Inka's directory in the new computer

Your file will be loaded the next time you run Inka!

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
