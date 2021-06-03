package datastructure.SparseArray_Queue;

public class Quene {
    //数组模拟队列
}
// 数组队列
class ArrayQueue{
    private int maxSize;    //表示数组的最大容量
    private int front;      //头指针
    private int rear;       //尾指针
    private int[] arr;      //该数组用于存放数据，模拟队列

    //创建数组队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        front = -1; //指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;  //指向队列尾部，指向队列尾（即队列最后一个数据）
    }
    //判断队列是否为空
    //内部有 （rear+maxSize-front）%maxSize个元素
    public boolean isEmpty(){
        return front == rear;
    }
    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //向队列中添加数据
    public void addQueue(int n){
        //如果满了则输出异常不添加数据
        if (isFull()){
            System.out.println("错误：队列已满不能再添加");
            return;
        }
        //没满，则指针后移，向指针所指的格子中添加数据
        rear++;
        arr[rear] = n;
    }
    //从队列中获得数据
    public int getQueue(){
        //如果队列是空的，抛出异常
        if (isEmpty()){
            throw new RuntimeException("队列是空的，不能取出数据");
        }
        //指针后移返回数据
        front++;
        return arr[front];
    }
    //将队列格式化输出
    public void show(){
        if (isEmpty()){
            System.out.println("队列是空的，没有数据");
            return;
        }

        for (int i = 0; i<arr.length;i++){
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }
}

//环形队列
class CircleArrayQueue {
    private int maxSize;    //表示数组的最大容量
    private int front;      //头指针
    private int rear;       //尾指针
    private int[] arr;      //该数组用于存放数据，模拟队列

    //创建数组队列的构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        front = 0;//指向队列头部，即队列的第一个元素
        rear = 0;//指向队列尾部，指向队列尾的后一个数据
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否满了
    public boolean isFull() {
        //如果尾指针的下一个元素是front，则满了
        return (rear + 1) % maxSize == front;
    }

    //向队列中添加数据
    public void addQueue(int n) {
        //如果满了则输出异常不添加数据
        if (isFull()) {
            System.out.println("错误：队列已满不能再添加");
            return;
        }
        //没满，向当前位置所指的格子中添加数据(因为rear所指的格子是空的)
        arr[rear] = n;
        //指针后移
        rear = (rear + 1) % maxSize;//防止出界
    }

    //从队列中获得数据
    public int getQueue() {
        //如果队列是空的，抛出异常
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，不能取出数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //先输出，再后移front
        //1. 先把front对应的值保留到一个临时变量
        //2. 将front后移
        //3. 将临时变量返回
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //将队列格式化输出
    public void show() {
        if (isEmpty()) {
            System.out.println("队列是空的，没有数据");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + (rear - front + maxSize) % maxSize; i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的，没有数据");
        }
        return arr[front];
    }
}



