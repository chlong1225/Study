package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create on 2023/5/4
 * @author chenglong
 * description : 强整数
 *
 * 给定三个整数x、y和bound，返回值小于或等于bound的所有强整数组成的列表。
 * 如果某一整数可以表示为x^i+ y^j，其中整数i >= 0 且j >= 0，那么我们认为该整数是一个强整数。
 * 你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。
 *
 * 示例 1：
 * 输入：x = 2, y = 3, bound = 10
 * 输出：[2,3,4,5,7,9,10]
 * 解释：
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 *
 * 示例2：
 * 输入：x = 3, y = 5, bound = 15
 * 输出：[2,4,6,8,10,14]
 *
 * 提示：
 * 1 <= x, y <= 100
 * 0 <= bound <= 10^6
 */
public class PowerfulIntegers {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        //1，构建数据源
        List<Integer> dates1 = new ArrayList<>();
        int start = 1;
        if (x == 1) {
            dates1.add(start);
        } else {
            while (start <= bound) {
                dates1.add(start);
                start *= x;
            }
        }
        List<Integer> dates2 = new ArrayList<>();
        start = 1;
        if (y == 1) {
            dates2.add(start);
        } else {
            while (start <= bound) {
                dates2.add(start);
                start *= y;
            }
        }
        //2，依次遍历dates1与dates2叠加
        Set<Integer> marks = new HashSet<>();
        for (int i = 0; i < dates1.size(); i++) {
            for (int j = 0; j < dates2.size(); j++) {
                int tem = dates1.get(i) + dates2.get(j);
                if (tem > bound) {
                    break;
                }
                marks.add(tem);
            }
        }
        return new ArrayList<>(marks);
    }
}
