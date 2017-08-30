package com.zsCat.common.kafka.client.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lixu
 * @version 1.0
 * @created 15-5-5
 */
public class StackTraceUtil {

    public static String getStackTrace(Throwable exception) {

        StringWriter sw;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            return sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
