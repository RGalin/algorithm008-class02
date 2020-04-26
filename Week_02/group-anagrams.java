//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例:
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//
// 说明：
//
//
// 所有输入均为小写字母。
// 不考虑答案输出的顺序。
//
// Related Topics 哈希表 字符串


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList();

        int[] count = new int[26];
        Map<String, List<String>> ans = new HashMap<>();

        for (String s : strs) {
            Arrays.fill(count, 0);

            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i : count) {
                sb.append("#");
                sb.append(i);
            }
            String key = sb.toString();

            if(!ans.containsKey(key)) ans.put(key, new ArrayList<String>());
            ans.get(key).add(s);
        }

        return new ArrayList<List<String>>(ans.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
