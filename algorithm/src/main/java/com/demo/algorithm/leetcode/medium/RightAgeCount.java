package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 12/31/21
 * @author chenglong
 * description : 适龄的朋友
 *
 * 在社交媒体网站上有n个用户。给你一个整数数组ages ，其中ages[i]是第i个用户的年龄。
 * 如果下述任意一个条件为真，那么用户x将不会向用户y（x != y）发送好友请求：
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 否则，x将会向y发送一条好友请求。
 * 注意，如果x向y发送一条好友请求，y不必也向x发送一条好友请求。另外，用户不会向自己发送好友请求。
 * 返回在该社交媒体网站上产生的好友请求总数。
 *
 * 示例 1：
 * 输入：ages = [16,16]
 * 输出：2
 * 解释：2 人互发好友请求。
 *
 * 示例 2：
 * 输入：ages = [16,17,18]
 * 输出：2
 * 解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
 *
 * 示例 3：
 * 输入：ages = [20,30,100,110,120]
 * 输出：3
 * 解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
 *  
 * 提示：
 * n == ages.length
 * 1 <= n <= 2 * 10^4
 * 1 <= ages[i] <= 120
 */
public class RightAgeCount {

    //暴力枚举
    public int numFriendRequests(int[] ages) {
        int length = ages.length;
        //1，将年龄进行排序
        Arrays.sort(ages);
        int count = 0;
        //2，双层循环遍历x，y
        for (int i = length - 1; i >= 1; i--) {
            int x = ages[i];
            for (int j = i - 1; j >= 0; j--) {
                int y = ages[j];
                //3，判断条件：x>=y时，条件2，3均为false，只需要判断条件1。同时由于y递减，判断为false时当前y的循环直接退出
                if (y <= x * 0.5 + 7) {
                    break;
                } else {
                    if (x == y) {
                        count += 2;
                    } else {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //经分析数量远大于数据的范围，可以使用桶排序，同时计数
    public int numFriendRequests2(int[] ages) {
        int length = ages.length;
        //1,使用桶排序,同时计数
        int[] datas = new int[121];
        for (int i = 0; i < length; i++) {
            datas[ages[i]]++;
        }
        //2,遍历统计。分析三个条件：x>=y时,需要满足:y>0.5x+7
        int count = 0;
        for (int i = 120; i >= 1; i--) {
            if (datas[i] > 0) {
                //分析两种情况x = y ; x>y
                if (datas[i] > 1) {
                    //有相同的年龄
                    count += getNum(i, datas[i]);
                }
                int min = (int) (0.5 * i + 7);
                int sum = 0;
                for (int j = i - 1; j > min; j--) {
                    if (datas[j] > 0) {
                        sum += datas[j];
                    }
                }
                sum *= datas[i];
                count += sum;
            }
        }
        return count;
    }

    private int getNum(int age, int count) {
        if (age <= 14) {
            return 0;
        }
        return count * (count - 1);
    }
}
