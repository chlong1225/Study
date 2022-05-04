package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/5/4.
 * description : 找出游戏的获胜者
 *
 * 共有n名小伙伴一起做游戏。小伙伴们围成一圈，按顺时针顺序从1到n编号。确切地说从第i名小伙伴顺时针移动一位会到达第(i+1)名小伙伴的位置，
 * 其中1<=i< n，从第n名小伙伴顺时针移动一位会回到第1名小伙伴的位置。
 *
 * 游戏遵循如下规则：
 * 从第1名小伙伴所在位置开始 。
 * 沿着顺时针方向数k名小伙伴，计数时需要包含起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
 * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
 * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的顺时针下一位小伙伴开始，回到步骤2继续执行。
 * 否则，圈子中最后一名小伙伴赢得游戏。
 * 给你参与游戏的小伙伴总数n，和一个整数k，返回游戏的获胜者。
 *
 * 示例 1：
 * 输入：n = 5, k = 2
 * 输出：3
 * 解释：游戏运行步骤如下：
 * 1) 从小伙伴 1 开始。
 * 2) 顺时针数 2 名小伙伴，也就是小伙伴 1 和 2 。
 * 3) 小伙伴 2 离开圈子。下一次从小伙伴 3 开始。
 * 4) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 4 。
 * 5) 小伙伴 4 离开圈子。下一次从小伙伴 5 开始。
 * 6) 顺时针数 2 名小伙伴，也就是小伙伴 5 和 1 。
 * 7) 小伙伴 1 离开圈子。下一次从小伙伴 3 开始。
 * 8) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 5 。
 * 9) 小伙伴 5 离开圈子。只剩下小伙伴 3 。所以小伙伴 3 是游戏的获胜者。
 *
 * 示例 2：
 * 输入：n = 6, k = 5
 * 输出：1
 * 解释：小伙伴离开圈子的顺序：5、4、6、2、3 。小伙伴 1 是游戏的获胜者。
 *
 * 提示：
 * 1 <= k <= n <= 500
 */
public class FindTheWinner {

    public int findTheWinner(int n, int k) {
        List<Integer> dates = new ArrayList<>();
        //1，构建数据源
        for (int i = 1; i <= n; i++) {
            dates.add(i);
        }
        //2，循环遍历删除
        int index = 0;
        while (dates.size() > 1) {
            //删除的位置
            int deleteIndex = (index + k - 1) % dates.size();
            dates.remove(deleteIndex);
            index = deleteIndex % dates.size();
        }
        return dates.get(0);
    }

    public int findTheWinner2(int n, int k) {
        /**
         * 约瑟夫环问题，可以使用动态规划找规则
         * F(n) = [1,2,3....n]删除一个元素，变成了
         * [k+1,k+2,....n,1,...k-1]
         * g(n-1) = [k+1,...n,1,...k-1]
         * f(n) = g(n-1)
         * f(n-1) = [1,2,3,4...n-1]
         * 此时，需要对比g(n-1)与f(n-1)。找到关系后即可转移f(n)与f(n-1)
         * (f(n-1)+k)%n = g(n-1). 但此时存在一个问题。转换后变成了[k+1,k+2,...n-1,0,1,....k-1]
         * 与g(n-1)在中间=n处不相符。故修改为：(f(n-1)+k-1)%n+1
         * f(n) = (f(n-1)+k)%n
         * f(1) = 1
         */
        int[] marks = new int[n + 1];
        marks[1] = 1;
        for (int i = 2; i <= n; i++) {
            marks[i] = (marks[i - 1] + k - 1) % i + 1;
        }
        return marks[n];
    }
}
