---
title: Zhan Hong's Project Portfolio Page 
---

# Zhan Hong's Project Portfolio Page

## Overview 

MovieMate is a desktop application to keep track of movies you have watched or want to watch.
You can additionally give a review to the movies you have watched. It also features ways search movies by name or genre.
The user interacts with it using a CLI, and it is written in Java.

### Summary of Contributions
> :link: [RepoSense page](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=choongzhanhong&breakdown=true)


**Project Foundation**

I mostly helped to build the foundation of the code and overall project, including base classes and functionalities. So, I had
a rough class structure and architecture in mind before we began.

- On top of `Movie` class, I also helped make the `Review` class.
- I adapted the `Storage` class from my individual project and refined it for MovieMate, adding a way to access the database
  and CSV files as well.
- I prepared the **Movie Database**. After downloading from IMDb, I trimmed and cleaned it up for our purposes. 
  - This was done using Python code in the interest of time (as we had to get the data ready to start testing). However,
given more time I could convert the code to Java.
  - I also set up the build settings to include the database file (.csv) in the JAR itself. This required me to
  modify the CSV reader to use Streams instead of normal File accessing.

You may find that my code contributions are larger at the start as I helped to set up the base code structure.
Near the end, I did slow down a little to work on documentation instead of code, as I felt I could offer
more help in writing, but still helped review PRs.

**Team Structure**
- I set up the repository and workflow (issues and milestones), as well as helped direct my team to understand
the workflow and processes.
  - Issue tracker, milestones, and labels were set up by me.
  - I also managed the releases for v1.0 and v2.0.
- I also helped set up the GitHub page, modifying its configs and contributing quite a bit to the DG and UG.
  - Added links in the header for user experience.
  - Most of the DG was primarily written by me.
  - I added the UML style and worked on most of the diagrams too.
  - I helped to re-format and migrate the UG from our Google Docs to markdown formatting.
- I helped to coordinate team meetings and keep the team on track.
    - I made some PR reviews to maintain code standards or to uphold programming principles.
    Check out the PRs I've reviewed [here](https://github.com/AY2223S2-CS2113-W12-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Achoongzhanhong).
    - Some nontrivial PR reviews: [#9](https://github.com/AY2223S2-CS2113-W12-4/tp/pull/9), [#29](https://github.com/AY2223S2-CS2113-W12-4/tp/pull/29),
    [#43](https://github.com/AY2223S2-CS2113-W12-4/tp/pull/43), [#49](https://github.com/AY2223S2-CS2113-W12-4/tp/pull/49),
    [#58](https://github.com/AY2223S2-CS2113-W12-4/tp/pull/58).

**Outside of MovieMate Team Project**
- I also tried to help my peers, and for peer reviews and PE-D bug testing I did submit above-average
number of reviews for both (and received notice of it in my email both times ^o^).
- I also made (few) posts to the [issues page](https://github.com/nus-cs2113-AY2223S2/forum/issues?q=is%3Aopen++author%3Achoongzhanhong).
I think largely, there weren't many issues to comment on. Haha.
- Outside of GitHub, I also help my friends taking this module as well on lecture contents and sharing useful tips.
Even something as simple as weekly reminders to do the quiz :joy:.


