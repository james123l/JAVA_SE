package creational.prototype.deepCopy;


public class Client {

    public static void main(String[] args) throws Exception {
        DeepCopy p = new DeepCopy();
        p.name = "Test";
        p.obj = new DeepCopyObject("a", "b");

        //方式1 完成深拷贝

        DeepCopy p2 = (DeepCopy) p.clone();

		System.out.println("p.name=" + p.name +'\t'+ "p.obj=" + p.obj.hashCode());
		System.out.println("p2.name=" + p2.name +'\t'+ "p2.obj=" + p2.obj.hashCode());

        //方式2 完成深拷贝
        DeepCopy p3 = (DeepCopy) p.deepClone();
        System.out.println("p.name=" + p.name +'\t'+ "p.obj=" + p.obj.hashCode());
        System.out.println("p3.name=" + p3.name +'\t'+ "p3.obj=" + p3.obj.hashCode());

    }

}
