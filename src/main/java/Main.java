import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String STATIC_RESOURCE_PATH = "src/main/resources/static/";
    private static final String PAGE_NOT_FOUND_404_PATH = "404.txt";
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while(true){
            InputStream inputStream = null;
            FileInputStream fileInputStream = null;
            OutputStream outputStream = null;
            BufferedReader bfr = null;
            try{
                //获取输入流
                Socket accept = server.accept();
                //获取输出流
                outputStream = accept.getOutputStream();
                inputStream = accept.getInputStream();
                //对流进行包装
                bfr = new BufferedReader(new InputStreamReader(inputStream));
                String firstLine = bfr.readLine();
                if (firstLine==null||"".equals(firstLine)){
                    return;
                }
                String httpPath = firstLine.split(" ")[1];
                //读取文件
                String path = STATIC_RESOURCE_PATH+httpPath;
                //创建文件对象
                File file = new File(path);
                //判断文件是否存在,不存在直接返回 404
                if (!file.exists()){
                    fileInputStream = new FileInputStream(STATIC_RESOURCE_PATH+PAGE_NOT_FOUND_404_PATH);
                    int b = 0;
                    byte[] bytes = new byte[1024];
                    while((b=fileInputStream.read(bytes))!=-1){
                        outputStream.write(bytes,0,b);
                    }
                }else{
                    fileInputStream = new FileInputStream(file);
                    //写文件

                    //响应行
                    outputStream.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
                    //响应头
                    outputStream.write(("Content-Length:"+file.length()+"\r\n").getBytes(StandardCharsets.UTF_8));
                    //响应空行
                    outputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    //响应内容
                    int b = 0;
                    byte[] bytes = new byte[1024];
                    while((b=fileInputStream.read(bytes))!=-1){
                        outputStream.write(bytes,0,b);
                    }
                }
                outputStream.flush();

            }catch (Exception e){
                e.printStackTrace();
            }finally{
                //释放资源
                if (outputStream!=null){
                    outputStream.close();
                }
                if (bfr!=null){
                    bfr.close();
                }
                if (fileInputStream!=null){
                    fileInputStream.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }

            }
        }

    }
}
