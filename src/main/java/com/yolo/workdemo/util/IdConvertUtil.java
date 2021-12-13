package com.yolo.workdemo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class IdConvertUtil {

    private static final int LENGTH = 6;

    private static final int FACTOR = 3;

    private static final String SALT = "fly";

    /**
     * 加密
     *
     * @param id    原始任务id
     * @param botId 机器id(废弃)
     * @return 加密后id
     */
    public static String encrypt(Long id, Long botId) {
//        StringBuilder cs = new StringBuilder();
//        String s = String.valueOf(id);
//        for (int i = 0; i < LENGTH - s.length(); i++) {
//            cs.append("0");
//        }
//        cs.append(id);
//        return botId * FACTOR + cs.toString();
        return encrypt(id);
    }

    /**
     * 解密
     *
     * @param encryptId 密文id
     * @param botId     机器id(废弃)
     * @return 原始id
     */
    public static String decrypt(String encryptId, Long botId) {
//        return encryptId.replaceFirst(String.valueOf(botId * FACTOR), "");
        return decrypt(encryptId);
    }

    /**
     * 根据加密id获取真实id
     *
     * @param aesId 加密后id
     * @param botId 机器id(废弃)
     * @return
     */
    public static Long getRealId(String aesId, Long botId) {
        if (StringUtils.isBlank(aesId)) {
            return null;
        }
        String id = IdConvertUtil.decrypt(aesId, botId);
        return StringUtils.isBlank(id) ? null : Long.parseLong(id);
    }

    /**
     * @param id 任务id
     * @return
     */
    private static String encrypt(Long id) {
        String idStr = String.valueOf(id);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < LENGTH - idStr.length(); i++) {
            builder.append("0");
        }
        idStr = builder.append(idStr).toString();
        String[] idArray = new String[idStr.length()];
        for (int i = 0; i < idStr.length(); i++) {
            idArray[i] = String.valueOf(idStr.charAt(i));
        }
        String[] melt = melt(SALT);
        String[] encryptArray = new String[idArray.length + 3];
        encryptArray[0] = melt[0];
        encryptArray[encryptArray.length - 1] = melt[melt.length - 1];
        encryptArray[encryptArray.length / 2] = melt[melt.length / 2];
        int j = 0;
        for (int i = 0; i < encryptArray.length; i++) {
            if (i == 0 || i == encryptArray.length - 1 || i == encryptArray.length / 2) {
                continue;
            }
            encryptArray[i] = idArray[j++];
        }
        StringBuilder r = new StringBuilder();
        for (String s : encryptArray) {
            r.append(s);
        }
        return r.toString();
    }

    /**
     * @param encryptId 加密后id
     * @return
     */
    private static String decrypt(String encryptId) {
        // 被解密ID不合法
        if (encryptId == null || encryptId.length() < 9 || encryptId.length() > 21 || !NumberUtils.isDigits(encryptId)) {
            return null;
        }
        // 被解密ID加密规则不合法
        List<Integer> saltArr = Arrays.asList(melt(SALT)).stream().map(v -> Integer.valueOf(v) + '0').collect(Collectors.toList());
        if (encryptId.charAt(0) != saltArr.get(0)
                || encryptId.charAt(encryptId.length() / 2) != saltArr.get(1)
                || encryptId.charAt(encryptId.length() - 1) != saltArr.get(2)) {
            return null;
        }
        int length = encryptId.length();
        String[] realIdArray = new String[length - 3];
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == length / 2 || i == length - 1) {
                continue;
            }
            realIdArray[j++] = String.valueOf(encryptId.charAt(i));
        }
        StringBuilder r = new StringBuilder();
        for (String s : realIdArray) {
            r.append(s);
        }
        return r.toString();

    }

    private static String[] melt(String salt) {
        int length = salt.length();
        String[] melt = new String[length];
        for (int i = 0; i < melt.length; i++) {
            melt[i] = String.valueOf(salt.charAt(i) % 5);
        }
        return melt;
    }

    public static void main(String[] args) {
        String encrypt = encrypt(201139431L);
        System.out.println(encrypt);
        String decrypt = "200030001";
        String decrypt1 = decrypt(decrypt);
        System.out.println(decrypt1);
        System.out.println(Long.valueOf("1231231231231231231"));
    }
}
