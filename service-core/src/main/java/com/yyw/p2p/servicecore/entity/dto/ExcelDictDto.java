package com.yyw.p2p.servicecore.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelDictDto {
    @ExcelProperty("id")
    private Integer id;

    @ExcelProperty("上级id")
    private Integer parentId;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("值")
    private Integer value;

    @ExcelProperty("编码")
    private String dictCode;

}
