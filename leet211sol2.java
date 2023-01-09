public class leet211sol2 {
    private static class TrieNode{
        TrieNode[] children;
        public boolean isEndofWord;//这里如果写 public static boolean就会把boolean默认值true共享给所有变量，总之不能static
        public TrieNode(){
            this.isEndofWord=false;
            this.children=new TrieNode[26];
            for(int i=0; i<26;i++){
                children[i]=null;
            }
        }
    }
    public static TrieNode root;
    public leet211sol2() {
        TrieNode root=new TrieNode();
        //新建leet208对象就需要新建TrieNode root，然后外面也要有成员变量 root，不然别的方法里面没法用，最后还要this.root联系起来，不然别人不知道
        this.root=root;
    }

    public static void addWord(String word){
        TrieNode currnode=root;
        for(char ch: word.toCharArray()){
            int index=ch-'a';
            if(currnode.children[index]==null){
                currnode.children[index]=new TrieNode();
            }
            currnode=currnode.children[index];
        }
        currnode.isEndofWord=true;
    }

    public static boolean search(String word){
        return hasword(word,root,0);
    }

    private static boolean hasword(String word, TrieNode currroot, int indexofword){
        if(currroot==null) return false;
        if(indexofword==word.length()){
            return currroot.isEndofWord;
        }
        if(word.charAt(indexofword)=='.'){
            for(TrieNode child: currroot.children){
                if(hasword(word, child, indexofword+1)) return true;
            }
            return false;
        }else{
            return hasword(word, currroot.children[word.charAt(indexofword)-'a'],indexofword+1);
        }
    }

    public static void main(String[] args) {
        //Design a data structure that supports adding new words and finding if a string matches any previously added string.
        //Implement the WordDictionary class:
        //WordDictionary() Initializes the object.
        //void addWord(word) Adds word to the data structure, it can be matched later.
        //bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
        //Example:
        //Input
        //["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        //[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
        //Output
        //[null,null,null,null,false,true,true,true]
        //Explanation
        //WordDictionary wordDictionary = new WordDictionary();
        //wordDictionary.addWord("bad");
        //wordDictionary.addWord("dad");
        //wordDictionary.addWord("mad");
        //wordDictionary.search("pad"); // return False
        //wordDictionary.search("bad"); // return True
        //wordDictionary.search(".ad"); // return True
        //wordDictionary.search("b.."); // return True
        //Constraints:
        //1 <= word.length <= 25
        //word in addWord consists of lowercase English letters.
        //word in search consist of '.' or lowercase English letters.
        //There will be at most 3 dots in word for search queries.
        //At most 104 calls will be made to addWord and search.
        leet211sol2 leet211sol2dictionary=new leet211sol2();
        leet211sol2dictionary.addWord("bad");
        leet211sol2dictionary.addWord("dad");
//        leet211sol2dictionary.addWord("mad");
//        leet211sol2dictionary.addWord("pad");
//        leet211sol2dictionary.addWord("bad");
        System.out.println(leet211sol2dictionary.search("bad"));//true
        System.out.println(leet211sol2dictionary.search("dad"));//true
        System.out.println(leet211sol2dictionary.search("da"));//false
        System.out.println(leet211sol2dictionary.search(".ad"));//true
        System.out.println(leet211sol2dictionary.search("b."));//false
        System.out.println(leet211sol2dictionary.search("b.d"));//true
        System.out.println(leet211sol2dictionary.search("..."));//true
        //Runtime: 1205 ms, faster than 47.60% of Java online submissions for Design Add and Search Words Data Structure.
        //Memory Usage: 224 MB, less than 77.73% of Java online submissions for Design Add and Search Words Data Structure.
    }
}
