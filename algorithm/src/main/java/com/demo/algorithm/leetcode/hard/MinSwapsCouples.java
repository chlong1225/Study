package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/11/13
 * @author chenglong
 * description : 情侣牵手
 *
 * n对情侣坐在连续排列的2n个座位上，想要牵到对方的手。
 * 人和座位由一个整数数组row表示，其中row[i]是坐在第i个座位上的人的ID。情侣们按顺序编号，第一对是(0,1)，第二对是(2,3)，以此类推，最后一对是(2n-2,2n-1)。
 * 返回最少交换座位的次数，以便每对情侣可以并肩坐在一起。每次交换可选择任意两人，让他们站起来交换座位。
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
 * n 是偶数
 * 0 <= row[i] < 2n
 * row 中所有元素均无重复
 */
public class MinSwapsCouples {

    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        //1，记录情侣id对应的位置
        int[] marks = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            marks[row[i]] = i;
        }
        //2，依次进行统计
        int step = 0;
        for (int i = 0; i < n; i++) {
            int leftId = row[2 * i];
            int rightId = row[2 * i + 1];
            if (leftId % 2 == 0) {
                if (rightId != leftId + 1) {
                    //此时leftId与rightId不可以组成情侣，需要进行换位置
                    int findId = leftId + 1;
                    int findIndex = marks[findId];
                    //此时交换findId与rightId的位置
                    row[2 * i + 1] = findId;
                    row[findIndex] = rightId;
                    marks[rightId] = findIndex;
                    marks[findId] = 2 * i + 1;
                    step++;
                }
            } else {
                if (rightId != leftId - 1) {
                    //此时rightId与leftId不可以组成情侣，需要进行换位置
                    int findId = leftId - 1;
                    int findIndex = marks[findId];
                    row[2 * i + 1] = findId;
                    row[findIndex] = rightId;
                    marks[findId] = 2 * i + 1;
                    marks[rightId] = findIndex;
                    step++;
                }
            }
        }
        return step;
    }
}
