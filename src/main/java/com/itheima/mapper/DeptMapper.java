package com.itheima.mapper;


import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {


    //方法1：手动让实体类属性名和数据库字段名一致

//    @Results({
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "update_time",property = "updateTime")
//    })
    //方法3：开启mybatis的驼峰命名自动映射，符合下划线之后是大写字母的属性名就是驼峰映射

    //方法2：起别名
    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc;")
    List<Dept> findAll();

    //根据id删除部门

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    //根据id查询部门
    @Select("select id, name, create_time createTime, update_time updateTime from dept where id = #{id}")
    Dept getById(Integer id);

    //根据id更新部门
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
