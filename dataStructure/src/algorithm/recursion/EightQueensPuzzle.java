package algorithm.recursion;

public class EightQueensPuzzle {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    //下标代表行 元素代表列
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        EightQueensPuzzle eightQueensPuzzle = new EightQueensPuzzle();
        //从第0行开始放入皇后
        eightQueensPuzzle.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }
    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n) {
        if(n == max) {  //摆放到了第八行 摆放结束
            print();
            return;
        }

        //在当前行一次从左到右摆放皇后
        for(int i = 0; i < max; i++) {
            //依次试探皇后摆放的位置 数组的元素就是当前可摆放的列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)) { // 当前位置与之前的皇后不冲突
                //接着放n+1个皇后,即开始递归
                check(n+1); //
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行的 后移的一个位置
        }
    }

    //查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        //用于计算判断次数
        judgeCount++;
        //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列 数组元素代表列
        //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
        //如果在一个斜线上 横坐标差等于纵坐标差
        //3.不需要判断是否在同一行, n 每次都在递增
        for(int i = 0; i < n; i++) {
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i]) ) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private  void print() {
        //统计解法数量
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
/*
截取一段console进行分析
先找到栈顶 就是数组最后一个元素 如果和前面的元素不冲突 则会打印
如果冲突那么则会return false 回溯到上一个栈 上一个栈再次进行测试下一个位置
    1.如果return true 则会继续打印
    2.如果return flse 继续回溯
直到回溯到了第一个栈 然后继续在第一个栈测试下一个位置操作
0 4 7 5 2 6 1 3
0 5 7 2 6 3 1 4
0 6 3 5 7 1 4 2
0 6 4 7 1 3 5 2
1 3 5 7 2 0 6 4
1 4 6 0 2 7 5 3
1 4 6 3 0 7 5 2
 */
