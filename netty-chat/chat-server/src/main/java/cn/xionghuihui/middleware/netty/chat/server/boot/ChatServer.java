package cn.xionghuihui.middleware.netty.chat.server.boot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.NettyRuntime;

import java.net.BindException;
import java.util.concurrent.TimeUnit;

/**
 * 服务
 *
 * @author 灰灰
 * @since 2022-08-29 23:23:45
 */
public class ChatServer {

    /**
     * 服务监听端口
     */
    private int port = 8080;

    /**
     * 实际绑定的端口
     */
    private int inUsePort = port;

    /**
     * 端口冲突是否自增端口
     */
    private boolean incrementPortInUse = false;

    /**
     * 最大端口自增次数
     */
    private int maxIncrementPortTime = 10;

    /**
     * 已经自增的次数
     */
    private int currentIncrementPortTime = 0;
    /**
     * 服务启动
     */
    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(NettyRuntime.availableProcessors() * 2);

        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                    }
                });

        int bindPort = this.port;
        doBind(serverBootstrap, bindPort);
    }

    private void doBind(ServerBootstrap serverBootstrap, int port) {
        // 启动
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                this.setInUsePort(port);
                System.out.println("服务启动成功，端口监听[" + port + "]");
            } else {
                System.out.println("端口绑定[" + port + "]失败!");
                // 如果设定了绑定冲突递增端口号，并且异常是 BindException ，就做端口自增操作
                if (isIncrementPortInUse() && future.cause() instanceof BindException) {
                    if (++currentIncrementPortTime < maxIncrementPortTime) {
                        System.out.println("启动端口自增绑定.....");
                        TimeUnit.SECONDS.sleep(2);
                        doBind(serverBootstrap, port + 1);
                    } else {
                        System.out.println("启动失败....");
                    }
                }
            }
        });
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isIncrementPortInUse() {
        return incrementPortInUse;
    }

    public void setIncrementPortInUse(boolean incrementPortInUse) {
        this.incrementPortInUse = incrementPortInUse;
    }

    public int getInUsePort() {
        return inUsePort;
    }

    public void setInUsePort(int inUsePort) {
        this.inUsePort = inUsePort;
    }

    public int getMaxIncrementPortTime() {
        return maxIncrementPortTime;
    }

    public void setMaxIncrementPortTime(int maxIncrementPortTime) {
        this.maxIncrementPortTime = maxIncrementPortTime;
    }

    public int getCurrentIncrementPortTime() {
        return currentIncrementPortTime;
    }

    public void setCurrentIncrementPortTime(int currentIncrementPortTime) {
        this.currentIncrementPortTime = currentIncrementPortTime;
    }
}
