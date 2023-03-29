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
        return univId + "," + moduleCode + "," + moduleName + "," + moduleMCs + "," + nusModuleCode + "," + nusModuleName + "," + nusModuleMCs;
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

    public boolean isInFilter(String filter) {
        if (filter == null) {
            return true;
        }
        String[] conditions = filter.split(", ");
        for (String condition : conditions) {
            if (condition.toLowerCase().startsWith("mc == ")) {
                int credits = Integer.parseInt(condition.substring(6));
                if (moduleMCs != credits) {
                    return false;
                }
            } else if (condition.toLowerCase().contains(" in name")) {
                String keyword = condition.substring(0, condition.indexOf(" in name"));
                if (!moduleName.toLowerCase().contains(keyword)) {
                    return false;
                }
            }
        }
        return true;
    }
}
