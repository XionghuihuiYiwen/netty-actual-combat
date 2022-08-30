package cn.xionghuihui.middleware.netty.chat.common.packet;

import cn.xionghuihui.middleware.netty.chat.common.constant.PacketConstants;

/**
 * 发送的包
 *
 * @author 灰灰
 * @since 2022-08-30 21:55:47
 */
public abstract class Packet {

    public static final Packet ERROR = null;

    /**
     * 协议版本
     */
    protected byte getVersion() {
        return PacketConstants.VERSION;
    }

    /**
     * 协议算法
     */
    protected PacketConstants.SerialAlgorithmEnum getAlgorithm() {
        return PacketConstants.SERIAL_ALGORITHM;
    }

    /**
     * 命令
     */
    protected abstract int getCommand();


}
