package cn.xionghuihui.middleware.netty.chat.common.serial;

import cn.xionghuihui.middleware.netty.chat.common.constant.PacketConstants;
import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;

/**
 * 序列化器
 *
 * @author 灰灰
 * @since 2022-08-30 22:42:13
 */
public interface Serializer {

    /**
     * 序列化为字节数组
     * @param packet 数据包
     * @return       字节数组
     */
    byte[] serialize(Packet packet);

    /**
     * 字节数组序列化为数据包对象
     */
    Packet deserialize(byte[] bytes, Class<? extends Packet> packetClass);
}
