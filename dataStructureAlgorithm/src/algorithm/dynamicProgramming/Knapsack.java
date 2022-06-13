package algorithm.dynamicProgramming;


/*
* 动态规划：把问题分成相互关联的小问题逐一解决
* 分治算法：把问题分成不相互关联的小问题逐一解决，例如汉诺塔问题 ，圆盘数量的变化，2个和10个是不相关的。
*
* */
public class Knapsack {
    public static void main(String[] args) {
        kanpsackSolution();
    }

    //背包问题的动态规划解法
    public static void kanpsackSolution() {
        int[] weight = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值 这里val[i] 就是前面讲的v[i]
        int knapsackCapacity = 4; //背包的容量
        int itemCount = val.length; //物品的个数

        //maxVal[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] maxVal = new int[itemCount+1][knapsackCapacity+1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[itemCount+1][knapsackCapacity+1];

        //初始化第一行和第一列, 这里在本程序中，可以不去处理，因为默认就是0
        for(int i = 0; i < maxVal.length; i++) {
            maxVal[i][0] = 0; //将第一列设置为0
        }
        for(int i=0; i < maxVal[0].length; i++) {
            maxVal[0][i] = 0; //将第一行设置0
        }
        /* 动态规划：用制表推导算法
        题目要求： 一个物品只可以放一次
         maxvalue/capacity        0               1           2           3           4
            item                  0               0           0           0           0
        item1(w=1,val=1500)       0               1500        1500        1500        1500      只有item1的情况下 不论多大 都只能放item1 最大value都是1500
        item2(w=4,val=3000)       0               1500        1500        1500        3000      当有item1和item2的时候，容量是0-3的时候只有item1，但是容量是4的时候 可以放item2
        item3(w=3,val=2000)       0               1500        1500        2000        2000+1500 当有三种商品的时候 会出现排列组合，并且和之前的存放方案比较
        总结公式：
        i代表列 j代表行
        1.maxVal[i][0]=maxVal[0][j]=0
        2.weight[i]>j时：maxVal[i][j] =maxVal[i-1][j]
            即准备加入的item的重量大于当前容量的时候，直接使用之前的策略
        3.weight[i]<j时：maxVal[i][j] =max{   maxVal[i-1][j], val[i]+maxVal[i-1][j-weight[i]] }
            即准备加入的item的重量小于当前容量的时候，可以加入。
            加入策略 ：把【要加入的物体价值+ 使用剩余空间大小[j-weight[i]]找出之前的使用同等大小空间装入的最大值】和之前同等容量的最大值maxVal[i-1][j]求max
         */


        //根据公式来动态规划处理
        for(int i = 1; i < maxVal.length; i++) { //不处理第一行 都是0
            for(int j=1; j < maxVal[0].length; j++) {//不处理第一列, 都是0
                //公式
                if(weight[i-1]> j) { // 因为我们程序i 是从1开始的，因此公式中的 w[i] 修改成 w[i-1]
                    maxVal[i][j]=maxVal[i-1][j];
                } else {
                    //因为我们的i 从1开始的， 因此公式需要调整成
                    //v[i][j]=Math.max(maxVal[i - 1][j], val[i - 1]+maxVal[i - 1][j-weight[i-1]]);
                    //v[i][j] = Math.max(maxVal[i - 1][j], val[i - 1] + maxVal[i - 1][j - weight[i - 1]]);
                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if(maxVal[i - 1][j] < val[i - 1] + maxVal[i - 1][j - weight[i - 1]]) {
                        maxVal[i][j] = val[i - 1] + maxVal[i - 1][j - weight[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        maxVal[i][j] = maxVal[i - 1][j];
                    }

                }
            }
        }

        //输出maxVal查看
        for(int i =0; i < maxVal.length;i++) {
            for(int j = 0; j < maxVal[i].length;j++) {
                System.out.print(maxVal[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("==========背包内商品情况==========");
        //输出最后我们是放入的哪些商品
        //遍历path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入的那个情况
//		for(int i = 0; i < path.length; i++) {
//			for(int j=0; j < path[i].length; j++) {
//				if(path[i][j] == 1) {
//					System.out.printf("第%d个商品放入到背包\n", i);
//				}
//			}
//		}


        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;  //列的最大下标
        while(i > 0 && j > 0 ) { //从path的最后开始找
            if(path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= weight[i-1]; //w[i-1]
            }
            i--;
        }

    }
}
