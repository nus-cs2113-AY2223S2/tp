package seedu.bigpp.component.storage;

import seedu.bigpp.component.Component;
import static seedu.bigpp.component.ComponentType.STORAGE_TYPE;

public class Storage extends Component {
    public static final String COMPONENT_STRING = STORAGE_TYPE;
    private String type;
    private int size; // in GB
    private float power;

    public Storage(String name, String brand, float price, String type, int size, float power) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.type = type;
        this.size = size;
        this.power = power;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    @Override
    public String toString(Boolean getDetails) {
        if (getDetails) {
            return "NAME: " + super.getName() +
                    "\nBRAND: " + super.getBrand() +
                    "\nPRICE: " + super.getPrice() +
                    "\nTYPE: " + type +
                    "\nSIZE: " + size +
                    "\nPOWER: " + power;
        } else {
            return super.name;
        }
    }
}
