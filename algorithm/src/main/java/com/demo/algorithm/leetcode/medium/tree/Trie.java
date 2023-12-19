package com.demo.algorithm.leetcode.medium.tree;

import com.demo.algorithm.leetcode.entity.DictionaryTree;

/**
 * create on 2023/12/18
 * @author chenglong
 * description : 实现Trie(前缀树)
 *
 * Trie（发音类似"try"）或者说前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现Trie类：
 * Trie()初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串word。
 * boolean search(String word) 如果字符串word在前缀树中，返回true（即，在检索之前已经插入）；否则，返回false。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word的前缀之一为prefix，返回true；否则，返回false。
 *
 * 示例：
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word和prefix仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数总计不超过3*10^4次
 */
public class Trie {

    private DictionaryTree root;

    public Trie() {
        root = new DictionaryTree();
    }

    public void insert(String word) {
        DictionaryTree p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
        }
        p.isEnd = true;
    }

    public boolean search(String word) {
        DictionaryTree p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                return false;
            }
            p = p.childrens[index];
        }
        return p.isEnd;
    }

    public boolean startsWith(String prefix) {
        DictionaryTree p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                return false;
            }
            p = p.childrens[index];
        }
        return true;
    }


}
