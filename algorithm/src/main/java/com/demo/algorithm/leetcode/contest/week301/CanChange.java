package com.demo.algorithm.leetcode.contest.week301;

/**
 * create on 2022/8/3
 * @author chenglong
 * description : 移动片段得到字符串
 *
 * 给你两个字符串start和target，长度均为n。每个字符串仅由字符'L'、'R'和'_'组成，其中：
 * 字符'L'和'R'表示片段，其中片段'L'只有在其左侧直接存在一个空位时才能向左移动，而片段'R'只有在其右侧直接存在一个空位时才能向右移动。
 * 字符'_'表示可以被任意'L'或'R'片段占据的空位。
 * 如果在移动字符串start中的片段任意次之后可以得到字符串target，返回true；否则，返回false 。
 *
 * 示例 1：
 * 输入：start = "_L__R__R_", target = "L______RR"
 * 输出：true
 * 解释：可以从字符串 start 获得 target ，需要进行下面的移动：
 * - 将第一个片段向左移动一步，字符串现在变为 "L___R__R_" 。
 * - 将最后一个片段向右移动一步，字符串现在变为 "L___R___R" 。
 * - 将第二个片段向右移动散步，字符串现在变为 "L______RR" 。
 * 可以从字符串 start 得到 target ，所以返回 true 。
 *
 * 示例 2：
 * 输入：start = "R_L_", target = "__LR"
 * 输出：false
 * 解释：字符串 start 中的 'R' 片段可以向右移动一步得到 "_RL_" 。
 * 但是，在这一步之后，不存在可以移动的片段，所以无法从字符串 start 得到 target 。
 *
 * 示例 3：
 * 输入：start = "_R", target = "R_"
 * 输出：false
 * 解释：字符串 start 中的片段只能向右移动，所以无法从字符串 start 得到 target 。
 *
 * 提示：
 * n == start.length == target.length
 * 1 <= n <= 10^5
 * start和target由字符'L'、'R'和'_'组成
 */
public class CanChange {

    public boolean canChange(String start, String target) {
        int length = start.length();
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (start.charAt(i) == '_') {
                continue;
            }
            if (index >= length) {
                return false;
            }
            if (start.charAt(i) == 'L') {
                boolean isFind = false;
                //遍历target中查找'L'，并且index<=i
                while (index < length) {
                    if (target.charAt(index) == '_') {
                        index++;
                    } else if (target.charAt(index) == 'R') {
                        return false;
                    } else {
                        if (index > i) {
                            return false;
                        } else {
                            isFind = true;
                            index++;
                            break;
                        }
                    }
                }
                if (!isFind) {
                    return false;
                }
            } else {
                boolean isFind = false;
                //遍历target查找'R'，并且index>=i
                while (index < length) {
                    if (target.charAt(index) == '_') {
                        index++;
                    } else if (target.charAt(index) == 'L') {
                        return false;
                    } else {
                        if (index < i) {
                            return false;
                        } else {
                            isFind = true;
                            index++;
                            break;
                        }
                    }
                }
                if (!isFind) {
                    return false;
                }
            }
        }
        for (int i = index; i < length; i++) {
            if (target.charAt(i) != '_') {
                return false;
            }
        }
        return true;
    }
}
