package site.dopplerxd.ui;

import java.util.Random;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {site.dopplerxd.ui}
 * @Date: {2024/3/26}
 * @Time: {16:42}
 */
public class Codeutil { // 验证码
    static char[] codeUnit = new char[36];
    static {
        int index = 0;
        while(index < 36) {
            if(index < 10) {
                codeUnit[index] = (char) ('0' + index);
            }
            else {
                codeUnit[index] = (char) ('a' + index - 10);
            }
            index++;
        }
    }
    static Random r = new Random();
    public static String getCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(codeUnit[r.nextInt(36)]);
        }
        return builder.toString();
    }
}
