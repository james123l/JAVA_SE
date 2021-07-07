package structural.adaptor.classadaptor;

import structural.adaptor.classes.InProduct;
import structural.adaptor.classes.OutProduct;

public class Client {
    public static void main(String[] args) {
        Adaptor adaptor = new Adaptor();
        InProduct in = new InProduct();
        OutProduct out = new OutProduct();
        System.out.println(out.output(adaptor.transfer(in.input("hello,world!"))));
    }
}
