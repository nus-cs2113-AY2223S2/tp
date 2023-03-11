package seedu.Ui;
import java.util.ArrayList;
public class WeightUi {

    public void requestWeight() {
        System.out.println("Please enter your weight (in kg):");
    }

    public void showLatestWeight(int weight) {
        System.out.println("This is the latest weight you have entered in: " + weight + " kg");
    }

    public void showAllWeight(ArrayList<String> weights) {
        System.out.println("List of weights (in kg): ");
    }

    public void showWeightAdded(int weight) {
        System.out.println("This is your updated weight: " + weight + " kg");
    }
}
