package seedu.bigpp.component.motherboard;

import seedu.bigpp.component.Component;

public class Motherboard extends Component {
    public static final String COMPONENT_STRING = "motherboard";
    private String formFactor;
    private String socket;
    private float power;

    public Motherboard(String name, String brand, float price, String formFactor, String socket, float power) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.formFactor = formFactor;
        this.socket = socket;
        this.power = power;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
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
    public String toString() {
        return "NAME: " + super.getName() +
                "\nBRAND: " + super.getBrand() +
                "\nPRICE: " + super.getPrice() +
                "\nFORMFACTOR: " + formFactor +
                "\nSOCKET: " + socket +
                "\nPOWER: " + power;
    }

}
