package top.kid297.example.string;

import java.math.BigDecimal;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/7/5 11:34
 */
public class test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(1.99);
        BigDecimal aDouble = BigDecimal.valueOf(33.99);
        System.out.println(bigDecimal.add(aDouble) );

    }
}
