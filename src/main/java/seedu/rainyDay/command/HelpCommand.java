package seedu.rainyDay.command;


public class HelpCommand extends Command {
    private static final String HELP_COMMAND = "" +
            "+====================================================================================================+\n" +
            "|Welcome to RainyDay! Here is the overview of commands available                                     |\n" +
            "+====================================================================================================+\n" +
            "|Feature          |Command |Additional Syntax                                                        |\n" +
            "+-----+-----------+--------+-------------------------------------------------------------------------+\n" +
            "|Add entry        |add     |[-in/-out] [description] $[value] {-c category} {-date date}             |\n" +
            "|Delete entry     |delete  |[index]                                                                  |\n" +
            "|View all entries |view    |                                                                         |\n" +
            "|Filter entries   |filter  |{-in} {-out} {-d description} {-c category} {-date date}                 |\n" +
            "|Edit an entry    |edit    |[index] {-in/ -out/ -d description/ -v $value/ -c category/ -date date}  |\n" +
            "|Export to .csv   |export  |                                                                         |\n" +
            "|Display a guide  |help    |{command}                                                                |\n" +
            "+=====+==============================================================================================+\n" +
            "|[] :  Denotes compulsory fields. Relevant details / flags must be included.                         |\n" +
            "|{} :  Denotes optional fields. Can be ignored                                                       |\n" +
            "|For more information on any command: type   < help {command} >   as shown above                     |\n" +
            "+====================================================================================================+\n";

    private static final String HELP_ADD_COMMAND = "" +
            "+====================================================================================================+\n" +
            "| Add command                 | Used to add a new entry to RainyDay                                  |\n" +
            "+====================================================================================================+\n" +
            "| Details       | Requirement | Description                                                          |\n" +
            "+---------------+-------------+----------------------------------------------------------------------+\n" +
            "| -in / -out    | Mandatory   | Used to denote if the entry is an inflow or an outflow               |\n" +
            "| <description> | Optional    | Used to describe the entry. Description will be left blank if omitted|\n" +
            "| $<value>      | Mandatory   | Used to set the value of the entry.                                  |\n" +
            "| -c <category> | Optional    | Used to denote the category of the entry. Set to Default if omitted  |\n" +
            "| -date <date>  | Optional    | Used to denote the date of the entry. Set to present date if omitted |\n" +
            "+====================================================================================================+\n" +
            "| Example Usage               | Description                                                          |\n" +
            "+-----------------------------+----------------------------------------------------------------------+\n" +
            "| add -in Salary $2113.09     | Adds entry with description: Salary and value: $2113.09 as inflow    |\n" +
            "|                             |                                                                      |\n" +
            "| add -out Noodles $5         | Adds entry with description: Noodles and value: $5 as outflow        |\n" +
            "|                             |                                                                      |\n" +
            "| add -out Bus $0.98 -c       | Adds entry with description: Bus and value: $0.98 as outflow,        |\n" +
            "| Transport                   | with its category set to Transport                                   |\n" +
            "|                             |                                                                      |\n" +
            "| add -out Bubble Tea $5.20   | Adds entry with description: Bubble Tea and value: $5.20 as outflow, |\n" +
            "| -c Food -date 22/03/2018    | with its category set to Food and date set to 22nd March, 2018       |\n" +
            "|                             |                                                                      |\n" +
            "| add -out $198 -c Bills      | Adds entry with no description and value: $198 as outflow, with its  |\n" +
            "|                             | category set to Bills                                                |\n" +
            "+====================================================================================================+\n";

    private static final String HELP_DELETE_COMMAND = "" +
            "+====================================================================================================+\n" +
            "| Delete command              | Used to delete an existing entry from RainyDay                       |\n" +
            "+====================================================================================================+\n" +
            "| Details    | Requirement    | Description                                                          |\n" +
            "+------------+----------------+----------------------------------------------------------------------+\n" +
            "| index      | Mandatory      | Used to delete an entry, as denoted by the given index               |\n" +
            "+====================================================================================================+\n" +
            "| Example Usage               | Description                                                          |\n" +
            "+-----------------------------+----------------------------------------------------------------------+\n" +
            "| delete 7                    | Deletes the 7th entry from the list                                  |\n" +
            "+====================================================================================================+\n";

    private static final String HELP_VIEW_COMMAND = "" +
            "+====================================================================================================+\n" +
            "| View command                | Views all entries in RainyDay                                        |\n" +
            "+====================================================================================================+\n" +
            "| Details    | Requirement    | Description                                                          |\n" +
            "+------------+----------------+----------------------------------------------------------------------+\n" +
            "| Behold the barren lands, that will be this way till sorting is implemented                         |\n" +
            "+====================================================================================================+\n" +
            "| Example Usage               | Description                                                          |\n" +
            "+-----------------------------+----------------------------------------------------------------------+\n" +
            "| WIP                         | WIP                                                                  |\n" +
            "+====================================================================================================+\n";
    private static final String HELP_FILTER_COMMAND = "WIP. Go Read the UG";
    private static final String HELP_EDIT_COMMAND = "WIP. Go Read the UG";
    private static final String HELP_EXPORT_COMMAND = "WIP. Go Read the UG";
    private static final String HELP_HELP_COMMAND = "Very funny";

    private final String description;

    public HelpCommand (String description) {
        this.description = description;
    }

    @Override
    protected void setupLogger() {

    }

    @Override
    public CommandResult execute() {
        if(description.equals("add")) {
            return new CommandResult(HELP_ADD_COMMAND);
        }
        if(description.equals("delete")) {
            return new CommandResult(HELP_DELETE_COMMAND);
        }
        if(description.equals("view")) {
            return new CommandResult(HELP_VIEW_COMMAND);
        }
        if(description.equals("filter")) {
            return new CommandResult(HELP_FILTER_COMMAND);
        }
        if(description.equals("edit")) {
            return new CommandResult(HELP_EDIT_COMMAND);
        }
        if(description.equals("help")) {
            return new CommandResult(HELP_HELP_COMMAND);
        }
        if(description.equals("export")) {
            return new CommandResult(HELP_EXPORT_COMMAND);
        }
        return new CommandResult(HELP_COMMAND);
    }
}
