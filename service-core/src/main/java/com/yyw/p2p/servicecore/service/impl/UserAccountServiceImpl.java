package com.yyw.p2p.servicecore.service.impl;

import com.yyw.p2p.servicecore.mapper.UserAccountMapper;
import com.yyw.p2p.servicecore.entity.UserAccount;
import com.yyw.p2p.servicecore.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author yyw
 * @since 2024-03-09
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
