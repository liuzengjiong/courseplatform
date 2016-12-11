package com.courseplatform.services.impl;

import com.courseplatform.bean.User;
import com.courseplatform.dao.UserMapper;
import com.courseplatform.services.LoginService;
import com.courseplatform.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author chen cy
 * Created by ye on 2016/12/8.
 */

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param account
     *         账号
     * @param password
     *         密码
     * @param type
     *         用户类型(1:学生,0:老师)
     * @return 用户标识字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 20:12
     */
    @Override
    public String login(String account, String password, int type) {
        User user = userMapper.selectByPrimaryKey(account);
        if (null != user) {
            // 检验密码和身份
            if (user.getType() == type && user.getPassword().equals(password)) {
                return MD5Util.getMD5(user);
            }
        }
        return "";
    }
}
