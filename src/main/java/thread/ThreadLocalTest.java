package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

    String str = "hello";
    Long lN = 3L;

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    ThreadLocal<Object> objLocal = new ThreadLocal<Object>();

    public void set(String s, Long l) {
        longLocal.set(l);
        stringLocal.set(s);
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    int pos;
    int count;
    byte[] buf;

    public synchronized int read(byte b[], int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }

        if (pos >= count) {
            return -1;
        }

        int avail = count - pos;
        if (len > avail) {
            len = avail;
        }
        if (len <= 0) {
            return 0;
        }
        System.arraycopy(buf, pos, b, off, len);
        pos += len;
        return len;
    }

    public static void main(String[] args) throws InterruptedException {


        final ThreadLocalTest test = new ThreadLocalTest();

        A a = new A();
        System.out.println(a);
        test.objLocal.set(a);
        System.out.println(test.objLocal.get());

        AtomicInteger nextHashCode =
                new AtomicInteger();
        System.out.println(nextHashCode);
        test.set(test.str, test.lN);
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread1 = new Thread(){
            public void run() {

                test.objLocal.set(a);
                System.out.println(test.objLocal.get());
                Object o = test.objLocal.get();
                ((A) o).setA("值被修改了");

                test.set(test.str, test.lN);
                String t = test.stringLocal.get();
                t = "修改";
                System.out.println(test.getLong());
                System.out.println(test.getString());
                System.out.println(test.objLocal.get());
            }
        };

        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
        System.out.println(test.objLocal.get());
    }

}


class A {

    private String a = "1";

    public String getA() {
        return a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }

    public void setA(String a) {
        this.a = a;
    }
}