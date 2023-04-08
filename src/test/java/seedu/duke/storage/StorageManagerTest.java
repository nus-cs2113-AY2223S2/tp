// package seedu.duke.storage;

// import org.junit.jupiter.api.Test;
// import seedu.duke.EventList;
// import seedu.duke.NPExceptions;
// import seedu.duke.NusModule;

// import java.io.File;
// import java.util.HashMap;


// class StorageManagerTest {
//     private static final String fileLocation = System.getProperty("user.dir") + "/save.json";
//     File saveFile = new File(fileLocation);
//     private EventListStorage eventListStorage = new JsonEventListStorage();
//     private NusModuleLoader nusModuleLoader = new JsonNusModuleLoader();
//     private Storage storage = new StorageManager(eventListStorage, nusModuleLoader);
//     @Test
//     void saveToFile() throws NPExceptions {
//         EventList testList = new EventList();
//         testList.addEvent("testing", "10:00", "2023/03/20", "10:00", "2023/03/20");
//         testList.addEvent("testing2","03:24", "2023/03/23", "08:50", "2023/03/23");
//         storage.saveToFile(testList);
//         assert(saveFile.exists());
//         saveFile.delete();
//     }

//     @Test
//     void loadEvents() throws NPExceptions {
//         EventList original = new EventList();
//         original.addEvent("testing", "10:00", "2023/03/20",
//                 "10:00", "2023/03/20");
//         original.addEvent("testing2","03:24", "2023/03/23",
//                 "08:50", "2023/03/23");
//         storage.saveToFile(original);
//         EventList testListCheck = new EventList();
//         testListCheck.addEvent("testing", "10:00", "2023/03/20",
//                 "10:00", "2023/03/20");
//         testListCheck.addEvent("testing2","03:24", "2023/03/23",
//                 "08:50", "2023/03/23");
//         EventList testList = new EventList(storage.loadEvents());
//         String a = testListCheck.getFullList().toString();
//         String b = testList.getFullList().toString();
//         assert(a.equals(b));
//         saveFile.delete();
//     }

//     @Test
//     void loadModules() {
//         HashMap<String, NusModule> nusmods = storage.loadModules();
//         System.out.println("CS2113 Details:");
//         NusModule cs2113 = nusmods.get("CS2113");
//         System.out.println(cs2113.getDescription());
//         System.out.println(cs2113.getFaculty());
//         System.out.println(cs2113.getLesson(2, "lecture", "1"));
//         System.out.println(nusmods.get("CS2113").getLesson(2, "Tutorial", "13").toString());
//         System.out.println("========================================================");
//         System.out.println("CG2023 Details:");
//         NusModule cg2023 = nusmods.get("CG2023");
//         System.out.println(cg2023.getDescription());
//         System.out.println(cg2023.getFaculty());
//         System.out.println(cg2023.getLesson(2,"lecture", "02"));
//     }

//     @Test
//     void loadEventTypes() throws NPExceptions {
//         EventList original = new EventList();
//         original.addEvent("testing", "10:00", "2023/03/21",
//                 "10:00", "2023/03/21"); //Normal Start and EndTime
//         original.addEvent("testing1", "10:00", "2023/03/20", "12:00",
//                 "2023/03/20", "1 W"); //Normal with Recur Time
//         original.addEvent("testing3", "10:00", "2023/03/20"); //Only Start Time
//         original.addEvent("testing4", "10:00", "2023/03/20", "1 W"); //recurring
//         //only start time.
//         storage.saveToFile(original);
//         EventList testListCheck = new EventList();
//         testListCheck.addEvent("testing", "10:00", "2023/03/21",
//                 "10:00", "2023/03/21"); //Normal Start and EndTime
//         testListCheck.addEvent("testing1", "10:00", "2023/03/20", "12:00",
//                 "2023/03/20", "1 W"); //Normal with Recur Time
//         testListCheck.addEvent("testing3", "10:00", "2023/03/20"); //Only Start Time
//         testListCheck.addEvent("testing4", "10:00", "2023/03/20", "1 W"); //recurring
//         EventList testList = new EventList(storage.loadEvents());
//         String a = testListCheck.getFullList().toString();
//         String b = testList.getFullList().toString();
//         assert(a.equals(b));
//         saveFile.delete();
//     }
// }
