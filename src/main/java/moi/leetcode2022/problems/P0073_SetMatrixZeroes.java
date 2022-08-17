package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static moi.leetcode2022.utils.ArrayUtil.of2;

/*
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Example 1:

Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:

Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class P0073_SetMatrixZeroes {

    public static void main(String[] args) {
        test();
    }

    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> columns = new HashSet<>();
        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (rows.contains(i) || columns.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void test() {
        Map<int[][], int[][]> cases = new HashMap<>();
        cases.put(of2("[[1,1,1],[1,0,1],[1,1,1]]"), of2("[[1,0,1],[0,0,0],[1,0,1]]"));
        cases.put(of2("[[0,1,2,0],[3,4,5,2],[1,3,1,5]]"), of2("[[0,0,0,0],[0,4,5,0],[0,3,1,0]]"));

        for (Map.Entry<int[][], int[][]> entry : cases.entrySet()) {
            int[][] input = entry.getKey();
            int[][] expect = entry.getValue();
            Logger.i("input=" + Arrays.deepToString(input) + ", except=" + Arrays.deepToString(expect));
            setZeroes(input);
            if (Arrays.deepEquals(input, expect)) {
                Logger.i("case pass by output=" + Arrays.deepToString(input));
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.deepToString(input) +
                        ", expect=" + Arrays.deepToString(expect) + ", but output=" + Arrays.deepToString(input));
            }
        }
        Logger.i("All Pass");
    }
}
