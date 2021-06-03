package basic.string.methods;

public class BufferTest {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("edcba");
        //反转字符串
        System.out.println(str.reverse());
        //插入字符 offset=3 从index=4开始插入 输入abchellode
        System.out.println(str.insert(3,"hello"));
        //删除元素
        // abcellode 删除下标为3
        // abcde 删除区间为[3，7)
        System.out.println(str.deleteCharAt(3));
        System.out.println(str.delete(3,7));
        //替换 [3,6) 替换为hello abchello
        System.out.println(str.replace(3,6,"hello"));
        //设置第几位字符 abbhello
        str.setCharAt(2,'b');   //返回值是void 所以不能直接写在打印函数里
        System.out.println(str);
        //其他操作
        //查看capacity
        System.out.println(str.capacity());
        //清空字符串
        str.setLength(0);
        //去掉缓冲区
        str.trimToSize();
    }

}
