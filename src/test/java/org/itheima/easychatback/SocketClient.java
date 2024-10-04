package org.itheima.easychatback;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {


    public static void main(String[] args) {
        Socket socket=null;

        try{
            socket= new Socket("127.0.0.1",1024);
            OutputStream out=socket.getOutputStream();
            PrintWriter pw=new PrintWriter(out);
            System.out.println("请输入发送内容：");

            new Thread(()->{
                while(true)
                {
                    //发消息
                    Scanner scanner=new Scanner(System.in);
                    String input=scanner.nextLine();

                    try {
                        pw.println(input);
                        pw.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

            InputStream inputStream=socket.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            new Thread(()->{
                while(true)
                {
                    //收消息
                    try{
                        String readData=bufferedReader.readLine();
                        System.out.println("收到来自服务器的消息："+readData);
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }



                }
            }).start();



        }catch(IOException e){
            e.printStackTrace();
        }


    }

}
