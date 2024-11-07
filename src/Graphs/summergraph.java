package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class summergraph {
    static void helper(int node, int parent,List<List<Integer>> ans,ArrayList<ArrayList<Integer>> adj, boolean [] visit,List<Integer> trace){
        visit[node]=true;
        trace.add(node);
        for(Integer it:adj.get(node)){
            if(!visit[it]){
                helper(it,node,ans,adj,visit,trace);
            }
            else if(it!=parent){
                int idx=trace.lastIndexOf(it);
                if(idx!=-1){
                    ans.add(new ArrayList<>(trace.subList(idx,trace.size())));
                }
            }
        }
        trace.remove(trace.size()-1);
    }
    public  static int CheapestFLight(int n,int flights[][],int src,int dst,int k) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        // Inserting the arraylist into the adj.
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Creating the adjacency list.
        for (int i = 0; i < flights.length; i++) {
            int[] temp = flights[i];
            int root = temp[0];
            int rootadj = temp[1];
            int price = temp[2];

            adj.get(root).add(new int[]{rootadj, price});
        }
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[src] = 0;
        PriorityQueue<List<Integer>> q = new PriorityQueue<>((x, y) -> x.get(0) - y.get(0));
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        temp.add(src);
        temp.add(0);
        q.add(temp);
        while (!q.isEmpty()) {
            List<Integer> node = q.poll();
            int price = node.get(0);
            int nodeele = node.get(1);
            int step = node.get(2);

            for (int[] arr : adj.get(nodeele)) {
                int nodeadj = arr[0];
                int wt = arr[1];
                if (nodeadj != dst) {
                    if (wt + price < ans[nodeadj]) {
                        ans[nodeadj] = wt + price;
                        List<Integer> temp2 = new ArrayList<>();
                        temp2.add(wt + price);
                        temp2.add(nodeadj);
                        temp2.add(step + 1);
                        q.add(temp2);
                    }
                } else {
                    if (step <= k && wt + price < ans[nodeadj]) {
                        ans[nodeadj] = wt + price;
                        List<Integer> temp2 = new ArrayList<>();
                        temp2.add(wt + price);
                        temp2.add(nodeadj);
                        temp2.add(step + 1);
                        q.add(temp2);
                    }
                }
            }
        }
        if (ans[dst] == Integer.MAX_VALUE) return -1;
        return ans[dst];
    }
    static int [] dijkstra(int src,int n,ArrayList<ArrayList<int[]>> adj){
        int [] ans=new int[n];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[src]=0;
        PriorityQueue<List<Integer>> q=new PriorityQueue<>((x,y)->x.get(0)-y.get(0));
        List<Integer> temp=new ArrayList<>();
        temp.add(0);
        temp.add(src);
        q.add(temp);
        while(!q.isEmpty()){
            List<Integer> temp2=q.poll();
            int dist=temp2.get(0);
            int parent=temp2.get(1);
            for(int [] arr:adj.get(parent)){
                int node=arr[0];
                int wt=arr[1];
                if(dist+wt<ans[node]){
                    ans[node]=dist+wt;
                    List<Integer> temp3=new ArrayList<>();
                    temp3.add(dist+wt);
                    temp3.add(node);
                    q.add(temp3);
                }
            }

        }
        return ans;
    }
    static int noOfCity(int [] arr,int target,int src){
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(i!=src&&arr[i]<=target){
                count++;
            }
        }
        return count;
    }
    static int findCity(int n, int m, int[][] edges,int target)
    {
        //   creating the adjacency list.
        ArrayList<ArrayList<int[]>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int node=edges[i][0];
            int adjnode=edges[i][1];
            int wt=edges[i][2];
            adj.get(node).add(new int[]{adjnode,wt});
            adj.get(adjnode).add(new int []{node,wt});
        }
        // main program call.
        int freq=Integer.MAX_VALUE;
        int ans=n-1;
        for(int i=0;i<n;i++){
            int [] arr=dijkstra(i,n,adj);
            int count=noOfCity(arr,target,i);
            System.out.println(Arrays.toString(arr));
            if(count<freq){
                freq=count;
                ans=i;
            }
            else if(count==freq &&i>ans){
                ans=i;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int [][] edges={{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        int n=4;
        int m=4;
        int target=4;
        System.out.println(findCity(m,m,edges,target));

    }
}
