package test.java.lang;

public class ObjectDemo {
    public static void main(String[] args) throws InterruptedException {
        ObjectCloneDemo object = new ObjectCloneDemo();
//        final Object obj = new Object();
////        obj.wait();
//        Thread t0 = new Thread(new MyThread1(obj));
//        t0.start();
//        Thread.sleep(100);
//        Thread t1 = new Thread(new MyThread2(obj));
//        t1.start();
        ///////

        synchronized (args){
            System.out.println("1111");
        }
    }
}

class MyThread1 implements Runnable{
    private ObjectCloneDemo object;

    MyThread1(ObjectCloneDemo object){
        this.object = object;
    }

    @Override
    public void run() {
        sycMethod();
    }

    private void sycMethod() {
        System.out.println(Thread.currentThread().getName()+"-> before synchronized ");
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+"-> start .");
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"-> start wait ");
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-> end .");
        }
        System.out.println(Thread.currentThread().getName()+"-> end synchronized ");
    }
}

class MyThread2 implements Runnable{
    private ObjectCloneDemo object;

    MyThread2(ObjectCloneDemo object){
        this.object = object;
    }

    @Override
    public void run() {
        sycMethod();
    }

    private void sycMethod() {
        System.out.println(Thread.currentThread().getName()+"-> before synchronized ");
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+"-> start .");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"-> start notify ");
                object.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-> end .");
        }
        System.out.println(Thread.currentThread().getName()+"-> end synchronized ");


    }
}

