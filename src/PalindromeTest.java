public class PalindromeTest {

    public boolean isPalindrome(int x){
        if(x < 0) return false;
        String num = String.valueOf(x);
        char[] chars = num.toCharArray();

        int start = 0, end = num.length() - 1;

        while(start<=end){
            if (chars[start] != chars[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


}
