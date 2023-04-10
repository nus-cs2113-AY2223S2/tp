# Sangjun Nam - Project Portfolio Page

### Summary of Contributions

BrokeMan is a desktop app for managing expenses, incomes, and personal budget, optimized for use via a Command Line Interface(CLI). If you can type fast, BrokeMan can get your expenses and income management tasks done faster than traditional GUI apps.

---

### Code Contributed

You can take a closer look at my code contribution in the link below:

[Code Contribution Statistic](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=namsengi11&tabRepo=AY2223S2-CS2113-F13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

---

### Enhancements implemented

I have helped implement the backbone of the EntryList class that is used to functionalize the IncomeList and ExpenseList that controls the list of entries made by the user. I have came up with the ideas to how the Entry class can be implemented. I have implemented the Expense and ExpenseList class - which tracks the expenses made by the user - myself.

I have also added the time component to the Expense, Income, and Budget class. I have made use of the java.time package, LocalDate and LocalDateTime for example. I have added them as attribute for the Entry class to add the time aspect to all entries made by the user. I made use of methods provided by the classes of java.time to add, sort, and find entries made at a certain time.

I have also implemented how the budget data of different months are kept track. I utilized a double hashmap that uses years and months as a key to retrieve budget information of the specific week. 

I also contributed to commands that handle adding, editing, and sorting the expenses and incomes, as well as setting and viewing budget of specific months.

I have also created and handled exceptions that might arise from users entering commands, such as error in entering the time information or parsing the time information. An example of this is handling exceptions that arises from date input that is outside the range set by the program. 

I developed JUnit tests for the Parser class, as well as the Classes in the Entry Package, which controls the input/output flow of the program. I wrote test cases to ensure that most, if not all, methods of the classes are tested. I also wrote testcases for io testing using runtest.bat and runtest.sh.

I also resolved issues raised by the PE_Dry by throwing and catching new exceptions and adding comments on the error message. 

---

### Contributions to the DG

I added how features like EntryList and their subclasses, as well as the budget class are implemented to the program. I elaborated on how the abstract classes provide underlying methods for its subclasses. 

I collaborated with other teammates on the descriptions of the features and structure implemented. 

I added description of the features to be added in the future.

I described the Project Scope and the User Story of the product.

I added User Stories for BrokeMan, from currently implemented features to features coming soon. 

---

### Contributions to the UG

I added and edited descriptions of our commands to resolve the issues that arose from the PE-D.

I also added UML diagram that represents the Budget Class and its dependencies.

---

### Contribution to team-tasks

I led discussion on how the Entry package will be implemented in our program, from the super/sub class relationships and the data structure to store the information. 

I have initiated team meetings and proposed features to be implemented in each iterations. 

I have set up the github repository for our team, and managed the development goals of each iterations. 

I have led and created the release v1.0, which laid foundation to our program. 
