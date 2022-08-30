package com.jackfrank.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jackfrank.server.config.security.JwtTokenUtil;
import com.jackfrank.server.pojo.Admin;
import com.jackfrank.server.mapper.AdminMapper;
import com.jackfrank.server.pojo.vo.JsonResult;
import com.jackfrank.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jackfrank
 * @since 2022-08-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminMapper adminMapper;

    /**
     * @param username
     * @param password
     * @param request
     * @Description: 登录之后返回Token
     * @Param: [username, password, request]
     * @return: com.jackfrank.server.pojo.vo.JsonResult
     * @Author: jackfrank
     * @Date: 2022/8/28 23:28
     */
    @Override
    public JsonResult login(String username, String password, HttpServletRequest request) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())){
            return JsonResult.errorMsg("用户名或密码不正确！");
        }
        if(!userDetails.isEnabled()){
            return JsonResult.errorMsg("账号被禁用，请联系管理员！");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成Token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return JsonResult.ok("登录成功！", tokenMap);
    }

    /**
     * @param username
     * @Description: 获取当前登录用户的信息
     * @Param: [username]
     * @return: com.jackfrank.server.pojo.Admin
     * @Author: jackfrank
     * @Date: 2022/8/29 0:04
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }
}
