package cn.xionghuihui.middleware.netty.chat.common.serial;

import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;
import com.alibaba.fastjson.JSON;

/**
 * @author 灰灰
 * @since 2022-08-31 00:18:07
 */
public class JsonSerializer implements Serializer{

    static JsonSerializer INSTANCE = new JsonSerializer();

    @Override
    public byte[] serialize(Packet packet) {
        return JSON.toJSONBytes(packet);
    }

    @Override
    public Packet deserialize(byte[] bytes, Class<? extends Packet> packetClass) {
        return JSON.parseObject(bytes, packetClass);
    }
}
