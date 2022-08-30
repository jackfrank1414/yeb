package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.Menu;
import com.jackfrank.server.mapper.MenuMapper;
import com.jackfrank.server.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
