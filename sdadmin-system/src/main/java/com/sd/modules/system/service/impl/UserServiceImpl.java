package com.sd.modules.system.service.impl;

import com.sd.exception.BadRequestException;
import com.sd.modules.system.domain.User;
import com.sd.modules.system.mapper.UserMapper;
import com.sd.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author siyang
 * @create 2020-01-12 20:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) throws BadRequestException {

        User user = userMapper.getUserByUsername(username);
        if(user==null){
            throw new BadRequestException("用户不存在");

        }else{
            if(user.getEnabled()==0){
                throw new BadRequestException("用户未激活");
            }

            return user;
        }
    }
}
