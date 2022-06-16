package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/6/11.
 * description : 我的日程安排表II
 *
 * 实现一个MyCalendar类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar有一个book(int start, int end)方法。它意味着在start到end时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数x的范围为start <= x < end。
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * 每次调用MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订返回true。否则返回false并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * 示例：
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * 解释：
 * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
 * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
 * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
 * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
 * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 *
 * 提示：
 * 每个测试用例，调用MyCalendar.book函数最多不超过1000次。
 * 调用函数MyCalendar.book(start, end)时，start和end的取值范围为[0, 10^9]。
 */
public class MyCalendarTwo {

    private static final int N = 1000000000;
    private static final int M = 120000;

    private Node[] trees = new Node[M];
    private int mIndex = 1;

    public MyCalendarTwo() {
    }

    public boolean book(int start, int end) {
        if (query(1, 1, N + 1, start + 1, end) >= 2) {
            return false;
        }
        update(1, 1, N + 1, start + 1, end, 1);
        return true;
    }

    private void update(int index, int l, int r, int start, int end, int v) {
        if (start <= l && r <= end) {
            trees[index].add += v;
            trees[index].max += v;
            return;
        }
        create(index);
        pushdown(index);
        int middle = (r - l) / 2 + l;
        if (start <= middle) {
            update(trees[index].ls, l, middle, start, end, v);
        }
        if (end > middle) {
            update(trees[index].rs, middle + 1, r, start, end, v);
        }
        pushup(index);
    }

    private void pushup(int index) {
        trees[index].max = Math.max(trees[trees[index].ls].max, trees[trees[index].rs].max);
    }

    private int query(int index, int l, int r, int start, int end) {
        if (start <= l && r <= end) {
            return trees[index].max;
        }
        create(index);
        pushdown(index);
        int middle = (r - l) / 2 + l;
        int ans = 0;
        if (start <= middle) {
            ans = Math.max(ans, query(trees[index].ls, l, middle, start, end));
        }
        if (end > middle) {
            ans = Math.max(ans, query(trees[index].rs, middle + 1, r, start, end));
        }
        return ans;
    }

    private void pushdown(int index) {
        trees[trees[index].ls].add += trees[index].add;
        trees[trees[index].rs].add += trees[index].add;
        trees[trees[index].ls].max += trees[index].add;
        trees[trees[index].rs].max += trees[index].add;
        trees[index].add = 0;
    }

    private void create(int index) {
        if (trees[index] == null) {
            trees[index] = new Node();
        }
        if (trees[index].ls == 0) {
            trees[index].ls = ++mIndex;
            trees[trees[index].ls] = new Node();
        }
        if (trees[index].rs == 0) {
            trees[index].rs = ++mIndex;
            trees[trees[index].rs] = new Node();
        }
    }

    static class Node{

        int ls;
        int rs;
        int add;
        int max;
    }
}
