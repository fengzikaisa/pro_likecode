package com.likecode.common.utils;

import java.nio.ByteBuffer;

/**
 * Created by wangkai on 2016/12/15.
 */
public class ByteUtil {

    public static String upLevel(String target, int offset) {
        byte[] bytes = hexToBytes(target);
        int i = Double.valueOf(Math.ceil(offset / 8.0d)).intValue() - 1;
        byte b = bytes[i];
        int pos = offset % 8;
        bytes[i] = setBitValue(b, pos, (byte) 1);

        return bytesToHex(bytes);
    }

    public static int upLevelCount(String target) {
        int count = 0;
        int i;

        byte[] bytes = hexToBytes(target);
        for (byte b : bytes) {
            for (i = 0; i < 8; i++) {
                if (getBitValue(b, i) == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 获取bit位
     *
     * @param source 目标字节
     * @param pos    位置(0~7从右往左)
     * @return 0 or 1
     */
    public static byte getBitValue(byte source, int pos) {
        return (byte) ((source >> pos) & 1);
    }

    /**
     * 设置bit位
     *
     * @param source 目标字节
     * @param pos    位置(0~7从右往左)
     * @param value  0 or 1
     * @return
     */
    public static byte setBitValue(byte source, int pos, byte value) {
        byte mask = (byte) (1 << pos);
        if (value > 0) {
            source |= mask;
        } else {
            source &= (~mask);
        }

        return source;
    }

    public static String bytesToHex(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.clear();

        int i;
        String hex;
        StringBuilder hexBuilder = new StringBuilder(32);
        while (buffer.hasRemaining()) {
            i = buffer.get() & 0xFF;
            hex = Integer.toHexString(i);
            if (hex.length() == 1) {
                hexBuilder.append("0");
            }
            hexBuilder.append(hex);
        }

        return hexBuilder.toString();
    }

    public static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }

        return bytes;
    }

//    public static void main(String[] args) {
//        String target = "0000000000000000";
//        for(int i = 1; i <= 64; i++){
//            target = upLevel(target, i);
//            System.out.println(target+"$"+upLevelCount(target));
//        }
//    }
}
