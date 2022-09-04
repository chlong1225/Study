package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/16
 *
 * @author chenglong
 * description : 设计有序流
 * <p>
 * 有n个(id, value)对，其中id是1到n之间的一个整数，value是一个字符串。不存在id相同的两个(id, value)对。
 * 设计一个流，以任意顺序获取n个(id, value)对，并在多次调用时按id递增的顺序返回一些值。
 * <p>
 * 实现 OrderedStream 类：
 * OrderedStream(int n) 构造一个能接收n个值的流，并将当前指针ptr设为1 。
 * String[] insert(int id, String value) 向流中存储新的(id, value)对。
 * 存储后：如果流存储有id =ptr的(id, value)对，则找出从id = ptr开始的最长id连续递增序列，并按顺序返回与这些id关联的值的列表。然后将ptr更新为最后那个id + 1。
 * 否则，返回一个空列表。
 * <p>
 * 示例：
 * 输入
 * ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * 输出
 * [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 * 解释
 * OrderedStream os= new OrderedStream(5);
 * os.insert(3, "ccccc"); // 插入 (3, "ccccc")，返回 []
 * os.insert(1, "aaaaa"); // 插入 (1, "aaaaa")，返回 ["aaaaa"]
 * os.insert(2, "bbbbb"); // 插入 (2, "bbbbb")，返回 ["bbbbb", "ccccc"]
 * os.insert(5, "eeeee"); // 插入 (5, "eeeee")，返回 []
 * os.insert(4, "ddddd"); // 插入 (4, "ddddd")，返回 ["ddddd", "eeeee"]
 * <p>
 * 提示：
 * 1 <= n <= 1000
 * 1 <= id <= n
 * value.length == 5
 * value仅由小写字母组成
 * 每次调用insert都会使用一个唯一的id
 * 恰好调用n次insert
 */
public class OrderedStream {

    private String[] dates;
    private int start;

    public OrderedStream(int n) {
        dates = new String[n + 1];
        start = 1;
    }

    public List<String> insert(int idKey, String value) {
        dates[idKey] = value;
        if (dates[start] == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (int i = start; i < dates.length; i++) {
            start = i;
            if (dates[i] == null) {
                break;
            } else {
                result.add(dates[start]);
            }
        }
        return result;
    }
}
