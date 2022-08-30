package cn.xionghuihui.middleware.netty.chat.common.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据包的常量
 *
 * @author 灰灰
 * @since 2022-08-30 21:59:51
 */
public interface PacketConstants {

    /**
     * 默认魔数
     */
    int MAGIC_NUMBER = 0x12345678;

    /**
     * 协议版本
     */
    byte VERSION = 1;

    /**
     * 默认的序列化算法
     */
    SerialAlgorithmEnum SERIAL_ALGORITHM = SerialAlgorithmEnum.JSON;

    /**
     * 序列化算法
     */
    enum SerialAlgorithmEnum {

        /**
         * JSON序列化
         */
        JSON((byte) 1);

        /**
         * 标识
         */
        private final byte code;

        private static final Map<Byte, SerialAlgorithmEnum> CACHE
                = Arrays.stream(values()).collect(Collectors.toMap(SerialAlgorithmEnum::getCode, x -> x));

        SerialAlgorithmEnum(byte code) {
            this.code = code;
        }

        public byte getCode() {
            return code;
        }

        public static SerialAlgorithmEnum findByCode(byte code) {
            return CACHE.get(code);
        }
    }
}
