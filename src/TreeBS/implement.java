package TreeBS;


import Trees.revision;

public class implement {
    public static class TreeNode{
        int val;
        TreeNode left=null;
        TreeNode right=null;
        public TreeNode(int val){
            this.val=val;
        }
    }
    public static  class Tree{
        TreeNode root=null;
        public Tree(){}
        private static TreeNode inserthelper(TreeNode root, int val){
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
    }
    static void inorder(TreeNode root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    static TreeNode search(TreeNode root,int val){
        if(root==null) return null ;
        if(root.val==val) return root;
        if(val<root.val) return search(root.left,val);
        return search(root.right,val);
    }
    static TreeNode delete(TreeNode root,int val){
        if(root==null) return null;
        if(root.val==val){
            if(root.right==null) return root.left;
            else {
                TreeNode temp=root.right;
                while(temp.left!=null){
                    temp=temp.left;
                }
                temp.left=root.left;
                return root.right;
            }
        }
        root.left=delete(root.left,val);
        root.right=delete(root.right,val);
        return root;
    }
    static TreeNode kthSmallest(TreeNode root,int k){
        if(root==null) return null;
        TreeNode left=kthSmallest(root.left,k);
        if(left!=null) return left;
        idx++;
        if(idx==k) return root;
        return kthSmallest(root.right,k);
    }
    static TreeNode createfromPreorder(int [] idx,int [] arr,int high){
        if(idx[0]==arr.length||arr[idx[0]]>high) return null;
        TreeNode root=new TreeNode(arr[idx[0]++]);
        root.left=createfromPreorder(idx,arr,arr[idx[0]]);
        root.right=createfromPreorder(idx,arr,high);
        return root;
    }
    static TreeNode inorderSuccessor(TreeNode root,TreeNode x)
    {
        if(root==null) return null;
        TreeNode ans=null;
        if(root.val>x.val) ans=root;
        TreeNode subans=root.val>x.val?inorderSuccessor(root.left,x):inorderSuccessor(root.right,x);
        if(subans!=null) return subans;
        return ans;
    }
    static TreeNode inorderPredecessor(TreeNode root,TreeNode x){
        if(root==null) return null;
        TreeNode ans=null;
        if(root.val<x.val) ans=root;
        TreeNode subans=root.val<x.val?inorderSuccessor(root.right,x):inorderSuccessor(root.left,x);
        if(subans!=null) return subans;
        return ans;
    }
    static TreeNode kthLargest(TreeNode root,int k){
        if(root==null) return null;
        TreeNode left=kthLargest(root.right,k);
        if(left!=null) return left;
        idx++;
        if(idx==k) return root;
        return kthLargest(root.left,k);
    }   
    static int idx=0;
    public static void main(String[] args) {
        Tree bt=new Tree();
        int [] arr={4,1,2,3,0,6,5,7,8};
        for(int x:arr) bt.insert(x);
        System.out.println(kthLargest(bt.root,7).val);
    }
}
