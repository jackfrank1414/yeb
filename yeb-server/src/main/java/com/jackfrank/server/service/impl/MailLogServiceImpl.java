package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.MailLog;
import com.jackfrank.server.mapper.MailLogMapper;
import com.jackfrank.server.service.IMailLogService;
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
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
