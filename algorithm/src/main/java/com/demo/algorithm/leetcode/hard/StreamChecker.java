package com.demo.algorithm.leetcode.hard;

import com.demo.algorithm.leetcode.entity.DictionaryTree;

/**
 * create on 2023/3/24
 * @author chenglong
 * description : 字符流
 *
 * 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组words中的一个字符串。
 * 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入4个字符 'a'、'x'、'y' 和 'z' ，你所设计的算法应当可以检测到"axyz"的后缀"xyz"与words中的字符串"xyz"匹配。
 * 按下述要求实现StreamChecker类：
 * StreamChecker(String[] words) ：构造函数，用字符串数组words初始化数据结构。
 * boolean query(char letter)：从字符流中接收一个新字符，如果字符流中的任一非空后缀能匹配words中的某一字符串，返回true；否则返回false。
 *
 * 示例：
 * 输入：
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * 输出：
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 * 解释：
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // 返回 False
 * streamChecker.query("b"); // 返回 False
 * streamChecker.query("c"); // 返回n False
 * streamChecker.query("d"); // 返回 True ，因为 'cd' 在 words 中
 * streamChecker.query("e"); // 返回 False
 * streamChecker.query("f"); // 返回 True ，因为 'f' 在 words 中
 * streamChecker.query("g"); // 返回 False
 * streamChecker.query("h"); // 返回 False
 * streamChecker.query("i"); // 返回 False
 * streamChecker.query("j"); // 返回 False
 * streamChecker.query("k"); // 返回 False
 * streamChecker.query("l"); // 返回 True ，因为 'kl' 在 words 中
 *
 * 提示：
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 200
 * words[i] 由小写英文字母组成
 * letter是一个小写英文字母
 * 最多调用查询 4 * 10^4 次
 */
public class StreamChecker {

    private StringBuilder builder;
    private DictionaryTree root;

    public StreamChecker(String[] words) {
        builder = new StringBuilder();
        root = new DictionaryTree();
        for (int i = 0; i < words.length; i++) {
            addWord(words[i]);
        }
    }

    //添加单词
    private void addWord(String word) {
        DictionaryTree p = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
        }
        p.isEnd = true;
    }

    public boolean query(char letter) {
        DictionaryTree p = root;
        builder.append(letter);
        for (int i = builder.length() - 1; i >= 0; i--) {
            int index = builder.charAt(i) - 'a';
            p = p.childrens[index];
            if (p == null) {
                return false;
            }
            if (p.isEnd) {
                return true;
            }
        }
        return false;
    }
}
