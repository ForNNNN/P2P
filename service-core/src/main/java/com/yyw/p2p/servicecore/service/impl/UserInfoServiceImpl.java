package com.yyw.p2p.servicecore.service.impl;

import com.yyw.p2p.servicecore.entity.UserInfo;
import com.yyw.p2p.servicecore.mapper.UserInfoMapper;
import com.yyw.p2p.servicecore.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author yyw
 * @since 2024-03-09
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
