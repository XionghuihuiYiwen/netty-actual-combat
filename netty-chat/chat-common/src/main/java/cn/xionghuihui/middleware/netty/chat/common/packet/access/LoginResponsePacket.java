package cn.xionghuihui.middleware.netty.chat.common.packet.access;

import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;

/**
 * 登录的响应结果
 *
 * @author 灰灰
 * @since 2022-09-05 01:14:52
 */
public class LoginResponsePacket extends Packet {

    @Override
    protected int getCommand() {
        return 0;
    }
}
