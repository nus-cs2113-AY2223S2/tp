import pandas as pd
import numpy as np
from pandasql import sqldf

print("import success")

# read in data downloaded from https://datasets.imdbws.com/
imdb_movies = pd.read_csv("movies.tsv", sep = '\t', low_memory=False)

# remove unnecessary parts

# get only movies
imdb_titles = imdb_movies[(imdb_movies['titleType'] == 'movie')]

# filter movies between 2010 to 2020
imdb_titles = imdb_titles[(imdb_titles['startYear'] >= '2010') & \
                          (imdb_titles['startYear'] <= '2020')]

imdb_titles = imdb_titles[(imdb_titles['runTime'] > 0)]

# remove adult movies
imdb_titles = imdb_titles[imdb_titles['isAdult'] == '0']

# change \\N to "NA"
imdb_titles = imdb_titles.replace(to_replace = '\\N', value = 'NA')

# remove columns "isAdult" and "endYear".
imdb_titles = imdb_titles.drop(['isAdult', 'endYear'], axis = 1)

print("done")

imdb_titles.to_csv("imdb.csv", index=False)
