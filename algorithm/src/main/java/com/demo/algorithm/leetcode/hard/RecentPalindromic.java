package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/3/2.
 * description : 寻找最近的回文数
 *
 * 给定一个表示整数的字符串n，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * “最近的”定义为两个整数差的绝对值最小。
 *
 * 示例 1:
 * 输入: n = "123"
 * 输出: "121"
 *
 * 示例 2:
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *
 * 提示:
 * 1 <= n.length <= 18
 * n只由数字组成
 * n不含前导 0
 * n代表在[1, 10^18- 1] 范围内的整数
 */
public class RecentPalindromic {

    public String nearestPalindromic(String n) {
        /**
         * 经分析Long最大取值为19位，超过了n的最大18位，可以转换为Long进行计算，比较
         * 最少更改的方案：将前半段反转覆盖后半段或后半段反转覆盖前半段。
         * 题目要求最近，前半段反转后数据变化相对较少。
         */
        int length = n.length();
        long num = Long.parseLong(n);
        if (length == 1) {
            //只有一位时，直接-1都是回文
            return "" + (num - 1);
        }
        //特殊处理
        if (num == 10 || num == 11) {
            return "9";
        }
        //进行场景分类处理
        String result = "";
        //数字字符串是否为偶数
        int offset = length % 2;
        //截取前半段。偶数长度时：abcd，此时反转使用ab；长度为奇数时：abcde，此时截取abc，但反转ab
        String pre = n.substring(0, length / 2 + offset);
        long preNum = Long.parseLong(pre);
        String add = "" + (preNum + 1);
        String reduce = "" + (preNum - 1);
        StringBuilder result1 = new StringBuilder(add);
        int addLength = add.length() - offset;
        if (add.length() > pre.length()) {
            /**
             * 此时pre进一位了。长度为偶数时，比如99|99，add=100，反转拼接后为100001，实际最近为10001
             * 长度为奇数时，比如99|9，此时add=100，反转使用10后拼接为10001，实际最近为1001。比较发现都是中间少拼接一位
             */
            for (int i = addLength - 2; i >= 0; i--) {
                result1.append(add.charAt(i));
            }
        } else {
            for (int i = addLength - 1; i >= 0; i--) {
                result1.append(add.charAt(i));
            }
        }
        StringBuilder result2 = new StringBuilder(reduce);
        int reduceLength = reduce.length() - offset;
        if (reduce.length() < pre.length()) {
            /**
             * 此时pre-1时退位了。长度为偶数时，比如10|01，reduce=9，反转拼接后为99。实际最近的为999
             * 长度为奇数时，比如101，reduce=9，反转后9.实际最近的为99。比较发现需要补充一个9
             */
            result2.append(9);
        }
        for (int i = reduceLength - 1; i >= 0; i--) {
            result2.append(reduce.charAt(i));
        }
        //比较result1与result2与num最近
        long diff1 = Math.abs(Long.parseLong(result1.toString()) - num);
        long diff2 = Math.abs(Long.parseLong(result2.toString()) - num);
        if (diff1 > diff2) {
            result = result2.toString();
        } else if (diff1 < diff2) {
            result = result1.toString();
        } else {
            //距离相同时取较小
            if (Long.parseLong(result1.toString()) > Long.parseLong(result2.toString())) {
                result = result2.toString();
            } else {
                result = result1.toString();
            }
        }
        if (isPalindromic(n)) {
            //当前数字字符串为回文时，只需要验证前缀+1，前缀-1反转
            return result;
        }
        //字符串不是回文数，本身也可以反转,然后跟result进行对比
        StringBuilder result3 = new StringBuilder(pre);
        int preLength = pre.length() - offset;
        for (int i = preLength - 1; i >= 0; i--) {
            result3.append(pre.charAt(i));
        }
        diff1 = Math.abs(Long.parseLong(result) - num);
        diff2 = Math.abs(Long.parseLong(result3.toString()) - num);
        if (diff1 > diff2) {
            return result3.toString();
        }
        if (diff1 < diff2) {
            return result;
        }
        if (Long.parseLong(result) > Long.parseLong(result3.toString())) {
            return result3.toString();
        }
        return result;
    }

    //判断数字字符串是否为回文
    private boolean isPalindromic(String n) {
        int length = n.length();
        for (int i = 0; i < length / 2; i++) {
            if (n.charAt(i) != n.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
