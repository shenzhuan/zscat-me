package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class BizNumberUtil {

    public static int curttNo;

    private final static String dataFormatString = "yyMMddHHmmss";

    public synchronized static final String createBizId() {
        if (curttNo < 999) {
            curttNo++;
        } else {
            curttNo = 1;
        }
        String curttNoStr = String.valueOf(curttNo);
        while (curttNoStr.length() < 3) {
            ;
            curttNoStr = "0" + curttNoStr;
        }
        return new SimpleDateFormat(dataFormatString).format(new Date()) + curttNoStr;
    }

    public static void main(String[] args) {
        System.out.println(createBizId());
    }

}
