package seedu.bigpp.component;

import seedu.bigpp.component.chassis.Chassis;
import seedu.bigpp.component.cpu.CPU;
import seedu.bigpp.component.cpucooler.CPUCooler;
import seedu.bigpp.component.gpu.GPU;
import seedu.bigpp.component.motherboard.Motherboard;
import seedu.bigpp.component.ram.RAM;
import seedu.bigpp.component.storage.Storage;
import seedu.bigpp.component.psu.PSU;

import java.util.ArrayList;

public class ComponentList<T> extends ArrayList<Component> {

    /*
     * Lists all components in the list. If the list is filtered, the indexes of the
     * components in the original list will be shown.
     * @return a string containing all the components in the list
     */
    public String getListString(ArrayList<Integer> componentIndexes, Boolean getDetails) {
        String outputString = "";

        if (componentIndexes.size() == 0) {
            for (int i = 0; i < super.size(); i++) {
                outputString += (i + 1) + "." + "\n" + super.get(i).toString(getDetails) + "\n" + "================\n";
            }

        } else {
            for (int i = 0; i < super.size(); i++) {
                outputString += componentIndexes.get(i).toString() + "." + "\n" + super.get(i)
                        .toString(getDetails) + "\n" + "================\n";
            }
        }

        return outputString;
    }

    //filter psu by efficiency. efficiency can be bronze, silver or gold
    public static ComponentList<?> filterByEfficiencyPsu(ComponentList<?> componentList, String efficiency,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            PSU psu = (PSU) componentList.get(i);
            if (psu.getEfficiency().toLowerCase().contains(efficiency)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter psu by power
    public static ComponentList<?> filterByPowerPsu(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            PSU psu = (PSU) componentList.get(i);
            if (psu.getPower() >= from && psu.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter storage by power
    public static ComponentList<?> filterByPowerStorage(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Storage storage = (Storage) componentList.get(i);
            if (storage.getPower() >= from && storage.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterBySizeStorage(ComponentList<?> componentList, int size,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Storage storage = (Storage) componentList.get(i);
            if (storage.getSize() == size) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByTypeStorage(ComponentList<?> componentList, String type,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Storage storage = (Storage) componentList.get(i);
            if (storage.getType().toLowerCase().contains(type)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByPowerRam(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            RAM ram = (RAM) componentList.get(i);
            if (ram.getPower() >= from && ram.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter ram by speed, speed could be 1600 or 3200
    public static ComponentList<?> filterBySpeed(ComponentList<?> componentList, int speed,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            RAM ram = (RAM) componentList.get(i);
            if (ram.getSpeed() == speed) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter ram by sticks, sticks could be 1 or 2
    public static ComponentList<?> filterBySticks(ComponentList<?> componentList, int sticks,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            RAM ram = (RAM) componentList.get(i);
            if (ram.getSticks() == sticks) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter ram by memory, memory could be 8, 16 or 32
    public static ComponentList<?> filterByMemory(ComponentList<?> componentList, int memory,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            RAM ram = (RAM) componentList.get(i);
            if (ram.getMemory() * ram.getSticks() == memory) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByPowerMotherboard(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Motherboard motherboard = (Motherboard) componentList.get(i);
            if (motherboard.getPower() >= from && motherboard.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterBySocketMotherboard(ComponentList<?> componentList, String socket,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Motherboard motherboard = (Motherboard) componentList.get(i);
            if (motherboard.getSocket().toLowerCase().equals(socket)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter motherboard by formfactor
    public static ComponentList<?> filterByFormFactorMotherboard(ComponentList<?> componentList, String formFactor,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Motherboard motherboard = (Motherboard) componentList.get(i);
            if (motherboard.getFormFactor().toLowerCase().equals(formFactor)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByPowerGpu(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            GPU gpu = (GPU) componentList.get(i);
            if (gpu.getPower() >= from && gpu.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByFormFactorGpu(ComponentList<?> componentList, String formFactor,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            GPU gpu = (GPU) componentList.get(i);
            if (gpu.getFormFactor().toLowerCase().contains(formFactor)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByPowerCpuCooler(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPUCooler cpuCooler = (CPUCooler) componentList.get(i);
            if (cpuCooler.getPower() >= from && cpuCooler.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter cpucooler by noise from to range int
    public static ComponentList<?> filterByNoise(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPUCooler cpuCooler = (CPUCooler) componentList.get(i);
            if (cpuCooler.getNoise() >= from && cpuCooler.getNoise() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    // filter cpucooler by rpm from and to range int
    public static ComponentList<?> filterByRpm(ComponentList<?> componentList, int from, int to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPUCooler cpuCooler = (CPUCooler) componentList.get(i);
            if (cpuCooler.getRpm() >= from && cpuCooler.getRpm() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterBySocketCpu(ComponentList<?> componentList, String socket,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPU cpu = (CPU) componentList.get(i);
            if (cpu.getSocket().toLowerCase().equals(socket)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByPowerCpu(ComponentList<?> componentList, float from, float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPU cpu = (CPU) componentList.get(i);
            if (cpu.getPower() >= from && cpu.getPower() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByBoostClock(ComponentList<?> componentList, Float from, Float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPU cpu = (CPU) componentList.get(i);
            if (cpu.getBoostClock() >= from && cpu.getBoostClock() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByBaseClock(ComponentList<?> componentList, Float from, Float to,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPU cpu = (CPU) componentList.get(i);
            if (cpu.getBaseClock() >= from && cpu.getBaseClock() <= to) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByThread(ComponentList<?> componentList, int thread,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPU cpu = (CPU) componentList.get(i);
            if (cpu.getThreads() == thread) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByCore(ComponentList<?> componentList, int core,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            CPU cpu = (CPU) componentList.get(i);
            if (cpu.getCores() == core) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    public static ComponentList<?> filterByFormFactorChassis(ComponentList<?> componentList, String formFactor,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            Chassis chassis = (Chassis) componentList.get(i);
            if (chassis.getFormFactor().toLowerCase().contains(formFactor)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    /*
     * Filters the list of components by name. Adds the index of the component in
     * the original list to the componentIndexes list.
     * @param componentList the list of components to be filtered
     */
    public static ComponentList<?> filterByName(ComponentList<?> componentList, String name,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            if (componentList.get(i).getName().toLowerCase().contains(name)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    /*
     * Filters the list of components by brand. Adds the index of the component in
     * the original list to the componentIndexes list.
     * @return componentList the list of components to be filtered
     */
    public static ComponentList<?> filterByBrand(ComponentList<?> componentList, String brand,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            if (componentList.get(i).getBrand().toLowerCase().contains(brand)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }

    /*
     * Filters the list of components by price. Adds the index of the component in
     * the original list to the componentIndexes list.
     * @return componentList the list of components to be filtered
     */
    public static ComponentList<?> filterByPrice(ComponentList<?> componentList, String priceFrom, String priceTo,
            ArrayList<Integer> componentIndexes) {
        ComponentList<?> filteredComponentList = new ComponentList<>();
        for (int i = 0; i <= componentList.size() - 1; i++) {
            if (componentList.get(i).getPrice() >= Float.parseFloat(priceFrom) && componentList.get(i)
                    .getPrice() <= Float.parseFloat(
                            priceTo)) {
                filteredComponentList.add(componentList.get(i));
                componentIndexes.add(i + 1);
            }
        }
        return filteredComponentList;
    }
}
