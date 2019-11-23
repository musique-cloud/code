package exception;

public class ExcepTest {
    public static void main(String[] args) {
        try {
            int[] data = new int[]{1,2,3};
            System.out.println(getDataByIndex(-1,data));
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        } finally {
            System.out.println("finally");
            return;

        }

    }

    public static int getDataByIndex(int index,int[] data) {
        if(index<0||index>=data.length)
            throw new ArrayIndexOutOfBoundsException("数组下标越界");
        return data[index];
    }
}