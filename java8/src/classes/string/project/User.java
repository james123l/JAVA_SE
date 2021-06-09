package  classes.string.project;

import javax.swing.JOptionPane;

public class User {
    boolean setUsernameFlag = true;
    boolean setEmailFlag = true;

    private String[] usernameMessage= {"用户名在8-25位","不能包含特殊符号","用户名合法"};
    private String[] passwordMessage= {"密码需要在6-18位之间","弱","中等","强","密码包含非法字符"};
    private String[] emailMessage={"邮箱不能以符号开头","请输入合法电子邮件，@和.请不要使用在邮件名中","邮件合法"};

    private String username;
    private String password;
    private String email;
    private Validate validate;

    //构造函数
    //default constructor
    public User() { }
    //set constructor
    public User(String username, String password, String email) {
        System.out.println("User object constructor ran!");
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }


    //获取用户名 密码 邮箱
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    //设置信息 用户名和邮箱不可更改
    //设置用户名
    private boolean setUsername(String username) {

        int index = validate.validateUsername(username);
        if(index==2) {
            if (setUsernameFlag = true) {
                this.username = username;
                setUsernameFlag = !setUsernameFlag;
                return true;
            } else {
                return false;
            }
        }
        else if(index==1|| index==0){
            JOptionPane.showMessageDialog(null,usernameMessage[index],"ERROR",0);
        }
        return false;
    }

    //设置密码
    //需求： 可以更改密码
    public boolean setPassword(String password) {
        int index = validate.validatePassword(password);
        if((index==1||index==2||index==3)&&(!setEmailFlag)&&(!setUsernameFlag))  {
            this.password = password;
            JOptionPane.showMessageDialog(null,"密码强度为："+passwordMessage[index],"Success",0);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,passwordMessage[index],"ERROR",0);
            return false;
        }
    }

    //设置邮箱
    private boolean setEmail(String email) {
        int index = validate.validateEmail(email);
        if(index == 2) {
            if (setUsernameFlag = true) {
                this.email = email;
                setEmailFlag = !setEmailFlag;
                return true;
            } else {
                return false;
            }
        }
        else {
            JOptionPane.showMessageDialog(null,emailMessage[index],"ERROR",0);
            return false;
        }
    }

}
