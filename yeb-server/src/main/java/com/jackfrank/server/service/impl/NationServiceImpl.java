package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.Nation;
import com.jackfrank.server.mapper.NationMapper;
import com.jackfrank.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
