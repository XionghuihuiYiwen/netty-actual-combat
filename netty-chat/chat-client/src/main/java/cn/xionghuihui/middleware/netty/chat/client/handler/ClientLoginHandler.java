package cn.xionghuihui.middleware.netty.chat.client.handler;

import cn.xionghuihui.middleware.netty.chat.common.packet.PacketCodec;
import cn.xionghuihui.middleware.netty.chat.common.packet.access.LoginPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * 登录的处理器
 * @author 灰灰
 * @since 2022-08-31 00:08:47
 */
public class ClientLoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setUserId(UUID.randomUUID().toString());
        ctx.channel().writeAndFlush(PacketCodec.encode(loginPacket, ctx.alloc()));
        super.channelActive(ctx);
    }
}
