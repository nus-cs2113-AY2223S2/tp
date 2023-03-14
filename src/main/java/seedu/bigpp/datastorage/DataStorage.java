package seedu.bigpp.datastorage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import seedu.bigpp.component.chassis.Chassis;
import seedu.bigpp.component.chassis.ChassisList;
import seedu.bigpp.component.cpu.CPU;
import seedu.bigpp.component.cpu.CPUList;
import seedu.bigpp.component.cpucooler.CPUCooler;
import seedu.bigpp.component.cpucooler.CPUCoolerList;
import seedu.bigpp.component.gpu.GPU;
import seedu.bigpp.component.gpu.GPUList;
import seedu.bigpp.component.motherboard.Motherboard;
import seedu.bigpp.component.motherboard.MotherboardList;
import seedu.bigpp.component.psu.PSU;
import seedu.bigpp.component.psu.PSUList;
import seedu.bigpp.component.ram.RAM;
import seedu.bigpp.component.ram.RAMList;
import seedu.bigpp.component.storage.Storage;
import seedu.bigpp.component.storage.StorageList;

public abstract class DataStorage {
    public static Map<String, ArrayList> stringToComponentListMap = new HashMap<String, ArrayList>();
    private static final String CHASSIS_PATH = "chassis.json";
    private static final String CPU_PATH = "cpu.json";
    private static final String CPU_COOLER_PATH = "cpucooler.json";
    private static final String GPU_PATH = "gpu.json";
    private static final String MOTHERBOARD_PATH = "motherboard.json";
    private static final String PSU_PATH = "psu.json";
    private static final String RAM_PATH = "ram.json";
    private static final String STORAGE_PATH = "storage.json";

    //private static final String PREBUILT_PATH = "prebuilt.json";

    private static final Gson GSON = new Gson();

    /**
     * Loads all the components from the json files.
     */
    public static void loadAll() {
        initStringToComponentListMap();
        loadChassis();
        loadCPU();
        loadCPUCooler();
        loadGPU();
        loadMotherboard();
        loadPSU();
        loadRAM();
        loadStorage();
    }

    public static void initStringToComponentListMap() {
        stringToComponentListMap.put("cpu", new CPUList());
        stringToComponentListMap.put("gpu", new GPUList());
        stringToComponentListMap.put("chassis", new ChassisList());
        stringToComponentListMap.put("cpu-cooler", new CPUCoolerList());
        stringToComponentListMap.put("psu", new PSUList());
        stringToComponentListMap.put("ram", new RAMList());
        stringToComponentListMap.put("storage", new StorageList());
        stringToComponentListMap.put("motherboard", new MotherboardList());
    }

    /**
     * Loads all the chassis from the json file into ChassisList.
     */
    public static void loadChassis() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(CHASSIS_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, Chassis> chassisMap = GSON.fromJson(json, new TypeToken<Map<String, Chassis>>() {
        }.getType());

        chassisMap.forEach((name, chassis) -> chassisMap.get(name).setName(name));

        // Add each chassis to the chassis list
        chassisMap.forEach((name, chassis) -> stringToComponentListMap.get("chassis").add(chassis));
    }

    /**
     * Loads all the CPUs from the json file into CPUList.
     */
    public static void loadCPU() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(CPU_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, CPU> cpuMap = GSON.fromJson(json, new TypeToken<Map<String, CPU>>() {
        }.getType());

        cpuMap.forEach((name, cpu) -> cpuMap.get(name).setName(name));

        // Add each cpu to the cpu list
        cpuMap.forEach((name, cpu) -> stringToComponentListMap.get("cpu").add(cpu));
    }

    /**
     * Loads all the CPU coolers from the json file into CPUCoolerList.
     */
    public static void loadCPUCooler() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(CPU_COOLER_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, CPUCooler> cpuCoolerMap = GSON.fromJson(json, new TypeToken<Map<String, CPUCooler>>() {
        }.getType());

        cpuCoolerMap.forEach((name, cpuCooler) -> cpuCoolerMap.get(name).setName(name));

        // Add each cpu cooler to the cpu cooler list
        cpuCoolerMap.forEach((name, cpuCooler) -> stringToComponentListMap.get("cpu-cooler").add(cpuCooler));
    }

    /**
     * Loads all the GPUs from the json file into GPUList.
     */
    public static void loadGPU() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(GPU_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, GPU> gpuMap = GSON.fromJson(json, new TypeToken<Map<String, GPU>>() {
        }.getType());

        gpuMap.forEach((name, gpu) -> gpuMap.get(name).setName(name));

        // Add each gpu to the gpu list
        gpuMap.forEach((name, gpu) -> stringToComponentListMap.get("gpu").add(gpu));
    }

    /**
     * Loads all the motherboards from the json file into MotherboardList.
     */
    public static void loadMotherboard() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(MOTHERBOARD_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, Motherboard> motherboardMap = GSON.fromJson(json, new TypeToken<Map<String, Motherboard>>() {
        }.getType());

        motherboardMap.forEach((name, motherboard) -> motherboardMap.get(name).setName(name));

        // Add each motherboard to the motherboard list
        motherboardMap.forEach((name, motherboard) -> stringToComponentListMap.get("motherboard").add(motherboard));
    }

    /**
     * Loads all the PSUs from the json file into PSUList.
     */
    public static void loadPSU() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(PSU_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, PSU> psuMap = GSON.fromJson(json, new TypeToken<Map<String, PSU>>() {
        }.getType());

        psuMap.forEach((name, psu) -> psuMap.get(name).setName(name));

        // Add each psu to the psu list
        psuMap.forEach((name, psu) -> stringToComponentListMap.get("psu").add(psu));
    }

    /**
     * Loads all the RAMs from the json file into RAMList.
     */
    public static void loadRAM() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(RAM_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, RAM> ramMap = GSON.fromJson(json, new TypeToken<Map<String, RAM>>() {
        }.getType());

        ramMap.forEach((name, ram) -> ramMap.get(name).setName(name));

        // Add each ram to the ram list
        ramMap.forEach((name, ram) -> stringToComponentListMap.get("ram").add(ram));
    }

    /**
     * Loads all the storage devices from the json file into StorageList.
     */
    public static void loadStorage() {
        ClassLoader classLoader = DataStorage.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(STORAGE_PATH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // read entire file into string
        String json = reader.lines().reduce("", (accumulator, actual) -> accumulator + actual);

        Map<String, Storage> storageMap = GSON.fromJson(json, new TypeToken<Map<String, Storage>>() {
        }.getType());

        storageMap.forEach((name, storage) -> storageMap.get(name).setName(name));

        // Add each ram to the ram list
        storageMap.forEach((name, storage) -> stringToComponentListMap.get("storage").add(storage));
    }

}
