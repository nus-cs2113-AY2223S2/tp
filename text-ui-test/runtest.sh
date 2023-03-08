#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test

cp save_test.txt save.txt

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Output: PASSED"
    # compare save file to expected save file
    diff save.txt save_result.txt
    if [ $? -eq 0 ]
    then
        echo "Save file: PASSED"
        echo "Test result: PASSED"
        exit 0
    else
        echo "Save file: FAILED"
        echo "Test result: FAILED"
        exit 1
    fi
else
    echo "Output: FAILED"
    echo "Test result: FAILED"
    exit 1
fi
