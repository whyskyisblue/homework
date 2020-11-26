package com.example.main.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ControllerUtil {

    public static void errorAlert(String msg,
                                  String redirectUrl,
                                  HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        out.println("<script>");
        out.println("alert('" + msg + "')");
        out.println("location.replace('" + redirectUrl + "')");
        out.println("</script>");
        out.close();
    }

}
