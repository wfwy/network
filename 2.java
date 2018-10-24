/*
            *查询主机IP和服务器IP
            * 抛出异常UnknownHostException
            * 获取主机IP    InetAddress的getLocalHost()方法
            * 获取服务器IP   InetAddress的getByName()方法
 */

public class Test2 {
    InetAddress MyIPAddress=null;
    InetAddress MyServer=null;
    public static void main(String[] args) {
        System.out.println("您的主机IP地址是："+new Test2().getMyIPAddress());
        System.out.println("您的服务器IP地址是："+ new Test2().getMyServer());
    }
    public InetAddress getMyIPAddress(){ //获取主机IP
        try {
            MyIPAddress=InetAddress.getLocalHost();
        }catch (UnknownHostException e){
            System.out.println(e.toString());
        }
        return MyIPAddress;
    }
    public InetAddress getMyServer(){  //获取服务器IP
        try {
           MyServer=InetAddress.getByName("");
        }catch (UnknownHostException e){
            System.out.println(e.toString());
        }
        return MyServer;
    }
}