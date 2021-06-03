public class LongestSubstringWithoutRepeatingCharacters2 {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("abcabcabc" );
        System.out.println(lengthOfLongestSubstring(stringBuffer.toString()));
    }

    //暴力算法
    public static int lengthOfLongestSubstring(String str) {
        if(str.length() == 0) return 0;
        //从头遍历 如果发现含有相同字符 找到index+1 重新计数
        int index = 0 , num ,max=1;
        StringBuffer buffer = new StringBuffer().append(str.charAt(index));
        String buff ="";
//        buff += str.charAt(index);
        for(int i = 1; i < str.length(); i++){
            if( (num =buffer.indexOf(buff =str.substring(i,i+1)))!=-1) {
                //说明有重复字符出现 num就是重复字符的下标 赋值给index
                index = num++;
                if(buffer.length()>max) max = buffer.length();
//                System.out.println(buffer.toString()+max);
                //删除是开区间
                buffer = buffer.delete(0,index+1).append(buff);
            }else{
                //没找到 buffer需要扩大长度
                buffer.append(buff);
            }
        }
        return Math.max(max,buffer.length());
    }
}
