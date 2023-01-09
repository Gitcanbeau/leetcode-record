public class leet443 {
    public static void main(String[] args) {
        //Given an array of characters chars, compress it using the following algorithm:
        //Begin with an empty string s. For each group of consecutive repeating characters in chars:
        //If the group's length is 1, append the character to s.
        //Otherwise, append the character followed by the group's length.
        //The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
        //After you are done modifying the input array, return the new length of the array.
        //You must write an algorithm that uses only constant extra space.
        //Example 1:
        //Input: chars = ["a","a","b","b","c","c","c"]
        //Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
        //Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
        //Example 2:
        //Input: chars = ["a"]
        //Output: Return 1, and the first character of the input array should be: ["a"]
        //Explanation: The only group is "a", which remains uncompressed since it's a single character.
        //Example 3:
        //Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
        //Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
        //Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
        //Constraints:
        //1 <= chars.length <= 2000
        //chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
        char[] chars={'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compress(chars));
        //Runtime: 1 ms, faster than 99.67% of Java online submissions for String Compression.
        //Memory Usage: 41.6 MB, less than 98.65% of Java online submissions for String Compression.
    }

    public static int compress(char[] chars) {
        int count = 1;
        int index = 0;
        char curr = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != curr) { //遇到下一个不一样的才会开始存东西
                chars[index++] = curr; //存字母

                if (count != 1) {
                    char[] countarr = String.valueOf(count).toCharArray(); //存上一个字母curr的个数
                    for (char c : countarr) {
                        chars[index++] = c;
                    }
                }

                count = 1; //恢复default
                curr = chars[i]; //新字母赋值给curr
            } else {
                count++; //一样的字母就计数就行了
            }
        }
        chars[index++]=curr; //最后一个字母后面没东西，所以无法启动添加过程，需要出了for循环手动书写一遍
        if(count!=1){
            char[] countarr=String.valueOf(count).toCharArray();
            for (char c : countarr) {
                chars[index++] = c;
            }
        }
        return index;
    }
}
