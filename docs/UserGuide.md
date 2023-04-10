# MovieMate User Guide
MovieMate is a command-line app for managing your own list of movies that you’ve watched or planning to watch.
It allows you to store your ratings and reviews for different movies and give you a quick glance of your movie list.
You can also keep track of all the movies you plan to watch separately.
Moreover, it allows you to filter specific types of movies to help you search for the movie you want effectively.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help--help)
  - [Add a movie you have watched: `watched`](#add-a-movie-you-have-watched--watched)
  - [Add a movie you want to watch: `towatch`](#add-a-movie-you-want-to-watch--towatch)
    - [List out movies you have watched: `list`](#list-out-movies-you-have-watched-or-plan-to-watch--list--watchlist)
    - [Remove a listing from watched/to-watch list: `remove`](#remove-a-listing-from-either-list--remove)
  - [See a movie's details: `seedetail`](#see-a-movies-details--seedetail)
  - [Filter movies from your list by genres: `filter`](#filter-movies-from-your-list-by-genres--filter)
  - [Review a movie you have watched: `review`](#review-a-movie-you-have-watched--addreview)
    - [Delete a review: `deletereview`](#delete-a-review--deletereview)
    - [View a review: `viewreview`](#view-a-review--viewreview)
  - [Exit and save: `bye` or `exit`](#exit-and-save--bye-or-exit)
  

<hr>

# Quick Start

1. Ensure you have Java 11 or above installed in your Computer. 
2. Download the latest `moviemate.jar` from [here](https://github.com/AY2223S2-CS2113-W12-4/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your MovieMate.
4. Open a command terminal, `cd` into the folder you put the jar file in,
and use the `java -jar moviemate.jar` command to run the application. 
5. You should then be able to use MovieMate through the command terminal.
6. Type the command in the command box and press Enter to execute it.
For example, you can start by typing the `help` command.
7. Refer to the [Features section](#features) below for details of each command.

<hr>

# Features
> ❗ **Note about usage:**
> 
> Words in `<angle brackets>` are parameters to be supplied by the user.\
> e.g. to add a watched movie with `watched <movie name>`,
> `<movie name>` is a parameter (spaces allowed), such as in `watched La La Land`.
> 
> Commands that do not take in parameters (such as `help`) will ignore any additional parameters.\
> e.g. inputting `help 123` will be interpreted as `help`.
>
> In the below examples, text following `>>` indicates user input.
> In addition, text without following `>>` indicates system output.
> For any command that is followed by a prompt for an index input, you can input a '0' to cancel the current command.

## Viewing help: `help`
Will print the extract of the commands that the user can use.
It will also provide the link of user guide to the user.

Format: `help`

Example:
```
>> help
First time using MovieMate? Below is the quick guide for you to get to know about how to use the app ...
Add A Movie to the watched List: watched
Adds a movie to the list of watched movies.
If the item currently exists in the watchlist, it will be moved to the watched list.
...
To know more about MovieMate's command. Please check the user guide.
Here is the link to the user guide: 
https://ay2223s2-cs2113-w12-4.github.io/tp/UserGuide.html
Hope it helps, and have a nice day!
-------------------------------------------------------------------------
```

## Add a movie you have watched: `watched`
Adds a movie to your watched list.

Format: `watched <movie name> [enter] <movie index>`

> ❗ **Tip**: you may provide an incomplete title.
>___________________________________________________________________________
> You will then be prompted to input an index to pick a movie among a few similar titles.
> 
> ❗ **Tip**: you can type 0 to exit inputting index.
>____________________________________________________________________________
> If the movie exists in your to-watch list, this will move the list to your watched list.

Examples:
```
>> watched La La
1. Acting Outlaws: The LA La Ride (2012)  [Documentary]
2. Ana Maria in Novela Land (2015)  [Comedy,Drama,Fantasy]
3. Gary Numan: Android in La La Land (2016)  [Biography,Documentary,Music]
4. Going Down in LA-LA Land (2011)  [Comedy,Drama,Romance]
5. I cavalieri della laguna (2014)  [Documentary]
Please enter the id of the movie you're looking for
The program will then proceed with adding the movie you chose, thanks!
-------------------------------------------------------------------------

>> 1
You have successfully added the movie into your list!
Here is the movie detail
Acting Outlaws: The LA La Ride (2012)  [Documentary]
Feel free to continue with other features
-------------------------------------------------------------------------
```

## Add a movie you want to watch: `towatch`
Adds an unwatched movie to your to-watch list.

Format: `towatch <movie name> [enter] <movie index>`
> ❗ **Note:** Functionality is similar to [`watched`](#add-a-movie-you-have-watched--watched).

### List out movies you have watched or plan to watch: `list`/`watchlist`
Show a list of all the movies you have watched.

Format:\
`list` for movies you have watched.\
`watchlist` for movies you plan to watch.

Example:
```
>> list

1. A Captain Unafraid
2. #cats_the_mewvie
3. !Women Art Revolution
-------------------------------------------------------------------------
These are the movies in your list.
-------------------------------------------------------------------------
```

### Remove a listing from either list: `remove`
Removes a movie from either list of movies.
You will first choose which list to remove from, and then separately provide the movie number to delete.

Format:\
`remove watched [enter] <movie index>` to remove a movie from your watchlist.\
`remove towatch [enter] <movie index>` to remove a movie from your to-watch list.

Examples:
```
>> remove watched
1. Avengers: Endgame
2. La La Land
-------------------------------------------------------------------------
These are the movies in your list
Please enter the id of the movie you would like to remove

>> 1
The movie has been deleted for you!
-------------------------------------------------------------------------

>> list
1. La La Land
```
> ❗ **Coming soon:** we will add better feedback for user after removal.

## See a movie's details: `seedetail`
See the details of the movie as provided by the database, by index or movie name.\
This does not include user-specific properties, like reviews.

Format:\
`seedetail watched [enter] <movie index>` to see the detail of a movie in your watched list.\
`seedetail towatch [enter] <movie index>` to see the detail of a movie in your to-watch list.

Examples:
```
>> seedetail watched
1. #cats_the_mewvie
2. !Women Art Revolution
3. Acting Outlaws: The LA La Ride
-------------------------------------------------------------------------
These are the movies in your list
Please enter the index of the movie you would like to see the detail of!

>> 1
Title: #cats_the_mewvie 
Year: 2020
Genres:  [Documentary]
Runtime Minutes: 90
The movie details are shown above!
Please feel free to continue with other features :))
-------------------------------------------------------------------------
```

Format: `seedetail movie [enter] <movie index>`

Examples:
```
>> seedetail movie
Please enter the name of the movie you would like to see the detail of!

>> cat
1. #cats_the_mewvie (2020)  [Documentary]
2. 1948: Creation & Catastrophe (2017)  [Documentary]
3. 2007: The Deadliest Year For Cats And Dogs In American History (2018)  [Documentary]
4. 22nd Catch (2016)  [Romance]
5. 3 Cats and a Man (2012)  [Drama]
Please enter the id of the movie you're looking for
The program will then proceed with showing the detail of the movie you chose, thanks!
-------------------------------------------------------------------------

>> 1
Title: #cats_the_mewvie
Year: 2020
Genres:  [Documentary]
Runtime Minutes: 90
-------------------------------------------------------------------------
The movie details are showed above!
Please feel free to continue with other features :))
-------------------------------------------------------------------------
```

## Filter movies from your list by genres: `filter`
Filters out and displays all movies in the watched and unwatched lists that fall under the specified genre.

Format: `filter <genre>`

Examples:
```
>> filter comedy

In watched list:
1. Maya the Bee Movie (2014)  [Adventure,Animation,Comedy]

In to-watch list:
There are no movies of this genre in this list
-------------------------------------------------------------------------
```

> ❗ **Coming soon:** we will add a way to list/search genres, in case the user makes a typo.

## Review a movie you have watched: `addreview`
Adds a review for a watched movie. The user is first prompted for a text review, which ends once the user
enters a blank line. The user is then asked for a numerical rating (out of 5).

Format: `addreview [enter] <movie index>`

Examples:
```
>> addreview
1. #cats_the_mewvie
2. !Women Art Revolution
3. Acting Outlaws: The LA La Ride
4. Maya the Bee Movie
-------------------------------------------------------------------------
These are the movies in your list.

>> 1
This is the current movie that you want to review:
#cats_the_mewvie
Write your review. Use as many lines as you need. To end, simply input a blank line.

>> Such a great movie!
>>
Text review added.
Please rate the movie from [0 to 5]:

>> 5
Star review added.
-------------------------------------------------------------------------
```

Reviews can also be deleted and viewed with the following commands:

### Delete a review: `deletereview`
Delete a review for a watched movie.
Format: `deletereview [enter] <movie index>`

Examples:
```
>> deletereview
1. #cats_the_mewvie
2. !Women Art Revolution
3. Acting Outlaws: The LA La Ride
4. Maya the Bee Movie
-------------------------------------------------------------------------
These are the movies in your list.

>> 1
Successfully deleted the review of movie 1.
-------------------------------------------------------------------------
```

### View a review: `viewreview`
Allows you to see your review of a movie.
Format: `viewreview [enter] <movie index>`

Examples:
```
>> viewreview
1. #cats_the_mewvie
2. !Women Art Revolution
3. Acting Outlaws: The LA La Ride
4. Maya the Bee Movie
-------------------------------------------------------------------------
These are the movies in your list.

>> 1
Review: Such a great movie!(5/5 stars)
-------------------------------------------------------------------------
```

## Generate random movies from the genre entered by you: `random`
Provide some random movies that fall in the genre entered
Format: random <genre>

Examples:
```
>> random comedy

Below are the random movies that fall in the genre entered :))
1. #Modus (2016)  [Comedy]
2. #Sequence (2013)  [Comedy,Drama,Family]
3. #TubeClash02 (2016)  [Animation,Comedy,Drama]
4. $50K and a Call Girl: A Love Story (2014)  [Action,Adventure,Comedy]
5. 'This Is Not an American Movie' (2011)  [Action,Comedy,Crime]
Feel free to add it to your movie list!
-------------------------------------------------------------------------
```
> ❗ **Coming soon:** we will add a way to list/search genres, in case the user makes a typo.


## Exit and save: `bye` or `exit`
Closes MovieMate and saves your data.
> ❗ Make sure to use this command instead of closing the window.
> This will save the data. Closing the window in other ways will not save the data.

Format: `bye` or `exit`
Examples:
```
>> bye

-------------------------------------------------------------------------
Thanks for using Movie Mate!
Hope to see you again soon :))
```

