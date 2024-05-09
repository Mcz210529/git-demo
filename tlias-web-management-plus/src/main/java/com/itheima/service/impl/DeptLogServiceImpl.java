package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pojo.DeptLog;
import com.itheima.mapper.DeptLogMapper;
import com.itheima.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl extends ServiceImpl<DeptLogMapper,DeptLog> implements DeptLogService {


}
