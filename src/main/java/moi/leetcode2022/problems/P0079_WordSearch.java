package moi.leetcode2022.problems;

import moi.leetcode2022.utils.ArrayUtil;
import moi.leetcode2022.utils.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Example 1:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class P0079_WordSearch {

    public static void main(String[] args) {
        test();
    }

    private static boolean[][] included;
    private static char[][] board;
    private static String word;

    //DFS
    public static boolean exist(char[][] b, String w) {
        word = w;
        board = b;

        final int totalRows = board.length;
        final int totalCols = board[0].length;
        included = new boolean[totalRows][totalCols];

        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                if (board[i][j] == word.charAt(0) && dfs(i, j, 0)) return true;
            }
        }

        return false;
    }

    private static boolean dfs(int i, int j, int index) {
        if (index == word.length() - 1) return true;

        included[i][j] = true;
        final char nextChar = word.charAt(index + 1);

        // up
        if (i > 0 && board[i - 1][j] == nextChar && !included[i - 1][j] && dfs(i - 1, j, index + 1)) {
            return true;
        }

        // down
        if (i < board.length - 1 && board[i + 1][j] == nextChar && !included[i + 1][j] && dfs(i + 1, j, index + 1)) {
            return true;
        }

        // left
        if (j > 0 && board[i][j - 1] == nextChar && !included[i][j - 1] && dfs(i, j - 1, index + 1)) {
            return true;
        }

        // right
        if (j < board[0].length - 1 && board[i][j + 1] == nextChar && !included[i][j + 1] && dfs(i, j + 1, index + 1)) {
            return true;
        }

        included[i][j] = false;

        return false;
    }

    public static void test() {
        HashMap<Case, Boolean> cases = new HashMap<>();
        cases.put(new Case(ArrayUtil.ofChar2("[[A,B,C,E],[S,F,C,S],[A,D,E,E]]"), "ABCCED"), true);
        cases.put(new Case(ArrayUtil.ofChar2("[[A,B,C,E],[S,F,C,S],[A,D,E,E]]"), "SEE"), true);
        cases.put(new Case(ArrayUtil.ofChar2("[[A,B,C,E],[S,F,C,S],[A,D,E,E]]"), "ABCB"), false);

        for (Map.Entry<Case, Boolean> entry : cases.entrySet()) {
            Case input = entry.getKey();
            boolean expect = entry.getValue();
            Logger.i("board=" + Arrays.deepToString(input.board) + ", word=" + input.word + ", except=" + expect);
            boolean output = exist(input.board, input.word);
            if (output == expect) {
                Logger.i("case pass by output=" + output);
            } else {
                throw new AssertionError("case fail by:" + "\nboard=" + Arrays.deepToString(input.board) + ", word=" + input.word + ", expect=" + expect + ", but output=" + output);
            }
        }
        Logger.i("All Pass");
    }

    public static class Case {
        char[][] board;
        String word;

        public Case(char[][] board, String word) {
            this.word = word;
            this.board = board;
        }
    }
}
