package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.

Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.

Constraints:

2 <= arr.length <= 10^5
arr.length is even.
1 <= arr[i] <= 10^5
 */
public class P1338_ReduceArraySizeHalf {

    public static void main(String[] args) {
        test();
    }

    public static int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> references = new HashMap<>();
        int count;
        for (int i : arr) {
            if (references.containsKey(i)) {
                count = references.get(i) + 1;
                references.replace(i, count);
            } else {
                references.put(i, 1);
            }
        }
        references = references
                .entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        Logger.i("sorted references=" + references);

        count = 0;
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : references.entrySet()) {
            count += entry.getValue();
            result++;
            if (count >= arr.length / 2) {
                break;
            }
        }

        return result;
    }

    public static void test() {
        Map<int[], Integer> cases = new HashMap<>();
        cases.put(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}, 2);
        cases.put(new int[]{7, 7, 7, 7, 7, 7}, 1);

        for (Map.Entry<int[], Integer> entry : cases.entrySet()) {
            int[] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.toString(input) + ", except=" + expect);
            int output = minSetSize(input);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\ninput=" + Arrays.toString(input) + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
