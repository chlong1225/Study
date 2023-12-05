package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 二进制手表
 *
 * 二进制手表顶部有4个LED代表小时（0-11），底部的6个LED代表分钟（0-59）。每个LED代表一个0或1，最低位在右侧。
 * 例如，下面的二进制手表读取"4:51"。
 *
 * 给你一个整数turnedOn，表示当前亮着的LED的数量，返回二进制手表可以表示的所有可能时间。你可以按任意顺序返回答案。
 * 小时不会以零开头：
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 *
 * 示例 1：
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 *
 * 示例 2：
 * 输入：turnedOn = 9
 * 输出：[]
 *
 * 提示：
 * 0 <= turnedOn <= 10
 */
public class ReadBinaryWatch {

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    if (j < 10) {
                        result.add(i + ":0" + j);
                    } else {
                        result.add(i + ":" + j);
                    }
                }
            }
        }
        return result;
    }
}
