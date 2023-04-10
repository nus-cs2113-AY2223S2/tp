# Chuah Wan Juin's Project Portfolio Page
## Project: Food Supply Tracker

### Overview
Food Supply Tracker (FST) is a desktop food list tracking the expiry date of the food in the list.
It is optimized for use via a Command Line Interface (CLI). If you can type fast, FST can get your food management tasks done faster than traditional GUI apps.

### Contributions

**1) New Feature: List Command**
  * What it does: List out all the food products in the order of expiry dates.
  * Justification: This feature makes viewing the food products a user convenient. Listing food in order 
of expiry dates makes it neater for user to look at foods that are expiring soon.
  * Enhancement implemented: 
    * Indicator of the number of days left or number of days expired to expired beside expiry date.
    * Label beside each food to indicate whether the food is expired or not.
    * Remind users the number of food products they still have in the list.

**2) New Feature: Add `-u` unit feature in the Add Food feature**
  * What it does: Allows the addition of unit to the food products. 
  * Justification: User can choose unit to gauge the remaining quantity 
of the food product that they are left. For example if the remaining quantity
is 2 without unit, it would be confusing how much is a "2". It could be 2 packets
or 2 boxes.
  * Enhancement implemented: 
     * Fixed set of units that users can choose from (Enumerations).
     * Food with common unit such as `milligrams` or `litre`, would automatically be
     assigned to units `mg` and `l` respectively, according to the fixed list of units provided.
     * Unit would change to plural or singular form according to the food quantity.

**3) New feature: Clear Command**
   * What it does: Allows users to clear the entire food list using one command.
   * Justification: Delete the entire food list, without the need of deleting food products one by one.

### Code contributed
[RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=wanjuin&tabRepo=AY2223S2-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project management & Review/mentoring contributions
* Set issues on milestone `v1.0` - `v2.1` on GitHub after finding bugs or possible enhancements.
  * [Link to Issues added by me](https://github.com/AY2223S2-CS2113-W13-3/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Awanjuin)
* Review issues set by group members and other peers (PE-D).
* Assign issues to myself and group members.
  * [Link to Issues assigned to me](https://github.com/AY2223S2-CS2113-W13-3/tp/issues?q=is%3Aclosed+assignee%3Awanjuin)
* Request for group members' review for my merge request on GitHub.
  * [Link to my Pull Request](https://github.com/AY2223S2-CS2113-W13-3/tp/pulls?q=is%3Apr+is%3Aclosed+author%3Awanjuin)
* Commented, suggested changes, and requested changes on group members' PRs.
  * [Link to PRs reviewed](https://github.com/AY2223S2-CS2113-W13-3/tp/issues?q=reviewed-by%3Awanjuin)

### Documentation
* User Guide: 
  * List Command section, Clear Command section, Exit Command section
  * Add Unit in Add Command section
  * Find Command example of usage, Update Command example of usage

* Developer Guide:
  * Diagrams:
    * ListCommandClassDiagram, ListCommandSequenceDiagram, ClearCommandClassDiagram, ClearCommandSequenceDiagram, ExitCommandClassDiagram, ExitCommandSequenceDiagram
  * Specifications: 
    * ListCommand, ClearCommand, and ExitCommand implementation description
  * Requirements (Appendix):
    * User Stories, Non-functional Requirements

* Logging of the project
  * [Link to Pull Request for Logging](https://github.com/AY2223S2-CS2113-W13-3/tp/pull/58)
