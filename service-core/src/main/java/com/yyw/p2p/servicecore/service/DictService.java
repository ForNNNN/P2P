package com.yyw.p2p.servicecore.service;

import com.yyw.p2p.servicecore.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author yyw
 * @since 2024-03-09
 */


public interface DictService extends IService<Dict> {
    void importExcelFile(InputStream inputStream);
}
