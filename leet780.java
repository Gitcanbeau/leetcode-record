public class leet780 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/reaching-points/
        System.out.println(reachingPoints(1,1,3,5));//true
        System.out.println(reachingPoints(1,1,2,2));//false
        System.out.println(reachingPoints(1,1,1,1));//true
    }
    private static boolean canReach;
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        //有点像棋盘问题的sudoku
        canReach=false; //初始化还是建议在主函数内部进行，因为全局变量会保存上一次的结果
        if(sx>tx || sy>ty) return canReach;
        backtracking(sx,sy,tx,ty);
        return canReach;
    }
    private static void backtracking(int x, int y, int tx, int ty){
        //stackoverflow, could be due to too many recursions
        if(x>tx || y>ty) return;
        if(x==tx && y==ty){
            canReach=true;
            return;
        }

        if(x<=tx && x+y<=ty){
            backtracking(x,x+y,tx,ty);
        }
        if(x+y<=tx && y<=ty) {
            backtracking(x + y, y, tx, ty);
        }


        return;
    }

    //Runtime: 1 ms, faster than 76.87% of Java online submissions for Reaching Points.
    //Memory Usage: 41.5 MB, less than 15.24% of Java online submissions for Reaching Points.
    //真他妈讨厌数学，我好像一个弱智啊
    public static boolean reachingPoints2(int sx, int sy, int tx, int ty) {

        if (sx > tx || sy > ty) return false;
        if (sx == tx && (ty - sy) % sx == 0) return true;
        if (sy == ty && (tx - sx) % sy == 0) return true;
        return reachingPoints2(sx, sy, tx % ty, ty % tx);
    }

    public static boolean reachingPoints3(int sx, int sy, int tx, int ty) {
        //Runtime: 443 ms, faster than 5.08% of Java online submissions for Reaching Points.
        //Memory Usage: 39.2 MB, less than 91.31% of Java online submissions for Reaching Points.
        while (sx <= tx && sy <= ty) {
            if (sx == tx && sy == ty) return true;

            if (tx == ty) return false;

            if (sx == tx) return (ty - sy) % sx == 0;
            if (sy == ty) return (tx - sx) % sy == 0;

            if (tx > ty)
                tx = tx - ty;
            else
                ty = ty - tx;
        }

        return false;
    }
}
