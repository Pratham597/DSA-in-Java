package Linkedlist;

public class practice {
    static float squareRoot(float k){
        float st=0.0f;
        float end=k;
        float mid=0.0f;
        while(st<=end){
            mid=st+(end-st)/2;
            float n=mid*mid;
            if(n==k) return mid;
            else if(n>k) end=mid-0.000001f;
            else st=mid+0.000001f;
        }
        return mid;
    }
    static int binaryMultipleIdx(int [] arr,int k){
        int st=0;
        int ans=-1;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==k){
                ans=mid;
                System.out.print(ans+" ");
                st=mid+1;
            }
            else if(k<arr[mid]) end=mid-1;
            else st=mid+1;
        }
        return ans;
    }
    static boolean binarySearch(int [] arr , int k){
        int st=0;
        int end=arr.length-1;
        while(st<end){
            int mid=st+(end-st)/2;
            if(arr[mid]==k) return true;
            else if(k<arr[mid]) end=mid-1;
            else st=mid+1;
        }
        return false;
    }
    static boolean binaryRec(int x,int [] arr,int st,int end){
        if(st>end) return false;
        int mid=st+(end-st)/2;
        if(arr[mid]==x) return true;
        else if(arr[mid]>x) return binaryRec(x,arr,st,mid-1);
        else return binaryRec(x,arr,mid+1,end);
    }
    static linkedlist1.node merge(linkedlist1.node h1,linkedlist1.node h2){
        linkedlist1.node temp=h1;
        linkedlist1.node temp2=h2;
        linkedlist1.node temp3=new linkedlist1.node(-1);
        linkedlist1.node h=temp3;
        while(temp!=null&&temp2!=null){
            if(temp.data<temp2.data){
                temp3.next=temp;
                temp=temp.next;
                temp3=temp3.next;
            }
            else{
                temp3.next=temp2;
                temp2=temp2.next;
                temp3=temp3.next;
            }
        }
        if(temp!=null){
            temp3.next=temp;
        }
        else{
            temp3.next=temp2;
        }
        return h.next;
    }
    // khatarnak approach using pratham's mind- mergesort approach:
    static linkedlist1.node mergeSort(linkedlist1.node head,linkedlist1.node tail){
        if(head==tail) {
            head.next=null;
            return head;
        }
        linkedlist1.node slow=head;
        linkedlist1.node fast=head;
        while(fast!=tail&&fast.next!=tail){
            slow=slow.next;
            fast=fast.next.next;
        }
        linkedlist1.node t3=slow.next;
        linkedlist1.node t1=mergeSort(head,slow);
        linkedlist1.node t2=mergeSort(t3,tail);
        return merge(t1,t2);
    }
    // brute force through recursion
    static linkedlist1.node sorted(linkedlist1.node head){//3 1 4 2
        if(head.next==null||head==null) return head;
        linkedlist1.node ans=sorted(head.next);//1 2 4
        linkedlist1.node temp=head;//3
        if(temp.data<=ans.data) {
            temp.next=ans;
            return temp;
        }
        else{
            linkedlist1.node t=ans;//1
            while(ans.next!=null&&temp.data>ans.next.data){
                ans=ans.next;
            }
            temp.next=ans.next;
            ans.next=temp;
            return t;//1 2 3 4
        }
    }
    static int findPeakIdx(int [] arr){
        int st=0,end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(mid==0||arr[mid]>arr[mid-1]&&mid==arr.length-1||arr[mid]>arr[mid+1]){
                return mid;
            }
            else if(arr[mid]<=arr[mid-1]) end=mid-1;
            else st=mid+1;
        }
        return -1;
    }
//    static int chocolateBoxes(int [] arr,int m){
//        int st=arr[0],end=arr[0];
//        for(int i=1;i<arr.length;i++){
//            if(st>arr[i])  st=arr[i];
//            end+=arr[i];
//        }
//        int ans=-1;
//        while(st<=end){
//            int mid=st+(st-end)/2;
//            if(divisionpossible(arr,m,mid)){
//                ans=mid;
//                end=mid-1;
//            }
//            st=mid+1;
//        }
//        return ans;
//    }
    public static void main(String[] args) {
       int []arr={1,2,3,4,5,6,9,8};
        System.out.println(findPeakIdx(arr));
    }
    //wap to find peak element in array using BS.
    static int findingPeakEle(int [] arr){
        int st=0,end=arr.length-1;
        int ans=0;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(mid>0&&arr[mid]>=arr[mid-1]) {
                ans=arr[mid];
                st=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }
    static int binarysearchduplicacy(int [] arr,int k){
        int st=0,end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]==k) return mid;
            else if(arr[mid]<=arr[end]){
                if(k>arr[mid]&&k<=arr[end]) st=mid+1;
                else end=mid-1;
            }
            else if(arr[st]<arr[mid]){
                if(k>=arr[st]&&k<arr[mid]) end=mid-1;
                else st=mid+1;
            }
            else{
                st++;
                end--;
            }
        }
        return -1;
    }
    static boolean binarySearch2DII(int[][] arr ,int k){
        int row=arr.length;
        int r=0,c=arr[0].length-1;
        while(r<row&&c>=0){
            if(arr[r][c]==k) return true;
            else if(k>arr[r][c]) r++;
            else c--;
        }
        return false;
    }
    static boolean binarySearch2D(int[][] arr,int k){
        int st=0;
        int n=arr.length;
        int m=arr[0].length;
        int end=n*m-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            int x=arr[mid/m][mid%m];
            if(x==k) return true;
            else if(k<x) end=mid-1;
            else st=mid+1;
        }
        return false;
    }
    static boolean search2D(int[][] arr,int k){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]==k) return true;
            }
        }
        return false;
    }
    static int  binarySearchRotSortedArray(int[] arr,int k){
        int st=0;
        int end=arr.length-1;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(k==arr[mid]) return mid;
            else if(arr[st]<=arr[mid]){
                if(k>=arr[st]&&k<arr[mid]) end=mid-1;
                else st=mid+1;
            }
            else{
                if(k>arr[mid]&&k<=arr[end]) st=mid+1;
                else end=mid-1;
            }
        }
        return -1;
    }
    static int binaryRotSortedArray(int[] arr){
        int st=0;
        int end=arr.length-1;
        int idx=0;
        while(st<end){
            int mid=st+(end-st)/2;
            if(arr[st]<arr[mid]) st=mid;
            else if(arr[st]>arr[mid]){
                idx=mid;
                end=mid-1;
            }
            else st=mid+1;
        }
        return idx;
    }
}
