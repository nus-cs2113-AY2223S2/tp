package seedu.bigpp.component.psu;

import seedu.bigpp.component.Component;
import static seedu.bigpp.component.ComponentType.PSU_TYPE;

public class PSU extends Component {
    public static final String COMPONENT_STRING = PSU_TYPE;
    private String efficiency;
    private String formfactor;
    private float power;

    public PSU(String name, float price, String brand, String efficiency, String formfactor, float power) {
        super.setName(name);
        super.setPrice(price);
        super.setBrand(brand);
        this.efficiency = efficiency;
        this.formfactor = formfactor;
        this.power = power;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getFormFactor() {
        return formfactor;
    }

    public void setFormFactor(String formfactor) {
        this.formfactor = formfactor;
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
                    "\nEFFICIENCY: " + efficiency +
                    "\nFORMFACTOR: " + formfactor +
                    "\nPOWER: " + power;
        } else {
            return super.name;
        }
    }
}
