import java.util.*;

public class leet332 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/reconstruct-itinerary/
    }
    public List<String> findItinerary(String[][] tickets) {
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        visit("JFK");
        return route;
    }

    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();

    void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }



    public List<String> findItinerary2(String[][] tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
                stack.push(targets.get(stack.peek()).poll());
            route.add(0, stack.pop());
        }
        return route;
    }




    //Before going into details about the problem some pre-requiste
    //
    //-Euler path:- It is a path in a graph which visists each edges exactly onces.
    //example:- A->B,B->C,C->D,D->E,E->C
    //-Euler Circuit-> it is a Euler Path , where starting and end nodes are same.
    //example:- A->B,B->C,C->D,D->E,E->C,C->A.
    //
    //The problem is exactly printing the Euler path/Circuit.
    //Example:- A->B,A->C,C->D,D->E,E->C,C->A.
    //Because the Euler Path for above graph is only one :- A->C->D->E->C->A->B.
    //
    //If we carefully look into the above graph, the problem seems very similar to topological sort in reverse order(topological sort can be applied to only acylic DAG).
    // To overcome the cyclic nature that can be present in this question, we will remove the edge once visited(making it acyclic).
    //Now we kinda do a topological sort and add nodes to result list in reverse.
    //Time complexity will remain O(V+E) and space as O(E)
    //
    //Inspired from @StefanPochmann soln ,
    //
    //1st Solution is Recursive
    //2nd Soltuion is Stack based Iterative






    Map<String, PriorityQueue<String>> targetMap = new HashMap<>();
    List<String> ans = new ArrayList<>();

    public List<String> findOrderRec(List<List<String>> tickets) {

        for(List<String> tick : tickets) {
            if(targetMap.get(tick.get(0)) == null) {
                targetMap.put(tick.get(0), new PriorityQueue<String>());
            }
            targetMap.get(tick.get(0)).add(tick.get(1));
        }
        visitDFS("JFK");
        return ans;
    }

    public void visitDFS(String airport) {

        while(targetMap.get(airport) != null && targetMap.get(airport).size() > 0) {
            String nextAirport = targetMap.get(airport).poll();
            visitDFS(nextAirport);
        }

        ans.add(0, airport);
    }









    public List<String> findOrderIter(List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> mapIter = new HashMap<>();
        List<String> resIter = new ArrayList<>();

        for(int i =0;i<tickets.size();i++) {

            if(mapIter.get(tickets.get(i).get(0)) == null) {
                mapIter.put(tickets.get(i).get(0), new PriorityQueue<String>());
            }
            mapIter.get(tickets.get(i).get(0)).add(tickets.get(i).get(1));
        }

        Stack<String> stack = new Stack<String> ();
        stack.push("JFK");
        while(!stack.isEmpty()) {

            while(mapIter.get(stack.peek()) != null && mapIter.get(stack.peek()).size() > 0) {
                String nextAirport = mapIter.get(stack.peek()).poll();
                stack.push(nextAirport);
            }
            resIter.add(0, stack.pop());

        }
        return resIter;
    }



    public List<String> findItinerary3(List<List<String>> tickets) {
//         O(V+E) time complexity and O(V) Space complexity
        // return findOrderRec(tickets);


//         Iterative Appraoch using Stack Based Approach
        return findOrderIter(tickets);

    }
}
