package basic.string.questions;

public class Stringtest {
    public static void main(String[] args)  {
        String str= "hello,world!";
        //System.out.println(Thisutil.secionalReverse(str, 1, 4));

        String str2 = "1abcabcabcabcandkacab";
        //System.out.println(Thisutil.substringAppearTimes("ab", str2));

        String str3= "abckac";
        System.out.println(Thisutil.getLongestSubstringMethod1(str2,str3));
        for (String stringiterator :Thisutil.getLongestSubstringMethod2(str2,str3)) {
            System.out.println(stringiterator);
        }



    }
}
class Thisutil{
    //区间reverse
    public static String secionalReverse(String str,int firstIndex,int lastIndex){
        if(firstIndex>=lastIndex||lastIndex>str.length()) try {
            throw new Exception("index is wrong!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int random =(int) Math.random()*100%2;
        switch (random) {
            case 0:
                //char array方法
                lastIndex--;
                char[] chars = str.toCharArray();
                char temp;
                do {
                    temp = chars[firstIndex];
                    chars[firstIndex] = chars[lastIndex];
                    chars[lastIndex] = temp;
                    firstIndex++;
                    lastIndex--;
                } while (lastIndex > firstIndex);
                return new String(chars);
            case 1:
                //字符串拼接法
                StringBuffer strBuf = new StringBuffer(str.substring(0, firstIndex));
                for (int i = lastIndex - 1; i >= firstIndex; i--) {
                    strBuf.append(str.charAt(i));
                }
                return new String(strBuf.append(str.substring(lastIndex)));   //从lastindex到结束
        }
        return null;
    }
    //计算子串出现的次数
    public static int substringAppearTimes (String sub,String parent){
        if(sub.length()>parent.length()) return 0;
        int count = 0;
        int index = 0;
        int len = sub.length();
        while((index=parent.indexOf(sub,index))!= -1){
               count++;
               index+= len;
        }
        return count;
    }
    //计算最长子串01
    /*算法：
    把两个字符串分别设置为第一行和第一列制作表格
    0   a   b   c
    a   1   0   0
    b   0   2   0
    c   0   0   3
    d   0   0   0
    对角线连续的最大的数字就对应了最长子串
     */

    public static String getLongestSubstringMethod1(String str1, String str2){
        //str1作为矩阵的第一列 str2作为第一行
        int[][] matrix = new int[str1.length()+2][str2.length()+2];
        int i=0,j = 0,maxlength=0,maxsolution=0;
        //设置列
        for (j = 1; j < str2.length()+1; j++) {
            //设置行
            for (i= 1; i < str1.length()+1; i++) {
                if(str2.charAt(j-1)==str1.charAt(i-1)) {
                    matrix[i][j] = matrix[i-1][j-1]+1;
                    if (matrix[i][j]>maxlength){
                        maxlength=matrix[i][j];
                        //i是对象str1的下标
                        maxsolution=i;
                    }
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (i = maxlength; i>0 ; i--) {
            stringBuffer.append(str1.charAt(maxsolution-i));
        }
        return stringBuffer.toString();
    }
    //计算最长子串02
    /*
    算法：例如短串长度为6 则先检测长度为6的子串是否存在 再检测5 一直到1
     */
     public static String[] getLongestSubstringMethod2(String str1, String str2){
         //设置承载最长字串的stringbuffer
        StringBuffer stringBuffer = new StringBuffer();
        String longstr = str1.length()>=str2.length()? str1:str2;
        String shortstr = str1.length()<str2.length()? str1:str2;
        int len = shortstr.length(); //设置长度
         //外循环是控制子串的长度
        for(int i = 0 ;i<len ; i++){
            //内循环控制子串在短串中的位置
            for(int x = 0, y = len-i ; y <= len ; x++,y++){
                String substr = shortstr.substring(x,y);
                if(longstr.contains(substr)){
                    stringBuffer.append(substr+'|');
                }
            }
            if(stringBuffer.length()!=0)  {  break; }
        }
        //先去掉最后一个|，在以|作为分割，返回数组
        String[] solution = stringBuffer.toString().replaceAll("|$","").split("\\|");
        return solution;
    }

}
