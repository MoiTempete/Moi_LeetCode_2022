#!/bin/bash
rm -rf README.md
touch README.md
{
echo "Moi_LeetCode_2022"
echo "|Problem ID|Description|"
echo "|:----------:|:-----------|"
} >> "README.md"

cd src/main/java/moi/leetcode2022/problems || exit
count=0
for fileName in *.java; do
  id="${fileName%_*}"
  desc=${fileName#*_}
  count=$((count+2))
  echo "|""${id#*Problem}""|[""${desc%.*}""](""$fileName"")|" >> ../../../../../../"README.md"
done
echo "|TOTAL|""${count}"" Problems Solved""|" >> ../../../../../../"README.md"
echo "|DATE|""$(date +%D)""|" >> ../../../../../../"README.md"

