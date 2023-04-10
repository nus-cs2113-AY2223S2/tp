package seedu.bigpp.component.motherboard;

import seedu.bigpp.component.Component;
import static seedu.bigpp.component.ComponentType.MOTHERBOARD_TYPE;

public class Motherboard extends Component {
    public static final String COMPONENT_STRING = MOTHERBOARD_TYPE;
    private String formfactor;
    private String socket;
    private float power;

    public Motherboard(String name, String brand, float price, String formfactor, String socket, float power) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.formfactor = formfactor;
        this.socket = socket;
        this.power = power;
    }

    public String getFormFactor() {
        return formfactor;
    }

    public void setFormFactor(String formfactor) {
        this.formfactor = formfactor;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
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
                    "\nFORMFACTOR: " + formfactor +
                    "\nSOCKET: " + socket +
                    "\nPOWER: " + power;
        } else {
            return super.name;
        }
    }
}
