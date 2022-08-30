package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.Oplog;
import com.jackfrank.server.mapper.OplogMapper;
import com.jackfrank.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
