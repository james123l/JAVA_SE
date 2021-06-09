package io.File;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;


public class FileMethodTestDemo {

    private static  final String filepath = "src/README.md";

    public static void main(String[] args) {
        //.代表当前工程的根目录
        File file = new File(".");
        //File file = new File("README.md");
        //File file = new File("filepath");
        System.out.print("当前文件是否存在："+file.exists()+'\n');
        System.out.print("当前文件是否存在："+file.isFile()+'\n');
        System.out.print("当前文件是否存在夹："+file.isDirectory()+'\n');
        System.out.print("文件名/目录名："+file.getName()+'\n');
        System.out.print("绝对路径："+file.getAbsolutePath()+'\n');
        System.out.print("路径："+file.getPath()+'\n');
        System.out.print("最后修改时间："+new Date(file.lastModified()).toLocaleString()+'\n');
        System.out.print("是否隐藏："+file.isHidden()+'\n');
        System.out.print("是否可读："+file.canRead()+'\n');
        System.out.print("是否可写："+file.canWrite()+'\n');
        System.out.print("文件大小："+file.length()/1024+ "KB\n");   //  只能计算文件 文件夹默认返回值是0
        //System.out.print("是否可执行："+file.canExecute()+'\n'); // 仅在linux有效

        //使用file类创建文件/文件夹
        if(!file.exists()){ //如果文件不存在，创建文件
            try {
                if(file.isFile()) file.createNewFile();
                else if (file.isDirectory())    file.mkdir();   //创建抽象类指定目录，仅能创建一级目录 并且需要父目录存在
                //file.mkdirs() 创建目录 可以创建多级目录 这两个函数返回值都是boolean

                /*
                if(file.createNewFile()) {
                    //创建文件会导致IO异常
                    System.out.println("文件/文件夹创建成功。");
                 */
                } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //删除文件
        if(file.exists()){
            file.delete();      //立即删除
            file.deleteOnExit(); //进程退出后删除
        }
        

        //JFileChooser类
        //案例选择当前根目录

        //JFileChooser 选择文件
        JFileChooser jFileChooser = new JFileChooser(new File("."));    //new File()设置的是文件打开时的根目录
        //显示一个目录对话框
        jFileChooser.showOpenDialog(null);
        //获得被选择的文件对象
        file = jFileChooser.getSelectedFile();      //建议判断是否为null 如果是null 则代表用户点击了取消按钮
        //String filename = JFileChooser;

        //JFileChooser 选择文件夹
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //也可以单独的是DIRECTORIES
        jFileChooser.setFileFilter(new MyFileFilter());    //自行定义的filter
        if(file.isDirectory()){
            //列出当前目录的文件名
            String[] namelist = file.list();
            for (String str: namelist) {
                System.out.print(str+"\t");
            }
        }


    }
    //这个类是FileTestDemo的内部类， 所以在main函数调用的时候FileTestDemo还没有被实体化，所以需要改为static类使得被实体化之前就存在
     static public class FileDirFilter implements FilenameFilter {
        //文件名过滤器，需要实现接口FilenameFilter
        @Override
        public boolean accept(File dir, String name) {
            //假设调用所有的txt文件
            if(name.endsWith("txt")) return true;
            return false;
        }

    }
    static public class MyFileFilter extends javax.swing.filechooser.FileFilter {
        //文件名过滤器，需要实现接口FilenameFilter

        @Override
        public boolean accept(File f) {
            //假设调用所有的txt文件
            if(f.getName().endsWith("txt")) return true;
            return false;
        }

        @Override
        public String getDescription() {
            return null;
        }
    }
}
