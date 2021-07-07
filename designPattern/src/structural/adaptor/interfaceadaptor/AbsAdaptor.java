package structural.adaptor.interfaceadaptor;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public abstract class AbsAdaptor implements InterfaceAdaptor{
    //默认实现
    @Override
    public ByteBuffer transfer(String str) {
        return null;
    }

    @Override
    public ByteBuffer transfer(ByteBuffer buffer) {
        return null;
    }

    @Override
    public String transfer(CharBuffer buffer) {
        return null;
    }
}
