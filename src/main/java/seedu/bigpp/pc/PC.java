package seedu.bigpp.pc;

import seedu.bigpp.component.Component;
import seedu.bigpp.component.chassis.Chassis;
import seedu.bigpp.component.cpu.CPU;
import seedu.bigpp.component.cpucooler.CPUCooler;
import seedu.bigpp.component.gpu.GPU;
import seedu.bigpp.component.motherboard.Motherboard;
import seedu.bigpp.component.psu.PSU;
import seedu.bigpp.component.ram.RAM;
import seedu.bigpp.component.storage.Storage;

import java.text.DecimalFormat;

public class PC {
    private String name;
    private Boolean isPrebuilt;
    private CPU cpu = null;
    private CPUCooler cpuCooler = null;
    private GPU gpu = null;
    private Motherboard motherboard = null;
    private RAM ram = null;
    private Storage storage = null;
    private PSU psu = null;
    private Chassis chassis = null;

    private int budget;

    public PC(String name, Boolean isPrebuilt) {
        this.name = name;
        // this.cpu = new CPU();
        this.isPrebuilt = isPrebuilt;
    }

    public void setCPU(CPU cpu) {
        this.cpu = cpu;
    }

    public void setCpuCooler(CPUCooler cpuCooler) {
        this.cpuCooler = cpuCooler;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setPsu(PSU psu) {
        this.psu = psu;
    }

    public void setComponent(Component component) {
        if (component instanceof CPU) {
            this.cpu = (CPU) component;
        } else if (component instanceof CPUCooler) {
            this.cpuCooler = (CPUCooler) component;
        } else if (component instanceof GPU) {
            this.gpu = (GPU) component;
        } else if (component instanceof Motherboard) {
            this.motherboard = (Motherboard) component;
        } else if (component instanceof RAM) {
            this.ram = (RAM) component;
        } else if (component instanceof Storage) {
            this.storage = (Storage) component;
        } else if (component instanceof PSU) {
            this.psu = (PSU) component;
        } else if (component instanceof Chassis) {
            this.chassis = (Chassis) component;
        }
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public boolean getIsPreBuilt() {
        return isPrebuilt;
    }
 
    public int getBudget() {
        return budget;
    }

    /**
     * Gets the cost of each of the components in the PC and returns the total.
     * @return totalCost
     */
    public float getCost() {
        float totalCost = 0.00f;
        Component[] components = { cpu, cpuCooler, gpu, motherboard, ram, storage, psu };
        for (Component component : components) {
            if (component != null) {
                totalCost += component.getPrice();
            }
        }
        //give price to 2 dp
        DecimalFormat df = new DecimalFormat("#.00");
        totalCost = Float.parseFloat(df.format(totalCost));
        return totalCost;
    }
    

    public String buildType(Boolean isPrebuilt) {
        if (isPrebuilt) {
            return "Prebuilt-PC:";
        } else {
            return "Custom-PC:";
        }
    }

    /**
     * Method to print all the components of the PC.
     */
    public String viewComponents() {
        String componentString = "";
        componentString += (buildType(isPrebuilt) + " [" + name + "]" + " - $" + getCost() + '\n');
        componentString += ("Components:" + '\n');
        String[] componentNames = { "CPU        :", "CPU Cooler :", "GPU        :", "Motherboard:", "RAM        :",
            "Storage    :", "PSU        :", "Chassis    :" };
        Component[] components = { cpu, cpuCooler, gpu, motherboard, ram, storage, psu, chassis };
        int index = 0;
        for (Component component : components) {
            if (component != null) {
                //                out.println(componentNames[index] + component.getName());
                componentString += (componentNames[index] + " " + component.getName() + '\n');
            } else {
                //                out.println(componentNames[index] + ": - Not Selected -");
                componentString += (componentNames[index] + " - Not Selected -" + '\n');
            }
            index++;
        }
        return componentString;
    }

    @Override
    public String toString() {
        return buildType(isPrebuilt) + " [" + name + "]" + " - $" + getCost();
    }
}
