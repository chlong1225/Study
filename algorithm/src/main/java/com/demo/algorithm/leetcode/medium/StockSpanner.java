package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2022/10/24
 * @author chenglong
 * description : 股票价格跨度
 *
 * 编写一个StockSpanner类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 * 示例：
 * 输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * 输出：[null,1,1,1,2,1,4,6]
 * 解释：
 * 首先，初始化 S = StockSpanner()，然后：
 * S.next(100) 被调用并返回 1，
 * S.next(80) 被调用并返回 1，
 * S.next(60) 被调用并返回 1，
 * S.next(70) 被调用并返回 2，
 * S.next(60) 被调用并返回 1，
 * S.next(75) 被调用并返回 4，
 * S.next(85) 被调用并返回 6。
 * 注意 (例如) S.next(75) 返回 4，因为截至今天的最后4个价格
 * (包括今天的价格 75) 小于或等于今天的价格。
 *
 * 提示：
 * 调用StockSpanner.next(int price)时，将有1 <= price <= 10^5。
 * 每个测试用例最多可以调用10000次StockSpanner.next。
 * 在所有测试用例中，最多调用150000次StockSpanner.next。
 * 此问题的总时间限制减少了50%。
 */
public class StockSpanner {

    private Deque<int[]> stack = new ArrayDeque<>();
    private int index = -1;

    public StockSpanner() {
        stack.clear();
        index = -1;
        stack.addFirst(new int[]{Integer.MAX_VALUE, index});
    }

    public int next(int price) {
        index++;
        int[] cur = new int[]{price, index};
        int count = 1;
        while (stack.size() > 0) {
            int[] pre = stack.peekLast();
            if (pre[0] <= cur[0]) {
                stack.pollLast();
            } else {
                count = cur[1] - pre[1];
                break;
            }
        }
        stack.addLast(cur);
        return count;
    }
}
