/*
                *基于无连接的数据报通信
                * UDP
                * 客户端与服务器之间点对点通信
 */
public class Server {
    boolean flag=true;
    String str1;
    String str2;
    String ServerName;
    DatagramPacket dp1;     //数据发送对象dp1
    DatagramPacket dp2;     //数据接收对象dp2
    DatagramSocket ds;      //建立UDP Socket
    public static void main(String[] args) {

        new  Server().ServerStart();
    }
    public void ServerStart(){
        try {
            ServerName = InetAddress.getLocalHost().getHostName();
            ds = new DatagramSocket(8000);      //定义端口号
            System.out.println("服务器名："+ServerName);
            System.out.println("端口号8000");
            System.out.print("服务器：");
            ServerThread s=new ServerThread();
            new Thread(s).start();
            //System.out.println("请发送消息给服务器:"+ServerName);
            //System.out.println(InetAddress.getLocalHost().getHostName());
            while (flag) {
                byte[] inbf = new byte[256];
                dp2 = new DatagramPacket(inbf, inbf.length);    //数据接收
                ds.receive(dp2);                                //接收数据报分组
                str2 = new String(dp2.getData());               //将接收到的分组中的数据转化为字符串
                str2 = str2.trim();                             //去掉字符串中的首位空格
                if (str2.length() > 0) {
                    System.out.println("客户端：" + str2);
                }
            }
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return;
        }
    }
    class ServerThread implements Runnable{
        @Override
        public void run() {
            try {
                while (true){
                    BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
                    str1=bf.readLine();                                             //键盘获取数据
                    byte[] outbf=new byte[str1.length()];                         //储存数据
                    outbf=str1.getBytes();
                    InetAddress address=InetAddress.getByName("");
                    dp1=new DatagramPacket(outbf,outbf.length,address,8001);//数据打包
                    ds.send(dp1);                                                  //发送UDP数据报
                    System.out.print("服务器：");
                }
            }catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                return;
            }
        }
    }
}