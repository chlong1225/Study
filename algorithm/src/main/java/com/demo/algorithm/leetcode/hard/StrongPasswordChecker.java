package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/4/2.
 * description : 强密码检验器
 *
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至少6个，至多20个字符组成。
 * 至少包含一个小写字母，一个大写字母，和一个数字。
 * 同一字符不能连续出现三次(比如 "...aaa..." 是不允许的, 但是"...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回将password修改到满足强密码条件需要的最少修改步数。如果password已经是强密码，则返回0 。
 *
 * 在一步修改操作中，你可以：
 * 插入一个字符到password，
 * 从password中删除一个字符，或
 * 用另一个字符来替换password中的某个字符。
 *
 * 示例 1：
 * 输入：password = "a"
 * 输出：5
 *
 * 示例 2：
 * 输入：password = "aA1"
 * 输出：3
 *
 * 示例 3：
 * 输入：password = "1337C0d3"
 * 输出：0
 *
 * 提示：
 * 1 <= password.length <= 50
 * password 由字母、数字、点 '.' 或者感叹号 '!'
 */
public class StrongPasswordChecker {

    public int strongPasswordChecker(String password) {
        int length = password.length();
        //1，特殊场景：长度为1,2,3时，直接可以添加5,4,3个字符构成强密码
        if (length < 4) {
            return 6 - length;
        }
        //2，检查是否有数字，大小写字母
        int hasNum = 0;
        int hasUppercase = 0;
        int hasLowerCase = 0;
        for (int i = 0; i < length; i++) {
            char tem = password.charAt(i);
            if (tem >= '0' && tem <= '9') {
                hasNum = 1;
            }
            if (tem >= 'A' && tem <= 'Z') {
                hasUppercase = 1;
            }
            if (tem >= 'a' && tem <= 'z') {
                hasLowerCase = 1;
            }
        }
        int m = hasNum + hasLowerCase + hasUppercase;
        //3，检查连续字符
        int n = length;
        if (n < 6) {
            return Math.max(6 - n, 3 - m);
        }
        if (n <= 20) {
            char pre = password.charAt(0);
            int count = 1;
            int step = 0;
            for (int i = 1; i < length; i++) {
                if (pre == password.charAt(i)) {
                    count++;
                } else {
                    step += (count / 3);
                    pre = password.charAt(i);
                    count = 1;
                }
            }
            step += (count / 3);
            return Math.max(step, 3 - m);
        }
        //分类讨论超过20字符的场景
        int remove = n - 20;
        //统计超过3个字符时对应字符的数量
        List<List<Integer>> dates = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            dates.add(new ArrayList<>());
        }
        char pre = password.charAt(0);
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (password.charAt(i) == pre) {
                count++;
            } else {
                if (count >= 3) {
                    dates.get(count % 3).add(count);
                }
                pre = password.charAt(i);
                count = 1;
            }
        }
        if (count >= 3) {
            dates.get(count % 3).add(count);
        }
        int step = remove;
        while (remove > 0) {
            if (dates.get(0).size() > 0) {
                List<Integer> items = dates.get(0);
                int tem = items.get(items.size() - 1) - 1;
                items.remove(items.size() - 1);
                if (tem > 3) {
                    dates.get(tem % 3).add(tem);
                }
                remove--;
            } else if (dates.get(1).size() > 0) {
                if (remove < 2) {
                    break;
                }
                List<Integer> items = dates.get(1);
                remove -= 2;
                int tem = items.get(items.size() - 1) - 2;
                items.remove(items.size() - 1);
                if (tem > 3) {
                    dates.get(tem % 3).add(tem);
                }
            } else {
                List<Integer> items = dates.get(2);
                if (items.size() == 0) {
                    break;
                }
                if (remove < 3) {
                    break;
                }
                remove -= 3;
                int tem = items.get(items.size() - 1) - 3;
                items.remove(items.size() - 1);
                if (tem > 3) {
                    items.add(tem);
                }
            }
        }
        //统计剩余replace的次数
        int replace = 0;
        for (int i = 0; i < dates.size(); i++) {
            List<Integer> items = dates.get(i);
            for (int j = 0; j < items.size(); j++) {
                replace += (items.get(j) / 3);
            }
        }
        return step + Math.max(replace, 3 - m);
    }
}
