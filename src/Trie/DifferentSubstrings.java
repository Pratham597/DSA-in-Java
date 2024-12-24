package Trie;

public class DifferentSubstrings {
    public static class TrieNode{
        TrieNode[] trie;
        boolean flag;

        public  TrieNode(){
            trie=new TrieNode[26];
            flag=false;
        }
        void setEnd() {
            this.flag=true;
        }
        boolean getEnd(){ return this.flag; }
    }
    public static  class Trie{
        private final TrieNode root;
        public Trie(){
            root=new TrieNode();
        }
        int  insert (String word){
            int count=0;
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
                if(!next.getEnd()){
                    count++;
                    next.setEnd();
                }
            }
            return count;
        }
    }
    public static int countDistinctSubstrings(String s)
    {
        Trie trie=new Trie();
        int n=s.length();
        int ans=0;
        for(int i=0;i<n;i++){
            String word=s.substring(i);
            ans+=trie.insert(word);
        }
        return ans+1;
    }
    public static void main(String[] args) {
        System.out.println(countDistinctSubstrings("aa"));
    }
}
