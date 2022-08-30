package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.Position;
import com.jackfrank.server.mapper.PositionMapper;
import com.jackfrank.server.service.IPositionService;
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
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
