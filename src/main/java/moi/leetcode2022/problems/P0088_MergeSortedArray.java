package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 10^9


Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class P0088_MergeSortedArray {

    public static void main(String[] args) {
        test();
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        m--;
        n--;

        while (n >= 0) {
            if (m >= 0 && nums1[m] > nums2[n]) {
                nums1[i--] = nums1[m--];
            } else {
                nums1[i--] = nums2[n--];
            }
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        /*
        [4, 5, 6, 0, 0, 0], [1, 2, 3]

        [1, 5, 6, 4, 0, 0], [0, 2, 3]
        [1, 5, 6, 4, 0, 0], [0, 2, 3]
         */
        if (nums2 == null || n == 0) {
            return;
        }
        int dstIndex = 0;
        int srcIndex = 0;
        int temp;
        int tailCount = 0;

        Logger.i("nums1=" + Arrays.toString(nums1) + ", nums2=" + Arrays.toString(nums2));
        while (dstIndex < m + n && srcIndex < n) {
            Logger.i("dstIndex=" + dstIndex + ", srcIndex=" + srcIndex);
            if (dstIndex > m - 1 && nums1[dstIndex] == 0) {
                nums1[dstIndex] = nums2[srcIndex];
                srcIndex++;
                Logger.i("at tail of nums1 with only 0");
                Logger.i("nums1=" + Arrays.toString(nums1) + ", nums2=" + Arrays.toString(nums2));
                continue;
            }
            if (nums1[dstIndex] <= nums2[srcIndex]) {
                dstIndex++;
                Logger.i("nums1[dstIndex] <= nums2[srcIndex]");
            } else {
                Logger.i("nums1[dstIndex] > nums2[srcIndex]");
                temp = nums1[dstIndex];
                nums1[dstIndex] = nums2[srcIndex];
                nums2[srcIndex] = temp;

                temp = nums2[srcIndex];
                nums2[srcIndex] = nums1[m + tailCount];
                nums1[m + tailCount] = temp;

                tailCount++;
                dstIndex++;
                srcIndex++;
            }
            Logger.i("nums1=" + Arrays.toString(nums1) + ", nums2=" + Arrays.toString(nums2));
        }
    }

    public static void test() {
        Map<Case, int[]> cases = new HashMap<>();
//        cases.put(new Case(new int[]{0}, 0, new int[]{1}, 1), new int[]{1});
        cases.put(new Case(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3), new int[]{1, 2, 2, 3, 5, 6});
        cases.put(new Case(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3), new int[]{1, 2, 3, 4, 5, 6});

        for (Map.Entry<Case, int[]> entry : cases.entrySet()) {
            Case input = entry.getKey();
            int[] nums1 = Arrays.copyOf(input.nums1, input.nums1.length);
            int[] expect = entry.getValue();
            Logger.i("input=" + input + ", except=" + Arrays.toString(expect));
            merge(nums1, input.m, input.nums2, input.n);
            if (Arrays.equals(expect, nums1)) {
                Logger.i("case pass by output=" + Arrays.toString(nums1));
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + input + ", expect=" + Arrays.toString(expect) + ", but output=" + Arrays.toString(nums1));
            }
        }
        Logger.i("All Pass");
    }

    public static class Case {
        int[] nums1, nums2;
        int m, n;

        public Case(int[] nums1, int m, int[] nums2, int n) {
            this.m = m;
            this.n = n;
            this.nums1 = nums1;
            this.nums2 = nums2;
        }

        @Override
        public String toString() {
            return "nums1=" + Arrays.toString(nums1) + ", m=" + m + ", nums2=" + Arrays.toString(nums2) + ", n=" + n;
        }
    }
}
