package algorithm.recursion;

import java.util.Scanner;
import java.util.Stack;

//汉诺塔问题
/**
*回溯方式： 先运算到栈顶 即只有一个盘从1移动到3
*移动成功后回溯 到上一级栈
*可以理解为如果是2个盘的汉诺塔 是建立在一个盘的算法之上 三个盘则是基于2个盘的基础之上
 */
public class TowerOfHanoi {
    //汉诺塔问题 所有的64个铁盘都在柱1上 要求移动到柱子3，编号小的可以压在大的上 大的不可以压在小的上
    public static void main(String[] args) {
        Stack stack = new Stack();
        Stack target= new Stack();
        for (int i = 3; i > 0; i--) {
            stack.push(i);
        }
        hanoiSolution(stack.size(), stack, new Stack(), target);
        while(target.size()!=0){
            System.out.println(target.pop());
        }
    }
    /*
    只有一个圆盘时 从1到3 否则：
    把1上的 盘子移动到只有一个 剩下的全部移动到2
    把1的盘子移动到3
    把3的盘子移动到2
     */
    private static Stack hanoiSolution(int count,Stack stack1,Stack stack2,Stack stack3){
        if(stack1.isEmpty()) return stack3;
        if(count==1){
            //只有一个的时候 1出栈3入栈
            move(stack1,stack3);
            return stack3;
        }else{
            //开始把1的盘子移动到2 除了最底下的
            /*
            hanoiSolution(stack1,stack3,stack2); 这样写的问题是没能计算移动多少，这个函数本身的定义是移动到stack1为空，所以不能更新递归的退出条件
             */
            hanoiSolution(count-1,stack1,stack3,stack2);
            //把1最底下的盘子移动到3
            move(stack1,stack3);
            //剩下的2的盘子移动到3
            hanoiSolution(count-1,stack2,stack1,stack3);
        }
        return stack3;
    }
    //stack1 栈顶移动到stack2
    private static boolean move(Stack stack1,Stack stack2){
        System.out.println(stack1.peek()+"move func  stack1");
        int priority;
        //stack2出现空指针异常  peek也会导致异常
        //判断条件当数字更小的时候可以入栈 为空则是100
        if(stack2.size()==0){
            priority = 100;
        }else{
            priority =(int)stack2.peek();
        }
        if((int)stack1.peek()< priority)  {
            stack2.push(stack1.pop());
            return true;
        }
        return false;
    }
}
class TowersOfHanoi {
    static int m =0;//标记移动次数
    //实现移动的函数
    public static void move(int disks,char N,char M)
    {
        System.out.println("第" + (++m) +" 次移动 : " +" 把 "+ disks+" 号圆盘从 " + N +" ->移到->  " + M);
    }
    //递归实现汉诺塔的函数
    public static void hanoi(int n,char A,char B,char C)
    {
        if(n == 1)//圆盘只有一个时，只需将其从A塔移到C塔
            TowersOfHanoi.move(1, A, C);//将编b号为1的圆盘从A移到C
        else
        {//否则
            hanoi(n - 1, A, C, B);//递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            TowersOfHanoi.move(n, A, C);//把A塔上编号为n的圆盘移到C上
            hanoi(n - 1, B, A, C);//递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
        }
    }
    public static void main(String[] args) {
        Scanner imput = new Scanner(System.in);
        char A = 'A';
        char B = 'B';
        char C = 'C';
        System.out.println("******************************************************************************************");
        System.out.println("这是汉诺塔问题（把A塔上编号从小号到大号的圆盘从A塔通过B辅助塔移动到C塔上去");
        System.out.println("******************************************************************************************");
        System.out.print("请输入圆盘的个数：");
        int disks = imput.nextInt();
        TowersOfHanoi.hanoi(disks, A, B, C);
        System.out.println(">>移动了" + m + "次，把A上的圆盘都移动到了C上");
        imput.close();
    }

}
