package seedu.duke;
import java.util.ArrayList;

public class Parser {
    private static ArrayList<University> universities = new DataReader().getUniversities();
    private static ArrayList<Module> modules = new DataReader().getModules();
    private static final String LINE = "____________________________________________________________";
    private static final String COMMAND_INPUT_ERROR = "Please type in the correct command input";

    public static ArrayList<String> parseCommand(String userInput) {
        String[] input = userInput.split((""), 2);
        ArrayList<String> commandWords = new ArrayList<>();
        String commandInput = input[0];
        String commandSpecifics = input[1];
        commandWords.add(commandInput);
        commandWords.add(commandSpecifics);
        return commandWords;
    }

    public static void printPUModules(int univID) {
        ArrayList<Module> puModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getUnivId() == univID) {
                puModules.add(module);
            }
        }
        int puModulesIndex = 0;
        for (Module module : puModules) {
            puModulesIndex++;
            String moduleCode = module.getModuleCode();
            String moduleName = module.getModuleName();
            int moduleMCs = module.getModuleMCs();
            String nusModuleCode = module.getNusModuleCode();
            String nusModuleName = module.getNusModuleName();
            int nusModuleMCs = module.getNusModuleMCs();
            System.out.print(puModulesIndex + ". ");
            System.out.println("[" + moduleCode + "]" + "[" + moduleName + "]" + "[" + moduleMCs + "]");
            System.out.print("   maps to ----> ");
            System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
        }
    }

    public static void printPUList() {
        System.out.println(LINE);
        for (University university : universities) {
            int uniId = university.getUnivId();
            String uniName = university.getUnivName();
            String uniAbbName = university.getUnivAbbName();
            System.out.println(uniId + ". " + uniName + " " + uniAbbName);
        }
        System.out.println(LINE);
    }

    public static void printCurrentModList() {
        int listIndex = 0;
        System.out.println(LINE);
        for (Module module : modules) {
            listIndex++;
            String moduleCode = module.getModuleCode();
            String moduleName = module.getModuleName();
            int moduleMCs = module.getModuleMCs();
            String nusModuleCode = module.getNusModuleCode();
            String nusModuleName = module.getNusModuleName();
            int nusModuleMCs = module.getNusModuleMCs();
            System.out.print(listIndex + ".");
            System.out.println("[" + moduleCode + "]" + "[" + moduleName + "]" + "[" + moduleMCs + "]");
            System.out.print("   map to ----> ");
            System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
        }
        System.out.println(LINE);
    }
}