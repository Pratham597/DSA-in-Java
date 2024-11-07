package binarySearch;


import java.util.*;

public class problems1 {
    //wap to implement binary search.
    static void implementbs(int [] arr,int x){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==x){
                System.out.println(mid);
                break;
            }
            else if(arr[mid]<x){
                st=mid+1;
            }
            else{
                end=mid-1;
            }
        }
    }
    static void recursivebs(int[] arr,int st,int end,int x){
        if(st>end) return;
        int mid=st+(end-st)/2;
        if(arr[mid]==x) {
            System.out.println(mid);
            return;
        }
        else if(arr[mid]>x){
            recursivebs(arr,st,mid-1,x);
        }
        else recursivebs(arr,mid+1,end,x);
    }
    static int lowerbound(int [] arr,int x){
        int st=0;
        int end=arr.length-1;
        int ele=arr.length;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]<x) st=mid+1;
            else{
                end=mid-1;
                ele=mid;
            }
        }
        return ele;
    }
    //wap to find the last and last occurence of given number in the array:
    //return -1 if element does not exist.
    static int firstOccurence(int [] arr,int x){
        int st=0;
        int ans=-1;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==x){
                ans=mid;
                end=mid-1;
            }
            else if(arr[mid]<x) st=mid+1;
            else end=mid-1;
        }
        return ans;
    }
    static int lastOccurence(int []arr,int x){
        int st=0;
        int ans=-1;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==x){
                ans=mid;
                st=mid+1;
            }
            else if(arr[mid]<x) st=mid+1;
            else end=mid-1;
        }
        return ans;
    }
    static int upperbound(int [] arr,int x){
        int st=0;
        int end=arr.length-1;
        int ele=arr.length;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]<=x) st=mid+1;
            else{
                end=mid-1;
                ele=mid;
            }
        }
        return ele;
    }
    static void searchandinsert(ArrayList<Integer> ans,int x){
        int st=0;
        int end=ans.size()-1;
        int idx=end+1;
        while(st<=end){
            int mid=st+(end-st)/2;
            int ele=ans.get(mid);
            if(ele==x) {
                return;
            }
            else if(ele<x) st=mid+1;
            else{
                idx=mid;
                end=mid-1;
            }
        }
        ans.add(idx,x);

    }
    static int ceilbs(int [] arr,int x){
        int st=0;
        int ans=arr.length;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]>=x){
                ans=mid;
                end=mid-1;
            }
            else st=mid+1;
        }
        return ans;
    }
    static int floorbs(int [] arr,int x){
        int st=0;
        int ans=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]>x) end=mid-1;
            else {
                ans=mid;
                st=mid+1;
            }
        }
        return ans;
    }
    static int count(int[] arr,int x){
        int idx=firstOccurence(arr,x);
        if(idx==-1) return 0;
        return lastOccurence(arr,x)-idx+1;
    }
    static int searchInRotatedArray(int[] arr,int x){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==x){
                return mid;
            }
            else if(arr[mid]<arr[end]){
                if(x>arr[mid]&&x<=arr[end]) st=mid+1;
                else end=mid-1;
            }
            else{
                if(x>=arr[st]&&x<arr[mid]) end=mid-1;
                else st=mid+1;
            }
        }
        return -1;
    }
    static int searchInRotatedDuplicates(int[] arr,int x){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==x){
                return mid;
            }
            else if(arr[st]==arr[end]&&arr[st]==arr[mid]){
                st++;
                end--;
            }
            else if(arr[mid]<arr[end]){
                if(x>arr[mid]&&x<=arr[end]) st=mid+1;
                else end=mid-1;
            }
            else{
                if(x>=arr[st]&&x<arr[mid]) end=mid-1;
                else st=mid+1;
            }
        }
        return -1;
    }
    static int minimumInRotatedSortedArray(int [] arr){
         int st=0;
         int end=arr.length-1;
         int ans=arr[0];
         while(st<=end){
             int mid=st+(end-st)/2;
             if(arr[mid]>=arr[0]) st=mid+1;
             else {
                 ans=arr[mid];
                 end=mid-1;
             }
         }
         return ans;
    }
    static int rotatedtimes(int [] arr){
        int st=0;
        int end=arr.length-1;
        int ans=0;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]>=arr[0]) st=mid+1;
            else {
                ans=mid;
                end=mid-1;
            }
        }
        return ans;
    }
    static int singleNonDuplicate(int [] arr){
        int st=0;
        int n=arr.length;
        int end=n-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(mid>0&&arr[mid]==arr[mid-1]){
                if(mid%2!=0) st=mid+1;
                else end=mid-2;
            }
            else if(mid<n-1&&arr[mid]==arr[mid+1]){
                if(mid%2==0)st=mid+2;
                else end=mid-1;
            }
            else return arr[mid];
        }
        return -1;
    }
    static int findpeakElement(int[] arr){
        int st=0;
        int end=arr.length-1;
        int ans=arr[0];
        while(st<=end){
            int mid=st+(end-st)/2;
            if(mid==0&&arr[mid]>=Integer.MIN_VALUE||mid>0&&arr[mid]>arr[mid-1]){
                ans=arr[mid];
                st=mid+1;
            }
            else end=mid-1;
//            if(mid<arr.length-1&&arr[mid]>arr[mid+1]){
//                ans=arr[mid];
//                end=mid-1;
//            }
//            else st=mid+1;
        }
        return ans;
    }
    static int nthroot(int m,int n){
        //binary search approach
        int st=1;
        int end=m;
        while(st<=end){
            int mid=st+(end-st)/2;
            long check=(long)Math.pow(mid,n);
            if(m==check) return mid;
            else if(check>m) end=mid-1;
            else st=mid+1;
        }
        return -1;
        //Linear search approach on x=3;
//        int ans=-1;
//        for(int i=0;i<x;i++){
//            if(i*i*i<x) continue;
//            if(i*i*i==x) ans=i;
//            else break;
//        }
//        return ans;
    }
    static int squareroot(int x){
        int st=1;
        int ans=1;
        int end=x;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(mid*mid<=x){
                ans=mid;
                st=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }
    static boolean possibleKoko(int [] arr,int hours,int mid){
        long count=0;
        int idx=0;
        while(idx!=arr.length) {
            count+=Math.ceilDiv(arr[idx],mid);
            idx++;
        }
        if(count<=hours) return true;
        return false;
    }
    static int kokoEatingBananas(int [] arr,int hours){
        int st=1;
        int end=(int) 1e9;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possibleKoko(arr,hours,mid)) {
                end=mid-1;
            }
            else st=mid+1;
        }
        return st;
    }
    static boolean bs(int [] arr,int x){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==x) return true;
            else if(arr[mid]>x) end=mid-1;
            else st=mid+1;
        }
        return false;
    }
    static int  boquets(int [] arr,int m,int k){
        int min=Integer.MAX_VALUE;
        int ans=-1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
            min= Math.min(min,arr[i]);
        }
        int st=min;
        int end=max;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possibleboquets(arr,m,k,mid)){
                ans=mid;
                end=mid-1;
            }
            else st=mid+1;
        }
        return ans;
    }
    static boolean possibleboquets(int[] arr, int m, int k, int mid) {
        //m->no. of boquets;
        //k->no.of adjacent flowers;
        int count=0;
        int cnt=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<=mid) cnt++;
            else{
                count+=cnt/k;
                cnt=0;
            }
        }
        count+=cnt/k;
        return count>=m;
    }
    static int smallestDivisor(int [] arr,int k){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
        }
        int st=1,end=max;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possibleDivisor(arr,k,mid)) end=mid-1;
            else st=mid+1;
        }
        return st;
    }

    static boolean possibleDivisor(int[] arr, int k, int mid) {
        int cnt=0;
        for(int i=0;i<arr.length;i++){
            cnt+=Math.ceilDiv(arr[i],mid);
        }
        return cnt<=k;
    }
    static int shipPackages(int [] arr,int days){
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            max=Math.max(arr[i],max);
        }
        int st=max;
        int end=sum;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possiblePackages(arr,days,mid)) end=mid-1;
            else st=mid+1;
        }
        return st;
    }

    static boolean possiblePackages(int[] arr, int days, int mid) {
        int sum=0;
        int count=1;
        for(int i=0;i<arr.length;i++){
            if(arr[i]+sum<=mid) sum+=arr[i];
            else{
                count++;
                sum=arr[i];
            }
        }
        return count<=days;
    }
    //wap to find the kth missing number in sorted arrays.
    static int kthmissingnumbers(int[] arr,int k){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]-1-mid<k) st=mid+1;
            else end=mid-1;
        }
        return k+1+end;//end always pointing to number from which we have to add;
    }
    static int aggressivecows(int [] arr,int cows){
        Arrays.sort(arr);
        int n=arr.length;
        int st=1;
        int end=arr[n-1]-arr[0];
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possibleCows(arr,cows,mid)) st=mid+1;
            else end=mid-1;
        }
        return end;
    }
    static int bookAllocation(int [] arr,int k){
        if(arr.length<k) return -1;
        int sum=0;
        int min=Integer.MIN_VALUE;
        int n=arr.length;
        for(int i=0;i<n;i++){
            min=Math.max(min,arr[i]);
            sum+=arr[i];
        }
        int st=min,end=sum;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(possibleBooks(arr,k,mid)) end=mid-1;
            else st=mid+1;
        }
        return st;
    }

    static boolean possibleBooks(int[] arr, int k, int mid) {
        int sum=mid;
        int count=1;
        for(int i=0;i<arr.length;i++){
            if(sum-arr[i]>=0) sum=sum-arr[i];
            else{
                sum=mid-arr[i];
                count++;
            }
        }
        return count<=k;
    }

    static boolean possibleCows(int[] arr, int cows, int mid) {
        int count=1;
        int prev=arr[0];
        int n=arr.length;
        for(int i=1;i<n;i++){
            if(arr[i]-prev>=mid){
                count++;
                prev=arr[i];
            }
            if(count==cows) break;
        }
        return count>=cows;
    }
    static boolean helpertoday(Stack<Integer> st){
        if(st.isEmpty()) return true;
        int x=st.pop();
        int y=st.pop();
        boolean check=Math.abs(x-y)==1;
        boolean check2=helpertoday(st);
        st.push(y);
        st.push(x);
        return check&&check2;
    }
    static boolean checkconsecutive(Stack<Integer> st){
        if(!oddeven(st)) {
            int x = st.pop();
            boolean check=helpertoday(st);
            st.push(x);
            return check;
        }
        return helpertoday(st);
    }
    static boolean oddeven(Stack<Integer> st){
        if(st.isEmpty()) return true;
        int x=st.pop();
        boolean check=oddeven(st);
        st.push(x);
        return !check;
    }
    public static void main(String[] args) {
        Stack<Integer> st=new Stack<>();
        st.push(2);
        st.push(3);
        st.push(88);
        st.push(89);
        st.push(1);
        System.out.println(checkconsecutive(st));
    }
}
