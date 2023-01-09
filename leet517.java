import java.util.Arrays;

public class leet517 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/super-washing-machines/
    }
    public static int findMinMoves(int[] machines) {
        if(machines==null || machines.length==0) return -1;
        int sumofdresses=0;
        for(int i=0;i<machines.length;i++){
            sumofdresses+=machines[i];
        }
        if(sumofdresses % machines.length!=0) return -1;

        //greedy or dp
        int goal = sumofdresses/machines.length;

        int maxChanges=0, totalSoFar=0;
        for(int i=0; i<machines.length; i++){
            totalSoFar+=machines[i];            //Sum of dresses in machines so far [0...i]

            //So far have too many dresses or need more dresses
            maxChanges = Math.max(maxChanges, Math.abs((i+1)*goal-totalSoFar));

            //Current machine has too many dresses
            maxChanges = Math.max(maxChanges, machines[i]-goal);
        }

        return maxChanges;

    }
}
