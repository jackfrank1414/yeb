package com.jackfrank.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackfrank.server.pojo.vo.JsonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 当未登录或者Token失效时访问接口，自定义返回结果
 * @createTime : 2022/8/30 0:15
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        JsonResult jsonResult = JsonResult.errorMsg("权限不足，请联系管理员！");
        jsonResult.setStatus(401);
        writer.write(new ObjectMapper().writeValueAsString(jsonResult));
        writer.flush();
        writer.close();
    }
}
