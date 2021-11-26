package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/26.
 * description : 一和零
 *
 * 给你一个二进制字符串数组 strs 和两个整数m和n。
 * 请你找出并返回strs的最大子集的长度，该子集中最多有m个0和n个1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMaxSubset {

    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        //组合的最大子集统计后的0,1数量小于等于m,n即可。无法直接使用marks[m][n],单独使用max进行记录最大
        int max = 0;
        /**
         * 1,构建打表的标记数组，
         * i：代表对应当前数量的子集统计0的数量，
         * j: 代表对应当前数量子集统计1的数量
         * marks记录当前i,j个0,1组成的最大子集数量
         */
        int[][] marks = new int[m + 1][n + 1];
        //2,初始化marks默认均为0
        //3,遍历字符串
        for (int k = 0; k < length; k++) {
            int[] counts = strToCount(strs[k]);
            //当前子集超出了0或1的数量限制,则不可能与其它组合
            if (counts[0] > m || counts[1] > n) {
                continue;
            }
            /**
             * 倒着遍历从后朝前查找
             * 如果findIndexX,findIndexY对应的值大于0,代表之前有findIndexX个0,findIndexY个1组合的子集,
             * 加上当前counts构成新的i个0,j个1的统计.此时子集数量 = marks[findIndexX][findIndexY]+1
             * 但可能之前有其它途径已经拼接了i个0,j个1.两种取最大
             * 特殊情况:统计counts个0和1,最少有一个子集本身
             */
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    int findIndexX = i - counts[0];
                    int findIndexY = j - counts[1];
                    if (findIndexX >= 0 && findIndexY >= 0) {
                        if (findIndexX == 0 && findIndexY == 0) {
                            marks[i][j] = Math.max(marks[i][j], 1);
                            if (marks[i][j] > max) {
                                max = marks[i][j];
                            }
                        } else {
                            if (marks[findIndexX][findIndexY] > 0) {
                                marks[i][j] = Math.max(marks[i][j], marks[findIndexX][findIndexY] + 1);
                                if (marks[i][j] > max) {
                                    max = marks[i][j];
                                }
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    //统计字符串子集中数字0,1的数量
    private int[] strToCount(String str) {
        int[] result = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            result[str.charAt(i) - '0']++;
        }
        return result;
    }
}
