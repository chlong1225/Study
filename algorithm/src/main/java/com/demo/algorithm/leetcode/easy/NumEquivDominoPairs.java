package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 等价多米诺骨牌对的数量
 *
 * 给你一个由一些多米诺骨牌组成的列表dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转0度或180度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i]=[a,b]和dominoes[j]=[c,d]等价的前提是a==c且b==d，或是a==d且 b==c。
 * 在0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i]和dominoes[j]等价的骨牌对(i,j)的数量。
 *
 * 示例：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 *
 * 提示：
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class NumEquivDominoPairs {

    public int numEquivDominoPairs(int[][] dominoes) {
        int n = dominoes.length;
        if (n == 1) {
            return 0;
        }
        int[] counts = new int[100];
        for (int i = 0; i < n; i++) {
            int cur = dominoes[i][0] * 10 + dominoes[i][1];
            counts[cur]++;
        }
        int sum = 0;
        for (int i = 10; i < 100; i++) {
            if (counts[i] > 0) {
                //对应 a=c && b == d
                sum += counts[i] * (counts[i] - 1) / 2;
                int a = i / 10;
                int b = i % 10;
                if (a < b) {
                    int other = b * 10 + a;
                    if (counts[other] > 0) {
                        //对应交换相等的场景：a==d && b==c
                        sum += (counts[i] * counts[other]);
                    }
                }
            }
        }
        return sum;
    }
}
