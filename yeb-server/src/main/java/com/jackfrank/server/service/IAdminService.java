package com.jackfrank.server.service;

import com.jackfrank.server.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jackfrank.server.pojo.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jackfrank
 * @since 2022-08-28
 */
public interface IAdminService extends IService<Admin> {

    /**
    * @Description: 登录之后返回Token
    * @Param: [username, password, request]
    * @return: com.jackfrank.server.pojo.vo.JsonResult
    * @Author: jackfrank
    * @Date: 2022/8/28 23:28
    */
    JsonResult login(String username, String password, HttpServletRequest request);

    /**
    * @Description: 获取当前登录用户的信息
    * @Param: [username]
    * @return: com.jackfrank.server.pojo.Admin
    * @Author: jackfrank
    * @Date: 2022/8/29 0:04
    */
    Admin getAdminByUserName(String username);
}
