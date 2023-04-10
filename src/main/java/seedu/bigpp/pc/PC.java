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
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;
import static seedu.bigpp.component.ComponentType.CHASSIS_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_COOLER_TYPE;
import static seedu.bigpp.component.ComponentType.GPU_TYPE;
import static seedu.bigpp.component.ComponentType.MOTHERBOARD_TYPE;
import static seedu.bigpp.component.ComponentType.PSU_TYPE;
import static seedu.bigpp.component.ComponentType.RAM_TYPE;
import static seedu.bigpp.component.ComponentType.STORAGE_TYPE;

import java.util.ArrayList;

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

    private int budget = -1;

    public PC(String name, Boolean isPrebuilt) {
        this.name = name;
        this.isPrebuilt = isPrebuilt;
    }

    public PC(PC pcCopy) {
        this.name = pcCopy.getName() + " (copy)";
        this.isPrebuilt = false;
        this.cpu = pcCopy.getCpu();
        this.cpuCooler = pcCopy.getCpuCooler();
        this.gpu = pcCopy.getGpu();
        this.motherboard = pcCopy.getMotherboard();
        this.ram = pcCopy.getRam();
        this.storage = pcCopy.getStorage();
        this.psu = pcCopy.getPsu();
        this.chassis = pcCopy.getChassis();
        this.budget = pcCopy.getBudget();
    }

    public String setChassis(Chassis chassis) throws PPException {
        if (chassis == null) {
            this.chassis = null;
            return "Chassis removed";
        }

        // Check if chassis is larger than all other components, else throw exception
        // with all incompatible components
        ArrayList<String> incompatibleComponents = new ArrayList<>();

        int chassisSize = FormFactorEnum.getFormFactorFromString(chassis.getFormFactor());

        if (gpu != null && FormFactorEnum.getFormFactorFromString(gpu.getFormFactor()) > chassisSize) {
            incompatibleComponents.add(GPU_TYPE);
        }
        if (motherboard != null && FormFactorEnum.getFormFactorFromString(motherboard.getFormFactor()) > chassisSize) {
            incompatibleComponents.add(MOTHERBOARD_TYPE);
        }
        if (psu != null && FormFactorEnum.getFormFactorFromString(psu.getFormFactor()) > chassisSize) {
            incompatibleComponents.add(PSU_TYPE);
        }
        if (incompatibleComponents.size() > 0) {
            throw new PPException("Chassis is too small for the following components:\n"
                    + UI.listToString(incompatibleComponents));
        }
        this.chassis = chassis;
        return "Chassis added: " + chassis.getName();
    }

    public String setCpu(CPU cpu) throws PPException {
        if (cpu == null) {
            this.cpu = null;
            return "CPU removed";
        }

        if (motherboard != null) {
            // Check if cpu socket is compatible with motherboard socket
            if (!cpu.getSocket().equals(motherboard.getSocket())) {
                throw new PPException("CPU socket is not compatible with current motherboard socket\n"
                        + "Please choose another cpu or change your motherboard");
            }
        }

        // if PSU is added, check if the total power draw of all components plus new
        // component exceeds the PSU's max power
        if (psu != null) {
            float totalPower = getTotalPowerConsumption() + cpu.getPower();

            // if pc already has a cpu, remove the power consumption of the old cpu
            if (this.cpu != null) {
                totalPower -= this.cpu.getPower();
            }

            if (totalPower > psu.getPower() * 0.8) {
                throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                        + "Please choose another cpu or add a higher wattage PSU");
            }
        }

        this.cpu = cpu;
        return "CPU added: " + cpu.getName();
    }

    public String setCpuCooler(CPUCooler cpuCooler) throws PPException {
        if (cpuCooler == null) {
            this.cpuCooler = null;
            return "CPU Cooler removed";
        }

        // if PSU is added, check if the total power draw of all components plus new
        // component exceeds the PSU's max power
        if (psu != null) {
            float totalPower = getTotalPowerConsumption() + cpuCooler.getPower();

            // if pc already has a cpu, remove the power consumption of the old cpu
            if (this.cpuCooler != null) {
                totalPower -= this.cpuCooler.getPower();
            }

            if (totalPower > psu.getPower() * 0.8) {
                throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                        + "Please choose another cpuCooler or add a higher wattage PSU");
            }
        }

        this.cpuCooler = cpuCooler;
        return "CPU Cooler added: " + cpuCooler.getName();
    }

    public String setGpu(GPU gpu) throws PPException {
        if (gpu == null) {
            this.gpu = null;
            return "GPU removed";
        }

        if (chassis != null && FormFactorEnum.getFormFactorFromString(gpu.getFormFactor()) > FormFactorEnum
                .getFormFactorFromString(chassis.getFormFactor())) {
            throw new PPException("GPU is too large for current chassis\n"
                    + "Please choose another gpu or change your chassis");
        }

        // if PSU is added, check if the total power draw of all components plus new
        // component exceeds the PSU's max power
        if (psu != null) {
            float totalPower = getTotalPowerConsumption() + gpu.getPower();

            // if pc already has a cpu, remove the power consumption of the old cpu
            if (this.gpu != null) {
                totalPower -= this.gpu.getPower();
            }

            if (totalPower > psu.getPower() * 0.8) {
                throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                        + "Please choose another gpu or add a higher wattage PSU");
            }
        }

        this.gpu = gpu;
        return "GPU added: " + gpu.getName();
    }

    public String setMotherboard(Motherboard motherboard) throws PPException {
        if (motherboard == null) {
            this.motherboard = null;
            return "Motherboard removed";
        }

        if (cpu != null) {
            // Check if cpu socket is compatible with motherboard socket
            if (!cpu.getSocket().equals(motherboard.getSocket())) {
                throw new PPException("Motherboard socket is not compatible with current cpu socket\n"
                        + "Please choose another motherboard or change your cpu");
            }
        }

        if (chassis != null && FormFactorEnum.getFormFactorFromString(motherboard.getFormFactor()) > FormFactorEnum
                .getFormFactorFromString(chassis.getFormFactor())) {
            throw new PPException("Motherboard is too large for current chassis\n"
                    + "Please choose another motherboard or change your chassis");
        }

        // if PSU is added, check if the total power draw of all components plus new
        // component exceeds the PSU's max power

        if (psu != null) {
            float totalPower = getTotalPowerConsumption() + motherboard.getPower();

            // if pc already has a cpu, remove the power consumption of the old cpu
            if (this.motherboard != null) {
                totalPower -= this.motherboard.getPower();
            }

            if (totalPower > psu.getPower() * 0.8) {
                throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                        + "Please choose another motherboard or add a higher wattage PSU");
            }
        }

        this.motherboard = motherboard;
        return "Motherboard added: " + motherboard.getName();
    }

    public String setRam(RAM ram) throws PPException {
        if (ram == null) {
            this.ram = null;
            return "RAM removed";
        }

        // if PSU is added, check if the total power draw of all components plus new
        // component exceeds the PSU's max power
        if (psu != null) {
            float totalPower = getTotalPowerConsumption() + ram.getPower();

            // if pc already has a cpu, remove the power consumption of the old cpu
            if (this.ram != null) {
                totalPower -= this.ram.getPower();
            }

            if (totalPower > psu.getPower() * 0.8) {
                throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                        + "Please choose another ram or add a higher wattage PSU");
            }
        }

        this.ram = ram;
        return "RAM added: " + ram.getName();
    }

    public String setStorage(Storage storage) throws PPException {
        if (storage == null) {
            this.storage = null;
            return "Storage removed";
        }

        // if PSU is added, check if the total power draw of all components plus new
        // component exceeds the PSU's max power
        if (psu != null) {
            float totalPower = getTotalPowerConsumption() + storage.getPower();

            // if pc already has a cpu, remove the power consumption of the old cpu
            if (this.storage != null) {
                totalPower -= this.storage.getPower();
            }

            if (totalPower > psu.getPower() * 0.8) {
                throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                        + "Please choose another storage or add a higher wattage PSU");
            }
        }

        this.storage = storage;
        return "Storage added: " + storage.getName();
    }

    public String setPsu(PSU psu) throws PPException {
        if (psu == null) {
            this.psu = null;
            return "PSU removed";
        }

        // check if total power draw of all components exceeds the PSU's max power
        if (getTotalPowerConsumption() > psu.getPower() * 0.8) {
            throw new PPException("Total power draw exceeds 80% of PSU's max power\n"
                    + "Please choose another psu or remove some components");
        }

        this.psu = psu;
        return "PSU added: " + psu.getName();
    }

    public String setComponent(Component component) throws PPException {
        if (component instanceof CPU) {
            return setCpu((CPU) component);
        } else if (component instanceof CPUCooler) {
            return setCpuCooler((CPUCooler) component);
        } else if (component instanceof GPU) {
            return setGpu((GPU) component);
        } else if (component instanceof Motherboard) {
            return setMotherboard((Motherboard) component);
        } else if (component instanceof RAM) {
            return setRam((RAM) component);
        } else if (component instanceof Storage) {
            return setStorage((Storage) component);
        } else if (component instanceof PSU) {
            return setPsu((PSU) component);
        } else if (component instanceof Chassis) {
            return setChassis((Chassis) component);
        } else {
            throw new PPException("Invalid component type");
        }
    }

    public String setNullComponent(String componentType) throws PPException {
        if (componentType.equals(CPU_TYPE)) {
            return setCpu(null);
        } else if (componentType.equals(CPU_COOLER_TYPE)) {
            return setCpuCooler(null);
        } else if (componentType.equals(GPU_TYPE)) {
            return setGpu(null);
        } else if (componentType.equals(MOTHERBOARD_TYPE)) {
            return setMotherboard(null);
        } else if (componentType.equals(RAM_TYPE)) {
            return setRam(null);
        } else if (componentType.equals(STORAGE_TYPE)) {
            return setStorage(null);
        } else if (componentType.equals(PSU_TYPE)) {
            return setPsu(null);
        } else if (componentType.equals(CHASSIS_TYPE)) {
            return setChassis(null);
        } else {
            throw new PPException("Invalid component type");
        }
    }

    public CPU getCpu() {
        return cpu;
    }

    public CPUCooler getCpuCooler() {
        return cpuCooler;
    }

    public GPU getGpu() {
        return gpu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public RAM getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public PSU getPsu() {
        return psu;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public Component getComponent(String componentName) {
        if (componentName.equals(CPU_TYPE)) {
            return getCpu();
        } else if (componentName.equals(CPU_COOLER_TYPE)) {
            return getCpuCooler();
        } else if (componentName.equals(GPU_TYPE)) {
            return getGpu();
        } else if (componentName.equals(MOTHERBOARD_TYPE)) {
            return getMotherboard();
        } else if (componentName.equals(RAM_TYPE)) {
            return getRam();
        } else if (componentName.equals(STORAGE_TYPE)) {
            return getStorage();
        } else if (componentName.equals(PSU_TYPE)) {
            return getPsu();
        } else if (componentName.equals(CHASSIS_TYPE)) {
            return getChassis();
        } else {
            return null;
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

    public String getBudgetString() {
        return (budget == -1) ? "infinite" : "$" + UI.moneyDecimalFormat.format(budget);
    }

    /**
     * Gets the cost of each of the components in the PC and returns the total.
     * @return totalCost
     */
    public float getCost() {
        float totalCost = 0.00f;
        Component[] components = { chassis, cpu, cpuCooler, gpu, motherboard, ram, storage, psu };
        for (Component component : components) {
            if (component != null) {
                totalCost += component.getPrice();
            }
        }
        // give price to 2 dp
        totalCost = Float.parseFloat(UI.moneyDecimalFormat.format(totalCost));
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
     * Gets the power consumption of each of the components in the PC and returns
     * the total.
     * @return the total power consumption of the PC
     */
    public float getTotalPowerConsumption() {
        float totalPowerConsumption = 0.00f;
        if (cpu != null) {
            totalPowerConsumption += cpu.getPower();
        }
        if (gpu != null) {
            totalPowerConsumption += gpu.getPower();
        }
        if (ram != null) {
            totalPowerConsumption += ram.getPower();
        }
        if (storage != null) {
            totalPowerConsumption += storage.getPower();
        }
        if (motherboard != null) {
            totalPowerConsumption += motherboard.getPower();
        }
        if (cpuCooler != null) {
            totalPowerConsumption += cpuCooler.getPower();
        }

        // give power consumption to 2 dp
        totalPowerConsumption = Float.parseFloat(UI.moneyDecimalFormat.format(totalPowerConsumption));
        return totalPowerConsumption;
    }

    /**
     * Method to check if the PC is complete.
     * @return true if all components are not null, false otherwise.
     */
    public boolean isComplete() {
        if (cpu == null || gpu == null || motherboard == null || ram == null || storage == null || psu == null
                || chassis == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method to print all the components of the PC.
     */
    public String viewComponents() {
        String componentString = "";

        componentString += this.toString() + "\n";
        if (psu == null) {
            componentString += ("Power Consumption: " + this.getTotalPowerConsumption() + "W/" + "0W\n");
        } else {
            componentString += ("Power Consumption: " + this.getTotalPowerConsumption() + "W/" + psu.getPower()
                    + "W\n");
        }
        componentString += ("Components:" + '\n');

        String[] componentNames = { "CPU        :", "CPU Cooler :", "GPU        :", "Motherboard:", "RAM        :",
            "Storage    :", "PSU        :", "Chassis    :" };

        Component[] components = { cpu, cpuCooler, gpu, motherboard, ram, storage, psu, chassis };

        int index = 0;
        for (Component component : components) {
            if (component != null) {
                // out.println(componentNames[index] + component.getName());
                componentString += (componentNames[index] + " " + component.getName() + '\n');
            } else {
                // out.println(componentNames[index] + ": - Not Selected -");
                componentString += (componentNames[index] + " - Not Selected -" + '\n');
            }
            index++;
        }
        return componentString;
    }

    /**
     * Method to print the PC's name and cost and build status.
     */
    @Override
    public String toString() {
        return buildType(isPrebuilt) + " [" + name + "]" + " - $" + getCost() + "/" + getBudgetString() + " - "
                + (isComplete() ? "Complete" : "Incomplete");
    }
}
