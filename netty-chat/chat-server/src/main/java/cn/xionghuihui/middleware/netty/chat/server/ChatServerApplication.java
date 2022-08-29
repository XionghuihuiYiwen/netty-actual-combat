package cn.xionghuihui.middleware.netty.chat.server;


import cn.xionghuihui.middleware.netty.chat.server.boot.ChatServer;

/**
 * 聊天服务端应用
 *
 * @author 灰灰
 * @since 2022-08-29 23:18:54
 */
public class ChatServerApplication {

    public static void main(String[] args) {
        new ChatServer().start();
    }
}
