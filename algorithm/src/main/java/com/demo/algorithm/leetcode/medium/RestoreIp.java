package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/11/7.
 * description : 复原ip
 *
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按 任何 顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 *
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 *
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * 提示：
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class RestoreIp {

    public List<String> restoreIpAddresses(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        int length = s.length();
        if (length < 4 || length > 12) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        List<Integer> ips = new ArrayList<>();
        findIp(0, s, ips, result);
        return result;
    }

    private void findIp(int index, String s, List<Integer> ips, List<String> result) {
        int length = s.length();
        if (index == length) {
            if (ips.size() == 4) {
                result.add(listToStr(new ArrayList<>(ips)));
            }
            return;
        }
        if (ips.size() == 4) {
            return;
        }
        int start = s.charAt(index) - '0';
        if (start == 0) {
            //只能添加一位的ip
            ips.add(0);
            findIp(index + 1, s, ips, result);
            ips.remove(ips.size() - 1);
        } else {
            ips.add(start);
            findIp(index + 1, s, ips, result);
            ips.remove(ips.size() - 1);
            if (index + 1 < length) {
                int second = s.charAt(index + 1) - '0';
                int number = start * 10 + second;
                ips.add(number);
                findIp(index + 2, s, ips, result);
                ips.remove(ips.size() - 1);
                if (index + 2 < length) {
                    int third = s.charAt(index + 2) - '0';
                    number = start * 100 + second * 10 + third;
                    if (number <= 255) {
                        ips.add(number);
                        findIp(index + 3, s, ips, result);
                        ips.remove(ips.size() - 1);
                    }
                }
            }
        }
    }

    //将集合形式的ip转换成字符串形式
    private String listToStr(List<Integer> ips) {
        StringBuilder builder = new StringBuilder();
        builder.append(ips.get(0));
        for (int i = 1; i < 4; i++) {
            builder.append(".");
            builder.append(ips.get(i));
        }
        return builder.toString();
    }
}
