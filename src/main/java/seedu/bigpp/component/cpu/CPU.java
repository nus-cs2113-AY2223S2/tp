package seedu.bigpp.component.cpu;

import seedu.bigpp.component.Component;

public class CPU extends Component {
    public static final String COMPONENT_STRING = "cpu";
    private int cores;
    private int threads;
    private float baseClock;
    private float boostClock;
    private float power;
    private String socket;

    public CPU(String name, float price, int cores, int threads, float baseClock, float boostClock, float power,
            String brand, String socket) {
        super.setName(name);
        super.setBrand(brand);
        super.setPrice(price);
        this.cores = cores;
        this.threads = threads;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.power = power;
        this.socket = socket;
    }

    public int getCores() {
        return this.cores;
    }

    public int getThreads() {
        return this.threads;
    }

    public float getBaseClock() {
        return this.baseClock;
    }

    public float getBoostClock() {
        return this.boostClock;
    }

    public float getPower() {
        return this.power;
    }

    public String getSocket() {
        return this.socket;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public void setBaseClock(float baseClock) {
        this.baseClock = baseClock;
    }

    public void setBoostClock(float boostClock) {
        this.boostClock = boostClock;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return  "NAME: " + super.getName() +
                "\nBRAND: " + super.getBrand() +
                "\nPRICE: " + super.getPrice() +
                "\nCORES: " + cores +
                "\nTHREADS: " + threads +
                "\nBASECLOCK: " + baseClock +
                "\nBOOSTCLOCK: " + boostClock +
                "\nPOWER: " + power +
                "\nSOCKET: " + socket;
    }
}
