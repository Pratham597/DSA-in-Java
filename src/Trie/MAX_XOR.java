package Trie;

public class MAX_XOR {
    private static class TrieNode{
        TrieNode [] trie;

        public  TrieNode(){
            trie=new TrieNode[2];
        }
    }
    public static  class Trie{
        private final TrieNode root;
        public Trie(){
            root=new TrieNode();
        }
        void insert (int n){
            TrieNode next=root;
            for(int i=31;i>=0;i--){
                int ele=(1&(n>>i));
                if(next.trie[ele]==null){
                    next.trie[ele]=new TrieNode();
                }
                next=next.trie[ele];
            }
        }
        int maxXor(int n){
            TrieNode next=root;
            int ans=0;
            for(int i=31;i>=0;i--){
                int ele=(1&(n>>i));
                int opp=ele==1?0:1;
                if(next.trie[opp]!=null){
                    next=next.trie[opp];
                    ans+=(1<<i);
                }
                else next=next.trie[ele];
            }
            return ans;
        }
    }
    public int findMaximumXOR(int[] nums) {
        int max=Integer.MIN_VALUE;
        Trie trie=new Trie();
        for(int ele:nums) trie.insert(ele);
        for(int ele:nums) max=Math.max(max,trie.maxXor(ele));
        return max;
    }

    public static void main(String[] args) {
        System.out.println(214004^330218644);
        System.out.println(Integer.toBinaryString(214004));
        System.out.println(Integer.toBinaryString(330218644));
        System.out.println(Integer.toBinaryString(404207941));
    }
}
