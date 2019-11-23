package double_and_bigdecial;

import java.math.BigDecimal;

public class DoubleAndBigDecimalTest {

    /**
     * 将double类型数据转为字符串（如将18.4转为1840，如果需要1840.0，把int强转去掉即可）
     * @param d
     * @return
     */
    public static String double2String(double d){
        BigDecimal bg = new BigDecimal(d * 3);
        double doubleValue = bg.setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal mData = bg.setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println(bg);
        System.out.println(mData);
//        System.out.println(doubleValue);
        return  String.valueOf(doubleValue);
    }

    public static void main(String[] args) {
        double2String(18.4);
        BigDecimal mData = new BigDecimal("9.655").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(mData);
        System.out.println(18.4 * 3);
    }

}
