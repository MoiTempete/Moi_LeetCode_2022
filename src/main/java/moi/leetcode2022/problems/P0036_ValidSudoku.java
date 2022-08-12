package moi.leetcode2022.problems;

import moi.leetcode2022.utils.Logger;

import java.util.HashMap;
import java.util.HashSet;

/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

 Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
 */
public class P0036_ValidSudoku {

    public static void main(String[] args) {
        boolean isValid;
//        isValid = isValidSudoku(new char[][]{
//                new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}});
        isValid = isValidSudoku(new char[][]{
                new char[]{'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                new char[]{'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                new char[]{'9', '.', '.', '.', '.', '.', '.', '.', '.'}});
        Logger.i("is valid sudoku = " + isValid);
    }

    public static boolean isValidSudoku(char[][] board) {
        HashSet<String> set = new HashSet<>();
        boolean rowUnique;
        boolean columnUnique;
        boolean boxUnique;
        String rowIndex;
        String columnIndex;
        String boxIndex;
        String var;

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                var = String.valueOf(board[row][column]);
                if (".".equals(var)) {
                    continue;
                }
                rowIndex = "row" + row + ":" + var;
                columnIndex = "column" + column + ":" + var;
                boxIndex = "box" + row / 3 + column / 3 + ":" + var;
                Logger.i("rowIndex=" + rowIndex + ", columnIndex=" + columnIndex + ", boxIndex=" + boxIndex);

                rowUnique = set.add(rowIndex);
                columnUnique = set.add(columnIndex);
                boxUnique = set.add(boxIndex);

                Logger.i("rowUnique=" + rowUnique + ", columnUnique=" + columnUnique + ", boxUnique=" + boxUnique);
                if (!rowUnique || !columnUnique || !boxUnique) {
                    return false;
                }
            }
        }

        return true;
    }
}
