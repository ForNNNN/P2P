package com.yyw.p2p.servicecore.mapper;

import com.yyw.p2p.servicecore.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyw.p2p.servicecore.entity.dto.ExcelDictDto;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author yyw
 * @since 2024-03-09
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDto> list);
}
