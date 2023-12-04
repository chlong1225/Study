package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 解码异或后的数组
 *
 * 未知整数数组arr由n个非负整数组成。
 * 经编码后变为长度为n-1的另一个整数数组encoded，其中encoded[i]=arr[i] XOR arr[i + 1] 。例如，arr=[1,0,2,1] 经编码后得到encoded=[1,2,3]。
 * 给你编码后的数组encoded和原数组arr的第一个元素first（arr[0]）。
 * 请解码返回原数组arr。可以证明答案存在并且是唯一的。
 *
 * 示例 1：
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 *
 * 示例 2：
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 *
 * 提示：
 * 2 <= n <= 10^4
 * encoded.length == n - 1
 * 0 <= encoded[i] <= 10^5
 * 0 <= first <= 10^5
 */
public class Decode {

    public int[] decode(int[] encoded, int first) {
        int n = encoded.length;
        int[] result = new int[n + 1];
        result[0] = first;
        for (int i = 0; i < n; i++) {
            result[i + 1] = encoded[i] ^ result[i];
        }
        return result;
    }
}
