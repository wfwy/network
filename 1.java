import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
/*
        *使用URL访问网络资源
        * 1.创建URL对象
        * 2.利用openStream()获取InputStream类的对象
        * 3通过InputStream对象读取文件内容
 */
public class Test2 {
    public static void main(String[] args){
        String urlName="http://www.baidu.com";
        if (args.length>0) urlName=args[0];
        new Test2().display(urlName);
    }
    public void display(String urlName){
        try {
            URL url=new URL(urlName);                                  //创建URL对象
            InputStreamReader in=new InputStreamReader(url.openStream());
            BufferedReader br=new BufferedReader(in);
            String aLine;
            while ((aLine=br.readLine())!=null)                         //从流中读取一行显示
                System.out.println(aLine);
        }catch (MalformedURLException e){
            System.out.println(e);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}