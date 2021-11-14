package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chl on 2021/11/14.
 * description : 键值映射
 * <p>
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *  
 * 示例：
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *  
 * 提示：
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
 */
public class MapSum {

    private Map<String, Integer> map = new HashMap<>();

    public MapSum() {
        map.clear();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int sum = 0;
        Set<String> keySets = map.keySet();
        for (String key : keySets) {
            if (isSame(prefix, key)) {
                sum += map.get(key);
            }
        }
        return sum;
    }

    private boolean isSame(String prefix, String key) {
        int length = key.length();
        int compare = prefix.length();
        if (length < compare) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < compare; i++) {
            if (key.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }
        return result;
    }
}
