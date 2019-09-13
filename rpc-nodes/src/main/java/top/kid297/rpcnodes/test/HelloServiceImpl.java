package top.kid297.rpcnodes.test;

/**
 * HelloServiceImpl 
 *  
 * @author william.liangf 
 */  
public class HelloServiceImpl implements HelloService {  
  
    public String hello(String name) {  
        return "Hello " + name;  
    }  
  
}  