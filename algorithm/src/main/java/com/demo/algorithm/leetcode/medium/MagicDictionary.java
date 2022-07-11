package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.DictionaryTree;

/**
 * create on 2022/7/11
 * @author chenglong
 * description : 实现一个魔法字典
 *
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词互不相同。 如果给出一个单词请判定能否只将这个单词中一个字母换成另一个字母使得所形成的新单词存在于你构建的字典中。
 *
 * 实现 MagicDictionary 类：
 * MagicDictionary() 初始化对象
 * void buildDict(String[]dictionary)使用字符串数组dictionary设定该数据结构，dictionary中的字符串互不相同
 * bool search(String searchWord)给定一个字符串searchWord，判定能否只将字符串中一个字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以返回true；否则返回false。
 *
 * 示例：
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 *
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 *
 * 提示：
 * 1 <=dictionary.length <= 100
 * 1 <=dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <=searchWord.length <= 100
 * searchWord仅由小写英文字母组成
 * buildDict仅在search之前调用一次
 * 最多调用100次search
 */
public class MagicDictionary {

    private DictionaryTree root;
    private boolean[] marks;

    public MagicDictionary() {
        root = new DictionaryTree();
        marks = new boolean[101];
    }

    public void buildDict(String[] dictionary) {
        int length = dictionary.length;
        for (int i = 0; i < length; i++) {
            String word = dictionary[i];
            marks[word.length()] = true;
            buildTree(word, root);
        }
    }

    //构建字典树
    private void buildTree(String word, DictionaryTree root) {
        if (word == null || word.length() == 0) {
            return;
        }
        DictionaryTree p = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
            if (i == length - 1) {
                p.isEnd = true;
            }
        }
    }

    public boolean search(String searchWord) {
        //1，先判断字典树中是否有长度相同的字符，交换字符时长度是不会变的
        int length = searchWord.length();
        if (!marks[length]) {
            return false;
        }
        char[] dates = searchWord.toCharArray();
        //2，遍历枚举修改后的字符串
        for (int i = 0; i < length; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                //3，构建新字符
                if (dates[i] == j) {
                    continue;
                }
                char tem = dates[i];
                dates[i] = j;
                boolean isFind = findWord(dates);
                if (isFind) {
                    return true;
                }
                dates[i] = tem;
            }
        }
        return false;
    }

    //在字典树中查找字符串
    private boolean findWord(char[] dates) {
        DictionaryTree p = root;
        int length = dates.length;
        for (int i = 0; i < length; i++) {
            int index = dates[i] - 'a';
            if (p.childrens[index] == null) {
                return false;
            }
            p = p.childrens[index];
            if (i == length - 1) {
                return p.isEnd;
            }
        }
        return false;
    }
}
