package Trees;

public class BST {
    public static class Node{
        Node left;
        int val;
        int height;
        Node right;
        public Node(int val){
            this.val=val;
        }
    }
    public static class bSearchTree{
        Node root;

        private Node helper(Node root,int val){
            if(root==null){
                return new Node(val);
            }
            if(val<=root.val) {
                root.left=helper(root.left,val);
            }
            if(val>root.val){
                root.right=helper(root.right,val);
            }
            root.height=Math.max(height(root.left),height(root.right))+1;
            return root;
        }
        private int height(Node temp){
            if(temp==null) return 0;
            else return temp.height;
        }
        public void insert(int val){
            root=helper(root,val);
        }
        private Node populateSorteds(int st,int end ,int [] nums){
            if(st>end) return null;
            int mid=st+(end-st)/2;
            Node temp=new Node(nums[mid]);
            temp.left=populateSorteds(st,mid-1,nums);
            temp.right=populateSorteds(mid+1,end,nums);
            return temp;
        }
        private void populateSortedinsert(int [] nums,int st, int end){
            if(st>end) return;
            int mid=st+(end-st)/2;
            insert(nums[mid]);
            populateSortedinsert(nums,st,mid-1);
            populateSortedinsert(nums,mid+1,end);
        }
        public void populateSorted(int [] nums){
            //root=populateSorteds(0,nums.length-1,nums);
            populateSortedinsert(nums,0, nums.length-1);
        }

        public void display(){
            prehelpdisplay(root);
            System.out.println();
            inhelpdisplay(root);
            System.out.println();
            posthelpdisplay(root);
            System.out.println();
        }
        public boolean balanced(){
            return helpbalanced(root);
        }

        private boolean helpbalanced(Node root) {
            if(root==null) return true;
            return Math.abs(root.left.height-root.right.height)<=1&&helpbalanced(root.left)&&helpbalanced(root.right);
        }
        private void posthelpdisplay(Node root){
            if(root==null) return;
            posthelpdisplay(root.left);
            posthelpdisplay(root.right);
            System.out.print(root.val+" ");
        }
        private void prehelpdisplay(Node root) {
            if(root==null) return;
            System.out.print(root.val+" ");
            prehelpdisplay(root.left);
            prehelpdisplay((root.right));
        }
        private void inhelpdisplay(Node root){
            if(root==null) return;
            inhelpdisplay(root.left);
            System.out.print(root.val+" ");
            inhelpdisplay(root.right);
        }
        private BST.Node deleteNode(BST.Node root,int val){
            if(root==null) return null;
            if(root.val==val){
                if(root.right==null) return root.left;
                Node temp=root.right;
                while(temp.left!=null){
                    temp=temp.left;
                }
                temp.left=root.left;
                return root.right;
            }
            if(val<root.val){
                root.left=deleteNode(root.left,val);
            }
            else root.right=deleteNode(root.right,val);
            return root;
        }
    }

    public static void main(String[] args) {
        bSearchTree bst=new bSearchTree();
        int[] arr={4,3,1,2,6,5,7};
        for(int x:arr){
            bst.insert(x);
        }
//        bst.populateSorted(arr);
        bst.display();
        bst.root=bst.deleteNode(bst.root,4);
        bst.display();
    }
}
