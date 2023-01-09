import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class leet17 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
        System.out.println(letterCombinations("234"));
    }
    public static List<String> letterCombinations(String digits) {
        //Runtime: 1 ms, faster than 96.42% of Java online submissions for Letter Combinations of a Phone Number.
        //Memory Usage: 40.5 MB, less than 99.66% of Java online submissions for Letter Combinations of a Phone Number.
        List<String> res=new ArrayList<>();
        if(digits==null || digits.length()==0) return res;
        Map<Character,ArrayList<String>> map1=new HashMap<>();
        map1.put('2',new ArrayList<>(Arrays.asList("a","b","c")));
        map1.put('3',new ArrayList<>(Arrays.asList("d","e","f")));
        map1.put('4',new ArrayList<>(Arrays.asList("g","h","i")));
        map1.put('5',new ArrayList<>(Arrays.asList("j","k","l")));
        map1.put('6',new ArrayList<>(Arrays.asList("m","n","o")));
        map1.put('7',new ArrayList<>(Arrays.asList("p","q","r","s")));
        map1.put('8',new ArrayList<>(Arrays.asList("t","u","v")));
        map1.put('9',new ArrayList<>(Arrays.asList("w","x","y","z")));

        StringBuilder sb=new StringBuilder();
        backtracking(digits,map1,res,sb,0);
        return res;
    }
    public static void backtracking(String digits, Map<Character,ArrayList<String>> map1,List<String> res,StringBuilder sb,int depth){
        if(sb.length()==digits.length()){
            res.add(sb.toString());
            return;
        }


        ArrayList listatthisnumber=map1.get(digits.charAt(depth));
        for (int j=0; j<listatthisnumber.size();j++){ //j++的处理是在for循环里面就是宽度，不用你人为传j这个参数
            sb.append(listatthisnumber.get(j));
            backtracking(digits,map1,res,sb,depth+1); //+1的处理是递归深度，是下一次选择的可选范围的代号
            sb.delete(sb.length()-1,sb.length());
        }
    }



    public static List<String> letterCombinations2(String digits) {
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
        //比如digits如果为"23",num 为0，则str表示2对应的 abc
        StringBuilder temp = new StringBuilder();
        //迭代处理
        backTracking2(digits, numString, list,temp,0);
        return list;

    }


    public static void backTracking2(String digits, String[] numString, List<String> list,StringBuilder temp,int whichdigit) {
        //遍历全部一次记录一次得到的字符串
        if (whichdigit == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(whichdigit) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTracking2(digits, numString, list,temp,whichdigit + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}
