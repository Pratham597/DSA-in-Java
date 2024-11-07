package sorting;

import java.util.Arrays;

public class revision {
    static void selectionSort(int [] arr){
        int n=arr.length;
        for(int i=0;i<n-1;i++){
            int idx=i;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[idx]) idx=j;
            }
            int temp=arr[i];
            arr[i]=arr[idx];
            arr[idx]=temp;
        }
    }
    static void insertionSort(int [] arr){
        int n=arr.length;
        for(int i=1;i<n;i++){
            int ele=arr[i];
            int j=i;
            while(j>0&&arr[j-1]>ele){
                arr[j]=arr[j-1];
                j--;
            }
            arr[j]=ele;
        }
    }
    static void mergeSort(int [] arr,int st,int end){
        if(st>=end) return ;
        int mid=st+(end-st)/2;
        mergeSort(arr,st,mid);
        mergeSort(arr,mid+1,end);
        merge(arr,st,mid,end);
    }
    static void merge(int [] arr,int st,int mid,int end){
        //n1->length of st:mid
        //n2-> length of mid+1:end;
        //n->arr of length;
        int n1=mid-st+1;
        int n2=end-mid;
        int [] arr1=Arrays.copyOfRange(arr,st,mid+1);
        int [] arr2=Arrays.copyOfRange(arr,mid+1,end+1);
        int idx=st;
        int i=0,j=0;
        while(i<n1&&j<n2){
            if(arr1[i]<arr2[j]) arr[idx++]=arr1[i++];
            else arr[idx++]=arr2[j++];
        }
        while(i<n1){
            arr[idx++]=arr1[i++];
        }
        while(j<n2){
            arr[idx++]=arr2[j++];
        }
    }
    static void bubbleSort(int [] arr){
        //3 4 5 2 1
        //i->4(0,1,2,3)
        int n=arr.length;
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    static void quickSort(int [] arr,int st,int end){
        if(st>=end) return;
        int idx=quick(arr,st,end);
        quickSort(arr,st,idx-1);
        quickSort(arr,idx+1,end);
    }

    static int  quick(int[] arr, int st, int end) {
        int idx=st+(end-st)/2;
        int count=st;//as st can vary with recursion so never instantiate count with zero.
        for(int i=st;i<=end;i++){
            if(arr[idx]>arr[i]) count++;
        }
        //pivot->count;
        int pivot=count;
        //swapping of pivot and actual position of pivot in array;
        int temp=arr[pivot];
        arr[pivot]=arr[idx];
        arr[idx]=temp;
        //ends here

        int i=st;
        int j=end;
        int ele=arr[pivot];
        while(i<j){
            while(i<j&&arr[i]<ele) i++;
            while(i<j&&arr[j]>=ele) j--;
            if(i<j){
                int temp2=arr[i];
                arr[i]=arr[j];
                arr[j]=temp2;
            }
        }
        return pivot;
    }
    static int findMax(int [] arr){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max=Math.max(arr[i],max);
        }
        return max;
    }
    static void countSort(int [] arr){
        int n=arr.length;
        int max=findMax(arr);
        int [] op=new int[max+1];
        //stable algorithm;
        for(int x:arr){
            op[x]++;
        }
        int idx=0;
        //op ready as it is serving frequency array.
        for(int i=1;i<op.length;i++){
            op[i]+=op[i-1];
        }
        //make op as prefix array.
        int [] ans=new int[arr.length];
        for(int i=n-1;i>=0;i--){
            int ele=op[arr[i]]-1;
            ans[ele]=arr[i];
            op[arr[i]]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i]=ans[i];
        }
    }
    static void countRadixSort(int [] arr,int div){
        int [] count=new int[10];
        int n=arr.length;
        for(int i=0;i<n;i++){
            int rem=(arr[i]/div)%10;
            count[rem]++;
        }
        for (int i = 1; i <n ; i++) {
            count[i]+=count[i-1];
        }
        int [] ans=new int[n];//stores answers at its actual position.
        for(int i=n-1;i>=0;i--){
            int ele=count[arr[i]]-1;
            ans[ele]=arr[i];
            count[arr[i]]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i]=ans[i];
        }
    }
    static void radixSort(int [] arr){
        int max=findMax(arr);
        for(int place=1;max/place>0;place*=10){
            countSort(arr);
        }
    }
    public static void main(String[] args) {
        int [] arr={1,2,5,4,3,2,3,6,8,9,1005,20,225,332};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
