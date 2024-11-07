package arrays;

import java.util.*;

import static java.util.Arrays.sort;

public class problems {
    static void reverseQueue(Queue<Integer> q){
        Stack<Integer> st=new Stack<>();
        while(!q.isEmpty()){
            st.push(q.poll());
        }
        while(!st.isEmpty()){
            q.add(st.pop());
        }
    }
    static void insertAtIdx(Queue<Integer> q,int idx,int val){
        int size=q.size();
        if(idx>size+1) return ;
        for(int i=0;i<idx;i++){
            q.add(q.poll());
        }
        q.add(val);
        for(int i=0;i<size-idx;i++){
            q.add(q.poll());
        }

    }
    static void deleteAtIdx(Queue<Integer> q,int idx){
        int size=q.size();
        if(idx>size+1) return ;
        for(int i=0;i<idx;i++){
            q.add(q.poll());
        }
        q.poll();
        for(int i=0;i<size-idx-1;i++){
            q.add(q.poll());
        }
    }
    //wap to find out maximum number in k length of subarray(O(n)-> space complexity ans O(n)-> time complexity):
    static int [] slidingwindowmaximum(int [] arr,int k){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int [] nge=new int[n];
        int [] ans=new int[n-k+1];
        nge[n-1]=n;
        st.push(n-1);
        //Next greater array formed using monotonic stack:
        for(int i=n-2;i>=0;i--){
            while(!st.isEmpty()&&arr[i]>arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) nge[i]=n;
            else nge[i]=st.peek();
            st.push(i);
        }
        //ans array formed and we jumps over the nge array:
        int temp=0;
        int idx=0;
        for(int i=0;i<ans.length;i++){
            if(idx>=i+k) idx=i;//reduce jumps and save time complexity:
            while(idx<i+k){
                temp=idx;
                idx=nge[idx];
            }
            ans[i]=arr[temp];
        }
        return ans;
    }
    static int [] slidingwindownegative(int [] arr,int k){
        Queue<Integer> q=new LinkedList<>();
        int n=arr.length;
        int [] ne=new int[n];
        int [] ans=new int[n-k+1];
        for(int i=0;i<n;i++){
            if (arr[i] > 0) {
                q.add(i);
            }
            else{
                while(!q.isEmpty()){
                    ne[q.poll()]=i;
                }
                ne[i]=i;
            }
        }
        while(!q.isEmpty()){
            ne[q.poll()]=n;
        }
        for(int i=0;i<ans.length;i++){
            int idx=ne[i];
            if(idx<i+k) ans[i]=arr[idx];
        }
        return ans;
    }
    static int [] optimalnegative(int[] arr,int k){
        Queue<Integer> q=new LinkedList<>();
        int n=arr.length;
        for(int i=0;i<n;i++){
            if(arr[i]<0) q.add(i);
        }
        int [] ans=new int [n-k+1];
        for(int i=0;i<ans.length;i++){
            if(q.isEmpty()) break;
            if(q.peek()<i) q.poll();
            if(q.isEmpty()) break;
            if(q.peek()<i+k){
                ans[i]=arr[q.peek()];
            }
        }
        return ans;
    }
    static int maxSumArray(int [] arr,int k){
        HashMap<Integer,Integer> mp=new HashMap<>();
        mp.put(0,-1);
        int max=0;
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            int rem=sum-k;
            if(mp.containsKey(rem)){
                int idx=mp.get(rem);
                max=Math.max(max,i-idx);
            }
            mp.putIfAbsent(sum,i);
        }
        return max;
    }
    static int optimisedforpostives(int [] arr,int k){
        int n=arr.length;
        int st=0;
        int end=0;
        int max=0;
        int sum=arr[0];
        while(end<n){
            if(st<=end&&sum>k){
                sum-=arr[st];
                st++;
            }
            else if(sum==k){
                max=Math.max(max,end-st+1);
            }
            end++;
            if(end<n) sum+=arr[end];
        }
        return max;
    }
    static void sortzeroesonestwosArray(int[] arr){
        int st=0;
        int end=arr.length-1;
        int mid=0;
        while(mid<=end){
            if(arr[mid]==0){
                if(st==mid){
                    st++;
                    mid++;
                }
                else{
                    int temp=arr[st];
                    arr[st]=0;
                    arr[mid]=temp;
                    st++;
                }
            }
            if(arr[mid]==2){
                int temp=arr[end];
                arr[end]=2;
                arr[mid]=temp;
                end--;
            }
            if(arr[mid]==1) mid++;
        }
    }
    static int maxSumSubArray(int [] arr,int k){
        HashMap<Integer,Integer> mp=new HashMap<>();
        mp.put(k,-1);
        int max=0;
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>k) {
                sum=0;
                mp.put(k,i);
            }
            else if(sum==k){
                int idx =mp.get(k);
                System.out.println(i-idx);
                max= Math.max(max,i-idx);
                mp.put(k,i);
                sum=0;
            }
        }
        return max;
    }
    static int[] twoSum(int [] arr,int k){
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(mp.containsKey(k-arr[i])){
                int idx=mp.get(k-arr[i]);
                return new int[]{idx,i};
            }
            mp.put(arr[i],i);
        }
        return new int[]{};
    }
    static int[] kadanesAlgorithm(int[] arr){
        int sum=0;
        int max=Integer.MIN_VALUE;
        int [] ans=new int[2];
        for(int i=0;i<arr.length;i++){
            if(sum==0) ans[0]=i;
            sum+=arr[i];
            if(sum>max){
                ans[1]=i;
                max=sum;
            }
            if(sum<0)sum=0;
        }
        return ans;
    }
    //Wap to find the subarray which sum is maximum:
    static int maxsubarraysum(int[] arr){
        int n=arr.length;
        int [] prefix=new int[n];
        int [] suffix=new int[n];
        int sum=0,sum1=0;
        //cresting prefix:
        int ans=arr[0];
        for(int i=0;i<n;i++){
            sum+=arr[i];
            sum1+=arr[n-1-i];
            suffix[i]=sum1;
            prefix[i]=sum;
            ans=Math.max(ans,arr[i]);
        }
        //prefix variales:
        int max=Integer.MIN_VALUE,idx=0,min=0,idxmin=-1;
        //suffix variables:
        int maxs=Integer.MIN_VALUE,idxs=0,mins=0,idxmins=-1;
        for(int i=0;i<n;i++){
            if(max<prefix[i]){
                idx=i;
                max=prefix[i];
            }
            if(maxs<suffix[i]){
                idxs=i;
                maxs=suffix[i];
            }
        }
        for(int i=0;i<idx;i++){
            if(min>prefix[i]){
                min=prefix[i];
                idxmin=i;
            }
        }
        for(int i=0;i<idxs;i++){
            if(mins>suffix[i]){
                mins=suffix[i];
                idxmins=i;
            }
        }
        int ans1=0,ans2=0;
        if(idxmins==-1){
            ans2=suffix[idxs];
        }
        else{
            ans2=suffix[idxs]-suffix[idxmins];
        }
        if(idxmin==-1){
            ans1= prefix[idx];
        }
        else ans1=prefix[idx]-prefix[idxmin];
        if(ans2>ans1) return ans2;
        return ans1;
    }
    //Moore voting algorithm:
    static int majorelement(int[] arr){
        int ele=arr[0];
        int count=1;
        for(int i=1;i<arr.length;i++){
            if(count==0){
                ele=arr[i];
                count=1;
            }
            else if(arr[i]!=ele)count--;
            else count++;
        }
        count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==ele){
                count++;
            }
        }
        if(count>arr.length/2)return ele;
        return -1;
    }
    static int majorityelements(int[] arr){
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(mp.containsKey(arr[i])){
                int freq=mp.get(arr[i]);
                mp.put(arr[i],freq+1);
            }
            else mp.put(arr[i],1);
        }
        int max=0;
        for(int x:mp.keySet()){
            int val=mp.get(x);
            if(val>arr.length/2){
                return val;
            }
        }
        return -1;
    }
    static boolean searchladder(int row,int col,char [][]board ,String word,String ans){
        int n=board.length;
        int m=board[0].length;
//        System.out.println(ans);
        System.out.println(row+" "+col);
        if(word.equals("")) return true;
        if(row<0||col<0||row>=n||col>=m) return false;
        if(board[row][col]=='.') return false;
        if(row!=0||col!=0&&board[row][col]!=word.charAt(0)) return false;
        if(board[row][col]==word.charAt(0)){
            char c=board[row][col];
            board[row][col]='.';
            if(searchladder(row,col+1,board,word.substring(1),ans+c)) return true;
            if(searchladder(row+1,col,board,word.substring(1),ans+c)) return true;
            if(searchladder(row,col-1,board,word.substring(1),ans+c)) return true;
            if(searchladder(row-1,col,board,word.substring(1),ans+c)) return true;
            board[row][col]=c;
            return false;
        }
        for(int i=col+1;i<m;i++){
            if(searchladder(row,i,board,word,ans)) return true;
        }
        return searchladder(row+1,0,board,word,ans);
    }
    static void rearrangebrute(int[]arr){
        int n=arr.length,p1=0,n1=0;
        int []pos =new int[n/2];
        int []neg =new int[n/2];
        for(int i=0;i<n;i++){
            if(arr[i]>0) pos[p1++]=arr[i];
            if(arr[i]<0) neg[n1++]=arr[i];
        }
        int a=0;
        p1=0;
        n1=0;
        for(int i=0;i<n/2;i++){
            arr[a++]=pos[p1++];
            arr[a++]=neg[n1++];
        }
        System.out.println(Arrays.toString(arr));
    }
    static int stockselling(int[]arr){
        int min=arr[0];
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max=Math.max(arr[i]-min,max);
            min=Math.min(min,arr[i]);
        }
        return max;
    }
    static void rearrange2(int[]arr){
        int n=arr.length,p1=0,n1=0;
        ArrayList<Integer> pos=new ArrayList<>();
        ArrayList<Integer> neg=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(arr[i]>0) pos.add(arr[i]);
            if(arr[i]<0) neg.add(arr[i]);
        }
        p1=0;
        n1=0;
        int plength=pos.size();
        int nlength=neg.size();
        int a=0;
        while(p1<plength&&n1<nlength){
            arr[a++]=pos.get(p1++);
            arr[a++]=neg.get(n1++);
        }
        while(p1<plength){
            arr[a++]=pos.get(p1++);
        }
        while(n1<nlength){
            arr[a++]=neg.get(n1++);
        }
        System.out.println(Arrays.toString(arr));
    }
    static int [] nextPermutation(int [] arr){
        int n=arr.length;
        int end=n-1;
        int send=end-1;
        while(end>0&&arr[end]<=arr[send]){
            end=send;
            send--;
        }
        if(end>0){
            int idx=n-1;
            while(arr[send]>=arr[idx]){
                idx--;
            }
            int temp=arr[send];
            arr[send]=arr[idx];
            arr[idx]=temp;
            sort(arr,end,n);

        }
        else{
            int st=0;
             end=n-1;
             while(st<end){
                int temp=arr[st];
                arr[st]=arr[end];
                arr[end]=temp;
                st++;
                end--;
            }
        }
        return arr;
    }
    static ArrayList<Integer> leaders(int[] arr){
        int n=arr.length;
        int max=Integer.MIN_VALUE;
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=n-1;i>=0;i--){
            if(arr[i]>max) ans.add(arr[i]);
            max=Math.max(arr[i],max);
        }
        Collections.reverse(ans);//leaders from starting of array.
        return ans;
    }
    static int longestconsecutivesequence(int[]arr){
        HashSet<Integer> hs=new HashSet<>();
        for(int x:arr)hs.add(x);
        int max=0;
        for(int i=0;i<arr.length;i++){
            int x=arr[i];
            int count=0;
            if(!hs.contains(x-1)){
                while(hs.contains(x)){
                    hs.remove(x);
                    x++;
                    count++;
                }
            }
            max=Math.max(count,max);
        }
        return max;
    }
    //WAp to set matrix zeroes of that row and coli=umn if that row and column contains zeroes.
    static void setmatrixzeroes(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int[] row=new int[n];
        int [] col=new int[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==0){
                    row[i]=1;
                    col[j]=1;
                }
            }
        }
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if(row[i]==1||col[j]==1){
                    arr[i][j]=0;
                }
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    static void matrixzeroesoptimised(int[][]arr){
        int n=arr.length;
        int m=arr[0].length;
        int col0=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==0){
                    arr[i][0]=0;
                    if(j!=0)arr[0][j]=0;
                    else col0=0;
                }
            }
        }
        //we have to track and finalise the array:
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(arr[i][0]==0||arr[0][j]==0) arr[i][j]=0;
            }
        }
        if(arr[0][0]==0){
            for(int j=1;j<m;j++){
                arr[0][j]=0;
            }
        }
        if(col0==0){
            for(int j=0;j<n;j++){
                arr[j][0]=0;
            }
        }
    }
    static int toatlsubarraysumK(int[] arr,int k){
        int sum=0,count=0,n=arr.length;
        HashMap<Integer,Integer> mp=new HashMap<>();
        mp.put(0,1);
        for (int j : arr) {
            sum += j;
            if (mp.containsKey(sum - k)) {
                count += mp.get(sum - k);
            }
            if (mp.containsKey(sum)) mp.put(sum, mp.get(sum) + 1);
            else mp.put(sum, 1);
        }
        return count;
    }
    static int nCr(int n,int r) {
        int ans=1;
        for(int i=0;i<r;i++){
           ans=ans*(n-i);
           ans=ans/(i+1);
        }
        return ans;
    }
    static List<Integer> rowofPascal(int row){
        int ans=1;
        List<Integer> arr=new ArrayList<>();
        arr.add(1);
        for(int j=0;j<row;j++){
            ans=ans*(row-j)/(j+1);
            arr.add(ans);
        }
        return arr;
    }
    static void printPascal(int n){
        for(int i=0;i<n;i++){
            rowofPascal(i);
        }
    }
    static List<Integer> majorityelements3(int[]arr){
        List<Integer> ans=new ArrayList<>();
        int ele1=0,ele2=0,count1=0,count2=0;
        for(int i=0;i<arr.length;i++){
            if(count1==0&&arr[i]!=ele2){
                ele1=arr[i];
                count1=1;
            }
            else if(count2==0&&arr[i]!=ele1){
                ele2=arr[i];
                count1=1;
            }
            else if(arr[i]==ele1)count1++;
            else if(arr[i]==ele2)count2++;
            else{
                count1--;
                count2--;
            }
        }
        count1=0;
        count2=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==ele1)count1++;
            else if(arr[i]==ele2)count2++;
        }
        if(count2>arr.length/3)ans.add(ele2);
        if(count1>arr.length/3)ans.add(ele1);
        return ans;
    }
    static String reverse(String s){
        while(s.charAt(0)==' '){
            s=s.substring(1);
        }
        int n=s.length();
        while(s.charAt(n-1)==' '){
            s=s.substring(0,n-1);
            n=n-1;
        }
        String[] l1=s.split(" ");
        StringBuilder ans=new StringBuilder();
        for(int i=l1.length-1;i>0;i--){
            if(l1[i].isEmpty())continue;
            l1[i]=l1[i]+" ";
            ans.append(l1[i]);
        }
        if(l1[0].isEmpty()) return ans+"";
        ans.append(l1[0]);
        return ans+"";
    }
    static List<List<Integer>> foursum(int []arr,int target){
        sort(arr);
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(i>0&&arr[i]==arr[i-1])continue;
            for(int j=i+1;j<arr.length;j++){
                if(j>i+1&&arr[j]==arr[j-1]) continue;
                int st=j+1;
                int end=arr.length-1;
                while(st<end){
                    long k=(long)arr[st]+arr[end]+arr[j]+arr[i];
                    if(target<k)end--;
                    else if(target>k)st++;
                    else{
                        List<Integer> temp=new ArrayList<>();
                        temp.add(arr[i]);
                        temp.add(arr[j]);
                        temp.add(arr[st]);
                        temp.add(arr[end]);
                        ans.add(temp);
                        st++;
                        end--;
                        while(st<end&&arr[st]==arr[st-1])st++;
                        while(st<end&&arr[end]==arr[end+1]) end--;
                    }
                }
            }
        }
        return ans;
    }
    static List<List<Integer>> threesum2(int[]arr){
        List<List<Integer>> ans=new ArrayList<>();
        sort(arr);
        int n=arr.length;
        int i=0;
        while(i<n){
            int st=i+1;
            int end=n-1;
            int k=0-arr[i];
            while(st<end){
                if(arr[st]+arr[end]==k){
                    List<Integer> temp=new ArrayList<>();
                    temp.add(arr[i]);
                    temp.add(arr[st]);
                    temp.add(arr[end]);
                    ans.add(temp);
                    st++;
                    end--;
                    while(st<end&&arr[st]==arr[st-1])st++;
                    while(st<end&&arr[end]==arr[end+1]) end--;
                }
                else if(arr[st]+arr[end]<k)st++;
                else if(arr[st]+arr[end]>k)end--;
            }
            i++;
            while(i<n&&arr[i]==arr[i-1]) i++;
        }
        return ans;
    }
    static List<List<Integer>> threesum(int[]arr){
        HashSet<List<Integer>> hs=new HashSet<>();
        int n=arr.length;
        for(int i=0;i<n;i++){
            HashSet<Integer> st=new HashSet<>();
            int k=0-arr[i];//sum-> to be searched and arise the question of two sum:
            for(int j=i+1;j<n;j++){
                if(st.contains(k-arr[j])){
                    List<Integer> temp=new ArrayList<>();
                    temp.add(arr[i]);
                    temp.add((arr[j]));
                    temp.add(k-arr[j]);
                    temp.sort(Comparator.naturalOrder());
                    hs.add(temp);
                }
                st.add(arr[j]);
            }
        }
        return hs.stream().toList();
    }
    static int countsubarraysxor(int[] arr,int target){
        int n=arr.length;
        HashMap<Integer,Integer>mp=new HashMap<>();
        mp.put(0,1);
        int xor=0;
        int count=0;
        for(int i=0;i<n;i++){
            xor=xor^arr[i];
            if(mp.containsKey(xor^6)){
                count+=mp.get(xor^6);
            }
            if(mp.containsKey(xor))mp.put(xor,mp.get(xor)+1);
            else mp.put(xor,1);
        }
        return count;
    }

    static boolean check(ArrayList<Integer> arr,int m,int[] pattern){
        for(int j=0;j<m;j++){
            int patt=pattern[j];
            if(patt==1){
                if(arr.get(j)>=arr.get(j+1)) return false;
            }
            else if(patt==0){
                if(!arr.get(j).equals(arr.get(j+1))) return false;
            }
            else{
                if(arr.get(j)<=arr.get(j+1)) return false;
            }
        }
        return true;
    }
    static int countMatchingSubarrays(int[] arr, int[] pattern) {
        int count=0;
        int row=arr.length;
        int m=pattern.length;
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<=m;i++){
            ans.add(arr[i]);
        }
        if(check(ans,m,pattern)) count++;
        System.out.println(count);
        for(int i=m+1;i<row;i++){
            ans.remove(0);
            ans.add(arr[i]);
            if(check(ans,m,pattern)) count++;
        }
        return count;
    }
    static List<List<Integer>> mergeintervals(int[][] arr){
        List<List<Integer>> ans=new ArrayList<>();
        Arrays.sort(arr);
        int n=arr.length;
        for(int i=0;i<n;i++){
            if(ans.isEmpty()||ans.getLast().get(1)<arr[i][1]){
                List<Integer> temp=new ArrayList<>();
                temp.add(arr[i][0]);
                temp.add(arr[i][1]);
                ans.add(temp);
            }
            else{
                int temp=Math.max(arr[i][1],ans.getLast().get(1));
                ans.getLast().add(1,temp);
            }
        }
        return ans;
    }
    static void mergewthoutspace(int [] arr,int []brr){
        int s1=0;
        int end=arr.length;
        while(s1<end){
            if(arr[s1]>brr[0]){
                   int temp=brr[0];
                   brr[0]=arr[s1];
                   arr[s1]=temp;
                   int st=0;
                   while(st<brr.length-1&&brr[st]>brr[st+1]){
                       int temp1=brr[st];
                       brr[st]=brr[st+1];
                       brr[st+1]=temp1;
                       st++;
                   }
            }
            s1++;
        }

    }
    static void mergeoptimised1(int [] arr,int [] brr){
        int left=arr.length-1;
        int right=0;
        while(left>=0&&right<brr.length){
            if(arr[left]>arr[right]){
                int temp=arr[left];
                arr[left]=brr[right];
                brr[right]=temp;
            }
            left--;
            right++;
        }
        Arrays.sort(arr);
        Arrays.sort(brr);
    }
    //wap to optimise merge sort using gap technique;
    static void mergeoptimised2(int [] arr,int [] brr){
        int n=arr.length;
        int m=brr.length;
        int len=n+m;
        int gap= len/2+len%2;
        while(gap>0){
            int left=0;
            int right=left+gap;
            while(right<len){
                if(left<n&&right>=n){
                    if(arr[left]>brr[right-n]){
                        int temp=arr[left];
                        arr[left]=brr[right-n];
                        brr[right-n]=temp;
                    }
                }
                else if(left>=n){
                    if(brr[left-n]>brr[right-n]){
                        int temp=brr[left-n];
                        brr[left-n]=brr[right-n];
                        brr[right-n]=temp;
                    }
                }
                else{
                    if(arr[left]>arr[right]){
                        int temp=arr[left];
                        arr[left]=arr[right];
                        arr[right]=temp;
                    }
                }
                left++;
                right++;
            }
            if(gap==1) break;
            gap=gap/2+gap%2;
        }
    }
    public static void main(String[] args) {
        int [] arr={0,1,3,5};
        int [] arr2={1,2,4,6,8};
        mergeoptimised1(arr,arr2);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }
}
