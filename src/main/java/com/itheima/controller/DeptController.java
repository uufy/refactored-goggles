package com.itheima.controller;


import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {


    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);



    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList =  deptService.findAll();
        return Result.success(deptList);

    }


    /*
    * 删除部门
    *
    * */
    //方式1

//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据删除部门"+id);
//        return Result.success();
//
//    }


    //方式2

//
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer id){
//        System.out.println("根据删除部门"+id);
//        return Result.success();
//
//    }


    //方式3
    //请求携带的参数与方法形参一致时，可以省略@RequestParam注解
    @DeleteMapping
    public Result delete( Integer id){
//        System.out.println("根据删除部门"+id);
        log.info("根据删除部门"+id);
        deptService.deleteById(id);
        return Result.success();

    }


    //添加部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("添加部门"+dept);
        log.info("添加部门"+dept);
        deptService.add(dept);
        return Result.success();

    }


    //根据id查询部门
    //如果形参和路径参数名一致，那么@PathVariable("id")的参数可以省略
    //只需要在形参上添加@PathVariable注解就可以了
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable  Integer id){
//        System.out.println("根据id查询部门"+id);
        log.info("根据id查询部门{}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);

    }

    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门"+dept);
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }


}
