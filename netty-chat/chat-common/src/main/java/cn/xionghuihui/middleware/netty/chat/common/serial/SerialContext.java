package cn.xionghuihui.middleware.netty.chat.common.serial;

import cn.xionghuihui.middleware.netty.chat.common.constant.PacketConstants;
import cn.xionghuihui.middleware.netty.chat.common.packet.Packet;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化策略对象
 *
 * @author 灰灰
 * @since 2022-08-30 22:41:41
 */
public class SerialContext implements Serializer {

    /**
     * 序列化算法
     */
    private final PacketConstants.SerialAlgorithmEnum serialAlgorithm;

    /**
     * 序列化器缓存
     */
    private static final Map<PacketConstants.SerialAlgorithmEnum, Serializer> REGISTER = new HashMap<>();

    /**
     * 获取对应的序列化器
     */
    public static Serializer getSerializer(PacketConstants.SerialAlgorithmEnum serialAlgorithm) {
        // FIXME 这里没有判断空或者没拿到的情况
        return REGISTER.get(serialAlgorithm);
    }

    /**
     * 注册
     * @param serialAlgorithm   序列化算法
     * @param serializer        具体的序列化器
     */
    public static void register(PacketConstants.SerialAlgorithmEnum serialAlgorithm, Serializer serializer) {
        REGISTER.put(serialAlgorithm, serializer);
    }

    static {
        register(PacketConstants.SerialAlgorithmEnum.JSON, JsonSerializer.INSTANCE);
    }

    public SerialContext(PacketConstants.SerialAlgorithmEnum serialAlgorithm) {
        this.serialAlgorithm = serialAlgorithm;
    }

    @Override
    public byte[] serialize(Packet packet) {
        return getSerializer(serialAlgorithm).serialize(packet);
    }

    @Override
    public Packet deserialize(byte[] bytes, Class<? extends Packet> packetClass) {
        return getSerializer(serialAlgorithm).deserialize(bytes, packetClass);
    }
}
