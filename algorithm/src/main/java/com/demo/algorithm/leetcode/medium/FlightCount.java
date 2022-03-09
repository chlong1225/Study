package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/9.
 * description : 航班预订统计
 *
 * 这里有n个航班，它们分别从1到n进行编号。
 * 有一份航班预订表bookings，表中第i条预订记录bookings[i]=[firsti,lasti,seatsi]意味着在从firsti到lasti（包含firsti和lasti）的每个航班上预订了seatsi个座位。
 * 请你返回一个长度为n的数组answer，里面的元素是每个航班预定的座位总数。
 *
 * 示例 1：
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 *
 * 示例 2：
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 *
 * 提示：
 * 1 <= n <= 2 * 10^4
 * 1 <= bookings.length <= 2 * 10^4
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 10^4
 */
public class FlightCount {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] marks = new int[n + 2];
        int length = bookings.length;
        //1，构建差分数组
        for (int i = 0; i < length; i++) {
            marks[bookings[i][0]] += bookings[i][2];
            marks[bookings[i][1] + 1] -= bookings[i][2];
        }
        //2，差分数组迭代获取当前位置的数据
        int[] result = new int[n];
        result[0] = marks[1];
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] + marks[i + 1];
        }
        return result;
    }
}
