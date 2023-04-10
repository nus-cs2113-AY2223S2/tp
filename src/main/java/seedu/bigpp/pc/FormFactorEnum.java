package seedu.bigpp.pc;

public enum FormFactorEnum {

    MINI(1, "mini"), MICRO(2, "micro"), ATX(3, "atx");

    private int value;
    private String formFactor;

    FormFactorEnum(int value, String formFactor) {
        this.value = value;
    }

    public int getFormFactorEnum() {
        return value;
    }

    public String getFormFactorEnumString() {
        return formFactor;
    }

    public static Boolean isFormFactor(String formFactor) {
        switch (formFactor) {
        case "mini":
            return true;
        case "micro":
            return true;
        case "atx":
            return true;
        default:
            return false;
        }
    }

    public static int getFormFactorFromString(String formFactor) {
        switch (formFactor) {
        case "mini":
            return 1;
        case "micro":
            return 2;
        case "atx":
            return 3;
        default:
            return 0;
        }
    }

}
