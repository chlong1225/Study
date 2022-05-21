package com.demo.algorithm.leetcode.contest.week293;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by chl on 2022/5/15.
 * description :
 */
public class CountIntervals {

    private TreeMap<Integer, Integer> marks = new TreeMap<>();
    private int total;

    public CountIntervals() {
        marks.clear();
        total = 0;
    }

    public void add(int left, int right) {
        Map.Entry<Integer, Integer> entry = marks.ceilingEntry(left);
        while (entry != null && entry.getValue() <= right) {
            int l = entry.getValue();
            int r = entry.getKey();
            left = Math.min(left, l);
            right = Math.max(right, r);
            total -= (r - l + 1);
            marks.remove(r);
            entry = marks.ceilingEntry(left);
        }
        total += (right - left + 1);
        marks.put(right, left);
    }

    public int count() {
        return total;
    }
}
