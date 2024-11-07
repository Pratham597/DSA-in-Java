package Trees;

import java.util.*;

public class problems {
    static int diameter(BST.Node root, int []dp){
        if(root==null) return 0;
        int left=diameter(root.left,dp);
        int right=diameter(root.right,dp);
        dp[0]=Math.max(left+right,dp[0]);//store the max diameter
        return Math.max(left,right)+1;
    }
    static void levelOrderTraversal(List<List<Integer>> l1, BST.Node root){
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            List<Integer> temp=new ArrayList<>();
            int len=q.size();
            for(int i=0;i<len;i++){
                if(q.peek().left!=null) q.add(q.peek().left);
                if(q.peek().right!=null) q.add(q.peek().right);
                temp.add(q.poll().val);
            }
            l1.add(temp);
        }
        for(var y:l1){
            for(int x:y){
                System.out.print(x+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static int sum(BST.Node root, int []dp){
        if(root==null) return 0;
        int ans=sum(root.left,dp);
        int ans1=sum(root.right,dp);
        dp[0]=Math.max(dp[0],ans+root.val);
        dp[0]=Math.max(dp[0],ans1+root.val);
        dp[0]=Math.max(dp[0],ans+ans1+root.val);
        //storing the maximum path sum:
        return Math.max(ans,ans1)+root.val;
    }
    static int levelOrderSucceesor(BST.Node root, int target){
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            int len=q.size();
            for(int i=0;i<len;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                if(temp.val==target) {
                    if(q.isEmpty()) return -1;
                    else return q.peek().val;
                }
            }
        }
        return -1;

    }
    static void zigZagUsingStack(BST.Node root){
        List<List<Integer>> ans=new ArrayList<>();
        Stack<BST.Node> st=new Stack<>();
        st.add(root);
        int cn=0;
        while(!st.isEmpty()){
            List<Integer> subans=new ArrayList<>();
            int size= st.size();
            Stack<BST.Node> subst=new Stack<>();
            if(cn%2==0){
                for(int i=0;i<size;i++){
                    BST.Node temp=st.pop();
                    if(temp.right!=null) subst.add(temp.right);
                    if(temp.left!=null) subst.add(temp.left);
                    subans.add(temp.val);
                }
            }
            else{
                for(int i=0;i<size;i++){
                    BST.Node temp=st.pop();
                    if(temp.left!=null) subst.add(temp.left);
                    if(temp.right!=null) subst.add(temp.right);
                    subans.add(temp.val);
                }
            }
            cn++;
            ans.add(subans);
            st=subst;
        }
        for(var y:ans){
            for(int x:y){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }
    static void zigZag(BST.Node root){
        List<List<Integer>> ans=new ArrayList<>();
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        int cn=1;
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> subans=new ArrayList<>();
            //level formation
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                subans.add(temp.val);
            }
            if(cn%2==0) ans.add(subans);
            else ans.add(subans.reversed());
            cn += 1;

        }
        for(var y:ans){
            for(int x:y){
                System.out.print(x+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void averageOfLevel(BST.Node root){
        List<Double> ans=new ArrayList<>();
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            double sum=0;
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                sum+=temp.val;
            }
            ans.add(sum/size);
        }
        for(double x:ans) System.out.print(x+" ");
    }
    static void populatingnextPointer(BST.Node root){
        if(root==null) return;
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                //if(i!=size-1) temp.next=q.peek();
            }
        }
    }
    static boolean isCousins(BST.Node root, int x, int y) {
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            boolean flag=false,flag1=false;
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null)q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
                if(temp.left!=null&&temp.right!=null){
                    if(temp.left.val==x&&temp.right.val==y) return false;
                    if(temp.left.val==y&&temp.right.val==x) return false;
                }
                if(temp.val==x) flag=true;
                if(temp.val==y) flag1=true;
                if(flag1&&flag) return true;
            }
        }
        return false;
    }
    static int level(BST.Node root, int level, int x){
        if(root==null) return 0;
        if(root.val==x) return level;
        int left=level(root.left,level+1,x);
        if(left!=0) return left;
        int right=level(root.right,level+1,x);
        return right;
    }
    static boolean siblings(BST.Node root, BST.Node xx, BST.Node yy){
        if(root==null) return false;
        if(root.left==xx&&root.right==yy||root.left==yy&&root.right==xx) return true;
        boolean ans1=siblings(root.left,xx,yy);
        if(ans1) return ans1;
        boolean ans2=siblings(root.right,xx,yy);
        return ans2;
    }
    static boolean symmetric(BST.Node left,BST.Node right){
        if(left==null&&right==null) return true;
        if(left==null||right==null) return false;
        if(left.val!=right.val) return false;
        boolean ans1=symmetric(left.left,right.right);
        if(!ans1) return ans1;
        return symmetric(left.right,right.left);
    }
    static BST.Node found(BST.Node root, int val){
        if(root==null) return null;
        if(root.val==val) return root;
        BST.Node left=found(root.left,val);
        if(left!=null) return left;
        BST.Node right=found(root.right,val);
        return right;
    }
    public static void main(String[] args) {
        int[] arr={2,0,1,4,3,5};
        int []dp={Integer.MIN_VALUE};
        List<List<Integer>> l1=new ArrayList<>();
        BST.bSearchTree bt=new BST.bSearchTree();
        for(int x:arr){
            bt.insert(x);
        }
//        levelOrderTraversal(l1,bt.root);
    }
}
