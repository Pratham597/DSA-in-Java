package Trie;

public class basicImplement {
    public static class TrieNode{
        TrieNode [] trie;
        boolean flag;

        public  TrieNode(){
            trie=new TrieNode[26];
            flag=false;
        }
        void setEnd() {
            this.flag=true;
        }
        boolean getEnd(){return this.flag;}
    }
    public static  class Trie{
        private final TrieNode root;
        public Trie(){
            root=new TrieNode();
        }
        void insert (String word){
            word=word.toLowerCase();
            int n=word.length();
            TrieNode next=root;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(next.trie[c-97]!=null){
                    next=next.trie[c-97];
                }
                else{
                    TrieNode temp=new TrieNode();
                    next.trie[c-97]=temp;
                    next=temp;
                }
            }
            next.setEnd();
        }

        boolean search(String word){
            word=word.toLowerCase();
            int n=word.length();
            TrieNode temp=root;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(temp.trie[c-97]==null) return false;
                else{
                    temp=temp.trie[c-97];
                }
            }
            return temp.getEnd();

        }
        boolean startsWith(String word){
            word=word.toLowerCase();
            int n=word.length();
            TrieNode temp=root;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(temp.trie[c-97]==null) return false;
                else{
                    temp=temp.trie[c-97];
                }
            }
            return true;
        }
    }
    public static void main(String[] args) {
        Trie trie=new Trie();
        trie.insert("Pratham");
        System.out.println(trie.startsWith("rat"));
    }
}
