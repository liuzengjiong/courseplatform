package com.courseplatform.services;

import com.courseplatform.bean.User;
import com.courseplatform.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author chen cy
 * Created by ye on 2016/12/8.
 */

@Service
public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private UserMapper userMapper;

    public String login(String account, String password, int type) {
        User user = userMapper.selectByPrimaryKey(account);
        if (null != user) {
            // 检验密码和身份
            if (user.getType() == type && user.getPassword().equals(password)) {
                String str = account + password + type;
                // 生成一个MD5摘要
                MessageDigest messageDigest = null;
                try {
                    messageDigest = MessageDigest.getInstance("MD5");
                    // 计算MD5
                    messageDigest.update(str.getBytes());
                    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
                    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
                    return new BigInteger(1, messageDigest.digest()).toString(16);
                } catch (NoSuchAlgorithmException e) {
                    LOG.error("登录错误",e.getMessage());
                }
            }
        }
        return "";
    }
}
