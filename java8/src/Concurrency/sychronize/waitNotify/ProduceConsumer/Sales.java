package Concurrency.sychronize.waitNotify.ProduceConsumer;

public class Sales {
        //initialize the count of product as 0
        int product = 0;
        public void produce(){
        produceProcess();
        //给其他线程足够的时间抢夺锁，非必要
//        try {
//            Thread.currentThread().sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    public void consume(){
        consumeProcess();
//        try {
//            Thread.currentThread().sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    //线程同步 避免安全问题
        private synchronized void produceProcess () {
            if (product == 20) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                product++;
                notify();
                //在这里出现了线程安全问题 自增后尚未打印，就被消费--，于是打印的时候打印成原来的数据
                System.out.println("生产一个商品，剩余商品：" + product);
            }
        }
        private synchronized void consumeProcess() {
            if (product == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                product--;
                notify();
                System.out.println("消费一个商品，剩余商品：" + product);
            }
        }
    }


class Producer extends  Thread{
    private Sales sales = null;

    public Producer(Sales sales) {
        this.sales = sales;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sales.produce();
        }
    }

}

class Consumer extends  Thread{
        private Sales sales = null ;

        public Consumer(Sales sales) {
            this.sales = sales;
        }

        @Override
        public  void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sales.consume();
            }
        }

    }






