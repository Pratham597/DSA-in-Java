package Trees;

import java.util.*;

public class problems3 {
    static void levelOrderTraversal(List<List<Integer>> l1, BST.Node root) {
        Queue<BST.Node> q = new LinkedList<>();
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
    static String serialise(BST.Node root){
        StringBuilder ans=new StringBuilder();
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp==null){
                    ans.append("n ");
                    continue;
                }
                ans.append(temp.val+" ");
                q.add(temp.left);
                q.add(temp.right);
            }
        }
        return ans.toString();
    }
    static Map<BST.Node,BST.Node> morristraversl(BST.Node root){
        Queue<BST.Node> q=new LinkedList<>();
        Map<BST.Node ,BST.Node> parent=new HashMap<>();
        q.add(root);
        while(!q.isEmpty()){
            int size= q.size();
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null){
                    q.add(temp.left);
                    parent.put(temp.left,temp);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                    parent.put(temp.right,parent.get(temp));
                }
            }
        }
        return parent;
    }
    static void traversalUsingHashMap(BST.Node root){
        Map<BST.Node,BST.Node> parent=morristraversl(root);
        BST.Node temp=root;
        while(temp!=null){
            System.out.print(temp.val+" ");
            if(temp.left!=null) {
                temp=temp.left;
            }
            else if(temp.right!=null){
                temp=temp.right;
            }
            else{
                temp=parent.get(temp);
                if(temp!=null){
                    while(temp.right==null){
                        temp=parent.get(temp);
                        if(temp==null) break;
                    }
                    if(temp!=null)temp=temp.right;
                }
            }
        }
    }
    static void traversalMorris(BST.Node root){
        BST.Node curr=root;
        while(curr!=null){
            //left se right le ja rha hai!
            if(curr.left==null){
                System.out.print(curr.val+" ");
                curr=curr.right;
            }
            else{
                BST.Node temp=curr.left;
                //take us to right of sub tree;
                while(temp.right!=null&&temp.right!=curr){
                    temp=temp.right;
                }
                //establish a connection with root;
                if(temp.right==null){
                    temp.right=curr;
                    System.out.print(curr.val+" ");
                    curr=curr.left;
                }
                //breaking a connection with root;
                else{
                    temp.right=null;
                    curr=curr.right;
                }
            }
        }
    }
    static void flatten(BST.Node root){
        BST.Node curr=root;
        while(curr!=null){
            BST.Node temp=curr.left;
            while(temp!=null&&temp.right!=null){
                temp=temp.right;
            }
            if(temp!=null){
                temp.right= curr.right;
                curr.right=curr.left;
                curr.left=null;
            }
            curr=curr.right;
        }
    }
    static BST.Node flattenusingrec(BST.Node root){
        if(root==null) return null;
        BST.Node left=flattenusingrec(root.left);
        BST.Node right=flattenusingrec(root.right);
        if(left==null){
            root.right=right;
        }
        else{
            root.right=left;
            BST.Node temp=root;
            while(temp.right!=null) temp=temp.right;
            temp.right=right;
        }
        root.left=null;
        return root;
    }
    static BST.Node deserialize(String ans){
        if(ans.isEmpty()) return null;
        String[] values=ans.split(" ");
        BST.Node root=new BST.Node(Integer.parseInt(values[0]));
        Queue<BST.Node> q=new LinkedList<>();
        q.add(root);
        for(int i=1;i<values.length;i++){
            BST.Node temp=q.poll();
            if(!values[i].equals("n")){
                BST.Node left=new BST.Node(Integer.parseInt(values[i]));
                temp.left=left;
                q.add(left);
            }
            i++;
            if(!values[i].equals("n")){
                BST.Node right=new BST.Node(Integer.parseInt(values[i]));
                temp.right=right;
                q.add(right);
            }
        }
        return root;
    }
    public static void main(String[] args) {
        List<List<Integer>> l1 = new ArrayList<>();
        int[] arr = {4,2,1,0,3,6,5,7};
        BST.bSearchTree bt = new BST.bSearchTree();
        for (int x : arr) bt.insert(x);
        traversalMorris(bt.root);
        System.out.println();
       levelOrderTraversal(l1,flattenusingrec(bt.root));
    }
}
