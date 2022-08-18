package moi.leetcode2022.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    public static int[] of(String str) {
        return ofList(str).stream().mapToInt(Integer::valueOf).toArray();
    }

    public static List<Integer> ofList(String str) {
        str = str.replaceAll("\\[|\\]", "");
        List<Integer> arrayList = new ArrayList<>();
        for (String var : str.split(",")) {
            arrayList.add(Integer.valueOf(var.trim()));
        }

        return arrayList;
    }

    public static int[][] of2(String str) {
        str = str.substring(1, str.length() - 1);
        List<int[]> arrayList = new ArrayList<>();
        for (String var : str.split("],\\[")) {
            if (var.trim().length() == 0) {
                arrayList.add(new int[]{});
            } else {
                arrayList.add(of(var));
            }
        }

        return arrayList.toArray(new int[arrayList.size()][arrayList.get(0).length]);
    }

    public static List<List<Integer>> ofList2(String str) {
        str = str.substring(1, str.length() - 1);
        List<List<Integer>> arrayList = new ArrayList<>();
        for (String var : str.split("],\\[")) {
            if (var.trim().length() == 0) {
                arrayList.add(new ArrayList<>());
            } else {
                arrayList.add(ofList(var));
            }
        }

        return arrayList;
    }

    public static int[] of(int... nums) {
        return ofList(nums).stream().mapToInt(Integer::valueOf).toArray();
    }

    public static List<Integer> ofList(int... nums) {
        List<Integer> arrayList = new ArrayList<>();
        for (int num : nums) {
            arrayList.add(num);
        }

        return arrayList;
    }

    public static List<List<Integer>> ofList2(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int[] ints : array) {
            result.add(ofList(ints));
        }
        return result;
    }
}
