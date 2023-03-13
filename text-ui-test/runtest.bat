@echo off
set local enable extensions
push d %~dp0

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jar loc=%%a
)

java -jar %jar loc% < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT

cd ..\..\text-ui-test

FC ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!
