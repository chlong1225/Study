package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/8/22
 * @author chenglong
 * description : 到最近的人的最大距离
 *
 * 给你一个数组seats表示一排座位，其中seats[i]=1代表有人坐在第i个座位上，seats[i]=0代表座位i上是空的（下标从0开始）。
 * 至少有一个空座位，且至少有一人已经坐在座位上。
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * 返回他到离他最近的人的最大距离。
 *
 * 示例 1：
 * 输入：seats = [1,0,0,0,1,0,1]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是2。
 *
 * 示例 2：
 * 输入：seats = [1,0,0,0]
 * 输出：3
 * 解释：
 * 如果亚历克斯坐在最后一个座位上，他离最近的人有3个座位远。
 * 这是可能的最大距离，所以答案是3。
 *
 * 示例 3：
 * 输入：seats = [0,1]
 * 输出：1
 *
 * 提示：
 * 2 <= seats.length <= 2 * 10^4
 * seats[i] 为 0 或 1
 * 至少有一个 空座位
 * 至少有一个 座位上有人
 */
public class MaxDistToClosest {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int max = 1;
        int left = -1;
        int right = -1;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                if (left == -1) {
                    left = i;
                    //此时[0,left-1]之前为空位
                    if (left > 0) {
                        if (left > max) {
                            max = left;
                        }
                    }
                } else {
                    if (right == -1) {
                        right = i;
                        //此时区间[left+1,right-1]之间为空位
                        int tem = (right - left) / 2;
                        if (tem > max) {
                            max = tem;
                        }
                    } else {
                        //更新区间
                        left = right;
                        right = i;
                        int tem = (right - left) / 2;
                        if (tem > max) {
                            max = tem;
                        }
                    }
                }
            }
        }
        if (right == -1) {
            //此时只有一个位置，区间为[left+1,n-1]
            int tem = n - 1 - left;
            if (tem > max) {
                max = tem;
            }
        } else {
            //最后的区间为[right+1,n-1]
            int tem = n - 1 - right;
            if (tem > max) {
                max = tem;
            }
        }
        return max;
    }
}
