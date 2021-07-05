package leetcode.array;

public class N9_PalindromeInteger {
    //string解法
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x==0) return true;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(x));
        StringBuffer reverse = new StringBuffer(String.valueOf(x)).reverse();
        System.out.println(reverse);
        System.out.println(stringBuffer);
        if(reverse.charAt(0)=='0') {return false;}
        //stringbuffer没有重载equals
        return stringBuffer.toString().equals(reverse.toString());
    }
    public boolean isPalindromeAllInOne(int x) {
        return x==0?true:(x<0||new StringBuffer(String.valueOf(x)).reverse().charAt(0)=='0')?false: String.valueOf(x).equals(new StringBuffer(String.valueOf(x)).reverse().toString());
    }

    //数学解法：
    public boolean isPalindromeMath(int x){
        char[] chars = String.valueOf(x).toCharArray();
        if(x<0) return false;
        if(x==0) return true;
        if(chars[chars.length-1]==0) return false;
        int front = chars.length/2-1;
        int back = chars.length%2==0?front+1:front+2;
        while(front!=-1){
            if(chars[front]!=chars[back]) return false;
            front--;
            back++;
        }
        return true;
    }

    public static void main(String[] args) {

        N9_PalindromeInteger n9PalindromeInteger091 = new N9_PalindromeInteger();
        System.out.println(n9PalindromeInteger091.isPalindromeMath(1234567899));
        System.out.println(n9PalindromeInteger091.isPalindromeMath(1221));
        System.out.println(n9PalindromeInteger091.isPalindromeMath(123));
        System.out.println(n9PalindromeInteger091.isPalindromeMath(-121));
        System.out.println(n9PalindromeInteger091.isPalindromeMath(1234567890));
        System.out.println(n9PalindromeInteger091.isPalindromeMath(0));
    }

}
