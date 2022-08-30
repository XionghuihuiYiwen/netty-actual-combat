package cn.xionghuihui.middleware.netty.chat.common.packet;

import cn.xionghuihui.middleware.netty.chat.common.command.CommandEnum;
import cn.xionghuihui.middleware.netty.chat.common.constant.PacketConstants;
import cn.xionghuihui.middleware.netty.chat.common.exception.ChatException;
import cn.xionghuihui.middleware.netty.chat.common.serial.SerialContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Objects;

/**
 * 数据包编解码器材
 * @author 灰灰
 * @since 2022-08-30 22:50:34
 */
public class PacketCodec {

    /**
     * 包解码
     * @param byteBuf   数据Buf
     * @return          解出来的包
     */
    public static Packet decode(ByteBuf byteBuf) {
        // 魔数校验
        if (!(PacketConstants.MAGIC_NUMBER == byteBuf.readInt())) {
            return Packet.ERROR;
        }

        // 跳过版本号 TODO 后续可以在这里补充多协议升级的逻辑
        byteBuf.skipBytes(1);

        // 协议
        PacketConstants.SerialAlgorithmEnum serialAlgorithmEnum =
                PacketConstants.SerialAlgorithmEnum.findByCode(byteBuf.readByte());
        if (serialAlgorithmEnum == null) {
            return Packet.ERROR;
        }

        // 指令编码
        int command = byteBuf.readInt();

        CommandEnum commandEnum = CommandEnum.findByCode(command);
        if (commandEnum == null) {
            return Packet.ERROR;
        }

        // 数据包长度
        int length = byteBuf.readInt();

        // 具体的数据
        byte[] data = new byte[length];
        byteBuf.readBytes(data);

        Packet deserialize = new SerialContext(serialAlgorithmEnum).deserialize(data, commandEnum.getSerialClass());
        return Objects.isNull(deserialize) ? Packet.ERROR : deserialize;
    }

    public static ByteBuf encode(Packet packet, ByteBufAllocator allocator) {
        PacketConstants.SerialAlgorithmEnum algorithm = packet.getAlgorithm();
        if (algorithm == null) {
            throw new ChatException("封包时算法指定错误");
        }

        byte[] serialize = new SerialContext(algorithm).serialize(packet);

        ByteBuf result = allocator.ioBuffer();
        // 写入魔数
        result.writeInt(PacketConstants.MAGIC_NUMBER);
        // 写入版本
        result.writeByte(packet.getVersion());
        // 写入算法
        result.writeByte(algorithm.getCode());
        // 写入命令
        result.writeInt(packet.getCommand());
        // 写入数据长度
        result.writeInt(serialize.length);
        // 写入实际的数据
        result.writeBytes(serialize);
        return result;
    }
}
