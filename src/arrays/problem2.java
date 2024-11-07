package arrays;

import java.util.*;

public class problem2 {
    static long gcd(long n1,long n2){
        if(n2==0) return n1;
        return gcd(n2,n1%n2);
    }
    static void repeating2(int[] arr,int n){
        long sn=(n*(n+1))/2;
        long s2n=(n*(n+1)*(2*n+1))/6;
        long s=0,s2=0;
        for(int i=0;i<n;i++){
            s+=arr[i];
            s2+=(long)arr[i]*(long)arr[i];
        }
        long diff=s-sn;
        long add=(s2-s2n)/diff;
        long repeat=(diff+add)/2;
        long missing=repeat-diff;
        System.out.println(repeat+" "+missing);
    }
    static int  countinversion(int[] arr){
        int n=arr.length;
        int count=0;
        for(int i=n-2;i>=0;i--){
            int j=i;
            while(j<n-1&&arr[j]<arr[j+1]){
                int temp=arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;
                j++;
            }
            count+=n-1-j;
        }
        System.out.println(Arrays.toString(arr));
        return count;
    }
    static int mergesort(int[] arr,int st,int end){
        if(st>=end) return 0;
        int count=0;
        int mid=st+(end-st)/2;
        count+=mergesort(arr,st,mid);
        count+=mergesort(arr,mid+1,end);
        count+=merge(arr,st,mid,end);
        return count;
    }

    static int merge(int[] arr, int st, int mid, int end) {
        int [] left=Arrays.copyOfRange(arr,st,mid+1);
        int[] right=Arrays.copyOfRange(arr,mid+1,end+1);
        int l1=0,r1=0;
        int count=0,a1=st;
        while(l1<left.length&&r1<right.length){
            if(left[l1]<right[r1]){
                arr[a1++]=left[l1++];
            }
            else{
                count+=left.length-l1;
                arr[a1++]=right[r1++];
            }
        }
        while(l1<left.length){
            arr[a1++]=left[l1++];
        }
        while(r1<right.length){
            arr[a1++]=right[r1++];
        }
        return count;
    }
    static int merge2(int[] arr, int st, int mid, int end) {
        int [] left=Arrays.copyOfRange(arr,st,mid+1);
        int[] right=Arrays.copyOfRange(arr,mid+1,end+1);
        int l1=0,r1=0;
        int count=0,a1=st;
        int rc=0;
        while(l1<left.length&&r1<right.length){
            if(left[l1]<right[r1]){
                arr[a1++]=left[l1++];
            }
            else{
                while(rc<left.length&&left[rc]<=2*right[r1]){
                    rc++;
                }
                count+=left.length-rc;
                arr[a1++]=right[r1++];
            }
        }
        while(l1<left.length){
            arr[a1++]=left[l1++];
        }
        while(r1<right.length){
            arr[a1++]=right[r1++];
        }
        return count;
    }
    static int mergesort2(int[] arr,int st,int end){
        if(st>=end) return 0;
        int count=0;
        int mid=st+(end-st)/2;
        count+=mergesort2(arr,st,mid);
        count+=mergesort2(arr,mid+1,end);
        count+=merge2(arr,st,mid,end);
        return count;
    }

    static int reversepair(int[] arr){
        return mergesort2( arr,0,arr.length-1);
    }
    static int countinversionoptimised(int[] arr){
        return mergesort(arr,0,arr.length-1);
    }
//    static long maxproductsubarray(int [] arr){
//
//    }
    static void arrangequeue(int n,Queue<Integer> q){
        Stack<Integer> st=new Stack<>();
        int check=n;
        int flag=0;
        while(!q.isEmpty()){
            if(q.peek()==check){
                st.push(q.poll());
                check--;
                flag=0;
            }
            else{
                flag++;
                if(flag==q.size()) {
                    System.out.println(check+" is missing");
                    return;
                }
                q.add(q.poll());
            }
        }
        while(!st.isEmpty()){
            q.add(st.pop());
        }
    }
    static boolean checksortedqueue(Queue<Integer>q,Queue<Integer> temp,Stack<Integer>st,int last){
        if(q.isEmpty()&& st.isEmpty()) return true;
        if(q.isEmpty()&&!st.isEmpty()) return false;
        if(q.peek()-last==1) {
            last = q.peek();
            temp.add(q.poll());
        }
        else{
            st.push(q.poll());
        }
        while(!st.isEmpty()&&st.peek()-last==1){
            last=st.peek();
            temp.add(st.pop());
        }
        return checksortedqueue(q,temp,st,last);
    }
    static int  towerofhanoi(int n,String src,String helper,String des){
        if(n==0) return 0;
        int count=0;
        count+=towerofhanoi(n-1,src,des,helper);
        System.out.println(n+" Transfer from"+src+"to"+des);
        count++;
        count+=towerofhanoi(n-1,helper,src,des);
        return count;
    }
    static int maxproductsubarray(int[] arr){
        int n=arr.length;
        int i=0;
        int max=Integer.MIN_VALUE;
        int ans=1,ans2=1;
        while(i<n&&arr[i]>=0){
            if(arr[i]==0){
                ans=1;
                i++;
                max=Math.max(0,max);
                continue;
            }
            ans*=arr[i];
            max=Math.max(ans,max);
            i++;
        }
        if(i<n)ans*=arr[i];
        for(int j=i+1;j<n;j++){
            if(arr[j]==0){
                ans=1;
                while(j<n&&arr[j]>=0){
                    if(arr[j]==0){
                        ans=1;
                        max=Math.max(0,max);
                        j++;
                        continue;
                    }
                    ans*=arr[j];
                    max=Math.max(ans,max);
                    j++;
                }
                if(j<n)ans*=arr[j];
                ans2=1;
            }
            else{
                ans2*=arr[j];
                ans*=arr[j];
                max=Math.max(max,Math.max(ans,ans2));
            }
        }
        return max;
    }
    static int maxsubarrayproduct2(int [] arr){
        int prefix=1;
        int n=arr.length;
        int suffix=1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            prefix*=arr[i];
            suffix*=arr[n-i-1];
            max=Math.max(max,Math.max(prefix,suffix));
            if(prefix==0) prefix=1;
            if(suffix==0) suffix=1;
        }
        return max;
    }
    public static void main(String[] args) {
        int n=64;
        int k=0;
        while(n>1){
            n/=2;
            k++;
        }
        System.out.println(k);
    }
}
