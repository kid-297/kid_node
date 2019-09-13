package top.kid297.example.publish;

import lombok.extern.slf4j.Slf4j;
import top.kid297.annoations.NotRecommend;
import top.kid297.annoations.NotThreadSafe;

/**
 * @version V1.0
 * @Description: 对象逸出
 * @Auther: ly
 * @Date: 2019/7/3 10:37
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    public class InnerClass {
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
