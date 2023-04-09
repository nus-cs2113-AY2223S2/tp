package seedu.duke;

public class Module {
    private int univId;
    private String moduleCode;
    private String moduleName;
    private int moduleMCs;
    private String nusModuleCode;
    private String nusModuleName;
    private int nusModuleMCs;

    public Module(int univId, String moduleCode, String moduleName, int moduleMCs, String nusModuleCode,
                  String nusModuleName, int nusModuleMCs) {
        this.univId = univId;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleMCs = moduleMCs;
        this.nusModuleCode = nusModuleCode;
        this.nusModuleName = nusModuleName;
        this.nusModuleMCs = nusModuleMCs;
    }

    @Override
    public String toString() {
        return univId + "," + moduleCode + "," + moduleName + "," + moduleMCs + "," + nusModuleCode + ","
                + nusModuleName + "," + nusModuleMCs;
    }

    public int getUnivId() {
        return univId;
    }

    public int getModuleMCs() {
        return moduleMCs;
    }

    public int getNusModuleMCs() {
        return nusModuleMCs;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getNusModuleCode() {
        return nusModuleCode;
    }

    public String getNusModuleName() {
        return nusModuleName;
    }

    //@@author L0Z1K
    public boolean isInFilter(String filter) {
        if (filter == null) {
            return true;
        }
        String[] conditions = filter.split(", ");
        for (String condition : conditions) {
            if (condition.startsWith("/mc")) {
                int credits = Integer.parseInt(condition.replaceFirst("/mc", "").trim());
                if (moduleMCs != credits) {
                    return false;
                }
            } else if (condition.startsWith("/name")) {
                String keyword = condition.replaceFirst("/name", "").trim();
                if (!moduleName.toLowerCase().contains(keyword)) {
                    return false;
                }
            }
        }
        return true;
    }
    //@@author

    /**
     * Returns the length of the line to be printed to User Console for a module information.
     *
     * @param module Module to be printed to User Console
     * @return Length of line printed when module information is printed out
     */
    public static int getPrintingLength(Module module) {
        String module1ModuleCode = module.getModuleCode();
        String module1ModuleName = module.getModuleName();
        int module1ModuleMCs = module.getModuleMCs();
        int module1PrintingLength = module1ModuleCode.length() + module1ModuleName.length()
                + String.valueOf(module1ModuleMCs).length();
        return module1PrintingLength;
    }
}
