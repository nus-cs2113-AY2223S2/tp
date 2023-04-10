# Meal360

Given below are instructions on how to use Meal360, a simple CLI meal planner application.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Ensure Intellij JDK 11 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 11 in a previous Intellij project.
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the set up**: After the importing is complete, locate the `src/main/java/seedu/meal360/Meal360.java` file, right-click it, and choose `Run Meal360.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava UP-TO-DATE
   > Task :processResources UP-TO-DATE
   > Task :classes UP-TO-DATE

   > Task :Meal360.main()
   ----------------------------------------------------------------------------------------------------
   Welcome to Meal360, your ultimate Recipe Manager!
   __  __          _ ____  __  __
   |  \/  |___ __ _| |__ / / / /  \
   | |\/| / -_) _` | ||_ \/ _ \ () |
   |_|  |_\___\__,_|_|___/\___/\__/
   
   ----------------------------------------------------------------------------------------------------
   | Loading recipes...                                                                               |
   | Recipes loaded successfully.                                                                     |
   | Loading weekly plan...                                                                           |
   | Weekly plan loaded successfully.                                                                 |
   | Loading ingredients...                                                                           |
   | Ingredients loaded successfully.                                                                 |
   ----------------------------------------------------------------------------------------------------
   ```
   Type `bye` and press enter to end the program.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A JUnit test (`src/test/java/seedu/meal360/Meal360Test.java`) is included in this project. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains the project documentation.

![Developer Guide](https://github.com/AY2223S2-CS2113-F10-3/tp/blob/master/docs/DeveloperGuide.md)

![User Guide](https://github.com/AY2223S2-CS2113-F10-3/tp/blob/master/docs/UserGuide.md)

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
