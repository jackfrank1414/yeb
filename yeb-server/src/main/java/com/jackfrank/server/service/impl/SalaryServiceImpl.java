package com.jackfrank.server.service.impl;

import com.jackfrank.server.pojo.Salary;
import com.jackfrank.server.mapper.SalaryMapper;
import com.jackfrank.server.service.ISalaryService;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

}
