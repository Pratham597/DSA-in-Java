package Trie;

public class ImplementII {
    public static class TrieNode{
        TrieNode [] trie;
        boolean flag;
        int count;

        public TrieNode(){
            this.trie=new TrieNode[26];
            this.flag=false;
            this.count=1;
        }
        void setEnd() {
            this.flag=true;
        }
        boolean getEnd(){return this.flag;}
    }
    public static class Trie {
        private final TrieNode root;
        public Trie() {
            root=new TrieNode();
        }

        public void insert(String word) {
            int n=word.length();
            TrieNode next=root;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(next.trie[c-97]!=null){
                    next=next.trie[c-97];
                    next.count+=1;
                }
                else{
                    TrieNode temp=new TrieNode();
                    next.trie[c-97]=temp;
                    next=temp;
                }
            }
            next.setEnd();
        }

        public int countWordsEqualTo(String word) {
            int n=word.length();
            TrieNode next=root;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(next.trie[c-97]!=null){
                    next=next.trie[c-97];
                }
                else{
                    return 0;
                }
            }
            if(next.getEnd()) return next.count;
            else return 0;
        }

        public int countWordsStartingWith(String word) {
            int n=word.length();
            TrieNode next=root;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(next.trie[c-97]!=null){
                    next=next.trie[c-97];
                }
                else{
                    return 0;
                }
            }
            return next.count;
        }

        public void erase(String word) {
            int n = word.length();
            TrieNode next = root;
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (next.trie[c - 97] != null) {
                    next = next.trie[c - 97];
                    next.count -= 1;
                }
            }
            next.flag=false;
        }

    }
    public static void main(String[] args) {

    }
}
