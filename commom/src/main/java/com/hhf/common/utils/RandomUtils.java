package com.hhf.common.utils;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 随机数生成
 */
public class RandomUtils {

    /**
     * 获取随机字符串  数字+字符
     * @param length
     * @return
     */
    public static String getRandomCharacterAndNumber(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
                // int choice = 97; // 指定字符串为小写字母
                val += (char) (choice + random.nextInt(26));
                // 数字
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 结果校验
     * @param str
     * @return
     */
    public static boolean isRandomUsable(String str) {
        String regExp = "^[0-9a-zA-Z]{6}$";
        Pattern pat = Pattern.compile(regExp);
        Matcher mat = pat.matcher(str);
        return mat.matches();
    }

    /**
     * 获取指定随机数中的一个
     * @param nums
     * @return
     */
    public static  String getNum(String [] nums) {
        int index=(int)(Math.random()*nums.length);
        String rand = nums[index];
        return rand;
    }

    /**
     * 获取指定范围内的一个数
     * @param min
     * @param max
     * @return
     */
    public static  Integer getNum(int min,int max) {
        return new Random().nextInt(max-min)+min;
    }

    /**
     * 获取指定分公司中的一个
     * @param branchCodes
     * @return
     */
    public static  String getBranchCode(List<String> branchCodes) {
        int index=(int)(Math.random()*branchCodes.size());
        String rand = branchCodes.get(index);
        return rand;
    }
}
