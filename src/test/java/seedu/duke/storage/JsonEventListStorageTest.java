// package seedu.duke.storage;

// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;

// import seedu.duke.EventList;
// import seedu.duke.NPExceptions;

// import java.io.File;
// import java.io.FileNotFoundException;

// class JsonEventListStorageTest {

//     private static final String fileLocation = System.getProperty("user.dir") + "/save.json";
//     File saveFile = new File(fileLocation);
//     private final JsonEventListStorage jsonEventListStorage = new JsonEventListStorage();
//     @Test
//     @Order(1)
//     public void emptyLoad(){ //check that it can load when there isn't an instance of save.json in the path.
//         saveFile.delete();
//         EventList testList = new EventList(jsonEventListStorage.loadEvents());
//         jsonEventListStorage.loadEvents();
//         assert((testList.getSize()==0) && (!saveFile.exists()));
//     }
//     @Test
//     @Order(2)
//     public void saveEvents() throws NPExceptions{ //check that storage class saves a file
//         EventList testList = new EventList();
//         testList.addEvent("testing", "10:00", "2023/03/20", "11:00", "2023/03/20");
//         testList.addEvent("testing2","03:24", "2023/03/23", "08:50", "2023/03/23");
//         jsonEventListStorage.saveToFile(testList);
//         assert(saveFile.exists());
//         saveFile.delete();
//     }

//     @Test
//     @Order(3)
//     public void loadEvents() throws FileNotFoundException, NPExceptions {
//         //checks that storage EventList from deserialized save.json matches original EventList data
//         EventList original = new EventList();
//         original.addEvent("testing", "10:00", "2023/03/20",
//                 "10:00", "2023/03/20");
//         original.addEvent("testing2","03:24", "2023/03/23",
//                 "08:50", "2023/03/23");
//         jsonEventListStorage.saveToFile(original);
//         EventList testListCheck = new EventList();
//         testListCheck.addEvent("testing", "10:00", "2023/03/20",
//                 "10:00", "2023/03/20");
//         testListCheck.addEvent("testing2","03:24", "2023/03/23",
//                 "08:50", "2023/03/23");
//         EventList testList = new EventList(jsonEventListStorage.loadEvents());
//         String a = testListCheck.getFullList().toString();
//         String b = testList.getFullList().toString();
//         assert(a.equals(b));
//         saveFile.delete();
//     }
//     @Test
//     @Order(4)
//     public void updateEvents() throws NPExceptions{ //Check that data can be updated and matches what was updated.
//         EventList original = new EventList();
//         original.addEvent("testing", "10:00", "2023/03/20",
//                 "10:00", "2023/03/20");
//         original.addEvent("testing2","03:24", "2023/03/23",
//                 "08:50", "2023/03/23");
//         jsonEventListStorage.saveToFile(original);
//         EventList edited = new EventList(jsonEventListStorage.loadEvents());
//         edited.addEvent("edit new event", "13:00", "2023/03/21", "19:00", "2023/03/21");
//         jsonEventListStorage.saveToFile(edited);
//         EventList savedEvent =  new EventList(jsonEventListStorage.loadEvents());
//         String a = edited.getFullList().toString();
//         String b = savedEvent.getFullList().toString();
//         assert(a.equals(b));
//         saveFile.delete();
//     }
// }
