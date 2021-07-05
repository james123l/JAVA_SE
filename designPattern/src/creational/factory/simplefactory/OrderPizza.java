package creational.factory.simplefactory;

import creational.factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
* 这个类用于组装pizza类的制作流程，服务于客户端
* 把pizza的种类选择交给工厂处理
* */
public class OrderPizza {

    Pizza pizza = null;
    String orderType = "";
    // 构造器
    public OrderPizza() {

        do {
            orderType = getType();
            pizza = SimpleFactory.createPizza2(orderType); //此处封装了制造pizza的过程

            // 输出pizza
            if (pizza != null) { // 订购成功
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println(" 订购披萨失败 ");
                break;
            }
        } while (true);
    }

    // 写一个方法，可以获取客户希望订购的披萨种类
    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类:");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
