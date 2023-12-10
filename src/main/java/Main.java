import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        //端口号
        int port = 8080;
        //监听端口
        ServerSocket socket = new ServerSocket(port);
        //重复监听
        while(true) {
            Socket accept = socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String requestLine = reader.readLine();
            System.out.println("请求行:");
            System.out.println(requestLine);
            System.out.println("请求头:");
            //请求头
            String requestHeader;
            while(!(requestHeader = reader.readLine()).isEmpty()) {
                System.out.println(requestHeader);
            }
            //返回响应
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            writer.write("HTTP/1.1 200 OK");
            writer.flush();
            writer.close();
            reader.close();
            accept.close();
        }
    }
}
