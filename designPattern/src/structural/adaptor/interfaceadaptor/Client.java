package structural.adaptor.interfaceadaptor;

import structural.adaptor.classes.InProduct;
import structural.adaptor.classes.OutProduct;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class Client {
    public static void main(String[] args) {
        InProduct in = new InProduct();
        OutProduct out = new OutProduct();
        AbsAdaptor adaptor = new AbsAdaptor() {
            @Override
            public ByteBuffer transfer(ByteBuffer buffer) {
                CharsetDecoder decoder = Charset.forName(StandardCharsets.UTF_8.name()).newDecoder();
                ByteBuffer product = null;
                try {
                    CharBuffer decode = decoder.decode(buffer);
                    CharsetEncoder encoder = Charset.forName(StandardCharsets.UTF_16.name()).newEncoder();
                    product = encoder.encode(decode);
                } catch (CharacterCodingException e) {
                    e.printStackTrace();
                }
                return product;
            }
        };
        System.out.println(out.output(adaptor.transfer(in.input("hello,world!"))));
    }
}
