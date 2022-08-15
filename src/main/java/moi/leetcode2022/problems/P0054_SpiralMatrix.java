package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.*;

/*
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */
public class P0054_SpiralMatrix {

    public static void main(String[] args) {
        test();
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        List<Integer> result = new ArrayList<>(m * n);

        // Initial Boundaries
        int left = 0;
        int top = 0;
        int right = n - 1;
        int bottom = m - 1;

        // Directions: RIGHT, DOWN, LEFT, UP
        final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Initial Direction: RIGHT
        int direction = 0;

        // Initial Position
        int row = 0;
        int column = -1;

        int index = 0;
        while (index < m * n) {
            int[] steps = directions[direction];

            // Keep moving until a boundary is hit
            while (row + steps[0] >= top && row + steps[0] <= bottom && column + steps[1] >= left && column + steps[1] <= right) {
                row = row + steps[0];
                column = column + steps[1];
                result.add(index++, matrix[row][column]);
            }

            // Update boundaries
            switch (direction) {
                case 0 -> top++;
                case 1 -> right--;
                case 2 -> bottom--;
                case 3 -> left++;
            }

            // Change direction
            direction = (direction + 1) % 4;
        }

        return result;
    }

    public static void test() {
        Map<int[][], List<Integer>> cases = new HashMap<>();
        cases.put(ArrayUtil.of2("[[1,2,3],[4,5,6],[7,8,9]]"), ArrayUtil.ofList("[1,2,3,6,9,8,7,4,5]"));
        cases.put(ArrayUtil.of2("[[1,2,3,4],[5,6,7,8],[9,10,11,12]]"), ArrayUtil.ofList("[1,2,3,4,8,12,11,10,9,5,6,7]"));

        for (Map.Entry<int[][], List<Integer>> entry : cases.entrySet()) {
            int[][] input = entry.getKey();
            List<Integer> expect = entry.getValue();
            Logger.i("input=" + Arrays.deepToString(input) + ", except=" + expect);
            List<Integer> output = spiralOrder(input);
            if (output.equals(expect)) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.deepToString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
