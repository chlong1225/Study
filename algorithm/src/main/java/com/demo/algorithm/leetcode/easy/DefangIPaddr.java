package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/6/21
 * @author chenglong
 * description : IP地址无效化
 *
 * 给你一个有效的IPv4地址address，返回这个IP地址的无效化版本。
 * 所谓无效化IP地址，其实就是用"[.]"代替了每个 "."。
 *
 * 示例 1：
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"
 *
 * 示例 2：
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 *
 * 提示：
 * 给出的address是一个有效的IPv4地址
 */
public class DefangIPaddr {

    public String defangIPaddr(String address) {
        int length = address.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (address.charAt(i) == '.') {
                builder.append("[");
                builder.append(address.charAt(i));
                builder.append("]");
            } else {
                builder.append(address.charAt(i));
            }
        }
        return builder.toString();
    }
}
