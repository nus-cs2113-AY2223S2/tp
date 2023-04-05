package seedu.duke.data.ipptcalculator;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

//@author ghzr0
public class ParserIPPT {
    public static void main(String[] args){
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("/scores.json");
            Scores scores = gson.fromJson(reader, Scores.class);
            System.out.println("Input age , run time, pushup rep, situp rep");
            Scanner scanner = new Scanner(System.in);
            String[] userInput = scanner.nextLine().split(" ");
            IPPTCmd ipptCommand = new IPPTCmd(userInput);
            ipptCommand.executeCommand();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
