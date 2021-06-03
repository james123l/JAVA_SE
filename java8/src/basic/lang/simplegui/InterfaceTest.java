package basic.lang.simplegui;

/*Java 的选项面板 javax拓展包 swing界面 JOptionPane选择面板  */
import javax.swing.*;

public class InterfaceTest {
    public static void main(String[] args) {
        //消息对话框
        //最原始的选项对话框 参数分别为父窗体 提示信息
        //JOptionPane.showMessageDialog(null,"message");
        //父窗体 信息 标题信息 消息类型
        //JOptionPane.showMessageDialog(null,"message","test",JOptionPane.WARNING_MESSAGE);
        //父窗体 信息 标题信息 消息类型 图片
        //JOptionPane.showMessageDialog(null,"message","test",JOptionPane.WARNING_MESSAGE,new ImageIcon("pic/test01.jpg"));

        //输入对话框
        //如果选择取消 返回类型Object，value为null  可以用Object obj= 来接收
        //如果接收的object需要转换为string 则可以强制转换 obj.toString();
        //类似的 如果接收的是字符串的整型 也可以用 int i= Integer.parseInt(obj.toString()); 先转换为string再转换为int
        //参数 父窗体 信息 标题 消息类型 图片 下拉菜单选项内容（可以填写0，这样没有下拉菜单） 输入栏默认信息
        //JOptionPane.showInputDialog(null,0,"message",new ImageIcon("pic/test01.jpg"),new String[]{"A","B","C"},"default");
        // 是 否 取消 三个选项
        JOptionPane.showConfirmDialog(null,"confirm message");

    }

}