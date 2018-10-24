/*
                 *基于无连接的数据报通信
                 * UDP
                 * 客户端发送，服务器接收
 */

public class Client{
    public static void main(String[] args){
       new Client();
    }
    public Client(){
        ClientThread ct=new ClientThread();
        ct.start();
    }
    class ClientThread extends Thread{          //客户端类线程
        @Override
        public void run() {
            String str;
            String ServerName= null;
            try {
                ServerName = InetAddress.getLocalHost().getHostName();
                System.out.println("请发送消息给服务器:"+ServerName);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                DatagramSocket ds=new DatagramSocket();     //建立UDP Socket端口对象
                DatagramPacket dp;                          //发送数据对象
                while (true){
                    BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("请输入信息：");
                    str=bf.readLine();
                    byte[] outbf=new byte[str.length()];
                    outbf=str.getBytes();
                    InetAddress address=InetAddress.getByName(ServerName);
                    dp=new DatagramPacket(outbf,outbf.length,address,8000);     //数据打包
                    ds.send(dp);
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
