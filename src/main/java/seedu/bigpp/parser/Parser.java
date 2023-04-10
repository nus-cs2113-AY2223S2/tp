package seedu.bigpp.parser;

import seedu.bigpp.command.Command;
import seedu.bigpp.command.buildercommand.BuilderListComponentCommand;
import seedu.bigpp.command.buildercommand.BuilderEditBudgetCommand;
import seedu.bigpp.command.buildercommand.BuilderSelectCommand;
import seedu.bigpp.command.buildercommand.BuilderEditNameCommand;
import seedu.bigpp.command.buildercommand.BuilderHelpCommand;
import seedu.bigpp.command.buildercommand.BuilderUnselectCommand;
import seedu.bigpp.command.buildercommand.BuilderInfoCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareChassisCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareCpuCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareCpuCoolerCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareGpuCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareMotherboardCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderComparePsuCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareRamCommand;
import seedu.bigpp.command.buildercommand.comparecommand.BuilderCompareStorageCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomChassisCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomCpuCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomCpuCoolerCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomGpuCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomMotherboardCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomPsuCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomRamCommand;
import seedu.bigpp.command.buildercommand.BuilderCustomStorageCommand;
import seedu.bigpp.command.commoncommand.BackCommand;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.command.commoncommand.UnrecognizedCommand;
import seedu.bigpp.command.viewercommand.ViewerAddCommand;
import seedu.bigpp.command.viewercommand.ViewerDeleteCommand;
import seedu.bigpp.command.viewercommand.ViewerEditCommand;
import seedu.bigpp.command.viewercommand.ViewerFilterCommand;
import seedu.bigpp.command.viewercommand.ViewerHelpCommand;
import seedu.bigpp.command.viewercommand.ViewerViewCommand;
import seedu.bigpp.ui.UI;
import static seedu.bigpp.component.ComponentType.CHASSIS_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_COOLER_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_TYPE;
import static seedu.bigpp.component.ComponentType.GPU_TYPE;
import static seedu.bigpp.component.ComponentType.MOTHERBOARD_TYPE;
import static seedu.bigpp.component.ComponentType.PSU_TYPE;
import static seedu.bigpp.component.ComponentType.RAM_TYPE;
import static seedu.bigpp.component.ComponentType.STORAGE_TYPE;

public class Parser {

    /**
     * Parses user input into command for execution, separates viewer and builder
     * commands.
     * @param userInput full user input string
     * @return the command
     */
    public Command parseCommand(String userInput) {

        String trimmedInput = userInput.trim();
        String[] inputList = trimmedInput.split(" ", 2);
        String arguments = "";

        if (inputList.length == 2) {
            arguments = inputList[1];
            arguments = arguments.trim();
        }
        String commandWord = inputList[0];
        commandWord = commandWord.toLowerCase();
        commandWord = commandWord.trim();

        // try common commands first
        if (commandWord.equals("back")) {
            return new BackCommand();
        }

        if (commandWord.equals("bye")) {
            return new ByeCommand();
        }

        // once common commands are tried, try menu specific commands
        switch (UI.getUiState()) {

        case PCVIEWER:
            return parseViewerCommand(commandWord, arguments);

        case PCBUILDER:
            return parseBuilderCommand(commandWord, arguments);

        default:
            return new UnrecognizedCommand("");
        }
    }

    /**
     * Parses user input for viewer commands.
     * @param commandWord the command word
     * @param arguments the arguments
     * @return the command
     */
    private Command parseViewerCommand(String commandWord, String arguments) {

        switch (commandWord) {
        case "add":
            return new ViewerAddCommand(arguments);
        case "delete":
            return new ViewerDeleteCommand(arguments);
        case "view":
            return new ViewerViewCommand(arguments);
        case "edit":
            return new ViewerEditCommand(arguments);
        case "filter":
            return new ViewerFilterCommand(arguments);
        case "help":
            return new ViewerHelpCommand();
        default:
            return new UnrecognizedCommand("");
        }
    }

    /**
     * Parses user input for builder commands.
     * @param commandWord the command word
     * @param arguments the arguments
     * @return the command
     */
    private Command parseBuilderCommand(String commandWord, String arguments) {

        switch (commandWord) {
        case "list":
            return new BuilderListComponentCommand(arguments);
        case "name":
            return new BuilderEditNameCommand(arguments);
        case "budget":
            return new BuilderEditBudgetCommand(arguments);
        case "select":
            return new BuilderSelectCommand(arguments);
        case "info":
            return new BuilderInfoCommand(arguments);
        case "unselect":
            return new BuilderUnselectCommand(arguments);
        case "custom":
            return parseCustomCommand(arguments);
        case "compare":
            return parseCompareCommand(arguments);
        case "help":
            return new BuilderHelpCommand();
        default:
            return new UnrecognizedCommand("");
        }
    }

    /**
     * Parses user input for custom component commands.
     * @param arguments the arguments
     * @return the command
     */
    private Command parseCustomCommand(String arguments) {
        String[] inputList = arguments.split(" ", 2);
        String componentType = inputList[0].toLowerCase();
        String attributes = "";
        if (inputList.length == 2) {
            attributes = inputList[1];
        }

        switch (componentType) {
        case CPU_TYPE:
            return new BuilderCustomCpuCommand(attributes);
        case GPU_TYPE:
            return new BuilderCustomGpuCommand(attributes);
        case RAM_TYPE:
            return new BuilderCustomRamCommand(attributes);
        case STORAGE_TYPE:
            return new BuilderCustomStorageCommand(attributes);
        case PSU_TYPE:
            return new BuilderCustomPsuCommand(attributes);
        case MOTHERBOARD_TYPE:
            return new BuilderCustomMotherboardCommand(attributes);
        case CHASSIS_TYPE:
            return new BuilderCustomChassisCommand(attributes);
        case CPU_COOLER_TYPE:
            return new BuilderCustomCpuCoolerCommand(attributes);
        default:
            return new UnrecognizedCommand(
                    "Invalid component type! Valid types are "
                            + "(cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }
    }

    private Command parseCompareCommand(String arguments) {
        String[] inputList = arguments.split(" ", 2);
        String componentType = inputList[0].trim();
        String attributes = "";
        if (inputList.length == 2) {
            attributes = inputList[1].trim();
        }

        switch (componentType) {
        case CPU_TYPE:
            return new BuilderCompareCpuCommand(attributes);
        case GPU_TYPE:
            return new BuilderCompareGpuCommand(attributes);
        case RAM_TYPE:
            return new BuilderCompareRamCommand(attributes);
        case STORAGE_TYPE:
            return new BuilderCompareStorageCommand(attributes);
        case PSU_TYPE:
            return new BuilderComparePsuCommand(attributes);
        case MOTHERBOARD_TYPE:
            return new BuilderCompareMotherboardCommand(attributes);
        case CHASSIS_TYPE:
            return new BuilderCompareChassisCommand(attributes);
        case CPU_COOLER_TYPE:
            return new BuilderCompareCpuCoolerCommand(attributes);
        default:
            return new UnrecognizedCommand(
                    "Invalid component type! Valid types are "
                            + "(cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }
    }
}
