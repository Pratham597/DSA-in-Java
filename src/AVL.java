import Trees.BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVL {
    public static class Node{
        int val;
        Node left;
        int height;
        Node right;
        public Node(int val){
            this.val=val;
            this.height=0;
        }

    }
    public static  class avl{
        Node root;
        public avl (){

        }
        public Node helper(Node root,int val){
            if(root==null){
                return new Node(val);
            }
            if (val < root.val) {
                root.left=helper(root.left,val);
            }
            else root.right=helper(root.right,val);
            root.height=Math.max(height(root.left),height(root.right))+1;
            return rotate(root);
        }
        private Node rightRotate(Node p){
            Node c=p.left;
            Node t=c.right;
            p.left=t;
            c.right=p;
            p.height=Math.max(height(p.left),height(p.right))+1;
            c.height=Math.max(height(c.left),height(c.right))+1;
            return c;
        }
        private Node leftRotate(Node c){
            Node p=c.right;
            Node t=p.left;
            p.left=c;
            c.right=t;
            p.height=Math.max(height(p.left),height(p.right))+1;
            c.height=Math.max(height(c.left),height(c.right))+1;
            return p;
        }
        private Node rotate(Node node){
            if(height(node.left)-height(node.right)>1){
                //left heavy
                if(height(node.left.left)-height(node.left.right)>0){
                    //left left case
                    return rightRotate(node);
                }
                if(height(node.left.left)-height(node.left.right)<0){
                    //left right case
                    node.left=leftRotate(node.right);
                    return rightRotate(node);
                }
            }
            else if(height(node.left)-height(node.right)<-1){
                //right heavy
                if(height(node.right.left)-height(node.right.right)<0){
                    //left left case
                    return leftRotate(node);
                }
                if(height(node.right.left)-height(node.right.right)>0){
                    //left right case
                    node.right=rightRotate(node.left);
                    return leftRotate(node);
                }
            }
            return node;
        }
        public int height(Node root){
            if(root==null) return 0;
            return root.height;
        }
        public void insert(int val){
            root=helper(root,val);
        }
        public void display(Node root){
            if(root==null) return;

            display(root.left);
            display(root.right);
            System.out.print(root.val+" ");
        }

    }
    static void levelOrderTraversal(List<List<Integer>> l1, Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            List<Integer> temp = new ArrayList<>();
            int len = q.size();
            for (int i = 0; i < len; i++) {
                if (q.peek().left != null) q.add(q.peek().left);
                if (q.peek().right != null) q.add(q.peek().right);
                temp.add(q.poll().val);
            }
            l1.add(temp);
        }
        for (var y : l1) {
            for (int x : y) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        avl t=new avl();
        int [] arr={1,2,3,4,5};
        for(int x:arr){
            t.insert(x);
        }
        levelOrderTraversal(new ArrayList<>(),t.root);
    }
}
