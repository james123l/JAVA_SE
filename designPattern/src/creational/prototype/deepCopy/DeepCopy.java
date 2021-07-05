package creational.prototype.deepCopy;

import java.io.*;

public class DeepCopy implements Serializable, Cloneable{

    public String name; //String 属性
    public DeepCopyObject obj;// 引用类型
    public DeepCopy() {
        super();
    }


    //深拷贝 - 方式 1 使用clone 方法
    @Override
    protected Object clone() throws CloneNotSupportedException {

        Object deep = null;
        //这里完成对基本数据类型(属性)和String的克隆
        deep = super.clone();
        DeepCopy deepCopy = (DeepCopy)deep;
        //对引用类型的属性，进行单独处理
        deepCopy.obj = (DeepCopyObject) obj.clone();

        return deepCopy;
    }

    //深拷贝 - 方式2 通过对象的序列化实现 (推荐)

    public Object deepClone() {

        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {

            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); //当前这个对象以对象流的方式输出

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepCopy copyObj = (DeepCopy)ois.readObject();

            return copyObj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭流
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }

    }

}