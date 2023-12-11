package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/10
 * description : 分糖果II
 *
 * 排排坐，分糖果。
 * 我们买了一些糖果candies，打算把它们分给排好队的n=num_people个小朋友。
 * 给第一个小朋友1颗糖果，第二个小朋友2颗，依此类推，直到给最后一个小朋友n颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友n+1颗糖果，第二个小朋友n+2颗，依此类推，直到给最后一个小朋友2*n颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），
 * 这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为num_people、元素之和为candies的数组，以表示糖果的最终分发情况（即 ans[i]表示第i个小朋友分到的糖果数）。
 *
 * 示例 1：
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 *
 * 示例 2：
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 *
 * 提示：
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 */
public class DistributeCandies {

    public int[] distributeCandies(int candies, int num_people) {
        //1，获取可以完整分配的次数: (count+1)*count/2<=candies
        int count = getCount(candies);
        //能够完整分配完的轮数
        int repeat = count / num_people;
        int[] answer = new int[num_people];
        int start = 1;
        if (repeat > 0) {
            for (int i = 0; i < num_people; i++) {
                int total = repeat * (i + 1) + num_people * (repeat - 1) * repeat / 2;
                answer[i] = total;
                candies -= total;
            }
            start += repeat * num_people;
        }
        for (int i = 0; i < num_people; i++) {
            int request = start + i;
            if (candies >= request) {
                answer[i] += request;
                candies -= request;
            } else {
                answer[i] += candies;
                break;
            }
        }
        return answer;
    }

    private int getCount(int num) {
        int start = (int) Math.sqrt(2 * num) - 1;
        while ((start + 1) * start / 2 <= num) {
            start++;
        }
        return start - 1;
    }
}
