package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/3/8
 * @author chenglong
 * description : 蜡烛之间的盘子
 *
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从0开始的字符串s，它只包含字符'*'和'|'，其中'*'表示一个盘子，'|'表示一支蜡烛。
 * 同时给你一个下标从0开始的二维整数数组queries，其中queries[i] = [lefti, righti]表示子字符串s[lefti...righti]（包含左右端点的字符）。
 * 对于每个查询，你需要找到 子字符串中在两支蜡烛之间的盘子的数目。如果一个盘子在子字符串中左边和右边都至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间。
 * 比方说，s = "||**||**|*"，查询[3, 8]，表示的是子字符串"*||**|"。子字符串中在两支蜡烛之间的盘子数目为2，子字符串中右边两个盘子在它们左边和右边都至少有一支蜡烛。
 * 请你返回一个整数数组answer，其中answer[i]是第i个查询的答案。
 *
 * 示例 1:
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 *
 * 示例 2:
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0]有9个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 *
 * 提示：
 * 3 <= s.length <= 10^5
 * s只包含字符'*' 和'|'。
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 */
public class PlatesBetweenCandles {

    public int[] platesBetweenCandles(String s, int[][] queries) {
        //1，预处理当前位置左边盘子的个数
        int length = s.length();
        int[] marks = new int[length];
        //当前位置离左边最近的蜡烛(包含自己)
        int[] lefts = new int[length];
        //当前位置离右边最近的蜡烛(包含自己)
        int[] rights = new int[length];
        int left = 0;
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == '*') {
                //当前位置为盘子
                marks[i + 1] = marks[i] + 1;
            } else {
                //当前位置为蜡烛
                marks[i + 1] = marks[i];
                left = i;
            }
            lefts[i] = left;
        }
        if (s.charAt(length - 1) == '*') {
            lefts[left - 1] = left;
        } else {
            lefts[length - 1] = length - 1;
        }
        int right = length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                right = i;
            }
            rights[i] = right;
        }

        int length2 = queries.length;
        int[] answer = new int[length2];
        for (int i = 0; i < length2; i++) {
            //左边界右边蜡烛的位置
            int a = rights[queries[i][0]];
            //右边界左边最近蜡烛的位置
            int b = lefts[queries[i][1]];
            int count = Math.max(marks[b] - marks[a], 0);
            answer[i] = count;
        }
        return answer;
    }
}
