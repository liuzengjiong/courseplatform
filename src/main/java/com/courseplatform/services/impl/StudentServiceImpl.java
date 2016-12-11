package com.courseplatform.services.impl;

import com.courseplatform.bean.User;
import com.courseplatform.dao.UserMapper;
import com.courseplatform.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author chen cy
 * Created by ye on 2016/12/11.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User info(String account) {
        return userMapper.selectByPrimaryKey(account);
    }

}
