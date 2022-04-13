package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by chl on 2022/4/13.
 * description : O(1)时间插入、删除和获取随机元素
 *
 * 实现RandomizedSet 类：
 * RandomizedSet() 初始化RandomizedSet对象
 * bool insert(int val)当元素val不存在时，向集合中插入该项，并返回true ；否则返回false 。
 * bool remove(int val)当元素val存在时，从集合中移除该项，并返回true ；否则返回false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有相同的概率被返回。
 * 你必须实现类的所有函数，并满足每个函数的平均时间复杂度为 O(1) 。
 *
 * 示例：
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 *
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *
 * 提示：
 * -2^31 <= val <= 2^31 - 1
 * 最多调用insert、remove和getRandom函数2*10^5 次
 * 在调用getRandom方法时，数据结构中 至少存在一个 元素。
 */
public class RandomizedSet {

    private Map<Integer, Integer> marks = new HashMap<>();
    private List<Integer> dates = new ArrayList<>();
    private Random random;

    public RandomizedSet() {
        marks.clear();
        dates.clear();
        random = new Random();
    }

    public boolean insert(int val) {
        if (marks.containsKey(val)) {
            return false;
        }
        marks.put(val, dates.size());
        dates.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (marks.containsKey(val)) {
            int index = marks.get(val);
            marks.remove(val);
            if (index == dates.size() - 1) {
                dates.remove(index);
            } else {
                int lastIndex = dates.size() - 1;
                int tem = dates.get(lastIndex);
                dates.set(index, tem);
                dates.remove(lastIndex);
                marks.put(tem, index);
            }
            return true;
        }
        return false;
    }

    public int getRandom() {
        int n = dates.size();
        int index = random.nextInt(n);
        return dates.get(index);
    }
}
