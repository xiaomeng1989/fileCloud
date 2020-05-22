package com.xm.service.impl;

import com.xm.entity.User;
import com.xm.mapper.UserMapper;
import com.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 尹晓蒙
 * @date 2020-05-21 16:13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> testUser() {
        return userMapper.selectList(null);
    }
}
