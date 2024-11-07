package Trees;
import java.util.*;

//It's mainly on DFS
public class probelm2 {
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

    static BST.Node lca(BST.Node root, BST.Node xx, BST.Node yy) {
        if (root == null) return null;
        if (xx == root || yy == root) return root;
        BST.Node left = lca(root.left, xx, yy);
        BST.Node right = lca(root.right, xx, yy);
        if (left != null && right != null) return root;
        else if (left != null) return left;
        else return right;
    }

    static int ksmallest(BST.Node root, int k) {
        int idx = 0;
        Stack<BST.Node> st = new Stack<>();
        BST.Node node = root;
        while (true) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                if (st.isEmpty()) break;
                BST.Node temp = st.pop();
                idx++;
                node = temp.right;
                if (idx == k) return temp.val;
            }
        }
        return 0;
    }

    public BST.Node buildTree(int[] pre, int[] in) {
        if (pre.length == 0) return null;
        BST.Node root = new BST.Node(pre[0]);
        int idx = 0;
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) idx = i;
        }
        root.left = buildTree(Arrays.copyOfRange(pre, 1, idx + 1), Arrays.copyOfRange(in, 0, idx));
        root.right = buildTree(Arrays.copyOfRange(pre, idx + 1, pre.length), Arrays.copyOfRange(in, idx + 1, in.length));
        return root;
    }

    static int pathMax(BST.Node root, int[] dp) {
        if (root == null) return 0;
        int left = pathMax(root.left, dp);
        int right = pathMax(root.right, dp);
        dp[0] = Math.max(dp[0], left + right);
        return Math.max(left, right) + 1;
    }

    static int pathSum(BST.Node root, int[] dp) {
        if (root == null) return 0;
        int left = pathSum(root.left, dp);
        int right = pathSum(root.right, dp);
        if (left > 0 && right > 0) {
            dp[0] = Math.max(dp[0], root.val + left + right);
            return Math.max(Math.max(root.val + left, root.val + right), root.val);
        }
        if (left < 0 && right < 0) {
            dp[0] = Math.max(dp[0], root.val);
            return Math.max(Math.max(root.val + left, root.val + right), root.val);
        }
        if (left > 0) dp[0] = Math.max(dp[0], root.val + left);
        else dp[0] = Math.max(dp[0], root.val + right);
        return Math.max(Math.max(root.val + left, root.val + right), root.val);

    }

    static String preorderString(BST.Node root) {
        if (root == null) return "";
        return (char) root.val + preorderString(root.left) + preorderString(root.right);
    }

    static String inorderString(BST.Node root) {
        if (root == null) return "";
        return inorderString(root.left) + (char) root.val + inorderString(root.right);
    }

    static BST.Node deserialise(String pre, String in) {
        if (pre.isEmpty()) return null;
        char c = pre.charAt(0);
        BST.Node root = new BST.Node((int)c);
        int idx = 0;
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == c) {
                idx = i;
                break;
            }
        }
        root.left = deserialise(pre.substring(1, idx + 1), in.substring(0, idx));
        root.right = deserialise(pre.substring(idx + 1), in.substring(idx + 1));
        return root;
    }

    static String serialise(BST.Node root) {
        return preorderString(root) + inorderString(root);

    }

    static BST.Node deserialize(String ans) {
        StringBuilder str = new StringBuilder();
        StringBuilder str1 = new StringBuilder();
        int len = ans.length() / 2;
        for (int i = 0; i < len; i++) {
            str.append(ans.charAt(i));
            str1.append(ans.charAt(len + i));
        }
        return deserialise(str + "", str1 + "");
    }

    static boolean pathCheckSum(BST.Node root, int x) {
        if (root == null) return false;
        if (root.val == x) {
            if (root.right == null && root.left == null) return true;
        }
        boolean left = pathCheckSum(root.left, x - root.val);
        if (left) return left;
        return pathCheckSum(root.right, x - root.val);
    }

    static int pathSumRootLeaf(BST.Node root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.right == null && root.left == null) return sum;
        return pathSumRootLeaf(root.left, sum) + pathSumRootLeaf(root.right, sum);
    }
    static boolean pathCheck(BST.Node root,int [] arr,int idx){
        if(root==null) return false;
        if(root.val==arr[idx]){
            if(root.left==null&&root.right==null&&idx==arr.length-1) return true;
            boolean left=pathCheck(root.left,arr,idx+1);
            if(left) return left;
            return pathCheck(root.right,arr,idx+1);
        }
        return false;
    }
    static void addLeaf(BST.Node root,ArrayList<Integer>ans){
        if(root==null) return ;
        addLeaf(root.left,ans);
        addLeaf(root.right,ans);
        if(root.left==null&&root.right==null) ans.add(root.val);
    }
    static ArrayList <Integer> boundary(BST.Node root)
    {
        ArrayList<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        ans.add(root.val);
        BST.Node temp=root.left;
        if(root.left==null&&root.right==null) return ans;
        while(temp!=null){
            if(temp.left!=null||temp.right!=null) ans.add(temp.val);
            if(temp.left!=null) temp=temp.left;
            else if(temp.right!=null) temp=temp.right;
            else break;
        }
        addLeaf(root,ans);
        temp=root.right;
        Stack<Integer>st=new Stack<>();
        while(temp!=null){
            if(temp.left!=null||temp.right!=null) st.push(temp.val);
            if(temp.right!=null) temp=temp.right;
            else if(temp.left!=null) temp=temp.left;
            else break;

        }
        while(!st.isEmpty()){
            ans.add(st.pop());
        }
        return ans;
    }
    static void vertical(BST.Node root,Map<Integer,List<Integer>> ans,int idx){
        if(root==null) {
            return;
        }
        if(ans.containsKey(idx)){
            List<Integer> sub=ans.get(idx);
            sub.add(root.val);
        }
        else{
            List<Integer> sub=new ArrayList<>();
            sub.add(root.val);
            ans.put(idx,sub);
        }
        vertical(root.left,ans,idx-1);
        vertical(root.right,ans,idx+1);
    }
    //wap to print path from root to node:
    static BST.Node printPath(BST.Node root,int x,List<Integer> ans){
        if(root==null) return null;
        if(x==root.val){
            ans.add(root.val);
            return root;
        }
        ans.add(root.val);
        BST.Node left=printPath(root.left,x,ans);
        if(left!=null) return left;
        BST.Node right=printPath(root.right,x,ans);
        if(right!=null) return right;
        ans.remove(ans.size()-1);
        return right;
    }
    static BST.Node optimisedhildSum(BST.Node root){
        if(root.left==null||root.right==null) return root;
        int sum=root.left.val+root.right.val;
        if(root.val<sum) root.val=sum;
        root.left.val=root.val;
        root.right.val=root.val;
        BST.Node left=optimisedhildSum(root.left);
        BST.Node right=optimisedhildSum(root.right);
        root.val=left.val+right.val;
        return root;
    }
    static BST.Node childrenSum(BST.Node root){
        if(root==null) return null;
        BST.Node left=childrenSum(root.left);
        BST.Node right=childrenSum(root.right);
        if(left==null||right==null) return root;
        int sum=left.val+right.val;
        if(sum==root.val) return root;
        else if(root.val<sum) root.val=sum;
        else {
            left.val=root.val-right.val;
            childrenSum(root.left);
        }
        return root;
    }
    static BST.Node foundNode(BST.Node root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        BST.Node left = foundNode(root.left, val);
        if (left != null) return left;
        return foundNode(root.right, val);
    }
    static void kthNodeHelper(BST.Node root,Map<BST.Node, BST.Node > parent){
        Queue<BST.Node> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                BST.Node temp=q.poll();
                if (temp.left != null) {
                    q.add(temp.left);
                    parent.put(temp.left,temp);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                    parent.put(temp.right,temp);
                }
            }
        }
    }
    static void kthNodeMain(BST.Node root,int val,int k,List<Integer> l1){
        BST.Node main=foundNode(root,val);
        int level =0;
        Map<BST.Node,BST.Node> parent=new HashMap<>();
        Map<BST.Node,Boolean> check=new HashMap<>();
        check.put(main,true);
        kthNodeHelper(root,parent);
        Queue<BST.Node> q=new LinkedList<>();
        q.add(main);
        while(!q.isEmpty()){
            int size=q.size();
            if(level==k) break;
            level++;
            for(int i=0;i<size;i++){
                BST.Node temp=q.poll();
                if(temp.left!=null&&check.get(temp.left)==null){
                    q.add(temp.left);
                    check.put(temp.left,true);
                }
                if(temp.right!=null&&check.get(temp.right)==null){
                    q.add(temp.right);
                    check.put(temp.right,true);
                }
                if(parent.get(temp)!=null&&check.get(parent.get(temp))==null){
                    q.add(parent.get(temp));
                    check.put(parent.get(temp),true);
                }
            }
        }
        while(!q.isEmpty()){
            l1.add(q.poll().val);
        }
    }
    static int burnTree(BST.Node root,Map<BST.Node ,BST.Node> parent,Map<BST.Node,Boolean>check){
        if(root==null) return -1;
        if(check.get(root)!=null) return -1;
        check.put(root,true);
        int left=burnTree(root.left,parent,check)+1;
        int right=burnTree(root.right,parent,check)+1;
        int top=burnTree(parent.get(root),parent,check)+1;
        System.out.println(left+" "+right+" "+top);
        return Math.max(left,Math.max(right,top));
    }
    static int burnTreeMain(BST.Node root,int val){
        BST.Node temp=foundNode(root,val);
        Map<BST.Node ,BST.Node> parent=new HashMap<>();
        kthNodeHelper(root,parent);
        Map<BST.Node ,Boolean> check=new HashMap<>();
        return burnTree(temp,parent,check);
    }
    static int findleftheight(BST.Node root) {
        int count = 0;
        while (root!= null) {
            count++;
            root = root.left;
        }
        return count;
    }
    static int findrightheight(BST.Node root){
        int count=0;
        while (root!= null) {
            count++;
            root = root.right;
        }
        return count;
    }
    static int count(BST.Node root){
        if(root==null) return 0;
        int left=findleftheight(root);
        int right=findrightheight(root);
        System.out.println(left+" "+right);
        if(left==right) return(int) Math.pow(2,left)-1;
        int count1=count(root.left);
        int count2=count(root.right);
        return count1+count2+1;

    }
    static BST.Node constructinandpost(int [] post,int []in){
        if(in.length==0) return null;
        int n=post.length;
        int val=post[n-1];
        BST.Node root=new BST.Node(val);
        int idx=0;
        for(int i=0;i<n;i++){
            if(val==in[i]){
                idx=i;
                break;
            }
        }
        root.left=constructinandpost(Arrays.copyOfRange(post,0,idx),Arrays.copyOfRange(in,0,idx));
        root.right=constructinandpost(Arrays.copyOfRange(post,idx,n-1),Arrays.copyOfRange(in,idx+1,n));
        return root;
    }
    public static void main(String[] args) {
        List<List<Integer>> l1 = new ArrayList<>();
        int[] arr = {5,3,1,-1,4,7,6,8};
        BST.bSearchTree bt = new BST.bSearchTree();
        for (int x : arr) bt.insert(x);
        int [] in={0,1,3,4,5,6,7,8};
        int []post={0,1,4,3,6,8,7,5};
        levelOrderTraversal(l1,deserialize(serialise(bt.root)));
    }
}
