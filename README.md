# DinerDirector

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Fork** this repo, and **clone** the fork into your computer.
2. Open the application `Intellij IDEA`.
   1. On the top left of the navigation bar, select `File` > `Open...`.
   2. Navigate to the folder, and select the folder containing the `DinerDirector` files that was cloned.
   3. By default, if there is a `build.gradle` in the project root, Intellij treats it as a Gradle project by default. Just in case it didn't work follow the guide [Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
3. Once the project is opened, **Configure the JDK**: Follow the guide [JetBrains Intellij IDEA: Configure JDK](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) to ensure Intellij is configured to use **JDK 11**.
4. Verify the setup
   1. Once the above steps are completed, locate the file `src/main/java/dinerdirector/DinerDirector.java`, right-click and select `Run 'DinerDirector.main()'`
   2. If setup is correctly done, this is what you should see:
   ```
    Welcome to DinerDirector! Please type "help" for a list of valid commands.
    What can I do for you?
    >

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* JUnit testing is used for this project. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
