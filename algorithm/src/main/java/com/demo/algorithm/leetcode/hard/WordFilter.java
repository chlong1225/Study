package com.demo.algorithm.leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2022/7/14
 *
 * @author chenglong
 * description : 前缀和后缀搜索
 * <p>
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * 实现 WordFilter 类：
 * WordFilter(string[] words) 使用词典中的单词words初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀prefix和后缀suff的单词的下标。如果存在不止一个满足要求的下标，返回其中最大的下标。如果不存在这样的单词，返回 -1 。
 * <p>
 * 示例：
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 * <p>
 * 提示：
 * 1 <= words.length <= 10^4
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数f执行10^4次调用
 */
public class WordFilter {

    private DictionaryTree preTree;
    private DictionaryTree suffixTree;
    private int maxIndex = -1;

    public WordFilter(String[] words) {
        preTree = new DictionaryTree();
        suffixTree = new DictionaryTree();
        //分别构建前缀字典树与后缀字典树
        int length = words.length;
        for (int i = 0; i < length; i++) {
            if (words[i] == null || words[i].length() == 0) {
                continue;
            }
            buildPreTree(i, words[i], preTree);
            buildSuffixTree(i, words[i], suffixTree);
        }
    }

    private void buildSuffixTree(int num, String word, DictionaryTree root) {
        int length = word.length();
        DictionaryTree p = root;
        for (int i = length - 1; i >= 0; i--) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
            if (i == 0) {
                p.index = num;
            }
        }
    }

    private void buildPreTree(int num, String word, DictionaryTree root) {
        int length = word.length();
        DictionaryTree p = root;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
            if (i == length - 1) {
                p.index = num;
            }
        }
    }

    public int f(String pref, String suff) {
        maxIndex = -1;
        //1，查找所有满足前缀的单词index
        DictionaryTree p = preTree;
        int length = pref.length();
        for (int i = 0; i < length; i++) {
            int index = pref.charAt(i) - 'a';
            p = p.childrens[index];
            if (p == null) {
                break;
            }
        }
        //找不到前缀对应的单词
        if (p == null) {
            return maxIndex;
        }
        Set<Integer> dates = new HashSet<>();
        //2，遍历获取所有满足前缀的单词index
        dfsPre(p, dates);
        //3，查找满足添加的后缀单词
        DictionaryTree q = suffixTree;
        length = suff.length();
        for (int i = length - 1; i >= 0; i--) {
            int index = suff.charAt(i) - 'a';
            q = q.childrens[index];
            if (q == null) {
                break;
            }
        }
        //找不到后缀对应的单词
        if (q == null) {
            return maxIndex;
        }
        //4，遍历满足条件的后缀单词，同时比较查找的前缀index集合
        dfsSuffix(q, dates);
        return maxIndex;
    }

    private void dfsSuffix(DictionaryTree root, Set<Integer> dates) {
        if (root.index != -1) {
            if (dates.contains(root.index)) {
                if (root.index > maxIndex) {
                    maxIndex = root.index;
                }
            }
        }
        for (int i = 0; i < root.childrens.length; i++) {
            DictionaryTree node = root.childrens[i];
            if (node != null) {
                dfsSuffix(node, dates);
            }
        }
    }

    private void dfsPre(DictionaryTree root, Set<Integer> dates) {
        if (root.index != -1) {
            dates.add(root.index);
        }
        for (int i = 0; i < root.childrens.length; i++) {
            DictionaryTree node = root.childrens[i];
            if (node != null) {
                dfsPre(node, dates);
            }
        }
    }

    public class DictionaryTree {

        public DictionaryTree[] childrens;
        public int index = -1;

        //默认长度26,只支持小写或大写字母。其它的需要自定义长度进行扩展
        public DictionaryTree() {
            this(26);
        }

        public DictionaryTree(int length) {
            childrens = new DictionaryTree[length];
        }
    }
}
