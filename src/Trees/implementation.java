package Trees;
import java.util.Scanner;
public class implementation {
    public static class Node{
        Node prev;
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    public static class binaryTree{
        private Node root;
        int size=0;
        //Code to insert a node in binary tree.
        public  void add(){
            size+=1;
            Scanner sc=new Scanner(System.in);
            if(root==null){
                System.out.println("Enter the value :");
                root=new Node(sc.nextInt());
            }
            helpadd(root,sc);
        }
        private void helpadd(Node root,Scanner sc){
            System.out.println("Want to go left of :"+root.val);
            boolean left=sc.nextBoolean();
            if(left){
                System.out.println("Enter the value :");
                root.prev=new Node(sc.nextInt());
                helpadd(root.prev,sc);
            }
            System.out.println("Want to go right of"+root.val);
            boolean right=sc.nextBoolean();
            if(right){
                System.out.println("Enter the value :");
                root.next=new Node(sc.nextInt());
                helpadd(root.next,sc);
            }
        }
        private void helpdisplay(Node root){
            if(root==null) return;
            System.out.print(root.val);
            helpdisplay(root.prev);
            helpdisplay(root.next);
        }
        public void display(){
            helpdisplay(root);
        }
    }

    public static void main(String[] args) {
        binaryTree bt=new binaryTree();
        bt.add();
        bt.display();
    }
}
