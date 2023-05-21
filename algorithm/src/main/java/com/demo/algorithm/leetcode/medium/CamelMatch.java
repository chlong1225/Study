package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/4/14
 * @author chenglong
 * description : 驼峰式匹配
 *
 * 如果我们可以将小写字母插入模式串pattern得到待查询项query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入0个字符。）
 * 给定待查询列表queries，和模式串pattern，返回由布尔值组成的答案列表answer。只有在待查项queries[i]与模式串pattern匹配时，answer[i]才为true，否则为false。
 *
 * 示例 1：
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * 输出：[true,false,true,true,false]
 * 示例：
 * "FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
 * "FootBall" 可以这样生成："F" + "oot" + "B" + "all".
 * "FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
 *
 * 示例 2：
 * 输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * 输出：[true,false,true,false,false]
 * 解释：
 * "FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
 * "FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
 *
 * 示例 3：
 * 输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * 输入：[false,true,false,false,false]
 * 解释：
 * "FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
 *
 * 提示：
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * 所有字符串都仅由大写和小写英文字母组成。
 */
public class CamelMatch {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length;
        List<String> patterns = splitWord("A" + pattern);
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(compare(splitWord("A" + queries[i]), patterns));
        }
        return result;
    }

    private boolean compare(List<String> dates, List<String> patterns) {
        if (dates.size() != patterns.size()) {
            return false;
        }
        for (int i = 0; i < dates.size(); i++) {
            boolean compareWord = compareWord(dates.get(i), patterns.get(i));
            if (!compareWord) {
                return false;
            }
        }
        return true;
    }

    private boolean compareWord(String word, String pattern) {
        //首位大写字母必须相等
        if (word.charAt(0) != pattern.charAt(0)) {
            return false;
        }
        //pattern添加小写字母后生成word，pattern不能比word长
        if (pattern.length() > word.length()) {
            return false;
        }
        //此时只有一个大写字母。word后的小写字母都靠添加
        if (pattern.length() == 1) {
            return true;
        }
        int index1 = 1;
        int index2 = 1;
        while (index1 < pattern.length() && index2 < word.length()) {
            if (pattern.charAt(index1) == word.charAt(index2)) {
                index1++;
                index2++;
            } else {
                index2++;
            }
        }
        return index1 == pattern.length();
    }

    //将单词按照大写字母分组
    private List<String> splitWord(String word) {
        List<String> dates = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                //此时大写字母
                dates.add(word.substring(start, i));
                start = i;
            }
        }
        dates.add(word.substring(start));
        return dates;
    }
}
