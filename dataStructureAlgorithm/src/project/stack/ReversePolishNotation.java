package project.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class ReversePolishNotation{

}

class ReversePolishNotationCalculator {
    //转换为后缀表达式
    public List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> operatorStack = new Stack<String>();
        List<String> resultStack = new ArrayList<String>(); //用list是因为这里不需要出栈 只需要入栈
        for(String item: list) {
            //通配符 匹配数字
            if(item.matches("\\d+")) {
                resultStack.add(item);
            } else if (item.equals("(")) {
                //左括号要把括号入操作符栈
                operatorStack.push(item);
            } else if (item.equals(")")) {
                while(!operatorStack.peek().equals("(")) {
                    //右括号 要把上一个左括号之后入栈的所有符号出符号栈
                    resultStack.add(operatorStack.pop());
                }
                //消除左括号
                operatorStack.pop();
            } else {
                //对比符号栈优先级 如果item优先级更低 则stack一直pop直到栈顶的优先级和item一致的时候 item入栈
                while(operatorStack.size() != 0 && Operator.getValue(operatorStack.peek()) >= Operator.getValue(item) ) {
                    resultStack.add(operatorStack.pop());
                }
                operatorStack.push(item);
            }
        }
        //把符号栈最后一个运算符加入结果栈
        while(operatorStack.size() != 0) {
            resultStack.add(operatorStack.pop());
        }

        return resultStack;

    }
    //转换中缀表达式为list
    public static List<String> toInfixExpressionList(String string) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        String str;
        char c;
        do {
            //如果不是数字
            if((c=string.charAt(i)) < 48 ||  (c=string.charAt(i)) > 57) {
                //转换为字符串
                list.add("" + c);
                i++;
            } else {
                //str作为多位数的缓冲区
                str = "";
                while(i < string.length() && (c=string.charAt(i)) >= 48 && (c=string.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while(i < string.length());
        return list;
    }
    //输入逆波兰表达式  表达式之间各个元素用空格分隔 依次 取出所有元素
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele: split) {
            list.add(ele);
        }
        return list;

    }

    /*逆波兰表达式
    32+5* （3+2）*5
    不需要括号 如果遇到数组 入栈 如果遇到运算符出栈两个数字 运算后入数栈
    数字栈最后剩余的数字就是结果
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String item : list) {
            //通配符匹配数字
            if (item.matches("\\d+")) {
                //压入数字栈
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("wrong operator");
                }
                stack.push("" + res);
            }

        }
        return Integer.parseInt(stack.pop());
    }

}

class Operator{
    //设置优先级别
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    //获取优先级
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("operator false " + operation);
                break;
        }
        return result;
    }

}
