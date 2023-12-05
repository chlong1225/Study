package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : Fizz Buzz
 *
 * 给你一个整数n，找出从1到n各个整数的Fizz Buzz表示，并用字符串数组answer（下标从1开始）返回结果，其中：
 * answer[i] == "FizzBuzz" 如果i同时是3和5的倍数。
 * answer[i] == "Fizz" 如果i是3的倍数。
 * answer[i] == "Buzz" 如果i是5的倍数。
 * answer[i] == i（以字符串形式）如果上述条件全不满足。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["1","2","Fizz"]
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：["1","2","Fizz","4","Buzz"]
 *
 * 示例 3：
 * 输入：n = 15
 * 输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *
 * 提示：
 * 1 <= n <= 104
 */
public class FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> answer = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int cur = i + 1;
            if (cur % 15 == 0) {
                answer.add("FizzBuzz");
            } else if (cur % 5 == 0) {
                answer.add("Buzz");
            } else if (cur % 3 == 0) {
                answer.add("Fizz");
            } else {
                answer.add("" + cur);
            }
        }
        return answer;
    }
}
