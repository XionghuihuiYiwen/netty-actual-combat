package cn.xionghuihui.middleware.netty.chat.common.packet.access;

import cn.xionghuihui.middleware.netty.chat.common.command.CommandEnum;
import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;

/**
 * 登录的数据包
 *
 * @author 灰灰
 * @since 2022-08-30 23:54:35
 */
public class LoginPacket extends Packet {

    /**
     * 用户ID
     */
    private String userId;

    @Override
    protected int getCommand() {
        return CommandEnum.CLIENT_LOGIN.getCode();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
