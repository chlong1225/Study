package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/10.
 * description : 字符串相乘
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class StrMultiply {

    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return "";
        }
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        if (chars1[0] == '0' || chars2[0] == '0') {
            return "0";
        }
        //1,拆分字符串后单个数字相乘
        int length2 = chars2.length;
        String[] datas = new String[length2];
        for (int i = 0; i < length2; i++) {
            datas[i] = multiplySingle(chars2[i] - '0', chars1, length2 - 1 - i);
        }
        //2,字符串求和,第一个乘积字符串应该是最长的
        int maxLength = datas[0].length();
        StringBuilder builder = new StringBuilder();
        int addNum = 0;
        for (int i = 0; i < maxLength; i++) {
            int sum = 0;
            for (int j = 0; j < length2; j++) {
                char[] tem = datas[j].toCharArray();
                if (i < tem.length) {
                    sum += (tem[i] - '0');
                }
            }
            sum += addNum;
            builder.append(sum % 10);
            addNum = sum / 10;
        }
        while (addNum > 0) {
            builder.append(addNum % 10);
            addNum /= 10;
        }
        //3,字符串反转
        builder.reverse();
        return builder.toString();
    }

    public String multiply2(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return "";
        }
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        if (chars1[0] == '0' || chars2[0] == '0') {
            return "0";
        }
        //1,拆分字符串后单个数字相乘
        int length2 = chars2.length;
        String sum = null;
        String singleMultiply;
        for (int i = length2 - 1; i >= 0; i--) {
            singleMultiply = multiplySingle2(chars2[i] - '0', chars1, length2 - 1 - i);
            //2,字符串求和
            if (sum == null) {
                sum = singleMultiply;
            } else {
                //2,字符串求和
                sum = addStr(sum,singleMultiply);
            }
        }
        return sum;
    }

    private String addStr(String a, String b) {
        char[] chars1 = a.toCharArray();
        char[] chars2 = b.toCharArray();
        int m = chars1.length - 1;
        int n = chars2.length - 1;
        int addNum = 0;
        StringBuilder builder = new StringBuilder();
        while (n >= 0 || m >= 0) {
            int x = m >= 0 ? (chars1[m] - '0') : 0;
            int y = n >= 0 ? (chars2[n] - '0') : 0;
            int sum = x + y + addNum;
            builder.append(sum % 10);
            addNum = sum / 10;
            n--;
            m--;
        }
        while (addNum > 0) {
            builder.append(addNum % 10);
            addNum /= 10;
        }
        return builder.reverse().toString();
    }

    private String multiplySingle2(int num, char[] chars, int count) {
        if (num == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        //1,先补充0
        while (count > 0) {
            builder.append("0");
            count--;
        }
        //2,计算乘积
        int length = chars.length;
        int addNum = 0;
        for (int i = length - 1; i >= 0; i--) {
            int a = chars[i] - '0';
            int sum = a * num + addNum;
            builder.append(sum % 10);
            addNum = sum / 10;
        }
        if (addNum > 0) {
            builder.append(addNum);
        }
        return builder.reverse().toString();
    }

    private String multiplySingle(int num, char[] chars, int count) {
        if (num == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        //1,先补充0
        while (count > 0) {
            builder.append("0");
            count--;
        }
        //2,计算乘积
        int length = chars.length;
        int addNum = 0;
        for (int i = length - 1; i >= 0; i--) {
            int a = chars[i] - '0';
            int sum = a * num + addNum;
            builder.append(sum % 10);
            addNum = sum / 10;
        }
        if (addNum > 0) {
            builder.append(addNum);
        }
        return builder.toString();
    }
}
