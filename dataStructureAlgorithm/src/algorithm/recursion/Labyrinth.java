package algorithm.recursion;

public class Labyrinth {
    public static void main(String[] args) {
        int[][] map = new int[12][15];
        //遍历行
        for (int i = 0; i < 15; i++) {
            map[0][i] = 1;
            map[11][i] = 1;
        }
        //遍历列
        for (int i = 0; i < 12; i++) {
            map[i][0] = 1;
            map[i][14] = 1;
        }
        //设置墙
        for (int i = 1; i < 5; i++) {
            map[3][i] = 1;
        }
        for (int i = 1; i < 8; i++) {
            map[i][9] = 1;
        }
        for (int i = 1; i < 7; i++) {
            map[9][i] = 1;
        }
        /**
        把入口堵死,产生回溯问题， 会走已经走过的路（被标记为2的点）
        原点标记为2 判断向下走的时候是true向下走一个点
        在下一个点除了上面的原点 都是不能走的路 经过下右上左四个方位的判断后  第四次判断时标记为3 并且return false
        原点在2的向下走的函数栈运算结束 并且执行向右 return false 向上 false 向左 false 最终函数再原点的函数栈运算结束 返回false 迷宫无解
         */
		map[1][2] = 1;
		map[2][2] = 1;
//        showMap(map);
        LabyrinthSolution.solution(map);
//        showMap(map);
    }

    //打印地图
    public static void showMap(int[][] map) {
        System.out.println("current map---------");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class LabyrinthSolution {
    //规定不同 策略不同 最终结果也不同
    /*
    规定：从[1][1]右上角开始进入 [11][14]左下角出迷宫
    策略：
    1.先往下走 再往右走，再往左走，如果都不能走则向上走。
    2. 将走过的点标记 0 代表没走过 1代表墙 2代表可以走且走过，3代表不能通过
    3. 找到通路 返回true 否则返回false
     */
    public static  void solution(int[][] labyrinth) {
        //以1，1为起点
        findWay(labyrinth,1,1);

    }

    public static boolean findWay(int[][] labyrinth, int i, int j) {
        Labyrinth.showMap(labyrinth);
        if (labyrinth[labyrinth.length - 2][labyrinth[0].length - 2] == 2) {
            //找到了出口
            return true;
        } else {
            //没有找到出口
            if (labyrinth[i][j] == 0) {
                //0代表没走过，这个地点先假设可以走，注意  3 不会再走一次，2如果再走一次就被设置为3
                labyrinth[i][j] = 2;
                if (findWay(labyrinth, i + 1, j)) {
                    //如果可以向下走
                    return true;
                } else if (findWay(labyrinth, i, j + 1)) {
                    //如果可以向右走
                    return true;
                } else if (findWay(labyrinth, i - 1, j)) {
                    //如果可以向左走
                    return true;
                } else if (findWay(labyrinth, i, j - 1)) {
                    //如果可以向上走
                    return true;
                } else {
                    //无路可走 设置为3 死路
                    labyrinth[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}

