package seedu.bigpp.pc;

import java.util.ArrayList;
import static seedu.bigpp.ui.UI.out;

public class PCList extends ArrayList<PC> {

    private static Boolean isFiltered;
    private static String priceFrom;
    private static String priceTo;
    private static String name;
    private static String built;

    public PCList() {
        isFiltered = false;
        built = "";
        priceFrom = "";
        priceTo = "";
        name = "";

    }

    public void printPcList() {
        if (getIsFiltered()) {
            printAltPcList();
        } else {
            if (this.size() > 0) {
                out.println("Here is the list of PC Builds:");
                for (int i = 0; i < this.size(); i++) {
                    out.println("    "  + (i + 1) + "." + this.get(i));
                }
            } else {
                out.println("List currently empty!");
            }
        }
    }

    public void printAltPcList() {
        int pcCount = 0;
        if (this.size() > 0) {
            out.println("Here is the filtered list of PC Builds:");
            for (int i = 0; i < this.size(); i++) {
                if (filterByBuilt(this.get(i), getIsBuilt()) && filterByPrice(this.get(i),
                        getPriceFrom(), getPriceTo()) && filterByName(this.get(i), getName())) {
                    out.println("    " + (i + 1) + "." + this.get(i));
                    pcCount += 1;
                }
            }
            if (pcCount == 0) {
                out.println("Filtered list is empty");
            }
        } else {
            out.println("List currently empty!");
        }
    }

    public static Boolean getIsFiltered() {
        return isFiltered;
    }

    public static String getIsBuilt() {
        return built;
    }

    public static String getName() {
        return name;
    }

    public static String getPriceFrom() {
        return priceFrom;
    }

    public static String getPriceTo() {
        return priceTo;
    }

    public static void setFilterTrue() {
        isFiltered = true;
    }

    public static void setFilterFalse() {
        isFiltered = false;
    }

    public static void setBuilt(String input) {
        built = input;
    }

    public static void setName(String input) {
        name = input;
    }

    public static void setPriceFrom(String input) {
        priceFrom = input;
    }

    public static void setPriceTo(String input) {
        priceTo = input;
    }

    public static Boolean filterByPrice(PC pc, String priceFrom, String priceTo) {
        if (getPriceFrom() == "") {
            return true;
        }
        if (pc.getCost() >= Integer.parseInt(priceFrom) && pc.getCost() <= Integer.parseInt(priceTo)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean filterByName(PC pc, String name) {
        if (name == "") {
            return true;
        }
        if (pc.getName().toLowerCase().contains(name)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean filterByBuilt(PC pc, String built) {
        if (built == "") {
            return true;
        }
        Boolean isBuilt = null;
        switch (built) {
        case "complete":
            isBuilt = true;
            break;
        case "incomplete":
            isBuilt = false;
            break;
        default:
            break;
        }
        if (pc.isComplete() == isBuilt) {
            return true;
        } else {
            return false;
        }
    }
}
