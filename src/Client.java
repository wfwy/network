*
                *基于连接的Socket通信
                * TCP/IP Socket
                * 客户端Socket
 */
public class Client implements Runnable{
    Socket clinetSocket;
    boolean flag=true;
    BufferedReader brin;
    DataOutputStream dout;
    public static void main(String[] args){
        new Client().ClinetStart();
    }
    public void ClinetStart(){
        try {
            clinetSocket=new Socket("localhost",8080);
            System.out.println("已建立连接！");
            while (flag){
                InputStream is=clinetSocket.getInputStream();
                brin=new BufferedReader(new InputStreamReader(is));
                OutputStream os=clinetSocket.getOutputStream();
                dout=new DataOutputStream(os);
                new Thread(this).start();
                String aLine;
                while ((aLine=brin.readLine())!=null){
                    System.out.println(aLine);
                    if (aLine.equals("bye")){
                        flag=false;
                        new Thread(this).interrupt();
                        break;
                    }
                }
                dout.close();
                os.close();
                brin.close();
                is.close();
                clinetSocket.close();
                System.exit(0);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void run() {
        while (true){
            try {
                int ch;
                while ((ch= System.in.read())!=-1){
                    dout.write((byte)ch);
                    if (ch=='\n')
                        dout.flush();
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}