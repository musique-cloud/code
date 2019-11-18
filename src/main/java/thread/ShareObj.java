package thread;

public class ShareObj {

    int a = 10;

    public static void main(String[] args) throws InterruptedException {
        ShareObj shareObj = new ShareObj();

        Runnable r1 = new ThreadA(shareObj);
        Runnable r2 = new ThreadB(shareObj);

        Thread t1 = new Thread(r1);
        t1.start();
        t1.join();
        System.out.println(shareObj.a);

        Thread t2 = new Thread(r2);
        t2.start();
        t2.join();
        System.out.println(shareObj.a);

    }

}

class ThreadA implements Runnable {

    ShareObj s;

    public ThreadA(ShareObj s) {
        this.s = s;
    }

    @Override
    public void run() {
        s.a = 100;
    }
}

class ThreadB implements Runnable {

    ShareObj s;

    public ThreadB(ShareObj s) {
        this.s = s;
    }

    @Override
    public void run() {
        s.a = 10000;
    }
}