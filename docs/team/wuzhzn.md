# Wu Zhen - Project Portfolio Page

## Overview

EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient
operation through the Command-Line Interface(CLI).

### Summary of Contributions

- Contributions to the project: [Reposense](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=wuzhzn&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).
- Added features: [New](#added-features-)
- Improved features: [Improved](#improved-features)
- UG: [UG contributions](#ug-contribution-)
- DG: [DG contribution](#dg-contribution-)
- Team contribution: [Team contribution](#team-contribution-)

#### Added features:
  - Venue, Storage, Parser, Event and Command classes
    - The base code and the general flow of the programme
  - Load samples and the venue list creation logic 
    - The load samples adds a much needed functionality for users to be able to try out the commands readily.
    - The venue list creation is done by adding individual venues into an array list and returning that array list. This is done as we felt that the venue list should be fixed and not be editable by users. 
    - Potential development: Update the venue list to be dynamic/have more entries
  - Purge 
    - Helps users to clear all the company list data previously saved in the text file
    - Potential development: Purge by industry/size
  - List venue 
    - Helps user to see the list of venues that he can choose, which is supplemented with choose venue command
  - Added Event and EventDetailsStorage class
      - The Event class is essential for users to keep track of all the event details when the programme runs, while the EventDetailsStorage class manages the saving and updating of the text file that containes the event details.
  - Choose venue command
      - This command helps users to update the venue for their event.

#### Improved features
- Bug fixes in parser regarding empty field in user input
- Fixed bug where programme crashes when user edits the text file
- Added general wrong format exception for user input
- Bug fixes in reading information from text file
- Junit test for Venue and venueList class
- Fixed bug in 

#### UG contribution: 
  - Command summary
  - Feature description of list venue, choose venue, load sample, purge commands
  
#### DG contribution:
  - Description of flow of programme
  - Helped in writing/reviewing most of the DG
  - Architect diagram of main programme

![broadOverview.png](..%2FUML%2FImage%2FbroadOverview.png)

  - Event class diagram

![event.png](..%2FUML%2FImage%2Fevent.png)

  - Storage overview diagram

![StorageOverview.png](..%2FUML%2FImage%2FStorageOverview.png)

  - Sequence diagram for choosing venue

![chooseVenueCommand.png](..%2FUML%2FImage%2FchooseVenueCommand.png)


#### Team contribution:
1. Management of releases, issue tracking and assigning issues to team members
2. Tagged issues with their respective tags
3. Helped review PRs and debug member's code
4. Guided team members on their DG and UG parts, redoing them when needed


    
