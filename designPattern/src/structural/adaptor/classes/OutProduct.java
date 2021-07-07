package structural.adaptor.classes;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class OutProduct {
    /*
     * 输出配适器的产品 要求输入ByteBuffer编码集是utf-16，decode为字符串
     * */
    public String output(ByteBuffer buffer){

        Charset cs1 = Charset.forName(StandardCharsets.UTF_16.name());
        //获取编码器
        CharsetDecoder cd = cs1.newDecoder();
        CharBuffer product = null;
        try {
            product = cd.decode(buffer);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        return product.toString();
    }
}
