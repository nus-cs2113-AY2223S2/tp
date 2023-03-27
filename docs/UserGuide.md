# Duck User Guide(v 2.0)

Duck is a **desktop app for managing tasks and deadlines, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Duck will have a number of quality-of-life features for busy students seeking a simple solution to task management. These include automated task removal upon expiration, or limiting the number of tasks displayed to within a date range. Duck also has recurring task functionality to automate routine tasks so users do not have to manually re-add expired tasks.



* [**Features**](#features)
    * [Viewing help :](#viewing-help--help) `help`
    * [Listing all tasks :](#listing-all-tasks--list) `list `
    * [Listing all tasks up to X days into the future :](#listing-all-tasks-up-to-x-days-in-the-future--list-x) `list X `
    * [Mark a specified task as done :](#marking-a-task--mark-task_number) `mark <task number> `
    * [Unmark a specified task as  not done :](#unmarking-a-task--unmark-task_number) `unmark <task number>`
    * [Deleting a task :](#deleting-a-task--delete) `delete <task number> `
    * [Designate a priority to a given task :](#designate-a-task-priority--priority-task_number-priority) 
            * `priority <task number> <priority>`   
    * [Purge expired tasks :](#purge-expired-tasks--purge) `purge`
    * [Clearing all tasks (including datafile) :](#clearing-tasks-from-storage-clear) `clear `
    * Designate a given task to be recurring **[TO BE ADDED]** : 
            * `recurrent <task number> <recurrent period> `
    * [Find tasks matching a given keyword :](#finding-tasks-from-storage-that-match-a-keyword-find-keyword) `find <keyword>`
    * [Add tasks that can be broken down into the following 4 types:](#adding-a-todo-task--description)
    
            * Add ToDo: <description>
            * Add Deadline: <description> /by <yyyy-MM-dd HHmm>
            * Add Event: <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
            * Add Class: <description> /class <class_name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
    * [Terminate the program :](#exiting-the-program--bye) `bye`


# Features


## **Viewing help : `help`**

Displays all available commands and their input format on the terminal.

**Input:** `help`

**Output:**


```
（`･v･´ ）: Here are the commands you can give me: 
COMMAND_FORMAT : COMMAND_FUNCTIONALITY EXPLANATION
.
.
.
（`･v･´ ）: How else may I assist you today, human?
```



## **Listing all tasks : `list`**

Displays all tasks currently stored in the application.

**Input:** `list `

**Output: Demonstrated with 1 of each type currently in the stored data array**


```
Here are the tasks in your list:
	 1.	 [T][ ] todo (No priority established.)
	 2.	 [D][ ] deadline (by: 2023-03-25 2359) (No priority established.)
	 3.	 [E][ ] event (from: 2023-03-25 1200 to: 2023-03-26 2359) (No priority established.)
	 4.	 [C][ ] CS2113: Class (from: 2023-03-25 1100 to: 2023-03-25 1200) (No priority established.)
```

## **Listing all low priority tasks : `low_priority`**
Displays all tasks that have been assigned low priority  
If there are no tasks in the low priority list, another message will be shown

**Input:** `low_priority`  
**Output: Demonstrated with 1 of each data type currently in the stored data array**
```
Quack!
	 You have 3 tasks that are low in priority!
	1.	 [T][ ] Water plants (Low priority.)
	2.	 [D][ ] Submit CS2113 quiz (by: 2023-03-31 2359) (Low priority.)
	3.	 [E][ ] CS2113 lecture (from: 2023-03-30 1100 to: 2023-03-30 1200) (Low priority.)
```
**If no tasks in low priority list:**
```
There are no tasks that are low in priority!
	____________________________________________________________
```

## **Listing all medium priority tasks : `medium_priority`**
Displays all tasks that have been assigned medium priority  
If there are no tasks in the medium priority list, another message will be shown

**Input:** `medium_priority`  
**Output: Demonstrated with 1 of each data type currently in the stored data array**
```
QUACK QUACK!!
	 You have 3 tasks that are medium in priority!
	1.	 [T][ ] Water plants (Medium priority.)
	2.	 [D][ ] Submit CS2113 quiz (by: 2023-03-31 2359) (Medium priority.)
	3.	 [E][ ] CS2113 lecture (from: 2023-03-30 1100 to: 2023-03-30 1200) (Medium priority.)
	____________________________________________________________
```
**If no tasks in medium priority list:**
```
There are no tasks that are medium in priority!
	____________________________________________________________
```

## **Listing all high priority tasks : `high_priority`**
Displays all tasks that have been assigned high priority  
If there are no tasks in the high priority list, another message will be shown

**Input:** `high_priority`  
**Output: Demonstrated with 1 of each data type currently in the stored data array**
```
QUACK QUACK QUACK!!!
	 You have 3 tasks that are high in priority!
	1.	 [T][ ] Water plants (High priority.)
	2.	 [D][ ] Submit CS2113 quiz (by: 2023-03-31 2359) (High priority.)
	3.	 [E][ ] CS2113 lecture (from: 2023-03-30 1100 to: 2023-03-30 1200) (High priority.)
	____________________________________________________________
```
**If no tasks in high priority list:**
```
There are no tasks that are high in priority!
	____________________________________________________________
```

## **Listing all tasks arranged by priority : `priority_list`**
Displays all tasks arranged by priority

**Input:** `priority_list`  
**Output: Demonstrated with 1 task in each priority list**
```
____________________________________________________________
	 Here are the tasks in your list arranged by priority:
	____________________________________________________________
	 QUACK QUACK QUACK!!!
	 You have 1 tasks that are high in priority!
	1.	 [E][ ] CS2113 lecture (from: 2023-03-30 1100 to: 2023-03-30 1200) (High priority.)
	____________________________________________________________
	 QUACK QUACK!!
	 You have 1 tasks that are medium in priority!
	1.	 [T][ ] Water plants (Medium priority.)
	____________________________________________________________
	 Quack!
	 You have 1 tasks that are low in priority!
	1.	 [D][ ] Submit CS2113 quiz (by: 2023-03-31 2359) (Low priority.)
	____________________________________________________________
```


## **Listing all tasks up to X days in the future : `list X`**

Displays all tasks currently stored in the application, up to X days into the future.

For instance <code>list 0<strong></strong></code>returns all tasks that are starting within 24 hours.

**Input:** `list X `

**Output:  Demonstrated by inputting <code>list 0</code> on 24/3/2023 12:40, using the same array as above</strong>**


```
 Here are your tasks in 0 days:
	 1.	 [E][ ] event (from: 2023-03-25 1200 to: 2023-03-26 2359) (No priority established.)
	 2.	 [C][ ] CS2113: Class (from: 2023-03-25 1100 to: 2023-03-25 1200) (No priority established.)
```



## **Marking a task : `mark <Task_Number>`**

Marks a task from the tasklist as complete.

**Input: `mark <Task_Number>`**

**Output:  Demonstrated with input `mark 1`**


```
Understood. I've marked this task as done:
	 	 [T][X] todo (No priority established.)
```



## **Unmarking a task : `unmark <Task_Number>`**

Unmarks a task from the tasklist as not complete.

**Input:** `unmark <Task_Number>`

**Output:  Demonstrated with input `unmark 1`**


```
 Understood. I've marked this task as not done yet:
	 	 [T][ ] todo (No priority established.)
```



## **Deleting a task : `delete`**

Removes a task from the tasklist.

**Input:** `delete <Task_Number>`

**Output:  Demonstrated with removing the following task**


```
Understood. I have removed this task:
	  [T][ ] todo (No priority established.)
You now have 3 tasks in your list.
```

## **Designate a task priority : `priority <Task_Number> <priority>`**



Specifies a priority for a given task, with the following assignments: 
* 1 - Low 
* 2 - Medium 
* 3 - High 

By default there is no priority specified.

**Input:** `priority <Task_Number> <priority>`

**Output:  Demonstrated with input `priority 1 1`**


```
Understood. The task's new priority is:
	 Low priority.
```


## **Purge Expired Tasks : `purge`**

Prompts the user for confirmation. Proceeds to remove all expired tasks from storage upon confirmation. This operation is automatically executed once upon Duck’s startup.

**Input:** `purge`

**Output: Demonstrated by having an expired deadline task in the array**


```
 Displaying all expired tasks below...

	 [D][ ] deadline (by: 2023-03-24 1100) (No priority established.)
____________________________________________________________
	 Quack! A total of 1 task has expired!
	 Should I remove these tasks from the pending list human?
____________________________________________________________
	 THIS IS AN IRREVERSIBLE PROCESS. ARE YOU SURE? Y/N

```



* **If the user inputs ‘Y’:** 
```
Expired Tasks have been purged from the list!
I love purging things,human...
```


* **If the user inputs ‘N’:** 
```
Quack! Expired tasks have not been purged.
```

## **Clearing tasks from storage: `clear`**

Prompts the user for confirmation. Proceeds to clear all tasks from storage upon confirmation.

**Input:** `clear`

**Output:** 


```
THIS IS AN IRREVERSIBLE PROCESS. ARE YOU SURE? Y/N
```



* **If the user inputs ‘Y’:** `Got it, all tasks have been cleared.`
* **If the user inputs ‘N’:** `Quack! Process cancelled.`

## **Finding tasks from storage that match a keyword: `find <keyword>`**

Traverses through the stored tasks to return all tasks that contain the keyword

**Input:** `find <keyword>`

**Output: Demonstrated by using the keyword `todo`**


```
Here are the matching tasks in your list:
1.	 [T][ ] todo (No priority established.)
```



## **Adding a ToDo Task : `<description>`**

Adds a ToDo task to the storage of Duck

**Input:** `<description>`

**Output:  Demonstrated by inputting `Todo`**


```
Alright, I have added this task: 
			 [T][ ] todo (No priority established.)
You now have 2 tasks in your list.
```



## **Adding a Deadline Task : `<description> /by <yyyy-MM-dd HHmm>`**

Adds a Deadline task to the storage of Duck

**Input:** `<description> /by <yyyy-MM-dd HHmm>`

**Output:  Demonstrated by inputting `deadline /by 2023-03-25 2359`**


```
Alright, I have added this task: 
	   [D][ ] deadline (by: 2023-03-25 2359) (No priority established.)
You now have 3 tasks in your list.
```



## **Adding an Event Task : `<description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`**

Adds an Event task to the storage of Duck

**Input:** `<description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

**Output:  Demonstrated by inputting `event /from 2023-03-25 2359 /to 2023-03-26 1100`**


```
Alright, I have added this task: 
		[E][ ] event (from: 2023-03-25 2359 to: 2023-03-26 1100) (No priority established.)
You now have 4 tasks in your list.
```



## **Adding a Class Task : `<description> /class <class_name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`**

Adds a Class task to the storage of Duck

**Input:** `<description> /class <class_name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

**Output:  Demonstrated by inputting `homework /class class /from 2023-03-25 1100 /to 2023-03-25 1300`**


```
Alright, I have added this task: 
			 [C][ ] class: homework (from: 2023-03-25 1100 to: 2023-03-25 1300) (No priority established.)
You now have 5 tasks in your list.
```



## **Exiting the program : `bye`**

Exits the program.

**Input:** `bye`

**Output:** 


```
Bye. Hope to see you again soon!
```


## **Saving the data :**

Duck’s data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
