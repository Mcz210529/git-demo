package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.anno.Log;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;

    @GetMapping
    public Result list() {
        log.info("查询全部部门数据：");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        log.info("根据ID删除部门信息:{}", id);
        deptService.removeById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("增加部门信息：{}", dept);
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.save(dept);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result listId(@PathVariable Integer id) {
        log.info("根据ID查询部门数据：{}", id);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);//

        List<Dept> list = deptMapper.selectList(wrapper);
        if (!list.isEmpty()) {
            return Result.success(list.get(0));
        } else {
            return Result.error("部门不存在");
        }


    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("根据ID和名字修改部门数据：{}", dept);
        deptService.updateById(dept);
        return Result.success();
    }

}
