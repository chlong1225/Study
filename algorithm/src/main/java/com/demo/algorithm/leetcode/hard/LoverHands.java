package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/3/19.
 * description : 情侣牵手
 *
 * n对情侣坐在连续排列的2n个座位上，想要牵到对方的手。
 * 人和座位由一个整数数组row表示，其中 row[i]是坐在第i个座位上的人的ID。情侣们按顺序编号，第一对是(0, 1)，第二对是(2, 3)，以此类推，最后一对是(2n-2, 2n-1)。
 * 返回最少交换座位的次数，以便每对情侣可以并肩坐在一起。 每次交换可选择任意两人，让他们站起来交换座位。
 *
 * 示例 1:
 * 输入: row = [0,2,1,3]
 * 输出: 1
 * 解释: 只需要交换row[1]和row[2]的位置即可。
 *
 * 示例 2:
 * 输入: row = [3,2,0,1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 *
 * 提示:
 * 2n == row.length
 * 2 <= n <= 30
 * n是偶数
 * 0 <= row[i] < 2n
 * row中所有元素均无重复
 */
public class LoverHands {

    public int minSwapsCouples(int[] row) {
        int length = row.length;
        //用于记录i所在的位置
        int[] marks = new int[length];
        for (int i = 0; i < length; i++) {
            marks[row[i]] = i;
        }
        int step = 0;
        for (int i = 0; i < length >> 1; i++) {
            int left = row[2 * i];
            int right = row[2 * i + 1];
            if (left % 2 == 0) {
                if (right == left + 1) {
                    //此时left与right是情侣，不用交换
                    continue;
                }
                //交换
                int findIndex = marks[left + 1];
                row[2 * i + 1] = left + 1;
                row[findIndex] = right;
                //记录表更新
                marks[left + 1] = 2 * i + 1;
                marks[right] = findIndex;
                step++;
            } else {
                if (right == left - 1) {
                    continue;
                }
                int findIndex = marks[left - 1];
                row[2 * i + 1] = left - 1;
                row[findIndex] = right;
                marks[left - 1] = 2 * i + 1;
                marks[right] = findIndex;
                step++;
            }
        }
        return step;
    }
}
