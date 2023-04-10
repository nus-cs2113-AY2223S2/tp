# Zhang Wenze - Project Portfolio Page

## Overview
**NUSPlanner** is a user-friendly desktop application designed for NUS students to efficiently handle their personal, school, and external activities. It utilizes a Command Line Interface (CLI) on the desktop, providing a rapid and elegant approach to organizing your schedule.

### Summary of Contributions
[Reposense: Code Contribution](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=f13-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=kyrixn&tabRepo=AY2223S2-CS2113-F13-3%2Ftp[master]&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features Implemented:

* **Class: Schedule** 
  * attribute: Schedule is the basic class and the super class that we used. The class's attribution contains all kinds of time information that are need for event/modules class, namely startTime, endTime, recurringTime. Moreover, the class also contains flags that indicates whether the current schedule is a recurring event and does it have specific time information (HH:mm)  for ending and starting time attributes.
  *  method/what it does: the methods of this class includes different constructors and related conversion function to convert String in different format to LocalDateTime, which is the class we used to store time information. In this case we are able to create Schedule instance with different format of time information (schedules with complete time details and partial time information). The Methods also includes functions that convert the information of one instance to a printable format
  *  justification: Event and Modules have similar time information, so it's better to create a super class and make Event/Module inherit from it and in this case, the code is more structured and has a higher level of cohesion.
  *  highlight: the class accepts instances with various formats of time information (whether has specific date/time information for starting  / ending time) and behaves differently according to the format.
* **Class: Event**
  * what it does: it is a class inherits from Schedule. It has it's own attribute which is Description. In this class, some methods are overrode to make them fit the need of this class
* **features: maintaining the list of Events/modules in EventList:**
  * what it does: EventList contains an ArrayList of all the added Schedule and it helps to maintain the ArrayList based on the commands given by the user and parsed by the Parser. The functions include adding events/modules, deleting events//modules, editing events/modules etc. Moreover, it is able to handle input events with different levels of details of time information and behave accordingly
  * justification: The EventList Class serves as an intermediary role and avoids other classes access deep into the functionality of classes (Event, Schedule etc.) inside the ArrayList, thus reduces the coupling of the code base. 
  * highlight: It accepts events with different levels of details of time information and behave accordingly.
* **features: check conflictions between all Schedules**
  * what it does: It will check whether the current event/class will clash with any of the existing schedule and this is done every time user add classes of module/ event and add events. Since this need complete time information so it will only check the confliction between events/modules that will be shown on the timetable.
  * justification: It is hard and inappropriate to show multiple event in the same slot on timetable. Moreover, is it reasonable to have a function to remind user of time confliction.
  * highlight: can check confliction between all modules and customized events no matter they are recurring or not. moreover, the confliction checking period is constrained to current semester.


### Contributions to User Guide:
* revise the command format from V1.0 to V2.0, added example or changed old example of the out put of different commands.
* clarify the issues that are posted during PE dry-run
* write notice for commands to clarify the scope of our app, what need users to check themselves and what behavior will trigger exceptions.

### Contributions to the Developer's Guide:
* Add documentation for the `EventList` component, which includes diagrams for better readability.
* help to edit `UserUtility` part to makes it match the latest behavior of the code.

### Contributions to team-based tasks:
* Lead and coordinated weekly team meetings, help to split the work and make the team work more efficiently.
* Contributed to product ideation, help to brainstorme user stories and key features
* Use Github Projects task tracker to track my and the team's progress for each milestone
* Assigned issues for every milestones.
* Review pull requests from other team members.
