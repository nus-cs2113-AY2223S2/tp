//@@author RuiShengGit
package seedu.todolist.constants;

public class Help {
    public static final String HELP_COMMAND = "Here are the list of commands that you can use:\n" +
            "+----------------------------------------------------------------------------------------+\n" +
            "| Command                                | Description                                   |\n" +
            "+----------------------------------------------------------------------------------------+\n" +
            "| add DESCRIPTION [-due DEADLINE]        | Adds a new task to your To-Do list.           |\n" +
            "|                 [-email EMAIL_ADDRESS] |                                               |\n" +
            "|                 [-email EMAIL_ADDRESS] |                                               |\n" +
            "|                 [-rep REPEAT_DURATION] |                                               |\n" +
            "|                 [-prio PRIORITY_LEVEL] |                                               |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| mark ID                                | Marks a task with the given id by the user    |\n" +
            "|                                        | as completed                                  |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| unmark ID                              | Unmarks a task with the given id by the user  |\n" +
            "|                                        | as incomplete.                                |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| delete ID                              | Removes the task with the given id by the     |\n" +
            "|                                        | user from the To-Do list.                     |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| desc ID -edit DESCRIPTION              | Edits the description of a task with the      |\n" +
            "|                                        | given id in the ToDo List.                    |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| prio ID -edit PRIORITY_LEVEL           | Edits, or deletes the priority level of a     |\n" +
            "|                                        | task with the given id in the ToDo List.      |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| due ID -edit DEADLINE                  | Edits or deletes the deadline of a task with  |\n" +
            "|        -del                            | the given id in the To-Do List.               |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| email ID -edit EMAIL_ADDRESS           | Adds, edits, or deletes the email address of  |\n" +
            "|          -del                          | a task with the given id in the To-Do List.   |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| tags ID -edit LIST_OF_TAGS             | Adds, edits, or deletes the tags of a task    |\n" +
            "|         -del                           | with the given id in the To-Do List.          |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| rep ID -edit REPEAT_DURATION           | Edits or deletes the recurring count of a     |\n" +
            "|                                        | task with the given id in the To-Do List.     |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| list                                   | Display all tasks stored in the To-Do List.   |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| info ID                                | Display all the attributes of the task with   |\n" +
            "|                                        | the given id in the To-Do List.               |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| progress                               | Displays the progress of and lists tasks      |\n" +
            "|                                        | that are due this week in To-Do list.         |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| help                                   | Displays all possible commands of the program |\n" +
            "|                                        | and their description                         |\n" +
            "------------------------------------------------------------------------------------------\n" +
            "| exit                                   | Exits the program.                            |\n" +
            "------------------------------------------------------------------------------------------";
}
