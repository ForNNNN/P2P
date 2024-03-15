package com.yyw.p2p.servicecore.service.impl;

import com.alibaba.excel.EasyExcel;
import com.yyw.p2p.servicecore.entity.Dict;
import com.yyw.p2p.servicecore.entity.dto.ExcelDictDto;
import com.yyw.p2p.servicecore.listener.ExcelDictDtoListener;
import com.yyw.p2p.servicecore.mapper.DictMapper;
import com.yyw.p2p.servicecore.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author yyw
 * @since 2024-03-09
 */

@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Transactional(rollbackFor = Exception.class) //任何异常出现时都进行回滚，用来处理excel文件传输到一半就失败的情况
    @Override
    public void importExcelFile(InputStream inputStream) {
        //除了可以接受字符串类型的参数还可以接受InputStream类型的参数
        EasyExcel.read(inputStream, ExcelDictDto.class, new ExcelDictDtoListener(dictMapper)).sheet().doRead();
        log.info("Excel导入成功");
    }

}
