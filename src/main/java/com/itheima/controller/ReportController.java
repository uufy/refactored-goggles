package com.itheima.controller;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;


    //统计员工各职位人数
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("查询员工职位数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    //统计员工性别
    @GetMapping("/empGenderData")
    public Result getEmpSexData(){
        log.info("查询员工性别数据");
        List<Map<String,Object>> DataOption = reportService.getEmpGenderData();
        return Result.success(DataOption);
    }




}
