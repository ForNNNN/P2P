package com.yyw.p2p.servicecore.controller.admin;

import com.yyw.p2p.common.exception.BusinessException;
import com.yyw.p2p.common.exception.MyAssert;
import com.yyw.p2p.common.result.R;
import com.yyw.p2p.common.result.ResponseEnum;
import com.yyw.p2p.servicecore.entity.IntegralGrade;
import com.yyw.p2p.servicecore.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Optional;


/*
管理人员操作后台的请求
 */

@Api(tags = "积分等级管理接口")
@Slf4j
@RestController
@RequestMapping("/admin/core/integralGrade")
@CrossOrigin
public class AdminIntegralGradedController {
    @Resource
    private IntegralGradeService integralGradeService;

    //获取等级积分表中所有的数据
    @GetMapping("/list")
    @ApiOperation(value = "所有积分等级")
    public R listAll(){
        log.info("log info");
        log.warn("log warn");
        log.error("log error");
        return R.ok().data("list",integralGradeService.list()).message("成功获取积分列表");
    }

    //根据id逻辑删除积分等级
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "根据id删除积分数据",notes = "逻辑删除")
    public R removeById(@ApiParam(value = "积分记录id",example = "100", required = true) @PathVariable("id") Long id){
        boolean b = integralGradeService.removeById(id);
        if (b){
            return R.ok().message("成功删除该积分数据");
        }
        return R.fail().message("删除该积分数据失败");
    }

    //根据id新增一条积分等级
    @PostMapping("/save")
    @ApiOperation(value = "新增积分等级")
    public R save(@ApiParam(value = "积分等级对象",required = true) @RequestBody IntegralGrade integralGrade){
        MyAssert.notNull(integralGrade.getBorrowAmount(),ResponseEnum.BORROW_AMOUNT_NULL_ERROR);

        boolean b = integralGradeService.save(integralGrade);
        if (b){
            return R.ok().message("成功添加一条数据");
        }
        return R.fail().message("添加数据失败");
    }

    //根据id查询积分等级
    @GetMapping("/get/{id}")
    @ApiOperation(value = "查询积分等级")
    public R getById(@ApiParam(value = "积分等级id",required = true) @PathVariable("id") Integer id){
        Optional<IntegralGrade> integralGrade = Optional.ofNullable(integralGradeService.getById(id));
        if (integralGrade.isPresent()){
            return R.ok().data("record",integralGrade.get()).message("成功获取到该积分等级");
        }
        return R.fail().message("获取该积分等级失败");
    }

    //根据id更新积分等级
    @PutMapping("/update")
    @ApiOperation(value = "修改积分等级")
    public R updateById(@ApiParam(value = "积分等级对象",required = true) @RequestBody IntegralGrade integralGrade){
        boolean b = integralGradeService.updateById(integralGrade);
        if (b){
            return R.ok().message("成功更新一条数据");
        }
        return R.fail().message("更新数据失败");
    }

}
