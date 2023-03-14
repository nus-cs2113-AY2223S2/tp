package seedu.bigpp.component.psu;

import seedu.bigpp.component.Component;

public class PSU extends Component {
    public static final String COMPONENT_STRING = "psu";
    private String efficiency;
    private String formFactor;
    private float power;

    public PSU(String name, float price, String brand, String efficiency, String formFactor, float power) {
        super.setName(name);
        super.setPrice(price);
        super.setBrand(brand);
        this.efficiency = efficiency;
        this.formFactor = formFactor;
        this.power = power;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "NAME: " + super.getName() +
                "\nBRAND: " + super.getBrand() +
                "\nPRICE: " + super.getPrice() +
                "\nEFFICIENCY: " + efficiency +
                "\nFORMFACTOR: " + formFactor +
                "\nPOWER: " + power;
    }
}
