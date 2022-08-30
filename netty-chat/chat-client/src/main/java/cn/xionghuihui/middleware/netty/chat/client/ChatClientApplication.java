package cn.xionghuihui.middleware.netty.chat.client;

import cn.xionghuihui.middleware.netty.chat.client.boot.ChatClient;

/**
 * @author 灰灰
 * @since 2022-08-30 21:20:14
 */
public class ChatClientApplication {

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.start(8080);
    }
}
