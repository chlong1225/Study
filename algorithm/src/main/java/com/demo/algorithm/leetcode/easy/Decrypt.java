package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/9/24
 * @author chenglong
 * description : 拆炸弹
 *
 * 你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为n的循环数组code以及一个密钥k。
 * 为了获得正确的密码，你需要替换掉每一个数字。所有数字会同时被替换。
 * 如果k > 0，将第i个数字用接下来k个数字之和替换。
 * 如果k < 0，将第i个数字用之前k个数字之和替换。
 * 如果k == 0，将第i个数字用0替换。
 * 由于code是循环的，code[n-1]下一个元素是code[0]，且code[0]前一个元素是code[n-1]。
 * 给你循环数组code和整数密钥k，请你返回解密后的结果来拆除炸弹！
 *
 * 示例 1：
 * 输入：code = [5,7,1,4], k = 3
 * 输出：[12,10,16,13]
 * 解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
 *
 * 示例 2：
 * 输入：code = [1,2,3,4], k = 0
 * 输出：[0,0,0,0]
 * 解释：当 k 为 0 时，所有数字都被 0 替换。
 *
 * 示例 3：
 * 输入：code = [2,4,9,3], k = -2
 * 输出：[12,5,6,13]
 * 解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
 *
 * 提示：
 * n == code.length
 * 1 <= n<= 100
 * 1 <= code[i] <= 100
 * -(n - 1) <= k <= n - 1
 */
public class Decrypt {

    public int[] decrypt(int[] code, int k) {
        int length = code.length;
        int[] result = new int[length];
        if (k == 0) {
            for (int i = 0; i < length; i++) {
                result[i] = 0;
            }
        } else if (k > 0) {
            int sum = 0;
            for (int i = 1; i <= k; i++) {
                sum += code[i];
            }
            result[0] = sum;
            for (int i = 1; i < length; i++) {
                sum -= code[i];
                sum += code[(i + k) % length];
                result[i] = sum;
            }
        } else {
            int sum = 0;
            k = -k;
            for (int i = length - k; i < length; i++) {
                sum += code[i];
            }
            result[0] = sum;
            for (int i = 1; i < length; i++) {
                sum += code[i - 1];
                sum -= code[(i - 1 + length - k) % length];
                result[i] = sum;
            }
        }
        return result;
    }
}
