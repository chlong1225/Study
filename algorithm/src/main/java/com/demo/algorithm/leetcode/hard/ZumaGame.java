package com.demo.algorithm.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2021/11/9.
 * description : 祖玛游戏
 *
 * 你正在参与祖玛游戏的一个变种。
 * 在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
 * 你的目标是 清空 桌面上所有的球。每一回合：
 * 从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
 * 接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
 * 如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
 * 如果桌面上所有球都被移除，则认为你赢得本场游戏。
 * 重复这个过程，直到你赢了游戏或者手中没有更多的球。
 * 给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。
 *  
 * 示例 1：
 * 输入：board = "WRRBBW", hand = "RB"
 * 输出：-1
 * 解释：无法移除桌面上的所有球。可以得到的最好局面是：
 * - 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
 * - 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
 * 桌面上还剩着球，没有其他球可以插入。
 *
 * 示例 2：
 * 输入：board = "WWRRBBWW", hand = "WRBRW"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
 * - 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 *
 * 示例 3：
 * 输入：board = "G", hand = "GGGGG"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'G' ，使桌面变为 GG 。
 * - 插入一个 'G' ，使桌面变为 GGG 。GGG -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 *
 * 示例 4：
 * 输入：board = "RBYYBBRRB", hand = "YRBGB"
 * 输出：3
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'Y' ，使桌面变为 RBYYYBBRRB 。RBYYYBBRRB -> RBBBRRB -> RRRB -> B
 * - 插入一个 'B' ，使桌面变为 BB 。
 * - 插入一个 'B' ，使桌面变为 BBB 。BBB -> empty
 * 只需从手中出 3 个球就可以清空桌面。
 *  
 * 提示：
 * 1 <= board.length <= 16
 * 1 <= hand.length <= 5
 * board 和 hand 由字符 'R'、'Y'、'B'、'G' 和 'W' 组成
 * 桌面上一开始的球中，不会有三个及三个以上颜色相同且连着的球
 */
public class ZumaGame {

    //记录桌面小球+手上小球与执行次数
    private Map<String, Integer> marks = new HashMap<>();
    //记录消除操作前后的桌面小球数量
    private Map<String, String> removeBalls = new HashMap<>();

    public int findMinStep(String board, String hand) {
        //1,统计不同颜色的球数量,排除一定不可能清空的情况,同时排除手上无效的小球
        int[] boards = new int[5];
        int[] hands = new int[5];
        int length = board.length();
        for (int i = 0; i < length; i++) {
            char tem = board.charAt(i);
            if (tem == 'R') {
                boards[0]++;
            } else if (tem == 'Y') {
                boards[1]++;
            } else if (tem == 'B') {
                boards[2]++;
            } else if (tem == 'G') {
                boards[3]++;
            } else if (tem == 'W') {
                boards[4]++;
            }
        }
        length = hand.length();
        for (int i = 0; i < length; i++) {
            char tem = hand.charAt(i);
            if (tem == 'R') {
                hands[0]++;
            } else if (tem == 'Y') {
                hands[1]++;
            } else if (tem == 'B') {
                hands[2]++;
            } else if (tem == 'G') {
                hands[3]++;
            } else if (tem == 'W') {
                hands[4]++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (boards[i] != 0) {
                if (boards[i] % 3 == 2 && hands[i] == 0) {
                    return -1;
                }
                if (boards[i] + hands[i] < 3) {
                    //桌面上某种颜色的球加上手上的少于3个,肯定无法消除
                    return -1;
                }
            } else {
                if (hands[i] < 3) {
                    hands[i] = 0;
                }
            }
        }
        //2,修正手上的球
        StringBuilder builder = new StringBuilder();
        if (hands[0] != 0) {
            //添加R球
            for (int i = 0; i < hands[0]; i++) {
                builder.append('R');
            }
        }
        if (hands[1] != 0) {
            //添加Y球
            for (int i = 0; i < hands[1]; i++) {
                builder.append('Y');
            }
        }
        if (hands[2] != 0) {
            //添加B球
            for (int i = 0; i < hands[2]; i++) {
                builder.append('B');
            }
        }
        if (hands[3] != 0) {
            //添加G球
            for (int i = 0; i < hands[3]; i++) {
                builder.append('G');
            }
        }
        if (hands[4] != 0) {
            //添加W球
            for (int i = 0; i < hands[4]; i++) {
                builder.append('W');
            }
        }
        if (builder.length() == 0) {
            return -1;
        }
        hand = builder.toString();
        //3,使用深度遍历+记忆搜索
        marks.clear();
        removeBalls.clear();
        int result = findPath(board, hand);
        return result == 6 ? -1 : result;
    }

    private int findPath(String board, String hand) {
        int result = 6;
        int length = hand.length();
        if (board.length() == 0) {
            return 0;
        }
        if (length == 0) {
            return result;
        }
        String key = board + ":" + hand;
        if (marks.get(key) != null) {
            return marks.get(key);
        }
        char pre = 'A';
        for (int i = 0; i < length; i++) {
            char tem = hand.charAt(i);
            if (tem == pre) {
                //当前插入的小球之前已经插入了相同的小球
                continue;
            }
            //将tem小球插入桌面board
            int bLength = board.length();
            for (int j = 0; j < bLength; j++) {
                if (j > 0) {
                    if (board.charAt(j - 1) == tem) {
                        continue;
                    }
                    if (board.charAt(j - 1) != tem && board.charAt(j) != tem && board.charAt(j - 1) != board.charAt(j)) {
                        continue;
                    }
                }
                StringBuilder newBuild = new StringBuilder();
                newBuild.append(board.substring(0, j));
                newBuild.append(tem);
                newBuild.append(board.substring(j));
                String newBoard = newBuild.toString();
                if (removeBalls.get(newBoard) == null) {
                    clean(newBuild, j);
                    removeBalls.put(newBoard, newBuild.toString());
                }
                StringBuilder newHand = new StringBuilder();
                if (i == 0) {
                    newHand.append(hand.substring(1));
                } else if (i == length - 1) {
                    newHand.append(hand.substring(0, length - 1));
                } else {
                    newHand.append(hand.substring(0, i));
                    newHand.append(hand.substring(i + 1));
                }
                result = Math.min(result, findPath(removeBalls.get(newBoard), newHand.toString()) + 1);
            }
            pre = tem;
        }
        marks.put(key, result);
        return result;
    }

    private void clean(StringBuilder builder, int index) {
        int length = builder.length();
        char compare = builder.charAt(index);
        int before = index;
        int after = index;
        for (int i = index + 1; i < length; i++) {
            if (builder.charAt(i) == compare) {
                after = i;
            } else {
                break;
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            if (builder.charAt(i) == compare) {
                before = i;
            } else {
                break;
            }
        }
        if (after - before >= 2) {
            builder.delete(before, after + 1);
            //找相邻需要被消除的小球
            before--;
            int next = before;
            if (before < 0) {
                next = 0;
            }
            if (builder.length() >= 3) {
                clean(builder, next);
            }
        }
    }
}
