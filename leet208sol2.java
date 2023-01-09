import java.util.HashMap;
import java.util.Map;

public class leet208sol2 {
    private static class TrieNode{
        public char ch;
        public Map<Character,TrieNode> children;
        public boolean iswordEnd;//这里的boolean不能static，否则所有变量都共享默认值true
        public TrieNode(char ch){
            this.ch=ch;
            this.children=new HashMap<>();
            this.iswordEnd=false;
        }
    }

    private static TrieNode root;
    public leet208sol2(){
        TrieNode root=new TrieNode('*');
        this.root = root;
    }

    public static void insert(String word) {
        TrieNode curr=root; //静态方法只能使用静态变量
//        TrieNode curr=this.root; //只有动态方法才能使用this.
        for(int level=0; level<word.length();level++){
            if(!curr.children.containsKey(word.charAt(level))){
                curr.children.put(word.charAt(level),new TrieNode(word.charAt(level)));
            }
            curr=curr.children.get(word.charAt(level));
        }
        curr.iswordEnd=true;
    }
    public static boolean search(String word) {
        TrieNode curr=root;
        for(int level=0; level<word.length();level++){
            if(curr.children.containsKey(word.charAt(level))){
                curr=curr.children.get(word.charAt(level));
            }else{
                return false;
            }
        }
        return curr.iswordEnd;
    }
    public static boolean startsWith(String prefix) {
        TrieNode curr=root;
        for(int level=0; level<prefix.length();level++){
            if(curr.children.containsKey(prefix.charAt(level))){
                curr=curr.children.get(prefix.charAt(level));
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
        //Implement the Trie class:
        //Trie() Initializes the trie object.
        //void insert(String word) Inserts the string word into the trie.
        //boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
        //boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
        //Example 1:
        //Input
        //["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
        //[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
        //Output
        //[null, null, true, false, true, null, true]
        //Explanation
        //Trie trie = new Trie();
        //trie.insert("apple");
        //trie.search("apple");   // return True
        //trie.search("app");     // return False
        //trie.startsWith("app"); // return True
        //trie.insert("app");
        //trie.search("app");     // return True
        //Constraints:
        //1 <= word.length, prefix.length <= 2000
        //word and prefix consist only of lowercase English letters.
        //At most 3 * 104 calls in total will be made to insert, search, and startsWith.
        String[] words = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        String[] output = {"Not present in trie", "Present in trie"};
        leet208sol2 trie208sol2=new leet208sol2();
        trie208sol2.insert("apple");
        System.out.println(trie208sol2.search("apple"));
        System.out.println(trie208sol2.search("app"));
        System.out.println(trie208sol2.startsWith("apple"));
        System.out.println(trie208sol2.startsWith("app"));
        trie208sol2.insert("app");
        System.out.println(trie208sol2.search("app"));
        //Runtime: 111 ms, faster than 9.67% of Java online submissions for Implement Trie (Prefix Tree).
        //Memory Usage: 70.7 MB, less than 17.21% of Java online submissions for Implement Trie (Prefix Tree).
    }

}
