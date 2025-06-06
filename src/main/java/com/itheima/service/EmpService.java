package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);


    //新增员工
    void save(Emp emp) throws Exception;

    void delete(List<Integer> ids);

    //根据id进行员工查询
    Emp getInfo(Integer id);

    //修改员工
    void update(Emp emp);

    LoginInfo login(Emp emp);

    //分页查询
    /*
    * page  页码
    * pageSize  每页条数
    * */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,LocalDate begin, LocalDate end);



}
