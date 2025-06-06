package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {


    //查找全部部门
    List<Dept> findAll();
    //根据id删除部门
    void deleteById(Integer id);

    //新增部门
    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
