package seedu.bigpp.component.gpu;

import seedu.bigpp.component.Component;
import static seedu.bigpp.component.ComponentType.GPU_TYPE;

public class GPU extends Component {
    public static final String COMPONENT_STRING = GPU_TYPE;
    private float power;
    private String formfactor;

    public GPU(String name, String brand, float price, float power, String formfactor) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.power = power;
        this.formfactor = formfactor;
    }

    public float getPower() {
        return power;
    }

    public String getFormFactor() {
        return formfactor;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setFormFactor(String formfactor) {
        this.formfactor = formfactor;
    }

    @Override
    public String toString(Boolean getDetails) {
        if (getDetails) {
            return "NAME: " + super.getName() +
                    "\nBRAND: " + super.getBrand() +
                    "\nPRICE: " + super.getPrice() +
                    "\nPOWER: " + power +
                    "\nFORMFACTOR: " + formfactor;
        } else {
            return super.name;
        }
    }
}
