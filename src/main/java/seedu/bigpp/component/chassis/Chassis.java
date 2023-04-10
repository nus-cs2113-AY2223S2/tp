package seedu.bigpp.component.chassis;

import seedu.bigpp.component.Component;
import static seedu.bigpp.component.ComponentType.CHASSIS_TYPE;

public class Chassis extends Component {
    public static final String COMPONENT_STRING = CHASSIS_TYPE;

    private String formfactor;

    public Chassis(String name, String brand, float price, String formfactor) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.formfactor = formfactor;
    }

    public String getFormFactor() {
        return formfactor;
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
                    "\nFORMFACTOR: " + formfactor;
        } else {
            return super.name;
        }
    }
}
