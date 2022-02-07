public class LongestPalindromic {

    public static void main(String[] args) {
        LongestPalindromic test = new LongestPalindromic();
        String str = test.solution("babad");
        System.out.println(str);
    }


    public String solution(String s){
        if ((null == s) || s.length()==0){return "";}
        String longest = "";
        int len = s.length();
        for (int i = 0; i < len; i++) {
            String l1 = getLongestStr(s, i, i);
            longest = l1.length()>longest.length()? l1: longest;
            String l2 = getLongestStr(s, i, i + 1);
            longest = l2.length()>longest.length()? l2: longest;
        }


        return longest;
    }

    private String getLongestStr(String s, int i, int j) {
        if(j > s.length() - 1){return "";}
        while( i >= 0 && j <= s.length() - 1){
            if (s.charAt(i)==s.charAt(j)){
                i--;
                j++;
            }else {
                break;
            }
        }
        return s.substring( i+1, j);
    }


}
