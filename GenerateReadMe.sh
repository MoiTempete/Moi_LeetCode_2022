#!/bin/bash
rm -rf README.md
touch README.md
{
echo "Moi_LeetCode_2022"
echo "|Problem ID|Description|"
echo "|:----------:|:-----------|"
} >> "README.md"

cd src/main/java/moi/leetcode2022/problems || exit
for fileName in *.java; do
  id="${fileName%_*}"
  desc=${fileName#*_}
  echo "|""${id#*Problem}""|[""${desc%.*}""](""$fileName"")|" >> ../../../../../../"README.md"
done
