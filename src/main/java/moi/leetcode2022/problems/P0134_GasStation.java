package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.

You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost,

return the starting gas station's index if you can travel around the circuit once in the clockwise direction,

otherwise return -1. If there exists a solution, it is guaranteed to be unique

Example 1:

Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.


Constraints:

n == gas.length == cost.length
1 <= n <= 105
0 <= gas[i], cost[i] <= 104
 */
public class P0134_GasStation {

    public static void main(String[] args) {
        test();
    }

    /*
     * Rule 1: If we want to finish the journey, gas total gain must not less than gas total cost.
     * Rule 2: If a gas station only can supply less gas than we cost to get there,
     *         this station can not be the first station in our journey.
     * Rule 3: Based on Rule No.2, if a section of road only can supply lees gas than we cost,
     *         this section of road can not be the beginning part of our journey.
     * https://leetcode.com/problems/gas-station/discuss/2476962/95%2B-Java-Solution-Time-O(n)-Space-O(1)-with-just-10-lines-of-code
     *
     * About - Why "sum(gas) >= sum(cost) and there is always a starting point"
     * It's simple to proof.
     * As we know. If every station can supply more gas than we get there, we can begin at any station to finish the journey.
     * If there has one or more station only can supply less gas than we get there. No matter where they are or how many there are.
     * We can cut the route into to parts - one has gas surplus and one has gas lack. Because the route is a circle~
     * And as we know the sum(gas) - sum(cost) >= 0, so the amount of gas surplus >= gas lack.
     * So if we begin journey at the beginning gas surplus part, we must be able to finish the trip.
     * https://leetcode.com/problems/gas-station/discuss/2473310/Proof-why-%22sum(gas)-greater-sum(cost)-and-there-is-always-a-starting-point%22
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int gasSurplus = 0;
        int gasSofar = 0;
        int startIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            gasSofar += gas[i] - cost[i];
            gasSurplus += gas[i] - cost[i];
            if (gasSofar < 0) {
                gasSofar = 0;
                startIndex = i + 1;
            }
        }
        return gasSurplus < 0 ? -1 : startIndex;
    }

    public static void test() {
        Map<int[][], Integer> cases = new HashMap<>();
        cases.put(ArrayUtil.of2("[[1,2,3,4,5],[3,4,5,1,2]]"), 3);

        for (Map.Entry<int[][], Integer> entry : cases.entrySet()) {
            int[][] input = entry.getKey();
            int expect = entry.getValue();
            Logger.i("input=" + Arrays.deepToString(input) + ", except=" + expect);
            int output = canCompleteCircuit(input[0], input[1]);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nexpect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }
}
