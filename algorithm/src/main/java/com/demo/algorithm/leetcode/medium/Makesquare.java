package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2022/6/1
 * @author chenglong
 * description : 火柴拼正方形
 *
 * 你将得到一个整数数组matchsticks，其中matchsticks[i]是第i个火柴棒的长度。你要用所有的火柴棍拼成一个正方形。
 * 你不能折断任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须使用一次 。
 * 如果你能使这个正方形，则返回true，否则返回false。
 *
 * 示例1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 *
 * 示例2:
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 *
 * 提示:
 * 1 <= matchsticks.length <= 15
 * 1 <= matchsticks[i] <= 10^8
 */
public class Makesquare {

    private boolean isFind = false;

    public boolean makesquare(int[] matchsticks) {
        int length = matchsticks.length;
        //1，处理特殊场景
        if (length < 4) {
            return false;
        }
        if (length == 4) {
            //必须四个长度相同
            if (matchsticks[0] != matchsticks[1] || matchsticks[1] != matchsticks[2] || matchsticks[2] != matchsticks[3]) {
                return false;
            }
        }
        //2，对火柴长度进行排序
        Arrays.sort(matchsticks);
        //3，统计所有火柴的长度和，并校验特殊场景
        int sum = matchsticks[0];
        for (int i = 1; i < length; i++) {
            sum += matchsticks[i];
        }
        //统计和必须是4的倍数
        if (sum % 4 != 0) {
            return false;
        }
        //单边的长度
        int target = sum / 4;
        //不能存在火柴长度超过单边长度
        if (matchsticks[length - 1] > target) {
            return false;
        }
        //如果最大长度的火柴小于单边，必须拼接其它火柴，如果加上最小的火柴超过了单边，则构建失败
        if (matchsticks[length - 1] < target && (matchsticks[length - 1] + matchsticks[0]) > target) {
            return false;
        }
        //4，构建4条边。两两拆分
        isFind = false;
        List<Integer> path = new ArrayList<>();
        dfs(length - 1, 0, target * 2, path, matchsticks);
        return isFind;
    }

    private void dfs(int index, int sum, int target, List<Integer> path, int[] nums) {
        if (index < 0) {
            return;
        }
        if (isFind) {
            return;
        }
        for (int i = index; i >= 0; i--) {
            if (isFind) {
                return;
            }
            int total = sum + nums[i];
            if (total > target) {
                continue;
            }
            if (total == target) {
                path.add(nums[i]);
                //此时path对应两条边的和
                List<Integer> dates = new ArrayList<>(path);
                List<Integer> last = getLastNum(dates, nums);
                if (isDoubleLine(dates, target / 2)) {
                    if (isDoubleLine(last, target / 2)) {
                        isFind = true;
                    }
                }
                path.remove(path.size() - 1);
            } else {
                path.add(nums[i]);
                dfs(i - 1, total, target, path, nums);
                path.remove(path.size() - 1);
            }
        }
    }

    //判断能否在dates中找到数据和为target
    private boolean isDoubleLine(List<Integer> dates, int target) {
        int sum = dates.get(0);
        if (sum == target) {
            return true;
        }
        return find(sum, target, 1, dates);
    }

    private boolean find(int sum, int target, int index, List<Integer> dates) {
        for (int i = index; i < dates.size(); i++) {
            if (sum + dates.get(i) == target) {
                return true;
            }
            if (sum + dates.get(i) > target) {
                continue;
            }
            if (find(sum + dates.get(i), target, i + 1, dates)) {
                return true;
            }
        }
        return false;
    }

    //获取剩余的火柴
    private List<Integer> getLastNum(List<Integer> dates, int[] nums) {
        List<Integer> result = new ArrayList<>();
        int length = nums.length;
        int index = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (index < dates.size()) {
                if (nums[i] == dates.get(index)) {
                    index++;
                } else {
                    result.add(nums[i]);
                }
            } else {
                result.add(nums[i]);
            }
        }
        return result;
    }
}
