package leetcode.stack;

import java.util.HashMap;
import java.util.Stack;

public class N20_ValidParentheses {
    public static void main(String[] args) {
        N20_ValidParentheses valid = new N20_ValidParentheses();
//        valid.isValidOfficial("()");
    }
    public boolean isValidOfficial(String str) {
        if(str.length()%2==1) return false;
        char[] stack = new char[str.length()];
        int idx = 0;

        for(char c : str.toCharArray())
        {
            if(c == '(')
                stack[idx++] = (')');
            else if(c == '[')
                stack[idx++] = (']');
            else if(c == '{')
                stack[idx++] = ('}');
            else
            {
                if(idx == 0)
                    return false;
                if(c != stack[--idx]) return false;
            }
        }
        return idx == 0;
    }
    public boolean isValidBruteForce(String str) {
        if(str.length()%2==1) return false;
        Stack<Character> stack = new Stack<>();
        HashMap<Character,Character> hashMap = new HashMap<>();
        hashMap.put('(',')');
        hashMap.put('[',']');
        hashMap.put('{','}');
        char temp;
        for (int i = 0; i < str.length(); i++) {
            temp = str.charAt(i);
            if(temp=='('||temp=='['||temp=='{'){
                stack.push(hashMap.get(temp));
            }else if(!stack.isEmpty()) {
                if(temp != stack.pop()) return false;
            }else{
                return false;
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}
