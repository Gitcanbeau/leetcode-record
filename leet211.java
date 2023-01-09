import java.util.HashMap;
import java.util.Map;

public class leet211 {
    private static class TrieNode{
        public char ch;
        Map<Character, TrieNode> children;
        public boolean isEnd;
        public TrieNode(char ch){
            this.ch = ch;
            this.children = new HashMap<>();
            this.isEnd = false;
        }
    }

//    private final TrieNode root;
    private static TrieNode root;
    public leet211() {
        TrieNode root=new TrieNode('*');
        this.root = root;
    }

    public void addWord(String word) {
//        TrieNode curr = this.root;
        TrieNode curr = root; //动态方法可以使用动静态成员
        for(char ch : word.toCharArray()){
            curr.children.putIfAbsent(ch, new TrieNode(ch));
            curr = curr.children.get(ch);
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        return hasWord(root, word, 0);
    }
    private boolean hasWord(TrieNode root, String word, int idx){
        for(int i=idx; i<word.length(); ++i){
            char wordcurr = word.charAt(i);
            if(wordcurr == '.'){
                for(TrieNode child : root.children.values()){
                    if(hasWord(child, word, i+1)) return true;
                }
                return false;
            }else if(root.children.containsKey(wordcurr)){
                root = root.children.get(wordcurr);
            }else{
                return false;
            }
        }
        return root.isEnd;
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
        leet211 leet211dictionary=new leet211();
        leet211dictionary.addWord("bad");
//        leet211dictionary.addWord("dad");
//        leet211dictionary.addWord("mad");
//        leet211dictionary.addWord("pad");
//        leet211dictionary.addWord("bad");
//        System.out.println(leet211dictionary.search("bad"));//true
//        System.out.println(leet211dictionary.search("dad"));//true
//        System.out.println(leet211dictionary.search("da"));//false
//        System.out.println(leet211dictionary.search(".ad"));//true
//        System.out.println(leet211dictionary.search("b."));//false
        System.out.println(leet211dictionary.search("b.d"));//true
        System.out.println(leet211dictionary.search("..."));//true
        //time limit exceeded
    }
}

