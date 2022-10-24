package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/10/24
 * @author chenglong
 * description : 第K个语法符号
 *
 * 我们构建了一个包含n行(索引从1开始)的表。首先在第一行我们写上一个0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * 例如，对于n=3，第1行是0，第2行是01，第3行是0110。
 * 给定行数n和序数k，返回第n行中第k个字符。（k从索引1开始）
 *
 * 示例 1:
 * 输入: n = 1, k = 1
 * 输出: 0
 * 解释: 第一行：0
 *
 * 示例 2:
 * 输入: n = 2, k = 1
 * 输出: 0
 * 解释:
 * 第一行: 0
 * 第二行: 01
 *
 * 示例 3:
 * 输入: n = 2, k = 2
 * 输出: 1
 * 解释:
 * 第一行: 0
 * 第二行: 01
 *
 * 提示:
 * 1 <= n <= 30
 * 1 <= k <= 2^(n-1)
 */
public class KthGrammar {

    public int kthGrammar(int n, int k) {
        return dfs(n, k);
    }

    private int dfs(int n, int k) {
        if (k == 1 || n == 1) {
            return 0;
        }
        int pre = (k + 1) / 2;
        int index = (k - 1) % 2;
        int preNum = dfs(n - 1, pre);
        if (preNum == 0) {
            //此时上一行为0，当前行对应01
            return index;
        }
        //此时上一行为1，当前行对应10
        return index == 0 ? 1 : 0;
    }
}
