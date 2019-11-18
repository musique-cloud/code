package tcp.heart;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHeart extends Thread {

    private ServerSocket serverSocket = null;

    private Object o = new Object();

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9090);
            while (true) {
                Socket client = serverSocket.accept();
                synchronized (o) {
                    new Thread(new Client(client)).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("开始检测客户端是否在线...");
        new ServerHeart().start();
    }

    /**
     * 客户端线程
     */
    class Client implements Runnable {
        Socket client;
        public Client(Socket client) {
            this.client = client;
        }
        @Override
        public void run() {
            try {
                while (true) {
                    ObjectInput in = new ObjectInputStream(client.getInputStream());
                    Entity entity = (Entity) in.readObject();
                    System.out.println(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
