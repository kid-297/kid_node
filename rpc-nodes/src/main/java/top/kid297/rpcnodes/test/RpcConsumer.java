package top.kid297.rpcnodes.test;

/**
 * RpcConsumer 
 *  
 * @author william.liangf 
 */  
public class RpcConsumer {  
      
    public static void main(String[] args) throws Exception {  
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 8080);
        for (int i = 0; i <= 1; i ++) {
            String hello = service.hello("cxh" + i);
            System.out.println(hello);  
            Thread.sleep(1000);  
        }  
    }  
      
}  