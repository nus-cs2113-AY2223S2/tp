

## Reynold Lam - Project Portfolio Page

### Overview

Hi I'm Reynold Lam, an Aspiring Computer Engineer from National University Of Singapore, and these are my contributions.
I have developed a product called Big PC Partpicker(BigPP) alongside my teammates. Big PC Part picker is a CLI application that allows
users to create and save their own custom PC build and a summary of my contributions to the team project Big PC Partpicker are as shown below

### Summary of Contributions
[Code Contributed](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=Reynold-SL&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=functional-code~docs~test-code~other&tabOpen=true&tabType=zoom&zA=Reynold-SL&zR=AY2223S2-CS2113-T12-2%2Ftp%5Bmaster%5D&zACS=81.58333333333333&zS=2023-02-17&zFS=Reynold-SL&zU=2023-04-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

### Enhancements implemented:
For the enhancements implemented, I focused more on the commands for the PCViewer Menu.

**Commands**
- `add PC_NAME`
  - This command allows the user to add a PC of a given name to the PC List
  - To accomplish this, a `ViewerAddCommand` class that extends from an abstract `Command` class is created
  - To ensure that the inputs are valid, exceptions are added to account for missing inputs
- `delete PC_INDEX`
  - This command allows the user to delete a PC of a given index from the PC List
  - To accomplish this, a `ViewerDeleteCommand` class that extends from an abstract `Command` class is created
  - To ensure that the inputs are valid, exceptions are added to take care of an invalid or missing index
- `help`
  - This command allows the user to access a help menu which informs them of the valid commands in the PCViewer Menu
- `filter FILTER-FLAGS` 
  - This command allows the user to filter the PC List by name, price and whether the built is complete or incomplete and also clear the filter
  - Initially, to accomplish this I created 4 different classes,`ViewerFilterName`, `ViewerFilterPrice`, `ViewerFilterisBuilt` and `ViewerFilterClear` to 
  filter based on each flag and execute the filter accordingly. However, this method only allowed the user to filter one flag at a time which meant
  that the user had to clear the existing filter before applying a new filter. In addition, the user was not able to edit any of the PC while 
  a filter was on. This method was not practical as the user should be able to filter using multiple flags and also edit the PC while the filter is 
  still on
  - To improve on this method, I created a single `ViewerFilterCommand` class that extends from an abstract `Command` class that is able to take 
  in a string of inputs. In addition, the way to print the filtered list had to be taken into account and thus edits to the `PCList` class were made. I created private variables in the PCList to store the arguments of the filter flags.
  With this in mind, methods were then created in the `ViewerFilterCommand` to update these private variables in the PCList. Whenever the PCList is printed, it will check if the filter is on. 
  If it is, the filtered list is printed, otherwise the original list will be printed instead.

### Contributions to the UG:
- `bye` command
  - Added a description as well as an expected outcome for better visualisation
- `help` command
  - Added a description for the two scenarios - PCViewer and PCBuilder as well as an expected outcome for better visualisation
- `filter FILTER_FLAGS` command
  - Added a description which informs the user on the types of flags that he can filter the PCList by and its expected outcome when filtering by a given input for better visualisation
  

### Contributions to the DG:
- `Parser` class
  - Created a description to explain the use case of this class as well as a class diagram for better visualisation
- `ViewerAddCommand` class description and its sequence diagram
  - Created a description to explain the flow of how the `ViewerAddCommand` class works as well as a sequence diagram for better visualisation
- Expected outcome for manual testing
  - Added expected outcome for the terminal when the user types in the respective code

### Contributions to team-based tasks:
For contributions to team based tasks, I was present for all the tutorials and provided relevant inputs and opinions and took turns to draw the diagrams required during the tutorials

### Review/mentoring contributions:
- [Review 1](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/79)
- [Review 2](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/63)
- [Review 3](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/82)
- [Review 4](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/120)
- [Review 5](https://github.com/AY2223S2-CS2113-T12-2/tp/pull/44)

### Contributions beyond the project team:
- [Review 1](https://github.com/nus-cs2113-AY2223S2/ip/pull/178/files/c89843fdf5ea4051b4ba683bd02502033e5ab91b)
- [Review 2](https://github.com/nus-cs2113-AY2223S2/ip/pull/26/files/f8d18020e63518122ed7001ce74f0c4ddf35d553)
- [Review 3](https://github.com/nus-cs2113-AY2223S2/tp/pull/52/files/77013d229815e2eb36bfb72434ccbc127934114f)
- [Review 4](https://github.com/nus-cs2113-AY2223S2/tp/pull/6/files/54eff476e453138d6441bc55dab2912939e887c1)

