package com.jingyu.framework.qunfang.QFsecurity.sms;

import com.jingyu.common.exception.ServiceException;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.qunfangqunzhi.constant.CommonUserConstants;
import com.jingyu.qunfangqunzhi.domain.CommonLoginUser;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author hjs
 */
@Service("userDetailsBySMSCode")
public class UserDetailsByPhonenumberServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsByPhonenumberServiceImpl.class);

    @Autowired
    private ICommonUsersService userService;



    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        CommonUser user = userService.selectCommonUserByPhoneNumber(phoneNumber);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", phoneNumber);
            throw new ServiceException("登录用户：" + phoneNumber+ " 不存在");
        } else if (CommonUserConstants.CommonUserStatus.DELETED.getValue().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", phoneNumber);
            throw new ServiceException("对不起，您的账号：" + phoneNumber+ " 已被删除");
        } else if (CommonUserConstants.CommonUserStatus.DISABLE.getValue().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", phoneNumber);
            throw new ServiceException("对不起，您的账号：" + phoneNumber+ " 已停用");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(CommonUser user) {
    	return new CommonLoginUser(user.getUserId(), null, user, null);
        // return new LoginUser(user, permissionService.getMenuPermission(user));
    }

}

