package Strings;


public class KMP {

    // It helps to construct LPS.
    static void constructLps(String pat, int[] lps) {
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) { len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // This function helps me to count the no of occurrences of pattern in given string text.

    static int count(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();

        int[] lps = new int[m];
        constructLps(pat, lps);
        int i = 0;
        int j = 0;
        int count=0;
        while (i < n) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    count++;
                    j = lps[j - 1];
                }
            } else {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s="ababcdabc";
        String t="bc";
        System.out.println(count(t,s));
    }
}

