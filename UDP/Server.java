/*
            *基于无连接的数据报通信
            * UDP
            * 客户端发送，服务器接收
 */

public class Server {

    public static void main(String[] args){
        new Server();
    }
    public Server(){                        //构造方法
        ServerThread st=new ServerThread();
        st.start();
    }

    class ServerThread extends Thread{          //服务器类线程
        @Override
        public void run() {
            String str;
            try {
                DatagramSocket ds=new DatagramSocket(8000);  //定义端口号
                System.out.print("服务器名：");
                System.out.println(InetAddress.getLocalHost().getHostName()); //显示计算机名称
                while (true){
                    byte[] inbf=new byte[256];
                    DatagramPacket dp=new DatagramPacket(inbf,inbf.length);//设置接收信息
                    ds.receive(dp);                         //接收数据报分组
                    str=new String(dp.getData());           //将接收到的分组中的数据转化为字符串
                    str=str.trim();                         //去掉字符串中的首位空格
                    if (str.length()>0){
                        int pot=dp.getPort();                //获取远程端口号
                        System.out.println("远程端口号："+pot);
                        System.out.println("服务器已接收信息："+str);
                    }
                }
            }catch (IOException e){
                return;
            }
        }
    }
}