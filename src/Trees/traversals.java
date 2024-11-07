package Trees;

import java.util.*;

public class traversals {
    static class Pair{
        BST.Node TreeNode;
        int data;
        public Pair(BST.Node TreeNode, int val ){
            this.TreeNode=TreeNode;
            this.data=val;
        }
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
    static void inorderUsingStack(BST.Node root){
        Stack<BST.Node> st=new Stack<>();
        BST.Node node=root;
        while(true){
            if(node!=null){
                st.push(node);
                node=node.left;
            }
            else{
                if(st.isEmpty()) break;
                BST.Node temp=st.pop();
                node=temp.right;
                System.out.print(temp.val+" ");
            }
        }
        System.out.println();
    }
    //it have a usage of two stacks:
    static void postorderUsingStack(BST.Node root){
        Stack<BST.Node> st=new Stack<>();
        Stack<Integer> ans =new Stack<>();
        st.push(root);
        while(st.size()>0){
            BST.Node temp=st.pop();
            ans.push(temp.val);
            if(temp.left!=null) st.push(temp.left);
            if(temp.right!=null) st.push(temp.right);
        }
        while(ans.size()>0){
            System.out.print(ans.pop()+" ");
        }
    }
    //time complexity:->O(2n) but space complexity->O(n);
    static void postorderusingStack(BST.Node root){
        Stack<BST.Node> st=new Stack<>();
        BST.Node curr=root,temp=null;
        while(curr!=null||st.size()>0){
            if(curr!=null){
                st.push(curr);
                curr=curr.left;
            }
            else{
                temp=st.peek().right;
                if(temp==null){//left->null and right-> null
                    temp=st.pop();
                    System.out.print(temp.val+" ");
                    while(!st.isEmpty()&&temp==st.peek().right){
                        temp=st.pop();
                        System.out.print(temp.val+" ");
                    }
                    if(!st.isEmpty()) curr=st.peek().right;
                }
                else curr=temp;
            }
        }
        System.out.println();
    }
    static void preorderUsingStack(BST.Node root){
        Stack<BST.Node> st=new Stack<>();
        st.push(root);
        while(st.size()>0){
            System.out.print(st.peek().val+" ");
            BST.Node temp=st.pop();
            if(temp.right!=null){
                st.push(temp.right);
            }
            if(temp.left!=null) {
                st.push(temp.left);
            }
        }
        System.out.println();
    }
    static void threeTraversal(BST.Node root){
        Stack<Pair> st = new Stack<>();
        ArrayList<Integer> preOrder =new ArrayList<>();
        ArrayList<Integer> postOrder=new ArrayList<>();
        ArrayList<Integer> inOrder =new ArrayList<>();
        st.push(new Pair(root,1));
        while(!st.isEmpty()){
            Pair it=st.pop();
            if(it.data==1){
                preOrder.add(it.TreeNode.val);
                it.data++;
                st.push(it);
                if(it.TreeNode.left!=null) st.push(new Pair(it.TreeNode.left,1));
            }
            else if(it.data==2){
                inOrder.add(it.TreeNode.val);
                it.data++;
                st.push(it);
                if(it.TreeNode.right!=null) st.push(new Pair(it.TreeNode.right,1));
            }
            else{
                postOrder.add(it.TreeNode.val);
            }
        }
        for(int x:preOrder){
            System.out.print(x+" ");
        }
        System.out.println();
        for(int x:inOrder){
            System.out.print(x+" ");
        }
        System.out.println();
        for(int x:postOrder){
            System.out.print(x+" ");
        }
    }
    static int height(BST.Node root){
        if(root==null) return 0;
        return Math.max(height(root.left),height(root.right))+1;
    }
    static int diameter(BST.Node root ){
        if(root==null) return 0;
        int ans=diameter(root.left);
        int ans1=diameter(root.right);
        int ans2=helpdiameter(root);
        return Math.max(ans,Math.max(ans1,ans2));
    }
    static int helpdiameter(BST.Node root){
        return height(root.left)+height(root.right);
    }
    static int heightTimeOptimised(BST.Node root){
        //it will check every left node and right node height that it's difference  should not greater than 1
        // before returning node actual right:
        if(root==null) return 0;
        int left=heightTimeOptimised(root.left);
        if(left==-1) return -1;
        int right=heightTimeOptimised(root.right);
        if(right==-1) return -1;
        if(Math.abs(left-right)>1) return -1;
        return Math.max(left,right)+1;
    }
    static boolean balancedOptimised(BST.Node root){
        return heightTimeOptimised(root)!=-1;
    }
    static boolean balanced(BST.Node root){
        if(root==null) return true;
        return Math.abs(height(root.left)-height(root.right))<=1&&balanced(root.left)&&balanced(root.right);
    }
    public static void main(String[] args) {
        List<List<Integer>> l1=new ArrayList<>();
        BST.bSearchTree bt=new BST.bSearchTree();
        int [] arr={2,1,0,4,3,6,7,5};
        for(int x:arr){
            bt.insert(x);
        }
        levelOrderTraversal(l1,bt.root);
        System.out.println(diameter(bt.root));

        //System.out.print(height(bt.root));
    }
}
