package Graphs;

import java.util.*;

public class problem2 {
    public static void helper(int [][] grid, boolean[][] visit, int row, int col, int [] delrow, int [] delcol, List<List<Integer>> arr){
        int n=grid.length;
        int m=grid[0].length;
        visit[row][col]=true;
        int [] island=new int[]{row,col};
        List<Integer> temp=new ArrayList<>();
        temp.add(row);
        temp.add(col);
        arr.add(temp);
        for(int i=0;i<4;i++){
            int nrow=delrow[i]+row;
            int ncol=delcol[i]+col;
            if(nrow>=0&&nrow<n&&ncol>=0&&ncol<m&&!visit[nrow][ncol]&&grid[nrow][ncol]==1){
                helper(grid,visit,nrow,ncol,delrow,delcol,arr);
            }
        }
    }
    public static  void refine(List<List<Integer>> check, HashSet<List<List<Integer>>> ans){
        int minrow=Integer.MAX_VALUE;
        int mincol=Integer.MAX_VALUE;
        for(List<Integer> x:check){
            minrow= Math.min(minrow,x.get(0));
            mincol=Math.min(mincol,x.get(1));
        }
        for(List<Integer> x:check){
            x.set(0,x.get(0)-minrow);
            x.set(1,x.get(1)-mincol);
        }
        ans.add(check);
    }
    public static int distinctIsland(int [][] arr){
        int n=arr.length;
        int m=arr[0].length;
        HashSet<List<List<Integer>>> hs=new HashSet<>();
        boolean[][]visit=new boolean[n][m];
        int [] delrow={-1,0,1,0};
        int [] delcol={ 0,1,0,-1};
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j]==1&&!visit[i][j]) {
                    List<List<Integer>>ans= new ArrayList<>();
                    helper(arr,visit,i,j,delrow,delcol,ans);
                    refine(ans,hs);
                }
            }
        }
        return hs.size();
    }
    public static boolean bipartiteGraph(int V,ArrayList<ArrayList<Integer>> arr){
        int [] color=new int[V];
        for(int i=0;i<V;i++){
            if(color[i]==0&& !arr.get(i).isEmpty()){
                if(!helperBipartite(arr,color,i,1)) return false;
            }
        }
        return  true;
    }
    public static boolean bipartiteGraphUsingbfs(int V,ArrayList<ArrayList<Integer>> arr,int idx,int [] color){
           Queue<int[]> q=new LinkedList<>();
           int [] ans=new int[]{idx,1};
           color[idx]=1;
           q.add(ans);
           while (!q.isEmpty()){
               int [] temp=q.poll();
               int ele=temp[0];
               int col=temp[1];
               for(Integer node:arr.get(ele)){
                   if(color[node]==0){
                       int rcol=(col==1)?-1:1;
                       color[node]=rcol;
                       q.add(new int[]{node,rcol});
                   }
                   else if(color[node]==col) return  false;
               }
           }
           return true;
    }
    public static boolean helperBipartite(ArrayList<ArrayList<Integer>> arr,int [] color,int idx,int col) {
        color[idx]=col;
        for(Integer x:arr.get(idx)){
            if(color[x]==0){
                int rcol=(col==1)?-1:1;
                if(!helperBipartite(arr,color,x,rcol)) return false;
            }
            else if(color[x]==col) return false;
        }
        return true;
    }
    //Wap to find the cycle in directed graph
    public boolean helperCyclic(ArrayList<ArrayList<Integer>> arr,boolean [] visit,int node,boolean [] check){
        visit[node]=true;
        check[node]=true;
        for(Integer x:arr.get(node)){
            if(!visit[x]){
                if(helperCyclic(arr,visit,x,check)) return true;
            }
            else if(check[x]) return true;
        }
        check[node]=false;
        return false;
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> arr) {
        boolean [] visit=new boolean[V];
        boolean [] check=new boolean[V];
        for(int i=0;i<V;i++){
            if(!visit[i]){
                if(helperCyclic(arr,visit,i,check)) return true;
            }
        }
        return false;
    }
    static void helperTopo(ArrayList<ArrayList<Integer>> arr,boolean [] visit,
                       ArrayList<Integer> ans,int node){

        visit[node]=true;
        for(Integer x:arr.get(node)){
            if(!visit[x]){
                helperTopo(arr,visit,ans,x);
            }
        }
        ans.add(0,node);
    }
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> arr)
    {
        int [] ret=new int[V];
        ArrayList<Integer>ans=new ArrayList<>();
        boolean [] visit=new boolean[V];
        for(int i=0;i<V;i++){
            if(!visit[i]){
                helperTopo(arr,visit,ans,i);
            }
        }
        for(int i=0;i<V;i++){
            ret[i]=ans.get(i);
        }
        return ret;
    }
    static void helper(int node,ArrayList<ArrayList<Integer>> arr,boolean [] visit,Queue<Integer> q){
        Queue<Integer> qh=new LinkedList<>();
        qh.add(node);
        visit[node]=true;
        while(!qh.isEmpty()){
            int ele=qh.poll();
            q.add(ele);
            for(Integer x:arr.get(ele)){
                if(!visit[x]){
                    qh.add(x);
                    visit[x]=true;
                }
            }
        }
    }
    static int[] topoSortBfs(int V, ArrayList<ArrayList<Integer>> arr)
    {
        Queue<Integer> ans=new LinkedList<>();
        boolean [] visit=new boolean[V];
        for(int i=0;i<V;i++){
            if(!visit[i]){
                helper(i,arr,visit,ans);
            }
        }
        int [] ret=new int[V];
        for(int i=0;i<V;i++){
            ret[i]=ans.poll();
        }
        return ret;
    }
    public static void dfs(char node,HashSet<Character> hs,HashMap<Character,
            HashSet<Character>> mp,Stack<Character> st){
        //set act as visited array.
        hs.add(node);
        for(Character c:mp.get(node)){
            if(!hs.contains(c)){
                dfs(c,hs,mp,st);
            }
        }
        st.add(node);
    }
    public static String findOrder(String [] arr, int N, int K)
    {
        Stack<Character> st=new Stack<>();
        HashSet<Character> hs=new HashSet<>();
        HashMap<Character,HashSet<Character>> mp=new HashMap<>();
        for(int i=0;i<K;i++){
            char c=(char)('a'+i);
            mp.put(c,new HashSet<>());
        }
        for(int i=0;i<arr.length-1;i++){
            int idx1=0;
            int idx2=0;
            String s1=arr[i];
            String s2=arr[i+1];
            char c=s1.charAt(idx1);
            char d=s2.charAt(idx2);
            while(c==d&&idx1<s1.length()&&idx2<s2.length()){
                c=s1.charAt(idx1++);
                d=s2.charAt(idx2++);
            }
            if(c!=d){
                mp.get(c).add(d);
            }
        }
        for(int i=0;i<K;i++){
            char c=(char)('a'+i);
            if(!hs.contains(c)){
                dfs(c,hs,mp,st);
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.toString();
    }
    public static void dfs(int node, int path,int[] ans,
                    ArrayList<ArrayList<int[]>> arr){

        ans[node]=path;
        for(int[] nodepath:arr.get(node)){
            //nodepath-> arr of containing next element
            int ele=nodepath[0];
            int pathnode=nodepath[1];
            if(ans[ele]==-1||ans[ele]>(path+pathnode)){
                dfs(ele,path+pathnode,ans,arr);
            }
        }
    }
    public static int[] shortestPath(int N,int M, int[][] edges) {
        int [] ans=new int[N];
        Arrays.fill(ans,-1);
        ArrayList<ArrayList<int[]>> arr=new ArrayList<>();
        for(int i=0;i<N;i++){
            ArrayList<int[]> temp=new ArrayList<>();
            arr.add(temp);
        }
        for(int i=0;i<M;i++){
            int root=edges[i][0];
            int node=edges[i][1];
            int distance=edges[i][2];
            int [] nodepath=new int[2];
            nodepath[0]=node;
            nodepath[1]=distance;
            arr.get(root).add(nodepath);
        }
        dfs(0,0,ans,arr);
        return ans;
    }
    public static int[] shortestPath(int[][] edges,int n,int m ,int src) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        int [] ans=new int[n];
        Arrays.fill(ans,-1);
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int it1=edges[i][0];
            int it2=edges[i][1];
            adj.get(it1).add(it2);
            adj.get(it2).add(it1);
        }
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        ans[src]=0;
        while(!q.isEmpty()){
            int node=q.poll();
            for(int it:adj.get(node)){
                if(ans[it]==-1){
                    ans[it]=ans[node]+1;
                    q.add(it);
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
//        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
//        arr.add(new ArrayList<>());
//        arr.add(new ArrayList<>());
//        arr.add(new ArrayList<>());
//        arr.add(new ArrayList<>());
//        arr.add(new ArrayList<>());
//        arr.add(new ArrayList<>());
//        arr.get(0).add(1);
//        arr.get(0).add(2);
//        arr.get(1).add(3);
//        arr.get(1).add(4);
//        arr.get(2).add(1);
//        arr.get(2).add(5);
//        System.out.println(Arrays.toString(topoSortBfs(6,arr)));
        String[] arr={"baa","abcd","abca","cab","cada"};
        System.out.println(findOrder(arr,5,4));

    }

}
