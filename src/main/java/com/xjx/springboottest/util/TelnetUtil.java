package com.xjx.springboottest.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TelnetUtil {
 
    /**
     * 测试telnet 机器端口的连通性
     * @param hostname
     * @param port
     * @param timeout
     * @return
     */
    public static boolean telnet(String hostname, int port, int timeout){
        Socket socket = new Socket();
        boolean isConnected = false;
        try {
            socket.connect(new InetSocketAddress(hostname, port), timeout); // 建立连接
            isConnected = socket.isConnected(); // 通过现有方法查看连通状态
        } catch (IOException e) {
            System.out.println("false");        // 当连不通时，直接抛异常，异常捕获即可
        }finally{
            try {
                socket.close();   // 关闭连接
            } catch (IOException e) {
                System.out.println("false");
            }
        }
        return isConnected;
    }
 
    public static void main(String[] args) {
        String hostname = args[0];    // hostname 可以是主机的 IP 或者 域名
        int port = Integer.valueOf(args[1]);
        int timeout = 200;
        boolean isConnected = telnet("192.168.3.123", port, timeout);
        System.out.println("isConnected:"  + isConnected);
    }
}
