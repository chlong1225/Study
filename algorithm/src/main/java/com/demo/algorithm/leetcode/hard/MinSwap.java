package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/10/10
 * @author chenglong
 * description :  使序列递增的最小交换次数
 *
 * 我们有两个长度相等且不为空的整型数组nums1和nums2。在一次操作中，我们可以交换nums1[i]和nums2[i]的元素。
 * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 * 返回使nums1和nums2严格递增所需操作的最小次数。
 * 数组arr严格递增且arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1]。
 *
 * 注意：
 * 用例保证可以实现操作。
 *
 * 示例 1:
 * 输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 *
 * 示例 2:
 * 输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * 输出: 1
 *
 * 提示:
 * 2 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 10^5
 */
public class MinSwap {

    public int minSwap(int[] nums1, int[] nums2) {
        int length = nums1.length;
        int[][] marks = new int[length][2];
        for (int i = 0; i < length; i++) {
            marks[i][0] = length + 10;
            marks[i][1] = length + 10;
        }
        marks[0][0] = 0;
        marks[0][1] = 1;
        for (int i = 1; i < length; i++) {
            //当前位置不交换，前一个位置不交换
            if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
                marks[i][0] = Math.min(marks[i][0], marks[i - 1][0]);
            }
            //前一个位置交换
            if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                marks[i][0] = Math.min(marks[i][0], marks[i - 1][1]);
            }
            //当前位置交换，前一个位置不交换
            if (nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i]) {
                marks[i][1] = Math.min(marks[i][1], marks[i - 1][0]);
            }
            //当前位置交换，前一个位置也交换
            if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
                marks[i][1] = Math.min(marks[i][1], marks[i - 1][1]);
            }
            marks[i][1]++;
        }
        return Math.min(marks[length - 1][0], marks[length - 1][1]);
    }
}
