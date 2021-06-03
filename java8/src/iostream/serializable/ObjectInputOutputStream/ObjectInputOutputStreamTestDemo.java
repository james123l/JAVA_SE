package iostream.serializable.ObjectInputOutputStream;

import java.io.*;

/*
ObjectInput/OutputStream的使用
 */
public class ObjectInputOutputStreamTestDemo {
    //序列化的过程 把内存中对象储存到磁盘或者通过网络传输，要使用ObjectOutputStream
    public static void testObjectOutputStream(){
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("readme.dat"));
            //.dat文件是存储数据的文件 也可以用txt
            objectOutputStream.writeObject(new String("abcd"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(objectOutputStream!= null) {
                try {
                    objectOutputStream.flush(); //清空缓冲区
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //反序列化
    public static void testObjectInputStream(){
        ObjectInput objectInput = null;
        try {
            objectInput = new ObjectInputStream(new FileInputStream("readme.dad"));
            String string = (String) objectInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInput!= null){
                try {
                    objectInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
class PersonSerializable implements Serializable{
    private static final long serialVersionUID = 10000000l;
    //此时这个类也可以和上面的string一样序列化和反序列化
}
