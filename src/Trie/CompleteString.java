package Trie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CompleteString {
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
        boolean insert (String word){
            // word=word.toLowerCase();
            int n=word.length();
            TrieNode next=root;
            boolean flag=true;
            for(int i=0;i<n;i++){
                char c=word.charAt(i);
                if(next.trie[c-97]!=null){
                    next=next.trie[c-97];
                    if(!next.getEnd()) flag=false;
                }
                else{
                    TrieNode temp=new TrieNode();
                    next.trie[c-97]=temp;
                    next=temp;
                    if(i!=n-1) flag=false;
                }
            }
            next.setEnd();
            return flag;
        }
    }

    public static String completeString(int n, String[] a) {
        Arrays.sort(a, String::compareTo);
        Trie trie=new Trie();
        String s="";
        for(String t:a){
            boolean flag=trie.insert(t);
            if(flag){
                if(t.length()>s.length()) s=t;
                else if(t.length()==s.length() && s.compareTo(t)>0){
                    s=t;
                }
            }
        }
        if(s.isEmpty()) return "None";
        else return s;
    }
}
