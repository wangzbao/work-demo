package com.yolo.workdemo.util;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * csv工具
 */
public class CSVUtils {

    // 分隔符
    private static final String SEPARATOR = ",";
    // 换行隔符
    private static final String LINE_SEPARATOR = "\n";
    // 转义字符
    private static final String ESCAPE_CHAR = "\"";
    private static final int SIZE = 6;
    private static final String SYMBOL = "*";

    /**
     * @param fileName
     * @param lines
     * @param response
     * @throws IOException
     */
    public static void responseCSV(String fileName, List<Object[]> lines, HttpServletResponse response) throws IOException {
        Assert.hasText(fileName, "fileName is required");
        Assert.notNull(response, "response can't be null");

        response.setContentType("text/csv;charset=utf-8");
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName + ".csv", "UTF-8"));

        if (CollectionUtils.isEmpty(lines)) {
            return;
        }

        OutputStream out = response.getOutputStream();
        out.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});

        StringBuilder lineSB = new StringBuilder();
        int maxLineIdx = lines.size() - 1;
        for (int lineIdx = 0; lineIdx <= maxLineIdx; lineIdx++) {
            Object[] line = lines.get(lineIdx);
            int maxCellIdx = line.length - 1;
            for (int cellIdx = 0; cellIdx <= maxCellIdx; cellIdx++) {
                lineSB.append(escapeSpecialChar(line[cellIdx]));
                if (cellIdx < maxCellIdx) {
                    lineSB.append(SEPARATOR);
                }
            }
            if (lineIdx < maxLineIdx) {
                lineSB.append(LINE_SEPARATOR);
            }
            out.write(lineSB.toString().getBytes());
            out.flush();
            // clear
            lineSB.setLength(0);
        }
    }

    /**
     * @param cell
     * @return
     */
    private static String escapeSpecialChar(Object cell) {
        if (null == cell) {
            return "";
        }

        String str = cell.toString();
        if (str.contains("\"") || str.contains(SEPARATOR) || str.contains(LINE_SEPARATOR)) {
            if (str.contains(ESCAPE_CHAR)) {
                str = str.replaceAll(ESCAPE_CHAR, ESCAPE_CHAR + ESCAPE_CHAR);
            }
            str = ESCAPE_CHAR + str + ESCAPE_CHAR;
        }
        return str;
    }

    public static String commonDisplay(String value) {
        if (null == value || "".equals(value)) {
            return value;
        }
        int len = value.length();
        int pamaone = len / 2;
        int pamatwo = pamaone - 1;
        int pamathree = len % 2;
        StringBuilder stringBuilder = new StringBuilder();
        if (len <= 2) {
            if (pamathree == 1) {
                return SYMBOL;
            }
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value.charAt(len - 1));
        } else {
            if (pamatwo <= 0) {
                stringBuilder.append(value.substring(0, 1));
                stringBuilder.append(SYMBOL);
                stringBuilder.append(value.substring(len - 1, len));

            } else if (pamatwo >= SIZE / 2 && SIZE + 1 != len) {
                int pamafive = (len - SIZE) / 2;
                stringBuilder.append(value.substring(0, pamafive));
                for (int i = 0; i < SIZE; i++) {
                    stringBuilder.append(SYMBOL);
                }
                if ((pamathree == 0 && SIZE / 2 == 0) || (pamathree != 0 && SIZE % 2 != 0)) {
                    stringBuilder.append(value.substring(len - pamafive, len));
                } else {
                    stringBuilder.append(value.substring(len - (pamafive + 1), len));
                }
            } else {
                int pamafour = len - 2;
                stringBuilder.append(value.substring(0, 1));
                for (int i = 0; i < pamafour; i++) {
                    stringBuilder.append(SYMBOL);
                }
                stringBuilder.append(value.substring(len - 1, len));
            }
        }
        return stringBuilder.toString();
    }
}
