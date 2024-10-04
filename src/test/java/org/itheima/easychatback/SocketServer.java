package org.itheima.easychatback;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketServer {
    public static void main(String[] args)  {
        ServerSocket server = null;
        Map<String, Socket> CLIENT_Map = new HashMap();
        try{
           server=new ServerSocket(1024);
            System.out.println("服务端启动，等待连接...");

            while(true){
                Socket socket=server.accept();
                String ip=socket.getInetAddress().getHostAddress();
                System.out.println("ip:"+ip+"端口："+socket.getPort()+"的用户已连接");
                String ClientKey=ip+socket.getPort();
                CLIENT_Map.put(ClientKey,socket);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true)
                        {
                            try{
                                InputStream in=socket.getInputStream();
                                InputStreamReader inputStreamReader=new InputStreamReader(in,"utf-8");
                                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                                String readData=bufferedReader.readLine();
                                System.out.println("收到客户端消息："+readData);



                                    CLIENT_Map.forEach((k,v)->{
                                        try {
                                            OutputStream outputStream = v.getOutputStream();
                                            PrintWriter printWriter = new PrintWriter( new OutputStreamWriter(outputStream,"utf-8") );
                                            printWriter.println(socket.getPort()+":"+readData);
                                            printWriter.flush();
                                        }
                                        catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    });

                            }catch (Exception e)
                            {
                                e.printStackTrace();
                                break;
                            }

                        }
                    }
                }).start();

            }


        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
