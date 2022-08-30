package com.jackfrank.server.controller;

import com.jackfrank.server.pojo.Admin;
import com.jackfrank.server.pojo.vo.AdminLogin;
import com.jackfrank.server.pojo.vo.JsonResult;
import com.jackfrank.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 用户登录控制器
 * @createTime : 2022/8/28 23:23
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回Token")
    @PostMapping("/login")
    public JsonResult login(AdminLogin adminLogin, HttpServletRequest request){
        return adminService.login(adminLogin.getUsername(), adminLogin.getPassword(), request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null == principal){
            return null;
        }

        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);

        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public JsonResult logout(){
        return JsonResult.ok("注销成功！");
    }
}
