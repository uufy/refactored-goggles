package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result page(Integer page, Integer pageSize, String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询，当前页码{},每页记录数{},姓名{},性别{},开始{},结束{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp>  pageResult =  empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }


    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询{}",empQueryParam);
        PageResult<Emp>  pageResult =  empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception{
        log.info("新增员工，员工信息：{}",emp);
        empService.save(emp);
        return Result.success();
    }


    //数组删除
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("批量删除员工，员工id：{}", Arrays.toString(ids));
//        return Result.success();
//    }

    //集合删除
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工，员工id：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //查询员工
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工信息，员工id：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }



    @PutMapping
    public Result  update(@RequestBody Emp emp){
        log.info("更新员工信息{}",emp);
        empService.update(emp);
        return Result.success();
    }






}
