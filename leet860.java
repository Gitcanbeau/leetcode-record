public class leet860 {
    public static void main(String[] args) {
        //https://leetcode.com/problems/lemonade-change/
//        int[] bills={5,5,5,10,20};
        int[] bills={5,5,10,10,20};
        System.out.println(lemonadeChange(bills));
    }
    public static boolean lemonadeChange(int[] bills) {
        //Runtime: 2 ms, faster than 98.07% of Java online submissions for Lemonade Change.
        //Memory Usage: 54.3 MB, less than 86.32% of Java online submissions for Lemonade Change.
        int count_five=0;
        int count_ten=0;
        int count_twenty=0;
        if(bills[0]!=5) return false;
        count_five++;

        for(int i=1; i<bills.length;i++){
            if(bills[i]==5) {
                count_five++;
            }

            if(bills[i]==10){
                if(count_five>=1){
                    count_five--;
                    count_ten++;
                }else{
                    return false;
                }
            }

            if(bills[i]==20){
                if(count_ten>=1 && count_five>=1){
                    count_ten--;
                    count_five--;
                    count_twenty++;
                }else if(count_ten<1 && count_five>=3){
                    count_five=count_five-3;
                    count_twenty++;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

}
