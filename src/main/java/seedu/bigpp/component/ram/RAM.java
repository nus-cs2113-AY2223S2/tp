package seedu.bigpp.component.ram;

import seedu.bigpp.component.Component;
import static seedu.bigpp.component.ComponentType.RAM_TYPE;

public class RAM extends Component {
    public static final String COMPONENT_STRING = RAM_TYPE;
    private int memory;
    private int sticks;
    private int speed;
    private float power;

    public RAM(String name, String brand, float price, int memory, int sticks, int speed, float power) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.memory = memory;
        this.sticks = sticks;
        this.speed = speed;
        this.power = power;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getSticks() {
        return sticks;
    }

    public void setSticks(int sticks) {
        this.sticks = sticks;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
                    "\nMEMORY: " + memory +
                    "\nSTICKS: " + sticks +
                    "\nSPEED: " + speed +
                    "\nPOWER: " + power;
        } else {
            return super.name;
        }
    }
}
