# Muhammad Akmal Hanis - Project Portfolio Page

## Overview
EveNtUS is a desktop application designed for career fair managers to manage career fairs, with a focus on efficient
operation through the Command-Line Interface(CLI).



### Summary of Contributions

Contributions to project: [Reposense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=akmalhanis&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=AkmalHanis&tabRepo=AY2223S2-CS2113-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

Written below are my contributions to the project, EveNtUS.

- New Added Feature 1: List companies feature of the companylist
  - Creation and alteration of the CompanyList Class
  - The ability to showcase the companies stored within the companyList through
    the list companies command
  - Resolved certain issues with `list companies` features in terms of indexing
  
- New Added Feature 2: Confirm and Unconfirm Command
  - Alters the company class to contain a confirmed or unconfirmed tag
  - Allows user to mark a specific company's attendance as confirmed or unconfirmed
  - Resolved certain issues with `confirm` / `unconfirm` features such as its ui elements and exceptions
  
- New Added Feature 3: List Unconfirmed companies feature
  - Filters the companies that are marked as unconfirmed and prints it out to the user
  - Reminds the user to see which companies still needs a confirmed attendance for an upcoming event
  - Resolved certain issues with `list unconfirmed` in terms of indexing 
  
- User guide: 
  - Added the user guide information for `list companies`, `confirm`, `unconfirm` and `list unconfirmed`
  - Updated user guide after resolving of issues from PE dry run
  
- Developer guide:
  - Added AddCommand class sequence diagram and class diagrams for Command classes
  - Wrote an explanation for all the command classes in the DG