This is a shortened version of the movie database that is much easier to handle without using databases, and smaller file size for easier usage for our purposes.

In essence, we are limiting to only movies in 2010-2020 (avoiding newly created movies in 2023 and just to be safe, 2022-2021, which may cause differences in the files)

Removing adult movies and unnecessary columns as well.

adapted from https://thatdatascienceguy.medium.com/working-with-the-imdb-dataset-589d46d230c6

python file added for reference too. 

I also made another further_filter which drops useless tables like movie type, and secondary title which we won't be using.

Also removed those movies without runtime data and with special characters in the title.