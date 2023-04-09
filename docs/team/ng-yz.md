# Ng Yan Zhen's Project Portfolio Page

## Overview 
MagusStock is a Java command-line interface (CLI) designed for inventory management.
This application that I have contributed to developing with my team aims to help store operators,
IT administrators and logistics managers to manage their inventory more efficiently and effectively.
While CLI applications are not as user-friendly as GUI applications, they are much faster to use
and more importantly, portable and can be used on any platform that supports Java.

## Summary of Contributions
Code contributed: [RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ng-yz&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Ng-YZ&tabRepo=AY2223S2-CS2113-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

Enhancements implemented:
* Added `remove` feature
  * Allows the user to remove items from the inventory as per necessary
  * Enhanced `remove` feature by allowing user to remove items both using UPC code and index of item in `list`
* Added `category` feature 
  * Allows the user to set and change an item's category on `add` and `edit` commands
  * Allows the user to view all categories available in the inventory by entering `cat list` command
  * Allows the user to view items by category (all items in each category are listed in a table) by entering `cat table` command
  * Enhanced `filter`, `add`, `edit` features to include any category of the same spelling yet different capitalizations under the same known category.
* Javadoc comments for relevant classes and methods
* Junit tests for `RemoveCommand` and `CategoryCommand`