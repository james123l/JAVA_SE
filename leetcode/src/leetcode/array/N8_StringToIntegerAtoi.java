package leetcode.array;

public class N8_StringToIntegerAtoi {
    public static void main(String[] args) {
        System.out.println(new N8_StringToIntegerAtoi().myAtoi("+1")+"result");
        System.out.println(new N8_StringToIntegerAtoi().myAtoi("4193 with words")+"result");
    }
    /*
    需求： 可以读取并且抛空格 判断正负数 读取数字到第一个字母或字符串结束 如果大于或者小于2^31 返回int最大或最小值
     */
    public int myAtoiBruteForce(String str) {
        //去掉空格 之后第一个一定是 - + 或者数字. ascii: - 45，+ 43 ， num = char - 48, char>=48 char<=57
        char[] arr = str.trim().toCharArray();
        if(arr.length==0) return 0;
        StringBuilder buffer = new StringBuilder();
        //flag: 2 负数, 1 第一个是+, 0 第一个是数字或其他字符
        int flag = arr[0]=='-'?2:arr[0]=='+'?1:0;
        int i,solution;
        for (i = flag==0?0:1; i < arr.length; i++) {
            //先判断是不是数字 如果不是 break
            if((int)arr[i]>57 || (int)arr[i]<48) break;
            buffer.append(arr[i]);
            System.out.println(arr[i]);
        }
        if(buffer.length()==0) return 0;
        try {
            if(flag == 0|| flag == 1){
                solution =Integer.parseInt(buffer.toString());
            }else{
                solution = 0 - Integer.parseInt(buffer.toString());
            }
            return solution;
        } catch (NumberFormatException e) {
            if(flag == 0|| flag == 1){
                return Integer.MAX_VALUE;
            }else{
                return Integer.MIN_VALUE;
            }
        }
    }

    //要想提升性能 少用string
    public int myAtoi(String str) {
        if (str.isEmpty()) return 0;
        int sign = 1, base = 0, i = 0, n = str.length();
        while (i < n && str.charAt(i) == ' ') ++i;      //去空格
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = (str.charAt(i++) == '+') ? 1 : -1;   //判断正负
        }
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }

}
