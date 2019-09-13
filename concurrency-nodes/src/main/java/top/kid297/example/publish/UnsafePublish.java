package top.kid297.example.publish;

import lombok.extern.slf4j.Slf4j;
import top.kid297.annoations.NotThreadSafe;

import java.util.Arrays;

/**
 * @version V1.0
 * @Description: 发布对象
 * @Auther: ly
 * @Date: 2019/7/3 10:29
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
