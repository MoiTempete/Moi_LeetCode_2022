#!/bin/bash
rm -rf README.md
touch README.md
{
  echo "Moi_LeetCode_2022"
  echo "|Problem ID|Description|"
  echo "|:----------:|:-----------|"
} >>"README.md"

filePath="src/main/java/moi/leetcode2022/problems/"
upPath="../../../../../../"

cd $filePath || exit

count=0
for fileName in *.java; do
  id="${fileName%_*}"
  desc=${fileName#*_}
  count=$((count + 1))
  echo "|""${id#*Problem}""|[""${desc%.*}""](""$filePath""$fileName"")|" >>"$upPath""README.md"
done
echo "|Total|[""${count}"" Problems Solved""](""$filePath"")|" >>"$upPath""README.md"
echo "|Date|[""$(date +%D)""](""$filePath"")|" >>"$upPath""README.md"
