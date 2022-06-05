package com.demo.algorithm.leetcode.contest.week296;

/**
 * Created by chl on 2022/6/5.
 * description : 设计一个文本编辑器
 *
 * 请你设计一个带光标的文本编辑器，它可以实现以下功能：
 * 添加：在光标所在处添加文本。
 * 删除：在光标所在处删除文本（模拟键盘的删除键）。
 * 移动：将光标往左或者往右移动。
 * 当删除文本时，只有光标左边的字符会被删除。光标会留在文本内，也就是说任意时候0<=cursor.position<=currentText.length都成立。
 *
 * 请你实现TextEditor类：
 * TextEditor()用空文本初始化对象。
 * void addText(string text)将text添加到光标所在位置。添加完后光标在text的右边。
 * int deleteText(int k)删除光标左边k个字符。返回实际删除的字符数目。
 * string cursorLeft(int k) 将光标向左移动k次。返回移动后光标左边min(10, len)个字符，其中len是光标左边的字符数目。
 * string cursorRight(int k)将光标向右移动k次。返回移动后光标左边min(10, len)个字符，其中len是光标左边的字符数目。
 *
 * 示例 1：
 * 输入：
 * ["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
 * [[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
 * 输出：
 * [null, null, 4, null, "etpractice", "leet", 4, "", "practi"]
 * 解释：
 * TextEditor textEditor = new TextEditor(); // 当前 text 为 "|" 。（'|' 字符表示光标）
 * textEditor.addText("leetcode"); // 当前文本为 "leetcode|" 。
 * textEditor.deleteText(4); // 返回 4
 *                           // 当前文本为 "leet|" 。
 *                           // 删除了 4 个字符。
 * textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
 * textEditor.cursorRight(3); // 返回 "etpractice"
 *                            // 当前文本为 "leetpractice|".
 *                            // 光标无法移动到文本以外，所以无法移动。
 *                            // "etpractice" 是光标左边的 10 个字符。
 * textEditor.cursorLeft(8); // 返回 "leet"
 *                           // 当前文本为 "leet|practice" 。
 *                           // "leet" 是光标左边的 min(10, 4) = 4 个字符。
 * textEditor.deleteText(10); // 返回 4
 *                            // 当前文本为 "|practice" 。
 *                            // 只有 4 个字符被删除了。
 * textEditor.cursorLeft(2); // 返回 ""
 *                           // 当前文本为 "|practice" 。
 *                           // 光标无法移动到文本以外，所以无法移动。
 *                           // "" 是光标左边的 min(10, 0) = 0 个字符。
 * textEditor.cursorRight(6); // 返回 "practi"
 *                            // 当前文本为 "practi|ce" 。
 *                            // "practi" 是光标左边的 min(10, 6) = 6 个字符。
 *
 * 提示：
 * 1 <= text.length, k <= 40
 * text只含有小写英文字母。
 * 调用 addText，deleteText，cursorLeft和cursorRight的总次数不超过2 * 10^4次。
 */
public class TextEditor {

    private StringBuilder builder;
    private int index;

    public TextEditor() {
        builder = new StringBuilder();
        index = 0;
    }

    public void addText(String text) {
        if (builder.length() == index) {
            builder.append(text);
        } else {
            //在index之后添加
            builder.insert(index, text);
        }
        index += text.length();
    }

    public int deleteText(int k) {
        if (index >= k) {
            builder.delete(index - k, index);
            index -= k;
            return k;
        } else {
            int tem = index;
            builder.delete(0, index);
            index = 0;
            return tem;
        }
    }

    public String cursorLeft(int k) {
        index -= k;
        if (index < 0) {
            index = 0;
        }
        if (index >= 10) {
            return builder.substring(index - 10, index);
        } else {
            return builder.substring(0, index);
        }
    }

    public String cursorRight(int k) {
        index += k;
        if (index > builder.length()) {
            index = builder.length();
        }
        if (index >= 10) {
            return builder.substring(index - 10, index);
        } else {
            return builder.substring(0, index);
        }
    }
}
