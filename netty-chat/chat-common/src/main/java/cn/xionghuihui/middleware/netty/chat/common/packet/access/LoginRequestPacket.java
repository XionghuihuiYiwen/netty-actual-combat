package cn.xionghuihui.middleware.netty.chat.common.packet.access;

import cn.xionghuihui.middleware.netty.chat.common.command.CommandEnum;
import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;

/**
 * 登录的数据包
 *
 * @author 灰灰
 * @since 2022-08-30 23:54:35
 */
public class LoginRequestPacket extends Packet {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    @Override
    protected int getCommand() {
        return CommandEnum.LOGIN_REQUEST.getCode();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
