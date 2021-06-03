package datastructure.SparseArray_Queue;

import java.io.*;

public class SparseArray {
//    public static void main(String[] args) {
//        SparseArrayQueueStack.SparseArray sparseArray = new SparseArrayQueueStack.SparseArray();
//        sparseArray.sparseArray();
//    }


    //通过稀疏数组实现存入。
     public void sparseArray(){
         //11*11棋盘
         int[][] chessOrigional = new int[11][11];
         //3个棋子
         chessOrigional[1][2] = 1; chessOrigional[2][3] = 2; chessOrigional[4][5] = 2;

         File file = saveAsSparseArray(chessOrigional);

         int[][] chessCopy=  readFromSparseArray(file);
         for (int[] row : chessOrigional) { for (int data : row) { System.out.printf("%d\t", data); }System.out.println(); }
         System.out.println("-----------------------------------------");
         for (int[] row : chessCopy) { for (int data : row) { System.out.printf("%d\t", data); }System.out.println(); }


     }

     public File saveAsSparseArray(int[][] origional) {
         //01 获取非0个数
         int sum = 0;
         for (int i = 0; i < origional.length; i++) {
             for (int j = 0; j < origional[0].length; j++) {
                 if (origional[i][j] != 0)  sum++;
             }
         }
         //02创建稀疏数组
         int sparseArr[][] = new int[sum + 1][3];
         sparseArr[0][0] = origional.length; sparseArr[0][1] = origional[0].length; sparseArr[0][2] = sum;
         //03添加进稀疏数组
         int count = 1; //自增
         for (int i = 0; i < origional.length; i++) {
             for (int j = 0; j < origional[0].length; j++) {
                 if (origional[i][j] != 0) {
                     sparseArr[count][0] = i;
                     sparseArr[count][1] = j;
                     sparseArr[count][2] = origional[i][j];
                     count++;
                 }
             }
         }
//         检查稀疏数组
         for (int[] row : sparseArr) { for (int data : row) { System.out.printf("%d\t", data); }System.out.println(); }
         System.out.println("-----------------------------------------");
         File file = new File("dataStructure\\src\\file\\sparseArray.txt");
         FileOutputStream fileOutputStream = null;
         OutputStreamWriter outputStreamWriter = null;
         try {
             fileOutputStream = new FileOutputStream(file);
             outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
             for (int i = 0; i < sum+1; i++) {
                 outputStreamWriter.append(sparseArr[i][0] + "\t" + sparseArr[i][1] + "\t" + sparseArr[i][2]+"\n");
             }
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             try {
                 if(outputStreamWriter!=null) outputStreamWriter.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             try {
                 if(fileOutputStream!=null) fileOutputStream.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         return file;
    }

    public int[][] readFromSparseArray(File file)  {
        //从file获取稀疏数组
        BufferedReader reader = null;
        String line;
        int[][] sparse = null;
        int row = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine())!=null){
                String[] strings  = line.split("\t");
                for (int i = 0; i<strings.length;i++){
                    if(row == 0&&i==0){
                        sparse = new int[Integer.parseInt(strings[i])][Integer.parseInt(strings[i+1])];
                    }
                    sparse[row][i] = Integer.parseInt(strings[i]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader!= null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //从稀疏数组解压为数组
        int[][] chessOrigional = new int[sparse[0][1]][sparse[0][1]];
        for(int i = 1; i < sparse.length; i++) {
            chessOrigional[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        return chessOrigional;

    }
}
