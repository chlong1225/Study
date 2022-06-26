package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by chl on 2022/6/26.
 * description : 黑名单中的随机数
 *
 * 给定一个整数n和一个无重复黑名单整数数组blacklist。设计一种算法，从[0,n-1]范围内的任意整数中选取一个未加入黑名单blacklist的整数。
 * 任何在上述范围内且不在黑名单blacklist中的整数都应该有同等的可能性被返回。
 * 优化你的算法，使它最小化调用语言内置随机函数的次数。
 *
 * 实现Solution类:
 * Solution(int n, int[] blacklist)初始化整数n和被加入黑名单blacklist的整数
 * int pick()返回一个范围为[0, n - 1]且不在黑名单blacklist中的随机整数
 *
 * 示例 1：
 * 输入
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * 输出
 * [null, 0, 4, 1, 6, 1, 0, 4]
 *
 * 解释
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
 *                  // 0、1、4和6的返回概率必须相等(即概率为1/4)。
 * solution.pick(); // 返回 4
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 6
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 0
 * solution.pick(); // 返回 4
 *
 * 提示:
 * 1 <= n <= 10^9
 * 0 <= blacklist.length <= min(10^5, n - 1)
 * 0 <= blacklist[i] < n
 * blacklist中所有值都 不同
 * pick最多被调用2 * 10^4次
 */
public class Solution {

    private final Random random = new Random();
    private final Map<Integer, Integer> marks = new HashMap<>();
    private int max;

    public Solution(int n, int[] blacklist) {
        int length = blacklist.length;
        /**
         * 分析：0~n-1之间随机一个有效的数字，数量范围变成了n-lenght。
         * 然后与blacklist中重复的数字需要映射到另外一个值。比如：n=8，blackList = {1,3,5}
         * 此时有效的取值数量为：5个。{0,2,4,6,7}。在0~5的取值范围内:1->6 ; 3->7
         */
        max = n - length;
        //构建映射表
        List<Integer> black = new ArrayList<>();
        Set<Integer> big = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (blacklist[i] >= max) {
                big.add(blacklist[i]);
            } else {
                black.add(blacklist[i]);
            }
        }
        Collections.sort(black);
        int start = max;
        for (int i = 0; i < black.size(); i++) {
            int key = black.get(i);
            while (big.contains(start)) {
                start++;
            }
            marks.put(key, start);
            start++;
        }
    }

    public int pick() {
        int num = random.nextInt(max);
        if (marks.containsKey(num)) {
            return marks.get(num);
        }
        return num;
    }
}
