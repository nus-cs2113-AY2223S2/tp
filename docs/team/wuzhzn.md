# Wu Zhen - Project Portfolio Page

## Overview

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient
operation through the Command-Line Interface(CLI).

### Summary of Contributions

Contributions to the project: [Reposense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=wuzhzn&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

- Added features:
  - Load samples command and the venue list creation logic 
    - The load samples command adds a much needed functionality for users to be able to try out the commands readily.
    - The venue list creation is done by adding individual venues into an array list and returning that array list. This is done as we felt that the venue list should be fixed and not be editable by users. 
    - Potential development: Filter venue list by venue size 
  - Purge 
    - Helps users to clear all the company list data previously saved in the text file
    - Potential development: Purge by industry/size
  - Added Event and EventDetailsStorage class
    - The Event class is essential for users to keep track of all the event details when the programme runs, while the EventDetailsStorage class manages the saving and updating of the text file that containes the event details.
  
- Improved features 

- UG contribution: 
  - Command summary
  - Feature description of list venue, choose venue, load sample, purge commands
  
- DG contribution:
  - Description of flow of programme
  - Architect diagram of main programme
  - Class diagram for event class 
  - ![event.png](..%2FUML%2FImage%2Fevent.png)
  - Sequence diagram for choosing venue
![chooseVenueCommand.png](..%2FUML%2FImage%2FchooseVenueCommand.png)

    
