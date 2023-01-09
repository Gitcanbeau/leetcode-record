import java.util.*;

public class leet444 {
    public static void main(String[] args) {
        //sequence reconstruction
        //you are given an integer array nums of length n where nums is a permutation of the integers in the range [1,n].
        //you are also given a 2d integer array sequence where sequences[i] is a subsequence of nums.
        //check if nums is the shortest length and has all sequences[i] as subsequences. there could be multiple valid
        //super-sequence with the for the given array sequences.
        ////for example, for sequences=[[1,2],[1,3]], there are two shortest super-sequences,[1,2,3] and [1,3,2]
        ////while for sequences=[[1,2],[1,3],[1,2,3]], the only shortest super-sequence possible is [1,2,3]. [1,2,3,4] is
        ////a possible super-sequence but not the shortest
        //return true if nums is the only shortest super-sequence for sequences, or false otherwise
        //a subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing
        // the order of the remaining elements.
        //example 1:
        //input: nums=[1,2,3], sequences=[[1,2],[1,3]]
        //output: false;
        //explanation: there are two possible super-sequences [1,2,3] and [1,3,2]
        //the sequence [1,2] is a subsequence of both [1,2,3] and [1,3,2]
        //the sequence [1,3] is a subsequence of both [1,2,3] and [1,3,2]
        //therefore, nums is not the only shortest super-sequence; return false;

        int[] nums={1,2,3};
        List<Integer> sequence1=new ArrayList<>();
        sequence1.add(1);
        sequence1.add(2);
        sequence1.add(3);
        List<Integer> sequence2=new ArrayList<>();
        sequence1.add(1);
        sequence1.add(3);
        List<List<Integer>> sequences=new ArrayList<>();
        sequences.add(sequence1);
        sequences.add(sequence2);
        System.out.println(sequenceReconstruction(nums,sequences));
    }

    public static boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences){
        Map<Integer, Set<Integer>> adj=new HashMap<>();
        Map<Integer,Integer> indegree=new HashMap<>();
        for(List<Integer> lst: sequences){
            if(lst.size()==1){
                adj.putIfAbsent(lst.get(0),new HashSet<>());
                indegree.putIfAbsent(lst.get(0),0);
            }else{
                for(int i=1; i<lst.size();i++){
                    adj.putIfAbsent(lst.get(i-1),new HashSet<>());
                    indegree.putIfAbsent(lst.get(i-1),0);
                    adj.putIfAbsent(lst.get(i),new HashSet<>());
                    indegree.putIfAbsent(lst.get(i),0);

                    int pre=lst.get(i-1);
                    int curr=lst.get(i);
                    if(adj.get(pre).add(curr)){
                        indegree.put(curr,indegree.get(curr)+1);
                    }
                }
            }
        }

        Queue<Integer> bfsque=new LinkedList<>();
        for(Integer inte: indegree.keySet()){
            if(indegree.get(inte)==0){
                bfsque.offer(inte);
            }
        }

        int idx=0;
        while(!bfsque.isEmpty()){
            if(bfsque.size()!=1) return false; //有多种选择就肯定不是唯一解
            int from=bfsque.poll();
            if(from!=nums[idx++]) return false; //是唯一解的前提，但是和你给的nums结果不同，说明你的nums给的不对

            for(int to:adj.get(from)){
                indegree.put(to,indegree.get(to)-1);
                if(indegree.get(to)==0){
                    bfsque.offer(to);
                }
            }
        }
        return true;
    }

}
