package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderIncorrectComponentException extends PPException {
    @Override
    public String getMessage() {
        return "Please select a valid component (cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)";
    }
}
