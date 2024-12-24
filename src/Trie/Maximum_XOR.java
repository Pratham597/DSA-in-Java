package Trie;

import java.util.Arrays;

public class Maximum_XOR {
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
                int opp=1-ele;
                if(next.trie[opp]!=null){
                    next=next.trie[opp];
                    ans+=(1<<i);
                }
                else if(next.trie[ele]!=null){
                    next=next.trie[ele];
                }
                else return -1;
            }
            return ans;
        }
    }
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int [][] q=new int[queries.length][3];
        for(int i=0;i<queries.length;i++){
            q[i][0]=queries[i][0];
            q[i][1]=queries[i][1];
            q[i][2]=i;
        }
        Arrays.sort(q,(x, y)->Integer.compare(x[1],y[1]));
        Arrays.sort(nums);
        int idx=0; //nums idx
        Trie trie=new Trie();
        int [] res=new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int qidx=q[i][2];
            int qmax=q[i][1];

            while(idx<nums.length && nums[idx]<=qmax){
                trie.insert(nums[idx++]);
            }
            res[qidx]=trie.maxXor(q[i][0]);
        }
        return res;
    }
}
