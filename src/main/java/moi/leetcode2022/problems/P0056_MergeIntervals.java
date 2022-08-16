package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

import static moi.leetcode2022.utils.ArrayUtil.of2;

/*
Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals,
and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Constraints:

1 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^4
 */
public class P0056_MergeIntervals {

    public static void main(String[] args) {
        test();
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingDouble(a -> a[0]));
        List<int[]> output = new ArrayList<>();
        output.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int lastEnd = output.get(output.size() - 1)[1];
            if (lastEnd >= intervals[i][0]) {
                output.get(output.size() - 1)[1] = Math.max(intervals[i][1], lastEnd);
            } else {
                output.add(intervals[i]);
            }
        }
        return output.toArray(new int[output.size()][]);
    }

    /*
    bad way

    public int[][] merge(int[][] intervals) {
        int[] result = new int[(int) Math.pow(10, 5)];

        for (int[] array : intervals) {
            for (int i = array[0]; i <= array[1]; i++) {
                if (i == array[1]) {
                    if (result[i] == 0) {
                        result[i] = 3;
                    }
                } else {
                    result[i] = 1;
                }
            }
        }

        List<int[]> list = new ArrayList<>();
        int lastPositiveIndex = -1;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 1) {
                if (lastPositiveIndex == -1) {
                    lastPositiveIndex = i;
                }
            }
            if (result[i] == 3) {
                list.add(new int[]{lastPositiveIndex == -1 ? i : lastPositiveIndex, i});
                lastPositiveIndex = -1;
            }
            if (result[i] == 0) {
                if (lastPositiveIndex >= 0) {
                    lastPositiveIndex = -1;
                }
            }
        }
        return list.toArray(new int[][]{});
    }
     */


    public static void test() {
        Map<int[][], int[][]> cases = new HashMap<>();
        cases.put(of2("[[1,3],[2,7],[8,10],[15,18]]"), of2("[[1,7],[8,10],[15,18]]"));
        cases.put(of2("[[1,4],[0,1]]"), of2("[[0,4]]"));
        cases.put(of2("[[1,4],[0,0]]"), of2("[[0,0],[1,4]]"));
        cases.put(of2("[[1,4],[4,5]]"), of2("[[1,5]]"));
        cases.put(of2("[[1,3],[2,6],[8,10],[15,18],[19,9999]]"), of2("[[1,6],[8,10],[15,18],[19,9999]]"));

        for (Map.Entry<int[][], int[][]> entry : cases.entrySet()) {
            int[][] input = entry.getKey();
            int[][] expect = entry.getValue();
            Logger.i("input=" + Arrays.deepToString(input) + ", except=" + Arrays.deepToString(expect));
            int[][] output = merge(input);
            if (Arrays.deepEquals(output, expect)) {
                Logger.i("case pass by output=" + Arrays.deepToString(output));
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.deepToString(input) + ", expect=" + Arrays.deepToString(expect) + ", but output=" + Arrays.deepToString(output));
            }
        }
        Logger.i("All Pass");
    }
}
