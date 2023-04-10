## Setup

---

* Table of Contents
  {:toc}

---

## Setting up the project on your computer

First, **fork** this repository and clone the fork into your computer.

If you are using IntelliJ IDEA (Highly recommended):
1. **Configure the JDK** to use **JDK 11**.
2. **Import the project as a Gradle project** as we are using gradle to build the project.
3. Verify the setup:
   1. Run `main` and try a few commands.
   2. Run the tests to ensure they all pass.

---

## Code Standards

1. We follow the [CS2113 code style](https://se-education.org/guides/conventions/java/basic.html) and 
some [tweaks are needed in IntelliJ](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to align with it.
2. Ensure any Pull Request passes CI checks. This can be done before a merge. No setup is necessary.
3. Learn about how MovieMate is built by going back to the [DeveloperGuide](DeveloperGuide.md).

---

## Database

We use the `title.basics` database for movies as found on [https://www.imdb.com/interfaces/](IMDb Datasets).

This should already be included in the repository as `movies_db.csv` under the resources root.
There are 5 columns in this CSV:

| tconst    | primaryTitle | startYear       | runtimeMinutes              | genres         |
|-----------|--------------|-----------------|-----------------------------|----------------|
| Unique ID | Movie Title  | Year of Release | Movie Duration (in Minutes) | Up to 3 Genres |
| tt0462335 | High-Rise    | 2015            | 119                         | Drama,Sci-Fi   |

