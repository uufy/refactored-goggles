package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//登录

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;


    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp){
        log.info("登录：{}",emp);
        LoginInfo  Info = empService.login(emp);
        if (Info != null){
            return Result.success(Info);
        }
        return Result.error("用户名或密码错误");
    }


}
