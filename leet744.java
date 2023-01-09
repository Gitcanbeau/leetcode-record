public class leet744 {
    public static void main(String[] args) {
        //Given a characters array letters that is sorted in non-decreasing order and a character target, return the smallest character in the array that is larger than target.
        //Note that the letters wrap around.
        //For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
        //Example 1:
        //Input: letters = ["c","f","j"], target = "a"
        //Output: "c"
        //Example 2:
        //Input: letters = ["c","f","j"], target = "c"
        //Output: "f"
        //Example 3:
        //Input: letters = ["c","f","j"], target = "d"
        //Output: "f"
        //Constraints:
        //2 <= letters.length <= 104
        //letters[i] is a lowercase English letter.
        //letters is sorted in non-decreasing order.
        //letters contains at least two different characters.
        //target is a lowercase English letter.
        char[] letters={'c','f','j'};
//        char target='f';
        char target='j';
        System.out.println(nextGreatestLetter(letters,target));
        //Runtime: 1 ms, faster than 57.95% of Java online submissions for Find Smallest Letter Greater Than Target.
        //Memory Usage: 48 MB, less than 46.71% of Java online submissions for Find Smallest Letter Greater Than Target.
    }
    public static char nextGreatestLetter(char[] letters, char target) {
        if(target>=letters[letters.length-1]){
            return letters[0];
        }
        int left=0;
        int right=letters.length;
        while(left<right){
            int mid=left+(right-left)/2;
            if(letters[mid]>target){//不要光按模版写，这个等号给谁具体问题具体分析，再检查检查
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return letters[left];
    }
}
