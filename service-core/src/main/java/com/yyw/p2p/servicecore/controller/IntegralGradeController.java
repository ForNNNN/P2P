package com.yyw.p2p.servicecore.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author yyw
 * @since 2024-03-09
 */

@Api(tags = "用户积分等级接口")
@RestController
@RequestMapping("/web/core/integralGrade")
public class IntegralGradeController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public void test(){
        return;
    }
}

