package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.Role;
import com.jackfrank.server.mapper.RoleMapper;
import com.jackfrank.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jackfrank
 * @since 2022-08-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
