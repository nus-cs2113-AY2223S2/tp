package seedu.duke.utils;

public class Sanitizer {
    public static String encode(String input){
        return input.replace(",","&cm;");
    }
    public static String decode(String input){
        return input.replace("&cm;",",");
    }
}
