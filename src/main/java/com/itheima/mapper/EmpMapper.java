package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Mapper
public interface EmpMapper {

    //--------原始方式分页查询---------

//  对总记录数进行查询
//    @Select("select count(*) from emp left outer join dept on emp.dept_id = dept.id")
//    public long count();
//
//  对员工进行分页查询
//    @Select("select emp.*,dept.name deptName from emp left outer join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);


    //-*-----mybatis-plus方式分页查询---------
    //结尾不能加分号

    //@Select("select emp.*,dept.name deptName from emp left outer join dept on emp.dept_id = dept.id " +
    //       "order by emp.update_time desc ")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);


    //条件查询
    public List<Emp> list(EmpQueryParam empQueryParam);


    //添加员工和工作经历
    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键值 --主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);



    void deleteByIds(List<Integer> ids);


    //根据id查询员工信息
    Emp getById(Integer id);


    void updateById(Emp emp);



    /*
    *统计员工各职位人数
    *
    * */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();


    /*
    *
    * 统计员工性别数据
    *
    * */
    @MapKey("name")
    List<Map<String,Object>> countEmpGenderData();

    //根据用户名和密码查询员工信息
    @Select("select id,username,name  from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
