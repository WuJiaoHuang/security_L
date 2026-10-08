package com.wu.secur.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebUtils {

    /**
     * 将字符串以 JSON 的形式写入响应
     *
     * @param response HttpServletResponse
     * @param string   要返回给前端的字符串
     * @return null
     */
    public static String renderString(
            HttpServletResponse response,
            String string) {

        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().print(string);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}