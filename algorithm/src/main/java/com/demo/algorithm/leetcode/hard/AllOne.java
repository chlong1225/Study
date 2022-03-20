package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/3/20.
 * description : 全O(1)的数据结构
 *
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * 实现 AllOne类：
 * AllOne() 初始化数据结构的对象。
 * inc(String key)字符串key的计数增加1。如果数据结构中尚不存在key，那么插入计数为1的key 。
 * dec(String key)字符串key的计数减少1 。如果key的计数在减少后为0，那么需要将这个key从数据结构中删除。
 * 测试用例保证：在减少计数前，key存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 *
 * 示例：
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 *
 * 提示：
 * 1 <= key.length <= 10
 * key 由小写英文字母组成
 * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 * 最多调用 inc、dec、getMaxKey和getMinKey方法5*10^4次
 */
public class AllOne {

    private Map<String, Integer> counts = new HashMap<>();
    private Map<Integer, List<String>> dates = new HashMap<>();
    private List<Integer> nums = new ArrayList<>();

    public AllOne() {
        counts.clear();
        dates.clear();
        nums.clear();
    }

    public void inc(String key) {
        if (counts.containsKey(key)) {
            int count = counts.get(key);
            dates.get(count).remove(key);
            if (dates.get(count + 1) == null) {
                List<String> item = new ArrayList<>();
                item.add(key);
                dates.put(count + 1, item);
            } else {
                dates.get(count + 1).add(key);
            }
            counts.put(key, count + 1);
            if (dates.get(count).size() == 0) {
                nums.remove((Integer) count);
            }
            if (dates.get(count + 1).size() == 1) {
                //count+1数量之前不存在
                nums.add(count + 1);
                Collections.sort(nums);
            }
        } else {
            counts.put(key, 1);
            if (dates.get(1) == null) {
                List<String> item = new ArrayList<>();
                item.add(key);
                dates.put(1, item);
            } else {
                dates.get(1).add(key);
            }
            //新增count=1
            if (dates.get(1).size() == 1) {
                nums.add(1);
                Collections.sort(nums);
            }
        }
    }

    public void dec(String key) {
        if (counts.containsKey(key)) {
            int count = counts.get(key);
            if (dates.get(count).size() == 1) {
                //数量为count的字符串只有key。删除后，就不存在nums中就不存在count值了
                nums.remove((Integer) count);
            }
            if (count == 1) {
                counts.remove(key);
                dates.get(count).remove(key);
            } else {
                counts.put(key, count - 1);
                dates.get(count).remove(key);
                dates.get(count - 1).add(key);
                if (dates.get(count - 1).size() == 1) {
                    nums.add(count - 1);
                    Collections.sort(nums);
                }
            }
        }
    }

    public String getMaxKey() {
        if (nums.size() == 0) {
            return "";
        }
        int count = nums.get(nums.size() - 1);
        return dates.get(count).get(0);
    }

    public String getMinKey() {
        if (nums.size() == 0) {
            return "";
        }
        int count = nums.get(0);
        return dates.get(count).get(0);
    }
}
