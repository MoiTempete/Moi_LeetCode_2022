package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P0048_RotateImage {

    public static void main(String[] args) {
        test();
    }

    //https://leetcode.com/problems/rotate-image/discuss/2414750/Java-faster-than-100-with-comment-end-example

    /*
     * move numbers like turning circles from outer to inner
     * Imagine that the input array int[][] matrix is a group of nested circles
     * When we want to rotate the array, we need to toggle the circles from the outer to the inner
     * For an array int[n][n], the outermost circle has a perimeter=4*(n-1), and we need to toggle it clockwise by step=n-1
     * And in the inner next one, we need to toggle it clockwise by step=(n-1) - 2
     * For point P(LT) in [i][j], it should be moved to P(RT) at [j][length - 1 - i],
     * and P(RT) should be moved to P(RB) at [length - 1 - i][length - 1 - j],
     * and P(RB) should be moved to P(LB) at [length - 1 - j][i],
     * and P(LB) should be moved to P(LT)
     *
     * eg:
     * input=
     * [[ 1,  2,  3,  4],
     *  [ 5,  6,  7,  8],
     *  [ 9, 10, 11, 12],
     *  [13, 14, 15, 16]]
     *
     * step 1 the outer circle.
     * 1.1
     * [[13,  2,  3,  1],
     *  [ 5,  6,  7,  8],
     *  [ 9, 10, 11, 12],
     *  [16, 14, 15, 4]]
     *
     * 1.2
     * [[13,  9,  3,  1],
     *  [ 5,  6,  7,  2],
     *  [15, 10, 11, 12],
     *  [16, 14,  8,  4]]
     *
     * 1.3
     * [[13,  9,  5,  1],
     *  [14,  6,  7,  2],
     *  [15, 10, 11,  3],
     *  [16, 12,  8,  4]]
     *
     * outer circle done, next we deal with the inner one
     * [[13,  9,  5,  1],
     *  [14, 10,  6,  2],
     *  [15, 11,  7,  3],
     *  [16, 12,  8,  4]]
     * got it!
     */
    public static void rotate(int[][] matrix) {
        int temp;
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = i; j < matrix.length - i - 1; j++) {
                temp = matrix[matrix.length - 1 - j][i];
                matrix[matrix.length - 1 - j][i] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                matrix[matrix.length - 1 - i][matrix.length - 1 - j] = matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    public static void test() {
        Map<int[][], int[][]> cases = new HashMap<>();
        cases.put(
                ArrayUtil.of2("[[1, 2, 3, 4],[5, 6, 7, 8],[9, 10, 11, 12],[13, 14, 15, 16]]"),
                ArrayUtil.of2("[[13, 9, 5, 1],[14, 10, 6, 2],[15, 11, 7, 3],[16, 12, 8, 4]]"));

        for (Map.Entry<int[][], int[][]> entry : cases.entrySet()) {
            int[][] input = entry.getKey();
            int[][] expect = entry.getValue();
            Logger.i("input=" + Arrays.deepToString(input) + ", except=" + Arrays.deepToString(expect));
            rotate(input);
            if (Arrays.deepEquals(input, expect)) {
                Logger.i("case pass by output=" + Arrays.deepToString(input));
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + Arrays.deepToString(expect) + ", but output=" + Arrays.deepToString(input));
            }
        }
        Logger.i("All Pass");
    }
}
