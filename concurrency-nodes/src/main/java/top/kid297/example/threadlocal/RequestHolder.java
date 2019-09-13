package top.kid297.example.threadlocal;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/7/3 15:45
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHoler = new ThreadLocal<>();

    public static void add(Long id){
        requestHoler.set(id);
    }

    public static Long getId(){
        return requestHoler.get();
    }

    public static void remove(){
        requestHoler.remove();
    }
}
