package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;




    //-------原始分页查询-------

//    @Override
//    public PageResult<Emp> page( Integer page, Integer pageSize) {
//        //@RequestParam(defaultValue = "10")可以用该注解生成默认值
//
//        if (page==null){
//            page=1;
//        }
//        if (pageSize==null){
//            pageSize=10;
//        }
//
//
//        //调用mapper接口，查询总记录数
//        long total =empMapper.count();
//
//        //查询结果列表
//        Integer start = (page-1)*pageSize;
//        List<Emp> rows = empMapper.list( start,pageSize);
//        //封装结果
//
//        return new PageResult<Emp>( total,rows);
//    }


    //-------使用MyBatisPlus的PageHelper分页查询-------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //设置分页参数(PageHelper)
//        PageHelper.startPage(page,pageSize);
//
//        //执行查询
//        List<Emp> empList = empMapper.list( name, gender, begin, end);
//        //解析查询结果，封装
//        Page<Emp> p =(Page<Emp>) empList;
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        //执行查询
        List<Emp> empList = empMapper.list( empQueryParam);
        //解析查询结果，封装
        Page<Emp> p =(Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) throws Exception {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empid赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工信息"+emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }


    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据id删除员工旧的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //再添加新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empid赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }


    }

    @Override
    public LoginInfo login(Emp emp) {
        //调用mapper接口，根据用户名查询员工信息
        Emp e=empMapper.selectByUsernameAndPassword(emp);
        //判断是否存在员工，存在就返回登录成功信息
        if (e!=null){
            log.info("登录成功，员工信息：{}",e);
            //生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);

        }

        //不存在，返回null

        return null;
    }


}
