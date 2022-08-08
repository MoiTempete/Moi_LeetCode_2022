package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.*;

/**
 * 4. Median of Two Sorted Arrays
 * Hard
 * <p>
 * 18300
 * <p>
 * 2143
 * <p>
 * Add to List
 * <p>
 * Share
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class Problem4_MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1 == null ? 0 : nums1.length;
        int n = nums2 == null ? 0 : nums2.length;
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        if (nums1 == null || nums1.length == 0) {
            if (nums2 != null) {
                if (n <= 1) {
                    return nums2[0];
                } else {
                    return n % 2 == 0 ? (nums2[n / 2 - 1] + nums2[n / 2]) / 2f : nums2[(n - 1) / 2];
                }
            } else {
                return 0.0;
            }
        }

        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0;
    }

    public static void test() {
        List<Case> cases = new ArrayList<>();
        cases.add(new Case(new int[]{1, 3}, new int[]{2}, 2));
        cases.add(new Case(new int[]{1, 2}, new int[]{3, 4}, 2.5));
        cases.add(new Case(new int[]{1, 2, 3}, new int[]{}, 2));
        cases.add(new Case(new int[]{}, new int[]{1}, 1));
        cases.add(new Case(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6}, 4));
        cases.add(new Case(new int[]{}, new int[]{2, 3}, 2.5));

        for (Case testCase : cases) {
            Logger.i(testCase.toString());
            double output = findMedianSortedArrays(testCase.nums1, testCase.nums2);
            if (output == testCase.median) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by output=" + output + ", but expect=" + testCase.median);
            }
        }
        Logger.i("All Pass");
    }

    static class Case {

        int[] nums1;
        int[] nums2;
        double median;

        public Case(int[] nums1, int[] nums2, double median) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            this.median = median;
        }

        @Override
        public String toString() {
            return "num1=" + Arrays.toString(nums1) + ", nums2=" + Arrays.toString(nums2) + ", expect result=" + median;
        }
    }
}
