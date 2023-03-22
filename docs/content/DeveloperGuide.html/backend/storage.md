---
title: Storage
weight: 2
---

## Constructors

If no arguments are passed into the Storage constructor, the Storage instance will be initialized with the default file name (for storage) and delimiter as specified in the config files.

```java
Storage storage = new Storage();
```

Otherwise, a `filePath` argument (as a String) can be passed into the constructor which will define where the storage file (used for testing purposes).

```java
String customStoragePath = './test/storage.txt';
Storage storageWithCustomStoragePath = new Storage(customStoragePath);
```

## Reading data from existing file

To read data from an existing storage file, instantiate a `seedu.pocketpal.backend.storage.Storage` instance, and call the `readFromDatabase()` method with no arguments to return a list of `Entry` objects.

The `readFromDatabase()` method throws both an `IOException` and an `InvalidReadFileException` which will either have to be caught by the function using it, or thrown as an exception.

```java
Storage storage = new Storage();
List<Entry> = storage.readFromDatabase();
```

If the file does not exist, a blank file will be created at the configured storage file path. Otherwise, the existing data in the file will not be edited.

## Writing data into storage file

To write existing data into a storage file from a list of `Entry` objects, instantiate a `seedu.pocketpal.backend.storage.Storage` instance, and call the `writeToDatabase()` method with a list of `Entry` objects as an argument.

The `writeToDatabase()` method throws an `IOException` which will either have to be caught by the function using it, or thrown as an exception.

```java
// ...
List<Entry> = entries.getEntriesList();
Storage storage = new Storage();
```

If the file does not exist, a new file will be created at the configured storage file path before data is being written in.

## Resetting data in storage file

To remove all data from the storage file, instantiate a `seedu.pocketpal.backend.storage.Storage` instance, and call the `reset()` method with no arguments.

The `reset()` method throws an `IOException` which will either have to be caught by the function using it, or thrown as an exception.

```java
Storage storage = new Storage();
storage.reset();
```
