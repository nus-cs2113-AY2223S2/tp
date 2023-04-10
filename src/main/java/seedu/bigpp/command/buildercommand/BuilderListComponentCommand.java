package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;

import seedu.bigpp.ui.UI;
import seedu.bigpp.ui.UIState;
import seedu.bigpp.component.ComponentList;

import static seedu.bigpp.component.ComponentType.CPU_TYPE;
import static seedu.bigpp.component.ComponentType.GPU_TYPE;
import static seedu.bigpp.component.ComponentType.RAM_TYPE;
import static seedu.bigpp.component.ComponentType.STORAGE_TYPE;
import static seedu.bigpp.component.ComponentType.MOTHERBOARD_TYPE;
import static seedu.bigpp.component.ComponentType.CHASSIS_TYPE;
import static seedu.bigpp.component.ComponentType.CPU_COOLER_TYPE;
import static seedu.bigpp.component.ComponentType.PSU_TYPE;
import java.util.ArrayList;
import java.util.Arrays;

public class BuilderListComponentCommand extends Command {

    private static final String NAME_FLAG = "-name";
    private static final String PRICE_FLAG = "-price";
    private static final String BRAND_FLAG = "-brand";
    private static final String CORE_FLAG = "-core";
    private static final String THREAD_FLAG = "-thread";
    private static final String BASE_CLOCK_FLAG = "-baseclock";
    private static final String BOOST_CLOCK_FLAG = "-boostclock";
    private static final String POWER_FLAG = "-power";
    private static final String SOCKET_FLAG = "-socket";
    private static final String SIZE_FLAG = "-size";
    private static final String RPM_FLAG = "-rpm";
    private static final String NOISE_FLAG = "-noise";
    private static final String MEMORY_FLAG = "-memory";
    private static final String STICKS_FLAG = "-sticks";
    private static final String SPEED_FLAG = "-speed";
    private static final String TYPE_FLAG = "-type";
    private static final String FORM_FACTOR_FLAG = "-formfactor";
    private static final String EFFICIENCY_FLAG = "-efficiency";
    private static final String DETAILS_FLAG = "-details";

    public BuilderListComponentCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Lists all components of a given component type including optional flags
     * that would filter the list of components. Such as filtering for name, price
     * and brand.
     * @return a string containing all components of a given component type
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {

        assert UI.getUiState() == UIState.PCBUILDER : "UI state should be PCBUILDER";

        String userInputString = getArguments();
        userInputString = userInputString.toLowerCase();

        String[] userInputStringArray = userInputString.split(" ");
        String componentType = userInputStringArray[0];

        if (userInputString.equals("")) {
            throw new PPException("Please enter a component");
        }
        if (!dataStorage.stringToComponentListMap.containsKey(componentType)) {
            throw new PPException(
                    "Please select a valid component (cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }

        ComponentList<?> componentList = dataStorage.stringToComponentListMap.get(componentType);
        ArrayList<Integer> componentIndexes = new ArrayList<Integer>();
        ArrayList<String> flagsArray = new ArrayList<String>();

        Boolean getDetails = false;

        if (userInputStringArray.length > 1) {
            String[] flagAndDescriptionArray = Arrays.copyOfRange(userInputStringArray, 1, userInputStringArray.length);
            if (hasFlag(userInputStringArray)) {
                if (containsFlag(flagAndDescriptionArray, NAME_FLAG)) {
                    componentList = handleNameFlag(componentList, flagsArray, flagAndDescriptionArray,
                            componentIndexes);
                }
                if (containsFlag(flagAndDescriptionArray, BRAND_FLAG)) {
                    componentList = handleBrandFlag(componentList, flagsArray, flagAndDescriptionArray,
                            componentIndexes);
                }
                if (containsFlag(flagAndDescriptionArray, PRICE_FLAG)) {
                    componentList = handlePriceFlag(userInputString, componentList, flagsArray, flagAndDescriptionArray,
                            componentIndexes);
                }
                if (containsFlag(flagAndDescriptionArray, DETAILS_FLAG)) {
                    getDetails = true;
                }

                switch (componentType) {
                case CHASSIS_TYPE:
                    if (containsFlag(flagAndDescriptionArray, FORM_FACTOR_FLAG)) {
                        componentList = handleFormFactorFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray,
                                componentIndexes, componentType);
                    }
                    break;
                case CPU_TYPE:
                    // handle core, thread, baseclock, boostclock, power and socket flag
                    if (containsFlag(flagAndDescriptionArray, CORE_FLAG)) {
                        componentList = handleCoreFlag(componentList, flagsArray, flagAndDescriptionArray,
                                componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, THREAD_FLAG)) {
                        componentList = handleThreadFlag(componentList, flagsArray, flagAndDescriptionArray,
                                componentIndexes);
                    }

                    if (containsFlag(flagAndDescriptionArray, BASE_CLOCK_FLAG)) {
                        componentList = handleBaseClockFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, BOOST_CLOCK_FLAG)) {
                        componentList = handleBoostClockFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    if (containsFlag(flagAndDescriptionArray, SOCKET_FLAG)) {
                        componentList = handleSocketFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    break;

                case CPU_COOLER_TYPE:
                    // handle rpm, noise and power flag
                    if (containsFlag(flagAndDescriptionArray, RPM_FLAG)) {
                        componentList = handleRpmFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray,
                                componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, NOISE_FLAG)) {
                        componentList = handleNoiseFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    break;
                case GPU_TYPE:
                    // handle power and size flag
                    if (containsFlag(flagAndDescriptionArray, FORM_FACTOR_FLAG)) {
                        componentList = handleFormFactorFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray,
                                componentIndexes, componentType);
                    }
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    break;
                case MOTHERBOARD_TYPE:
                    // handle formfactor, socket and power flag
                    if (containsFlag(flagAndDescriptionArray, FORM_FACTOR_FLAG)) {
                        componentList = handleFormFactorFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray,
                                componentIndexes, componentType);
                    }
                    if (containsFlag(flagAndDescriptionArray, SOCKET_FLAG)) {
                        componentList = handleSocketFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    break;
                case RAM_TYPE:
                    // handle memory, sticks, speed and power flag
                    if (containsFlag(flagAndDescriptionArray, MEMORY_FLAG)) {
                        componentList = handleMemoryFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, STICKS_FLAG)) {
                        componentList = handleSticksFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, SPEED_FLAG)) {
                        componentList = handleSpeedFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    break;
                case STORAGE_TYPE:
                    // handle type, size and power flag
                    if (containsFlag(flagAndDescriptionArray, TYPE_FLAG)) {
                        componentList = handleTypeFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, SIZE_FLAG)) {
                        componentList = handleSizeFlag(componentList, flagsArray, flagAndDescriptionArray,
                                componentIndexes);
                    }
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    break;
                case PSU_TYPE:
                    // handle power, formfactor and efficiency flag
                    if (containsFlag(flagAndDescriptionArray, POWER_FLAG)) {
                        componentList = handlePowerFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray, componentIndexes, componentType);
                    }
                    if (containsFlag(flagAndDescriptionArray, FORM_FACTOR_FLAG)) {
                        componentList = handleFormFactorFlag(userInputString, componentList, flagsArray,
                                flagAndDescriptionArray,
                                componentIndexes, componentType);
                    }
                    if (containsFlag(flagAndDescriptionArray, EFFICIENCY_FLAG)) {
                        componentList = handleEfficiencyFlag(componentList, flagsArray, flagAndDescriptionArray,
                                componentIndexes);
                    }
                    break;
                default:
                    break;
                }

            } else {
                throw new PPException("Please enter a valid flag after the component type");
            }

        }

        String outputString = "";

        if (componentList.size() == 0) {
            outputString = "There are no components of type '" + componentType + "' ";
        } else {
            outputString = "Here are all available components of type '" + componentType + "': \n";
        }

        if (flagsArray.size() > 0) {
            outputString += "meeting the following criteria: \n";
            for (String flagDescription : flagsArray) {
                outputString += flagDescription + "\n";
            }
        }
        return outputString + componentList.getListString(componentIndexes, getDetails);
    }

    /*
     * This method handles the form factor flag for psu
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleEfficiencyFlag(ComponentList<?> componentList, ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int efficiencyIndex = indexOfFlag(flagAndDescriptionArray, EFFICIENCY_FLAG);
        if (efficiencyIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the efficiency flag");
        }

        String efficiencyDescription = flagAndDescriptionArray[efficiencyIndex + 1].trim().toLowerCase();

        String[] efficiencyDescriptionArray = Arrays.copyOfRange(efficiencyDescription.split(" "), 0, 1);
        if (hasFlag(efficiencyDescriptionArray)) {
            throw new PPException("Flag detected in efficiency description");
        }

        String efficiency = efficiencyDescriptionArray[0];
        if (!efficiency.equals("bronze") && !efficiency.equals("silver") && !efficiency.equals("gold")) {
            throw new PPException("Please enter a valid efficiency description (bronze, silver or gold)");
        }

        flagsArray.add("Efficiency: " + efficiency);

        componentList = ComponentList.filterByEfficiencyPsu(componentList, efficiency, componentIndexes);

        return componentList;
    }

    /*
     * This method handles the size flag for storage
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleSizeFlag(ComponentList<?> componentList, ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int sizeIndex = indexOfFlag(flagAndDescriptionArray, SIZE_FLAG);
        if (sizeIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the size flag");
        }

        String sizeDescription = flagAndDescriptionArray[sizeIndex + 1].trim().toLowerCase();
        if (sizeDescription.split(" ").length < 1) {
            throw new PPException("Please enter size description");
        }

        String[] sizeDescriptionArray = Arrays.copyOfRange(sizeDescription.split(" "), 0, 1);
        if (hasFlag(sizeDescriptionArray)) {
            throw new PPException("Flag detected in size description");
        }

        String size = sizeDescriptionArray[0];
        if (!size.equals("512") && !size.equals("1024") && !size.equals("2048") && !size.equals("4096")) {
            throw new PPException("Please enter a valid size (512, 1024, 2048, 4096)");
        }

        int sizeInt = 0;
        try {
            sizeInt = Integer.parseInt(size);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a valid size (512, 1024, 2048, 4096)");
        }

        flagsArray.add("Size: " + size + "GB");
        componentList = ComponentList.filterBySizeStorage(componentList, sizeInt, componentIndexes);
        return componentList;
    }

    /*
     * This method handles the type flage for storage
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleTypeFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int typeIndex = indexOfFlag(flagAndDescriptionArray, TYPE_FLAG);
        if (typeIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the type flag");
        }

        String typeDescription = userInputString.split(TYPE_FLAG)[1].trim().toLowerCase();
        if (typeDescription.split(" ").length < 1) {
            throw new PPException("Please enter type description");
        }

        String[] typeDescriptionArray = Arrays.copyOfRange(typeDescription.split(" "), 0, 1);
        if (hasFlag(typeDescriptionArray)) {
            throw new PPException("Flag detected in type description");
        }

        String type = typeDescriptionArray[0];
        if (!type.equals("hdd") && !type.equals("ssd")) {
            throw new PPException("Please enter a valid type description (hdd or ssd))");
        }

        flagsArray.add("Type: " + type);
        componentList = ComponentList.filterByTypeStorage(componentList, type, componentIndexes);

        return componentList;
    }

    /*
     * This method handles the speed flag for ram
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleSpeedFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int speedIndex = indexOfFlag(flagAndDescriptionArray, SPEED_FLAG);
        if (speedIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the speed flag");
        }

        String speedDescription = userInputString.split(SPEED_FLAG)[1].trim().toLowerCase();
        if (speedDescription.split(" ").length < 1) {
            throw new PPException("Please enter speed description");
        }

        String[] speedDescriptionArray = Arrays.copyOfRange(speedDescription.split(" "), 0, 1);
        if (hasFlag(speedDescriptionArray)) {
            throw new PPException("Flag detected in speed description");
        }

        int speed = 0;
        try {
            speed = Integer.parseInt(speedDescriptionArray[0]);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a valid speed (1600 or 3200))");
        }
        if (speed != 1600 && speed != 2000 && speed != 2666 && speed != 3000 && speed != 3200 && speed != 3600) {
            throw new PPException("Please enter a valid speed (1600/2000/2666/3000/3200/3600))");
        }

        flagsArray.add("speed: " + speed);
        componentList = ComponentList.filterBySpeed(componentList, speed, componentIndexes);

        return componentList;
    }

    /*
     * This method handles the sticks flag for ram
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleSticksFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int sticksIndex = indexOfFlag(flagAndDescriptionArray, STICKS_FLAG);
        if (sticksIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the sticks flag");
        }

        String sticksDescription = userInputString.split(STICKS_FLAG)[1].trim().toLowerCase();
        if (sticksDescription.split(" ").length < 1) {
            throw new PPException("Please enter sticks description");
        }

        String[] sticksDescriptionArray = Arrays.copyOfRange(sticksDescription.split(" "), 0, 1);
        if (hasFlag(sticksDescriptionArray)) {
            throw new PPException("Flag detected in sticks description");
        }

        int sticks = 0;

        try {
            sticks = Integer.parseInt(sticksDescriptionArray[0]);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a valid sticks description (1 or 2))");
        }
        if (sticks != 1 && sticks != 2) {
            throw new PPException("Please enter a valid sticks description (1 or 2))");
        }
        flagsArray.add("Sticks: " + sticks);
        componentList = ComponentList.filterBySticks(componentList, sticks, componentIndexes);

        return componentList;
    }

    /*
     * This method handles the memory flag for storage
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleMemoryFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int memoryIndex = indexOfFlag(flagAndDescriptionArray, MEMORY_FLAG);
        if (memoryIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the memory flag");
        }

        String memoryDescription = userInputString.split(MEMORY_FLAG)[1].trim().toLowerCase();

        String[] memoryDescriptionArray = Arrays.copyOfRange(memoryDescription.split(" "), 0, 1);
        if (hasFlag(memoryDescriptionArray)) {
            throw new PPException("Flag detected in memory description");
        }

        int memory = 0;
        try {
            memory = Integer.parseInt(memoryDescriptionArray[0]);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a valid memory description (8, 16, 32 or 64)");
        }
        if (memory != 8 && memory != 16 && memory != 32 && memory != 64) {
            throw new PPException("Please enter a valid memory description (8, 16, 32 or 64)");

        }

        flagsArray.add("Memory: " + memory + "GB");
        componentList = ComponentList.filterByMemory(componentList, memory, componentIndexes);

        return componentList;
    }

    /*
     * This method handles the formfactor flag for storage
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleFormFactorFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes, String componentType)
            throws PPException {
        int formFactorIndex = indexOfFlag(flagAndDescriptionArray, FORM_FACTOR_FLAG);
        if (formFactorIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the formfactor flag");
        }

        String formFactorDescription = userInputString.split(FORM_FACTOR_FLAG)[1].trim().toLowerCase();
        if (formFactorDescription.split(" ").length < 1) {
            throw new PPException("Please enter a full formfactor description");
        }

        String[] formFactorDescriptionArray = Arrays.copyOfRange(formFactorDescription.split(" "), 0, 1);
        if (hasFlag(formFactorDescriptionArray)) {
            throw new PPException("Flag detected in formfactor description. Please enter a valid form factor");
        }

        String formFactor = formFactorDescriptionArray[0];
        if (!formFactor.equals("atx") && !formFactor.equals("micro") && !formFactor.equals("mini")) {
            throw new PPException("Please enter a valid formfactor (ATX, Micro or Mini)");
        }

        flagsArray.add("Form Factor: " + formFactor);

        switch (componentType) {
        case MOTHERBOARD_TYPE:
            componentList = ComponentList.filterByFormFactorMotherboard(componentList, formFactor, componentIndexes);
            break;
        case CHASSIS_TYPE:
            componentList = ComponentList.filterByFormFactorChassis(componentList, formFactor, componentIndexes);
            break;
        case GPU_TYPE:
            componentList = ComponentList.filterByFormFactorGpu(componentList, formFactor, componentIndexes);
            break;
        default:
            break;
        }

        return componentList;
    }

    /*
     * This method handles the noise flag for cpucooler
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleNoiseFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int rpmNoiseIndex = indexOfFlag(flagAndDescriptionArray, NOISE_FLAG);
        if (rpmNoiseIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the noise flag");
        }

        String noiseDescription = userInputString.split(NOISE_FLAG)[1].trim().toLowerCase();
        if (noiseDescription.split(" ").length < 4) {
            throw new PPException("Please enter a full noise description");
        }

        String[] noiseDescriptionArray = Arrays.copyOfRange(noiseDescription.split(" "), 0, 4);
        if (hasFlag(noiseDescriptionArray)) {
            throw new PPException("Flag detected in noise description. Please enter a valid noise range");
        }

        String fromFlag = noiseDescriptionArray[0];
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please enter '/from' before the noise range");
        }

        String noiseFrom = noiseDescriptionArray[1];
        if (noiseFrom.matches(".*\\D.*")) {
            throw new PPException("Noise start range must be an integer");
        }

        String toFlag = noiseDescriptionArray[2];
        if (!toFlag.equals("/to")) {
            throw new PPException("Please enter '/to' before the noise range");
        }

        String noiseTo = noiseDescriptionArray[3];
        if (noiseTo.matches(".*\\D.*")) {
            throw new PPException("Noise end range must be an integer");
        }

        int noiseFromInt = 0;
        int noiseToInt = 0;

        try {
            noiseFromInt = Integer.parseInt(noiseFrom);
            noiseToInt = Integer.parseInt(noiseTo);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        if (noiseFromInt > noiseToInt) {
            throw new PPException("Noise start range must be smaller than noise end range");
        }

        if (noiseFromInt > 500 || noiseToInt > 500) {
            throw new PPException("Noise must be smaller than 500");
        }

        if (noiseFromInt < 0 || noiseToInt < 0) {
            throw new PPException("Noise must be a positive integer");
        }

        componentList = ComponentList.filterByNoise(componentList, noiseFromInt, noiseToInt, componentIndexes);

        flagsArray.add("Noise: " + noiseFrom + " to " + noiseTo);

        return componentList;
    }

    /*
     * This method handles the rpm flag for cpucooler
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleRpmFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int rpmFlagIndex = indexOfFlag(flagAndDescriptionArray, RPM_FLAG);

        if (rpmFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the rpm flag");
        }

        String rpmDescription = userInputString.split(RPM_FLAG)[1].trim().toLowerCase();

        if (rpmDescription.split(" ").length < 4) {
            throw new PPException("Please enter a full rpm description");
        }

        String[] rpmDescriptionArray = Arrays.copyOfRange(rpmDescription.split(" "), 0, 4);
        if (hasFlag(rpmDescriptionArray)) {
            throw new PPException("Flag detected in rpm description. Please enter a valid rpm range");
        }

        String fromFlag = rpmDescriptionArray[0];
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please enter '/from' before the rpm range");
        }

        String rpmFrom = rpmDescriptionArray[1];
        if (rpmFrom.matches(".*\\D.*")) {
            throw new PPException("Rpm start range must be an integer");
        }

        String toFlag = rpmDescriptionArray[2];
        if (!toFlag.equals("/to")) {
            throw new PPException("Please enter '/to' before the rpm range");
        }

        String rpmTo = rpmDescriptionArray[3];
        if (rpmTo.matches(".*\\D.*")) {
            throw new PPException("Rpm end range must be an integer");
        }

        int rpmFromInt = 0;
        int rpmToInt = 0;

        try {
            rpmFromInt = Integer.parseInt(rpmFrom);
            rpmToInt = Integer.parseInt(rpmTo);
            rpmFromInt = Integer.parseInt(rpmFrom);
            rpmToInt = Integer.parseInt(rpmTo);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }

        if (rpmFromInt > rpmToInt) {
            throw new PPException("Rpm start range must be smaller than power end range");
        }

        if (rpmFromInt < 0 || rpmToInt < 0) {
            throw new PPException("Rpm must be a positive integer");
        }

        componentList = ComponentList.filterByRpm(componentList, rpmFromInt, rpmToInt, componentIndexes);

        flagsArray.add("Rpm: " + rpmFrom + " to " + rpmTo);

        return componentList;
    }

    /*
     * This method handles the socket flag for cpu and motherboard
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleSocketFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes, String componentType)
            throws PPException {
        int socketFlagIndex = indexOfFlag(flagAndDescriptionArray, SOCKET_FLAG);
        if (socketFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the socket flag");
        }

        String socket = userInputString.split(SOCKET_FLAG)[1].trim().toLowerCase();

        if (isFlag(socket)) {
            throw new PPException("Flag detected in socket description. Please enter a valid socket");
        }

        if (!socket.equals("am4") && !socket.equals("am5") && !socket.equals("lga1200") && !socket.equals("lga1700")) {
            throw new PPException("Please enter a valid socket (am4, am5, lga1200, lga1700))");
        }

        flagsArray.add("Socket: " + socket);

        switch (componentType) {
        case CPU_TYPE:
            componentList = ComponentList.filterBySocketCpu(componentList, socket, componentIndexes);
            break;
        case MOTHERBOARD_TYPE:
            componentList = ComponentList.filterBySocketMotherboard(componentList, socket, componentIndexes);
            break;
        default:
            throw new PPException("Invalid component type");
        }
        return componentList;
    }

    /*
     * This method handles the power flag for cpu, cpucooler, gpu, motherboard, ram,
     * storage and psu
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handlePowerFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes, String componentType)
            throws PPException {
        int powerFlagIndex = indexOfFlag(flagAndDescriptionArray, POWER_FLAG);
        if (powerFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the power flag");
        }

        String powerDescription = userInputString.split(POWER_FLAG)[1].trim().toLowerCase();
        if (powerDescription.split(" ").length < 4) {
            throw new PPException("Please enter a full power description");
        }

        String[] powerDescriptionArray = Arrays.copyOfRange(powerDescription.split(" "), 0, 4);
        if (hasFlag(powerDescriptionArray)) {
            throw new PPException("Flag detected in power description. Please enter a valid power range");
        }
        float powerFromFloat = 0;
        float powerToFloat = 0;

        String fromFlag = powerDescriptionArray[0];
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please enter '/from' before the power range");
        }

        String powerFrom = powerDescriptionArray[1];
        try {
            powerFromFloat = Float.parseFloat(powerFrom);
            if (powerFromFloat > 5000) {
                throw new PPException("Power start range must be smaller than 5000");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Power start range must be a float");
        }

        String toFlag = powerDescriptionArray[2];
        if (!toFlag.equals("/to")) {
            throw new PPException("Please enter '/to' before the power range");
        }

        String powerTo = powerDescriptionArray[3];

        try {
            powerToFloat = Float.parseFloat(powerTo);
            if (powerToFloat > 5000) {
                throw new PPException("Power end range must be smaller than 5000");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Power end range must be a float");
        }
        // if (powerFromInt > powerToInt
        if (powerFromFloat > powerToFloat) {
            throw new PPException("Power start range must be smaller than power end range");
        }

        if (powerFromFloat < 0 || powerToFloat < 0) {
            throw new PPException("Power must be a positive float");
        }

        switch (componentType) {
        case CPU_TYPE:
            componentList = ComponentList.filterByPowerCpu(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        case CPU_COOLER_TYPE:
            componentList = ComponentList.filterByPowerCpuCooler(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        case GPU_TYPE:
            componentList = ComponentList.filterByPowerGpu(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        case MOTHERBOARD_TYPE:
            componentList = ComponentList.filterByPowerMotherboard(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        case RAM_TYPE:
            componentList = ComponentList.filterByPowerRam(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        case STORAGE_TYPE:
            componentList = ComponentList.filterByPowerStorage(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        case PSU_TYPE:
            componentList = ComponentList.filterByPowerPsu(componentList, powerFromFloat, powerToFloat,
                    componentIndexes);
            break;
        default:
            throw new PPException("Invalid component type");
        }

        flagsArray.add("Power: " + powerFrom + "W to " + powerTo + "W");

        return componentList;
    }

    /*
     * This method handles the boost clock flag for cpu
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleBoostClockFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int boostClockFlagIndex = indexOfFlag(flagAndDescriptionArray, BOOST_CLOCK_FLAG);
        if (boostClockFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the boostclock flag");
        }

        String boostClockDescription = userInputString.split(BOOST_CLOCK_FLAG)[1].trim().toLowerCase();
        if (boostClockDescription.split(" ").length < 4) {
            throw new PPException("Please enter a full boost clock description");
        }

        String[] boostClockDescriptionArray = Arrays.copyOfRange(boostClockDescription.split(" "), 0, 4);
        if (hasFlag(boostClockDescriptionArray)) {
            throw new PPException(
                    "Flag detected in boost clock description. Please enter a valid boost clock description");
        }

        String fromFlag = boostClockDescriptionArray[0];
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please use /from to specify the lower bound of the boost clock");
        }

        String boostClockLowerBoundString = boostClockDescriptionArray[1];
        float boostClockLowerBound = 0;
        float boostClockUpperBound = 0;

        String toFlag = boostClockDescriptionArray[2];
        if (!toFlag.equals("/to")) {
            throw new PPException("Please use /to to specify the upper bound of the boost clock");
        }

        String boostClockUpperBoundString = boostClockDescriptionArray[3];

        try {
            boostClockLowerBound = Float.parseFloat(boostClockLowerBoundString);
            if (boostClockLowerBound > 10) {
                throw new PPException("Boost clock cannot be greater than 00");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a valid lower bound for the boost clock");
        }

        try {
            boostClockUpperBound = Float.parseFloat(boostClockUpperBoundString);
            if (boostClockUpperBound > 10) {
                throw new PPException("Boost clock cannot be greater than 10");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a valid upper bound for the boost clock");
        }

        if (boostClockLowerBound > boostClockUpperBound) {
            throw new PPException("Please enter a lower bound that is smaller than the upper bound");
        }

        if (boostClockLowerBound < 0 || boostClockUpperBound < 0) {
            throw new PPException("Please enter a positive boost clock");
        }

        flagsArray.add("Boost Clock: " + boostClockLowerBound + " to " + boostClockUpperBound);
        componentList = ComponentList.filterByBoostClock(componentList, boostClockLowerBound, boostClockUpperBound,
                componentIndexes);

        return componentList;

    }

    /*
     * This method handles the base clock flag for cpu
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleBaseClockFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int baseClockFlagIndex = indexOfFlag(flagAndDescriptionArray, BASE_CLOCK_FLAG);
        if (baseClockFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a description after the baseclock flag");
        }

        String baseClockDescription = userInputString.split(BASE_CLOCK_FLAG)[1].trim().toLowerCase();
        if (baseClockDescription.split(" ").length < 4) {
            throw new PPException("Please enter a full base clock description");
        }

        String[] baseClockDescriptionArray = Arrays.copyOfRange(baseClockDescription.split(" "), 0, 4);
        if (hasFlag(baseClockDescriptionArray)) {
            throw new PPException(
                    "Flag detected in base clock description. Please enter a valid base clock description");
        }
        String fromFlag = baseClockDescriptionArray[0].trim();
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please use /from to specify the lower bound of the base clock");
        }

        String fromBaseClock = baseClockDescriptionArray[1].trim();

        String toFlag = baseClockDescriptionArray[2].trim();
        if (!toFlag.equals("/to")) {
            throw new PPException("Please use /to to specify the upper bound of the base clock");
        }

        String toBaseClock = baseClockDescriptionArray[3].trim();

        float fromBaseClockFloat = 0;
        float toBaseClockFloat = 0;

        try {
            fromBaseClockFloat = Float.parseFloat(fromBaseClock);
            if (fromBaseClockFloat > 10) {
                throw new PPException("Base clock cannot be more than 10");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a float for the lower bound of the base clock");
        }

        try {
            toBaseClockFloat = Float.parseFloat(toBaseClock);
            if (toBaseClockFloat > 10) {
                throw new PPException("Base clock cannot be more than 10");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a float for the upper bound of the base clock");
        }
        if (fromBaseClockFloat > toBaseClockFloat) {
            throw new PPException("Start range must be smaller than end range");
        }

        if (fromBaseClockFloat < 0 || toBaseClockFloat < 0) {
            throw new PPException("Base clock cannot be negative");
        }

        flagsArray.add("Base clock from " + fromBaseClock + " to " + toBaseClock);
        componentList = ComponentList.filterByBaseClock(componentList, fromBaseClockFloat, toBaseClockFloat,
                componentIndexes);
        return componentList;
    }

    /*
     * This method handles the thread flag for cpu
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleThreadFlag(ComponentList<?> componentList, ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int threadFlagIndex = indexOfFlag(flagAndDescriptionArray, THREAD_FLAG);
        if (threadFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a thread number after the thread flag");
        }

        String thread = flagAndDescriptionArray[threadFlagIndex + 1].trim().toLowerCase();

        if (isFlag(thread)) {
            throw new PPException("Flag detected in thread description. Please enter a valid thread number");
        }

        if (!thread.equals("4") && !thread.equals("8") && !thread.equals("12") && !thread.equals("16") && !thread
                .equals("20") && !thread.equals("24") && !thread.equals("32")) {
            throw new PPException("Please enter a valid thread number (4, 8, 12, 16, 20, 24 or 32)");
        }

        if (thread.matches(".*\\D.*")) {
            throw new PPException("Please enter an integer for thread number");
        }
        int threadInt = 0;
        try {
            threadInt = Integer.parseInt(thread);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        flagsArray.add("Thread: " + thread);

        componentList = ComponentList.filterByThread(componentList, threadInt, componentIndexes);
        return componentList;
    }

    /*
     * This method handles the core flag for cpu
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleCoreFlag(ComponentList<?> componentList, ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int coreFlagIndex = indexOfFlag(flagAndDescriptionArray, CORE_FLAG);
        if (coreFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a core number after the core flag");
        }

        String core = flagAndDescriptionArray[coreFlagIndex + 1].trim().toLowerCase();

        if (isFlag(core)) {
            throw new PPException("Flag detected in core description. Please enter a valid core number");
        }

        if (!core.equals("4") && !core.equals("6") && !core.equals("8") && !core.equals("10") && !core.equals(
                "12") && !core.equals("16")) {
            throw new PPException("Please enter a valid core number (4, 6, 8, 10, 12 or 16)");
        }
        flagsArray.add("core: " + core);

        if (core.matches(".*\\D.*")) {
            throw new PPException("Please enter an integer for core number");
        }
        int coreInt = 0;
        try {
            coreInt = Integer.parseInt(core);
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        componentList = ComponentList.filterByCore(componentList, coreInt, componentIndexes);
        return componentList;
    }

    /*
     * This method handles the price flag for all components
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handlePriceFlag(String userInputString, ComponentList<?> componentList,
            ArrayList<String> flagsArray, String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes)
            throws PPException {
        int priceFlagIndex = indexOfFlag(flagAndDescriptionArray, PRICE_FLAG);
        if (priceFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a price description after the price flag");
        }
        String flagPriceDescription = userInputString.split(PRICE_FLAG)[1].trim();
        if (flagPriceDescription.split(" ").length < 4) {
            throw new PPException(
                    "Please enter the full price description after the flag containing the start "
                            + "and end price range");
        }
        String[] flagPriceDescriptionArray = Arrays.copyOfRange(flagPriceDescription.split(" "), 0, 4);
        if (hasFlag(flagPriceDescriptionArray)) {
            throw new PPException(
                    "Flag detected in price description. Please enter a different price"
                            + " description after the flag");
        }
        String fromFlag = flagPriceDescriptionArray[0].trim();
        if (!fromFlag.equals("/from")) {
            throw new PPException("Please use /from to specify the start price range");
        }
        float priceFromFloat = 0;
        float priceToFloat = 0;
        String priceFrom = flagPriceDescriptionArray[1].trim();
        try {
            priceFromFloat = Float.parseFloat(priceFrom);
            if (priceFromFloat > 100000) {
                throw new PPException("Price must be smaller than 100000");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a float for price start range");
        }

        String toFlag = flagPriceDescriptionArray[2].trim();
        if (!toFlag.equals("/to")) {
            throw new PPException("Please use /to to specify the end price range");
        }
        String priceTo = flagPriceDescriptionArray[3].trim();

        try {
            priceToFloat = Float.parseFloat(priceTo);
            if (priceToFloat > 100000) {
                throw new PPException("Price must be smaller than 100000");
            }
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a float for price end range");
        }

        if (priceFromFloat > priceToFloat) {
            throw new PPException("Start price must be less than end price");
        }
        if (priceFromFloat < 0 || priceToFloat < 0) {
            throw new PPException("Price must be greater than 0");
        }

        componentList = ComponentList.filterByPrice(componentList, priceFrom, priceTo, componentIndexes);
        flagsArray.add("price: " + priceFrom + " to " + priceTo);
        return componentList;
    }

    /*
     * This method handles the brand flag for all components
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleBrandFlag(ComponentList<?> componentList, ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int brandFlagIndex = indexOfFlag(flagAndDescriptionArray, BRAND_FLAG);
        if (brandFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a brand after the brand flag");
        }
        String brand = flagAndDescriptionArray[brandFlagIndex + 1].trim().toLowerCase();
        if (isFlag(brand)) {
            throw new PPException("Flag detected as a brand. Please enter another brand after the flag");
        }
        componentList = ComponentList.filterByBrand(componentList, brand, componentIndexes);
        flagsArray.add("brand: " + brand);
        return componentList;
    }

    /*
     * This method handles the name flag for all components
     * @param userInputString the user input string, flagsArray the arraylist of
     * flags, flagAndDescriptionArray the
     * array of flags and description, componentIndexes the arraylist of component
     * indexes
     * @return the filtered component list
     */
    private ComponentList<?> handleNameFlag(ComponentList<?> componentList, ArrayList<String> flagsArray,
            String[] flagAndDescriptionArray, ArrayList<Integer> componentIndexes) throws PPException {
        int nameFlagIndex = indexOfFlag(flagAndDescriptionArray, NAME_FLAG);
        if (nameFlagIndex == flagAndDescriptionArray.length - 1) {
            throw new PPException("Please enter a name after the name flag");
        }
        String name = flagAndDescriptionArray[nameFlagIndex + 1].trim().toLowerCase();
        if (isFlag(name)) {
            throw new PPException("Flag detected as a name. Please enter another name after the flag");
        }
        flagsArray.add("name: " + name);
        componentList = ComponentList.filterByName(componentList, name, componentIndexes);
        return componentList;
    }

    /*
     * This method checks if the flag is a valid flag
     * @param flag the flag to be checked
     * @return true if the flag is valid, false otherwise
     */
    private static boolean isFlag(String flag) {
        return flag.equals(NAME_FLAG) || flag.equals(PRICE_FLAG) || flag.equals(BRAND_FLAG) || flag.equals(CORE_FLAG)
                || flag.equals(THREAD_FLAG) || flag.equals(BASE_CLOCK_FLAG) || flag.equals(SIZE_FLAG)
                || flag.equals(BOOST_CLOCK_FLAG) || flag.equals(POWER_FLAG)
                || flag.equals(SOCKET_FLAG) || flag.equals(RPM_FLAG) || flag.equals(NOISE_FLAG)
                || flag.equals(MEMORY_FLAG) || flag.equals(STICKS_FLAG) || flag.equals(SPEED_FLAG)
                || flag.equals(TYPE_FLAG) || flag.equals(FORM_FACTOR_FLAG)
                || flag.equals(EFFICIENCY_FLAG) || flag.equals(DETAILS_FLAG);
    }

    /*
     * This method checks if the user input string contains any flag
     * @param userInputStringArray the array of the user input string
     * @return true if the user input string contains a flag, false otherwise
     */
    private static boolean hasFlag(String[] userInputStringArray) {
        for (String flag : userInputStringArray) {
            if (isFlag(flag)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method checks if the flagAndDescriptionArray contains a specific flag
     * @param flagAndDescriptionArray the array of flags and description
     * @param flag the flag to be checked
     * @return true if the flagAndDescriptionArray contains the flag, false
     * otherwise
     */
    private static boolean containsFlag(String[] flagAndDescriptionArray, String flag) {
        for (String flagAndDescription : flagAndDescriptionArray) {
            if (flagAndDescription.equals(flag)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This method returns the index of the flag in the flagAndDescriptionArray
     * @param flagAndDescriptionArray the array of flags and description
     * @param flag the flag to be checked
     * @return the index of the flag in the flagAndDescriptionArray
     */
    private static int indexOfFlag(String[] flagAndDescriptionArray, String flag) {
        for (int i = 0; i < flagAndDescriptionArray.length; i++) {
            if (flagAndDescriptionArray[i].equals(flag)) {
                return i;
            }
        }
        return -1;
    }
}
