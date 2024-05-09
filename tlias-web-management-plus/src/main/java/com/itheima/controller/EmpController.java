package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.anno.Log;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private EmpMapper empMapper;

    /*查询员工信息*/
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("查询员工数据：{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);

        // 构建查询条件
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (gender != null) {
            queryWrapper.eq("gender", gender);
        }
        if (begin != null) {
            queryWrapper.ge("hire_date", begin);
        }
        if (end != null) {
            queryWrapper.le("hire_date", end);
        }

        // 分页查询
        Page<Emp> empPage = new Page<>(page, pageSize);
        IPage<Emp> empIPage = empService.page(empPage, queryWrapper);

        // 将查询结果封装到自定义的 PageBean 中
        PageBean pageBean = new PageBean();
        pageBean.setTotal(empIPage.getTotal());
        pageBean.setRows(empIPage.getRecords());


        return Result.success(pageBean);
    }


    @Log
    /*删除员工信息*/
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工信息：{}", ids);
        empMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("添加员工信息：{}", emp);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result listId(@PathVariable Integer id) {
        log.info("根据ID查询员工数据：{}", id);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);//

        List<Emp> list = empMapper.selectList(wrapper);
        if (!list.isEmpty()) {
            return Result.success(list.get(0));
        } else {
            return Result.error("员工不存在");
        }
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息：{}", emp);
        empService.updateById(emp);
        return Result.success();
    }


}
