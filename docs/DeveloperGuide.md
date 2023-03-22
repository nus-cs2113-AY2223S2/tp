# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Setup/Prerequisites
1. Ensure you have Java 11 installed.
2. Ensure your local repository is synced with the main repository at [AY2223S2-CS2113-F13-1/tp](https://github.com/AY2223S2-CS2113-F13-1/tp)

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

Product is geared towards users who are familiar with CLI (e.g. Computing professionals, university students).
The user is ideally someone who is conscious about their health and would like to learn/improve their cooking

### Value proposition

The user will be able to keep a database of recipes for home cooking, and be able to view both the recipes as well as 
attributes such as calorie count and required ingredients.<br>
It will also allow them to follow recipes in a step-by-step fashion with additional assistance functions such as timers.<br>
The user will be able to keep close tabs on their nutrition based on the recipes that they decide to cook and add to their meal plans.

## User Stories

| Version |     As a ...    |                                      I want to be able to ...                                     |                                  So that I can...                                 |
|:-------:|:---------------:|:-------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|
|   v1.0  | potential user  | read the User Guide easily                                                                        | I get to know the feature of the app and get started quickly                      |
|   v1.0  | potential user  | add ingredients list to a particular recipes                                                      | refer to it when I go shopping for ingredients                                    |
|   v1.0  | new user        | initially see the estimated cooking time for recipes                                              | choose the faster ones when in a rush                                             |
|   v1.0  | new user        | see basic instructions for the first time I use the app                                           | avoid having to keep referring back to the user guide.                            |
|   v1.0  | new user        | ask the app to provide a format guide for me when I type the wrong format for an instruction      | get started quickly.                                                              |
|   v1.0  | new user        | see a full list of recipes currently available                                                    | get an overview of what is already available                                      |
|   v1.0  | new user        | add recipes                                                                                       | add the ones i like                                                               |
|   v1.0  | new user        | delete recipes                                                                                    | remove the ones i don't like                                                      |
|   v1.0  | new user        | edit the recipe                                                                                   | correct spelling mistakes when typing the recipe                                  |
|   v1.0  | new user        | go through the recipe line by line                                                                | follow the recipe in real time while i cook                                       |
|   v1.0  | long-term user  | mark the steps I have done for the recipe                                                         | be aware of my next steps as I am cooking.                                        |
|   v2.0  | proficient user | trigger certain recipes to be displayed once the app launches                                     | get quick access to my regular cooking recipes                                    |
|   v2.0  | proficient user | customize the shortcut commands                                                                   | customize the keystrokes to my own preferences                                    |
|   v2.0  | potential user  | see the app with sample data and can easily manage to delete it after the exploration of the app. |  Iunderstand the function of the app easily.                                      |
|   v2.0  | potential user  | craft meal plans for different days                                                               | plan my meals ahead for the week                                                  |
|   v2.0  | new user        | register my dietary requirements / restrictions                                                   | avoid eating food I cannot eat.                                                   |
|   v2.0  | new user        | start an automatic timer when required                                                            | avoid forgetting to set a timer when the recipe calls for it                      |
|   v2.0  | new user        | see that the app can provide fuzzy search                                                         | access recipes even if I type the name wrongly                                    |
|   v2.0  | new user        | learn more about the shortcut commands                                                            | easily navigate through the app interface quicker                                 |
|   v2.0  | long-time user  | press any key if prompted to continue to the next step                                            | use my elbow instead of my oily hands on the keyboard                             |
|   v2.0  | long-time user  | get warnings if my fat/sugar intake based on recent dishes is too high                            | better regulate my diet                                                           |
|   v2.0  | long-term user  | sort through stored recipes based on the dishes' nutritional value.                               | better regulate my diet.                                                          |
|   v2.0  | long-term user  | sort by cuisines.                                                                                 | select a particular cuisine                                                       |
|   v2.0  | long-term user  | add my own notes when I’m done cooking                                                            | comment about and adjust the recipe to my liking (e.g. less sweet, less salty)    |
|   v2.0  | long-term user  | hide dishes that I am tired of or tried and do not like                                           | spend less time filtering through dishes                                          |
|   v2.0  | long-term user  | “favorite” dishes that I enjoy                                                                    | quickly select them                                                               |
|   v2.0  | expert user     | get the app to randomly suggest one of my favorite snacks                                         | have help in making decisions on what snacks to eat when I hesitate to choose one |
|   v2.0  | expert user     | rate and comment on the recipe                                                                    | choose my favorite recipe by using the ratings                                    |
|   v3.0  | new user        | the option to mark recipes as drinks/cocktail                                                     | also use the app to find and refer to drinks recipes quickly                      |
|   v3.0  | long-term user  | get encouragement to avoid unhealthy food                                                         | better control my diet                                                            |
|   v3.0  | long-term user  | sort by portion size                                                                              | prep dishes catered to groups when I have friends over                            |
|  v420.0 | long-term user  | have the app to warn me if my laptop battery life is not enough to finish the recipe              | avoid scrambling for a charger while cooking                                      |

## Non-Functional Requirements

* Users should be able to run on any common operating system (Windows, Mac, Linux)
* Users should not need to manipulate any files in the directory manually
* Users should be able to run all functions of the program on the CLI only (i.e. keyboard inputs only)
## Glossary

* *Recipe* - A set of instructions for preparing a food item. In our implementation it should contain the dish's name,
ingredients required and steps to make the dish
* *Cuisine* - A category of food originating from a given country or religion

## Instructions for manual testing

Sample inputs should be placed into the input.txt file, and an expected output in the EXPECTED.txt file.
Run `./runtest.bat` to automatically get a result of whether the actual output follows the expected.
The resultant output can be found under ACTUAL.txt
<br> <br>
Manual test cases should include scenarios of the following
1. correct input parameters
2. missing input parameters
3. wrong format input parameters (i.e. substituting an expected number for a word)


