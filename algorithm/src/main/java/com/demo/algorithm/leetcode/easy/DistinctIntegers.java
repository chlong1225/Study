package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2024/3/23
 * @author chenglong
 * description : 统计桌面上的不同数字
 *
 * 给你一个正整数n，开始时，它放在桌面上。在10^9天内，每天都要执行下述步骤：
 * 对于出现在桌面上的每个数字x，找出符合1 <= i <= n且满足x % i == 1 的所有数字i。
 * 然后，将这些数字放在桌面上。
 * 返回在 10^9 天之后，出现在桌面上的不同整数的数目。
 * 注意：
 * 一旦数字放在桌面上，则会一直保留直到结束。
 * % 表示取余运算。例如，14 % 3 等于 2 。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：4
 * 解释：最开始，5 在桌面上。
 * 第二天，2 和 4 也出现在桌面上，因为 5 % 2 == 1 且 5 % 4 == 1 。
 * 再过一天 3 也出现在桌面上，因为 4 % 3 == 1 。
 * 在十亿天结束时，桌面上的不同数字有 2 、3 、4 、5 。
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：2
 * 解释：
 * 因为 3 % 2 == 1 ，2 也出现在桌面上。
 * 在十亿天结束时，桌面上的不同数字只有两个：2 和 3 。
 *
 * 提示：
 *
 * 1 <= n <= 100
 */
public class DistinctIntegers {

    public int distinctIntegers(int n) {
        if (n == 1) {
            return 1;
        }
        boolean[] marks = new boolean[n + 1];
        List<Integer> curs = new ArrayList<>();
        curs.add(n);
        marks[n] = true;
        List<Integer> nexts = new ArrayList<>();
        List<Integer> dates = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            dates.add(i);
        }
        while (curs.size() > 0 && dates.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int x = curs.get(i);
                for (int j = dates.size() - 1; j >= 0; j--) {
                    int num = dates.get(j);
                    if (x % num == 1) {
                        if (!marks[num]) {
                            marks[num] = true;
                            nexts.add(num);
                        }
                        dates.remove(j);
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        int count = 0;
        for (int i = 2; i < n + 1; i++) {
            if (marks[i]) {
                count++;
            }
        }
        return count;
    }
}
