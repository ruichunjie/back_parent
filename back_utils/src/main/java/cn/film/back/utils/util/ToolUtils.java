package cn.film.back.utils.util;

import cn.film.back.utils.exception.BusinessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.film.back.utils.enums.BusinessEnum.UNSUPPORTEDTYPE;

public class ToolUtils {

    private ToolUtils(){
        throw new RuntimeException("ToolUtils 工具类不允许实例化!");
    }

    private static final Pattern pattern = Pattern.compile("[0-9]*");

    /**
     * 是否是int类型
     * @param str
     * @return
     */
    public static boolean checkInt(String str) {
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 字符串转换为int类型
     * @param str
     * @return
     */
    public static Integer str2Int(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
           throw new BusinessException(UNSUPPORTEDTYPE);
        }
        return Integer.parseInt(str);
    }

    /**
     * 字符串转换为LocalDateTime
     * @param dateStr
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String dateStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateStr,df);
        return ldt;
    }

}
