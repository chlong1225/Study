package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 将数组分成和相等的三个部分
 *
 * 给你一个整数数组arr，只有可以将其划分为三个和相等的非空部分时才返回true，否则返回false。
 * 形式上，如果可以找出索引 i + 1 < j 且满足 (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1]) 就可以将数组三等分。
 *
 * 示例 1：
 * 输入：arr = [0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 *
 * 示例 2：
 * 输入：arr = [0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 *
 * 示例 3：
 * 输入：arr = [3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *
 * 提示：
 * 3 <= arr.length <= 5 * 10^4
 * -10^4 <= arr[i] <= 10^4
 */
public class ThreePartsEqualSum {

    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum % 3 != 0) {
            return false;
        }
        int target = sum / 3;
        //1，遍历查找第一组target
        sum = 0;
        int findIndex1 = -1;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == target) {
                findIndex1 = i;
                break;
            }
        }
        if (findIndex1 == -1) {
            return false;
        }
        //2，第二轮查找
        sum = 0;
        int findIndex2 = -1;
        for (int i = findIndex1 + 1; i < arr.length; i++) {
            sum += arr[i];
            if (sum == target) {
                findIndex2 = i;
                break;
            }
        }
        if (findIndex2 == -1 || findIndex2 == arr.length - 1) {
            return false;
        }
        return true;
    }
}
