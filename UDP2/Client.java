/*
                     *基于无连接的数据报通信
                     * UDP
                     * 客户端与服务器之间点对点通信
 */
public class Client{
    boolean flag=true;
    DatagramPacket dp1;         //数据发送对象dp1
    DatagramPacket dp2;         //数据接收对象dp2
    String str1;
    String str2;
    String ServerName;
    DatagramSocket ds;           //建立UDP Socket
    public static void main(String[] args){
        new Client().ClinetStart();
    }
    public void ClinetStart(){
        try {
            ServerName = InetAddress.getLocalHost().getHostName();
            ds = new DatagramSocket(8001);      //定义端口号
            System.out.println("服务器名："+ServerName);
            System.out.println("端口号8001");
            System.out.print("客户端：");
            new ClientThread().start();
            while (flag) {
                byte[] inbf = new byte[256];
                dp2 = new DatagramPacket(inbf, inbf.length);        //数据接收
                ds.receive(dp2);                                    //接收数据报分组
                str2 = new String(dp2.getData());                   //将接收到的分组中的数据转化为字符串
                str2 = str2.trim();                                 //去掉字符串中的首位空格
                if (str2.length() > 0) {
                    System.out.println("服务器：" + str2);
                }
                //System.out.println("请输入回复：");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e){
            System.out.println(e);
        }
    }
    class ClientThread extends Thread{
        @Override
        public void run() {
            try {
                while (true){
                    BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
                    str1=bf.readLine();
                    byte[] outbf=new byte[str1.length()];
                    outbf=str1.getBytes();
                    InetAddress address=InetAddress.getByName("");
                    dp1=new DatagramPacket(outbf,outbf.length,address,8000); //数据打包
                    ds.send(dp1);                                                    //发送UDP数据报
                    System.out.print("客户端：");
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }catch (IOException e){
                System.out.println(e);
            }

        }
    }
}