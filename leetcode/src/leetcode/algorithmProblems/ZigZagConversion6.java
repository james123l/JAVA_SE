package leetcode.algorithmProblems;

public class ZigZagConversion6 {
    public static void main(String[] args) {
        ZigZagConversion6 zig = new ZigZagConversion6();
        System.out.println(zig.convert("PAYPALISHIRING", 4));
    }
    /*
    * 快速算法 对比 算法一样 代码方式更佳.
    * */
    public String convert(String s, int numRows) {
        // T O(n) S O(1)
        int length = s.length();
        if (numRows > length || numRows <= 1) {
            return s;
        }

        char[] zigZagChars = new char[length];
        int count = 0;
        //跳跃长度
        int interval = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            int step = interval - 2 * i;
            for (int j = i; j < length; j+= interval) {
                zigZagChars[count] = s.charAt(j);
                count++;
                if (step > 0 && step < interval && j + step < length) {
                    zigZagChars[count] = s.charAt(j + step);
                    count++;
                }
            }
        }
        return new String(zigZagChars);
    }

    /*
    暴力破解
    分配char[n][string.length] 然后逐行输出
     */
    public String convertBruteForce(String str, int rows) {
        if(rows == 1||str.length()<rows) return str;
        int temp;
        char[][] solution =new char[rows][str.length()];
        //记录各行当前长度
        int[] lengths = new int[rows];
        for (int i = 0; i < rows; i++) {
            temp = i;
            while(temp-2*i<str.length()) {
                //回溯是第i行回溯2i ,先加回溯再加自己
                if(i!=0&&i!=rows-1&&temp-2*i>0){
                    //如果这个下标不越界 则加入
//                    System.out.println(temp);
                    solution[i][lengths[i]]= str.charAt(temp-2*i);
                    lengths[i]++;
                }
                if(temp>=str.length()) break;
                solution[i][lengths[i]] = str.charAt(temp);
                lengths[i]++;
                //跳跃间隔是 (rows-1)*2
                temp += (rows-1)*2;
            }
//            System.out.println(solution[i]);
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lengths[i] ; j++) {
                buffer.append(solution[i][j]);
            }
        }
        return buffer.toString();
    }
}
