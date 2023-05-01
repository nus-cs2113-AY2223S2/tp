package seedu.securenus.storage;

import seedu.securenus.SecureNUSLogger;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.FolderNotEmptyException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.secrets.Secret;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.logging.Level;

/**
 * Class that manages the storage and retrieval of secrets and folders.
 */
public class SecretEnumerator {
    private final ArrayList<Secret> storage;

    /**
     * Hashtable that stores secrets organized by folder.
     */
    private final Hashtable<String, ArrayList<Secret>> folders;

    /**
     * Constructor for the SecretEnumerator class.
     */
    public SecretEnumerator() {
        storage = new ArrayList<Secret>();
        folders = new Hashtable<String, ArrayList<Secret>>();
    }

    /**
     * Constructor for the SecretEnumerator class that takes in existing storage and folder data.
     *
     * @param storage ArrayList of Secret objects to be added to the storage.
     * @param folders Hashtable of Secret objects organized by folder.
     */
    public SecretEnumerator(ArrayList<Secret> storage, Hashtable<String, ArrayList<Secret>> folders) {
        this.storage = storage;
        this.folders = folders;
    }

    /**
     * Creates a new folder in the Hashtable of folders.
     *
     * @param folderName String name of the folder to be created.
     * @throws FolderExistsException if the folder already exists in the Hashtable.
     */
    public void createFolder(String folderName) throws FolderExistsException {
        assert this.folders != null;
        assert folderName.length() > 0;
        if (folders.containsKey(folderName)) {
            SecureNUSLogger.LOGGER.log(Level.WARNING, "error, folder already exists, " + folderName);
            throw new FolderExistsException();
        }
        folders.put(folderName,
                new ArrayList<Secret>());
    }

    /**
     * Deletes a folder from the Hashtable of folders.
     *
     * @param folderName String name of the folder to be deleted.
     * @throws FolderNotFoundException if the folder does not exist in the Hashtable.
     * @throws FolderNotEmptyException if the folder is not empty.
     */
    public void deleteFolder(String folderName) throws FolderNotFoundException, FolderNotEmptyException {
        assert this.folders != null;
        assert folderName.length() > 0;
        if (!folders.containsKey(folderName)) {
            throw new FolderNotFoundException();
        }
        if (!folders.get(folderName).isEmpty()) {
            throw new FolderNotEmptyException();
        }
        folders.remove(folderName);
    }

    /**
     * Returns the Secret object located at the specified index in the storage list.
     *
     * @param index the index of the Secret object to retrieve
     * @return the Secret object at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is greater than or equal to the size of the storage list
     */
    public Secret get(int index) {
        assert this.storage != null;
        assert index >= 0;
        if (index >= storage.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return storage.get(index);
    }

    /**
     * Retrieves a Secret object from the storage ArrayList based on its index in the ArrayList.
     *
     * @param index the index of the Secret object to be retrieved
     * @param folderName the name of the folder where the Secret object is stored
     * @return the Secret object at the specified index in the specified folder
     * @throws ArrayIndexOutOfBoundsException if the specified index is out of bounds of the specified folder
     */
    public Secret get(int index, String folderName) {
        assert this.folders != null;
        assert folderName.length() > 0;
        assert index >= 0;
        assert this.folders.get(folderName) != null;

        ArrayList<Secret> tempStorage = folders.get(folderName);

        if (index >= tempStorage.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return tempStorage.get(index);
    }

    /**
     * Returns an ArrayList containing all the secrets stored in the application.
     *
     * @return an ArrayList of all secrets stored in the application.
     */
    public ArrayList<Secret> getList() {
        assert this.storage != null;
        return storage;
    }

    /**
     * Returns an ArrayList containing all the secrets stored in the specified folder.
     * If the folder does not exist, returns null.
     *
     * @param folderName the name of the folder.
     * @return an ArrayList of secrets stored in the specified folder.
     */
    public ArrayList<Secret> getList(String folderName) {
        assert this.folders != null;
        assert folderName.length() > 0;
        return folders.get(folderName);
    }

    /**
     * Adds a new secret to the storage.
     * Throws FolderExistsException if the folder name already exists.
     *
     * @param secret The secret to be added.
     * @throws FolderExistsException if the folder name already exists.
     */
    public void add(Secret secret) throws FolderExistsException {
        assert this.storage != null;
        assert this.folders != null;
        assert secret != null;
        storage.add(secret);
        // creates a folder if it doesn't already exist
        if (!folders.containsKey(secret.getFolderName())) {
            createFolder(secret.getFolderName());
        }
        folders.get(secret.getFolderName()).add(secret);
    }

    /**
     * Deletes the given secret from the storage, including from its corresponding folder.
     * If the folder becomes empty after the deletion, it will also be removed from the folders list.
     *
     * @param secret the secret to be deleted
     */
    public void delete(Secret secret) {
        assert this.storage != null;
        assert this.folders != null;
        assert secret != null;
        storage.remove(secret);
        folders.get(secret.getFolderName()).remove(secret);
        // delete folder if it is empty
        if (folders.get(secret.getFolderName()).isEmpty()) {
            folders.remove(secret.getFolderName());
        }
    }

    /**
     * Returns the number of secrets stored in the container.
     *
     * @return the size of the container
     */
    public int size() {
        assert this.storage != null;
        return this.storage.size();
    }

    /**
     * Returns a HashSet of all the folder names currently in the container.
     *
     * @return a HashSet containing all folder names
     */
    public HashSet<String> getFolders() {
        assert this.folders != null;
        HashSet<String> folderHashset = new HashSet();
        for (String name : this.folders.keySet()) {
            folderHashset.add(name);
        }
        return folderHashset;
    }

    /**
     * Checks if a folder with the given name exists.
     *
     * @param folderName the name of the folder to check
     * @return true if the folder exists, false otherwise
     */
    public boolean folderExists(String folderName) {
        return folders.containsKey(folderName);
    }
}
