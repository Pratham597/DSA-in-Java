import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mathematics {
    public static int [] f(int n){
        int[] ans=new int[n+1];
        for(int i=0;i<=n;i++){
            ans[i]=i;
        }
        for(int i=2;i*i<=n;i++){
            if(ans[i]==i){
                for(int j=i*i;j<=n;j+=i){
                    if(ans[j]==j) ans[j]=i;
                }
            }
        }
        return ans;
    }
    public static void querySmallestPrime(){
        Scanner sc=new Scanner(System.in);
        int query=sc.nextInt();
        int [] prime=f(1000);
        for(int i=0;i<query;i++){
            int num=sc.nextInt();
            System.out.println(prime[num]);
        }
        sc.close();
    }
    public static void main(String[] args) {
        querySmallestPrime();
    }
}
