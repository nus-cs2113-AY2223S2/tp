package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.exceptions.DukeError;
import seedu.duke.userdata.UserCareerData;

import java.time.LocalDateTime;

public class StorageHandler {
    private Gson gson;
    private GsonBuilder gsonBuilder;
    private final UserDataWriter userDataWriter;
    private final UserDataLoader userDataLoader;
    private String FILEPATH;

    public StorageHandler (String filePath) {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        this.userDataWriter = new UserDataWriter(gson);
        this.userDataLoader = new UserDataLoader(gson);
        this.FILEPATH = filePath;
    }

    public void writeToJson (UserCareerData userCareerData) throws DukeError {
        boolean writeStatus = this.userDataWriter.saveToJson(FILEPATH, userCareerData);
        assert writeStatus : "An exception should be thrown, this part of code should not be run";
        System.out.println("File has been written successfully!");
    }

    public UserCareerData loadUserCareer () {
        UserCareerData userCareerData;
        try {
            userCareerData = userDataLoader.loadFromJson(FILEPATH);
            System.out.println("Data has been restored from previous session!");
        } catch (DukeError e) {
            userCareerData = new UserCareerData();
            try {
                writeToJson(userCareerData);
                System.out.println(
                        "Data file has been corrupted or missing, we will create a new file and reset your progress.");
            } catch (DukeError error) {
                System.out.println(error.getMessage());
            }
        }
        return userCareerData;
    }

}
