package com.demo.algorithm.leetcode.lcp;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : LCP 22. 黑白方格画
 *
 * 小扣注意到秋日市集上有一个创作黑白方格画的摊位。摊主给每个顾客提供一个固定在墙上的白色画板，画板不能转动。画板上有n * n的网格。
 * 绘画规则为，小扣可以选择任意多行以及任意多列的格子涂成黑色（选择的整行、整列均需涂成黑色），所选行数、列数均可为0。
 * 小扣希望最终的成品上需要有k个黑色格子，请返回小扣共有多少种涂色方案。
 * 注意：两个方案中任意一个相同位置的格子颜色不同，就视为不同的方案。
 *
 * 示例 1：
 * 输入：n = 2, k = 2
 * 输出：4
 * 解释：一共有四种不同的方案： 第一种方案：涂第一列； 第二种方案：涂第二列； 第三种方案：涂第一行； 第四种方案：涂第二行。
 *
 * 示例 2：
 * 输入：n = 2, k = 1
 * 输出：0
 * 解释：不可行，因为第一次涂色至少会涂两个黑格。
 *
 * 示例 3：
 * 输入：n = 2, k = 4
 * 输出：1
 * 解释：共有 2*2=4 个格子，仅有一种涂色方案。
 *
 * 限制：
 * 1 <= n <= 6
 * 0 <= k <= n * n
 */
public class PaintingPlan {

    public int paintingPlan(int n, int k) {
        if (k == 0) {
            return 1;
        }
        if (k < n) {
            return 0;
        }
        if (k == n * n) {
            return 1;
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            //枚举绘制的行，对应的格子数
            int count = i * n;
            if (k >= count) {
                //剩余绘制列时可以新增的格子数
                int last = k - count;
                if (last % (n - i) == 0) {
                    int j = last / (n - i);
                    //此时绘制i行，j列的格子
                    int countI = 1;
                    if (i > 0) {
                        //排列组合的n个中取i个的数量
                        countI = getCount(i, n);
                    }
                    int countJ = 1;
                    if (j > 0) {
                        countJ = getCount(j, n);
                    }
                    total += (countI * countJ);
                }
            }
        }
        return total;
    }

    private int getCount(int m,int n){
        int a = 1;
        for (int i = n - m + 1; i <= n; i++) {
            a *= i;
        }
        int b = 1;
        for (int i = 1; i <= m; i++) {
            b *= i;
        }
        return a / b;
    }
}
