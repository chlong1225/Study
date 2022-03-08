package com.demo.algorithm.leetcode.contest.week283;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/8.
 * description : Excel 表中某个范围内的单元格
 *
 * Excel表中的一个单元格(r, c)会以字符串"<col><row>"的形式进行表示，其中：
 * <col> 即单元格的列号c 。用英文字母表中的字母标识。
 * 例如，第1列用'A'表示，第2列用'B'表示，第3列用'C'表示，以此类推。
 * <row> 即单元格的行号r 。第r行就用整数r标识。
 * 给你一个格式为 "<col1><row1>:<col2><row2>"的字符串s，其中<col1>表示c1列，<row1>表示r1行，<col2>表示c2列，<row2>表示r2行，并满足 r1<=r2且c1<=c2。
 * 找出所有满足r1 <= x <= r2 且 c1 <= y <= c2 的单元格，并以列表形式返回。单元格应该按前面描述的格式用字符串表示，并以非递减顺序排列（先按列排，再按行排）。
 *
 * 示例 1：
 * 输入：s = "K1:L2"
 * 输出：["K1","K2","L1","L2"]
 * 解释：
 * 上图显示了列表中应该出现的单元格。
 * 红色箭头指示单元格的出现顺序。
 *
 * 示例 2：
 * 输入：s = "A1:F1"
 * 输出：["A1","B1","C1","D1","E1","F1"]
 * 解释：
 * 上图显示了列表中应该出现的单元格。
 * 红色箭头指示单元格的出现顺序。
 *
 * 提示：
 * s.length == 5
 * 'A' <= s[0] <= s[3] <= 'Z'
 * '1' <= s[1] <= s[4] <= '9'
 * s 由大写英文字母、数字、和 ':' 组成
 */
public class CellsInRange {

    public List<String> cellsInRange(String s) {
        List<String> result = new ArrayList<>();
        char c1 = s.charAt(0);
        int r1 = s.charAt(1) - '0';
        char c2 = s.charAt(3);
        int r2 = s.charAt(4) - '0';
        for (char i = c1; i <= c2; i++) {
            for (int j = r1; j <= r2; j++) {
                StringBuilder builder = new StringBuilder();
                builder.append(i);
                builder.append(j);
                result.add(builder.toString());
            }
        }
        return result;
    }
}
