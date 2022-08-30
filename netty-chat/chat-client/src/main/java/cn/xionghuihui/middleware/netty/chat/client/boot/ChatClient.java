package cn.xionghuihui.middleware.netty.chat.client.boot;

import cn.xionghuihui.middleware.netty.chat.client.handler.ClientHelloWorldHandler;
import cn.xionghuihui.middleware.netty.chat.client.handler.ClientLoginHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author 灰灰
 * @since 2022-08-30 21:20:55
 */
public class ChatClient {

    public void start(int port) {
        NioEventLoopGroup work = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap()
                .group(work)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientLoginHandler());
                    }
                });
        bootstrap.connect("127.0.0.1", port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        });
    }
}
