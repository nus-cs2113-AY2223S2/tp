import pandas as pd
import numpy as np
from pandasql import sqldf
from string import printable # to test character entries

print("import success")

imdb_titles = pd.read_csv("movies.csv", low_memory=False)

imdb_titles = imdb_titles[(imdb_titles['runtimeMinutes'] > 0)]

imdb_titles = imdb_titles.drop(['titleType', 'originalTitle'], axis = 1)

imdb_titles.to_csv("movies_optimised.csv", index=False)

import csv

with open('movies_optimised.csv', 'r', encoding="utf8") as inp, open('edit.csv', 'w', newline="") as out:
    reader = csv.reader(inp, skipinitialspace=True)
    writer = csv.writer(out)
    writer.writerow(next(reader))
    for row in reader:
        try:
            if str(row[1]).isascii():
                writer.writerow(row)
        except:
            continue
print("done")
