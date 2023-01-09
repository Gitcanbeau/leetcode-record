import java.util.ArrayList;
import java.util.HashMap;

public class leet79 {
    public static void main(String[] args) {
        //Given an m x n grid of characters board and a string word, return true if word exists in the grid.
        //The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
        //Example 1:
        //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        //Output: true
        //Example 2:
        //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
        //Output: true
        //Example 3:
        //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
        //Output: false
        //Constraints:
        //m == board.length
        //n = board[i].length
        //1 <= m, n <= 6
        //1 <= word.length <= 15
        //board and word consists of only lowercase and uppercase English letters.
        char[][]board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        //写不出来完整的
    }
    public static boolean exist(char[][] board, String word) {
        HashMap<Character, ArrayList<Integer>> hm1=new HashMap<>();
        int rowtotal=board.length;
        int coltotal=board[0].length;
        //将charr作为key存储到字典中，每个重复的charr的位置添加到字典的值value的数组中
        //charr在board中出现的位置使用一维表示
        // 位置除以4得到行数，对4取余得到列数，同一行左右相邻的话会差1，同一列上下相邻的话会差4，但是注意不要超过边界,用getmax判断一下
        //找到一个合理的需要标记为用过，就是可以把字典中的值的数组中的该位置删除
        int hm1index=0;
        for(int i=0; i<rowtotal;i++){
            for(int j=0; j<coltotal;j++){
                if(hm1.containsKey(board[i][j])){
                    hm1.get(board[i][j]).add(hm1index);
                    hm1index++;
                }else{
                    ArrayList<Integer> bolocationofKey=new ArrayList<>();
                    bolocationofKey.add(hm1index);
                    hm1.put(board[i][j],bolocationofKey);//不平移也行
                    hm1index++;
                }
            }
        }
        int[] store = new int[26];
        // Traverse string to keep track number of times each character in the string appears...

        char[] chararr = word.toCharArray();
        for(char ch : chararr){
            store[ch-'A']++;  //字符对应的编码-a字母对应的编码就等于0，刚好index0就可以用
        }//++是说store[index]在index处的值+1
        HashMap<Character,Integer> hmsummary=new HashMap<>();

        for(int i=0; i<chararr.length;i++){
            if(!hm1.containsKey(chararr[i])){ //连你要的字母都没有就直接false了
                return false;
            }
            //如果想要个字母个数比我字典里面该字母的位置出现的次数都多的话，那就肯定不对，就直接false了
            //简单理解就是，连你要的字母都没有就直接false了
        }

        //如果基本条件符合，我再从选择最少的字母入手查看它的邻居是否符合

        return false;

    }

    public static boolean isNeighbour (int i, int j){
        int max=Math.max(i,j);
        if(j-i==4 | j-i==1){
            return true;
        }else{
            return false;
        }
    }
}
