package Graphs;
import java.util.*;

public class poblem3 {
    public static ArrayList<String> helper(String word,String[] wordList,int idx){
        ArrayList<String> ans=new ArrayList<>();
        for(int i=0;i<wordList.length;i++){
            if(idx==i) continue;
            //comparison

            String s1=wordList[i];
            int i1=0,j1=s1.length()-1;
            //loopings for checking purpose.
            while(s1.charAt(i1)==word.charAt(i1)){
                i1++;
            }
            while(s1.charAt(j1)==word.charAt(j1)){
                j1--;
            }
            if(j1-i1==0){
                ans.add(wordList[i]);
            }
        }
        return ans;
    }
    public static int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        boolean flag=false;
        //checking the targetword in list.
        for(int i=0;i<wordList.length;i++){
            if(wordList[i].equals(targetWord)){
                flag=true;
                break;
            }
        }
        if(!flag) return 0;
        HashMap<String,ArrayList<String>> mp=new HashMap<>();
        for(int i=0;i<wordList.length;i++ ){
            mp.put(wordList[i],helper(wordList[i],wordList,i));
        }
        if(!mp.containsKey(startWord)){
            mp.put(startWord,helper(startWord,wordList,-1));
        }
        Queue<String> q=new LinkedList<>();
        q.add(startWord);
        HashSet<String> hs=new HashSet<>();
        hs.add(startWord);
        int ans=1;
        System.out.println(mp);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                String s=q.poll();
                for(String s1:mp.get(s)){
                    if(!hs.contains(s1)){
                        q.add(s1);
                        hs.add(s1);
                    }
                }
            }
            ans++;
            if(hs.contains(targetWord)) return ans;
        }
        return 0;
    }
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int src) {
        int[] ans = new int[V];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[src] = 0;
        PriorityQueue<List<Integer>> q = new PriorityQueue<>((x,y)->x.get(0)-y.get(0));
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        temp.add(1);
        q.add(temp);
        while (!q.isEmpty()) {
            List<Integer> arr = q.poll();
            int node = arr.get(0);
            int wt = arr.get(1);
            for (ArrayList<Integer> al : adj.get(node)) {
                int nodeele = al.get(0);
                int nodewt = al.get(1);
                if (nodewt + wt < ans[nodeele]) {
                    ans[nodeele] = nodewt + wt;
                    List<Integer> nodetemp = new ArrayList<>();
                    nodetemp.add(nodewt + wt);
                    nodetemp.add(nodeele);
                    q.add(nodetemp);
                }
            }
        }
        return ans;
    }
    static int spanningTree(int V, int E, int edges[][]){
        ArrayList<ArrayList<int[]>> adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<int[]>());
        }
        for(int i=0;i<E;i++){
            int root=edges[i][0];
            int node=edges[i][1];
            int wt=edges[i][2];
            int[] nodearr=new int[]{node,wt};
            int [] arrnode=new int[]{root,wt};
            adj.get(root).add(nodearr);
            adj.get(node).add(arrnode);
        }
        int [] ans=new int[V];
        List<Integer> temp=new ArrayList<>();
        temp.add(0);
        temp.add(0);
        PriorityQueue<List<Integer>> q=new PriorityQueue<>((x,y)->x.get(0)-y.get(0));
        q.add(temp);
        int sum=0;
        boolean[] visit=new boolean[V];
        while(!q.isEmpty()){
            List<Integer> nodeparent=q.poll();
            int root=nodeparent.get(1);
            int wt=nodeparent.get(0);
            if(visit[root]) continue;
            visit[root]=true;
            sum+=wt;
            for(int [] it:adj.get(root)){
                int adjnode=it[0];
                int adjwt=it[1];
                if(!visit[adjnode]){
                    ans[adjnode]=adjwt;
                    List<Integer> qnode=new ArrayList<>();
                    qnode.add(adjwt);
                    qnode.add(adjnode);
                    q.add(qnode);
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int [][] edges={{0,1,3},{1,3,3},{1,5,10},{2,4,6},{2,6,9},{3,6,8},{4,5,6}};
        System.out.println(spanningTree(7,7,edges));

    }
}
