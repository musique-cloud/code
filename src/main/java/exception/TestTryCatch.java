package exception;

public class TestTryCatch {

    public static void main(String[] args) {
        TestTryCatch test = new TestTryCatch();
        System.out.println(test.fun2());
    }
    public int fun() {
        int i = 10;
        try {
            //doing something
            int a = i / 0;
            return i;
        }catch(Exception e){
            return i;
        }finally{
            i = 20;
            return 50;
        }
    }
    public StringBuilder fun2()
    {
        StringBuilder s = new StringBuilder("Hello");
        try
        {
            //doing something
            s.append("Word");

            return s;
        }catch(Exception e){
            return s;
        }finally{
            s.append("finally");
            StringBuilder s2 = new StringBuilder("finally");
            int a = 1 / 0;
            s = s2;
        }
    }
}
