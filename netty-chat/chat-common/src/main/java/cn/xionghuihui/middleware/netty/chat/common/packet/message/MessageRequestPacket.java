package cn.xionghuihui.middleware.netty.chat.common.packet.message;

import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;

/**
 * @author 灰灰
 * @since 2022-09-05 01:09:58
 */
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    protected int getCommand() {
        return 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
