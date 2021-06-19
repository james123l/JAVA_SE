package  classes.string.project;
import javax.swing.JOptionPane;


public class InterfaceTest {
    public static void main(String[] args) {
        /*
        1.
        用户名在8-25位 不能包含特殊符号 空格
        密码在6-18位 只能包含字母 数字 特殊符号
        纯数字/字母 密码强度为弱
        两种搭配为中等
        三种搭配为强
        2.
        用户需要输入正确的email 仅有一个@和. 符号不能开头不能结尾
         */
        boolean flag=false;
        String[] informationT={"username","password","email"};
        String[] information= new String[3];
        for (int i = 0; i < 3; i++) {
            information[i]=new String(JOptionPane.showInputDialog("input the "+informationT[i]));
        }
        User user = new User(information[0],information[1],information[2]);

    }

}