package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/5/6
 * @author chenglong
 * description : 最近的请求次数
 *
 * 写一个RecentCounter类来计算特定时间范围内最近的请求。
 *
 * 请你实现 RecentCounter 类：
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间t添加一个新请求，其中t表示以毫秒为单位的某个时间，并返回过去3000毫秒内发生的所有请求数（包括新请求）。
 * 确切地说，返回在[t-3000, t]内发生的请求数。
 * 保证每次对ping的调用都使用比之前更大的t值。
 *
 * 示例 1：
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 *
 * 提示：
 * 1 <= t <= 10^9
 * 保证每次对 ping 调用所使用的t值都严格递增
 * 至多调用 ping 方法 10^4 次
 */
public class RecentCounter {

    private final List<Integer> dates = new ArrayList<>();

    public RecentCounter() {
        dates.clear();
    }

    public int ping(int t) {
        dates.add(t);
        //需要查找比较的起点值
        int find = t - 3000;
        //使用二分查找获取比find小的最大位置
        int index = findIndex(find);
        return dates.size() - index;
    }

    private int findIndex(int target) {
        if (dates.get(0) >= target) {
            return 0;
        }
        int start = 0;
        int end = dates.size() - 1;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (dates.get(middle) < target) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return start;
    }
}
