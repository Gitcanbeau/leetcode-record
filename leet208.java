public class leet208 {
    private static class TrieNode{
        TrieNode[] children=new TrieNode[26];
        public boolean isEndofWord;//这里如果写 public static boolean就会把boolean默认值true共享给所有变量，总之不能static
        public TrieNode(){
            isEndofWord=false;
            for(int i=0; i<26;i++){
                children[i]=null;
            }
        }
    }

    public static TrieNode root;
    public leet208() {
        TrieNode root=new TrieNode();
        //新建leet208对象就需要新建TrieNode root，然后外面也要有成员变量 root，不然别的方法里面没法用，最后还要this.root联系起来，不然别人不知道
        this.root=root;
    }

    public static void insert(String word) {
        int index;
        TrieNode pcrawl=root;
        for(int level=0; level<word.length();level++){
            index=word.charAt(level)-'a';
            if(pcrawl.children[index]==null) {
                pcrawl.children[index] = new TrieNode();
            }
            pcrawl=pcrawl.children[index];
        }
        pcrawl.isEndofWord=true;//mark last node as leaf
    }

    public static boolean search(String word) {
        int index;
        TrieNode pcrawl=root;
        for(int level=0; level<word.length();level++){
            index=word.charAt(level)-'a';
            if(pcrawl.children[index]==null) return false;
            pcrawl=pcrawl.children[index];
        }
        return pcrawl.isEndofWord;
    }

    public static boolean startsWith(String prefix) {
        int index;
        TrieNode pcrawl=root;
        for(int level=0; level<prefix.length();level++){
            index=prefix.charAt(level)-'a';
            if(pcrawl.children[index]==null) return false;
            pcrawl=pcrawl.children[index];
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
        leet208 trie208=new leet208();
        trie208.insert("apple");
        System.out.println(trie208.search("apple"));
        System.out.println(trie208.search("app"));
        System.out.println(trie208.startsWith("apple"));
        System.out.println(trie208.startsWith("app"));
        //Runtime: 95 ms, faster than 18.42% of Java online submissions for Implement Trie (Prefix Tree).
        //Memory Usage: 69.1 MB, less than 33.95% of Java online submissions for Implement Trie (Prefix Tree).
    }
}
