package com.yyw.p2p.servicecore.controller.admin;

import com.yyw.p2p.common.exception.BusinessException;
import com.yyw.p2p.common.result.R;
import com.yyw.p2p.common.result.ResponseEnum;
import com.yyw.p2p.servicecore.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/admin/core/dict")
@Api(tags = "数据字典管理接口")
@CrossOrigin
@Slf4j
public class AdminDictController {
    @Resource
    private DictService dictService;

    @ApiOperation("Excel文件导入")
    @PostMapping("/import")
    public R importExcelFile(@ApiParam(value = "Excel数据字典文件", required = true) @RequestParam("file") MultipartFile file){
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            dictService.importExcelFile(inputStream);
            return R.ok().message("数据字典excel文件导入成功");
        } catch (Exception e) {
            System.out.println(e);
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
