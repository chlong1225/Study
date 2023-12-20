package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/19
 * @author chenglong
 * description : 水壶问题
 *
 * 有两个水壶，容量分别为jug1Capacity和jug2Capacity升。水的供应是无限的。确定是否有可能使用这两个壶准确得到targetCapacity升。
 * 如果可以得到targetCapacity升水，最后请用以上水壶中的一或两个来盛放取得的targetCapacity升水。
 * 你可以：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 *
 * 示例 1:
 * 输入: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
 * 输出: true
 * 解释：来自著名的 "Die Hard"
 *
 * 示例 2:
 * 输入: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
 * 输出: false
 *
 * 示例 3:
 * 输入: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
 * 输出: true
 *
 * 提示:
 * 1 <= jug1Capacity, jug2Capacity, targetCapacity <= 10^6
 */
public class MeasureWater {

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        //1，判断特殊场景，限制target的范围
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }
        int tem;
        if (jug1Capacity > jug2Capacity) {
            tem = gcd(jug1Capacity, jug2Capacity);
        } else {
            tem = gcd(jug2Capacity, jug1Capacity);
        }
        return targetCapacity % tem == 0;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
