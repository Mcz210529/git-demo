package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.BaseStream;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {



}
