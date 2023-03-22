#!/usr/bin/env bash

# change to script directory
# shellcheck disable=SC2164
cd "${0%/*}"

# shellcheck disable=SC2103
cd ..
./gradlew clean shadowJar

cd text-ui-test

# shellcheck disable=SC2046
java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
# shellcheck disable=SC2181
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
