package project.stack;
import datastructure.List_Stack.Stack;
//中缀表达式计算器

public class CalculatorDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate("(-5)*2+((1+2)*3+1)+(1+2*(3-1))"));
    }

}
class Calculator {
    private CalStack expressions = new CalStack(20);
    private CalStack numStack = new CalStack(20);
    private CalStack operatorStack = new CalStack(10);

    char temp;//扫描到的char
    /*
    把带字符串的表达式分解成括号的进行计算
     */
    public int calculate(String expression){
        //记录临时的表达式
        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        while(true) {
            System.out.print(stringBuffer);
            System.out.println("buffer");
            temp = expression.charAt(index);
            if (temp == '(') {
                //第一个（ 开始字符串append 如果没遇到） ，则直到第二个（，stringbuffer入栈
                //如果栈内没有元素 直接加buffer 否则入栈 入栈需要存string 如果存buffer 则会导致pop出来的东西是null
                expressions.push(stringBuffer.toString());
                //System.out.println(expressions.peekTop()+"peek");
                stringBuffer.delete(0,stringBuffer.length());
            }else if(temp == ')'){
                //找到了） 开始出栈,加在stringbuffer上组合成为表达式 计算结果
                int num =calculateNoBracket(stringBuffer.toString()) ;
                stringBuffer.delete(0,stringBuffer.length());
                stringBuffer.append((String)expressions.pop()).append(num);
            }else{
                stringBuffer.append(temp);
            }
            index++;
            if(index==expression.length()) break;
        }
        System.out.println(stringBuffer.toString());
        return calculateNoBracket(stringBuffer.toString());
    }
    /*
    解决方案：
    如果是数字 压入栈，如果是运算符 对比栈顶，如果优先级高 则入栈，优先级低则先出栈一个运算符和两个数字运算后压入数栈，在把运算符压入栈
    最后把所有的运算符都出栈运算到运算符栈为空
     */
    private int calculateNoBracket(String expression){
        int index = 1, num1 = 0, num2 = 0 ;
        char temp;
        //拼接int的字符串
        StringBuffer stringBuffer = new StringBuffer();
        if(expression.charAt(0)=='-'){
            stringBuffer.append('-');
        }else{
            stringBuffer.append(expression.charAt(0)-48);
        }
        while(true){
            //获取字符
            temp = expression.charAt(index);
            //如果不是运算符
            if(!numStack.isOperator(temp)){
                stringBuffer.append(temp);
            }else{
                //如果扫描到运算符,判断stringbuffer是否为空 如果非空则需要压入数字栈
                if (stringBuffer.length() != 0) {
                    numStack.push(Integer.parseInt(stringBuffer.toString()));
                    stringBuffer.delete(0, stringBuffer.length());
                }
                if(operatorStack.isNull()) {
                    operatorStack.push(temp);
                }else {
                    //判断符号栈top与temp权限,如果小于 则应当计算栈内的数字
                    if (operatorStack.priority(temp) <= operatorStack.priority(operatorStack.peekTop().toString().charAt(0))) {
                        num1 = Integer.parseInt(numStack.pop().toString());
                        num2 = Integer.parseInt(numStack.pop().toString());
                        numStack.push(numStack.calculate(num1, num2, operatorStack.pop().toString().charAt(0)));
                        operatorStack.push(temp);
                    } else {
                        operatorStack.push(temp);
                    }
                }
            }
            if(index == expression.length()-1) {
                if (stringBuffer.length() != 0) {
                    numStack.push(Integer.parseInt(stringBuffer.toString()));
                    stringBuffer.delete(0, stringBuffer.length());
                }
                break;
            }
            index++;
        }
//        operatorStack.showStack();
//        numStack.showStack();
        while(!operatorStack.isNull()) {
            if(operatorStack.isNull()) break;
//            System.out.println("calculate");
            num1 = Integer.parseInt(numStack.pop().toString());
            num2 = Integer.parseInt(numStack.pop().toString());
            numStack.push(numStack.calculate(num1, num2, operatorStack.pop().toString().charAt(0)));
        }
        return (int) numStack.pop();
    }
}
class CalStack extends Stack {

    public CalStack(int capacity) {
        super(capacity);
    }
    //判断传入的操作符优先级
    public  int priority(int oper){
        if(oper=='*'||oper=='/') return 1;
        else if(oper=='+'||oper=='-') return 0;
        //运算符有误
        return -1;
    }
    public boolean isOperator(char var){
        return var=='+'||var=='-'||var=='*'||var=='/';
    }
    public int calculate(int num1,int num2,int operator){
        System.out.println((char) operator);
        switch (operator){
            case '+':
                return num1+num2;
            case '-':
                //栈后入先出 num2是先入栈的数字 后出，
                return num2-num1;
            case '*':
                return num1*num2;
            case '/':
                //栈后入先出
                return num2/num1;
        }
        return 0;
    }
}