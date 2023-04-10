---
title: Zhang Zhanshan(zzs-redcocoon)'s Project Portfolio Page

---

## Project: MovieMate

MovieMate is a desktop movie application used to save your own watched list and to-watch list.
The user interacts with it using a CLI, and it is written in Java.

Given below are my contributions to the project.

* **Enhancement**: Refactor the code (change main class, add parser function and command class)
  * What it does: Previously there were no command classes, and the main class is responsible for parsing all the input commands and calling corresponding functions. The enhancement creates a parser function and classes for all the commands. Then the main class can call the parser object to get the command and call its execute function to execute it.
  * Justification: This enhancement makes the code more explicit. The main function is shortened; the code is divided into several logic modules.
  * Highlights: The enhancement is challenging because it requires creating all command classes based on the existing functions. The enhancement changed the code structure and had a big impact on the subsequent work.

* **Enhancement**: Refactor the code according to the SLAP principle
  * What it does: According to the SLAP principle, modified RemoveListCommand.java, SeeDetailCommand.java. e.g. abstract the method to reduce duplicate code; made the happy path prominent; put all print functions to Ui class.
  * Justification: This enhancement makes the code more explicit. Programmers can easily find out the function of code blocks after the SLAP update.
  * Highlights: The enhancement is challenging because it requires abstraction ability and an understanding of code quality.

* **New Feature**: Add the ability to filter movies according to their genres
  * What it does: Get the genre of the movie input by the user, and then display the movies that match the genre in the list of movies the user has watched / wants to watch.
  * Justification: This feature enables the user to search for the movie according to their genres.
  * Highlights: This enhancement affects existing commands and commands to be added in the future.

* **Enhancement**: Add exceptions and fix bugs in the code.
  * What it does: Add exceptions for watched and towatch command. Fix bugs in addreview, viewreview, deletereview command. 
  * Justification: This feature prevents invalid inputs and shows a proper error message.
  * Highlights: This enhancement needs a long time of testing and understanding of the code.


* **Code contributed**: [here](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=zzs-redcocoon)
* **Project management**:
  * Actively reply in group meetings.
  * continuously post new issues of the project
  * help manage releases `v1.0` - `v2.1` 
* **Documentation**:
  * User Guide, Developer Guide:
    * Added initial documentation for 3 review features. Continued to check the user guide & developer guide throughout the project. 
* **Community**:
  * Reported bugs and suggestions for other teams in the class during PE Dry Run.
  * PRs reviewed (with non-trivial review comments): #22, #56, #57, #131
