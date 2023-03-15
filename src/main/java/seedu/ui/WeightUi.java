package seedu.ui;
import java.util.ArrayList;
public class WeightUi extends GeneralUi {

    @Override
    public void requestWeight() {
        System.out.println("Please enter your weight (in kg):");
    }

    @Override
    public void showLatestWeight(int weight) {
        System.out.println("This is the latest weight you have entered in: " + weight + " kg");
    }

    //@Override
    public void showAllWeight(ArrayList<String> weights) {
        System.out.println("List of weights (in kg): ");
    }

    //@Override
    public void showWeightAdded(int weight) {
        System.out.println("This is your updated weight: " + weight + " kg");
    }
}
