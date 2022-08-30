package cn.xionghuihui.middleware.netty.chat.common.command;

import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;
import cn.xionghuihui.middleware.netty.chat.common.packet.access.LoginPacket;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 命令枚举
 *
 * @author 灰灰
 * @since 2022-08-30 23:49:08
 */
public enum CommandEnum {

    /**
     * 登录命令
     */
    CLIENT_LOGIN(1, LoginPacket.class);

    private final int code;

    private final Class<? extends Packet> serialClass;

    private static Map<Integer, CommandEnum> CACHE
            = Arrays.stream(values()).collect(Collectors.toMap(CommandEnum::getCode, e -> e));

    CommandEnum(int code, Class<? extends Packet> serialClass) {
        this.code = code;
        this.serialClass = serialClass;
    }

    public static CommandEnum findByCode(int code) {
        return CACHE.get(code);
    }

    public int getCode() {
        return code;
    }

    public Class<? extends Packet> getSerialClass() {
        return serialClass;
    }
}
