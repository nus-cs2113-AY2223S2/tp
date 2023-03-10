package seedu.duke;

public class Module {
    private int univId;
    private String moduleCode;
    private String moduleName;
    private int moduleMCs;
    private String nusModuleCode;
    private String nusModuleName;
    private int nusModuleMCs;

    public Module(int univId, String moduleCode, String moduleName, int moduleMCs,
            String nusModuleCode, String nusModuleName, int nusModuleMCs) {
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
        return univId + "," + moduleCode + "," + moduleName + "," + moduleMCs + "," + nusModuleCode
                + "," + nusModuleName + "," + nusModuleMCs;
    }

    public int getUnivId() {
        return univId;
    }

    public String getModuleCode() {
        return moduleCode;
    }
}
