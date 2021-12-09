package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/12/9.
 * description : 有效的井字游戏
 *
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * 以下是井字游戏的规则：
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 *  
 * 示例 1：
 * 输入：board = ["O  ","   ","   "]
 * 输出：false
 * 解释：玩家 1 总是放字符 "X" 。
 *
 * 示例 2：
 * 输入：board = ["XOX"," X ","   "]
 * 输出：false
 * 解释：玩家应该轮流放字符。
 *
 * 示例 3：
 * 输入：board = ["XXX","   ","OOO"]
 * 输出：false
 *
 * Example 4:
 * 输入：board = ["XOX","O O","XOX"]
 * 输出：true
 *  
 * 提示：
 * board.length == 3
 * board[i].length == 3
 * board[i][j] 为 'X'、'O' 或 ' '
 */
public class ValidGame {

    public boolean validTicTacToe(String[] board) {
        /**
         * 分析：根据游戏规则可以得知
         * 1，玩家1赢，x的数量 = o的数量+1
         * 2，玩家2赢，x的数量 = o的数量
         * 3，没有空位时游戏结束，x的数量 = o的数量
         */
        //1，统计x与o字符的数量
        int xCount = 0;
        int oCount = 0;
        int length = board.length;
        for (int i = 0; i < length; i++) {
            String tem = board[i];
            for (int j = 0; j < tem.length(); j++) {
                if (tem.charAt(j) == 'X') {
                    xCount++;
                } else if (tem.charAt(j) == 'O') {
                    oCount++;
                }
            }
        }
        if (xCount == oCount) {
            //x不存在连续
            return !isWind(board, 'X');
        }
        if ((xCount - oCount) == 1) {
            //o不存在连续
            return !isWind(board, 'O');
        }
        return false;
    }

    private boolean isWind(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            if (c == board[0].charAt(i) && c == board[1].charAt(i) && c == board[2].charAt(i)) {
                //列方向有相同的
                return true;
            }
            if (c == board[i].charAt(0) && c == board[i].charAt(1) && c == board[i].charAt(2)) {
                //行方向相同
                return true;
            }
        }
        //对角线
        if (c == board[0].charAt(0) && c == board[1].charAt(1) && c == board[2].charAt(2)) {
            return true;
        }
        if (c == board[0].charAt(2) && c == board[1].charAt(1) && c == board[2].charAt(0)) {
            return true;
        }
        return false;
    }
}
