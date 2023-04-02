package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2023/2/8
 * @author chenglong
 * description : 删除子文件夹
 *
 * 你是一位系统管理员，手里有一份文件夹列表folder，你的任务是要删除该列表中的所有子文件夹，并以任意顺序返回剩下的文件夹。
 * 如果文件夹folder[i]位于另一个文件夹folder[j]下，那么folder[i]就是folder[j]的子文件夹。
 * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/'后跟一个或者多个小写英文字母。
 * 例如，"/leetcode"和"/leetcode/problems"都是有效的路径，而空字符串和"/"不是。
 *
 * 示例 1：
 * 输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * 输出：["/a","/c/d","/c/f"]
 * 解释："/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
 *
 * 示例 2：
 * 输入：folder = ["/a","/a/b/c","/a/b/d"]
 * 输出：["/a"]
 * 解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
 *
 * 示例 3：
 * 输入: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * 输出: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 * 提示：
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i]只包含小写字母和'/'
 * folder[i]总是以字符'/'起始
 * 每个文件夹名都是唯一的
 */
public class RemoveSubfolders {

    public List<String> removeSubfolders(String[] folder) {
        //1，按照字母顺序排序
        Arrays.sort(folder);
        //2，依次判断是否满足条件
        List<String> result = new ArrayList<>();
        result.add(folder[0]);
        for (int i = 1; i < folder.length; i++) {
            String pre = result.get(result.size() - 1);
            String cur = folder[i];
            if (pre.length() >= cur.length()) {
                result.add(cur);
            } else {
                if (!isContain(cur, pre)) {
                    result.add(cur);
                }
            }
        }
        return result;
    }

    private boolean isContain(String cur, String pre) {
        for (int i = 0; i < pre.length(); i++) {
            if (cur.charAt(i) != pre.charAt(i)) {
                return false;
            }
        }
        return cur.charAt(pre.length()) == '/';
    }
}
