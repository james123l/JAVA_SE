package structural.adaptor.objectadaptor;

import structural.adaptor.classes.InProduct;
import structural.adaptor.classes.OutProduct;

public class Client {
    public static void main(String[] args) {
        Adaptor adaptor = new Adaptor(new InProduct());
        OutProduct out = new OutProduct();
        System.out.println(out.output(adaptor.transfer("hello,world!")));
    }
}
