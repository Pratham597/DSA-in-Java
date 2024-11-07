package Trees;

import java.util.Scanner;
import java.util.*;

public class revision {
    public static class TreeNode{
        int val;
        TreeNode left=null;
        TreeNode right=null;
        public TreeNode(int val){
            this.val=val;
        }
    }
    public static class Tree{
        TreeNode root=null;
        public void add(){
            Scanner sc=new Scanner(System.in);
            if(root==null){
                System.out.print("Enter the value of root");
                TreeNode temp=new TreeNode(sc.nextInt());
                root=temp;
            }
            addhelper(root,sc);
        }
        private void addhelper(TreeNode root,Scanner sc){
            System.out.print("Want to go left of "+root.val+" :");
            boolean left=sc.nextBoolean();
            if(left){
                System.out.print("Enter the value of TreeNode :");
                root.left=new TreeNode(sc.nextInt());
                addhelper(root.left,sc);
            }
            System.out.print("Want to go right of "+root.val+" :");
            boolean right=sc.nextBoolean();
            if(right){
                System.out.print("Enter the value of TreeNode:");
                root.right=new TreeNode(sc.nextInt());
                addhelper(root.right,sc);
            }
        }
        private static  TreeNode inserthelper(TreeNode root,int val){
            if(root==null){
                TreeNode temp=new TreeNode(val);
                return temp;
            }
            if(val<root.val) root.left=inserthelper(root.left,val);
            else root.right=inserthelper(root.right,val);
            return root;
        }
        public void insert(int val){
            root=inserthelper(root,val);
        }
        private void displayhelper(TreeNode root){
            if(root==null) return;
            System.out.print(root.val+" ");
            displayhelper(root.left);
            displayhelper(root.right);
        }
        public void display(){
            displayhelper(root);
            System.out.println();
        }
    }
    static void preorder(TreeNode root){
        if(root==null) return;
        System.out.print(root.val+" ");
        preorder(root.left);
        preorder(root.right);
    }
    static void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    static void bfs(TreeNode root){
        if(root==null) return;
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode temp=q.poll();
                if(temp.left!=null)q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
                System.out.print(temp.val+" ");
            }
            System.out.println();
        }
    }
    static void postorder(TreeNode root){
        if(root==null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val+" ");
    }
    static void preorderUsingStacks(TreeNode root){
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            //maintain order what it is saying.
            TreeNode temp=st.pop();
            System.out.print(temp.val+" ");
            if(temp.right!=null) st.push(temp.right);
            if(temp.left!=null) st.push(temp.left);
        }
        System.out.println();
    }
    static void inorderUsingStacks(TreeNode root){
        Stack<TreeNode> st=new Stack<>();
        TreeNode temp=root;
        while(true){
            //left ko maintain krwaya
            if(temp!=null){
                st.push(temp);
                temp=temp.left;
            }
            else{
                if(st.isEmpty()) break;
                temp=st.pop();
                //root ko dekha
                System.out.print(temp.val+" ");
                //right ko inorder me leke aao.
                temp=temp.right;
            }
        }
    }
    static void postorderUsingStack(TreeNode root){
        Stack<TreeNode> st=new Stack<>();
        TreeNode temp=root;
        while(true){
            if(temp!=null){
                st.push(temp);
                temp=temp.left;
            }
            else if(!st.isEmpty()&&st.peek().right!=null){
                temp=st.peek().right;
            }
            else{
                TreeNode store=st.pop();
                System.out.print(store.val+" ");
                while(!st.isEmpty()&&st.peek().right==store){
                    store=st.pop();
                    System.out.print(store.val+" ");
                }
                if(st.isEmpty()) break;
                temp=st.peek().right;
            }
        }
    }
    static int height(TreeNode root){
        if(root==null) return 0;
        int left=height(root.left);
        int right=height(root.right);
        return Math.max(left,right)+1;
    }
    static int ans=Integer.MIN_VALUE;
    static int helper(TreeNode root){
        if(root==null) {
            ans=0;
            return 0;
        }
        int left=helper(root.left);
        int right=helper(root.right);
        int diameter=left+right+1;
        ans=Math.max(ans,diameter);
        return Math.max(left,right)+1;
    }
    static void helperBoundary(TreeNode root,ArrayList<Integer> arr){
        if(root==null) return;
        if(root.left==null&&root.right==null) arr.add(root.val);
        helperBoundary(root.left,arr);
        helperBoundary(root.right,arr);

    }
    static ArrayList<Integer> border(TreeNode root){
        ArrayList<Integer> arr=new ArrayList<>();
        if(root==null) return arr;
        arr.add(root.val);
        if(root.left==null&&root.right==null) return arr;
        TreeNode temp=root.left;
        while(temp!=null){
            if(temp.left!=null) {
                arr.add(temp.val);
                temp=temp.left;
            }
            else if(temp.right!=null) {
                arr.add(temp.val);
                temp=temp.right;
            }
            else break;
        }
        helperBoundary(root,arr);
        Stack<Integer> st=new Stack<>();
        temp=root.right;
        while(temp!=null){
            if(temp.right!=null){
                st.push(temp.val);
                temp=temp.right;
            }
            else if(temp.left!=null){
                st.push(temp.val);
                temp=temp.left;
            }
            else break;
        }
        while(!st.isEmpty()){
            arr.add(st.pop());
        }
        return arr;

    }
    public static class tuple{
        TreeNode node;
        int vertical;
        int level;
        public tuple(TreeNode node,int v,int l){
            this.node=node;
            this.vertical=v;
            this.level=l;
        }
    }
    static void verticalOrderTraveersal(TreeNode root){
        Queue<tuple> q=new LinkedList<>();
        q.add(new tuple(root,0,0));
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> mp=new TreeMap<>();
        while(!q.isEmpty()){
            tuple t=q.poll();
            TreeNode temp=t.node;
            int y=t.level;
            int x=t.vertical;
            if(!mp.containsKey(x)){
                mp.put(x,new TreeMap<>());
            }
            if(!mp.get(x).containsKey(y)){
                mp.get(x).put(y,new PriorityQueue<>());
            }
            mp.get(x).get(y).add(temp.val);
            if(temp.left!=null){
                q.add(new tuple(temp.left,x-1,y+1));
            }
            if(temp.right!=null){
                q.add(new tuple(temp.right,x+1,y+1));
            }
        }
        List<List<Integer>> list=new ArrayList<>();
        for(TreeMap<Integer,PriorityQueue<Integer>> ys:mp.values()){
            List<Integer> temp=new ArrayList<>();
            for(PriorityQueue<Integer> nodes :ys.values()){
                while(!nodes.isEmpty()){
                    temp.add(nodes.poll());
                }
            }
            list.add(temp);
        }
    }
    public static class tuple1{
        TreeNode node;
        int vertical;
        public tuple1(TreeNode node,int vertical){
            this.node=node;
            this.vertical=vertical;
        }
    }
    static void topView(TreeNode root,TreeMap<Integer,Integer> mp){
        if(root==null) return;
        Queue<tuple1> q=new LinkedList<>();
        q.add(new tuple1(root,0));
        while(!q.isEmpty()){
            tuple1 t=q.poll();
            int x=t.vertical;
            TreeNode temp=t.node;
            if(!mp.containsKey(x)) mp.put(x,temp.val);
            if(temp.left!=null) q.add(new tuple1(temp.left,x-1));
            if(temp.right!=null)q.add(new tuple1(temp.right,x+1));
        }
    }
    static void downView(TreeNode root,TreeMap<Integer,Integer> mp){
        if(root==null) return;
        Queue<tuple1> q=new LinkedList<>();
        q.add(new tuple1(root,0));
        while(!q.isEmpty()){
            tuple1 t=q.poll();
            int x=t.vertical;
            TreeNode temp=t.node;
            mp.put(x,temp.val);
            if(temp.left!=null) q.add(new tuple1(temp.left,x-1));
            if(temp.right!=null)q.add(new tuple1(temp.right,x+1));
        }
    }
    static boolean printPath(TreeNode root,ArrayList<Integer> arr,int val){
        if(root==null) return false;
        arr.add(root.val);
        if(root.val==val) return true;
        if(printPath(root.left,arr,val)) return true;
        if(printPath(root.right,arr,val)) return true;
        arr.remove(arr.size()-1);
        return false;
    }
    static void leftView(TreeNode root){
        Queue<TreeNode> q=new LinkedList<>();
        ArrayList<Integer> arr=new ArrayList<>();
        if(root==null) return;
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode temp=q.poll();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                if(i==0) arr.add(temp.val);
            }
        }
        for(Integer x:arr){
            System.out.print(x+" ");
        }

    }
    static void helperKNode(HashMap<TreeNode,TreeNode> mp,TreeNode root,TreeNode parent){
        if(root==null) return;
        mp.put(root,parent);
        helperKNode(mp,root.left,root);
        helperKNode(mp,root.right,root);
    }
    static TreeNode findNode(TreeNode root,int val){
        if(root==null) return null;
        if(root.val==val) return root;
        TreeNode left=findNode(root.left,val);
        return (left!=null)?left:findNode(root.right,val);
    }
    static void helperAddKthNode(HashMap<TreeNode,TreeNode> mp,HashSet<TreeNode> hs,ArrayList<Integer>arr,int k,TreeNode root){
        if(root==null) return;
        if(hs.contains(root)) return;
        if(k==0){
            arr.add(root.val);
            return;
        }
        hs.add(root);
        helperAddKthNode(mp,hs,arr,k-1,root.left);
        helperAddKthNode(mp,hs,arr,k-1,root.right);
        helperAddKthNode(mp,hs,arr,k-1,mp.get(root));
    }
    static ArrayList<Integer> KthNode(TreeNode root, int val, int k){
        HashMap<TreeNode,TreeNode> mp= new HashMap<>();
        ArrayList<Integer> arr=new ArrayList<>();
        helperKNode(mp,root,null);
        TreeNode node=findNode(root,val);
        HashSet<TreeNode> hs=new HashSet<>();
        helperAddKthNode(mp,hs,arr,k,node);
        return arr;
    }
    public static void main(String[] args) {
        Tree bt=new Tree();
        int[] arr = {4,1,2,0,3,6,5,7,8};
        for (int x : arr) bt.insert(x);
        for(Integer x:KthNode(bt.root,3,5)){
            System.out.print(x+" ");
        }
    }
}
