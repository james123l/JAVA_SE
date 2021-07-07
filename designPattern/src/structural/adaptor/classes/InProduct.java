package structural.adaptor.classes;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class InProduct {
    /*
    * 输入配适器的产品 要求输出ByteBuffer编码集是UTF-8
    * */
    public ByteBuffer input(String str){
        Charset cs1 = Charset.forName(StandardCharsets.UTF_8.name());
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        CharBuffer cBuf = CharBuffer.allocate(1024);
        ByteBuffer information = null;
        cBuf.put(str);
        try {
            cBuf.flip();
            information = ce.encode(cBuf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        return information;
    }
}
