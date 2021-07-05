package principle.depenciesInversion;


/*
* 依赖倒转原则：
* 1. 高层模块不能依赖底层模块
* 2. 抽象不应该依赖细节， 二者都应该依赖其抽象
* 3. 依赖倒转的中心思想是面向接口编程
* 4. 相对于细节的多变性，抽象的东西要稳定得多。以抽象为基础搭建的架构比以细节为基础搭建的要稳定得多。 抽象就是接口和抽象类， 细节就是具体的实现类
* 5. 接口和抽象类的目的是制定规范而不涉及具体操作，把细节交给实现类完成。
* 注意：
*   1) 低层模块尽量都要有抽象类或接口，或者两者都有，程序稳定性更好.
*   2) 变量的声明类型尽量是抽象类或接口, 这样我们的变量引用和实际对象间，就存在一个缓冲层，利于程序扩展和优化
*   3) 继承时遵循里氏替换原则
*
* */
public class DepenciesInversionDemo {
    public static void main(String[] args) {
        //客户端无需改变
        Person person = new Person();
        person.receive(new Email());

        person.receive(new Wechat());
    }
}
//定义接口
interface IReceiver {
    public String getInfo();
}

class Email implements IReceiver {
    public String getInfo() {
        return "电子邮件信息: hello,world";
    }
}

//增加微信 新功能
class Wechat implements IReceiver {
    public String getInfo() {
        return "微信信息: hello,world";
    }
}

//方式2
class Person {
    //这里是对接口的依赖
    public void receive(IReceiver receiver ) {
        System.out.println(receiver.getInfo());
    }
}