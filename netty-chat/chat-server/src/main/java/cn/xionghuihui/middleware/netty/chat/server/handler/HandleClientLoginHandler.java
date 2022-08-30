package cn.xionghuihui.middleware.netty.chat.server.handler;

import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;
import cn.xionghuihui.middleware.netty.chat.common.packet.PacketCodec;
import cn.xionghuihui.middleware.netty.chat.common.packet.access.LoginPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 灰灰
 * @since 2022-08-31 00:14:02
 */
public class HandleClientLoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = PacketCodec.decode((ByteBuf) msg);
        if (packet instanceof LoginPacket) {
            System.out.println("((LoginPacket) packet).getUserId() = " + ((LoginPacket) packet).getUserId());
        }
        super.channelRead(ctx, msg);
    }
}
