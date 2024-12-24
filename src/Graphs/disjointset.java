package Graphs;

import java.util.*;

public class disjointset {
    public static class DisjointSet{
        List<Integer> parent;
        List<Integer> rank;
        List<Integer> size;
        public DisjointSet(int n){
            parent=new ArrayList<>();
            rank=new ArrayList<>();
            size=new ArrayList<>();
            for(int i=0;i<n;i++){
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }
        public int findParent(int node){
            if(parent.get(node)==node) return node;
            parent.set(node,findParent(parent.get(node)));
            return parent.get(node);
        }
        public void unionByRank(int u,int v){
            int su=findParent(u);
            int sv=findParent(v);
            if(su==sv) return;
            if(rank.get(su)<rank.get(sv)){
                parent.set(su,sv);
            }
            else if(rank.get(sv)<rank.get(su)){
                parent.set(sv,su);
            }
            else{
                parent.set(sv,su);
                rank.set(su,rank.get(su)+1);
            }
        }
        public void unionBySize(int u,int v){
            int su=findParent(u);
            int sv=findParent(v);
            if(su==sv) return;
            if(size.get(su)<size.get(sv)){
                // su-> Parent is sv.
                parent.set(su,sv);
                size.set(sv,size.get(sv)+size.get(su));
            }
            else{
                //sv-Parent is su.
                parent.set(sv,su);
                size.set(su,size.get(sv)+size.get(su));
            }
        }
    }
    static int maxRemove(int[][] stones, int stone_n) {

        int maxrow=Integer.MIN_VALUE;
        int maxcol=Integer.MIN_VALUE;
        HashSet<Integer> rows=new HashSet<>();
        HashSet<Integer> cols=new HashSet<>();
        for(int i=0;i<stone_n;i++){
            int row=stones[i][0];
            int col=stones[i][1];
            rows.add(row);
            cols.add(col);
            maxrow=Math.max(row,maxrow);
            maxcol=Math.max(col,maxcol);
        }
        int n=maxrow+2;
        int m=maxcol+2;
        DisjointSet ds=new DisjointSet(n*m);
        for(int i=0;i<stone_n;i++){
            int row=stones[i][0]+1;
            int col=stones[i][1]+1;
            int ele=row*m+col;
            int u=row*m;
            int v=col;
            ds.unionBySize(ele,u);
            ds.unionBySize(ele,v);
        }
        int size=0;
        for(int i=0;i<n*m;i++){
            if(ds.findParent(i)==i){
                size+=ds.size.get(i)-1;

            }
        }
        return size-cols.size()-rows.size();

    }
    public static int countComponents(int[] nums, int k) {
        HashMap<Integer,Integer> mp=new HashMap<>();
        DisjointSet ds=new DisjointSet(nums.length);
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            mp.put(nums[i],i);
        }
        boolean [] visit=new boolean[nums.length];
        int [] map=new int[k+1];
        Arrays.fill(map,-1);
        for(int i=0;i<nums.length;i++){
            int st=2;
            int ele=nums[i];
            if(visit[i]) continue;
            while(ele*st<=k){
                int multiple=ele*st;
                if(mp.containsKey(multiple)){
                    int idx=mp.get(multiple);
                    ds.unionBySize(i,idx);
                    visit[i]=true;
                }
                else{
                    if(map[multiple]==-1) map[multiple]=i;
                    else{
                        ds.unionBySize(i,map[multiple]);
                    }
                }
                st++;
            }
        }
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(ds.findParent(i)==i){
                count++;
            }
        }
        System.out.println(ds.parent);
        return count;

    }
    public static  int f(String s){
        int n=s.length();
        int count=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int test=sc.nextInt();
        for(int k=0;k<test;k++){
            int n=sc.nextInt();
            int l=sc.nextInt();
            int r=sc.nextInt();

            int [] arr=new int[n];

            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            
        }
    }
}
