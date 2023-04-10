---
title: Jared Ee's Project Portfolio Page
---

## Project: MovieMate

MovieMate is a desktop movie application used to save your own watched list and to-watch list.
The user interacts with it using a CLI, and it is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add movies to the watched/to-watch lists.
    * What it does: Get the movie name entered by the user and display some titles that fit the description.
    * Justification: This feature enables the user to search for the movie they have in mind by name and place it in their watched/to-watch list for future reference.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the ability to remove movies from the watched/to-watch lists.
    * What it does: Allow the user to remove movies from their lists by index.
    * Justification: This feature improves the user experience when the user would like to reduce clutter or if they have changed their mind on wanting a particular movie in the list.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. The implementation too was challenging as it required changes to existing commands.
    * removeWatchedList and removeToWatchList were previously 2 methods performing the remove function for their respective lists, only to be later merged into a single method, removeMovieList.


* **Code contributed**: [here](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=jared-ee&breakdown=true)
* **Project management**:
    * Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

* **Enhancements to existing features**:
    * General fixes for unhandled exceptions and unexpected output throughout the code
    * Enhanced `watched` and `towatch` features, automatically remove movie from to-watch list if user tries to add that same movie to watched list. Conversely, informs user if they try to add a movie already in watched list to to-watch list and prompts for Y/N response if they would like to proceed with deleting it in watched list.
    * Enhanced any feature requiring integer input (i.e. all except `filter` and `bye`) by continuously prompting for further id input if invalid input is registered, instead of requiring the user the re-type the command name again. Input 0 (otherwise an invalid movie id) is accepted in case the user wants to cancel the last command.

* **Documentation**:
    * User Guide:
        * Added initial documentation for all features. Continued to update examples provided regularly to reflect the code's functionality accurately as it changed throughout the project. 
    * Developer Guide:
        * Added implementation details of the `filter` feature.

* **Community**:
    * Reported bugs and suggestions for other teams in the class during PE Dry Run.
    * Raised bugs discovered when testing to team-mates and asked for suggestions on ways to resolve them. If I could come up with a solution myself, I would implement it first and ask for feedback from the rest.
