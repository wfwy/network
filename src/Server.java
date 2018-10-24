/*
                *基于连接的Socket通信
                * TCP/IP Socket
                * 服务器端ServerSocket
 */
public class Server implements Runnable{
    ServerSocket server=null;
    Socket clinetSocket;            //标记当前C/S通信中的Socket对象
    boolean flag=true;              //标记是否结束
    BufferedReader brin;             //输入流
    DataOutputStream dout;           //输出流

    public static void main(String[] args){
        new Server().ServerStart();
    }

    public void ServerStart(){
        try {
            server = new ServerSocket(8080);                    //建立监听，端口号8080
            System.out.println("端口号:"+server.getLocalPort());
            while (flag){
                clinetSocket=server.accept();                        //接收链接
                System.out.println("链接已经建立完毕！");
                InputStream is=clinetSocket.getInputStream();
                brin=new BufferedReader(new InputStreamReader(is));
                OutputStream os=clinetSocket.getOutputStream();
                dout=new DataOutputStream(os);
                new Thread(this).start();                        //线程启动
                String aLine;
                while ((aLine=brin.readLine())!=null){                 //从客户端读入信息
                    System.out.println(aLine);
                    if (aLine.equals("bye")){
                        flag=false;
                        new Thread(this).interrupt();              //线程中断
                        break;
                    }
                }
                dout.close();
                os.close();
                brin.close();
                is.close();
                clinetSocket.close();                                 //关闭Socket
                System.exit(0);

            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void run() {
        while (true){
            try {
                int ch;
                while ((ch= System.in.read())!=-1){             //从键盘接收字符并向客户端发送
                    dout.write((byte)ch);
                    if (ch=='\n')
                        dout.flush();                           //将缓冲区内容向客户端输出
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void finalize(){
        try {
            server.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}