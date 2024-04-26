package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2024/4/26
 * @author chenglong
 * description : 快照数组
 *
 * 实现支持下列接口的「快照数组」- SnapshotArray：
 * SnapshotArray(int length) - 初始化一个与指定长度相等的类数组的数据结构。初始时，每个元素都等于0。
 * void set(index, val) - 会将指定索引index处的元素设置为val。
 * int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去1）。
 * int get(index, snap_id) - 根据指定的snap_id选择快照，并返回该快照指定索引index的值。
 *
 * 示例：
 * 输入：["SnapshotArray","set","snap","set","get"]
 *      [[3],[0,5],[],[0,6],[0,0]]
 * 输出：[null,null,0,null,5]
 * 解释：
 * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
 * snapshotArr.set(0,5);  // 令 array[0] = 5
 * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
 *
 * 提示：
 * 1 <= length <= 50000
 * 题目最多进行50000 次set，snap，和 get的调用 。
 * 0 <= index < length
 * 0 <= snap_id < 我们调用 snap() 的总次数
 * 0 <= val <= 10^9
 */
public class SnapshotArray {

    private final List<int[]>[] dates;
    private int snapIndex;

    public SnapshotArray(int length) {
        dates = new List[length];
        for (int i = 0; i < length; i++) {
            List<int[]> item = new ArrayList<>();
            item.add(new int[]{0, 0});
            dates[i] = item;
        }
        snapIndex = 0;
    }

    public void set(int index, int val) {
        List<int[]> items = dates[index];
        //获取最后一条数据
        int[] last = items.get(items.size() - 1);
        if (last[0] == snapIndex) {
            last[1] = val;
        } else {
            items.add(new int[]{snapIndex, val});
        }
    }

    public int snap() {
        snapIndex++;
        return snapIndex - 1;
    }

    public int get(int index, int snap_id) {
        List<int[]> items = dates[index];
        //查找最近的值
        if (snap_id == 0) {
            return items.get(0)[1];
        }
        if (snap_id >= snapIndex) {
            return items.get(items.size() - 1)[1];
        }
        //使用二分查找
        int start = 0;
        int end = items.size() - 1;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (items.get(middle)[0] == snap_id) {
                return items.get(middle)[1];
            } else if (items.get(middle)[0] > snap_id) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        if (items.get(start)[0] > snap_id) {
            return items.get(start - 1)[1];
        } else {
            return items.get(start)[1];
        }
    }
}
