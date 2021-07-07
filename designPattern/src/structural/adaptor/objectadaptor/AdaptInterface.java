package structural.adaptor.objectadaptor;

import java.nio.ByteBuffer;

public interface AdaptInterface {
    //把utf-8 转换为utf-16
    ByteBuffer transfer(String str);
}
