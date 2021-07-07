package structural.adaptor.classadaptor;

import structural.adaptor.classes.InProduct;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class Adaptor extends InProduct implements AdaptInterface {
    @Override
    //把utf-8 转换为utf-16
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
}
