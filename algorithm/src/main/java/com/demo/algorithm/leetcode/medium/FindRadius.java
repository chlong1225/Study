package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * Created by chl on 2021/12/20.
 * description : 供暖器
 *
 * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋houses和供暖器heaters的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 * 示例 1:
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 *
 * 示例 2:
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 *
 * 示例 3：
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 *  
 * 提示：
 * 1 <= houses.length, heaters.length <= 3 * 104
 * 1 <= houses[i], heaters[i] <= 109
 */
public class FindRadius {

    public int findRadius(int[] houses, int[] heaters) {
        //1,对房屋位置与供暖位置进行排序
        Arrays.sort(houses);
        Arrays.sort(heaters);
        //2,原地数据去重
        int length = houses.length;
        int pre = houses[0];
        int count1 = 1;
        for (int i = 1; i < length; i++) {
            if (houses[i] != pre) {
                pre = houses[i];
                houses[count1] = pre;
                count1++;
            }
        }
        length = heaters.length;
        pre = heaters[0];
        int count2 = 1;
        for (int i = 1; i < length; i++) {
            if (heaters[i] != pre) {
                pre = heaters[i];
                heaters[count2] = pre;
                count2++;
            }
        }
        //3,遍历房屋,查找最近的供暖器
        int result = 0;
        int start = 0;
        int end;
        for (int i = 0; i < count1; i++) {
            int position = houses[i];
            end = count2 - 1;
            if (position <= heaters[start]) {
                result = Math.max(result, heaters[start] - position);
            } else if (position >= heaters[end]) {
                result = Math.max(result, position - heaters[end]);
            } else {
                while (start < end) {
                    int middle = (start + end) >> 1;
                    if (position == heaters[middle]) {
                        start = middle;
                        break;
                    } else if (position < heaters[middle]) {
                        end = middle;
                        if (end - start == 1) {
                            int space = Math.min(heaters[end] - position, position - heaters[start]);
                            result = Math.max(result, space);
                            break;
                        }
                    } else {
                        start = middle;
                        if (end - start == 1) {
                            int space = Math.min(heaters[end] - position, position - heaters[start]);
                            result = Math.max(result, space);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}
