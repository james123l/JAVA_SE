package algorithm.dynamicProgramming.KMP;

public class KMP {
    //kmp模式匹配字符串
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(kmpSearch(str1, str2));
    }
    /*
    * kmp是一种动态规划 多次使用到了j = next[j-1];不断使用之前得到的结论，让新的结论和旧的相互关联
    * */
    public static int kmpSearch(String str1, String str2) {
        if(str2.length()==0) return -1;
        int[] next = getNextVal(str2);
        //i记录str1的位置，j记录str2的位置
        //kmp算法 对于str1是不需要回溯的 所以i++
        for(int i = 0, j = 0; i < str1.length(); i++) {
            //两个字符不相等 通过next数组找到str2的指针应该跳转到的对应下标
            while( j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }
            //单个字符匹配成功，进行下次循环对比下个字符
            if(str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            //此时全部都被匹配
            if(j == str2.length()) {
                return i - j + 1;
            }
        }
        return  -1;
    }
    //获取部分匹配表next的方式一
    public static int[] getNext(String str){
        int[] next = new int[str.length()];
        //
        next[0] = 0;
        //双指针为next数组赋值，j在前，i在后面
        for(int i = 1, j = 0; i < str.length(); i++) {
            //如果next[i]！=next[j],就找到j下标对应的回溯位置
            while(j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j-1];
            }
            //如果next[i]==next[j] 那么当i不匹配的时候 应该指向j的下一位
            if(str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    //获取nextVal数组的方式
    public static int[] getNextVal(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        //双指针为next数组赋值，j在前，i在后面
        for(int i = 1, j = 0; i < str.length(); i++) {
            //如果str.charAt(i) != str.charAt(j),没有匹配上，就找到j下标对应的回溯位置
            while(j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j-1];
            }
            //如果next[i]==next[j] 那么当i不匹配的时候 应该指向j的下一位
            if(str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    //nextVal数组求法
//    public static int[] getNextVal(String str) {
//        int[] next = new int[str.length()];
//        next[0] = 0;
//        int k = 0;     //标记str的下标
//        int j = 1;      //标记str的下标
//        while (j < str.length() - 1) {
//            //str.charAt(k)表示前缀，str.charAt(j)表示后缀
//            if (k == 0 || str.charAt(j) == str.charAt(k)) {
//                ++j;
//                ++k;
//                if (str.charAt(j) != str.charAt(k))
//                    next[j] = k;
//                else
//                    //因为不能出现str.charAt(j) = str.charAt(next[j])，所以当出现时需要继续递归，k = next[k] = next[next[k]]
//                    next[j] = next[k];
//            }
//            else {
//                k = next[k];
//            }
//        }
//        for (int i:
//             next) {
//            System.out.println(i);
//        }
//        return next;
//    }
}
