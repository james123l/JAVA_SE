package structural.adaptor.objectadaptor;

import structural.adaptor.classes.InProduct;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

/*
* 对象适配器把原有的继承关系变成了组合关系
* */
public class Adaptor implements AdaptInterface {
    private InProduct in;

    public Adaptor(InProduct in) {
        this.in = in;
    }

    @Override
    //把utf-8 转换为utf-16
    public ByteBuffer transfer(String str) {
        ByteBuffer buffer = in.input(str);
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
}
