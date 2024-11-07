package TreeBS;

import java.util.Stack;

public class BSTiterator {
    public static class iterator{
        Stack<TreeNode> st=new Stack<>();
        boolean reverse=false;
        public iterator(TreeNode root,boolean reverse){
            this.reverse=reverse;
            pushAll(root);
        }
        int next(){
            TreeNode temp=st.pop();
            if(!reverse) pushAll(temp.right);
            else pushAll(temp.left);
            return temp.val;
        }
        TreeNode nextNode(){
            TreeNode temp=st.pop();
            if(!reverse) pushAll(temp.right);
            else pushAll(temp.left);
            return temp;
        }
        void pushAll(TreeNode root){
            if(!reverse){
                while(root!=null){
                    st.push(root);
                    root=root.left;
                }
            }
            else {
                while(root!=null){
                    st.push(root);
                    root=root.right;
                }
            }
        }
        boolean hasNext(){
            return !st.isEmpty();
        }
    }
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
    static boolean twoSumBst(TreeNode root,int k){
        iterator st=new iterator(root,false);
        iterator end=new iterator(root,true);
        int st1=st.next();
        int end1=end.next();
        while(st1<end1){
            System.out.println(st1+" "+end1);
            if(st1+end1==k) return true;
            else if(st1+end1>k) end1=end.next();
            else st1=st.next();
        }
        return false;
    }
    static void recoverBST(TreeNode root){
        iterator st=new iterator(root,false);
        iterator end=new iterator(root,true);
        TreeNode st1=st.nextNode();
        while(st.hasNext()){
            TreeNode st2=st.nextNode();
            if(st2.val<st1.val) break;
            st1=st2;
        }
        TreeNode end1=end.nextNode();
        while(end.hasNext()){
            TreeNode end2=end.nextNode();
            if(end2.val>end1.val) break;
            end1=end2;
        }
        if(st1.val>end1.val){
            int temp=st1.val;
            st1.val=end1.val;
            end1.val=temp;
        }
    }
    static  TreeNode fixed(TreeNode root,TreeNode []low ,boolean check){
        if(root==null) return null;
        TreeNode left=check?fixed(root.left,low,check):fixed(root.right,low,check);
        if(left!=null) return left;
        if(check){
            if(root.val<low[0].val) return low[0];
        }
        else if(root.val>low[0].val) return low[0];
        low[0]=root;
        return check?fixed(root.right,low,check):fixed(root.left,low,check);
    }
    static void recoverTree(TreeNode root) {
        TreeNode ans=new TreeNode(Integer.MIN_VALUE);
        TreeNode []low={ans};
        TreeNode temp1=fixed(root,low,true);
        ans.val=Integer.MAX_VALUE;
        low[0]=ans;
        TreeNode temp2=fixed(root,low,false);
        int temp=temp1.val;
        temp1.val=temp2.val;
        temp2.val=temp;
    }
    public static void main(String[] args) {
        Tree bt=new Tree();
        int [] arr={4,1,2,0,6,5,7,8};
        for(int x:arr)bt.insert(x);
        System.out.println(twoSumBst(bt.root,1));
    }
}
