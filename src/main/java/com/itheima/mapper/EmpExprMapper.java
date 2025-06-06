package com.itheima.mapper;


import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    //批量保存员工工作经历

    void insertBatch(List<EmpExpr> exprList);


    void deleteByEmpIds(List<Integer> empIds);
}
