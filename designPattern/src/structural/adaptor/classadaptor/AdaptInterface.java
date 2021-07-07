package structural.adaptor.classadaptor;

import java.nio.ByteBuffer;

public interface AdaptInterface {
    //把utf-8 转换为utf-16
    ByteBuffer transfer(ByteBuffer buffer);
}
