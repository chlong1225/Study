package com.demo.algorithm.leetcode.contest.doubleweek83;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * create on 2022/8/2
 * @author chenglong
 * description : 设计数字容器系统
 *
 * 设计一个数字容器系统，可以实现以下功能：
 * 在系统中给定下标处插入或者替换一个数字。
 * 返回系统中给定数字的最小下标。
 *
 * 请你实现一个NumberContainers类：
 * NumberContainers()初始化数字容器系统。
 * void change(int index, int number)在下标index处填入number。如果该下标index处已经有数字了，那么用number替换该数字。
 * int find(int number)返回给定数字number在系统中的最小下标。如果系统中没有number，那么返回-1。
 *
 * 示例：
 * 输入：
 * ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
 * [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
 * 输出：
 * [null, -1, null, null, null, null, 1, null, 2]
 * 解释：
 * NumberContainers nc = new NumberContainers();
 * nc.find(10); // 没有数字 10 ，所以返回 -1 。
 * nc.change(2, 10); // 容器中下标为 2 处填入数字 10 。
 * nc.change(1, 10); // 容器中下标为 1 处填入数字 10 。
 * nc.change(3, 10); // 容器中下标为 3 处填入数字 10 。
 * nc.change(5, 10); // 容器中下标为 5 处填入数字 10 。
 * nc.find(10); // 数字 10 所在的下标为 1 ，2 ，3 和 5 。因为最小下标为 1 ，所以返回 1 。
 * nc.change(1, 20); // 容器中下标为 1 处填入数字 20 。注意，下标 1 处之前为 10 ，现在被替换为 20 。
 * nc.find(10); // 数字 10 所在下标为 2 ，3 和 5 。最小下标为 2 ，所以返回 2 。
 *
 * 提示：
 * 1 <= index, number <= 10^9
 * 调用change和find的总次数不超过10^5次。
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class NumberContainers {

    //记录当前位置与对应值的关系
    private final Map<Integer, Integer> marks = new HashMap<>();
    //记录当前值与所有位置的关系
    private final Map<Integer, PriorityQueue<Integer>> dates = new HashMap<>();

    public NumberContainers() {
        dates.clear();
        marks.clear();
    }


    public void change(int index, int number) {
        //防止添加重复的值
        if (marks.containsKey(index) && marks.get(index) == number) {
            return;
        }
        marks.put(index, number);
        if (dates.containsKey(number)) {
            dates.get(number).offer(index);
        } else {
            PriorityQueue<Integer> items = new PriorityQueue<>((o1, o2) -> o1 - o2);
            items.offer(index);
            dates.put(number, items);
        }
    }

    public int find(int number) {
        if (dates.containsKey(number)) {
            PriorityQueue<Integer> queue = dates.get(number);
            while (!queue.isEmpty()) {
                int index = queue.peek();
                if (marks.get(index) == number) {
                    return index;
                } else {
                    queue.poll();
                }
            }
        }
        return -1;
    }
}
