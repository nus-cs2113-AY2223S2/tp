package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.duke.Event;
import seedu.duke.EventList;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private static final String fileLocation = System.getProperty("user.dir") + "/save.json";
    File saveFile = new File(fileLocation);

    @Test
    public void emptyLoad(){
        saveFile.delete();
        Storage loader = new Storage();
        EventList testList = new EventList(loader.loadEvents());
        loader.loadEvents();
        assert((testList.getSize()==0) && (!saveFile.exists()));
    }
    @Test public void saveEvents(){
        EventList testList = new EventList();
        testList.addEvent("testing", "10:00", "2023/03/20", "10:00", "2023/03/21");
        testList.addEvent("testing2","03:24", "2023/04/01", "08:50", "2023/03/23");
        Storage save = new Storage();
        save.saveToFile(testList);
        assert(saveFile.exists());
    }

    /*
    @Test
    public void loadEvents(){
        Storage load = new Storage();
        EventList testList = new EventList(load.loadEvents());
        EventList testList_check = new EventList();
        testList_check.addEvent("testing", "10:00", "2023/03/20", "10:00", "2023/03/21");
        testList_check.addEvent("testing2","03:24", "2023/04/01", "08:50", "2023/03/23");
        assert(testList.equals(testList_check));
    }

     */
}