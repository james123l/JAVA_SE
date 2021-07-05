package leetcode.solving;

import java.io.PrintStream;

public class N43MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("9", "9"));
        System.out.println(multiply("123", "456"));
    }

    //最快方法
    public static String multiply(String num1, String num2) {
        int[] n1 = getIntArray(num1);
        int[] n2 = getIntArray(num2);
        int[] result = new int[num1.length() + num2.length() - 1];
        for (int x = n1.length - 1; x >= 0; x--) {
            for (int y = n2.length - 1; y >= 0; y--) {
                int i = n1.length + n2.length - x - y - 2;
                result[i] = result[i] + n1[x] * n2[y];
            }
        }
        if (result[result.length - 1] == 0)
            return "0";
        int[] finalResult = new int[result.length + 1];
        for (int x = 0; x < result.length; x++) {
            if (x < result.length - 1) {
                finalResult[x] = result[x] % 10;
                result[x + 1] = result[x + 1] + result[x] / 10;
            }
            else if (result[x] != 0) {
                finalResult[x] = result[x] % 10;
                if (result.length != finalResult.length) {
                    finalResult[x + 1] = result[x] / 10;
                }
            }
        }
        char[] superFinalResult = new char[finalResult.length + (finalResult[finalResult.length - 1] == 0 ? -1 : 0)];
        boolean zeroLeading = true;
        for (int x = finalResult.length - 1, y = 0; x >= 0; x--) {
            if (finalResult[x] == 0 && zeroLeading) {
                continue;
            }
            superFinalResult[y++] = (char) (finalResult[x] + '0');
            zeroLeading = false;
        }
        return new String(superFinalResult);
    }

    private static int[] getIntArray(String str) {
        char[] oldArray = str.toCharArray();
        int[] newArray = new int[oldArray.length];
        for (int x = 0; x < newArray.length; x++) {
            newArray[x] = oldArray[x] - '0';
        }
        return newArray;
    }

    //我的较慢方法
    private static int[] num01;
    private static int[] solution ;
    public static String multiplyMy(String num1, String num2) {
        if(num1.equals("0")||num2.equals("0")) return "0";
        num01 = toIntArray(num1);
        int[] num02 = toIntArray(num2);
        solution = new int[num01.length+num02.length];
        for (int i = 0; i < num02.length; i++) {
            getProductWithSingleNum(num02[i],num02.length-i-1);
        }
        int index = 0;  //找到非零起始下标
        for (index = 0; index < solution.length; index++) {
            if(solution[index]!= 0) break;
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = index ; i< solution.length; i++) {
            buffer.append(solution[i]);
        }
        return buffer.toString();
    }
    private static void getProductWithSingleNum(int num,int zeroCount){
        int[] tempRes = new int[solution.length];
        int flag = 0;
        int i,j;
        for (i = solution.length-1-zeroCount,j = num01.length-1; j>=0 ; i--,j--) {
            //i 是solution索引 j是 num01索引
            tempRes[i] = (num01[j] * num + flag) % 10;
            flag = (num01[j] * num + flag) / 10;
        }
        if(flag!=0){
            tempRes[i] = flag;
            flag = 0;
        }
        for (j = solution.length-1;  j >=0 ; j--) {
            int temp = (solution[j]+tempRes[j]+flag)%10;
            flag = (solution[j]+tempRes[j]+flag) /10;
            solution[j] = temp;
        }

    }
    private static int[] toIntArray(String str){
        char[] chs = str.toCharArray();
        int[] solution = new int[chs.length];
        for (int i = 0; i < chs.length; i++)    solution[i] = chs[i] - '0';
        return solution;
    }
}
