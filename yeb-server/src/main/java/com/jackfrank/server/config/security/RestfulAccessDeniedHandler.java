package com.jackfrank.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackfrank.server.pojo.vo.JsonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 当访问接口没有权限时，自定义返回结果
 * @createTime : 2022/8/30 23:52
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonResult jsonResult = JsonResult.errorMsg("尚未登录，请登录！");
        jsonResult.setStatus(403);
        writer.write(new ObjectMapper().writeValueAsString(jsonResult));
        writer.flush();
        writer.close();
    }
}
