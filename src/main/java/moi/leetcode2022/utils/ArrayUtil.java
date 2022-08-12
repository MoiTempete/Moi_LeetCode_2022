package moi.leetcode2022.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    public static int[] of(String str) {
        str = str.replaceAll("\\[|\\]", "");
        List<Integer> arrayList = new ArrayList<>();
        for (String var : str.split(",")) {
            arrayList.add(Integer.valueOf(var.trim()));
        }

        return arrayList.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static int[][] of2(String str) {
        str = str.substring(1, str.length() - 1);
        List<int[]> arrayList = new ArrayList<>();
        for (String var : str.split("],\\[")) {
            arrayList.add(of(var));
        }

        return arrayList.toArray(new int[arrayList.size()][arrayList.get(0).length]);
    }

    public static int[] of(int... nums) {
        List<Integer> arrayList = new ArrayList<>();
        for (int num : nums) {
            arrayList.add(num);
        }

        return arrayList.stream().mapToInt(Integer::valueOf).toArray();
    }
}
