package leetcode.array;


public class N5_LongestPalindromicSubstring {
    //最快解法
    //初始化指针为0 用来记录子串的起始index和终止index
    int start = 0, end = 0;
    public String longestPalindromeFast(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] c = s.toCharArray();
        longestPallindromeAt(c, 0);
        return s.substring(start, end + 1);
    }

    private void longestPallindromeAt(char[] charArray, int ptr) {
        int a = ptr;
        int b = ptr;
        int length = charArray.length;   //数组长度
        /*
        退出递归条件
        (length - p) < (end - start + 1) / 2)  当剩余的字母少于最长子串的一半时 退出递归 无需继续计算 因为无法得到更长子串
        p == length - 1  此时已经处理到了数组最后一个元素
        */
        if (ptr == length - 1 || (length - ptr) < (end - start + 1) / 2) {
            return;
        }
        /*
        charArray[b] == charArray[b + 1]判断 在数组内 b指针和下一个元素是不是相等 如果相等 b++，可以得到一个字符全是一样的子串，直到遇到不一样的字符
        因为如果字符都相等 不论如何都是回文字符串
         */
        while (b < length - 1 && charArray[b] == charArray[b + 1]) {
            b++;
        }
        //保存当前指针 下次计算的起始点
        ptr = b;
        /*
        a > 0 && b < length - 1 保证在数组区间内 无越界
        charArray[a - 1] == charArray[b + 1] 往外拓展 直到不是回文字符串
         */
        while (a > 0 && b < length - 1 && charArray[a - 1] == charArray[b + 1]) {
            a--;
            b++;
        }
        //如果这个字符串长度大于之前储存的字符串长度 储存这个字符串索引
        if (b - a > end - start) {
            end = b;
            start = a;
        }
        /*
        下次计算的起始索引是 之前判断的同字符组成的字符串最后一个位置的下一个
        从这里开始是因为在刚开始进入的时候的a b区间内不会再有大于这次计算得到的字符串了
         */
        longestPallindromeAt(charArray, ptr + 1);
    }


    //官方解法
    public String longestPalindromeOfficial(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    //暴力破解方法
    public String longestPalindromeBruteforce(String str) {
        if(str.length()==0||str.length()==1) return str;
        LongestPalindromicSubstringSolutionBruteforce solution = new LongestPalindromicSubstringSolutionBruteforce();
        return solution.solution(str);
    }
    static class LongestPalindromicSubstringSolutionBruteforce {
        static char[] arr;
        public String solution(String str) {
            this.arr = str.toCharArray();
            int[] solution = new int[2];
            int max = 0;
            for (int i = 0; i < arr.length-max+1; i++) {
                for (int j = arr.length-1; j >= i+max; j--) {
                    //如果是
                    if(validatePalindromicSubstring(i,j)){
                        if(j-i+1 > max) {
                            max = j-i+1;
                            solution[0] = i;
                            solution[1] = j;
                        }
                    }
                }
            }
            if(solution[1]==arr.length-1) return str.substring(solution[0]);
            return str.substring(solution[0],solution[1]+1);
        }
        //根据静态字符数组的下标进行[start, end]区间对比 判断是不是
        public boolean validatePalindromicSubstring(int start, int end) {
            for (int i = start, j = end; i < j; i++, j--) {
                if (arr[i] != arr[j]) return false;
            }
            return true;
        }
    }
}
