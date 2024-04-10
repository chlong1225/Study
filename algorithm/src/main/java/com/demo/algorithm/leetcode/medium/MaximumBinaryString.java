package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/4/10
 * @author chenglong
 * description : 修改后的最大二进制字符串
 *
 * 给你一个二进制字符串binary，它仅有0或者1组成。你可以使用下面的操作任意次对它进行修改：
 * 操作1 ：如果二进制串包含子字符串"00"，你可以用"10"将其替换。
 * 比方说，"00010" -> "10010"
 * 操作2 ：如果二进制串包含子字符串"10"，你可以用"01"将其替换。
 * 比方说， "00010" -> "00001"
 * 请你返回执行上述操作任意次以后能得到的最大二进制字符串。如果二进制字符串x对应的十进制数字大于二进制字符串y对应的十进制数字，那么我们称二进制字符串x大于二进制字符串y。
 *
 * 示例 1：
 * 输入：binary = "000110"
 * 输出："111011"
 * 解释：一个可行的转换为：
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 *
 * 示例 2：
 * 输入：binary = "01"
 * 输出："01"
 * 解释："01" 没办法进行任何转换。
 *
 * 提示：
 * 1 <= binary.length <= 10^5
 * binary仅包含'0'和'1'。
 */
public class MaximumBinaryString {

    public String maximumBinaryString(String binary) {
        int n = binary.length();
        //1，统计第一个0的位置与0的数量
        int findIndex = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (binary.charAt(i) == '0') {
                count++;
                if (findIndex == -1) {
                    findIndex = i;
                }
            }
        }
        //2，少于两个时，无法修改更大
        if (count < 2) {
            return binary;
        }
        //3，超过两个时，可以修改为11110111的结果。其中0的位置index = findIndex+count-1
        char[] dates = new char[n];
        int zeroIndex = findIndex + count - 1;
        for (int i = 0; i < n; i++) {
            if (i == zeroIndex) {
                dates[i] = '0';
            } else {
                dates[i] = '1';
            }
        }
        return new String(dates);
    }
}
