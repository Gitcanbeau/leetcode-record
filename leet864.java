import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class leet864 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/shortest-path-to-get-all-keys/
    }
    class State{
        int x=-1;
        int y=-1;
        StringBuilder keys = null;
        public State(int x, int y, StringBuilder keys){
            this.x = x;
            this.y = y;
            this.keys = keys;
        }

        public boolean canUnlock(char lock){
            lock = Character.toLowerCase(lock);
            return keys.charAt(lock-'a')==lock;
        }
    }

    int num_keys;
    int startx;
    int starty;
    int m;
    int n;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final String KEY_MASK = "abcdef";
    private final String EMPTY_KEY_MASK = "      ";

    public int shortestPathAllKeys(String[] grid) {
        //Runtime: 757 ms, faster than 11.64% of Java online submissions for Shortest Path to Get All Keys.
        //Memory Usage: 118.6 MB, less than 15.11% of Java online submissions for Shortest Path to Get All Keys
        m = grid.length;
        n = grid[0].length();
        num_keys=0;
        findStartAndKeys(grid);

        String all_keys = KEY_MASK.substring(0, num_keys);
        int steps = 0;
        Set<String> visited = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        State start_state = new State(startx, starty, new StringBuilder(EMPTY_KEY_MASK));
        q.offer(start_state);
        visited.add(startx+","+starty+","+start_state.keys.toString());

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                State s = q.poll();
                //check for finish condition
                if(s.keys.toString().trim().equals(all_keys))return steps;
                for(int[] dir: dirs){
                    int nx = s.x + dir[0];
                    int ny = s.y + dir[1];

                    if(nx<0 || nx>=m || ny<0 || ny>=n)continue;
                    if(visited.contains(nx+","+ny+","+s.keys.toString())){
                        continue;
                    }
                    visited.add(nx+","+ny+","+s.keys.toString());//mark visited

                    char ch = grid[nx].charAt(ny);
                    if(ch=='#')continue;
                    //Collect if key
                    StringBuilder new_keys = new StringBuilder(s.keys);
                    if(Character.isLowerCase(ch)){
                        new_keys.setCharAt(ch-'a', ch);
                    }
                    //LOCK
                    if(Character.isUpperCase(ch) && !s.canUnlock(ch)){
                        continue;
                    }
                    //good to offer
                    State new_state = new State(nx, ny, new_keys);

                    q.offer(new_state);
                }

            }
            steps++;

        }


        return -1;
    }

    private void findStartAndKeys(String[] grid){
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char ch = grid[i].charAt(j);
                if(ch>='a' && ch<='f'){
                    num_keys = Math.max(num_keys, (ch-'a'+1));
                }
                else if(ch=='@'){
                    startx = i;
                    starty = j;
                }
            }
        }
    }




}
