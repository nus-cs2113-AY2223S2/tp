package seedu.duke.util;

public class StringSplitter {
    private static final String BLANK = " ";
    public String[] splitString(String rawInput){
        String[] splitInputs = rawInput.split(BLANK);
        for (int i = 0; i < splitInputs.length; i++){
            splitInputs[i] = splitInputs[i].toLowerCase();
        }
        return splitInputs;
    }
}
