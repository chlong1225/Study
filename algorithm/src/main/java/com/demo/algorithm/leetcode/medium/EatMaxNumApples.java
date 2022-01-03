package com.demo.algorithm.leetcode.medium;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.PriorityQueue;

/**
 * Created by chl on 2022/1/3.
 * description : 吃苹果的最大数目
 *
 * 有一棵特殊的苹果树，一连n天，每天都可以长出若干个苹果。在第i天，树上会长出apples[i]个苹果，
 * 这些苹果将会在days[i]天后（也就是说，第 i+days[i]天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，
 * 此时用apples[i]==0 且 days[i]==0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这n天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 *
 * 示例 1：
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 *
 * 示例 2：
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 *  
 * 提示：
 * apples.length == n
 * days.length == n
 * 1 <= n <= 2 * 10^4
 * 0 <= apples[i], days[i] <= 2 * 10^4
 * 只有在 apples[i] = 0 时，days[i] = 0 才成立
 */
public class EatMaxNumApples {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int eatenApples(int[] apples, int[] days) {
        int count = 0;
        //1，定义二维数组存放：到期时间，苹果数量
        int length = apples.length;
        int[][] datas = new int[length][2];
        for (int i = 0; i < length; i++) {
            datas[i][0] = i + days[i];
            datas[i][1] = apples[i];
        }
        //2，定义优先队列存储datas数据
        PriorityQueue<int[]> nums = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int index = 1;
        while (index <= length) {
            //1，把当天的生长的苹果加到队列
            if (datas[index - 1][1] > 0) {
                nums.offer(datas[index - 1]);
            }
            //2，从队列中取出已经过期的苹果
            while (!nums.isEmpty() && nums.peek()[0] < index) {
                nums.poll();
            }
            //3，从队列中获取最近一个快到期的苹果
            if (!nums.isEmpty()) {
                int[] tem = nums.peek();
                if (tem[1] > 0) {
                    count++;
                    tem[1]--;
                    if (tem[1] == 0) {
                        nums.poll();
                    }
                }
            }
            index++;
        }
        //4，n天后还有没过期的苹果可以接着chi
        while (!nums.isEmpty()) {
            //5，去掉已经过期的数据
            while (!nums.isEmpty() && nums.peek()[0] < index) {
                nums.poll();
            }
            //6，清除过期苹果后需要判断是否还有数据
            if (nums.isEmpty()) {
                break;
            }
            int[] tem = nums.poll();
            if (tem[1] > 0) {
                int validDay = tem[0] - index + 1;
                if (validDay <= tem[1]) {
                    //苹果足够迟到有效期tem[1]
                    count += validDay;
                    index += validDay;
                } else {
                    //苹果不够,吃完当前苹果
                    count += tem[1];
                    index += tem[1];
                }
            }
        }
        return count;
    }

    public int eatenApples2(int[] apples, int[] days) {
        int length = apples.length;
        //1，记录有效苹果数量
        int[] datas = new int[40001];
        int count = 0;
        //minDay记录当前最近有效期内的苹果，再之前的有效苹果被舍弃
        int minDay = 0;
        //记录当前最远有效期内的苹果。可查询范围：minDay~maxDay
        int maxDay = 0;
        for (int i = 0; i < 40000; i++) {
            if (minDay <= i) {
                //重置min,清除已经过期的苹果
                minDay = i + 1;
            }
            if (i < length && apples[i] > 0) {
                //苹果有效的截止日期
                int cur = i + days[i];
                datas[cur] = apples[i];
                maxDay = Math.max(maxDay, cur);
                minDay = Math.min(minDay, cur);
            }
            while (datas[minDay] == 0 && minDay <= maxDay) {
                minDay++;
            }
            if (minDay <= maxDay) {
                datas[minDay]--;
                count++;
            } else {
                if (i > length) {
                    return count;
                }
            }
        }
        return count;
    }
}
