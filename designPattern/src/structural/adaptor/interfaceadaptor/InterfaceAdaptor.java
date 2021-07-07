package structural.adaptor.interfaceadaptor;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public interface InterfaceAdaptor {

    ByteBuffer transfer(String str);

    ByteBuffer transfer(ByteBuffer buffer);

    String transfer(CharBuffer buffer);
}
