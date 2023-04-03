package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/3
 * @author chenglong
 * description : 隐藏个人信息
 *
 * 给你一条个人信息字符串s，可能表示一个邮箱地址，也可能表示一串电话号码。返回按如下规则隐藏个人信息后的结果：
 * 电子邮件地址：
 * 一个电子邮件地址由以下部分组成：
 * 一个名字由大小写英文字母组成后面跟着一个'@'字符，后面跟着
 * 一个域名，由大小写英文字母和一个位于中间的'.'字符组成。'.'不会是域名的第一个或者最后一个字符。
 * 要想隐藏电子邮件地址中的个人信息：
 * 名字和域名部分的大写英文字母应当转换成小写英文字母。
 * 名字中间的字母（即，除第一个和最后一个字母外）必须用5个"*****"替换。
 *
 * 电话号码：
 * 一个电话号码应当按下述格式组成：
 * 电话号码可以由10-13位数字组成
 * 后10位构成本地号码
 * 前面剩下的0-3位，构成国家代码
 * 利用 {'+', '-', '(', ')', ' '} 这些分隔字符按某种形式对上述数字进行分隔
 * 要想隐藏电话号码中的个人信息：
 * 移除所有分隔字符
 * 隐藏个人信息后的电话号码应该遵从这种格式：
 * "***-***-XXXX" 如果国家代码为0位数字
 * "+*-***-***-XXXX" 如果国家代码为1位数字
 * "+**-***-***-XXXX" 如果国家代码为2位数字
 * "+***-***-***-XXXX" 如果国家代码为3位数字
 * "XXXX" 是最后4位本地号码
 *
 * 示例 1：
 * 输入：s = "LeetCode@LeetCode.com"
 * 输出："l*****e@leetcode.com"
 * 解释：s 是一个电子邮件地址。
 * 名字和域名都转换为小写，名字的中间用 5 个 * 替换。
 *
 * 示例 2：
 * 输入：s = "AB@qq.com"
 * 输出："a*****b@qq.com"
 * 解释：s 是一个电子邮件地址。
 * 名字和域名都转换为小写，名字的中间用 5 个 * 替换。
 * 注意，尽管 "ab" 只有两个字符，但中间仍然必须有 5 个 * 。
 *
 * 示例 3：
 * 输入：s = "1(234)567-890"
 * 输出："***-***-7890"
 * 解释：s 是一个电话号码。
 * 共计 10 位数字，所以本地号码为 10 位数字，国家代码为 0 位数字。
 * 因此，隐藏后的电话号码应该是 "***-***-7890" 。
 *
 * 提示：
 * s 是一个有效的电子邮件或者电话号码
 * 如果s是一个电子邮件：8 <= s.length <= 40
 * s是由大小写英文字母，恰好一个'@'字符，以及'.'字符组成
 * 如果s是一个电话号码：
 * 10 <= s.length <= 20
 * s 是由数字、空格、字符 '('、')'、'-' 和 '+' 组成
 */
public class MaskPII {

    public String maskPII(String s) {
        if (isEmail(s)) {
            return maskEmail(s);
        }
        return maskPhone(s);
    }

    private String maskPhone(String s) {
        int n = s.length();
        //统计电话号码
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                builder.append(s.charAt(i));
            }
        }
        StringBuilder result = new StringBuilder();
        if (builder.length() == 10) {
            result.append("***-***-");
            result.append(builder.substring(6));
        } else if (builder.length() == 11) {
            result.append("+*-***-***-");
            result.append(builder.substring(7));
        } else if (builder.length() == 12) {
            result.append("+**-***-***-");
            result.append(builder.substring(8));
        } else {
            result.append("+***-***-***-");
            result.append(builder.substring(9));
        }
        return result.toString();
    }

    private String maskEmail(String s) {
        int n = s.length();
        StringBuilder builder = new StringBuilder();
        builder.append(toLowerCase(s.charAt(0)));
        builder.append("*****");
        int findIndex = -1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '@') {
                findIndex = i;
                break;
            }
        }
        builder.append(toLowerCase(s.charAt(findIndex - 1)));
        builder.append('@');
        for (int i = findIndex + 1; i < n; i++) {
            builder.append(toLowerCase(s.charAt(i)));
        }
        return builder.toString();
    }

    //字母转小写
    private char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }

    //判断是否为邮箱
    private boolean isEmail(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '@') {
                return true;
            }
        }
        return false;
    }
}
